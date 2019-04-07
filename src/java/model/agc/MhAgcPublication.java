/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agc;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_agc_publication", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhAgcPublication.findAll", query = "SELECT m FROM MhAgcPublication m")
    , @NamedQuery(name = "MhAgcPublication.findByCodePub", query = "SELECT m FROM MhAgcPublication m WHERE m.codePub = :codePub")
    , @NamedQuery(name = "MhAgcPublication.findByTitre", query = "SELECT m FROM MhAgcPublication m WHERE m.titre = :titre")
    , @NamedQuery(name = "MhAgcPublication.findByDateD", query = "SELECT m FROM MhAgcPublication m WHERE m.dateD = :dateD")
    , @NamedQuery(name = "MhAgcPublication.findByDateF", query = "SELECT m FROM MhAgcPublication m WHERE m.dateF = :dateF")
    , @NamedQuery(name = "MhAgcPublication.findByDatePub", query = "SELECT m FROM MhAgcPublication m WHERE m.datePub = :datePub")
    , @NamedQuery(name = "MhAgcPublication.findByValide", query = "SELECT m FROM MhAgcPublication m WHERE m.valide = :valide")})
public class MhAgcPublication implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_pub", nullable = false, length = 200)
    private String codePub;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titre", nullable = false, length = 100)
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "detail", nullable = false, length = 65535)
    private String detail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_d", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_f", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateF;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_pub", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePub;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valide", nullable = false)
    private boolean valide;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codePub")
    private Collection<MhAgcPublicationInscrit> mhAgcPublicationInscritCollection;
    @JoinColumn(name = "code_a", referencedColumnName = "code_a", nullable = false)
    @ManyToOne(optional = false)
    private MhAgence codeA;

    public MhAgcPublication() {
    }

    public MhAgcPublication(String codePub) {
        this.codePub = codePub;
    }

    public MhAgcPublication(String codePub, String titre, String detail, Date dateD, Date dateF, Date datePub, boolean valide) {
        this.codePub = codePub;
        this.titre = titre;
        this.detail = detail;
        this.dateD = dateD;
        this.dateF = dateF;
        this.datePub = datePub;
        this.valide = valide;
    }

    public String getCodePub() {
        return codePub;
    }

    public void setCodePub(String codePub) {
        this.codePub = codePub;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public boolean getValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    @XmlTransient
    public Collection<MhAgcPublicationInscrit> getMhAgcPublicationInscritCollection() {
        return mhAgcPublicationInscritCollection;
    }

    public void setMhAgcPublicationInscritCollection(Collection<MhAgcPublicationInscrit> mhAgcPublicationInscritCollection) {
        this.mhAgcPublicationInscritCollection = mhAgcPublicationInscritCollection;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codePub != null ? codePub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhAgcPublication)) {
            return false;
        }
        MhAgcPublication other = (MhAgcPublication) object;
        if ((this.codePub == null && other.codePub != null) || (this.codePub != null && !this.codePub.equals(other.codePub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhAgcPublication[ codePub=" + codePub + " ]";
    }
    
}
