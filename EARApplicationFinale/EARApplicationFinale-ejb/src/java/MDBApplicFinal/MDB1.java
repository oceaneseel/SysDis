/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDBApplicFinal;

import EntityClass.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jerome
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "MDB1"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDB1 implements MessageListener {
    
    @PersistenceContext(unitName = "DBbanque")
    private EntityManager em;
    
    public MDB1() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        TextMessage tm = (TextMessage) message;
        Log log = new Log();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
            
        String logMessage = "[" + dateFormat.format(date) + "]: ";  
        
        String[] elemMessage;
        try {
            elemMessage = tm.getText().split("#");
        } catch (JMSException ex) {
            log.setMessage(logMessage + " Erreur de reception du message sur le topic");
            //On enregistre le log dans la BD
            em.persist(log);
            ex.printStackTrace();
            
            return;
        }
        
        switch(elemMessage[0]){
            
            case "login" : 
                    logMessage += "Connexion de l'utilisateur : " + elemMessage[1];
                break;
            
            case "failSendMoney" :
                    logMessage += elemMessage[1];
                break;
                
            case "sendMoney" :
                    logMessage += "Transfert de " + elemMessage[1] + " du compte " + elemMessage[2] + "au compte" + elemMessage[3];
                break;
                
            case "loginEmp":
                    logMessage += "L'employe " + elemMessage[1] + " vient de se connecter";
                break;
                
            case "creditValide":
                    logMessage += "Une demande de credit a ete validee automatiquement montant : " + elemMessage[6] + "  Demandeur : " + elemMessage[5];
                break;
                
            case "loginSup":
                    logMessage += "Le superviseur " + elemMessage[1] + " vient de se connecter";
                break;
                
        }
        
        log.setMessage(logMessage);
        em.persist(log);
    }
    
}
