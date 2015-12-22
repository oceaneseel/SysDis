/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author Jerome
 */
@Stateless
public class EJB1 implements EJB1Remote {
    
    @Resource SessionContext ctx;
    
    @RolesAllowed({"employe", "superviseur"})
    @Override
    public boolean login() {
        
        if(ctx.getCallerPrincipal().getName().equals("ANONYMOUS"))
            return false;
        
        return true;
    }

    @Override
    public boolean creditRequest(double montant, float taux, int duree, double salaire, double charge) {
        
        
        return true;
    }
    
    

}
