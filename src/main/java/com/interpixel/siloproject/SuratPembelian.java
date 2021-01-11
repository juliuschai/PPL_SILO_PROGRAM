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
public class SuratPembelian {

    public String nomorInvoice;
    public String nomorPO;
    public String namaSuplier;
    public LocalDate tanggalOrder;
    public LocalDate tanggalSelesai;
    public Status status;
    public ArrayList<SuratPembelian.ItemBeli> items = new ArrayList<>();

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

    public void setItemBeli(ArrayList<String[]> results) {
        for (String[] result : results) {
            Item item = new Item(result);
            int jumlah = Integer.parseInt(result[7]);
            items.add(new SuratPembelian.ItemBeli(item, jumlah));
        }
    }

    public SuratPembelian(String[] row) {
        this(
                row[0],
                row[1],
                row[2],
                row[3],
                row[4],
                row[5]
        );
    }

    public SuratPembelian(String nomorInvoice, String nomorPO, String namaSuplier,
            String tanggalOrder, String tanggalSelesai, String status) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        this.nomorInvoice = nomorInvoice;
        this.nomorPO = nomorPO;
        this.namaSuplier = namaSuplier;
        this.tanggalOrder = LocalDate.parse(tanggalOrder, formatter);
        if (tanggalSelesai != null) {
            this.tanggalSelesai = LocalDate.parse(tanggalSelesai, formatter);
        }
        this.status = new Status(status);;
    }

    public Vector<String> toVector() {
        Vector<String> current = new Vector<>();
        current.add(this.nomorInvoice);
        current.add(this.nomorPO);
        current.add(this.namaSuplier);
        current.add(this.tanggalOrder.toString());
        if (this.tanggalSelesai == null) {
            current.add(null);
        } else {
            current.add(this.tanggalSelesai.toString());
        }
        current.add(this.status.toString());
        return current;
    }

    public void terimaSP() {
        this.status.terimaSP();
    }

    public void pendingSP() {
        this.status.pendingSP();
    }

    public class Status {

        State state;

        public Status(String status) {
            if (status.equals("new")) {
                this.state = new NewState();
            } else if (status.equals("pending")) {
                this.state = new PendingState();
            } else if (status.equals("completed")) {
                this.state = new CompletedState();
            } else {
                throw new UnsupportedOperationException("SP Status: " + status
                        + " undefined");
            }
        }

        private void setState(State newState) {
            this.state = newState;
        }

        public String toString() {
            return state.toString();
        }

        public void terimaSP() {
            state.terimaSP(this);
        }

        public void pendingSP() {
            state.pendingSP(this);
        }

    }

    interface State {

        String toString();

        void terimaSP(Status context);

        void pendingSP(Status context);
    }

    class NewState implements State {

        @Override
        public String toString() {
            return "new";
        }

        @Override
        public void terimaSP(Status context) {
            context.setState(new CompletedState());
        }

        @Override
        public void pendingSP(Status context) {
            context.setState(new PendingState());
        }

    }

    class CompletedState implements State {

        @Override
        public String toString() {
            return "completed";
        }

        @Override
        public void terimaSP(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for current state.");
        }

        @Override
        public void pendingSP(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for current state.");
        }

    }

    class PendingState implements State {

        @Override
        public String toString() {
            return "pending";
        }

        @Override
        public void terimaSP(Status context) {
            context.setState(new CompletedState());
        }

        @Override
        public void pendingSP(Status context) {
            throw new UnsupportedOperationException("Function should not be "
                    + "called for current state.");
        }

    }
}
