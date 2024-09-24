package View.Login;

import Controller.LoginController;
import Model.UserModel;
import View.Home.Home;
import View.Resources.CustomFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import Controller.RemoteUserInterface;

/**
 *
 * @author benjamin
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        loadFonts();
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
        passwordLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        usernameTF = new javax.swing.JTextField();
        passwordPF = new javax.swing.JPasswordField();
        loginBTN = new javax.swing.JButton();
        logoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(369, 575));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        passwordLabel.setBackground(new java.awt.Color(62, 62, 62));
        passwordLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(62, 62, 62));
        passwordLabel.setText("Password:");

        userNameLabel.setBackground(new java.awt.Color(62, 62, 62));
        userNameLabel.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        userNameLabel.setForeground(new java.awt.Color(62, 62, 62));
        userNameLabel.setText("Username:");

        usernameTF.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        usernameTF.setPreferredSize(new java.awt.Dimension(258, 45));

        passwordPF.setFont(new java.awt.Font("Liberation Sans", 0, 18)); // NOI18N
        passwordPF.setPreferredSize(new java.awt.Dimension(258, 45));

        loginBTN.setBackground(new java.awt.Color(34, 151, 153));
        loginBTN.setFont(new java.awt.Font("Liberation Sans", 1, 18)); // NOI18N
        loginBTN.setForeground(new java.awt.Color(255, 255, 255));
        loginBTN.setLabel("Login");
        loginBTN.setPreferredSize(new java.awt.Dimension(258, 55));
        loginBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBTNMouseClicked(evt);
            }
        });

        logoLabel.setFont(new java.awt.Font("Liberation Sans", 1, 36)); // NOI18N
        logoLabel.setForeground(new java.awt.Color(72, 207, 203));
        logoLabel.setText("LOGO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameLabel)
                    .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoLabel)
                .addGap(170, 170, 170))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(logoLabel)
                .addGap(63, 63, 63)
                .addComponent(userNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBTNMouseClicked
        /*String username = usernameTF.getText().trim();
        String password = new String(passwordPF.getPassword());
        login(username, password);*/
        
        //Test code
        UserModel user = new UserModel("1", "Ben", "Ben123", "Benjamin", "0718375748", "Admin");
        user.setEmail("pramodyabenjamin@gmail.com");
        new Home(user).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginBTNMouseClicked

    private void login(String username, String password) {
        if(!username.isEmpty() && !password.isEmpty()) {
            
            LoginController.login(username, password);
            
        } else {
            JOptionPane.showMessageDialog(null, "Please enter both username & password!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        setLookAndFeel();
        FlatMacLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    private static void setLookAndFeel() {
        UIManager.put( "Button.arc", 20 );
        UIManager.put( "Component.arc", 10 );
        UIManager.put( "ProgressBar.arc", 20 );
        UIManager.put( "TextComponent.arc", 10 );
        UIManager.put( "TextComponent.accent", new Color(34, 151, 153));
    }
    
    private void loadFonts() {
        userNameLabel.setFont(CustomFont.formLabelFont);
        passwordLabel.setFont(CustomFont.formLabelFont);
        loginBTN.setFont(CustomFont.formLabelFont);
        usernameTF.setFont(CustomFont.formTextFieldFont);
        passwordPF.setFont(CustomFont.formTextFieldFont);
        logoLabel.setFont(CustomFont.panelHeadingFont);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBTN;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordPF;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
