/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classutil;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.*;

/**
 *
 * @author Jerome
 */
public class MyMessageConsumer implements MessageListener{

    @Resource(mappedName = "jms/topicBanque")
    private Topic topicBanque;
    
    @Resource(mappedName = "kms/javaee7/TopicConnectionFactory")
    private TopicConnectionFactory tcf;
    
    public MyMessageConsumer() {
        
        TopicConnection tc;
        try {
            
            tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);   
            MessageConsumer consumer = ts.createConsumer(topicBanque);
            consumer.setMessageListener(this);
            tc.start();
        } catch (JMSException ex) {
            ex.printStackTrace();
            return;
        }
        
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
    }
}
