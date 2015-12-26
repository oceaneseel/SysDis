/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classutil;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Jerome
 */
public class MyMessageConsumer implements MessageListener{

    
    public MyMessageConsumer() {
 
        try {   
            Context c = new InitialContext();
        
            ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/topicBanqueFactory");
            Connection co = cf.createConnection();
            Session sess = co.createSession(false, Session.AUTO_ACKNOWLEDGE); 
            
            Destination destination = (Destination) c.lookup("jms/topicBanque");
            MessageConsumer mc = sess.createConsumer(destination);
            
            System.out.println("gneeeeeeee");
            
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
        System.out.println(message);
    }

    private Message createJMSMessageForjmsTopicBanque(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToTopicBanque2(Object messageData) throws JMSException, NamingException {
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/topicBanqueFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, s.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/topicBanque");
            MessageProducer mp = s.createProducer(destination);
            mp.send(createJMSMessageForjmsTopicBanque(s, messageData));
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
