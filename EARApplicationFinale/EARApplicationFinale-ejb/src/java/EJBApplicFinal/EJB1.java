/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Credit;
import java.util.UUID;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jerome
 */
@Stateless
public class EJB1 implements EJB1Remote {
    @Resource(mappedName = "jms/topicBanque")
    private Topic topicBanque;
    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;
    @PersistenceContext(unitName = "DBbanque")
    private EntityManager em;
    
    @Resource SessionContext ctx;
    
    @RolesAllowed({"employe", "superviseur"})
    @Override
    public boolean login() {
        
        if(ctx.getCallerPrincipal().getName().equals("ANONYMOUS"))
            return false;
        
        sendJMSMessageToTopicBanque("loginEmp#"+ctx.getCallerPrincipal().getName());
        
        return true;
    }
    
    @RolesAllowed("employe")
    @Override
    public void creditRequest(Credit creditDemande, UUID idClient) {
        
        
        //Creation de la chaine à envoyer sur le topic :
        
        String messageTopic = "requestCredit";
        messageTopic += "#" + idClient;
        messageTopic += "#" + creditDemande.getChargeCredit();
        messageTopic += "#" + creditDemande.getDuree();
        messageTopic += "#" + creditDemande.getInfosClient();
        messageTopic += "#" + creditDemande.getMontant();
        messageTopic += "#" + creditDemande.getSalaire();
        messageTopic += "#" + creditDemande.getTaux();
        
        //Vérifications pour savoir si le crédit est accordé 
        if(creditDemande.getMontant() > 250000)
        {
            //TODO topic pour prevenir superviseur
            
            messageTopic += "#NON";
            sendJMSMessageToTopicBanque(messageTopic);
        }
        
        if((creditDemande.getSalaire()/100*40) < creditDemande.getChargeCredit())
        {
            //TO DO topic pour le superviseur
            messageTopic += "#NON";
            sendJMSMessageToTopicBanque(messageTopic);
        }
        
        messageTopic += "#OK";
    }

    public void persist(Object object) {
        em.persist(object);
    }

    private void sendJMSMessageToTopicBanque(String messageData) {
        context.createProducer().send(topicBanque, messageData);
    }
    
    

}
