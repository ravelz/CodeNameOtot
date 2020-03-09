/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import koneksi.koneksi;


public class EntryPelanggan extends javax.swing.JFrame {

    /**
     * Creates new form EntryPelanggan
     */
    public EntryPelanggan() {
        initComponents();
        AutoNumber();
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);
    }
    public EntryPelanggan(ArrayList<String> text){
        initComponents();
        showData(text);
        btnSimpan.setEnabled(false);
        btnHapus.setEnabled(true);
        btnUbah.setEnabled(true);
    }
    void showData(ArrayList<String> ss){
        txtIdPelanggan.setText(ss.get(0));
        txtNmPelanggan.setText(ss.get(1));
        txtAlmtPelanggan.setText(ss.get(2));
        txtNoTelp.setText(ss.get(3));
    }
    void AutoNumber(){
        try {
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            String sql = "select max(right(1718157_IdPelanggan, 3)) from 1718157_pelanggan";
//            String sql = "select * from 1718157_pelanggan order by 1718157_IdPelanggan DESC";
            Statement state = mySql.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int a = rs.getInt(1);
                System.out.println(a);
                String b = rs.getString(1);
                System.out.println(b);
                if(rs.getInt(1)<9){
                   txtIdPelanggan.setText("P"+0+0+(a+1)); 
                }else if(rs.getInt(1)<99){
                   txtIdPelanggan.setText("P"+0+(a+1));  
                }else{
                    txtIdPelanggan.setText("P"+(a+1)); 
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
           
//            gagal 1
//            while (rs.next()) {
//                if (rs.first() == false) {
//                    txtIdPelanggan.setText("P001");
//                } else {
//                    rs.last();
//                    int auto_id = rs.getInt(1) + 1;
//                    String no = String.valueOf(auto_id);
//                    int NomorJual = no.length();
//                    //MENGATUR jumlah 0
//                    for (int j = 0; j < 3 - NomorJual; j++) {
//                        no = "0" + no;
//                    }
//                    txtIdPelanggan.setText("P" + no);
//                }
//            }
//              gagal 2
//            String sql1 = "SELECT SUBSTRING(1718157_IdPelanggan, 2, 4) FROM 1718157_pelanggan";
           
//            gagal 1
//            while (rs.next()) {
//                if (rs.first() == false) {
//                    txtIdPelanggan.setText("P001");
//                } else {
//                    rs.last();
//                    int auto_id = rs.getInt(1) + 1;
//                    String no = String.valueOf(auto_id);
//                    int NomorJual = no.length();
//                    //MENGATUR jumlah 0
//                    for (int j = 0; j < 3 - NomorJual; j++) {
//                        no = "0" + no;
//                    }
//                    txtIdPelanggan.setText("P" + no);
//                }
//            }
//              gagal 2
//            String sql1 = "SELECT SUBSTRING(1718157_IdPelanggan, 2, 4) FROM 1718157_pelanggan";
            
    }
    void Clear(){
        txtIdPelanggan.setText(null);
        AutoNumber();
        txtNmPelanggan.setText(null);
        txtAlmtPelanggan.setText(null);
        txtNoTelp.setText(null);
        btnSimpan.setEnabled(true);
        btnHapus.setEnabled(false);
        btnUbah.setEnabled(false);
    }
    void validasi() {
        if (txtIdPelanggan.getText().isEmpty() || txtNmPelanggan.getText().isEmpty() || txtAlmtPelanggan.getText().isEmpty() || txtNoTelp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon isi semua data!", "Validasi!", JOptionPane.ERROR_MESSAGE);
        } else {
            Simpan();
        }
    }
    void validasiUbah(){
        if (txtIdPelanggan.getText().isEmpty() || txtNmPelanggan.getText().isEmpty() || txtAlmtPelanggan.getText().isEmpty() || txtNoTelp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon isi semua data!", "Validasi!", JOptionPane.ERROR_MESSAGE);
        } else {
            Ubah();
        }
    }
    void Ubah(){
        String id = txtIdPelanggan.getText();
        String nama = txtNmPelanggan.getText();
        String alamat = txtAlmtPelanggan.getText();
        String telp = txtNoTelp.getText();
       
        PreparedStatement ps;
        
        System.out.println(id);
        try {
            String query = "UPDATE `1718157_pelanggan` SET `1718157_IdPelanggan`='"
                + id+"'"
                + ",`1718157_NmPelanggan`='"
                + nama+"'"
                + ",`1718157_Alamat`='"
                + alamat+"'"
                + ",`1718157_NoTelp`='"
                + telp+"'"
                + " WHERE `1718157_IdPelanggan` ='"+id+"' ";
            //dipindah + id ke atas mau dong si sue-_-
//                + id+"'";
//            query = String.format(id, nama, alamat,telp, id);
            ps = koneksi.getConnection().prepareStatement(query);
            ps.execute(query);
//            ps.executeUpdate();
//            JOptionPane.showMessageDialog(null, "Pelanggan Berhasil Diubah!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Pelanggan Berhasil Diubah!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
                Clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void Simpan(){
                String id = txtIdPelanggan.getText();
        String nama = txtNmPelanggan.getText();
        String alamat = txtAlmtPelanggan.getText();
        String telp = txtNoTelp.getText();

        PreparedStatement ps;
        String query = "INSERT INTO `1718157_pelanggan`(`1718157_IdPelanggan`, `1718157_NmPelanggan`, `1718157_Alamat`, `1718157_NoTelp`) VALUES (?,?,?,?)";
        
        try {
            ps = koneksi.getConnection().prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, nama);
            ps.setString(3, alamat);
            ps.setString(4, telp);
            
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Pelanggan Berhasil Ditambahkan!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
                Clear();
            }
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
        txtIdPelanggan = new javax.swing.JTextField();
        txtNmPelanggan = new javax.swing.JTextField();
        txtAlmtPelanggan = new javax.swing.JTextField();
        txtNoTelp = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Form Entry Pelanggan");

        jLabel1.setText("ID Pelanggan");

        jLabel2.setText("Alamat Pelanggan");

        jLabel3.setText("No. Telp");

        jLabel4.setText("Nama Pelanggan");

        txtIdPelanggan.setEditable(false);

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Search-2-icon.png"))); // NOI18N
        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Floppy-Small-icon.png"))); // NOI18N
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
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSimpan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAlmtPelanggan)
                        .addComponent(txtNmPelanggan)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(txtNoTelp))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnUbah)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal)
                        .addGap(18, 18, 18)
                        .addComponent(btnKeluar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNmPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2))
                    .addComponent(txtAlmtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        validasi();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new FrmMaster().setVisible(true);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        Clear();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        String id = txtIdPelanggan.getText();
        String nama = txtNmPelanggan.getText();
        String alamat = txtAlmtPelanggan.getText();
        String telp = txtNoTelp.getText();
       
        PreparedStatement ps;
        String query = "DELETE FROM `1718157_pelanggan` WHERE 1718157_IdPelanggan='"
                +id
                +"'";
        
        try {
            ps = koneksi.getConnection().prepareStatement(query);
            if(ps.executeUpdate() > 0){
                JOptionPane.showMessageDialog(null, "Pelanggan Berhasil Dihapus!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
                Clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        validasiUbah();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        new CariDataPelanggan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCariActionPerformed

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
            java.util.logging.Logger.getLogger(EntryPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntryPelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private static javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtAlmtPelanggan;
    private static javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JTextField txtNmPelanggan;
    private javax.swing.JTextField txtNoTelp;
    // End of variables declaration//GEN-END:variables
}
