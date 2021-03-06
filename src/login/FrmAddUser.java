/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksi;
import javax.swing.JOptionPane;

public class FrmAddUser extends javax.swing.JFrame {

    koneksi DB = new koneksi();
    private String user = "", password = "", hakAkses = "";
    private boolean val = true;
    private boolean autoAdd = true;
    private String username = "";

    /**
     * Creates new form FrmAddUser
     */
    public FrmAddUser() {
        initComponents();
    }

    private void clearForm() {
        txtUsernameAU.setText("");
        txtPassAU.setText("");
        revalidate();
    }

    private void addUser() {
        try {
//            DB.konek = DB.getConnection();
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            Statement state = mySql.createStatement();
            String queryCheckUsername = "SELECT * FROM user";
            ResultSet res = state.executeQuery(queryCheckUsername);

            PreparedStatement ps;
            if (res.next()) {
                user = txtUsernameAU.getText();
                password = txtPassAU.getText();
                hakAkses = String.valueOf(cbHakAksesAU.getSelectedItem());

                String queryAddUser = "INSERT INTO user(username, password, hakAkses) VALUES (?,?,?)";
                ps = koneksi.getConnection().prepareStatement(queryAddUser);
                ps.setString(1, user);
                ps.setString(2, password);
                ps.setString(3, hakAkses);

                if (ps.executeUpdate() > 0) {
                    if (hakAkses.equals("admin")) {
                        JOptionPane.showMessageDialog(null, "User berhasil Ditambahkan! Hak Akses = Admin", "Infomation!", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "User berhasil Ditambahkan! Hak Akses = User", "Infomation!", JOptionPane.INFORMATION_MESSAGE);
                        clearForm();
                    }
                }
            } else {
                user = txtUsernameAU.getText();
                password = txtPassAU.getText();
                hakAkses = "admin";

                String queryAddUser = "INSERT INTO user(username, password, hakAkses) VALUES (?,?,?)";
                ps = koneksi.getConnection().prepareStatement(queryAddUser);
                ps.setString(1, user);
                ps.setString(2, password);
                ps.setString(3, hakAkses);

                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil Ditambah! Data Tersebut Akan dijadikan Sebagai Admin!", "Warning", JOptionPane.WARNING_MESSAGE);
                    clearForm();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        } finally {
//            try {
////                DB.c.close();
////                DB.pstm.close();
////                DB.rs.close();
////                DB.stm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
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
        txtUsernameAU = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassAU = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbHakAksesAU = new javax.swing.JComboBox();
        btnAddUser = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Username");

        jLabel2.setText("Password");

        jLabel3.setText("Hak Akses");

        cbHakAksesAU.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "user", "admin" }));

        btnAddUser.setText("Add User");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Add User");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(jLabel4))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(45, 45, 45)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnAddUser)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtUsernameAU)
                                .addComponent(txtPassAU)
                                .addComponent(cbHakAksesAU, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addContainerGap(59, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jLabel4)
                    .addGap(38, 38, 38)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtUsernameAU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(txtPassAU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3)
                        .addComponent(cbHakAksesAU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(38, 38, 38)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddUser)
                        .addComponent(btnBatal))
                    .addContainerGap(38, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        if (txtUsernameAU.getText().equals("") && txtPassAU.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Username dan Password Tidak Boleh Kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (txtUsernameAU.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Username Tidak Boleh Kosong!", "Warning", JOptionPane.WARNING_MESSAGE);
            txtUsernameAU.requestFocusInWindow();
        } else if (txtPassAU.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong!", "Warning", JOptionPane.WARNING_MESSAGE);;
            txtPassAU.requestFocusInWindow();
        } else {
            try {
                koneksi DB = new koneksi();
                Connection mySql = DB.getConnection();
                Statement state = mySql.createStatement();
//                String queryCheckUsername = "SELECT * FROM user";
                

                PreparedStatement ps;
                
                
                String queryCheckUsername = "SELECT username FROM user WHERE username = '" + txtUsernameAU.getText() + "'";
                ResultSet res = state.executeQuery(queryCheckUsername);
                res = state.executeQuery(queryCheckUsername);
                if (res.next()) {
                    if (res.getString("username").equals(txtUsernameAU.getText())) {
                        JOptionPane.showMessageDialog(null, "Username tersebut sudah ada!", "Warning!", JOptionPane.WARNING_MESSAGE);
                        txtUsernameAU.requestFocusInWindow();
                    } else {
                        addUser();
                    }
                } else {
                    addUser();
                }
        }   catch (SQLException ex) {
                Logger.getLogger(FrmAddUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        int pilih = JOptionPane.showConfirmDialog(null, "Kembali ke Form Login?", "Question", JOptionPane.YES_NO_OPTION);
        if (pilih == JOptionPane.YES_OPTION) {
            new FrmLogin().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Form Akan dikosongkan!", "Warning", JOptionPane.WARNING_MESSAGE);
            txtUsernameAU.setText("");
            txtPassAU.setText("");
            txtUsernameAU.requestFocusInWindow();
        }
    }//GEN-LAST:event_btnBatalActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAddUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAddUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnBatal;
    private javax.swing.JComboBox cbHakAksesAU;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtPassAU;
    private javax.swing.JTextField txtUsernameAU;
    // End of variables declaration//GEN-END:variables

}
