/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import javax.ejb.Remote;

/**
 *
 * @author John
 */
@Remote
public interface EJB2Remote {

    boolean login(String login, char[] password);
    
    
}