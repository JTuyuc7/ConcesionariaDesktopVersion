/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Utils.Queries;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jaime Tuyuc
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */

    //? Crear Cuenta
    Register register_user = new Register();
    Queries dbQuery = new Queries();

    int user_id = 0;

    public Login() throws SQLException {
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        login_account = new java.awt.Button();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        email_user = new java.awt.TextField();
        password_user = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        get_account = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 770));

        jPanel1.setBackground(new java.awt.Color(148, 82, 217));
        jPanel1.setPreferredSize(new java.awt.Dimension(820, 730));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inicio Sesion");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        login_account.setBackground(new java.awt.Color(132, 0, 255));
        login_account.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        login_account.setForeground(new java.awt.Color(255, 255, 255));
        login_account.setLabel("INICIAR SESION");
        login_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    login_accountActionPerformed(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Correo");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");

        email_user.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        password_user.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("No tienes una cuenta? ");

        get_account.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        get_account.setForeground(new java.awt.Color(204, 204, 255));
        get_account.setText("OBTEN UNA CUENTA");
        get_account.setActionCommand("CREA UNA CUENTA");
        get_account.setContentAreaFilled(false);
        get_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                get_accountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addComponent(get_account))
                    .addComponent(jLabel3)
                    .addComponent(password_user)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(email_user, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(login_account, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email_user, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password_user, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(login_account, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(get_account))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        get_account.getAccessibleContext().setAccessibleName("getAccount");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_accountActionPerformed(java.awt.event.ActionEvent evt) throws SQLException {//GEN-FIRST:event_login_accountActionPerformed
        ResultSet user_found_data = null;
        String user_email_found = "", user_password_found = "";
        String email_input = email_user.getText();
        char[] password_input = password_user.getPassword();

        if(email_input.isEmpty() || new String(password_input).isEmpty() ){
            JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Opps", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            user_found_data = dbQuery.findSingleAccount(email_input);
        }catch (SQLException e){
            System.out.println(e+"Oops, algo salio mal");
        }

        if(user_found_data != null){
            while (user_found_data.next()){
                user_id = user_found_data.getInt(1);
                user_email_found = user_found_data.getString(4);
                user_password_found = user_found_data.getString(5);
            }
        }

        if(user_email_found.length() > 1){
            if(!user_password_found.equals(new String(password_input))){
                JOptionPane.showMessageDialog(null, "Por favor revisa tu contraseña", "Algo salio mal", JOptionPane.WARNING_MESSAGE);
                return;
            }
                UI dashboard = new UI(user_id);
                dashboard.show();
                dispose();

        }else {
            JOptionPane.showMessageDialog(null, "Parece que no encontramos tu cuenta \n por favor revisa tu correo", "Algo salio mal", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_login_accountActionPerformed

    private void get_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_get_accountActionPerformed
        register_user.show();
        dispose();
    }//GEN-LAST:event_get_accountActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Login().setVisible(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextField email_user;
    private javax.swing.JButton get_account;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private java.awt.Button login_account;
    private javax.swing.JPasswordField password_user;
    // End of variables declaration//GEN-END:variables
}
