/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.util.Vector;

/**
 *
 * @author Julius
 */
public class Item {

    public int id;
    public String barcode;
    public String judul;
    public String deskripsi;
    public String pemanufaktur;
    public int stock;
    public String url;

    public Item(String[] row) {
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

    public String toString() {
        String res = "id: " + id + "\n"
                + "Barcode: " + barcode + "\n"
                + "Judul: " + judul + "\n"
                + "Deskripsi: " + deskripsi + "\n"
                + "Pemanufaktur: " + pemanufaktur + "\n"
                + "Stock: " + stock + "\n"
                + "URL: " + url + "\n";
        return res;
    }
    
    // Constructs Item from fields
    public Item(String id, String barcode, String judul, String deskripsi, 
            String pemanufaktur, String stock, String url) {

        this.id = Integer.parseInt(id);
        this.barcode = barcode;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.pemanufaktur = pemanufaktur;
        this.stock = Integer.parseInt(stock);
        this.url = url;
    }

    // Construct Item baru
    public Item(String barcode, String judul, String deskripsi, 
            String pemanufaktur, String stock, String url) {

        this.barcode = barcode;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.pemanufaktur = pemanufaktur;
        this.stock = Integer.parseInt(stock);
        this.url = url;
    }

    public Vector<String> toVector() {
        Vector<String> current = new Vector<>();
        current.add(String.valueOf(this.id));
        current.add(this.barcode);
        current.add(this.judul);
        current.add(this.deskripsi);
        current.add(this.pemanufaktur);
        current.add(String.valueOf(this.stock));
        current.add(this.url);
        return current;
    }
}
