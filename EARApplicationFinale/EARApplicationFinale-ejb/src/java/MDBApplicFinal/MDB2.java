/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDBApplicFinal;

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
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "MDB2"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "Durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDB2 implements MessageListener {
    
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
        
        if(elemMessage[0].equalsIgnoreCase("requestCredit"))
        {
            //todo do struff
            
            System.out.println("MDB2");
        }
    }
    
}
