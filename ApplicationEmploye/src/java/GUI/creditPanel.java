/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import EJBApplicFinal.EJB1Remote;
import EntityClass.Credit;
import classutil.DemandeCreditAttente;
import classutil.MyMessageConsumer;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jerome
 */
public class creditPanel extends javax.swing.JPanel {
    
    private int idDemande = 0;
    private UUID idClient;
    private MyMessageConsumer mc = new MyMessageConsumer();
    

    /**
     * Creates new form creditPanel
     */
    public creditPanel() {
        initComponents();
        idClient = UUID.randomUUID();
        
        errorLabel.setVisible(false);
        
        listAttente.setModel(new DefaultListModel());
        
    }
    
    //Tous les champs ont il bien été remplis ?
    private boolean checkEmptyField()
    {       
        if(nomTextField.getText().isEmpty())
        {
            displayError("Le nom du demandeur de crédit doit être indiqué");
            return false;
        }
        
        if(prenomTextField.getText().isEmpty())
        {
            displayError("Le prénom du demandeur de crédit doit être indiqué");
            return false;
        }
            
        if(adresseTextField.getText().isEmpty())
        {
            displayError("L'adresse du demandeur doit être indiquée");
            return false;
        }
        
        if(salaireTextField.getText().isEmpty())
        {
            displayError("Le salaire du demandeur doit être renseigné");
            return false;
        }
        
        if(chargeTextField.getText().isEmpty())
        {
            displayError("La charge de crédit du demandeur doit être renseignée");
            return false;
        }
        
        if(montantTextField.getText().isEmpty())
        {
            displayError("Le montant du crédit doit être renseigné");
            return false;
        }
        
        if(tauxTextField.getText().isEmpty())
        {
            displayError("Le taux annuel doit être indiqué");
            return false;
        }
        
        if(dureeTextField.getText().isEmpty())
        {
            displayError("La durée du crédit doit être renseignée");
            return false;
        }
        
        return true;
    }
    
    
    
