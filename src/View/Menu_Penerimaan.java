/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author neubri reja dimas purkon
 */
public class Menu_Penerimaan extends javax.swing.JPanel {
     Connection conn = controller.koneksi.getKoneksi();
ResultSet rs = null;
PreparedStatement pst = null; 
int xx , xy;
private DefaultTableModel tabmode;
public String NIK, nmpasien, JK;
public String IdPtgs, nmptgs;
public String IdAmb, jenis;
public String idter, NamaPasien, NamaPetugas, JenisKndaraan;
//public string itu variabel baru yang di panggil di item terpilih
String Lokasi;
Object[] Baris ={"Id Terima","NIK Pasien","Jenis Kelamin","Id Petugas","Nama Petugas","Id Ambulans","Jenis Kendaraan","Jenis Kejadian","Waktu","Keterangan"};

    public Menu_Penerimaan() {
        initComponents();
         Locale locale = new Locale("id","ID");
         Locale.setDefault(locale);
        kosong();
        aktif();
        datatable();
        datatable1();
        autonumber();
        autonumber1();
    }

     protected void aktif(){
    txtNIK.requestFocus();
    txtIdPtgs.requestFocus();
    txtIdAmb.requestFocus();
    txtidter.requestFocus();
   
 }
     
  public void itemTerpilihPasien(){
PopUpPasien Prps = new PopUpPasien();
Prps.Rps = this;
 txtNIK.setText(NIK);
 txtnmpasien.setText(nmpasien);
 txtJK.setText(JK);
}
  
  public void itemTerpilihPetugas(){
PopUpPetugasPosko Prpt = new PopUpPetugasPosko();
Prpt.Rpt = this;
 txtIdPtgs.setText(IdPtgs);
 txtnmptgs.setText(nmptgs);
}
  
  public void itemTerpilihAmbulans(){
PopUpAmbulans Prpa = new PopUpAmbulans();
Prpa.Rpa = this;
txtIdAmb.setText(IdAmb);
txtjenis.setText(jenis);
}
  
  public void itemTerpilihTerima(){
PopUpTerima Prpt = new PopUpTerima();
Prpt.Rpt = this;
txtidter.setText(idter);
txt_NamaPasien.setText(NamaPasien);
txt_NamaPetugas.setText(NamaPetugas);
txt_JenisKndaraan.setText(JenisKndaraan);
}
  
  
  protected void autonumber(){
 String idt="";
 try{
 String sql = "SELECT id_terima FROM penerimaan order by id_terima asc";
 PreparedStatement stat = conn.prepareStatement(sql);
 ResultSet rs=stat.executeQuery(sql);

 while(rs.next()){
 idt=rs.getString("idt");
 }
 }catch(SQLException sqle){idt="";}
 if(idt.length() <1){idt="IT000";}
 String ur=idt.substring(2);
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
 idt ="IT"+ur;
 txtIdTrima.setText(idt);
}
  
  protected void autonumber1(){
 String ids="";
 try{
 String sql = "SELECT id_standby FROM standby order by id_standby asc";
 PreparedStatement stat = conn.prepareStatement(sql);
 ResultSet rs=stat.executeQuery(sql);

 while(rs.next()){
 ids=rs.getString("ids");
 }
 }catch(SQLException sqle){ids="";}
 if(ids.length() <1){ids="IS000";}
 String ur=ids.substring(2);
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
 ids ="IS"+ur;
 txtIdStndby.setText(ids);
}
    
   protected void kosong(){
 txtIdTrima.setText("");         
 txtNIK.setText("");
 txtnmpasien.setText("");
 txtJK.setText("");
 txtIdPtgs.setText("");
 txtnmptgs.setText("");
 txtIdAmb.setText("");
 txtjenis.setText("");
 jeniskejadian.clearSelection();
 txtWktu.setText("");
 txtKet.setText("");
 }
   
