/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Julius
 */
public class ItemCtl {
    
    DBHandler dbHandler;
    MainPage mainPage;

    public ItemCtl(MainPage mainPage) {
        this.addMainPage(mainPage);
    }

    public void addDBHanlder(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }

    public void addMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    public void tampilkanDaftarItem() {
        mainPage.tampilkanDaftarItem();
    }

    public void tampilkanItemBaruForm() {
        mainPage.tampilkanItemBaruForm();
    }
    
    public void tampilkanGagalBuatItemDialog() {
        int result = JOptionPane.showConfirmDialog(null, "Gagal membuat item, "
                + "terdapat field yang kosong. Cancel pembuatan item?",
                "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            mainPage.tampilkanDaftarItem();
        }
    }
    
    public void simpanItemBaru(String[] input) {
        dbHandler.simpanItemBaru(input);
    }

    public void tampilkanEditItemForm(Item curItem) {
        mainPage.tampilkanEditItemForm(curItem);
    }

    public void simpanEditItem(String[] input) {
        dbHandler.simpanEditItem(input);
    }
    public ArrayList<Item> getItems() {
        List<String[]> results = dbHandler.getItems();

        ArrayList<Item> items = new ArrayList<>();
        // Create SJ Objects
        for (String[] row : results) {
            Item item = new Item(row);
            items.add(item);
        }
        return items;
    }

}
