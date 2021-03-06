/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interpixel.siloproject;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Julius
 */
public class MainPage extends javax.swing.JFrame {

    private DBHandler dbHandler;
    private ItemCtl itemCtl;
    private SJCtl sjCtl;
    private SPCtl spCtl;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel;

    private DaftarItem daftarItem;
    private DaftarSJ daftarSJ;
    private BuatSJForm lastBuatSJForm;
    private ItemBaruForm lastItemBaruForm;
    private EditItemForm lastEditItemForm;
    private DetailSJForm lastDetailSJForm;
    private EmailSJForm lastEmailSJForm;
    private DaftarSP daftarSP;
    private DetailSPForm lastDetailSPForm;

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        setLayout(new BorderLayout());
        initComponents();
        initObjects();

    }

    public void initObjects() {
        dbHandler = new DBHandler();
        itemCtl = new ItemCtl(this);
        itemCtl.addDBHanlder(dbHandler);
        sjCtl = new SJCtl(this);
        sjCtl.addDBHanlder(dbHandler);
        spCtl = new SPCtl(this);
        spCtl.addDBHanlder(dbHandler);
        daftarItem = new DaftarItem(this);
        daftarSJ = new DaftarSJ(this);
        daftarSP = new DaftarSP(this);
        
        // Init JPanels
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        cardPanel.add(new JPanel(), "Empty Panel");
        cardPanel.add(daftarItem, "Daftar Item");
        cardPanel.add(daftarSJ, "Daftar SJ");
        cardPanel.add(daftarSP, "Daftar SP");
        setContentPane(cardPanel);
    }

    void tampilkanMainPage() {
        cardLayout.show(cardPanel, "Empty Panel");
    }

    // Start routes
    public void tampilkanDaftarItem() {
        daftarItem.refresh();
        cardLayout.show(cardPanel, "Daftar Item");
    }

    public ArrayList<Item> getItems() {
        return itemCtl.getItems();
    }

    public ArrayList<Item> cariItem(String keyword) {
        return itemCtl.cariItem(keyword);
    }

    public void onTampilkanItemBaruForm() {
        itemCtl.tampilkanItemBaruForm();
    }

    public void tampilkanItemBaruForm() {
        if (lastItemBaruForm != null) {
            cardPanel.remove(lastItemBaruForm);
        }
        lastItemBaruForm = new ItemBaruForm(this);
        cardPanel.add(lastItemBaruForm, "Detail SJ");
        cardLayout.show(cardPanel, "Detail SJ");
    }
    
    public void gagalBuatItem() {
        itemCtl.tampilkanGagalBuatItemDialog();
    }
    
    public void simpanItemBaru(String[] input) {
        itemCtl.simpanItemBaru(input);

        this.tampilkanDaftarItem();
    }

    public void onTampilkanEditItemForm(Item curItem) {
        itemCtl.tampilkanEditItemForm(curItem);
    }

    public void tampilkanEditItemForm(Item curItem) {
        if (lastEditItemForm != null) {
            cardPanel.remove(lastEditItemForm);
        }
        lastEditItemForm = new EditItemForm(this, curItem);
        cardPanel.add(lastEditItemForm, "Detail SJ");
        cardLayout.show(cardPanel, "Detail SJ");
    }

    public void simpanEditItem(String[] input) {
        itemCtl.simpanEditItem(input);

        this.tampilkanDaftarItem();
    }

    public void tampilkanDaftarSJ() {
        daftarSJ.refresh();
        cardLayout.show(cardPanel, "Daftar SJ");
    }

    public ArrayList<SuratJalan> getSJ() {
        return sjCtl.getSJ();
    }

    public ArrayList<SuratJalan> cariSJ(String keyword) {
        return sjCtl.cariSJ(keyword);
    }

    public void onTampilkanDetailSJ(SuratJalan curSuratJalan) {
        sjCtl.tampilkanDetailSJ(curSuratJalan);
    }

    public void tampilkanDetailSJ(SuratJalan curSuratJalan) {
        if (lastDetailSJForm != null) {
            cardPanel.remove(lastDetailSJForm);
        }
        lastDetailSJForm = new DetailSJForm(this, curSuratJalan);
        cardPanel.add(lastDetailSJForm, "Detail SJ");
        cardLayout.show(cardPanel, "Detail SJ");
    }

    public void prepareSJ(SuratJalan suratJalan) {
        sjCtl.prepareSJ(suratJalan);
    }

    public void signSJ(SuratJalan suratJalan) {
        sjCtl.signSJ(suratJalan);
    }

    public void pendingSJ(SuratJalan suratJalan) {
        sjCtl.pendingSJ(suratJalan);
    }

    public void tampilkanBuatSJForm() {
        if (lastBuatSJForm != null) {
            cardPanel.remove(lastBuatSJForm);
        }
        lastBuatSJForm = new BuatSJForm(this);
        cardPanel.add(lastBuatSJForm, "Buat SJ Form");
        cardLayout.show(cardPanel, "Buat SJ Form");
    }

    public int tampilkanConfirmDialog() {
        return sjCtl.tampilkanConfirmDialog();
    }

    public void confirmBuatSJ(String[] input) {
        SuratJalan curSuratJalan = sjCtl.confirmBuatSJ(input);
        if (curSuratJalan == null) {
            return;
        }

        if (lastDetailSJForm != null) {
            cardPanel.remove(lastDetailSJForm);
        }
        lastDetailSJForm = new DetailSJForm(this, curSuratJalan);
        cardPanel.add(lastDetailSJForm, "Detail SJ");
        cardLayout.show(cardPanel, "Detail SJ");
    }
    
    public void onTampilkanEmailSJForm(SuratJalan curSuratJalan) {
        sjCtl.tampilkanEmailSJForm(curSuratJalan);
    }

    public void tampilkanEmailSJForm(SuratJalan curSuratJalan) {
        if (lastEmailSJForm != null) {
            cardPanel.remove(lastEmailSJForm);
        }
        lastEmailSJForm = new EmailSJForm(this, curSuratJalan);
        cardPanel.add(lastEmailSJForm, "Detail SJ");
        cardLayout.show(cardPanel, "Detail SJ");
    }

    public void emailSJ(String emailTo, String emailSubject, String emailBody) {
        sjCtl.emailSJ(emailTo,
                emailSubject, 
                emailBody
        );

    }

    public void printSJ(SuratJalan suratJalan) {
        sjCtl.printSJ(suratJalan);
    }

    public void tampilkanDaftarSP() {
        daftarSP.refresh();
        cardLayout.show(cardPanel, "Daftar SP");
    }
    public ArrayList<SuratPembelian> getSPs() {
        return spCtl.getSPs();
    }

    public ArrayList<SuratPembelian> cariSP(String keyword) {
        return spCtl.cariSP(keyword);
    }

    public void onTampilkanDetailSP(SuratPembelian curSuratPembelian) {
        spCtl.tampilkanDetailSP(curSuratPembelian);
    }

    public void tampilkanDetailSP(SuratPembelian curSuratPembelian) {
        if (lastDetailSPForm != null) {
            cardPanel.remove(lastDetailSPForm);
        }
        lastDetailSPForm = new DetailSPForm(this, curSuratPembelian);
        cardPanel.add(lastDetailSPForm, "Detail SP");
        cardLayout.show(cardPanel, "Detail SP");
    }

    public void tampilkanDetailSP() {
        if (lastBuatSJForm != null) {
            cardPanel.remove(lastBuatSJForm);
        }
        lastBuatSJForm = new BuatSJForm(this);
        cardPanel.add(lastBuatSJForm, "Detail SP Form");
        cardLayout.show(cardPanel, "Detail SJ Form");
    }

    public void terimaSP(SuratPembelian suratPembelian) {
        spCtl.terimaSP(suratPembelian);
    }

    public void pendingSP(SuratPembelian suratPembelian) {
        spCtl.pendingSP(suratPembelian);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        daftarItemMenu = new javax.swing.JMenu();
        daftarSJMenu = new javax.swing.JMenu();
        buatSJMenu = new javax.swing.JMenu();
        daftarSPMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MainPage");

        daftarItemMenu.setText("Daftar Item");
        daftarItemMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarItemMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(daftarItemMenu);

        daftarSJMenu.setText("Daftar SJ");
        daftarSJMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarSJMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(daftarSJMenu);

        buatSJMenu.setText("Buat SJ");
        buatSJMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buatSJMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(buatSJMenu);

        daftarSPMenu.setText("Daftar SP");
        daftarSPMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftarSPMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(daftarSPMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buatSJMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buatSJMenuMouseClicked
        sjCtl.tampilkanBuatSJForm();
    }//GEN-LAST:event_buatSJMenuMouseClicked

    private void daftarItemMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarItemMenuMouseClicked
        itemCtl.tampilkanDaftarItem();
    }//GEN-LAST:event_daftarItemMenuMouseClicked

    private void daftarSJMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarSJMenuMouseClicked
        sjCtl.tampilkanDaftarSJ();
    }//GEN-LAST:event_daftarSJMenuMouseClicked

    private void daftarSPMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftarSPMenuMouseClicked
        spCtl.tampilkanDaftarSP();
    }//GEN-LAST:event_daftarSPMenuMouseClicked

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
                (new MainPage()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu buatSJMenu;
    private javax.swing.JMenu daftarItemMenu;
    private javax.swing.JMenu daftarSJMenu;
    private javax.swing.JMenu daftarSPMenu;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables

}
