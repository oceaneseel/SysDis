/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClass;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author John
 */
@Entity
@Table(name = "CREDIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Credit.findAll", query = "SELECT c FROM Credit c"),
    @NamedQuery(name = "Credit.findById", query = "SELECT c FROM Credit c WHERE c.id = :id"),
    @NamedQuery(name = "Credit.findByInfosClient", query = "SELECT c FROM Credit c WHERE c.infosClient = :infosClient"),
    @NamedQuery(name = "Credit.findByMontant", query = "SELECT c FROM Credit c WHERE c.montant = :montant"),
    @NamedQuery(name = "Credit.findByTaux", query = "SELECT c FROM Credit c WHERE c.taux = :taux"),
    @NamedQuery(name = "Credit.findByDuree", query = "SELECT c FROM Credit c WHERE c.duree = :duree"),
    @NamedQuery(name = "Credit.findBySalaire", query = "SELECT c FROM Credit c WHERE c.salaire = :salaire"),
    @NamedQuery(name = "Credit.findByChargeCredit", query = "SELECT c FROM Credit c WHERE c.chargeCredit = :chargeCredit"),
    @NamedQuery(name = "Credit.findByAccorde", query = "SELECT c FROM Credit c WHERE c.accorde = :accorde")})
public class Credit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 300)
    @Column(name = "INFOS_CLIENT")
    private String infosClient;
    @Column(name = "MONTANT")
    private Integer montant;
    @Column(name = "TAUX")
    private Integer taux;
    @Column(name = "DUREE")
    private Integer duree;
    @Column(name = "SALAIRE")
    private Integer salaire;
    @Column(name = "CHARGE_CREDIT")
    private Integer chargeCredit;
    @Size(max = 4)
    @Column(name = "ACCORDE")
    private String accorde;

    public Credit() {
    }

    public Credit(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfosClient() {
        return infosClient;
    }

    public void setInfosClient(String infosClient) {
        this.infosClient = infosClient;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Integer getTaux() {
        return taux;
    }

    public void setTaux(Integer taux) {
        this.taux = taux;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }

    public Integer getChargeCredit() {
        return chargeCredit;
    }

    public void setChargeCredit(Integer chargeCredit) {
        this.chargeCredit = chargeCredit;
    }

    public String getAccorde() {
        return accorde;
    }

    public void setAccorde(String accorde) {
        this.accorde = accorde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Credit)) {
            return false;
        }
        Credit other = (Credit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.Credit[ id=" + id + " ]";
    }
    
}
