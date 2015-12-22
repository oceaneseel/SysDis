/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import javax.ejb.Stateless;

/**
 *
 * @author Jerome
 */
@Stateless
public class EJB1 implements EJB1Remote {

    @Override
    public boolean login() {
        return false;
    }

    
}
