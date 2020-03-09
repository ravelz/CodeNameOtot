/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import koneksi.koneksi;

/**
 *
 * @author russt
 */
public class EntryBarang extends javax.swing.JFrame {

    /**
     * Creates new form EntryPelanggan
     */
    public EntryBarang() {
        initComponents();
        AutoNumber();
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);
    }

    public EntryBarang(ArrayList<String> text) {
        initComponents();
        showData(text);
        btnSimpan.setEnabled(false);
        btnHapus.setEnabled(true);
        btnUbah.setEnabled(true);
    }

    void showData(ArrayList<String> ss) {
        txtKdBarang.setText(ss.get(0));
        txtNmBarang.setText(ss.get(1));
        txtSatuan.setText(ss.get(2));
        txtStok.setText(ss.get(3));
        txtHrgBarang.setText(ss.get(4));
    }

    void AutoNumber() {
        try {
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            String sql = "select max(right(1718157_KdBarang, 3)) from 1718157_barang";
            Statement state = mySql.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt(1);
                System.out.println(a);
                String b = rs.getString(1);
                System.out.println(b);
                if (rs.getInt(1) < 9) {
                    txtKdBarang.setText("BRG00" + (a + 1));
                } else if (rs.getInt(1) < 99) {
                    txtKdBarang.setText("BRG0" + (a + 1));
                } else {
                    txtKdBarang.setText("BRG" + (a + 1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void validasi() {
        if (txtNmBarang.getText().isEmpty() || txtSatuan.getText().isEmpty() || txtStok.getText().isEmpty() || txtHrgBarang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon isi semua data!", "Validasi!", JOptionPane.ERROR_MESSAGE);
        } else {
            Simpan();
        }
    }
    void validasiUbah(){
        if (txtNmBarang.getText().isEmpty() || txtSatuan.getText().isEmpty() || txtStok.getText().isEmpty() || txtHrgBarang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon isi semua data!", "Validasi!", JOptionPane.ERROR_MESSAGE);
        } else {
            Ubah();
        }
    }


    void Clear() {
        txtKdBarang.setText(null);
        AutoNumber();
        txtNmBarang.setText(null);
        txtSatuan.setText(null);
        txtStok.setText(null);
        txtHrgBarang.setText(null);
        btnSimpan.setEnabled(true);
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);
    }
    void Ubah(){
            String kd = txtKdBarang.getText();
            String nama = txtNmBarang.getText();
            String satuan = txtSatuan.getText();
            String stok = txtStok.getText();
            String harga = txtHrgBarang.getText();
            PreparedStatement ps;
            try {
                String query = "UPDATE `1718157_barang` SET `1718157_KdBarang`='"
                        + kd + "'"
                        + ",`1718157_NmBarang`='"
                        + nama + "'"
                        + ",`1718157_Satuan`='"
                        + satuan + "'"
                        + ",`1718157_Stok`='"
                        + stok + "'"
                        + ",`1718157_HrgBarang`='"
                        + harga + "'"
                        + " WHERE `1718157_KdBarang` ='" + kd + "' " ;
                ps = koneksi.getConnection().prepareStatement(query);
                ps.execute(query);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Barang Berhasil Diubah!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
                    Clear();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    void Simpan() {
        String kd = txtKdBarang.getText();
        String nama = txtNmBarang.getText();
        String satuan = txtSatuan.getText();
        String stok = txtStok.getText();
        String harga = txtHrgBarang.getText();
        PreparedStatement ps;
        String query = "INSERT INTO `1718157_barang`(`1718157_KdBarang`, `1718157_NmBarang`, `1718157_Satuan`, `1718157_stok`, `1718157_HrgBarang`) VALUES (?,?,?,?,?)";
        try {
            ps = koneksi.getConnection().prepareStatement(query);
            ps.setString(1, kd);
            ps.setString(2, nama);
            ps.setString(3, satuan);
            ps.setString(4, stok);
            ps.setString(5, harga);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Barang Berhasil Ditambahkan!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
            Clear();
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKdBarang = new javax.swing.JTextField();
        txtNmBarang = new javax.swing.JTextField();
        txtSatuan = new javax.swing.JTextField();
        txtHrgBarang = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtStok = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Entry Pelanggan");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Satuan");

        jLabel3.setText("Harga Barang");

        jLabel4.setText("Nama Barang");

        txtKdBarang.setEditable(false);

        txtNmBarang.setColumns(10);

        txtHrgBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHrgBarangActionPerformed(evt);
            }
        });

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Search-2-icon.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Save-icon.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Actions-document-edit-icon.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Bookmark-delete-icon.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
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

        jLabel5.setText("Stok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus))
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(18, 18, 18)
                        .addComponent(btnKeluar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSatuan, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNmBarang, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHrgBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNmBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHrgBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        validasi();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        validasiUbah();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        String kd = txtKdBarang.getText();
        PreparedStatement ps;
        String query = "DELETE FROM `1718157_barang` WHERE 1718157_KdBarang='"
                + kd
                + "'";
        try {
            ps = koneksi.getConnection().prepareStatement(query);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Pelanggan Berhasil Dihapus!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
                Clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtHrgBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHrgBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHrgBarangActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        new CariDataBarang().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        Clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        this.setVisible(false);
        new FrmMaster().setVisible(true);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(EntryBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntryBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtHrgBarang;
    private javax.swing.JTextField txtKdBarang;
    private javax.swing.JTextField txtNmBarang;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtStok;
    // End of variables declaration//GEN-END:variables
}
