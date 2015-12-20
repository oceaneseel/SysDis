/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Client;
import EntityClass.Compte;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        
        //Pas de clients trouvés
        if(clFound == null)
            return null;
        
        //Test du mot de passe
        if(Arrays.equals(password,clFound.getPassword().toCharArray()))
        {
            //Login ok : on envoit un message sur le topic pour mettre dans les logs
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            
            String log = "[" + dateFormat.format(date) + "]: Connexion du client : " + login;  
            sendJMSMessageToTopicBanque(log);
            
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
            throw new EJBException("Le compte destinataire n'existe pas");
        
        if(source == null)
            throw new EJBException("Le compte source n'existe pas");
        
        if(source.getSolde() < montant)
            throw new EJBException("Pas assez d'argent sur le compte");

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);
        
    }

    private void sendJMSMessageToTopicBanque(String messageData) {
        context.createProducer().send(topicBanque, messageData);
    }

}
