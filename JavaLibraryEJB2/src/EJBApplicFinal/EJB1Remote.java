/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import javax.ejb.Remote;

/**
 *
 * @author Jerome
 */
@Remote
public interface EJB1Remote {

    boolean login();
    boolean creditRequest(double montant, float taux, int duree, double salaire, double charge);
    
}
