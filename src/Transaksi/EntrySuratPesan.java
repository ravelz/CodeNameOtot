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
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.koneksi;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EntrySuratPesan extends javax.swing.JFrame {

    /**
     * Creates new form EntrySuratPesan
     */
    ArrayList<String> text = new ArrayList<>();

    public EntrySuratPesan() {
        initComponents();
        AutoNumber();
        date();
        Hapus();
        Hapus1();
//        btnSimpan.setEnabled(false);
        btnTambah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnCariBarang.setEnabled(false);
    }

    public EntrySuratPesan(ArrayList<String> text) {
        initComponents();
        AutoNumber();
        date();
        showData(text);
        Table();
        btnSimpan.setEnabled(true);
        btnTambah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnCariBarang.setEnabled(true);
        Total();
        validasiB();
    }

    

    void Clear() {
        txtNoSP.setText("");
        AutoNumber();
        txtTglPsn.setText("");
        date();
        txtIdPelanggan.setText("");
        txtNmPelanggan.setText("");
        txtAlmtPelanggan.setText("");
        txtNoTelp.setText("");
        txtKdBarang.setText("");
        txtNmBarang.setText("");
        txtSatuan.setText("");
        txtHrgBarang.setText("");
        txtHrgPesan.setText("");
        txtJmlPsn.setValue(0);
        DefaultTableModel model = (DefaultTableModel) tabelOtot.getModel();
        model.setRowCount(0);
    }

    void Hapus() {
        PreparedStatement ps;
        String query = "DELETE FROM `dpelanggan`";

        try {
            ps = koneksi.getConnection().prepareStatement(query);
            if (ps.executeUpdate() > 0) {
                System.out.println("Berhasil Dihapus!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Hapus1() {
        PreparedStatement ps;
        String query = "DELETE FROM `d_detil`";

        try {
            ps = koneksi.getConnection().prepareStatement(query);
            if (ps.executeUpdate() > 0) {
                System.out.println("Berhasil Dihapus!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void date() {
        Date dt = new java.util.Date();
        SimpleDateFormat sdf
                = new SimpleDateFormat("dd-MMMM-yyyy");
        txtTglPsn.setText(sdf.format(dt));
    }

    void SimpanSP() {
        String NoSP = txtNoSP.getText();
        String Id = txtIdPelanggan.getText();
        PreparedStatement ps;
        String query = "INSERT INTO `1718157_sp`(`1718157_NoSP`, `1718157_IdPelanggan`, `1718157_TglSP`) VALUES (?,?,?)";
        try {
            ps = koneksi.getConnection().prepareStatement(query);
            ps.setString(1, NoSP);
            ps.setString(2, Id);
            ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Surat Pesan Berhasil Ditambahkan!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void SimpanAda() {
        try {
            DefaultTableModel model = (DefaultTableModel) tabelOtot.getModel();
            PreparedStatement ps;
            String query = "INSERT INTO `1718157_detil_pesan`(`1718157_NoSP`, `1718157_KdBarang`, `1718157_JmlJual`, `1718157_HrgJual`) VALUES (?,?,?,?)";
            ps = koneksi.getConnection().prepareStatement(query);
            for (int row = 0; row < tabelOtot.getRowCount(); row++) {
                String NoSP = txtNoSP.getText();
                String KdBarang = tabelOtot.getValueAt(row, 0).toString();
                String JmlJual = tabelOtot.getValueAt(row, 3).toString();
                String HrgJual = tabelOtot.getValueAt(row, 4).toString();
                ps.setString(1, NoSP);
                ps.setString(2, KdBarang);
                ps.setString(3, JmlJual);
                ps.setString(4, HrgJual);
                if (ps.executeUpdate() >= row) {
                    
                }
            }
            ps.addBatch();
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showData(ArrayList<String> ss) {
        txtKdBarang.setText(ss.get(0));
        txtNmBarang.setText(ss.get(1));
        txtSatuan.setText(ss.get(2));
        txtHrgBarang.setText(ss.get(3));
        txtIdPelanggan.setText(ss.get(4));
        txtNmPelanggan.setText(ss.get(5));
        txtAlmtPelanggan.setText(ss.get(6));
        txtNoTelp.setText(ss.get(7));

    }

    void showData1(ArrayList<String> po) {
        txtIdPelanggan.setText(po.get(0));
        txtNmPelanggan.setText(po.get(1));
        txtAlmtPelanggan.setText(po.get(2));
        txtNoTelp.setText(po.get(3));
    }

    void AutoNumber() {
        try {
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            String sql = "select max(right(1718157_NoSP, 4)) from 1718157_sp";
            Statement state = mySql.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int a = rs.getInt(1);
                System.out.println(a);
                String b = rs.getString(1);
                System.out.println(b);
                if (rs.getInt(1) < 9) {
                    txtNoSP.setText("SP000" + (a + 1));
                } else if (rs.getInt(1) < 99) {
                    txtNoSP.setText("SP00" + (a + 1));
                } else if (rs.getInt(1) < 99) {
                    txtNoSP.setText("SP00" + (a + 1));
                } else {
                    txtNoSP.setText("SP" + (a + 1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void validasiB() {
        DefaultTableModel model = (DefaultTableModel) tabelOtot.getModel();

    }

    void Table() {
        DefaultTableModel model = (DefaultTableModel) tabelOtot.getModel();
        try {
            String sql = "select * from d_detil";
            koneksi DB = new koneksi();
            Connection mySql = DB.getConnection();
            Statement state = mySql.createStatement();
            ResultSet res = state.executeQuery(sql);
           
            while (res.next()) {
                String a = res.getString(1);
                System.out.println(a);
                model.addRow(new Object[]{res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)});

                
            }
            tabelOtot.setModel(model);
        } catch (Exception e) {
        }
    }
    void Total() {
        int total = 0;
        int Amount;
        String z;
        for (int i = 0; i < tabelOtot.getRowCount(); i++) {
            Amount = Integer.valueOf(tabelOtot.getValueAt(i, 5).toString());
            total = Amount + total;
            System.out.println(total);
        }
        z = Integer.toString(total);
        txtTotal.setText(z);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNoSP = new javax.swing.JTextField();
        txtTglPsn = new javax.swing.JTextField();
        txtKdBarang = new javax.swing.JTextField();
        txtNmBarang = new javax.swing.JTextField();
        txtSatuan = new javax.swing.JTextField();
        txtHrgBarang = new javax.swing.JTextField();
        txtHrgPesan = new javax.swing.JTextField();
        txtJmlPsn = new javax.swing.JSpinner();
        btnCariBarang = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtIdPelanggan = new javax.swing.JTextField();
        txtNmPelanggan = new javax.swing.JTextField();
        txtAlmtPelanggan = new javax.swing.JTextField();
        txtNoTelp = new javax.swing.JTextField();
        btnCariPelanggan = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelOtot = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Data Pesanan");

        jLabel2.setText("Nomor Pesanan");

        jLabel3.setText("Satuan");

        jLabel4.setText("Nama Barang");

        jLabel5.setText("Jumlah Pesan");

        jLabel6.setText("Kode Barang");

        jLabel7.setText("Harga Barang");

        jLabel8.setText("Tanggal Pesanan");

        jLabel9.setText("Data Barang");

        jLabel10.setText("Harga Pesan");

        txtNoSP.setEditable(false);

        txtTglPsn.setEditable(false);
        txtTglPsn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTglPsnActionPerformed(evt);
            }
        });

        txtKdBarang.setEditable(false);
        txtKdBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKdBarangActionPerformed(evt);
            }
        });

        txtNmBarang.setEditable(false);

        txtSatuan.setEditable(false);

        txtHrgBarang.setEditable(false);

        btnCariBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Search-2-icon.png"))); // NOI18N
        btnCariBarang.setText("Cari");
        btnCariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariBarangActionPerformed(evt);
            }
        });

        jLabel11.setText("Data Pelanggan");

        jLabel12.setText("Nama Pelanggan");

        jLabel13.setText("Alamat Pelanggan");

        jLabel14.setText("Telepon Pelanggan");

        jLabel15.setText("Id Pelanggan");

        txtIdPelanggan.setEditable(false);

        txtNmPelanggan.setEditable(false);

        txtAlmtPelanggan.setEditable(false);

        txtNoTelp.setEditable(false);

        btnCariPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Search-2-icon.png"))); // NOI18N
        btnCariPelanggan.setText("Cari");
        btnCariPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPelangganActionPerformed(evt);
            }
        });

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/edit_add.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/button_cancel.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/32x32/Floppy-Small-icon.png"))); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setEnabled(false);
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

        tabelOtot.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode Barang", "Nama Barang", "Satuan Barang", "Jumlah Pesan", "Harga Pesan", "Jumlah Harga"
            }
        ));
        tabelOtot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelOtotMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelOtot);

        txtTotal.setEditable(false);

        jLabel16.setText("Total Harga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(97, 97, 97)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(400, 400, 400))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel5))
                                                .addGap(38, 38, 38)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtSatuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                                                            .addComponent(txtNmBarang)
                                                            .addComponent(txtKdBarang)
                                                            .addComponent(txtHrgBarang)
                                                            .addComponent(txtHrgPesan))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnCariBarang))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtJmlPsn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel1))
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtNoSP)
                                                    .addComponent(txtTglPsn, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(104, 104, 104)
                                                .addComponent(btnTambah)
                                                .addGap(73, 73, 73)
                                                .addComponent(btnHapus)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(btnSimpan))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnBatal)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnKeluar))
                                            .addComponent(txtIdPelanggan)
                                            .addComponent(txtNmPelanggan)
                                            .addComponent(txtAlmtPelanggan)
                                            .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCariPelanggan)))))))
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCariPelanggan))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtNmPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtAlmtPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(txtNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNoSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtTglPsn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtKdBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnCariBarang)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNmBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtHrgBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(txtHrgPesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtJmlPsn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnHapus)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal)
                    .addComponent(btnKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPelangganActionPerformed
        // TODO add your handling code here:
        Hapus();
        new CariDataPelanggan().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCariPelangganActionPerformed

    private void btnCariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariBarangActionPerformed
        new CariDataBarang().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCariBarangActionPerformed

    private void txtKdBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKdBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKdBarangActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        Clear();
        Hapus();
        Hapus1();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        this.setVisible(false);
        new FrmMaster().setVisible(true);
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        btnTambah.setEnabled(false);
        
        Object[] row = new Object[6];
        DefaultTableModel model = (DefaultTableModel) tabelOtot.getModel();
        row[0] = txtKdBarang.getText();
        row[1] = txtNmBarang.getText();
        row[2] = txtSatuan.getText();
        row[3] = txtJmlPsn.getValue();
        row[4] = txtHrgPesan.getText();
        int a = (int) txtJmlPsn.getValue();
        int b = Integer.parseInt(txtHrgPesan.getText());
        int c = a * b;
        row[5] = c;
//        System.out.println(row[0].toString());

        model.addRow(row);
        String d = row[0].toString();
//        if (model.getValueAt(1, 0).equals(model.getValueAt(0, 0))) {
//            btnTambah.setEnabled(false);
//            JOptionPane.showMessageDialog(null, "TOLOOL!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            
//        }
        PreparedStatement ps;
        String query = "INSERT INTO d_detil(`KdBarang`, `NmBarang`, `Satuan`, `JumlahP`, `HrgPesan`, `JumlahH`) VALUES (?,?,?,?,?,?)";

        try {
            ps = koneksi.getConnection().prepareStatement(query);
            ps.setString(1, (String) row[0]);
            ps.setString(2, (String) row[1]);
            ps.setString(3, (String) row[2]);
            ps.setInt(4, a);
            ps.setInt(5, b);
            ps.setInt(6, c);
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Pelanggan Berhasil Ditambahkan!", "Berhasil!", JOptionPane.INFORMATION_MESSAGE);
//                Clear();

            }
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
            Total();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        SimpanAda();
        SimpanSP();
        Clear();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed

        DefaultTableModel model = (DefaultTableModel) tabelOtot.getModel();

        int baris = tabelOtot.getSelectedRow();
        int kolom = tabelOtot.getSelectedColumn();
        System.out.println(tabelOtot.getValueAt(baris, 0).toString());
        PreparedStatement ps;
        String query = "DELETE FROM `d_detil` WHERE KdBarang='"
                + tabelOtot.getValueAt(baris, 0).toString()
                + "'";
        try {
            model.removeRow(tabelOtot.getSelectedRow());
            ps = koneksi.getConnection().prepareStatement(query);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(EntryPelanggan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void tabelOtotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelOtotMouseClicked
        int baris = tabelOtot.getSelectedColumn();
        System.out.println(tabelOtot.getValueAt(baris, 0).toString());

    }//GEN-LAST:event_tabelOtotMouseClicked

    private void txtTglPsnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTglPsnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTglPsnActionPerformed

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
            java.util.logging.Logger.getLogger(EntrySuratPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntrySuratPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntrySuratPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntrySuratPesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EntrySuratPesan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariBarang;
    private javax.swing.JButton btnCariPelanggan;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelOtot;
    private javax.swing.JTextField txtAlmtPelanggan;
    private javax.swing.JTextField txtHrgBarang;
    private javax.swing.JTextField txtHrgPesan;
    private javax.swing.JTextField txtIdPelanggan;
    private javax.swing.JSpinner txtJmlPsn;
    private javax.swing.JTextField txtKdBarang;
    private javax.swing.JTextField txtNmBarang;
    private javax.swing.JTextField txtNmPelanggan;
    private javax.swing.JTextField txtNoSP;
    private javax.swing.JTextField txtNoTelp;
    private javax.swing.JTextField txtSatuan;
    private javax.swing.JTextField txtTglPsn;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
