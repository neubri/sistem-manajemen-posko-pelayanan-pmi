/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dimas setyanto
 */
public class PopUpPetugasPosko extends javax.swing.JFrame {
 Connection conn = controller.koneksi.getKoneksi();
ResultSet rs = null;
PreparedStatement pst = null; 
int xx , xy;
private DefaultTableModel tabmode;
public Menu_Penerimaan Rpt = null;
    /**
     * Creates new form PopUpPasien
     */
    public PopUpPetugasPosko() {
        initComponents();
        datatable();
    }

     protected void datatable(){
 Object[] Baris ={"Id Petugas","Nama Petugas"};
 tabmode = new DefaultTableModel(null, Baris);
 String cariitem=txCari.getText();
 
 try{
 String sql = "SELECT*FROM petugas where id_petugas like '%"+cariitem+"%' or nama_petugas like'%"+cariitem+"%' order by id_petugas asc";
 java.sql.Statement stat = conn.createStatement();
 ResultSet hasil = stat.executeQuery(sql);
 while (hasil.next()){
 tabmode.addRow(new Object[]{
 hasil.getString(1),
 hasil.getString(2),
 });
 }
 tblpopuppetugas.setModel(tabmode);
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

        custom_JPanelGradient2 = new Custom.Custom_JPanelGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpopuppetugas = new Custom.Custom_JTable();
        txCari = new Custom.Custom_JTextFieldRounded();
        btn_Cari = new Custom.Custom_JButtonRounded();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        custom_JPanelGradient2.setColorEnd(new java.awt.Color(255, 209, 209));
        custom_JPanelGradient2.setColorStart(new java.awt.Color(255, 0, 0));

        tblpopuppetugas.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpopuppetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpopuppetugasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpopuppetugas);

        txCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCariKeyPressed(evt);
            }
        });

        btn_Cari.setBackground(new java.awt.Color(255, 0, 0));
        btn_Cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/btncari.png"))); // NOI18N
        btn_Cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CariActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("POP UP PETUGAS");

        javax.swing.GroupLayout custom_JPanelGradient2Layout = new javax.swing.GroupLayout(custom_JPanelGradient2);
        custom_JPanelGradient2.setLayout(custom_JPanelGradient2Layout);
        custom_JPanelGradient2Layout.setHorizontalGroup(
            custom_JPanelGradient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custom_JPanelGradient2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(custom_JPanelGradient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(custom_JPanelGradient2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(custom_JPanelGradient2Layout.createSequentialGroup()
                        .addComponent(txCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        custom_JPanelGradient2Layout.setVerticalGroup(
            custom_JPanelGradient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custom_JPanelGradient2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(custom_JPanelGradient2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txCari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        getContentPane().add(custom_JPanelGradient2, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, -5, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CariActionPerformed
        datatable();
    }//GEN-LAST:event_btn_CariActionPerformed

    private void txCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCariKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
      datatable();
    }//GEN-LAST:event_txCariKeyPressed
    }
    private void tblpopuppetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpopuppetugasMouseClicked
    int tabelpetugas1 = tblpopuppetugas.getSelectedRow();
Rpt.IdPtgs = tblpopuppetugas.getValueAt(tabelpetugas1, 0).toString();
Rpt.nmptgs = tblpopuppetugas.getValueAt(tabelpetugas1, 1).toString();
Rpt.itemTerpilihPetugas();
this.dispose();
    }//GEN-LAST:event_tblpopuppetugasMouseClicked
    
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
            java.util.logging.Logger.getLogger(PopUpPetugasPosko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PopUpPetugasPosko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PopUpPetugasPosko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PopUpPetugasPosko.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PopUpPetugasPosko().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Custom.Custom_JButtonRounded btn_Cari;
    private Custom.Custom_JPanelGradient custom_JPanelGradient2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private Custom.Custom_JTable tblpopuppetugas;
    private Custom.Custom_JTextFieldRounded txCari;
    // End of variables declaration//GEN-END:variables
}
