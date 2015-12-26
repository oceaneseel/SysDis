/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classUtil;

import EntityClass.Credit;

/**
 *
 * @author Jerome
 */
public class DemandeCreditAttente{
    
    private int idTemp;
    private Credit cred;

    public DemandeCreditAttente(int i, Credit c) {
        idTemp = i;
        cred = c;
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
        return idTemp + " -- " + cred.getMontant() + " -- " + cred.getInfosClient();
    }
}
