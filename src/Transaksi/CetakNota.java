/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Master.EntryPelanggan;
import Master.FrmMaster;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class CetakNota extends javax.swing.JFrame {

    public CetakNota() {
        initComponents();
        date();
        AutoNumber();
        btnSimpan.setEnabled(false);
    }

    public CetakNota(ArrayList<String> text) {
        initComponents();
        AutoNumber();
        date();
        showData(text);
         btnSimpan.setEnabled(true);
        Table();
        Total();

    }

    void showData(ArrayList<String> ss) {
        txtNoSP.setText(ss.get(0));
        txtTglSP.setText(ss.get(1));
        txtIdPelanggan.setText(ss.get(2));
        txtNmPelanggan.setText(ss.get(3));
        txtAlmtPelanggan.setText(ss.get(4));
        txtNoTelp.setText(ss.get(5));

    }
    void Batal(){
        txtNoNota.setText("");
        AutoNumber();
        txtTglNota.setText("");
        date();
        txtNoSP.setText("");
        txtTglSP.setText("");
        txtIdPelanggan.setText("");
        txtNmPelanggan.setText("");
        txtAlmtPelanggan.setText("");
        txtNoTelp.setText("");
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        txtTotal.setText("");
    }

    void Table() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        try {
            String sql = "select s.1718157_KdBarang, b.1718157_NmBarang, b.1718157_Satuan, s.1718157_JmlJual, s.1718157_HrgJual from 1718157_detil_pesan s, 1718157_barang b  WHERE s.1718157_KdBarang=b.1718157_KdBarang and s.1718157_NoSp='" + txtNoSP.getText() + "'";
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            Statement state = mySql.createStatement();
            ResultSet res = state.executeQuery(sql);
            while (res.next()) {
                String a = res.getString(3);
                System.out.println(a);
                int b = res.getInt(4);
                int c = (int) res.getDouble(5);
                int e = b * c;
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), e});
            }
            jTable2.setModel(model);
        } catch (Exception e) {
        }

    }

    void Total() {
        int total = 0;
        int Amount;
        String z;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            Amount = (int) jTable2.getValueAt(i, 5);
            total = Amount + total;
            System.out.println(total);
        }
        z = Integer.toString(total);
        txtTotal.setText(z);
    }

    void JumlahHarga() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        for (int row = 0; row < jTable2.getRowCount(); row++) {
            String jml = jTable2.getValueAt(row, 3).toString();
            int JmlJual = (int) Math.round((double) jTable2.getValueAt(row, 4));
            int b = Integer.parseInt(jml);
            int e = b * JmlJual;
            System.out.println(e);
            jTable2.setValueAt(e, row, 5);
        }

    }

    void AutoNumber() {
        try {
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            String sql = "select max(right(1718157_NoNota, 3)) from 1718157_nota";
            Statement state = mySql.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt(1);
                System.out.println(a);
                String b = rs.getString(1);
                System.out.println(b);
                if (rs.getInt(1) < 9) {
                    txtNoNota.setText("NT000" + (a + 1));
                } else if (rs.getInt(1) < 99) {
                    txtNoNota.setText("NT00" + (a + 1));
                } else if (rs.getInt(1) < 99) {
                    txtNoNota.setText("NT00" + (a + 1));
                } else {
                    txtNoNota.setText("NT" + (a + 1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void date() {
        Date dt = new java.util.Date();
        SimpleDateFormat sdf
                = new SimpleDateFormat("dd-MMMM-yyyy");
        txtTglNota.setText(sdf.format(dt));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNoNota = new javax.swing.JTextField();
        txtTglNota = new javax.swing.JTextField();
        txtNoSP = new javax.swing.JTextField();
        txtTglSP = new javax.swing.JTextField();
        txtIdPelanggan = new javax.swing.JTextField();
        txtNmPelanggan = new javax.swing.JTextField();
        txtAlmtPelanggan = new javax.swing.JTextField();
        txtNoTelp = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data Nota");

        jLabel2.setText("Nomor Nota");

        jLabel3.setText("Nama Pelanggan");

        jLabel4.setText("Alamat Pelanggan");

        jLabel5.setText("Tanggal Nota");

        jLabel6.setText("No Telp.");

        jLabel7.setText("Id Pelanggan");

        jLabel8.setText("Tanggal Pesanan");

        jLabel9.setText("Data Pesanan");

        jLabel10.setText("Nomor Pesanan");

        jLabel11.setText("Data Pelanggan");

        txtNoNota.setEditable(false);

        txtTglNota.setEditable(false);

        txtNoSP.setEditable(false);

        txtTglSP.setEditable(false);

        txtIdPelanggan.setEditable(false);

        txtNmPelanggan.setEditable(false);

        txtAlmtPelanggan.setEditable(false);

        txtNoTelp.setEditable(false);

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Search-2-icon.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Satuan Barang", "Jumlah Pesan", "Harga Pesan", "Jumlah Harga"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Floppy-Small-icon.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Actions-dialog-cancel-icon.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Actions-application-exit-icon.png"))); // NOI18N
        btnKeluar.setText("Keluar");
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        jLabel12.setText("Total Harga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(18, 18, 18)
                        .addComponent(btnKeluar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTglSP))
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5))
                                        .addGap(27, 27, 27)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTglNota, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(txtNoNota)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel9))
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNoSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel6))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAlmtPelanggan)
                                        .addComponent(txtNmPelanggan)
                                        .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNmPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(txtAlmtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNoNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTglNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtNoSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTglSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        new CariSP().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String nota = txtNoNota.getText();
        String sp = txtNoSP.getText();
        Date dt = new java.util.Date();
        SimpleDateFormat sdf1
                = new SimpleDateFormat("dd-MM-yyyy");
        txtTglNota.setText(sdf1.format(dt));
        String tgl = txtTglNota.getText();
        String total = txtTotal.getText();
        PreparedStatement ps;
        String query = "INSERT INTO `1718157_nota`(`1718157_NoNota`, `1718157_NoSP`, `1718157_TglNota`, `1718157_JmlHarga`) VALUES (?,?,?,?)";
        try {
            ps = koneksi.getConnection().prepareStatement(query);
            ps.setString(1, nota);
            ps.setString(2, sp);
            ps.setString(4, total);
            ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Nota Berhasil Ditambahkan!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
//                Clear();
            }
            JasperPrint jp;
            Map param = new HashMap();
            param.put("no_nota", txtNoNota.getText());
            try {
                param.put("no_nota", txtNoNota.getText());
                jp = JasperFillManager.fillReport(getClass().getResourceAsStream("rpesan.jasper"), param, koneksi.getConnection());
                JasperViewer.viewReport(jp, false);
                Batal();
            } catch (JRException ex) {
                Logger.getLogger(CetakNota.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        new FrmMaster().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        Batal();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

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
            java.util.logging.Logger.getLogger(CetakNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CetakNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CetakNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CetakNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CetakNota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField txtAlmtPelanggan;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JTextField txtNmPelanggan;
    private javax.swing.JTextField txtNoNota;
    private javax.swing.JTextField txtNoSP;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtTglNota;
    private javax.swing.JTextField txtTglSP;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
