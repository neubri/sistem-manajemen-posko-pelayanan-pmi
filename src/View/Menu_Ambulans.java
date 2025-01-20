/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author neubr
 */
public class Menu_Ambulans extends javax.swing.JPanel {
    Connection conn = controller.koneksi.getKoneksi();
ResultSet rs = null;
PreparedStatement pst = null; 
int xx , xy;
private DefaultTableModel tabmode;
String Lokasi;
Object[] Baris ={"Id Ambulans","Jenis Kemdaraan","Masa Berlaku"};

    /**
     * Creates new form Menu_Dashboard
     */
    public Menu_Ambulans() {
        initComponents();
        Locale locale = new Locale("id","ID");
        Locale.setDefault(locale);
        kosong();
        aktif();
        datatable();
        autonumber();
    }
    
     private static Connection koneksi;
    public static Connection getKoneksi(){
    if(koneksi==null){
        try{
            String url;
            url="jdbc:mysql://localhost:3306/poskopmi";
            String username = "root";
            String password = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            koneksi = DriverManager.getConnection(url,username,password);
        }catch (SQLException t){
            JOptionPane.showMessageDialog(null,"error koneksi");
        }
    }return koneksi;
}static Object getConnection(){
        throw new UnsupportedOperationException("Not yet implementation");
    }

    protected void aktif(){
    txtIdAmbulans.requestFocus();
    tabmode = new DefaultTableModel(null, Baris);
    tabelambulans.setModel(tabmode);
    }
    
 protected void autonumber(){
 String id_ambulans="";
 try{
 String sql = "SELECT id_ambulans FROM ambulans order by id_ambulans asc";
 PreparedStatement stat = conn.prepareStatement(sql);
 ResultSet rs=stat.executeQuery(sql);

 while(rs.next()){
 id_ambulans=rs.getString("id_ambulans");
 }
 }catch(SQLException sqle){id_ambulans="";}
 if(id_ambulans.length() <1){id_ambulans="ID0000";}
 String ur=id_ambulans.substring(2);
 int u=Integer.parseInt(ur)+1;
 System.out.println(ur+"=="+u);
 if(u<10)
 {ur="000"+u;}
 else if(u<100)
 {ur="00"+u;}
 else if(u<1000)
 {ur="0"+u;}
 else
 {ur=""+u;}
 id_ambulans ="ID"+ur;
 txtIdAmbulans.setText( id_ambulans);
}
    
    protected void kosong(){
    txtIdAmbulans.setText("");
    txtJenisKendaraan.setText("");
    txtMasa.setText("");
 }
    
    protected void datatable(){
 Object[] Baris ={"Id Ambulans","Jenis Kendaraan","Masa Berlaku"};
 tabmode = new DefaultTableModel(null, Baris);
 String cariitem=cari.getText();
 
 try{
 String sql = "SELECT*FROM ambulans where id_ambulans like '%"+cariitem+"%' or jenis_kendaraan like'%"+cariitem+"%' order by id_ambulans asc";
 java.sql.Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
 tabmode.addRow(new Object[]{
 hasil.getString(1),
 hasil.getString(2),
 hasil.getString(3),
 });
 }
 tabelambulans.setModel(tabmode);
 } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
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

