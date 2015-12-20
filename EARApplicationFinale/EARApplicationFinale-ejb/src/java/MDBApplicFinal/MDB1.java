/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MDBApplicFinal;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Jerome
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "clientId", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "subscriptionDurability", propertyValue = "durable"),
    @ActivationConfigProperty(propertyName = "subscriptionName", propertyValue = "jms/topicBanque"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class MDB1 implements MessageListener {
    
    public MDB1() {
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println("i'm alive");
    }
    
}
