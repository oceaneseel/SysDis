/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EJBApplicFinal.EJB2Remote;
import EntityClass.Client;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.SwingUtilities;

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
        connexionButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        titreLabel.setText("Connexion gestion compte");

        connexionButton.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
        errorLabel.setText("jLabel1");

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
                        .addGap(239, 239, 239)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel)
                            .addComponent(connexionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(139, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGap(101, 101, 101)
                .addComponent(connexionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(errorLabel)
                .addContainerGap(234, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void login()
    {   
        errorLabel.setVisible(false);
        //Tentative de connexion via EJB2
        Client curClient;
        try{
            curClient = lookupEJB2Remote().login();
        }
        catch(Exception x)
        {
            errorLabel.setText("Connexion echouee");
            errorLabel.setVisible(true);
            x.printStackTrace();
            return;
        }
        if(curClient == null)
        {
            errorLabel.setText("Vous n'etes pas enregistre dans la base de donnees");
            errorLabel.setVisible(true);
            return;
        }

        //Recuperation de la fenetre parente
        frameClient fc = (frameClient)SwingUtilities.getWindowAncestor(this);
        
        fc.setCurUser(curClient);
        fc.changeCard("virement");

        //On rafraichis le panneau virement avec les comptes du client connecté
        fc.getVirementPanel().refresh();
        
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
