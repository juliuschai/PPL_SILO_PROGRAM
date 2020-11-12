/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import javax.swing.JFrame;

/**
 *
 * @author Julius
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuDaftarSJButton = new javax.swing.JButton();
        menuDaftarItemButton = new javax.swing.JButton();
        menuBuatSJ = new javax.swing.JButton();
        menuDaftarSP = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MainPage");

        menuDaftarSJButton.setText("MenuDaftarSJ");
        menuDaftarSJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDaftarSJButtonActionPerformed(evt);
            }
        });

        menuDaftarItemButton.setText("MenuDaftarItem");
        menuDaftarItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDaftarItemButtonActionPerformed(evt);
            }
        });

        menuBuatSJ.setText("MenuBuatSJ");
        menuBuatSJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBuatSJActionPerformed(evt);
            }
        });

        menuDaftarSP.setText("MenuDaftarSP");
        menuDaftarSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDaftarSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuDaftarSJButton)
                    .addComponent(menuDaftarItemButton)
                    .addComponent(menuBuatSJ)
                    .addComponent(menuDaftarSP))
                .addGap(0, 289, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuDaftarItemButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDaftarSJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuBuatSJ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuDaftarSP)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuDaftarSJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDaftarSJButtonActionPerformed
        JFrame frame = new DaftarSJ();
        frame.setVisible(true);
    }//GEN-LAST:event_menuDaftarSJButtonActionPerformed

    private void menuDaftarItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDaftarItemButtonActionPerformed
        JFrame frame = new DaftarItem();
        frame.setVisible(true);
    }//GEN-LAST:event_menuDaftarItemButtonActionPerformed

    private void menuBuatSJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBuatSJActionPerformed
        JFrame frame = new BuatSJ();
        frame.setVisible(true);
    }//GEN-LAST:event_menuBuatSJActionPerformed

    private void menuDaftarSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDaftarSPActionPerformed
        JFrame frame = new DaftarSP();
        frame.setVisible(true);
    }//GEN-LAST:event_menuDaftarSPActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton menuBuatSJ;
    private javax.swing.JButton menuDaftarItemButton;
    private javax.swing.JButton menuDaftarSJButton;
    private javax.swing.JButton menuDaftarSP;
    // End of variables declaration//GEN-END:variables
}