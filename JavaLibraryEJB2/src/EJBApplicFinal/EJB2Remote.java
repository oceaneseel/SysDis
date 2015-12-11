/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Client;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author John
 */
@Remote
public interface EJB2Remote {

    Client login(String login, char[] password);

    List getComptes(Client c);

    void sendMoney(int idSource, int idDest, double montant);
     
}
