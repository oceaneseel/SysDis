/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Client;
import java.util.Arrays;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author John
 */
@Stateless
public class EJB2 implements EJB2Remote {
    
    @PersistenceContext(unitName = "DBbanque")
    private EntityManager em;
    
    @Override
    public boolean login(String login, char[] password) {
        
        em.find(Client.class, login);
        
        if("jerome".equals(login) && Arrays.equals(password, new String("fink").toCharArray()))
            return true;
        
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
