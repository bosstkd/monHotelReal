/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agc;

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
@Table(name = "mh_agc_publication_inscrit", catalog = "jlvljuzg_monhotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhAgcPublicationInscrit.findAll", query = "SELECT m FROM MhAgcPublicationInscrit m")
    , @NamedQuery(name = "MhAgcPublicationInscrit.findByNumInsc", query = "SELECT m FROM MhAgcPublicationInscrit m WHERE m.numInsc = :numInsc")
    , @NamedQuery(name = "MhAgcPublicationInscrit.findByDateInsc", query = "SELECT m FROM MhAgcPublicationInscrit m WHERE m.dateInsc = :dateInsc")})
public class MhAgcPublicationInscrit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "num_insc", nullable = false, length = 200)
    private String numInsc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_insc", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateInsc;
    @JoinColumn(name = "npid", referencedColumnName = "n_p_id", nullable = false)
    @ManyToOne(optional = false)
    private MhCltSCh npid;
    @JoinColumn(name = "code_a", referencedColumnName = "code_a", nullable = false)
    @ManyToOne(optional = false)
    private MhAgence codeA;
    @JoinColumn(name = "code_pub", referencedColumnName = "code_pub", nullable = false)
    @ManyToOne(optional = false)
    private MhAgcPublication codePub;

    public MhAgcPublicationInscrit() {
    }

    public MhAgcPublicationInscrit(String numInsc) {
        this.numInsc = numInsc;
    }

    public MhAgcPublicationInscrit(String numInsc, Date dateInsc) {
        this.numInsc = numInsc;
        this.dateInsc = dateInsc;
    }

    public String getNumInsc() {
        return numInsc;
    }

    public void setNumInsc(String numInsc) {
        this.numInsc = numInsc;
    }

    public Date getDateInsc() {
        return dateInsc;
    }

    public void setDateInsc(Date dateInsc) {
        this.dateInsc = dateInsc;
    }

    public MhCltSCh getNpid() {
        return npid;
    }

    public void setNpid(MhCltSCh npid) {
        this.npid = npid;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
    }

    public MhAgcPublication getCodePub() {
        return codePub;
    }

    public void setCodePub(MhAgcPublication codePub) {
        this.codePub = codePub;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numInsc != null ? numInsc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhAgcPublicationInscrit)) {
            return false;
        }
        MhAgcPublicationInscrit other = (MhAgcPublicationInscrit) object;
        if ((this.numInsc == null && other.numInsc != null) || (this.numInsc != null && !this.numInsc.equals(other.numInsc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhAgcPublicationInscrit[ numInsc=" + numInsc + " ]";
    }
    
}
