/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Julius
 */
public class DetailSJForm extends javax.swing.JPanel {

    private MainPage mainPage;
    private SuratJalan suratJalan;

    /**
     * Creates new form DetailSJForm
     */
    public DetailSJForm(MainPage mainPage, SuratJalan curSuratJalan) {
        initComponents();
        this.addMainPage(mainPage);
        this.suratJalan = curSuratJalan;
        populateForm();
        fillTable();
        updateEnabledButtons();
    }

    private void addMainPage(MainPage mainPage) {
        this.mainPage = mainPage;
    }

    private void populateForm() {
        this.nomorInvoiceTxt.setText(suratJalan.nomorInvoice);
        this.nomorSJTxt.setText(suratJalan.nomorSJ);
        this.namaCustomerTxt.setText(suratJalan.namaCustomer);
        this.emailCustomerTxt.setText(suratJalan.emailCustomer);
        this.tanggalOrderTxt.setText(suratJalan.tanggalOrder.toString());
        if (suratJalan.tanggalSelesai != null) {
            this.tanggalSelesaiTxt.setText(suratJalan.tanggalSelesai.toString());
        }
        this.statusTxt.setText(suratJalan.status.toString());
    }

    private void fillTable() {
        for (SuratJalan.ItemBeli item : this.suratJalan.items) {
            Vector<String> row = item.toVector();
            ((DefaultTableModel) this.tabelItems.getModel()).insertRow(this.tabelItems.getRowCount(), row);
        }
    }

