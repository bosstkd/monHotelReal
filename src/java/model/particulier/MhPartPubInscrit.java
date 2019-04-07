/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.particulier;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import model.MhCltSCh;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_part_pub_inscrit", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhPartPubInscrit.findAll", query = "SELECT m FROM MhPartPubInscrit m")
    , @NamedQuery(name = "MhPartPubInscrit.findByNumInscription", query = "SELECT m FROM MhPartPubInscrit m WHERE m.numInscription = :numInscription")
    , @NamedQuery(name = "MhPartPubInscrit.findByDateInscription", query = "SELECT m FROM MhPartPubInscrit m WHERE m.dateInscription = :dateInscription")})
public class MhPartPubInscrit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "num_inscription", nullable = false, length = 100)
    private String numInscription;
    @Column(name = "date_inscription")
    @Temporal(TemporalType.DATE)
    private Date dateInscription;
    @JoinColumn(name = "code_pub_part", referencedColumnName = "code_pub_particulier")
    @ManyToOne
    private MhPubParticulier codePubPart;
    @JoinColumn(name = "npidins", referencedColumnName = "n_p_id")
    @ManyToOne
    private MhCltSCh npidins;
    @JoinColumn(name = "npidp", referencedColumnName = "n_p_id")
    @ManyToOne
    private MhCltSCh npidp;

    public MhPartPubInscrit() {
    }

    public MhPartPubInscrit(String numInscription) {
        this.numInscription = numInscription;
    }

    public String getNumInscription() {
        return numInscription;
    }

    public void setNumInscription(String numInscription) {
        this.numInscription = numInscription;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public MhPubParticulier getCodePubPart() {
        return codePubPart;
    }

    public void setCodePubPart(MhPubParticulier codePubPart) {
        this.codePubPart = codePubPart;
    }

    public MhCltSCh getNpidins() {
        return npidins;
    }

    public void setNpidins(MhCltSCh npidins) {
        this.npidins = npidins;
    }

    public MhCltSCh getNpidp() {
        return npidp;
    }

    public void setNpidp(MhCltSCh npidp) {
        this.npidp = npidp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numInscription != null ? numInscription.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhPartPubInscrit)) {
            return false;
        }
        MhPartPubInscrit other = (MhPartPubInscrit) object;
        if ((this.numInscription == null && other.numInscription != null) || (this.numInscription != null && !this.numInscription.equals(other.numInscription))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.particuler.MhPartPubInscrit[ numInscription=" + numInscription + " ]";
    }
    
}
