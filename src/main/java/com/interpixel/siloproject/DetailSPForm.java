/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julius
 */
public class DetailSPForm extends javax.swing.JPanel {

    private MainPage mainPage;
    private SuratPembelian suratPembelian;

    /**
     * Creates new form DetailSPForm
     */
    public DetailSPForm(MainPage mainPage, SuratPembelian curSuratPembelian) {
        initComponents();
        this.addMainPage(mainPage);
        this.suratPembelian = curSuratPembelian;
        populateForm();
        fillTable();
        updateEnabledButtons();
    }

    private void addMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    private void populateForm() {
        this.nomorInvoiceTxt.setText(suratPembelian.nomorInvoice);
        this.nomorPOTxt.setText(suratPembelian.nomorPO);
        this.namaSuplierTxt.setText(suratPembelian.namaSuplier);
        this.tanggalOrderTxt.setText(suratPembelian.tanggalOrder.toString());
        if (suratPembelian.tanggalSelesai != null) {
            this.tanggalSelesaiTxt.setText(suratPembelian.tanggalSelesai.toString());
        }
        this.statusTxt.setText(suratPembelian.status.toString());
    }

    private void fillTable() {
        for (SuratPembelian.ItemBeli item : this.suratPembelian.items) {
            Vector<String> row = item.toVector();
            ((DefaultTableModel) this.tabelItems1.getModel()).insertRow(this.tabelItems1.getRowCount(), row);
        }
    }

    private void updateEnabledButtons() {
        if (this.suratPembelian.status.toString().equals("new")) {
            acceptBtn.setEnabled(true);
            pendingBtn.setEnabled(true);
        } else if (this.suratPembelian.status.toString().equals("pending")) {
            acceptBtn.setEnabled(true);
            pendingBtn.setEnabled(false);
        } else if (this.suratPembelian.status.toString().equals("completed")) {
            acceptBtn.setEnabled(false);
            pendingBtn.setEnabled(false);
        } else {
            throw new UnsupportedOperationException("SuratPembelian status undefined");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelItems = new javax.swing.JTable();
        nomorInvoiceLbl = new javax.swing.JLabel();
        nomorInvoiceTxt = new javax.swing.JTextField();
        nomorPOLbl = new javax.swing.JLabel();
        nomorPOTxt = new javax.swing.JTextField();
        namaSuplierLbl = new javax.swing.JLabel();
        namaSuplierTxt = new javax.swing.JTextField();
        tanggalOrderLbl = new javax.swing.JLabel();
        tanggalOrderTxt = new javax.swing.JTextField();
        tanggalSelesaiLbl = new javax.swing.JLabel();
        tanggalSelesaiTxt = new javax.swing.JTextField();
        statusLbl = new javax.swing.JLabel();
        statusTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelItems1 = new javax.swing.JTable();
        pendingBtn = new javax.swing.JButton();
        acceptBtn = new javax.swing.JButton();

        tabelItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barcode", "Judul", "Deskripsi", "Pemanufaktur", "Stock", "URL", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelItems);

        nomorInvoiceLbl.setText("Nomor Invoice");

        nomorInvoiceTxt.setEditable(false);
        nomorInvoiceTxt.setToolTipText("");

        nomorPOLbl.setText("Nomor PO");

        nomorPOTxt.setEditable(false);
        nomorPOTxt.setToolTipText("");

        namaSuplierLbl.setText("Nama Suplier");

        namaSuplierTxt.setEditable(false);
        namaSuplierTxt.setToolTipText("");

        tanggalOrderLbl.setText("Tanggal Order");

        tanggalOrderTxt.setEditable(false);
        tanggalOrderTxt.setToolTipText("");

        tanggalSelesaiLbl.setText("Tanggal Selesai");

        tanggalSelesaiTxt.setEditable(false);
        tanggalSelesaiTxt.setToolTipText("");

        statusLbl.setText("Status");

        statusTxt.setEditable(false);
        statusTxt.setToolTipText("");

        tabelItems1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Barcode", "Judul", "Deskripsi", "Pemanufaktur", "Stock", "URL", "Jumlah"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabelItems1);

        pendingBtn.setText("Pending");
        pendingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingBtnActionPerformed(evt);
            }
        });

        acceptBtn.setText("Accept");
        acceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusLbl)
                                    .addComponent(tanggalSelesaiLbl)
                                    .addComponent(tanggalOrderLbl))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tanggalOrderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tanggalSelesaiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomorPOLbl)
                                    .addComponent(nomorInvoiceLbl)
                                    .addComponent(namaSuplierLbl))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namaSuplierTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomorPOTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomorInvoiceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acceptBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pendingBtn, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomorInvoiceLbl)
                    .addComponent(nomorInvoiceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomorPOLbl)
                    .addComponent(nomorPOTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendingBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaSuplierLbl)
                    .addComponent(namaSuplierTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggalOrderLbl)
                    .addComponent(tanggalOrderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggalSelesaiLbl)
                    .addComponent(tanggalSelesaiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLbl)
                    .addComponent(statusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void acceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptBtnActionPerformed
        mainPage.terimaSP(suratPembelian);
        updateEnabledButtons();
    }//GEN-LAST:event_acceptBtnActionPerformed

    private void pendingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingBtnActionPerformed
        mainPage.pendingSP(suratPembelian);
        updateEnabledButtons();
    }//GEN-LAST:event_pendingBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel namaSuplierLbl;
    private javax.swing.JTextField namaSuplierTxt;
    private javax.swing.JLabel nomorInvoiceLbl;
    private javax.swing.JTextField nomorInvoiceTxt;
    private javax.swing.JLabel nomorPOLbl;
    private javax.swing.JTextField nomorPOTxt;
    private javax.swing.JButton pendingBtn;
    private javax.swing.JLabel statusLbl;
    private javax.swing.JTextField statusTxt;
    private javax.swing.JTable tabelItems;
    private javax.swing.JTable tabelItems1;
    private javax.swing.JLabel tanggalOrderLbl;
    private javax.swing.JTextField tanggalOrderTxt;
    private javax.swing.JLabel tanggalSelesaiLbl;
    private javax.swing.JTextField tanggalSelesaiTxt;
    // End of variables declaration//GEN-END:variables
}
