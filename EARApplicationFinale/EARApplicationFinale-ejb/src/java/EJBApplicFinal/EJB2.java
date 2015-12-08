/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import java.util.Arrays;
import javax.ejb.Stateless;

/**
 *
 * @author John
 */
@Stateless
public class EJB2 implements EJB2Remote {

    @Override
    public boolean login(String login, char[] password) {
        
        //TO DO accès BDD 
        
        if(login == "jerome" && Arrays.equals(password, new String("fink").toCharArray()))
            return true;
        
        return false;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
