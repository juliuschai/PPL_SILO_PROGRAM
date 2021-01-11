/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Julius
 */
public class SPCtl {
    DBHandler dbHandler;
    MainPage mainPage;

    public SPCtl(MainPage mainPage) {
        this.addMainPage(mainPage);
    }

    public void addDBHanlder(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void addMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void tampilkanDaftarSP() {
        mainPage.tampilkanDaftarSP();
    }
    
    public ArrayList<SuratPembelian> getSPs() {
        List<String[]> results = dbHandler.getSPs();

        ArrayList<SuratPembelian> sps = new ArrayList<>();
        // Create SP Objects
        for (String[] row : results) {
            SuratPembelian sp = new SuratPembelian(row);
            sps.add(sp);
        }
        return sps;
    }

    public ArrayList<SuratPembelian> cariSP(String keyword) {
        List<String[]> results = dbHandler.cariSP(keyword);

        ArrayList<SuratPembelian> sps = new ArrayList<>();
        // Create SP Objects
        for (String[] row : results) {
            SuratPembelian sp = new SuratPembelian(row);
            sps.add(sp);
        }
        return sps;
    }

    public void tampilkanDetailSP(SuratPembelian curSuratPembelian) {
        var results = dbHandler.getSPItems(curSuratPembelian);
        curSuratPembelian.setItemBeli(results);
        mainPage.tampilkanDetailSP(curSuratPembelian);
    }

    public void terimaSP(SuratPembelian suratPembelian) {
        // Check if stock is enough
        HashMap<Integer, Integer> dbItems = dbHandler.getStock(suratPembelian.itemIdsToArr());
        HashMap<Integer, Integer> spItems = suratPembelian.itemsToDict();
        for (Integer key : spItems.keySet()) {
            // Add items from SuratPembelian
            dbItems.put(key, dbItems.get(key) + spItems.get(key));
        }
        dbHandler.updateStock(dbItems);

        // Change status of SJ
        suratPembelian.tanggalSelesai = LocalDate.now();
        suratPembelian.status.terimaSP();
        dbHandler.terimaSP(suratPembelian);
    }

    public void pendingSP(SuratPembelian suratPembelian) {
        suratPembelian.status.pendingSP();
        dbHandler.pendingSP(suratPembelian);
    }


}
