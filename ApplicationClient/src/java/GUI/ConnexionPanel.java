/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EJBApplicFinal.EJB2Remote;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author John
 */
public class ConnexionPanel extends javax.swing.JPanel {

    /**
     * Creates new form ConnexionPanel
     */
    public ConnexionPanel() {
        initComponents();
        errorLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titreLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        connexionButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        titreLabel.setText("Connexion gestion compte");

        loginLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loginLabel.setText("Login : ");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Mot de passe : ");

        connexionButton.setText("Connexion");
        connexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionButtonActionPerformed(evt);
            }
        });
        connexionButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                connexionButtonKeyPressed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(titreLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(loginLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(connexionButton)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(loginTextField)
                                        .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)))))))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginLabel)
                    .addComponent(loginTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(connexionButton)
                .addGap(46, 46, 46)
                .addComponent(errorLabel)
                .addContainerGap(208, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void login()
    {
        errorLabel.setVisible(false);
        
        if(loginTextField.getText().isEmpty())
        {
            errorLabel.setText("Vous devez pr�ciser un login");
            errorLabel.setVisible(true);
            return;
        }
        
        if(passwordField.getPassword().length == 0)
        {
            errorLabel.setText("Vous devez pr�ciser un mot de passe ");
            errorLabel.setVisible(true);
            return;
        }
        
        if(!lookupEJB2Remote().login(loginTextField.getText(), passwordField.getPassword()))
        {
            errorLabel.setText("Login ou mot de passe invalide");
            errorLabel.setVisible(true);
        }
    }
    
    
    //Clique sur le bouton login
    private void connexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionButtonActionPerformed
        login();
    }//GEN-LAST:event_connexionButtonActionPerformed
    
    
    //Si la touche enter est pr�ss�e lorsque le bouton est s�l�ectionn�
    private void connexionButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_connexionButtonKeyPressed
        
        //Si la touche press�e n'est pas enter
        if(evt.getKeyCode() != KeyEvent.VK_ENTER)
            return;
        
        login();
    }//GEN-LAST:event_connexionButtonKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connexionButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel titreLabel;
    // End of variables declaration//GEN-END:variables

    private EJB2Remote lookupEJB2Remote() {
        try {
            Context c = new InitialContext();
            return (EJB2Remote) c.lookup("java:comp/env/EJB2");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
