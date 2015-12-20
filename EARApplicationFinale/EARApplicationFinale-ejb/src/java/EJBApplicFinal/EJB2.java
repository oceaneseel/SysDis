/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJBApplicFinal;

import EntityClass.Client;
import EntityClass.Compte;
import Exception.notEnoughMoney;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
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
         
        System.out.println("tentative connexion : " + login);
        Client clFound = em.find(Client.class, login);
        
        //Pas de clients trouvés
        if(clFound == null)
            return null;
        
        //Test du mot de passe
        if(Arrays.equals(password,clFound.getPassword().toCharArray()))
        {
            System.out.println("ok : " + login);
            return clFound;
        }
        
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List getComptes(Client c) {
        
        Query q = em.createNamedQuery("Compte.findByLogin").setParameter("login", c);
        
        return q.getResultList();
    }

    @Override
    public void sendMoney(int idSource, int idDest, double montant) {
        
        Compte source, dest;
        
        source = em.find(Compte.class, idSource);
        dest = em.find(Compte.class, idDest);
        
        if(dest == null)
            throw new EJBException("Le compte destinataire n'existe pas");
        
        if(source == null)
            throw new EJBException("Le compte source n'existe pas");
        
        if(source.getSolde() < montant)
            throw new EJBException("Pas assez d'argent sur le compte");

        source.setSolde(source.getSolde() - montant);
        dest.setSolde(dest.getSolde() + montant);
        
        return;
    }
 
}
