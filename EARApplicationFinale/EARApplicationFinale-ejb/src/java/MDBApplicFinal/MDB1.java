/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDBApplicFinal;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author Jerome
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDB1 implements MessageListener {
    
    public MDB1() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        TextMessage tm = (TextMessage) message;
        
        try {
            System.out.println(tm.getText());
        } catch (JMSException ex) {
            Logger.getLogger(MDB1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
