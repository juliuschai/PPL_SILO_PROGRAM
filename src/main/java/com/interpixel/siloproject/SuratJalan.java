/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Julius
 */
public class SuratJalan {

    public String nomorInvoice;
    public String nomorSJ;
    public String namaCustomer;
    public String emailCustomer;
    public LocalDate tanggalOrder;
    public LocalDate tanggalSelesai;
    public SuratJalan.Status status;
    public ArrayList<SuratJalan.ItemBeli> items = new ArrayList<>();

    public class ItemBeli {

        Item item;
        int jumlah;

        public ItemBeli(Item item, int jumlah) {
            this.item = item;
            this.jumlah = jumlah;
        }

        public Vector<String> toVector() {
            var result = this.item.toVector();
            result.add(String.valueOf(this.jumlah));
            return result;
        }

    }
    
    public String toString() {
        String res = "Nomor Invoice: " + nomorInvoice + "\n"
                + "Nomor SJ: " + nomorSJ + "\n"
                + "Nama Customer: " + namaCustomer + "\n"
                + "Email Customer: " + emailCustomer + "\n"
                + "Tanggal Order: " + tanggalOrder + "\n"
                + "Tanggal Selesai: " + tanggalSelesai + "\n"
                + "Status: " + status + "\n"
                + "Items: \n"
                + itemsToString();
        return res;
    }

    private String itemsToString() {
        StringBuilder sb = new StringBuilder();
        String res = "";
        for (ItemBeli itemBeli : items) {
            sb.append(itemBeli.item.toString());
            sb.append(" ");
            sb.append(itemBeli.jumlah);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void setItemBeli(ArrayList<String[]> results) {
        for (String[] result : results) {
            Item item = new Item(result);
            int jumlah = Integer.parseInt(result[7]);
            items.add(new ItemBeli(item, jumlah));
        }
    }

    public HashMap<Integer, Integer> itemsToDict() {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (ItemBeli itemBeli : this.items) {
            result.put(itemBeli.item.id, itemBeli.jumlah);
        }
        return result;
    }

    public ArrayList<Integer> itemIdsToArrList() {
        ArrayList<Integer> result = new ArrayList<>();
        for (ItemBeli itemBeli : this.items) {
            result.add(itemBeli.item.id);
        }
        return result;
    }

    // item ids of cur SJ into int array, used to create sql array
    public int[] itemIdsToArr() {
        int length = this.items.size();
        int[] result = new int[length];
        for (int i = 0; i < this.items.size(); i++) {
            ItemBeli itemBeli = this.items.get(i);
            result[i] = itemBeli.item.id;
        }
        return result;
    }

    // Constructs SuratJalan from a row result set
    public SuratJalan(String[] row) {
        this(
                row[0],
                row[1],
                row[2],
                row[3],
                row[4],
                row[5],
                row[6]
        );
    }

    // Constructs SuratJalan from fields
    public SuratJalan(String nomorInvoice, String nomorSJ, String namaCustomer,
            String emailCustomer, String tanggalOrder, String tanggalSelesai,
            String status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        this.nomorInvoice = nomorInvoice;
        this.nomorSJ = nomorSJ;
        this.namaCustomer = namaCustomer;
        this.emailCustomer = emailCustomer;
        this.tanggalOrder = LocalDate.parse(tanggalOrder, formatter);
        if (tanggalSelesai != null) {
            this.tanggalSelesai = LocalDate.parse(tanggalSelesai, formatter);
        }
        this.status = new Status(status);
    }

    // Constructs SuratJalan from input (without tanggalSelesai and status)
    public SuratJalan(String nomorInvoice, String nomorSJ, String namaCustomer,
            String emailCustomer, String tanggalOrder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.nomorInvoice = nomorInvoice;
        this.nomorSJ = nomorSJ;
        this.namaCustomer = namaCustomer;
        this.emailCustomer = emailCustomer;
        this.tanggalOrder = LocalDate.parse(tanggalOrder, formatter);
        this.status = new Status("new");
    }

    public Vector<String> toVector() {
        Vector<String> current = new Vector<>();
        current.add(this.nomorInvoice);
        current.add(this.nomorSJ);
        current.add(this.namaCustomer);
        current.add(this.emailCustomer);
        current.add(this.tanggalOrder.toString());
        if (this.tanggalSelesai == null) {
            current.add(null);
        } else {
            current.add(this.tanggalSelesai.toString());
        }
        current.add(this.status.toString());
        return current;
    }

    public class Status {

        State state;

        private Status(String status) {
            if (status.equals("new")) {
                this.state = new NewState();
            } else if (status.equals("preparing")) {
                this.state = new PreparingState();
            } else if (status.equals("completed")) {
                this.state = new CompletedState();
            } else if (status.equals("pending")) {
                this.state = new PendingState();
            } else {
                throw new UnsupportedOperationException("Status string '"
                        + status + "' is defined");
            }
        }

        public String toString() {
            return state.toString();
        }

        private void setState(State newState) {
            this.state = newState;
        }

        public void prepareSJ() {
            state.prepareSJ(this);
        }

        public void signSJ() {
            state.signSJ(this);
        }

        public void pendingSJ() {
            state.pendingSJ(this);
        }

    }

    public void prepareSJ() {
        this.status.prepareSJ();
    }

    public void signSJ() {
        this.status.signSJ();
    }

    public void pendingSJ() {
        this.status.pendingSJ();
    }

    interface State {

        public String toString();

        void prepareSJ(Status context);

        void signSJ(Status context);

        void pendingSJ(Status context);

    }

    class NewState implements State {

        @Override
        public String toString() {
            return "new";
        }

        @Override
        public void prepareSJ(Status context) {
            context.setState(new PreparingState());
        }

        @Override
        public void signSJ(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for the current state");
        }

        @Override
        public void pendingSJ(Status context) {
            context.setState(new PendingState());
        }

    }

    class PreparingState implements State {

        @Override
        public String toString() {
            return "preparing";
        }

        @Override
        public void prepareSJ(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for the current state");
        }

        @Override
        public void signSJ(Status context) {
            context.setState(new CompletedState());
        }

        @Override
        public void pendingSJ(Status context) {
            context.setState(new PendingState());
        }

    }

    class CompletedState implements State {

        @Override
        public String toString() {
            return "completed";
        }

        @Override
        public void prepareSJ(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for the current state");
        }

        @Override
        public void signSJ(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for the current state");
        }

        @Override
        public void pendingSJ(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for the current state");
        }

    }

    class PendingState implements State {

        @Override
        public String toString() {
            return "pending";
        }

        @Override
        public void prepareSJ(Status context) {
            context.setState(new PreparingState());
        }

        @Override
        public void signSJ(Status context) {
            context.setState(new CompletedState());
        }

        @Override
        public void pendingSJ(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for the current state");
        }

    }

}
