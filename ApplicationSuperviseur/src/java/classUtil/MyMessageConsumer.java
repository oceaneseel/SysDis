/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classUtil;

import EntityClass.Credit;
import GUI.SupervisionPanel;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jerome
 */
public class MyMessageConsumer implements MessageListener{

    private SupervisionPanel supervision;
    
    public MyMessageConsumer(SupervisionPanel sp) {
        
        supervision = sp;
        
        try {   
            Context c = new InitialContext();
        
            ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/topicBanqueFactory");
            Connection co = cf.createConnection();
            Session sess = co.createSession(false, Session.AUTO_ACKNOWLEDGE); 
            
            Destination destination = (Destination) c.lookup("jms/topicBanque");
            MessageConsumer mc = sess.createConsumer(destination);

            mc.setMessageListener(this);
            co.start();
            
        } catch (JMSException ex) {
            ex.printStackTrace();
            return;
        } catch (NamingException ex) {
            ex.printStackTrace();
            return;
        }
    }
    
    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        
        String[] elemMessage;
        try {
            elemMessage = tm.getText().split("#");
        } catch (JMSException ex) {
            ex.printStackTrace();
            return;
        }
        
        switch(elemMessage[0]){
            
            case "sendMoney" : 
                    supervision.setMontant(Double.parseDouble(elemMessage[1]));
                break;
            
            case "creditValide" : 
                    String logValid = "Montant : " + elemMessage[6] + "  Demandeur : " + elemMessage[5];
                    supervision.logValide(logValid);
                break;
                
            case "avisSuperviseur" : 
                    //Credit mis en attente dans la jlist pour validation de superviseur
                    addDemande(elemMessage);
                break;
                
        }
    }
    
    
    private void addDemande(String[] demande)
    {
        Credit c = new Credit();
        
        c.setAccorde("KO");
        c.setChargeCredit(Double.parseDouble(demande[3]));
        c.setDuree(Integer.parseInt(demande[4]));
        c.setInfosClient(demande[5]);
        c.setMontant(Double.parseDouble(demande[6]));
        c.setSalaire(Double.parseDouble(demande[7]));
        c.setTaux(Float.parseFloat(demande[8]));
        c.setId(Integer.parseInt(demande[9]));
        
        DemandeCreditAttente dca = new DemandeCreditAttente(demande[1],Integer.parseInt(demande[2]) ,c);
        
        supervision.addDemande(dca);
    }
}