        btngrp_Jeniskelamin = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        pn_Main = new javax.swing.JPanel();
        pn_View = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelambulans = new Custom.Custom_JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_firstpage = new Custom.Custom_JButtonRounded();
        btn_before = new Custom.Custom_JButtonRounded();
        btn_after = new Custom.Custom_JButtonRounded();
        btn_lastpage = new Custom.Custom_JButtonRounded();
        cb_halaman = new Custom.Custom_JComboBox();
        jLabel4 = new javax.swing.JLabel();
        btnTambah = new Custom.Custom_JButtonRounded();
        btnHapus = new Custom.Custom_JButtonRounded();
        btnBatal = new Custom.Custom_JButtonRounded();
        cari = new Custom.Custom_JTextFieldRounded();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btncari = new Custom.Custom_JButtonRounded();
        Ctak = new Custom.Custom_JButtonRounded();
        pn_Add = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnSimpan = new Custom.Custom_JButtonRounded();
        custom_JButtonRounded6 = new Custom.Custom_JButtonRounded();
        txtIdAmbulans = new Custom.Custom_JTextFieldRounded();
        jLabel13 = new javax.swing.JLabel();
        txtJenisKendaraan = new Custom.Custom_JTextFieldRounded();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMasa = new Custom.Custom_JTextFieldRounded();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txtMasa);

        setLayout(new java.awt.CardLayout());

        pn_Main.setBackground(new java.awt.Color(255, 255, 255));
        pn_Main.setPreferredSize(new java.awt.Dimension(1028, 658));
        pn_Main.setLayout(new java.awt.CardLayout());

        pn_View.setBackground(new java.awt.Color(255, 255, 255));
        pn_View.setPreferredSize(new java.awt.Dimension(1028, 658));

        tabelambulans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelambulans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelambulansMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelambulans);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Data Daftar Ambulans");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon AmbulanSB.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btn_firstpage.setBackground(new java.awt.Color(255, 0, 0));
        btn_firstpage.setForeground(new java.awt.Color(255, 255, 255));
        btn_firstpage.setText("First Page");
        btn_firstpage.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_firstpage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstpageActionPerformed(evt);
            }
        });

        btn_before.setBackground(new java.awt.Color(255, 0, 0));
        btn_before.setForeground(new java.awt.Color(255, 255, 255));
        btn_before.setText("<");
        btn_before.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_before.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_beforeActionPerformed(evt);
            }
        });

        btn_after.setBackground(new java.awt.Color(255, 0, 0));
        btn_after.setForeground(new java.awt.Color(255, 255, 255));
        btn_after.setText(">");
        btn_after.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_after.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_afterActionPerformed(evt);
            }
        });

        btn_lastpage.setBackground(new java.awt.Color(255, 0, 0));
        btn_lastpage.setForeground(new java.awt.Color(255, 255, 255));
        btn_lastpage.setText("Last Page");
        btn_lastpage.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_lastpage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastpageActionPerformed(evt);
            }
        });

        cb_halaman.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cb_halaman.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8", "16", "32", "64" }));
        cb_halaman.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jLabel4.setText("Halaman of halaman");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(btn_firstpage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_after, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_lastpage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(442, 442, 442))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_halaman, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_after, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_before, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_firstpage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lastpage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btnTambah.setBackground(new java.awt.Color(0, 153, 255));
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Add.png"))); // NOI18N
        btnTambah.setText("TAMBAH");
        btnTambah.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 204, 0));
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Delete.png"))); // NOI18N
        btnHapus.setText("HAPUS");
        btnHapus.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setBackground(new java.awt.Color(0, 204, 153));
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Cancel.png"))); // NOI18N
        btnBatal.setText("BATAL");
        btnBatal.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        cari.setForeground(new java.awt.Color(102, 102, 102));
        cari.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });
        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariKeyPressed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon DashboardSB.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Master Data > Ambulans");
        jLabel2.setPreferredSize(new java.awt.Dimension(176, 19));

        btncari.setBackground(new java.awt.Color(255, 102, 102));
        btncari.setForeground(new java.awt.Color(255, 255, 255));
        btncari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Cancel.png"))); // NOI18N
        btncari.setText("CARI");
        btncari.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btncari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariActionPerformed(evt);
            }
        });

        Ctak.setBackground(new java.awt.Color(153, 153, 153));
        Ctak.setForeground(new java.awt.Color(255, 255, 255));
        Ctak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cetakputih.png"))); // NOI18N
        Ctak.setText("CETAK");
        Ctak.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Ctak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CtakActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_ViewLayout = new javax.swing.GroupLayout(pn_View);
        pn_View.setLayout(pn_ViewLayout);
        pn_ViewLayout.setHorizontalGroup(
            pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ViewLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_ViewLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_ViewLayout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Ctak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        pn_ViewLayout.setVerticalGroup(
            pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ViewLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(pn_ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ctak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        pn_Main.add(pn_View, "card2");

        pn_Add.setBackground(new java.awt.Color(255, 255, 255));
        pn_Add.setPreferredSize(new java.awt.Dimension(1028, 658));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Tambah Data Ambulans");

        btnSimpan.setBackground(new java.awt.Color(0, 153, 255));
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Save.png"))); // NOI18N
        btnSimpan.setText("SIMPAN");
        btnSimpan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        custom_JButtonRounded6.setBackground(new java.awt.Color(0, 204, 153));
        custom_JButtonRounded6.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Cancel.png"))); // NOI18N
        custom_JButtonRounded6.setText("BATAL");
        custom_JButtonRounded6.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded6ActionPerformed(evt);
            }
        });

        txtIdAmbulans.setForeground(new java.awt.Color(102, 102, 102));
        txtIdAmbulans.setText("Id Ambulans");
        txtIdAmbulans.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtIdAmbulans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAmbulansActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Id Ambulans");

        txtJenisKendaraan.setForeground(new java.awt.Color(102, 102, 102));
        txtJenisKendaraan.setText("Jenis Kendaraan");
        txtJenisKendaraan.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtJenisKendaraan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisKendaraanActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Jenis Kendaraan");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Tanggal Masa Berlaku");

        txtMasa.setForeground(new java.awt.Color(102, 102, 102));
        txtMasa.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtMasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMasaActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon AmbulanSB.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Master Data > Ambulans");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon DashboardSB.png"))); // NOI18N

        javax.swing.GroupLayout pn_AddLayout = new javax.swing.GroupLayout(pn_Add);
        pn_Add.setLayout(pn_AddLayout);
        pn_AddLayout.setHorizontalGroup(
            pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_AddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(custom_JButtonRounded6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 890, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJenisKendaraan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdAmbulans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_AddLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 553, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)))
                        .addGap(20, 20, 20))))
        );
        pn_AddLayout.setVerticalGroup(
            pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_AddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(614, 614, 614))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(custom_JButtonRounded6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdAmbulans, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJenisKendaraan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMasa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(317, 317, 317))))
        );

        pn_Main.add(pn_Add, "card2");

        add(pn_Main, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_firstpageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstpageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_firstpageActionPerformed

    private void btn_beforeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_beforeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_beforeActionPerformed

    private void btn_afterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_afterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_afterActionPerformed

    private void btn_lastpageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastpageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lastpageActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        pn_Main.removeAll();
        pn_Main.add(pn_Add);
        pn_Main.repaint();
        pn_Main.revalidate();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
          int ok = JOptionPane.showConfirmDialog(null,"hapus","konfirmasidialog",JOptionPane.YES_NO_OPTION);
    if (ok==0){
    String sql = "delete from ambulans where id_ambulans ='"+txtIdAmbulans.getText()+"'";
    try{
    PreparedStatement stat = conn.prepareStatement(sql);
    stat.executeUpdate();
    JOptionPane.showMessageDialog(null, "data berhasil dihapus");
    kosong();
    txtIdAmbulans.requestFocus();
    }
    catch (SQLException e){
    JOptionPane.showMessageDialog(null, "data gagal dihapus"+e);
    }
    datatable();
    }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBatalActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
         String sql = "insert into ambulans values (?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdAmbulans.getText());
            stat.setString(2, txtJenisKendaraan.getText());
            stat.setString(3, txtMasa.getText());
            
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
            txtIdAmbulans.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal disimpan"+e);
        }
        datatable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void custom_JButtonRounded6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded6ActionPerformed
        pn_Main.removeAll();
        pn_Main.add(pn_View);
        pn_Main.repaint();
        pn_Main.revalidate();
    }//GEN-LAST:event_custom_JButtonRounded6ActionPerformed

    private void txtIdAmbulansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAmbulansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAmbulansActionPerformed

    private void txtJenisKendaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisKendaraanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJenisKendaraanActionPerformed

    private void txtMasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMasaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMasaActionPerformed

    private void btncariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariActionPerformed
       datatable();
    }//GEN-LAST:event_btncariActionPerformed

    private void cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    datatable();
         }
    }//GEN-LAST:event_cariKeyPressed

    private void tabelambulansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelambulansMouseClicked
        int bar = tabelambulans.getSelectedRow();
 String a = tabmode.getValueAt(bar, 0).toString();
 String b = tabmode.getValueAt(bar, 1).toString();
 String c = tabmode.getValueAt(bar, 2).toString();

 txtIdAmbulans.setText(a);
 txtJenisKendaraan.setText(b);
 txtMasa.setText(c);
 datatable();
    }//GEN-LAST:event_tabelambulansMouseClicked

    private void CtakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CtakActionPerformed
         try{
            String path ="./src/Report/reportambulans.jasper";
            HashMap parameter = new HashMap();
            JasperPrint print =
            JasperFillManager.fillReport(path, parameter, conn);
            JasperViewer.viewReport(print, false);
        }catch(JRException e){
            JOptionPane.showMessageDialog(null,"Dokumen Tidak Ada");
            System.out.println(e);
        }
    }//GEN-LAST:event_CtakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Custom_JButtonRounded Ctak;
    private Custom.Custom_JButtonRounded btnBatal;
    private Custom.Custom_JButtonRounded btnHapus;
    private Custom.Custom_JButtonRounded btnSimpan;
    private Custom.Custom_JButtonRounded btnTambah;
    private Custom.Custom_JButtonRounded btn_after;
    private Custom.Custom_JButtonRounded btn_before;
    private Custom.Custom_JButtonRounded btn_firstpage;
    private Custom.Custom_JButtonRounded btn_lastpage;
    private Custom.Custom_JButtonRounded btncari;
    private javax.swing.ButtonGroup btngrp_Jeniskelamin;
    private Custom.Custom_JTextFieldRounded cari;
    private Custom.Custom_JComboBox cb_halaman;
    private Custom.Custom_JButtonRounded custom_JButtonRounded6;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pn_Add;
    private javax.swing.JPanel pn_Main;
    private javax.swing.JPanel pn_View;
    private Custom.Custom_JTable tabelambulans;
    private Custom.Custom_JTextFieldRounded txtIdAmbulans;
    private Custom.Custom_JTextFieldRounded txtJenisKendaraan;
    private Custom.Custom_JTextFieldRounded txtMasa;
    // End of variables declaration//GEN-END:variables
}