    protected void datatable(){
 Object[] Baris ={"Id Terima","NIK Pasien","Nama Pasien","Jenis Kelamin","Id Petugas","Nama Petugas","Id Ambulans","Jenis Kendaraan","Jenis Kejadian","Waktu","Keterangan"};
 tabmode = new DefaultTableModel(null, Baris);
 String cariitem=txCari.getText();
 
 try{
 String sql = "SELECT*FROM penerimaan where nik like '%"+cariitem+"%' or nm_pasien like'%"+cariitem+"%' order by nik asc";
 java.sql.Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
 tabmode.addRow(new Object[]{
 hasil.getString(1),
 hasil.getString(2),
 hasil.getString(3),
 hasil.getString(4),
 hasil.getString(5),
 hasil.getString(6),
 hasil.getString(7),
 hasil.getString(8),
 hasil.getString(9),
 hasil.getString(10),
 hasil.getString(11),
 });
 }
 tabelterima.setModel(tabmode);
 } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
    }    
    }
    
    protected void datatable1(){
 Object[] Baris ={"Id Standby","Id Terima","Nama Pasien","Nama Petugas","Jenis Kendaraan","Keterangan"};
 tabmode = new DefaultTableModel(null, Baris);
 String cariitem=txt_caristndby.getText();
 
 try{
 String sql = "SELECT*FROM standby where id_terima like '%"+cariitem+"%' or nm_pasien like'%"+cariitem+"%' order by id_terima asc";
 java.sql.Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
 tabmode.addRow(new Object[]{
 hasil.getString(1),
 hasil.getString(2),
 hasil.getString(3),
 hasil.getString(4),
 hasil.getString(5),
 hasil.getString(6),
 });
 }
 tabelstandby.setModel(tabmode);
 } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "data gagal dipanggil"+e);
    }    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrp_Jeniskelamin = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        dateChooser2 = new com.raven.datechooser.DateChooser();
        jeniskejadian = new javax.swing.ButtonGroup();
        pn_Main = new javax.swing.JPanel();
        pn_View = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelterima = new Custom.Custom_JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_firstpage = new Custom.Custom_JButtonRounded();
        btn_before = new Custom.Custom_JButtonRounded();
        btn_after = new Custom.Custom_JButtonRounded();
        btn_lastpage = new Custom.Custom_JButtonRounded();
        cb_halaman = new Custom.Custom_JComboBox();
        jLabel4 = new javax.swing.JLabel();
        custom_JButtonRounded1 = new Custom.Custom_JButtonRounded();
        hps = new Custom.Custom_JButtonRounded();
        custom_JButtonRounded3 = new Custom.Custom_JButtonRounded();
        txCari = new Custom.Custom_JTextFieldRounded();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        butoncri = new Custom.Custom_JButtonRounded();
        Cetak = new Custom.Custom_JButtonRounded();
        pn_Add = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnSimpan = new Custom.Custom_JButtonRounded();
        btnBatal = new Custom.Custom_JButtonRounded();
        txtIdTrima = new Custom.Custom_JTextFieldRounded();
        jLabel13 = new javax.swing.JLabel();
        txtNIK = new Custom.Custom_JTextFieldRounded();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIdPtgs = new Custom.Custom_JTextFieldRounded();
        txtIdAmb = new Custom.Custom_JTextFieldRounded();
        radrujuk = new javax.swing.JRadioButton();
        radkec = new javax.swing.JRadioButton();
        radGd = new javax.swing.JRadioButton();
        txtWktu = new Custom.Custom_JTextFieldRounded();
        caripasien = new Custom.Custom_JButtonRounded();
        btnNext = new Custom.Custom_JButtonRounded();
        jLabel22 = new javax.swing.JLabel();
        txtnmpasien = new Custom.Custom_JTextFieldRounded();
        caripetugas = new Custom.Custom_JButtonRounded();
        cariambulans = new Custom.Custom_JButtonRounded();
        txtJK = new Custom.Custom_JTextFieldRounded();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtnmptgs = new Custom.Custom_JTextFieldRounded();
        jSeparator1 = new javax.swing.JSeparator();
        txtKet = new Custom.Custom_JTextFieldRounded();
        txtjenis = new Custom.Custom_JTextFieldRounded();
        jLabel26 = new javax.swing.JLabel();
        pn_Standby = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        btn_simpanstndby = new Custom.Custom_JButtonRounded();
        custom_JButtonRounded13 = new Custom.Custom_JButtonRounded();
        txtIdStndby = new Custom.Custom_JTextFieldRounded();
        jLabel29 = new javax.swing.JLabel();
        txtidter = new Custom.Custom_JTextFieldRounded();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_Ket = new Custom.Custom_JTextFieldRounded();
        cariterima = new Custom.Custom_JButtonRounded();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txt_NamaPasien = new Custom.Custom_JTextFieldRounded();
        txt_NamaPetugas = new Custom.Custom_JTextFieldRounded();
        jLabel41 = new javax.swing.JLabel();
        txt_JenisKndaraan = new Custom.Custom_JTextFieldRounded();
        pn_ViewStandby = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelstandby = new Custom.Custom_JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_firstpage1 = new Custom.Custom_JButtonRounded();
        btn_before1 = new Custom.Custom_JButtonRounded();
        btn_after1 = new Custom.Custom_JButtonRounded();
        btn_lastpage1 = new Custom.Custom_JButtonRounded();
        cb_halaman1 = new Custom.Custom_JComboBox();
        jLabel12 = new javax.swing.JLabel();
        custom_JButtonRounded8 = new Custom.Custom_JButtonRounded();
        custom_JButtonRounded9 = new Custom.Custom_JButtonRounded();
        custom_JButtonRounded10 = new Custom.Custom_JButtonRounded();
        txt_caristndby = new Custom.Custom_JTextFieldRounded();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cari_stndby = new Custom.Custom_JButtonRounded();

        dateChooser1.setTextRefernce(txtWktu);

        setLayout(new java.awt.CardLayout());

        pn_Main.setBackground(new java.awt.Color(255, 255, 255));
        pn_Main.setPreferredSize(new java.awt.Dimension(1028, 658));
        pn_Main.setLayout(new java.awt.CardLayout());

        pn_View.setBackground(new java.awt.Color(255, 255, 255));
        pn_View.setPreferredSize(new java.awt.Dimension(1028, 658));

        tabelterima.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelterima.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelterimaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelterima);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Data Daftar Penerimaan");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon PenerimaanSB.png"))); // NOI18N

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
                .addContainerGap(341, Short.MAX_VALUE))
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

        custom_JButtonRounded1.setBackground(new java.awt.Color(0, 153, 255));
        custom_JButtonRounded1.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Add.png"))); // NOI18N
        custom_JButtonRounded1.setText("TAMBAH");
        custom_JButtonRounded1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded1ActionPerformed(evt);
            }
        });

        hps.setBackground(new java.awt.Color(255, 204, 0));
        hps.setForeground(new java.awt.Color(255, 255, 255));
        hps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Delete.png"))); // NOI18N
        hps.setText("HAPUS");
        hps.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        hps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hpsActionPerformed(evt);
            }
        });

        custom_JButtonRounded3.setBackground(new java.awt.Color(0, 204, 153));
        custom_JButtonRounded3.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Cancel.png"))); // NOI18N
        custom_JButtonRounded3.setText("BATAL");
        custom_JButtonRounded3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded3ActionPerformed(evt);
            }
        });

        txCari.setForeground(new java.awt.Color(102, 102, 102));
        txCari.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCariActionPerformed(evt);
            }
        });
        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon DashboardSB.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Transaksi > Penerimaan");
        jLabel2.setPreferredSize(new java.awt.Dimension(176, 19));

        butoncri.setBackground(new java.awt.Color(255, 102, 102));
        butoncri.setForeground(new java.awt.Color(255, 255, 255));
        butoncri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/searchputih.png"))); // NOI18N
        butoncri.setText("CARI");
        butoncri.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        butoncri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butoncriActionPerformed(evt);
            }
        });

        Cetak.setBackground(new java.awt.Color(153, 153, 153));
        Cetak.setForeground(new java.awt.Color(255, 255, 255));
        Cetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cetakputih.png"))); // NOI18N
        Cetak.setText("CETAK");
        Cetak.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        Cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CetakActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_ViewLayout.createSequentialGroup()
                        .addComponent(custom_JButtonRounded1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(hps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(custom_JButtonRounded3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(butoncri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(custom_JButtonRounded1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(custom_JButtonRounded3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butoncri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        pn_Main.add(pn_View, "card2");

        pn_Add.setBackground(new java.awt.Color(255, 255, 255));
        pn_Add.setPreferredSize(new java.awt.Dimension(1028, 658));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Tambah Data Penerimaan");

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

        txtIdTrima.setForeground(new java.awt.Color(102, 102, 102));
        txtIdTrima.setText("Id Terima");
        txtIdTrima.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtIdTrima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdTrimaActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Id Terima");

        txtNIK.setForeground(new java.awt.Color(102, 102, 102));
        txtNIK.setText("NIK");
        txtNIK.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtNIK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIKActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("NIK Pasien");

        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Id Petugas");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Id Ambulans");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Jenis Kejadian");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Waktu/Tanggal");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon PenerimaanSB.png"))); // NOI18N

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Keterangan");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Transaksi > Penerimaan");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon DashboardSB.png"))); // NOI18N

        txtIdPtgs.setForeground(new java.awt.Color(102, 102, 102));
        txtIdPtgs.setText("Id Petugas");
        txtIdPtgs.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtIdPtgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdPtgsActionPerformed(evt);
            }
        });

        txtIdAmb.setForeground(new java.awt.Color(102, 102, 102));
        txtIdAmb.setText("Id Ambulans");
        txtIdAmb.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtIdAmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdAmbActionPerformed(evt);
            }
        });

        radrujuk.setBackground(new java.awt.Color(255, 255, 255));
        jeniskejadian.add(radrujuk);
        radrujuk.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        radrujuk.setText("Rujukan");
        radrujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radrujukActionPerformed(evt);
            }
        });

        radkec.setBackground(new java.awt.Color(255, 255, 255));
        jeniskejadian.add(radkec);
        radkec.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        radkec.setText("Kecelakaan");

        radGd.setBackground(new java.awt.Color(255, 255, 255));
        jeniskejadian.add(radGd);
        radGd.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        radGd.setText("Gawat Darurat");

        txtWktu.setForeground(new java.awt.Color(102, 102, 102));
        txtWktu.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtWktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWktuActionPerformed(evt);
            }
        });

        caripasien.setBackground(new java.awt.Color(255, 102, 102));
        caripasien.setForeground(new java.awt.Color(255, 255, 255));
        caripasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/btncari.png"))); // NOI18N
        caripasien.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        caripasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caripasienActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(255, 204, 0));
        btnNext.setForeground(new java.awt.Color(255, 255, 255));
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/next.png"))); // NOI18N
        btnNext.setText("NEXT");
        btnNext.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Nama");

        txtnmpasien.setForeground(new java.awt.Color(102, 102, 102));
        txtnmpasien.setText("Nama Pasien");
        txtnmpasien.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtnmpasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmpasienActionPerformed(evt);
            }
        });

        caripetugas.setBackground(new java.awt.Color(255, 102, 102));
        caripetugas.setForeground(new java.awt.Color(255, 255, 255));
        caripetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/btncari.png"))); // NOI18N
        caripetugas.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        caripetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caripetugasActionPerformed(evt);
            }
        });

        cariambulans.setBackground(new java.awt.Color(255, 102, 102));
        cariambulans.setForeground(new java.awt.Color(255, 255, 255));
        cariambulans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/btncari.png"))); // NOI18N
        cariambulans.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cariambulans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariambulansActionPerformed(evt);
            }
        });

        txtJK.setForeground(new java.awt.Color(102, 102, 102));
        txtJK.setText("Jenis Kelamin");
        txtJK.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJKActionPerformed(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Jenis Kelamin");

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Nama");

        txtnmptgs.setForeground(new java.awt.Color(102, 102, 102));
        txtnmptgs.setText("Nama Petugas");
        txtnmptgs.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtnmptgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnmptgsActionPerformed(evt);
            }
        });

        txtKet.setForeground(new java.awt.Color(102, 102, 102));
        txtKet.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKetActionPerformed(evt);
            }
        });

        txtjenis.setForeground(new java.awt.Color(102, 102, 102));
        txtjenis.setText("Jenis Kendaraan");
        txtjenis.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjenisActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Jenis Kendaraan");

        javax.swing.GroupLayout pn_AddLayout = new javax.swing.GroupLayout(pn_Add);
        pn_Add.setLayout(pn_AddLayout);
        pn_AddLayout.setHorizontalGroup(
            pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(pn_AddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(caripasien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addGap(20, 20, 20))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addGap(18, 175, Short.MAX_VALUE)
                                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_AddLayout.createSequentialGroup()
                                        .addComponent(txtIdPtgs, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(caripetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel15))
                                .addGap(102, 102, 102)
                                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pn_AddLayout.createSequentialGroup()
                                        .addComponent(txtIdAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cariambulans, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16)
                                    .addComponent(txtjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_AddLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdTrima, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)))
                        .addGap(31, 31, 31))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnmpasien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(txtnmptgs, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(433, 433, 433))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(txtJK, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addComponent(radrujuk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radkec)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radGd)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18)
                            .addComponent(txtKet, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
                            .addComponent(txtWktu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pn_AddLayout.setVerticalGroup(
            pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_AddLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(83, 83, 83))
                    .addGroup(pn_AddLayout.createSequentialGroup()
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_AddLayout.createSequentialGroup()
                                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtIdTrima, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(28, 28, 28)))
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdAmb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdPtgs, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caripetugas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cariambulans, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNIK, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(caripasien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnmpasien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnmptgs, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtJK, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(pn_AddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radrujuk)
                    .addComponent(radkec)
                    .addComponent(radGd))
                .addGap(26, 26, 26)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtWktu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtKet, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
        );

        pn_Main.add(pn_Add, "card2");

        pn_Standby.setBackground(new java.awt.Color(255, 255, 255));
        pn_Standby.setPreferredSize(new java.awt.Dimension(1028, 658));

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Tambah Data Standby Ambulan");

        btn_simpanstndby.setBackground(new java.awt.Color(0, 153, 255));
        btn_simpanstndby.setForeground(new java.awt.Color(255, 255, 255));
        btn_simpanstndby.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Save.png"))); // NOI18N
        btn_simpanstndby.setText("SIMPAN");
        btn_simpanstndby.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_simpanstndby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanstndbyActionPerformed(evt);
            }
        });

        custom_JButtonRounded13.setBackground(new java.awt.Color(0, 204, 153));
        custom_JButtonRounded13.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/previous.png"))); // NOI18N
        custom_JButtonRounded13.setText("KEMBALI");
        custom_JButtonRounded13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded13ActionPerformed(evt);
            }
        });

        txtIdStndby.setForeground(new java.awt.Color(102, 102, 102));
        txtIdStndby.setText("Id Standby Ambulan");
        txtIdStndby.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtIdStndby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdStndbyActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Id Standby Ambulan");

        txtidter.setForeground(new java.awt.Color(102, 102, 102));
        txtidter.setText("Id Terima");
        txtidter.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txtidter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidterActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 102, 102));
        jLabel30.setText("Id Terima");

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon AmbulanSB.png"))); // NOI18N

        jLabel36.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setText("Keterangan");

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 102, 102));
        jLabel37.setText("Transaksi > Penerimaan");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon DashboardSB.png"))); // NOI18N

        txt_Ket.setForeground(new java.awt.Color(102, 102, 102));
        txt_Ket.setText("Keterangan");
        txt_Ket.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txt_Ket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_KetActionPerformed(evt);
            }
        });

        cariterima.setBackground(new java.awt.Color(255, 102, 102));
        cariterima.setForeground(new java.awt.Color(255, 255, 255));
        cariterima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/btncari.png"))); // NOI18N
        cariterima.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cariterima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariterimaActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("Nama Pasien");

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 102, 102));
        jLabel40.setText("Nama Petugas");

        txt_NamaPasien.setForeground(new java.awt.Color(102, 102, 102));
        txt_NamaPasien.setText("Nama Pasien");
        txt_NamaPasien.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txt_NamaPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NamaPasienActionPerformed(evt);
            }
        });

        txt_NamaPetugas.setForeground(new java.awt.Color(102, 102, 102));
        txt_NamaPetugas.setText("Nama Petugas");
        txt_NamaPetugas.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txt_NamaPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NamaPetugasActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 102, 102));
        jLabel41.setText("Jenis Kendaraan");

        txt_JenisKndaraan.setForeground(new java.awt.Color(102, 102, 102));
        txt_JenisKndaraan.setText("Jenis Kendaraan");
        txt_JenisKndaraan.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txt_JenisKndaraan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_JenisKndaraanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_StandbyLayout = new javax.swing.GroupLayout(pn_Standby);
        pn_Standby.setLayout(pn_StandbyLayout);
        pn_StandbyLayout.setHorizontalGroup(
            pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_StandbyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_StandbyLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_StandbyLayout.createSequentialGroup()
                        .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_JenisKndaraan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_NamaPetugas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_NamaPasien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_Ket, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIdStndby, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pn_StandbyLayout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pn_StandbyLayout.createSequentialGroup()
                                .addComponent(btn_simpanstndby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(custom_JButtonRounded13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pn_StandbyLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtidter, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(cariterima, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))
                    .addGroup(pn_StandbyLayout.createSequentialGroup()
                        .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39)
                            .addComponent(jLabel36)
                            .addComponent(jLabel30))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pn_StandbyLayout.setVerticalGroup(
            pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_StandbyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_StandbyLayout.createSequentialGroup()
                        .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpanstndby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(custom_JButtonRounded13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel29)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdStndby, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pn_StandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cariterima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtidter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_NamaPasien, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_NamaPetugas, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_JenisKndaraan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_Ket, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(163, 163, 163))
        );

        pn_Main.add(pn_Standby, "card2");

        pn_ViewStandby.setBackground(new java.awt.Color(255, 255, 255));
        pn_ViewStandby.setPreferredSize(new java.awt.Dimension(1028, 658));

        tabelstandby.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelstandby.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelstandbyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelstandby);

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Data Daftar Standby Ambulan");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon AmbulanSB.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btn_firstpage1.setBackground(new java.awt.Color(255, 0, 0));
        btn_firstpage1.setForeground(new java.awt.Color(255, 255, 255));
        btn_firstpage1.setText("First Page");
        btn_firstpage1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_firstpage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstpage1ActionPerformed(evt);
            }
        });

        btn_before1.setBackground(new java.awt.Color(255, 0, 0));
        btn_before1.setForeground(new java.awt.Color(255, 255, 255));
        btn_before1.setText("<");
        btn_before1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_before1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_before1ActionPerformed(evt);
            }
        });

        btn_after1.setBackground(new java.awt.Color(255, 0, 0));
        btn_after1.setForeground(new java.awt.Color(255, 255, 255));
        btn_after1.setText(">");
        btn_after1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_after1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_after1ActionPerformed(evt);
            }
        });

        btn_lastpage1.setBackground(new java.awt.Color(255, 0, 0));
        btn_lastpage1.setForeground(new java.awt.Color(255, 255, 255));
        btn_lastpage1.setText("Last Page");
        btn_lastpage1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btn_lastpage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastpage1ActionPerformed(evt);
            }
        });

        cb_halaman1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cb_halaman1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "8", "16", "32", "64" }));
        cb_halaman1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jLabel12.setText("Halaman of halaman");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(343, 343, 343)
                .addComponent(btn_firstpage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_before1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_halaman1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_after1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_lastpage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(341, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(442, 442, 442))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_halaman1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_after1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_before1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_firstpage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lastpage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        custom_JButtonRounded8.setBackground(new java.awt.Color(0, 153, 255));
        custom_JButtonRounded8.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Add.png"))); // NOI18N
        custom_JButtonRounded8.setText("TAMBAH");
        custom_JButtonRounded8.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded8ActionPerformed(evt);
            }
        });

        custom_JButtonRounded9.setBackground(new java.awt.Color(255, 204, 0));
        custom_JButtonRounded9.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Delete.png"))); // NOI18N
        custom_JButtonRounded9.setText("HAPUS");
        custom_JButtonRounded9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded9ActionPerformed(evt);
            }
        });

        custom_JButtonRounded10.setBackground(new java.awt.Color(0, 204, 153));
        custom_JButtonRounded10.setForeground(new java.awt.Color(255, 255, 255));
        custom_JButtonRounded10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon Cancel.png"))); // NOI18N
        custom_JButtonRounded10.setText("BATAL");
        custom_JButtonRounded10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        custom_JButtonRounded10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custom_JButtonRounded10ActionPerformed(evt);
            }
        });

        txt_caristndby.setForeground(new java.awt.Color(102, 102, 102));
        txt_caristndby.setFont(new java.awt.Font("SansSerif", 2, 12)); // NOI18N
        txt_caristndby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_caristndbyActionPerformed(evt);
            }
        });
        txt_caristndby.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_caristndbyKeyPressed(evt);
            }
        });

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Icon DashboardSB.png"))); // NOI18N

        jLabel21.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Transaksi > Penerimaan");
        jLabel21.setPreferredSize(new java.awt.Dimension(176, 19));

        cari_stndby.setBackground(new java.awt.Color(255, 102, 102));
        cari_stndby.setForeground(new java.awt.Color(255, 255, 255));
        cari_stndby.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/searchputih.png"))); // NOI18N
        cari_stndby.setText("CARI");
        cari_stndby.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cari_stndby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari_stndbyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_ViewStandbyLayout = new javax.swing.GroupLayout(pn_ViewStandby);
        pn_ViewStandby.setLayout(pn_ViewStandbyLayout);
        pn_ViewStandbyLayout.setHorizontalGroup(
            pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ViewStandbyLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pn_ViewStandbyLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_ViewStandbyLayout.createSequentialGroup()
                        .addComponent(custom_JButtonRounded8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(custom_JButtonRounded9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(custom_JButtonRounded10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_caristndby, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cari_stndby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(20, 20, 20))
        );
        pn_ViewStandbyLayout.setVerticalGroup(
            pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ViewStandbyLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel11)))
                .addGap(18, 18, 18)
                .addGroup(pn_ViewStandbyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(custom_JButtonRounded8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(custom_JButtonRounded9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(custom_JButtonRounded10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_caristndby, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari_stndby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        pn_Main.add(pn_ViewStandby, "card2");

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

    private void custom_JButtonRounded1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded1ActionPerformed
        pn_Main.removeAll();
        pn_Main.add(pn_Add);
        pn_Main.repaint();
        pn_Main.revalidate();
    }//GEN-LAST:event_custom_JButtonRounded1ActionPerformed

    private void hpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hpsActionPerformed
          int ok = JOptionPane.showConfirmDialog(null,"hapus","konfirmasidialog",JOptionPane.YES_NO_OPTION);
    if (ok==0){
    String sql = "delete from penerimaan where id_terima ='"+txtIdTrima.getText()+"'";
    try{
    PreparedStatement stat = conn.prepareStatement(sql);
    stat.executeUpdate();
    JOptionPane.showMessageDialog(null, "data berhasil dihapus");
    kosong();
    txtIdTrima.requestFocus();
    }
    catch (SQLException e){
    JOptionPane.showMessageDialog(null, "data gagal dihapus"+e);
    }
    datatable();
    }
    }//GEN-LAST:event_hpsActionPerformed

    private void custom_JButtonRounded3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custom_JButtonRounded3ActionPerformed

    private void txCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txCariActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
     String jenis = null;
    if(radrujuk.isSelected()){
        jenis = "Rujukan";
    }else if(radkec.isSelected()){
        jenis = "Kecelakaan";
    }else if(radGd.isSelected()){
        
    }
    
        String sql = "insert into penerimaan values (?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdTrima.getText());
            stat.setString(2, txtNIK.getText());
            stat.setString(3, txtnmpasien.getText());
            stat.setString(4, txtJK.getText());
            stat.setString(5, txtIdPtgs.getText());
            stat.setString(6, txtnmptgs.getText());
            stat.setString(7, txtIdAmb.getText());
            stat.setString(8, txtjenis.getText());
            stat.setString(9, jenis);
            stat.setString(10, txtWktu.getText());
            stat.setString(11, txtKet.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
            txtNIK.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal disimpan"+e);
        }
        datatable();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        pn_Main.removeAll();
        pn_Main.add(pn_View);
        pn_Main.repaint();
        pn_Main.revalidate();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void txtIdTrimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdTrimaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdTrimaActionPerformed

    private void txtNIKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIKActionPerformed

    private void txtIdPtgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdPtgsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdPtgsActionPerformed

    private void txtIdAmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdAmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdAmbActionPerformed

    private void txtWktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWktuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtWktuActionPerformed

    private void radrujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radrujukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radrujukActionPerformed

    private void caripasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caripasienActionPerformed
PopUpPasien Prps = new PopUpPasien();
Prps.Rps = this;
Prps.setVisible(true);
Prps.setResizable(false);        
    }//GEN-LAST:event_caripasienActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        pn_Main.removeAll();
        pn_Main.add(pn_ViewStandby);
        pn_Main.repaint();
        pn_Main.revalidate(); 
    }//GEN-LAST:event_btnNextActionPerformed

    private void btn_simpanstndbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanstndbyActionPerformed
     
        String sql = "insert into standby values (?,?,?,?,?,?)";
        try{
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, txtIdStndby.getText());
            stat.setString(2, txtidter.getText());
            stat.setString(3, txt_NamaPasien.getText());
            stat.setString(4, txt_NamaPetugas.getText());
            stat.setString(5, txt_JenisKndaraan.getText());
            stat.setString(6, txt_Ket.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "data berhasil disimpan");
            kosong();
            txtidter.requestFocus();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "data gagal disimpan"+e);
        }
        datatable1();
    }//GEN-LAST:event_btn_simpanstndbyActionPerformed

    private void custom_JButtonRounded13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custom_JButtonRounded13ActionPerformed

    private void txtIdStndbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdStndbyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdStndbyActionPerformed

    private void txtidterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidterActionPerformed

    private void txt_KetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_KetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_KetActionPerformed

    private void btn_firstpage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstpage1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_firstpage1ActionPerformed

    private void btn_before1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_before1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_before1ActionPerformed

    private void btn_after1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_after1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_after1ActionPerformed

    private void btn_lastpage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastpage1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lastpage1ActionPerformed

    private void custom_JButtonRounded8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded8ActionPerformed
         pn_Main.removeAll();
        pn_Main.add(pn_Standby);
        pn_Main.repaint();
        pn_Main.revalidate();
    }//GEN-LAST:event_custom_JButtonRounded8ActionPerformed

    private void custom_JButtonRounded9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custom_JButtonRounded9ActionPerformed

    private void custom_JButtonRounded10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custom_JButtonRounded10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custom_JButtonRounded10ActionPerformed

    private void txt_caristndbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_caristndbyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_caristndbyActionPerformed

    private void butoncriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butoncriActionPerformed
        datatable();
    }//GEN-LAST:event_butoncriActionPerformed

    private void txtnmpasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmpasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmpasienActionPerformed

    private void caripetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caripetugasActionPerformed
    PopUpPetugasPosko Prpt = new PopUpPetugasPosko();
