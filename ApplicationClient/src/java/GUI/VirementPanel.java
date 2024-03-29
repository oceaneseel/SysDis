/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EJBApplicFinal.EJB2Remote;
import EntityClass.Compte;
import java.awt.CardLayout;
import java.util.ArrayList;
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
public class VirementPanel extends javax.swing.JPanel {
    
    private ArrayList<Compte> listCompte;

    /**
     * Creates new form VirementPanel
     */
    public VirementPanel() {
        initComponents();
        errorRefreshLabel.setVisible(false);
    }
    
    
    public void refresh()
    {
        errorRefreshLabel.setVisible(false);
        
        frameClient parentFrame = (frameClient)SwingUtilities.getWindowAncestor(this);
        listCompte = new ArrayList(lookupEJB2Remote().getComptes(parentFrame.getUser()));
        
        if(listCompte == null)
        {
            errorRefreshLabel.setText("Aucun compte trouve");
            errorRefreshLabel.setVisible(true);
            return;
        }
        
        compteCombo.removeAllItems();
        
        for(Compte c : listCompte)
            compteCombo.addItem(c);
        
        //On rafraichis les sous panneau
        
        transfertPanel1.setTransfertPanel(listCompte, (Compte)compteCombo.getSelectedItem(), this);
        virementAutreComptePanel1.setPanel((Compte)compteCombo.getSelectedItem(), this);
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groupChoixDestinataire = new javax.swing.ButtonGroup();
        titreLabel = new javax.swing.JLabel();
        sourceLabel = new javax.swing.JLabel();
        compteCombo = new javax.swing.JComboBox();
        soldeLabel = new javax.swing.JLabel();
        errorRefreshLabel = new javax.swing.JLabel();
        transfertRadio = new javax.swing.JRadioButton();
        virementRadio = new javax.swing.JRadioButton();
        destinataireLabel = new javax.swing.JLabel();
        panelOperation = new javax.swing.JPanel();
        transfertPanel1 = new GUI.transfertPanel();
        virementAutreComptePanel1 = new GUI.virementAutreComptePanel();
        rafraichirButton = new javax.swing.JButton();

        titreLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titreLabel.setText("Virement");

        sourceLabel.setText("Compte source : ");

        compteCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compteComboActionPerformed(evt);
            }
        });

        soldeLabel.setText("Solde : ");

        errorRefreshLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorRefreshLabel.setText("jLabel1");

        groupChoixDestinataire.add(transfertRadio);
        transfertRadio.setSelected(true);
        transfertRadio.setText("Transfert sur un de vos compte");
        transfertRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transfertRadioActionPerformed(evt);
            }
        });

        groupChoixDestinataire.add(virementRadio);
        virementRadio.setText("Virement autre destinataire");
        virementRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                virementRadioActionPerformed(evt);
            }
        });

        destinataireLabel.setText("Destinataire : ");

        panelOperation.setLayout(new java.awt.CardLayout());
        panelOperation.add(transfertPanel1, "transfert");
        panelOperation.add(virementAutreComptePanel1, "virement");

        rafraichirButton.setText("Rafraichir");
        rafraichirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rafraichirButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destinataireLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(transfertRadio)
                        .addGap(72, 72, 72)
                        .addComponent(virementRadio))
                    .addComponent(panelOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sourceLabel)
                            .addComponent(errorRefreshLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(compteCombo, 0, 117, Short.MAX_VALUE)
                            .addComponent(rafraichirButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(46, 46, 46)
                        .addComponent(soldeLabel)))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(titreLabel)
                .addGap(30, 275, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titreLabel)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(errorRefreshLabel)
                    .addComponent(rafraichirButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sourceLabel)
                    .addComponent(compteCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(soldeLabel))
                .addGap(22, 22, 22)
                .addComponent(destinataireLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transfertRadio)
                    .addComponent(virementRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void compteComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compteComboActionPerformed
        Compte c = (Compte)compteCombo.getSelectedItem();
        
        if(c == null)
            return;
        
        soldeLabel.setText("Solde : " + String.format("%.2f", c.getSolde()) + " €");
        transfertPanel1.setTransfertPanel(listCompte, c, this);
    }//GEN-LAST:event_compteComboActionPerformed

    private void transfertRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transfertRadioActionPerformed
        
        if(!transfertRadio.isSelected())
            return;
        
        //Changement du panneau de transfert
        ((CardLayout)panelOperation.getLayout()).show(panelOperation, "transfert");
               
    }//GEN-LAST:event_transfertRadioActionPerformed

    private void virementRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_virementRadioActionPerformed
        if(!virementRadio.isSelected())
            return;
        
        //Changement du panneau de transfert
        ((CardLayout)panelOperation.getLayout()).show(panelOperation, "virement");
    }//GEN-LAST:event_virementRadioActionPerformed

    private void rafraichirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rafraichirButtonActionPerformed
        refresh();
    }//GEN-LAST:event_rafraichirButtonActionPerformed

    
    private EJB2Remote lookupEJB2Remote() {
        try {
            Context c = new InitialContext();
            return (EJB2Remote) c.lookup("java:comp/env/EJB2");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox compteCombo;
    private javax.swing.JLabel destinataireLabel;
    private javax.swing.JLabel errorRefreshLabel;
    private javax.swing.ButtonGroup groupChoixDestinataire;
    private javax.swing.JPanel panelOperation;
    private javax.swing.JButton rafraichirButton;
    private javax.swing.JLabel soldeLabel;
    private javax.swing.JLabel sourceLabel;
    private javax.swing.JLabel titreLabel;
    private GUI.transfertPanel transfertPanel1;
    private javax.swing.JRadioButton transfertRadio;
    private GUI.virementAutreComptePanel virementAutreComptePanel1;
    private javax.swing.JRadioButton virementRadio;
    // End of variables declaration//GEN-END:variables

}
