/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Client;
import EntityClass.Compte;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class EJB2 implements EJB2Remote {
    @Resource SessionContext ctx;
    
    @PersistenceContext(unitName = "DBbanque")
    private EntityManager em;
    
    //Ressource nécessaires au topic
    @Resource(mappedName = "jms/javaee7/TopicConnectionFactory")
    private static TopicConnectionFactory topicConnectionFactory;
    
    @Resource(mappedName = "jms/javaee7/Topic")
    private static Topic topic;
    
    private TopicPublisher tp = null;
    
    @Override
    public Client login(String login, char[] password) {
         
        Client clFound = em.find(Client.class, login);
        
        if(ctx.isCallerInRole("client"))
            System.out.println("un client");
        
        //Pas de clients trouvés
        if(clFound == null)
            return null;
        
        //Test du mot de passe
        if(Arrays.equals(password,clFound.getPassword().toCharArray()))
            return clFound;
        
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List getComptes(Client c) {
        
        Query q = em.createNamedQuery("Compte.findByLogin").setParameter("login", c);
        
        return q.getResultList();
    }

    @Override
    public void sendMoney(int idSource, int idDest, double montant) {
        
        Compte source, dest;
        
        source = em.find(Compte.class, idSource);
        dest = em.find(Compte.class, idDest);
        
        if(dest == null)
            throw new EJBException("Le compte destinataire n'existe pas");
        
        if(source == null)
            throw new EJBException("Le compte source n'existe pas");
        
        if(source.getSolde() < montant)
            throw new EJBException("Pas assez d'argent sur le compte");

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);
        
    }
    
    private TopicPublisher getTopicPublisher()
    {
        if(tp == null)
        {
            try {
                TopicConnection connection = topicConnectionFactory.createTopicConnection();
                connection.start();
                TopicSession ts = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                
                tp = ts.createPublisher(topic);
            } catch (JMSException ex) {
                ex.printStackTrace();
            }  
        }
        
        return tp;
    }
 
}