Prpt.Rpt = this;
Prpt.setVisible(true);
Prpt.setResizable(false);   
    }//GEN-LAST:event_caripetugasActionPerformed

    private void cariambulansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariambulansActionPerformed
        PopUpAmbulans Prpa = new PopUpAmbulans();
Prpa.Rpa = this;
Prpa.setVisible(true);
Prpa.setResizable(false);
    }//GEN-LAST:event_cariambulansActionPerformed

    private void txtJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJKActionPerformed

    private void txtnmptgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnmptgsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnmptgsActionPerformed

    private void txtKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKetActionPerformed

    private void txtjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjenisActionPerformed

    private void tabelterimaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelterimaMouseClicked
       int bar = tabelterima.getSelectedRow();
 String a = tabmode.getValueAt(bar, 0).toString();
 String b = tabmode.getValueAt(bar, 1).toString();
 String c = tabmode.getValueAt(bar, 2).toString();
 String d = tabmode.getValueAt(bar, 3).toString();
 String e = tabmode.getValueAt(bar, 4).toString();
 String f = tabmode.getValueAt(bar, 5).toString();
 String g = tabmode.getValueAt(bar, 6).toString();
 String h = tabmode.getValueAt(bar, 7).toString();
 String i = tabmode.getValueAt(bar, 8).toString();
 String j = tabmode.getValueAt(bar, 9).toString();
 String k = tabmode.getValueAt(bar, 10).toString();

 txtIdTrima.setText(a);
 txtNIK.setText(b);
 txtnmpasien.setText(c);
 txtJK.setText(d);
 txtIdPtgs.setText(e);
 txtnmptgs.setText(f);
 txtIdAmb.setText(g);
 txtjenis.setText(h);
 if ("Rujukan".equals(i)) {
 radrujuk.setSelected(true);
 } 
 else{
 radGd.setSelected(true);
 }
 txtWktu.setText(j);
 txtKet.setText(k);
 datatable();
    }//GEN-LAST:event_tabelterimaMouseClicked

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    datatable();
         }
    }//GEN-LAST:event_txCariKeyPressed

    private void cariterimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariterimaActionPerformed
    PopUpTerima Prpt = new PopUpTerima();
