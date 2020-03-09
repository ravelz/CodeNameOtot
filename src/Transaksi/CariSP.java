/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Master.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import koneksi.koneksi;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CariSP extends javax.swing.JFrame {

    ArrayList<String> text = new ArrayList<>();
    public CariSP() {
        initComponents();
        Table();   
    }
    void Table(){
        DefaultTableModel model = (DefaultTableModel) tabelPelanggan1.getModel();;
        try {
            String sql = "SELECT a.1718157_NoSP, a.1718157_TglSP, b.1718157_IdPelanggan, b.1718157_NmPelanggan, b.1718157_Alamat, b.1718157_NoTelp FROM 1718157_sp a,1718157_pelanggan b WHERE a.1718157_IdPelanggan = b.1718157_IdPelanggan  and  a.1718157_NoSP  not in(Select  1718157_NoSP from 1718157_nota) Order By a.1718157_NoSP Asc";
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            Statement state = mySql.createStatement();
            ResultSet res = state.executeQuery(sql);
        while(res.next()){
            model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)});
        }
        tabelPelanggan1.setModel(model);
         } catch (Exception e) {
        }
        int barang = tabelPelanggan1.getRowCount();
        System.out.println(barang);
        txtKtKunci.setText(String.valueOf(barang));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPelanggan1 = new javax.swing.JTable();
        txtKtKunci = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelPelanggan1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No SP", "Tgl Sp", "Id Pelanggan", "Nama Pelanggan", "Alamat", "No Telp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPelanggan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPelanggan1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelPelanggan1);

        jLabel1.setText("Total SP");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtKtKunci, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKtKunci, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tabelPelanggan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelanggan1MouseClicked
        int baris = tabelPelanggan1.rowAtPoint(evt.getPoint());
            text.add(tabelPelanggan1.getValueAt(baris, 0).toString());
            text.add(tabelPelanggan1.getValueAt(baris,1).toString());
            text.add(tabelPelanggan1.getValueAt(baris,2).toString());
            text.add(tabelPelanggan1.getValueAt(baris,3).toString());
            text.add(tabelPelanggan1.getValueAt(baris,4).toString());
            text.add(tabelPelanggan1.getValueAt(baris,5).toString());
            new CetakNota(text).setVisible(true);
            dispose();
    }//GEN-LAST:event_tabelPelanggan1MouseClicked

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
            java.util.logging.Logger.getLogger(CariSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CariSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CariSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CariSP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CariSP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelPelanggan1;
    private javax.swing.JTextField txtKtKunci;
    // End of variables declaration//GEN-END:variables
}
