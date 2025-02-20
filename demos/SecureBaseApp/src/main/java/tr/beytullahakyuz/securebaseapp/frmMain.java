package tr.beytullahakyuz.securebaseapp;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import tr.beytullahakyuz.securebase.SecureBase;

/**
 *
 * @author beytullahakyuz
 */
public class frmMain extends javax.swing.JFrame {

    public frmMain() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblResults = new javax.swing.JLabel();
        lblInputdata = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        editResults = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        editInput = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        cmbLang = new javax.swing.JComboBox<>();
        cmbEncoding = new javax.swing.JComboBox<>();
        lblSecretkey = new javax.swing.JLabel();
        editSecretkey = new javax.swing.JTextField();
        lblLanguage = new javax.swing.JLabel();
        lblEncoding = new javax.swing.JLabel();
        btnDecode = new javax.swing.JButton();
        btnEncode = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SecureBaseApp #java");
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        lblResults.setText("Results;");
        lblResults.setToolTipText("");

        lblInputdata.setText("Data;");

        editResults.setEditable(false);
        editResults.setColumns(20);
        editResults.setLineWrap(true);
        editResults.setRows(5);
        jScrollPane2.setViewportView(editResults);

        editInput.setColumns(20);
        editInput.setLineWrap(true);
        editInput.setRows(5);
        jScrollPane4.setViewportView(editInput);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblResults)
                    .addComponent(lblInputdata, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblInputdata)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResults)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 150, 520, 280);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        cmbLang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "Türkçe" }));
        cmbLang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLangActionPerformed(evt);
            }
        });

        cmbEncoding.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "unicode", "utf-8" }));
        cmbEncoding.setSelectedIndex(1);

        lblSecretkey.setText("Secret key:");

        lblLanguage.setText("Language:");

        lblEncoding.setText("Encoding:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSecretkey, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cmbLang, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(lblEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(editSecretkey))
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEncoding, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLanguage)
                    .addComponent(lblEncoding))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSecretkey)
                    .addComponent(editSecretkey, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3);
        jPanel3.setBounds(20, 10, 520, 130);

        btnDecode.setText("Decode");
        btnDecode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDecode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDecodeMouseClicked(evt);
            }
        });
        getContentPane().add(btnDecode);
        btnDecode.setBounds(410, 440, 130, 40);

        btnEncode.setText("Encode");
        btnEncode.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEncode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEncodeMouseClicked(evt);
            }
        });
        getContentPane().add(btnEncode);
        btnEncode.setBounds(260, 440, 130, 40);

        setSize(new java.awt.Dimension(571, 522));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        btnEncode.setBackground(new Color(0, 122, 255));
        btnEncode.setFocusPainted(false);
        btnDecode.setBackground(new Color(255, 159, 10));
        btnDecode.setFocusPainted(false);
    }//GEN-LAST:event_formWindowOpened

    private void cmbLangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLangActionPerformed
        if (cmbLang.getSelectedIndex()==0) {
            lblEncoding.setText("Encoding:");
            lblInputdata.setText("Data;");
            lblResults.setText("Results;");
            lblSecretkey.setText("Secret key:");
            lblLanguage.setText("Language:");
            btnEncode.setText("Encode");
            btnDecode.setText("Decode");
        } else {
            lblEncoding.setText("Kodlama:");
            lblInputdata.setText("Veri;");
            lblResults.setText("Sonuçlar;");
            lblSecretkey.setText("Gizli anahtar:");
            lblLanguage.setText("Dil:");
            btnEncode.setText("Kodlama");
            btnDecode.setText("Kod çözme");
        }
    }//GEN-LAST:event_cmbLangActionPerformed
    
    
    private void Processing(int pr) throws Exception{
        SecureBase.SBEncoding encoding;
        if (cmbEncoding.getSelectedIndex() == 0)
            encoding = SecureBase.SBEncoding.UNICODE;
        else
            encoding = SecureBase.SBEncoding.UTF8;
        String secretkey = editSecretkey.getText().trim();
        String input = editInput.getText();
        
        SecureBase sb = new SecureBase(encoding);
        sb.SetSecretKey(secretkey);
        if (pr == 1){
            editResults.setText(sb.encode(input));
        } else if (pr == 2) {
            editResults.setText(sb.decode(input));
        }
    }
    
    private void btnEncodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEncodeMouseClicked
        try {
            Processing(1);
        } catch (Exception ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEncodeMouseClicked

    private void btnDecodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDecodeMouseClicked
        try {
            Processing(2);
        } catch (Exception ex) {
            Logger.getLogger(frmMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDecodeMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel( new FlatMacDarkLaf());
            UIManager.put("Button.foreground", new Color(255, 255, 255)); 
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDecode;
    private javax.swing.JButton btnEncode;
    private javax.swing.JComboBox<String> cmbEncoding;
    private javax.swing.JComboBox<String> cmbLang;
    private javax.swing.JTextArea editInput;
    private javax.swing.JTextArea editResults;
    private javax.swing.JTextField editSecretkey;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JLabel lblEncoding;
    private javax.swing.JLabel lblInputdata;
    private javax.swing.JLabel lblLanguage;
    private javax.swing.JLabel lblResults;
    private javax.swing.JLabel lblSecretkey;
    // End of variables declaration//GEN-END:variables
}
