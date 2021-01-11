/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Julius
 */
public class DBHandler {

    private Connection myConnection;

    protected static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException {
        String dbURL = "jdbc:mysql://localhost:3306/silo";

        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(dbURL, "root", "");
        return con;
    }

    // Get all SJs from db
    public ArrayList<String[]> getItems() {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM items");

            ResultSet rs = st.executeQuery();

            ArrayList<String[]> results = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("id"),
                    rs.getString("barcode"),
                    rs.getString("judul"),
                    rs.getString("deskripsi"),
                    rs.getString("pemanufaktur"),
                    rs.getString("stock"),
                    rs.getString("url"),
                };
                results.add(row);
            }

            rs.close();
            st.close();
            con.close();

            return results;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void simpanItemBaru(String[] input) {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("INSERT INTO items"
                    + "(barcode, judul, deskripsi, pemanufaktur, stock, url) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            st.setString(1, input[0]);
            st.setString(2, input[1]);
            st.setString(3, input[2]);
            st.setString(4, input[3]);
            st.setString(5, input[4]);
            st.setString(6, input[5]);
            st.executeUpdate();

            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void simpanEditItem(String[] input) {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("UPDATE items SET "
                    + "barcode = ?, "
                    + "judul = ?, "
                    + "deskripsi = ?, "
                    + "pemanufaktur = ?, "
                    + "stock = ?, "
                    + "url = ?"
                    + "WHERE id = ?");

            st.setString(1, input[1]);
            st.setString(2, input[2]);
            st.setString(3, input[3]);
            st.setString(4, input[4]);
            st.setString(5, input[5]);
            st.setString(6, input[6]);
            st.setString(7, input[0]);
            st.executeUpdate();

            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Get all SJs from db
    public ArrayList<String[]> getSJ() {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM surat_jalans");

            ResultSet rs = st.executeQuery();

            ArrayList<String[]> results = new ArrayList<>();

            while (rs.next()) {
                // special case for when tanggalSelesai is null
                String tanggalSelesai = null;
                if (rs.getTimestamp("tanggalSelesai") == null) {
                    tanggalSelesai = null;
                } else {
                    tanggalSelesai = rs.getTimestamp("tanggalSelesai").toString();
                }
                String[] row = {
                    rs.getString("nomorInvoice"),
                    rs.getString("nomorSj"),
                    rs.getString("namaCustomer"),
                    rs.getString("emailCustomer"),
                    rs.getTimestamp("tanggalOrder").toString(),
                    tanggalSelesai,
                    rs.getString("status")
                };
                results.add(row);
            }

            rs.close();
            st.close();
            con.close();

            return results;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get SJs giving a certain keyword
    public ArrayList<String[]> cariSJ(String keyword) {
        try {
            // Append % symbol
            keyword = "%" + keyword + "%";
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM surat_jalans "
                    + "WHERE nomorInvoice LIKE ? "
                    + "OR nomorSj LIKE ? "
                    + "OR namaCustomer LIKE ? "
                    + "OR emailCustomer LIKE ? "
                    + "OR nomorInvoice LIKE ? "
                    + "OR tanggalOrder LIKE ? "
                    + "OR tanggalSelesai LIKE ? "
                    + "OR status LIKE ? ");

            st.setString(1, keyword);
            st.setString(2, keyword);
            st.setString(3, keyword);
            st.setString(4, keyword);
            st.setString(5, keyword);
            st.setString(6, keyword);
            st.setString(7, keyword);
            st.setString(8, keyword);
            ResultSet rs = st.executeQuery();

            ArrayList<String[]> results = new ArrayList<>();

            while (rs.next()) {
                // special case for when tanggalSelesai is null
                String tanggalSelesai = null;
                if (rs.getTimestamp("tanggalSelesai") == null) {
                    tanggalSelesai = null;
                } else {
                    tanggalSelesai = rs.getTimestamp("tanggalSelesai").toString();
                }
                String[] row = {
                    rs.getString("nomorInvoice"),
                    rs.getString("nomorSj"),
                    rs.getString("namaCustomer"),
                    rs.getString("emailCustomer"),
                    rs.getTimestamp("tanggalOrder").toString(),
                    tanggalSelesai,
                    rs.getString("status")
                };
                results.add(row);
            }

            rs.close();
            st.close();
            con.close();

            return results;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get items belonging to a SJ
    public ArrayList<String[]> getSJItems(SuratJalan curSuratJalan) {
        try {
            // Append % symbol
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("SELECT id, barcode, "
                    + "judul, deskripsi, pemanufaktur, stock, url, jumlah "
                    + "FROM surat_jalan_items "
                    + "INNER JOIN items ON items.id = surat_jalan_items.itemId "
                    + "WHERE nomorSJ = ? ");

            st.setString(1, curSuratJalan.nomorSJ);
            ResultSet rs = st.executeQuery();

            ArrayList<String[]> results = new ArrayList<>();

            while (rs.next()) {
                String[] row = {
                    rs.getString("id"),
                    rs.getString("barcode"),
                    rs.getString("judul"),
                    rs.getString("deskripsi"),
                    rs.getString("pemanufaktur"),
                    rs.getString("stock"),
                    rs.getString("url"),
                    rs.getString("jumlah")
                };
                results.add(row);
            }

            rs.close();
            st.close();
            con.close();

            return results;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Get 1 item from barcode or id
    public String[] itemFromStr(String itemStr) {
        String[] res = null;
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("SELECT * FROM items "
                    + "WHERE barcode = ? "
                    + "OR judul = ?");

            st.setString(1, itemStr);
            st.setString(2, itemStr);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                res = new String[]{
                    rs.getString("id"),
                    rs.getString("barcode"),
                    rs.getString("judul"),
                    rs.getString("deskripsi"),
                    rs.getString("pemanufaktur"),
                    rs.getString("stock"),
                    rs.getString("url")
                };
            }

            rs.close();
            st.close();
            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return res;
    }

    // Buat SJ baru
    public void simpanBuatSJ(SuratJalan suratJalan) {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("INSERT INTO surat_jalans"
                    + "(nomorInvoice, nomorSj, namaCustomer, emailCustomer, "
                    + "tanggalOrder, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?)");

            st.setString(1, suratJalan.nomorInvoice);
            st.setString(2, suratJalan.nomorSJ);
            st.setString(3, suratJalan.namaCustomer);
            st.setString(4, suratJalan.emailCustomer);
            st.setString(5, suratJalan.tanggalOrder.toString());
            st.setString(6, "new");
            st.executeUpdate();

            st.close();
            for (SuratJalan.ItemBeli itemBeli : suratJalan.items) {
                Item item = itemBeli.item;
                st = con.prepareStatement("INSERT INTO surat_jalan_items"
                        + "(nomorSj, itemId, jumlah) "
                        + "VALUES (?, ?, ?)");

                st.setString(1, suratJalan.nomorSJ);
                st.setString(2, String.valueOf(item.id));
                st.setString(3, String.valueOf(itemBeli.jumlah));
                st.executeUpdate();
            }
            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void prepareSJ(SuratJalan suratJalan) {
        updateStatusSJ(suratJalan);
    }

    public void signSJ(SuratJalan suratJalan) {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("UPDATE surat_jalans "
                    + "SET status = ?, "
                    + "tanggalSelesai = ? "
                    + "WHERE nomorSj = ?");

            st.setString(1, suratJalan.status.toString());
            st.setString(2, suratJalan.tanggalSelesai.toString());
            st.setString(3, suratJalan.nomorSJ);
            st.executeUpdate();

            st.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void pendingSJ(SuratJalan suratJalan) {
        updateStatusSJ(suratJalan);
    }

    // Propagate status of current SJ to dbHandler
    private void updateStatusSJ(SuratJalan suratJalan) {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st = con.prepareStatement("UPDATE surat_jalans "
                    + "SET status = ? "
                    + "WHERE nomorSj = ?");

            st.setString(1, suratJalan.status.toString());
            st.setString(2, suratJalan.nomorSJ);
            st.executeUpdate();

            st.close();

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Get current item stocks for a SJ
    public HashMap<Integer, Integer> getStock(SuratJalan suratJalan) {
        HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
        try {
            Connection con = initializeDatabase();
            PreparedStatement st;

            int[] ids = suratJalan.itemIdsToArr();
            for (int id : ids) {
                st = con.prepareStatement("SELECT id, stock "
                        + "FROM items WHERE id IN (?) ");

                st.setInt(1, id);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    result.put(rs.getInt("id"), rs.getInt("stock"));
                } else {
                    throw new UnsupportedOperationException("Item with id: "
                            + id + " Nnt found");
                }
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Decrease stock of Items from comleted suratJalan
    public void updateStock(HashMap<Integer, Integer> stock) {
        try {
            Connection con = initializeDatabase();
            PreparedStatement st;
            for (Integer key : stock.keySet()) {
                st = con.prepareStatement("UPDATE items SET stock = ? "
                        + "WHERE id = ?");
                st.setInt(1, stock.get(key));
                st.setInt(2, key);
                st.executeUpdate();
                st.close();
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