Prpt.Rpt = this;
Prpt.setVisible(true);
Prpt.setResizable(false);
    }//GEN-LAST:event_cariterimaActionPerformed

    private void txt_NamaPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NamaPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NamaPasienActionPerformed

    private void txt_NamaPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NamaPetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NamaPetugasActionPerformed

    private void txt_JenisKndaraanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_JenisKndaraanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_JenisKndaraanActionPerformed

    private void tabelstandbyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelstandbyMouseClicked
        int bar = tabelstandby.getSelectedRow();
 String a = tabmode.getValueAt(bar, 0).toString();
 String b = tabmode.getValueAt(bar, 1).toString();
 String c = tabmode.getValueAt(bar, 2).toString();
 String d = tabmode.getValueAt(bar, 3).toString();
 String e = tabmode.getValueAt(bar, 4).toString();
 String f = tabmode.getValueAt(bar, 5).toString();

 txtIdStndby.setText(a);
 txtidter.setText(b);
 txt_NamaPasien.setText(c);
 txt_NamaPetugas.setText(d);
 txt_JenisKndaraan.setText(e);
 txt_Ket.setText(f);
 datatable1();
    }//GEN-LAST:event_tabelstandbyMouseClicked

    private void cari_stndbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari_stndbyActionPerformed
        datatable1();
    }//GEN-LAST:event_cari_stndbyActionPerformed

    private void txt_caristndbyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_caristndbyKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    datatable1();
         }
    }//GEN-LAST:event_txt_caristndbyKeyPressed

    private void CetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CetakActionPerformed
        try{
            String path ="./src/Report/reportpenerimaan.jasper";
            HashMap parameter = new HashMap();
            JasperPrint print =
            JasperFillManager.fillReport(path, parameter, conn);
            JasperViewer.viewReport(print, false);
        }catch(JRException e){
            JOptionPane.showMessageDialog(null,"Dokumen Tidak Ada");
            System.out.println(e);
        }
    }//GEN-LAST:event_CetakActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Custom_JButtonRounded Cetak;
    private Custom.Custom_JButtonRounded btnBatal;
    private Custom.Custom_JButtonRounded btnNext;
    private Custom.Custom_JButtonRounded btnSimpan;
    private Custom.Custom_JButtonRounded btn_after;
    private Custom.Custom_JButtonRounded btn_after1;
    private Custom.Custom_JButtonRounded btn_before;
    private Custom.Custom_JButtonRounded btn_before1;
    private Custom.Custom_JButtonRounded btn_firstpage;
    private Custom.Custom_JButtonRounded btn_firstpage1;
    private Custom.Custom_JButtonRounded btn_lastpage;
    private Custom.Custom_JButtonRounded btn_lastpage1;
    private Custom.Custom_JButtonRounded btn_simpanstndby;
    private javax.swing.ButtonGroup btngrp_Jeniskelamin;
    private Custom.Custom_JButtonRounded butoncri;
    private Custom.Custom_JButtonRounded cari_stndby;
    private Custom.Custom_JButtonRounded cariambulans;
    private Custom.Custom_JButtonRounded caripasien;
    private Custom.Custom_JButtonRounded caripetugas;
    private Custom.Custom_JButtonRounded cariterima;
    private Custom.Custom_JComboBox cb_halaman;
    private Custom.Custom_JComboBox cb_halaman1;
    private Custom.Custom_JButtonRounded custom_JButtonRounded1;
    private Custom.Custom_JButtonRounded custom_JButtonRounded10;
    private Custom.Custom_JButtonRounded custom_JButtonRounded13;
    private Custom.Custom_JButtonRounded custom_JButtonRounded3;
    private Custom.Custom_JButtonRounded custom_JButtonRounded8;
    private Custom.Custom_JButtonRounded custom_JButtonRounded9;
    private com.raven.datechooser.DateChooser dateChooser1;
    private com.raven.datechooser.DateChooser dateChooser2;
    private Custom.Custom_JButtonRounded hps;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.ButtonGroup jeniskejadian;
    private javax.swing.JPanel pn_Add;
    private javax.swing.JPanel pn_Main;
    private javax.swing.JPanel pn_Standby;
    private javax.swing.JPanel pn_View;
    private javax.swing.JPanel pn_ViewStandby;
    private javax.swing.JRadioButton radGd;
    private javax.swing.JRadioButton radkec;
    private javax.swing.JRadioButton radrujuk;
    private Custom.Custom_JTable tabelstandby;
    private Custom.Custom_JTable tabelterima;
    private Custom.Custom_JTextFieldRounded txCari;
    private Custom.Custom_JTextFieldRounded txtIdAmb;
    private Custom.Custom_JTextFieldRounded txtIdPtgs;
    private Custom.Custom_JTextFieldRounded txtIdStndby;
    private Custom.Custom_JTextFieldRounded txtIdTrima;
    private Custom.Custom_JTextFieldRounded txtJK;
    private Custom.Custom_JTextFieldRounded txtKet;
    private Custom.Custom_JTextFieldRounded txtNIK;
    private Custom.Custom_JTextFieldRounded txtWktu;
    private Custom.Custom_JTextFieldRounded txt_JenisKndaraan;
    private Custom.Custom_JTextFieldRounded txt_Ket;
    private Custom.Custom_JTextFieldRounded txt_NamaPasien;
    private Custom.Custom_JTextFieldRounded txt_NamaPetugas;
    private Custom.Custom_JTextFieldRounded txt_caristndby;
    private Custom.Custom_JTextFieldRounded txtidter;
    private Custom.Custom_JTextFieldRounded txtjenis;
    private Custom.Custom_JTextFieldRounded txtnmpasien;
    private Custom.Custom_JTextFieldRounded txtnmptgs;
    // End of variables declaration//GEN-END:variables
}
