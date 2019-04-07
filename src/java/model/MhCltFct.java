/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_clt_fct", catalog = "jlvljuzg_monhotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_c"})
    , @UniqueConstraint(columnNames = {"nrc"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhCltFct.findAll", query = "SELECT m FROM MhCltFct m")
    , @NamedQuery(name = "MhCltFct.findByRaisonSociale", query = "SELECT m FROM MhCltFct m WHERE m.raisonSociale = :raisonSociale")
    , @NamedQuery(name = "MhCltFct.findByNrc", query = "SELECT m FROM MhCltFct m WHERE m.nrc = :nrc")
    , @NamedQuery(name = "MhCltFct.findByNif", query = "SELECT m FROM MhCltFct m WHERE m.nif = :nif")
    , @NamedQuery(name = "MhCltFct.findByNai", query = "SELECT m FROM MhCltFct m WHERE m.nai = :nai")
    , @NamedQuery(name = "MhCltFct.findByAdresse", query = "SELECT m FROM MhCltFct m WHERE m.adresse = :adresse")
    , @NamedQuery(name = "MhCltFct.findByTel", query = "SELECT m FROM MhCltFct m WHERE m.tel = :tel")
    , @NamedQuery(name = "MhCltFct.findByExonore", query = "SELECT m FROM MhCltFct m WHERE m.exonore = :exonore")
    , @NamedQuery(name = "MhCltFct.findByCodeC", query = "SELECT m FROM MhCltFct m WHERE m.codeC = :codeC")
    , @NamedQuery(name = "MhCltFct.findByMail", query = "SELECT m FROM MhCltFct m WHERE m.mail = :mail")})
public class MhCltFct implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeC")
    private Collection<MhDemandeR> mhDemandeRCollection;

    @OneToMany(mappedBy = "codeC")
    private Collection<MhCltSCh> mhCltSChCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeC")
    private Collection<MhConvention> mhConventionCollection;

    @OneToMany(mappedBy = "codeC")
    private Collection<MhReservation> mhReservationCollection;

    private static final long serialVersionUID = 1L;
   
    
    @Size(max = 200)
    @Column(name = "raison_sociale", length = 200)
    private String raisonSociale;
    
    @Size(max = 80)
    @Column(name = "nrc", length = 80)
    private String nrc;
    @Size(max = 80)
    @Column(name = "nif", length = 80)
    private String nif;
    @Size(max = 80)
    @Column(name = "nai", length = 80)
    private String nai;
    @Size(max = 2000)
    @Column(name = "adresse", length = 2000)
    private String adresse;
    @Size(max = 30)
    @Column(name = "tel", length = 30)
    private String tel;
    @Column(name = "exonore")
    private Boolean exonore;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 402)
    @Column(name = "code_c", nullable = false, length = 402)
    private String codeC;
    @Size(max = 100)
    @Column(name = "mail", length = 100)
    private String mail;

    public MhCltFct() {
    }

    public MhCltFct(String codeC) {
        this.codeC = codeC;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNai() {
        return nai;
    }

    public void setNai(String nai) {
        this.nai = nai;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Boolean getExonore() {
        return exonore;
    }

    public void setExonore(Boolean exonore) {
        this.exonore = exonore;
    }

    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeC != null ? codeC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhCltFct)) {
            return false;
        }
        MhCltFct other = (MhCltFct) object;
        if ((this.codeC == null && other.codeC != null) || (this.codeC != null && !this.codeC.equals(other.codeC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhCltFct[ codeC=" + codeC + " ]";
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    @XmlTransient
    public Collection<MhConvention> getMhConventionCollection() {
        return mhConventionCollection;
    }

    public void setMhConventionCollection(Collection<MhConvention> mhConventionCollection) {
        this.mhConventionCollection = mhConventionCollection;
    }

    @XmlTransient
    public Collection<MhCltSCh> getMhCltSChCollection() {
        return mhCltSChCollection;
    }

    public void setMhCltSChCollection(Collection<MhCltSCh> mhCltSChCollection) {
        this.mhCltSChCollection = mhCltSChCollection;
    }

    @XmlTransient
    public Collection<MhDemandeR> getMhDemandeRCollection() {
        return mhDemandeRCollection;
    }

    public void setMhDemandeRCollection(Collection<MhDemandeR> mhDemandeRCollection) {
        this.mhDemandeRCollection = mhDemandeRCollection;
    }
    
}
