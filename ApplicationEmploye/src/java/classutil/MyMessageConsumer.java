/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classutil;

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

    private JList listattente;
    
    public MyMessageConsumer(JList la) {
        
        listattente = la;
        
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
            
            case "creditValide" : 
                    valide(Integer.parseInt(elemMessage[2]));
                break;
                
        }
    }
    
    
    private void valide(int numDemande)
    {
        DefaultListModel list = (DefaultListModel) listattente.getModel();
        DemandeCreditAttente dca = null;
        
        for(int i = 0; i < list.getSize(); i++)
        {
            dca = (DemandeCreditAttente) list.get(i);
            
            if(dca.getIdTemp() == numDemande)
            {
                list.remove(i);
                break;
            }
        }
        
        String messageDialog = "Le prêt demandé par : " + dca.getCredit().getInfosClient() + "  pour un montant de  : ";
        messageDialog += dca.getCredit().getMontant() + " a été accordé";

        JOptionPane.showMessageDialog(null, messageDialog);
    }
}