    private void updateEnabledButtons() {
        if (this.suratJalan.status.toString().equals("new")) {
            preparingBtn.setEnabled(true);
            signBtn.setEnabled(false);
            pendingBtn.setEnabled(true);
            emailBtn.setEnabled(true);
            printBtn.setEnabled(true);
        } else if (this.suratJalan.status.toString().equals("preparing")) {
            preparingBtn.setEnabled(false);
            signBtn.setEnabled(true);
            pendingBtn.setEnabled(true);
            emailBtn.setEnabled(false);
            printBtn.setEnabled(false);
        } else if (this.suratJalan.status.toString().equals("pending")) {
            preparingBtn.setEnabled(true);
            signBtn.setEnabled(true);
            pendingBtn.setEnabled(false);
            emailBtn.setEnabled(false);
            printBtn.setEnabled(false);
        } else if (this.suratJalan.status.toString().equals("completed")) {
            preparingBtn.setEnabled(false);
            signBtn.setEnabled(false);
            pendingBtn.setEnabled(false);
            emailBtn.setEnabled(false);
            printBtn.setEnabled(false);
        } else {
            throw new UnsupportedOperationException("SuratJalan status undefined");
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

        nomorInvoiceLbl = new javax.swing.JLabel();
        nomorInvoiceTxt = new javax.swing.JTextField();
        nomorSJLbl = new javax.swing.JLabel();
        nomorSJTxt = new javax.swing.JTextField();
        namaCustomerLbl = new javax.swing.JLabel();
        namaCustomerTxt = new javax.swing.JTextField();
        emailCustomerLbl = new javax.swing.JLabel();
        emailCustomerTxt = new javax.swing.JTextField();
        tanggalOrderLbl = new javax.swing.JLabel();
        tanggalOrderTxt = new javax.swing.JTextField();
        tanggalSelesaiLbl = new javax.swing.JLabel();
        tanggalSelesaiTxt = new javax.swing.JTextField();
        statusLbl = new javax.swing.JLabel();
        statusTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelItems = new javax.swing.JTable();
        pendingBtn = new javax.swing.JButton();
        preparingBtn = new javax.swing.JButton();
        signBtn = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        emailBtn = new javax.swing.JButton();

        nomorInvoiceLbl.setText("Nomor Invoice");

        nomorInvoiceTxt.setEditable(false);
        nomorInvoiceTxt.setToolTipText("");

        nomorSJLbl.setText("Nomor SJ");

        nomorSJTxt.setEditable(false);
        nomorSJTxt.setToolTipText("");

        namaCustomerLbl.setText("Nama Customer");

        namaCustomerTxt.setEditable(false);
        namaCustomerTxt.setToolTipText("");

        emailCustomerLbl.setText("Email Customer");

        emailCustomerTxt.setEditable(false);
        emailCustomerTxt.setToolTipText("");

        tanggalOrderLbl.setText("Tanggal Order");

        tanggalOrderTxt.setEditable(false);
        tanggalOrderTxt.setToolTipText("");

        tanggalSelesaiLbl.setText("Tanggal Selesai");

        tanggalSelesaiTxt.setEditable(false);
        tanggalSelesaiTxt.setToolTipText("");

        statusLbl.setText("Status");

        statusTxt.setEditable(false);
        statusTxt.setToolTipText("");

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

        pendingBtn.setText("Pending");
        pendingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pendingBtnActionPerformed(evt);
            }
        });

        preparingBtn.setText("Preparing");
        preparingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preparingBtnActionPerformed(evt);
            }
        });

        signBtn.setText("Sign");
        signBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signBtnActionPerformed(evt);
            }
        });

        printBtn.setText("Print Hardcopy");
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });

        emailBtn.setText("Send Softcopy");
        emailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomorSJLbl)
                            .addComponent(namaCustomerLbl)
                            .addComponent(emailCustomerLbl)
                            .addComponent(statusLbl)
                            .addComponent(tanggalSelesaiLbl)
                            .addComponent(nomorInvoiceLbl)
                            .addComponent(tanggalOrderLbl))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomorSJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namaCustomerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomorInvoiceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tanggalOrderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tanggalSelesaiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailCustomerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(preparingBtn)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(pendingBtn))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addComponent(signBtn)))
                            .addComponent(printBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(emailBtn, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomorInvoiceLbl)
                    .addComponent(nomorInvoiceTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preparingBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomorSJLbl)
                    .addComponent(nomorSJTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(signBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(namaCustomerLbl)
                    .addComponent(namaCustomerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendingBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailCustomerLbl)
                    .addComponent(emailCustomerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggalOrderLbl)
                    .addComponent(tanggalOrderTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggalSelesaiLbl)
                    .addComponent(tanggalSelesaiTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLbl)
                    .addComponent(statusTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void preparingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preparingBtnActionPerformed
        mainPage.prepareSJ(this.suratJalan);
        updateEnabledButtons();
    }//GEN-LAST:event_preparingBtnActionPerformed

    private void signBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signBtnActionPerformed
        mainPage.signSJ(this.suratJalan);
        updateEnabledButtons();
    }//GEN-LAST:event_signBtnActionPerformed

    private void pendingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pendingBtnActionPerformed
        mainPage.pendingSJ(this.suratJalan);
        updateEnabledButtons();
    }//GEN-LAST:event_pendingBtnActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed
        mainPage.printSJ(this.suratJalan);
    }//GEN-LAST:event_printBtnActionPerformed

    private void emailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailBtnActionPerformed
        mainPage.tampilkanEmailSJForm(this.suratJalan);
    }//GEN-LAST:event_emailBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton emailBtn;
    private javax.swing.JLabel emailCustomerLbl;
    private javax.swing.JTextField emailCustomerTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel namaCustomerLbl;
    private javax.swing.JTextField namaCustomerTxt;
    private javax.swing.JLabel nomorInvoiceLbl;
    private javax.swing.JTextField nomorInvoiceTxt;
    private javax.swing.JLabel nomorSJLbl;
    private javax.swing.JTextField nomorSJTxt;
    private javax.swing.JButton pendingBtn;
    private javax.swing.JButton preparingBtn;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton signBtn;
    private javax.swing.JLabel statusLbl;
    private javax.swing.JTextField statusTxt;
    private javax.swing.JTable tabelItems;
    private javax.swing.JLabel tanggalOrderLbl;
    private javax.swing.JTextField tanggalOrderTxt;
    private javax.swing.JLabel tanggalSelesaiLbl;
    private javax.swing.JTextField tanggalSelesaiTxt;
    // End of variables declaration//GEN-END:variables

}
