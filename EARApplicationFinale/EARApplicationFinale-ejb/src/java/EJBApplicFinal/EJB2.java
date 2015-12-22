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
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class EJB2 implements EJB2Remote {
    @Resource(mappedName = "jms/topicBanque")
    private Topic topicBanque;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
 
    @Resource SessionContext ctx;
    
    @PersistenceContext(unitName = "DBbanque")
    private EntityManager em;
    
    
    @Override
    public Client login(String login, char[] password) {         
        Client clFound = em.find(Client.class, login);
        
        //Remplissage de la BD//
        /*
        Client c1, c2;
        Compte co1, co2, co3, co4;
        
        c1 =  new Client("je");
        c1.setNom("Fink");
        c1.setPassword("je");
        c1.setPrenom("Jérôme");
        c1.setType("CLIENT");
        em.persist(c1);
        
        c2 = new Client("oce");
        c2.setNom("Seel");
        c2.setPassword("oce");
        c2.setPrenom("Oceane");
        em.persist(c2);
        
        co1 = new Compte();
        co1.setLoginClient(c1);
        co1.setSolde(20000000.00);
        em.persist(co1);
        
        co2 = new Compte();
        co2.setLoginClient(c1);
        co2.setSolde(500.00);
        em.persist(co2);
        
        co3 = new Compte();
        co3.setLoginClient(c2);
        co3.setSolde(20.00);
        em.persist(co3);
        
        co4 = new Compte();
        co4.setLoginClient(c2);
        co4.setSolde(5000.00);
        em.persist(co4);*/

        //Pas de clients trouvés
        if(clFound == null)
            return null;
        
        //Test du mot de passe
        if(Arrays.equals(password,clFound.getPassword().toCharArray()))
        {
            //Login ok : on envoit un message sur le topic pour mettre dans les logs
            sendJMSMessageToTopicBanque("login#"+login);     
            return clFound;
        }
        
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
        {
            sendJMSMessageToTopicBanque("failSendMoney#Tentative de transfert à un compte inexistant");
            throw new EJBException("Le compte destinataire n'existe pas");
        }
        
        if(source == null)
        {
            sendJMSMessageToTopicBanque("failSendMoney#Tentative d'envois d'argent à partir d'un compte inconnus");
            throw new EJBException("Le compte source n'existe pas");
        }
        
        if(source.getSolde() < montant)
        {
            sendJMSMessageToTopicBanque("failSendMoney#Pas assez d'argent sur le compte " + idSource + "pour effectuer le transfert");
            throw new EJBException("Pas assez d'argent sur le compte");
        }

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);
        
        sendJMSMessageToTopicBanque("sendMoney#" + montant + "#" + idSource + "#" + idDest);
    }

    private void sendJMSMessageToTopicBanque(String messageData) {
        context.createProducer().send(topicBanque, messageData);
    }

}
