/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDBApplicFinal;

import EntityClass.Credit;
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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "MDB2"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDB2 implements MessageListener {
    @PersistenceContext(unitName = "DBbanque")
    private EntityManager em;
    
    public MDB2() {
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
        
        //Si le message a été validé automatiquement. Enregistrement dans la BD
        if(elemMessage[0].equalsIgnoreCase("creditValide"))
        {
            Credit c = new Credit();
            
            c.setAccorde("OK");
            c.setChargeCredit(Double.parseDouble(elemMessage[3]));
            c.setDuree(Integer.parseInt(elemMessage[4]));
            c.setInfosClient(elemMessage[5]);
            c.setMontant(Double.parseDouble(elemMessage[6]));
            c.setSalaire(Double.parseDouble(elemMessage[7]));
            c.setTaux(Float.parseFloat(elemMessage[8]));
            
            //Enregistrement du credit sur la BD
            em.persist(c);
            
            return;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
