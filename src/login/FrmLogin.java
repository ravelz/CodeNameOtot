/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import Master.FrmMaster;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksi;
import javax.swing.JOptionPane;


public class FrmLogin extends javax.swing.JFrame {

    koneksi DB = new koneksi();
    private boolean setAccesbility;
    private String username = "", password = "", hakakses = "";

    /**
     * Creates new form FrmLogin
     */
    public FrmLogin() {
        initComponents();
        btnAddUser.setEnabled(false);
        txtHakAkses.setEditable(false);
        try {
            tampilUser();
        } catch (SQLException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//<editor-fold defaultstate="collapsed" desc="Login">
    private void loginUser() {
        revalidate();
        if (txtPassword.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong!", "Warning!", JOptionPane.WARNING_MESSAGE);
            btnAddUser.setEnabled(false);
            txtPassword.requestFocusInWindow();
        } else {
            try {
                koneksi DB = new koneksi();
                Connection mySql = DB.getConnection();
                Statement state = mySql.createStatement();
//                String queryCheckUsername = "SELECT * FROM user";
                PreparedStatement ps;
                String queryLogAdmin = "SELECT * FROM user";
                ResultSet res = state.executeQuery(queryLogAdmin);
                res = state.executeQuery(queryLogAdmin);

                while (res.next()) {
                    username = res.getString("username");
                    password = res.getString("password");
                    hakakses = res.getString("hakAkses");

                    if (cbUsername.getSelectedItem().equals(username) && txtPassword.getText().equals(password)) {
                        txtHakAkses.setText(hakakses);
                        if (txtHakAkses.getText().equals("admin")) {
                            btnAddUser.setEnabled(true);
                            setAccesbility = true;
                            JOptionPane.showMessageDialog(null, "Berhasil Login. Hak Akses = Admin", "Succes", JOptionPane.INFORMATION_MESSAGE);

                            int pilih1 = JOptionPane.showConfirmDialog(null, "Akan Dialihkan ke Form Master, Click Yes untuk Melanjutkan.", "Question", JOptionPane.YES_NO_OPTION);
                            if (pilih1 == JOptionPane.YES_OPTION) {
                                dispose();
                                new FrmMaster(setAccesbility).setVisible(true);
                                break;
                            }
                        } else {
                            if (txtHakAkses.getText().equals("user")) {
                                JOptionPane.showMessageDialog(null, "Berhasil Login. Hak Akses = User", "Succes", JOptionPane.INFORMATION_MESSAGE);
                                btnAddUser.setEnabled(false);
                                setAccesbility = false;

                                int pilih2 = JOptionPane.showConfirmDialog(null, "Akan Dialihkan ke Form Master, Click Yes untuk Melanjutkan.", "Question", JOptionPane.YES_NO_OPTION);
                                if (pilih2 == JOptionPane.YES_OPTION) {
                                    dispose();
                                    new FrmMaster(setAccesbility).setVisible(true);
                                    break;
                                } else {
                                    break;
                                }
                            }

                        }
                    } else {
                        if (cbUsername.getSelectedItem().equals(username) && txtPassword.getText() != password) {
                            JOptionPane.showMessageDialog(null, "Password Salah", "Error", JOptionPane.ERROR_MESSAGE);
                            txtPassword.requestFocusInWindow();
                            btnAddUser.setEnabled(false);
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Login User dan Admin"> //Cuma nyoba bang
//    private void loginForAdmin() {
//        try {
//            DB.c = DB.GetConnection();
//            String queryLoginUser = "SELECT * FROM user WHERE hakakses='admin'";
//            DB.stm = DB.c.createStatement();
//            DB.rs = DB.stm.executeQuery(queryLoginUser);
//
//            while (DB.rs.next()) {
//                username = DB.rs.getString("username");
//                password = DB.rs.getString("password");
//                hakakses = DB.rs.getString("hakAkses");
//
//                if (cbUsername.getSelectedItem().equals(username) && txtPassword.getText().equals(password)) {
//                    txtHakAkses.setText(hakakses);
//                    if (txtHakAkses.getText().equals("admin")) {
//                        btnAddUser.setEnabled(true);
//                        JOptionPane.showMessageDialog(null, "Login Sebagai Admin!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
//                    }
//                } else {
//                    if (cbUsername.getSelectedItem().equals(username) && txtPassword.getText() != password) {
//                        btnAddUser.setEnabled(false);
//                        JOptionPane.showMessageDialog(null, "Password Salah", "Error", JOptionPane.ERROR_MESSAGE);
//                        txtPassword.requestFocusInWindow();
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DB.c.close();
//                DB.stm.close();
//                DB.rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void loginForUser() {
//        try {
//            DB.c = DB.GetConnection();
//            String queryLoginUser = "SELECT * FROM user WHERE hakakses='user'";
//            DB.stm = DB.c.createStatement();
//            DB.rs = DB.stm.executeQuery(queryLoginUser);
//
//            while (DB.rs.next()) {
//                username = DB.rs.getString("username");
//                password = DB.rs.getString("password");
//                hakakses = DB.rs.getString("hakAkses");
//
//                if (cbUsername.getSelectedItem().equals(username) && txtPassword.getText().equals(password)) {
//                    txtHakAkses.setText(hakakses);
//                    if (txtHakAkses.getText().equals("user")) {
//                        JOptionPane.showMessageDialog(null, "Login Sebagai User!", "Succes!", JOptionPane.INFORMATION_MESSAGE);
//                        btnAddUser.setEnabled(false);
//                    }
//                } else {
//                    if (cbUsername.getSelectedItem().equals(username) && txtPassword.getText() != password) {
//                        JOptionPane.showMessageDialog(null, "Password Salah", "Error", JOptionPane.ERROR_MESSAGE);
//                        txtPassword.requestFocusInWindow();
//                        btnAddUser.setEnabled(false);
//                    }
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                DB.c.close();
//                DB.stm.close();
//                DB.rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Auto add AND Show all data user">
    private void valAutoAdd() throws SQLException {
        revalidate();
        koneksi DB = new koneksi();
        Connection mySql = DB.getConnection();
        Statement state = mySql.createStatement();
        PreparedStatement ps;
        String tampilUser = "SELECT * FROM user";
        ResultSet res = state.executeQuery(tampilUser);
        res = state.executeQuery(tampilUser);
        if (res.next()) {
            //do Nothin
        } else {
            JOptionPane.showMessageDialog(null, "Belum ada user yang terdaftar! Akan dialihkan ke form add User", "Warning!", JOptionPane.WARNING_MESSAGE);
            new FrmAddUser().setVisible(true);
            dispose();
        }
    }

    private void tampilUser() throws SQLException {
        revalidate();
        koneksi DB = new koneksi();
        Connection mySql = DB.getConnection();
        Statement state = mySql.createStatement();
        PreparedStatement ps;
        String tampilUser = "SELECT * FROM user";
        ResultSet res = state.executeQuery(tampilUser);
        //            res = state.executeQuery(tampilUser);
        res = state.executeQuery(tampilUser);
        while (res.next()) {
            cbUsername.addItem(res.getString("username"));
        }
    }
//</editor-fold>

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddUser = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbUsername = new javax.swing.JComboBox();
        txtPassword = new javax.swing.JTextField();
        txtHakAkses = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btnAddUser.setText("Add User");
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });

        jLabel3.setText("Hak Akses");

        jLabel2.setText("Password");

        jLabel1.setText("Username");

        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/metik-tranparan.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbUsername, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPassword)
                            .addComponent(txtHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtHakAkses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        // TODO add your handling code here:
        new FrmAddUser().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loginUser();
        revalidate();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        txtHakAkses.setText("");
        txtPassword.setText("");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            // TODO add your handling code here:
            // Auto add user when there is no data in table user
            valAutoAdd();
        } catch (SQLException ex) {
            Logger.getLogger(FrmLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnCancel;
    private javax.swing.JComboBox cbUsername;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtHakAkses;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
