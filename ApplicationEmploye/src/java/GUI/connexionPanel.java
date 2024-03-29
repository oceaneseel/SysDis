/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EJBApplicFinal.EJB1Remote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jerome
 */
public class connexionPanel extends javax.swing.JPanel {

    
    /**
     * Creates new form connexionPanel
     */
    public connexionPanel() {
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
        titreLabel.setText("Application credit");
        titreLabel.setToolTipText("");

        connexionButton.setText("Connexion");
        connexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionButtonActionPerformed(evt);
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
                        .addGap(196, 196, 196)
                        .addComponent(titreLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errorLabel)
                            .addComponent(connexionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGap(113, 113, 113)
                .addComponent(connexionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(errorLabel)
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void connexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionButtonActionPerformed
       
        errorLabel.setVisible(false);
        
        boolean resultConnexion = true;
        try
        {
            resultConnexion = lookupEJB1Remote().login();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            errorLabel.setText("Connexion echouee");
            errorLabel.setVisible(true);
            return;
        }
        
        if(!resultConnexion)
        {
            errorLabel.setText("Connexion échouée");
            errorLabel.setVisible(true);
            
            return;
        }
        
        //Changement de fenêtre
        frameEmploye fe = (frameEmploye)SwingUtilities.getWindowAncestor(this);
        fe.changeCard("credit");

    }//GEN-LAST:event_connexionButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connexionButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel titreLabel;
    // End of variables declaration//GEN-END:variables

    private EJB1Remote lookupEJB1Remote() {
        try {
            Context c = new InitialContext();
            return (EJB1Remote) c.lookup("java:comp/env/EJB1");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