    private void displayError(String errorMessage)
    {
        errorLabel.setText(errorMessage);
        errorLabel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listAttente = new javax.swing.JList();
        attenteTitre = new javax.swing.JLabel();
        deconnexionButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        informationSubTitle = new javax.swing.JLabel();
        nomLabel = new javax.swing.JLabel();
        nomTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        prenomTextField = new javax.swing.JTextField();
        adresseLAbel = new javax.swing.JLabel();
        adresseTextField = new javax.swing.JTextField();
        informationSubTitle1 = new javax.swing.JLabel();
        montantLabel = new javax.swing.JLabel();
        montantTextField = new javax.swing.JTextField();
        tauxLabel = new javax.swing.JLabel();
        tauxTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dureeLabel = new javax.swing.JLabel();
        dureeTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        salaireTextField = new javax.swing.JTextField();
        chargeLabel = new javax.swing.JLabel();
        chargeTextField = new javax.swing.JTextField();
        demandeButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        jScrollPane1.setViewportView(listAttente);

        attenteTitre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        attenteTitre.setText("Demandes en attente");

        deconnexionButton.setText("Déconnexion");
        deconnexionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deconnexionButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Nouvelle demande : ");

        informationSubTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        informationSubTitle.setText("Informations demandeur : ");

        nomLabel.setText("Nom : ");

        jLabel2.setText("Prénom : ");

        adresseLAbel.setText("Adresse : ");

        informationSubTitle1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        informationSubTitle1.setText("Informations sur le crédit : ");

        montantLabel.setText("Montant : ");

        tauxLabel.setText("Taux : ");

        jLabel3.setText("%");

        dureeLabel.setText("Durée :");

        jLabel4.setText("an(s)");

        jLabel5.setText("Salaire : ");

        chargeLabel.setText("Charge de crédits : ");

        demandeButton.setText("Envoyer la demande ");
        demandeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demandeButtonActionPerformed(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 0, 0));
        errorLabel.setText("jLabel6");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(informationSubTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nomLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prenomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(adresseLAbel)
                            .addComponent(adresseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(informationSubTitle1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(montantLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(montantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tauxLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tauxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(dureeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dureeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salaireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(chargeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chargeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(errorLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(demandeButton)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(attenteTitre)
                                .addGap(28, 28, 28))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deconnexionButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deconnexionButton)
                .addGap(7, 35, Short.MAX_VALUE)
                .addComponent(attenteTitre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(informationSubTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomLabel)
                    .addComponent(nomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(prenomTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adresseLAbel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adresseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chargeLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(salaireTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chargeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(informationSubTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montantLabel)
                    .addComponent(montantTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tauxLabel)
                    .addComponent(tauxTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(dureeLabel)
                    .addComponent(dureeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(62, 62, 62)
                .addComponent(demandeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deconnexionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionButtonActionPerformed
        //Changement de fenêtre
        frameEmploye fe = (frameEmploye)SwingUtilities.getWindowAncestor(this);
        fe.changeCard("connexion");
    }//GEN-LAST:event_deconnexionButtonActionPerformed
    
    
    /*Création d'un objet credit et envois à l'EJB pour le traitement*/
    private void demandeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demandeButtonActionPerformed
        
        errorLabel.setVisible(false);
        
        //vérif si tous les chamsp sont remplis
        if(!checkEmptyField())
            return;
        
        
        //transformation du texte en nombre
        double montant, salaire, charge;
        float taux;
        int duree;
        
        try
        {
            salaire = Double.parseDouble(salaireTextField.getText());
            
            if(salaire < 0)
            {
                displayError("Le salaire doit être un nombre positif");
                return;
            }
        }
        catch(NumberFormatException nb)
        {
            displayError("Le salaire doit être un nombre positif");
            return;
        }
        
        
        try
        {
            charge = Double.parseDouble(chargeTextField.getText());
            
            if(charge < 0)
            {
                displayError("Le montant des charges mensuelles doit être un nombre positif");
                return;
            }
        }
        catch(NumberFormatException nbe)
        {
            displayError("Le montant des charges mensuelles doit être un nombre positif");
            return;
        }
        
        
        try
        {
            montant = Double.parseDouble(montantTextField.getText());
            
            if(montant < 0)
            {
                displayError("Le montant du crédit doit être un nombre positif");
                return;
            }
            
        }
        catch(NumberFormatException nbe)
        {
            displayError("Le montant du crédit doit être un nombre positif");
            return;
        }
        
        try
        {
            taux = Float.parseFloat(tauxTextField.getText());
            
            if(taux < 0 || taux > 100)
            {
                displayError("Le taux annuel doit être un nombre compris entre 0 et 100");
                return;
            }
        }
        catch(NumberFormatException nbe)
        {
            displayError("Le taux annuel doit être un nombre compris entre 0 et 100");
            return;
        }
        
        try
        {
            duree =  Integer.parseInt(dureeTextField.getText());
            
            if(duree < 0  || duree > 100)
            {
                displayError("La durée du crédit doit être comprise entre 0 et 100 ans");
                return;
            }
        }
        catch(NumberFormatException nbe)
        {
            displayError("La durée du crédit doit être comprise entre 0 et 100 ans");
            return;
        }
        
        
        //Maintenant les choses sérieuses 
        
        //On envoie l'objet à l'EJB
        Credit creditDemande =  new Credit();
        
        creditDemande.setChargeCredit(charge);
        creditDemande.setDuree(duree);
        creditDemande.setInfosClient(prenomTextField.getText()+"  --  " + nomTextField.getText()+"  --  " + adresseTextField.getText());
        creditDemande.setMontant(montant);
        creditDemande.setSalaire(salaire);
        creditDemande.setTaux(taux);
        
        lookupEJB1Remote().creditRequest(creditDemande, idClient, idDemande);
        
        
        //On va ajouter dans la liste les requetes en attente
        
        DemandeCreditAttente dca = new DemandeCreditAttente(idDemande, creditDemande);              
        
        ((DefaultListModel)listAttente.getModel()).addElement(dca);
       
        idDemande++;
    }//GEN-LAST:event_demandeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel adresseLAbel;
    private javax.swing.JTextField adresseTextField;
    private javax.swing.JLabel attenteTitre;
    private javax.swing.JLabel chargeLabel;
    private javax.swing.JTextField chargeTextField;
    private javax.swing.JButton deconnexionButton;
    private javax.swing.JButton demandeButton;
    private javax.swing.JLabel dureeLabel;
    private javax.swing.JTextField dureeTextField;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel informationSubTitle;
    private javax.swing.JLabel informationSubTitle1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listAttente;
    private javax.swing.JLabel montantLabel;
    private javax.swing.JTextField montantTextField;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JTextField nomTextField;
    private javax.swing.JTextField prenomTextField;
    private javax.swing.JTextField salaireTextField;
    private javax.swing.JLabel tauxLabel;
    private javax.swing.JTextField tauxTextField;
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
