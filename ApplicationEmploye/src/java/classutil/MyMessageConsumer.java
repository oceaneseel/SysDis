/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classutil;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.*;

/**
 *
 * @author Jerome
 */
public class MyMessageConsumer implements MessageListener{
    
    @Resource SessionContext ctx;
    
    @Resource(mappedName = "jms/topicBanque")
    private Topic topicBanque;
    
    public MyMessageConsumer() {
        
        if(topicBanque == null)
            System.out.println("la c'est vraiment la merde");
        
        topicBanque.getClass();
        
        /*try {                 
            
            if(dcf == null)
                System.out.println("WHY");
            
            System.out.println("test1");
            Connection c = dcf.createConnection();
            System.out.println("test2");
            Session sess = c.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("test3");
            MessageConsumer mc = sess.createConsumer(topicBanque);
            mc.setMessageListener(this);
            c.start();
            
        } catch (JMSException ex) {
            ex.printStackTrace();
            return;
        }*/
        
    }
    
    @Override
    public void onMessage(Message message) {
        System.out.println(message);
    }
}
