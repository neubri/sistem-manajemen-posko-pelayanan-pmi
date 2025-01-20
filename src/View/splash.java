package View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 *
 * @author LENOVO
 */
public class splash extends javax.swing.JFrame {
Timer timer;
    ActionListener action;
    splash sp;

    String[] images = {"/Image/pasienSplash.png", "/Image/petugasSplash.png", "/Image/ambulanSplash.png", "/Image/penerimaanSplash.png", "/Image/penangananSplash.png"};
    String[] loadingTexts = {"   Loading Pasien...", "   Loading Petugas...", "   Loading Ambulan...", "   Loading Penerimaan...", "   Loading Penanganan..."};
    int currentImageIndex = 0;
    int currentTextIndex = 0;
    int changesCount = 0; 
    
    /**
     *
     */
 
       
    public splash() {
        initComponents();
        initComponents();
        setLocationRelativeTo(this);
        act();
        timer = new Timer(900,action);
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        LPicture = new javax.swing.JLabel();
        LKalimat = new javax.swing.JLabel();
        customProgressBar1 = new Custom.CustomProgressBar();
        LPersen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        LPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/Login Posko.png"))); // NOI18N

        LKalimat.setFont(new java.awt.Font("SansSerif", 2, 11)); // NOI18N

        customProgressBar1.setForeground(new java.awt.Color(255, 0, 0));

        LPersen.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        customProgressBar1.add(LPersen);
        LPersen.setBounds(410, 0, 30, 30);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(customProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(393, 393, 393)
                        .addComponent(LKalimat)))
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(LPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(LPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(customProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LKalimat)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new splash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LKalimat;
    private javax.swing.JLabel LPersen;
    private javax.swing.JLabel LPicture;
    private Custom.CustomProgressBar customProgressBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    private void act() {
         action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (changesCount < 5) {
                    updateImage();
                    updateLoadingText();
                    updateProgressBar();
                    changesCount++;
                } else {
                    timer.stop();
                    dispose();
                    Form_Login n = new Form_Login();
                    n.setVisible(true);
                }
            }

             private void updateImage() {
             LPicture.setIcon(new ImageIcon(getClass().getResource(images[currentImageIndex])));
             currentImageIndex = (currentImageIndex + 1) % images.length; // Menggunakan modulo agar indeks kembali ke 0 setelah mencapai indeks maksimum
             }

             private void updateLoadingText() {
             LKalimat.setText(loadingTexts[currentTextIndex]);
        currentTextIndex = (currentTextIndex + 1) % loadingTexts.length; // Menggunakan modulo agar indeks kembali ke 0 setelah mencapai indeks maksimum
                 }

             private void updateProgressBar() {
                   int progress = (int) (((double) changesCount / 5) * 100);
                   customProgressBar1.setValue(progress);
                   LPersen.setText(progress + "%");

             }
        };
    }
}
