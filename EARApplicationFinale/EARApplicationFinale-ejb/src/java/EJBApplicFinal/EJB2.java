/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Client;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author John
 */
@Stateless
public class EJB2 implements EJB2Remote {
    
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
}
