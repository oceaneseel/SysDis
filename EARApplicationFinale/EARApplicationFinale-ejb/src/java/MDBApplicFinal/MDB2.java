/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDBApplicFinal;

import EntityClass.Credit;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
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
    @Resource(mappedName = "jms/topicBanque")
    private Topic topicBanque;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
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
        
        //Si le message n'est pas valide automatiquement (attente de l'avis du superviseur)
        if(elemMessage[0].equalsIgnoreCase("demandeNonValidee"))
        {
            Credit c = new Credit();
            
            c.setAccorde("KO");
            c.setChargeCredit(Double.parseDouble(elemMessage[3]));
            c.setDuree(Integer.parseInt(elemMessage[4]));
            c.setInfosClient(elemMessage[5]);
            c.setMontant(Double.parseDouble(elemMessage[6]));
            c.setSalaire(Double.parseDouble(elemMessage[7]));
            c.setTaux(Float.parseFloat(elemMessage[8]));
            
            //Enregistrement du credit sur la BD
            em.persist(c);

            //Envois d'un message pour que le superviseur soit au courant de l'ID
            String messageSuperviseur = "avisSuperviseur#";
            messageSuperviseur += elemMessage[1] + "#" + elemMessage[2]+"#"+elemMessage[3]+"#"+elemMessage[4]+"#";
            messageSuperviseur += elemMessage[5] + "#" + elemMessage[6]+"#"+elemMessage[7]+"#"+elemMessage[8]+"#";
            messageSuperviseur += c.getId();
            
            sendJMSMessageToTopicBanque(messageSuperviseur);
            return;
        }
        
        
        //validé par un superviseur
        if(elemMessage[0].equalsIgnoreCase("validationSuperviseur"))
        {         
            Credit c = em.find(Credit.class, Integer.parseInt(elemMessage[3]));
            if(c == null)
                return;
            
            c.setAccorde("OK");
            return;
        }
        
        
        //refusé par un superviseur
        if(elemMessage[0].equalsIgnoreCase("refusSuperviseur"))
        {         
            Credit c = em.find(Credit.class, Integer.parseInt(elemMessage[3]));
            if(c == null)
                return;
            //on supprime
            em.remove(c);
            return;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

    private void sendJMSMessageToTopicBanque(String messageData) {
        context.createProducer().send(topicBanque, messageData);
    }
    
}
