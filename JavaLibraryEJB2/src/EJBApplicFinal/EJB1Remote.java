/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Credit;
import java.util.UUID;
import javax.ejb.Remote;

/**
 *
 * @author Jerome
 */
@Remote
public interface EJB1Remote {

    boolean login();
    void creditRequest(Credit creditDemande, UUID idClient);
    
}
