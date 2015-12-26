/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classUtil;

import EntityClass.Credit;
import java.util.UUID;

/**
 *
 * @author Jerome
 */
public class DemandeCreditAttente{
    
    private String idEmployeSource;
    private int idTemp;
    private Credit cred;

    public DemandeCreditAttente(String id, int i, Credit c) {
        idEmployeSource = id;
        idTemp = i;
        cred = c;
    }
    
    public void setIdEmployeSource(String i)
    {
        idEmployeSource = i;
    }
    
    public String getIdEmployeSource()
    {
        return idEmployeSource;
    }
    
    public void setIdTemp(int i)
    {
        idTemp = i; 
    }
    
    public int getIdTemp()
    {
        return idTemp; 
    }
    
    public void setCredit(Credit c)
    {
        cred = c;
    }
    
    public Credit getCredit()
    {
        return cred;
    }
    
    @Override
    public String toString()
    {
        return "Montant : " + cred.getMontant() + " infos : " + cred.getInfosClient() + "  charges : " + cred.getChargeCredit();
    }
}
