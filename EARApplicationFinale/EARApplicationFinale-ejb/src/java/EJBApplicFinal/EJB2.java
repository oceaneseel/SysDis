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
        
        System.out.println(login +" ----- "+ password.toString());
        
        Client clFound = em.find(Client.class, login);
        
        //Pas de clients trouvés
        if(clFound == null)
        {
            System.err.println("rien");
            return false;
        }
        
        System.out.println(clFound.getLogin() +" ----- "+ clFound.getPassword());
        
        //Test du mot de passe
        if(Arrays.equals(password,clFound.getPassword().toCharArray()))
            return true;
        
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
