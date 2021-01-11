/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Julius
 */
public class SJCtl {

    DBHandler dbHandler;
    MainPage mainPage;

    public SJCtl(MainPage mainPage) {
        this.addMainPage(mainPage);
    }

    public void addDBHanlder(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void addMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    // Start routes
    public void tampilkanDaftarSJ() {
        mainPage.tampilkanDaftarSJ();
    }

    public ArrayList<SuratJalan> getSJ() {
        List<String[]> results = dbHandler.getSJ();

        ArrayList<SuratJalan> suratJalans = new ArrayList<>();
        // Create SJ Objects
        for (String[] row : results) {
            SuratJalan suratJalan = new SuratJalan(row);
            suratJalans.add(suratJalan);
        }
        return suratJalans;
    }

    public ArrayList<SuratJalan> cariSJ(String keyword) {
        ArrayList<String[]> results = dbHandler.cariSJ(keyword);

        ArrayList<SuratJalan> suratJalans = new ArrayList<>();
        // Create SJ Objects
        for (String[] row : results) {
            SuratJalan suratJalan = new SuratJalan(row);
            suratJalans.add(suratJalan);
        }
        return suratJalans;
    }

    public void tampilkanDetailSJ(SuratJalan curSuratJalan) {
        var results = dbHandler.getSJItems(curSuratJalan);
        curSuratJalan.setItemBeli(results);
        mainPage.tampilkanDetailSJ(curSuratJalan);
    }

    public void prepareSJ(SuratJalan suratJalan) {
        suratJalan.status.prepareSJ();
        dbHandler.prepareSJ(suratJalan);
    }

    public void signSJ(SuratJalan suratJalan) {
        // Check if stock is enough
        HashMap<Integer, Integer> dbItems = dbHandler.getStock(suratJalan);
        HashMap<Integer, Integer> sjItems = suratJalan.itemsToDict();
        for (Integer key : sjItems.keySet()) {
            if (dbItems.get(key) >= sjItems.get(key)) {
                dbItems.put(key, dbItems.get(key) - sjItems.get(key));
            } else {
                // If item isn't enough
                JOptionPane.showMessageDialog(null, "Item tidak mencukupi! SJ "
                        + "di set sebagai pending");
                suratJalan.pendingSJ();
                dbHandler.pendingSJ(suratJalan);
                return;
            }
        }
        dbHandler.updateStock(dbItems);

        // Change status of SJ
        suratJalan.tanggalSelesai = LocalDate.now();
        suratJalan.status.signSJ();
        dbHandler.signSJ(suratJalan);
    }

    public void pendingSJ(SuratJalan suratJalan) {
        suratJalan.status.pendingSJ();
        dbHandler.pendingSJ(suratJalan);
    }

    public void tampilkanBuatSJForm() {
        mainPage.tampilkanBuatSJForm();
    }

    public int tampilkanConfirmDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah Anda "
                + "yakin ingin membuat SJ ini?",
                "Warning", JOptionPane.YES_NO_OPTION);
        return dialogResult;
    }

    public SuratJalan confirmBuatSJ(String[] input) {
        SuratJalan newSuratJalan = new SuratJalan(
                input[0],
                input[1],
                input[2],
                input[3],
                input[4]
        );
        String itemsStr = input[5];

        // Surat jalan may have no items
        if (itemsStr != null) {
            String[] parts = itemsStr.split(",");
            for (String part : parts) {
                // Process string
                String[] temp = part.trim().split(" ", 2);
                int jumlah = Integer.parseInt(temp[0].trim());
                String itemStr = temp[1].trim();

                // Get item from string
                String[] itemArr = dbHandler.itemFromStr(itemStr);
                if (itemArr == null) {
                    // If item doesn't exist
                    JOptionPane.showMessageDialog(null, "Item: " + itemStr
                            + " tidak ditemukan!");
                    return null;
                }
                Item item = new Item(itemArr);
                // add item to suratjalan
                newSuratJalan.items.add(newSuratJalan.new ItemBeli(item, jumlah));
            }
        }

        dbHandler.simpanBuatSJ(newSuratJalan);
        return newSuratJalan;
    }

    public void tampilkanEmailSJForm(SuratJalan curSuratJalan) {
        mainPage.tampilkanDetailSJ(curSuratJalan);
    }

    public void emailSJ(String emailTo, String emailSubject, String emailBody) {
        JOptionPane.showMessageDialog(null, "Email SJ Terkirim.");
        mainPage.tampilkanMainPage();
    }

    public void printSJ(SuratJalan suratJalan) {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(new TextPrinter(suratJalan.toString()));
        boolean doPrint = printJob.printDialog();
        if (doPrint) {
            try {
                printJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public void tampilkanDaftarSP() {
        mainPage.tampilkanDaftarSP();
    }

}
