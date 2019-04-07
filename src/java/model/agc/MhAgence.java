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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.MhReservation;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_agence", catalog = "jlvljuzg_monhotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_a"})
    , @UniqueConstraint(columnNames = {"num_contrat"})
    , @UniqueConstraint(columnNames = {"nrc"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhAgence.findAll", query = "SELECT m FROM MhAgence m")
    , @NamedQuery(name = "MhAgence.findByCodeA", query = "SELECT m FROM MhAgence m WHERE m.codeA = :codeA")
    , @NamedQuery(name = "MhAgence.findByRaisonSocial", query = "SELECT m FROM MhAgence m WHERE m.raisonSocial = :raisonSocial")
    , @NamedQuery(name = "MhAgence.findByAdresse", query = "SELECT m FROM MhAgence m WHERE m.adresse = :adresse")
    , @NamedQuery(name = "MhAgence.findByCommune", query = "SELECT m FROM MhAgence m WHERE m.commune = :commune")
    , @NamedQuery(name = "MhAgence.findByWilaya", query = "SELECT m FROM MhAgence m WHERE m.wilaya = :wilaya")
    , @NamedQuery(name = "MhAgence.findByCodePostal", query = "SELECT m FROM MhAgence m WHERE m.codePostal = :codePostal")
    , @NamedQuery(name = "MhAgence.findByNrc", query = "SELECT m FROM MhAgence m WHERE m.nrc = :nrc")
    , @NamedQuery(name = "MhAgence.findByNif", query = "SELECT m FROM MhAgence m WHERE m.nif = :nif")
    , @NamedQuery(name = "MhAgence.findByNai", query = "SELECT m FROM MhAgence m WHERE m.nai = :nai")
    , @NamedQuery(name = "MhAgence.findByRib", query = "SELECT m FROM MhAgence m WHERE m.rib = :rib")
    , @NamedQuery(name = "MhAgence.findByTel", query = "SELECT m FROM MhAgence m WHERE m.tel = :tel")
    , @NamedQuery(name = "MhAgence.findByFax", query = "SELECT m FROM MhAgence m WHERE m.fax = :fax")
    , @NamedQuery(name = "MhAgence.findByMail", query = "SELECT m FROM MhAgence m WHERE m.mail = :mail")
    , @NamedQuery(name = "MhAgence.findByCompte", query = "SELECT m FROM MhAgence m WHERE m.compte = :compte")
    , @NamedQuery(name = "MhAgence.findByDateAdaptation", query = "SELECT m FROM MhAgence m WHERE m.dateAdaptation = :dateAdaptation")
    , @NamedQuery(name = "MhAgence.findByDateContrat", query = "SELECT m FROM MhAgence m WHERE m.dateContrat = :dateContrat")
    , @NamedQuery(name = "MhAgence.findByNumContrat", query = "SELECT m FROM MhAgence m WHERE m.numContrat = :numContrat")
    , @NamedQuery(name = "MhAgence.findByIndiceP", query = "SELECT m FROM MhAgence m WHERE m.indiceP = :indiceP")
    , @NamedQuery(name = "MhAgence.findByParametreGps", query = "SELECT m FROM MhAgence m WHERE m.parametreGps = :parametreGps")})
public class MhAgence implements Serializable {

    @OneToMany(mappedBy = "codeA")
    private Collection<MhCompteUserA> mhCompteUserACollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeA")
    private Collection<MhAgcRsvAnnuler> mhAgcRsvAnnulerCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeA")
    private Collection<MhAgcPublicationInscrit> mhAgcPublicationInscritCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeA")
    private Collection<MhAgcPublication> mhAgcPublicationCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeA")
    private Collection<MhDemandeConvAgc> mhDemandeConvAgcCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeA")
    private Collection<MhConventionAgc> mhConventionAgcCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_a", nullable = false, length = 200)
    private String codeA;
    @Size(max = 500)
    @Column(name = "raison_social", length = 500)
    private String raisonSocial;
    @Size(max = 2000)
    @Column(name = "adresse", length = 2000)
    private String adresse;
    @Size(max = 80)
    @Column(name = "Commune", length = 80)
    private String commune;
    @Size(max = 80)
    @Column(name = "wilaya", length = 80)
    private String wilaya;
    @Size(max = 20)
    @Column(name = "code_postal", length = 20)
    private String codePostal;
    @Size(max = 30)
    @Column(name = "nrc", length = 30)
    private String nrc;
    @Size(max = 50)
    @Column(name = "nif", length = 50)
    private String nif;
    @Size(max = 50)
    @Column(name = "nai", length = 50)
    private String nai;
    @Size(max = 100)
    @Column(name = "rib", length = 100)
    private String rib;
    @Size(max = 25)
    @Column(name = "tel", length = 25)
    private String tel;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 25)
    @Column(name = "fax", length = 25)
    private String fax;
    @Size(max = 60)
    @Column(name = "mail", length = 60)
    private String mail;
    @Column(name = "compte")
    private Boolean compte;
    @Column(name = "date_adaptation")
    @Temporal(TemporalType.DATE)
    private Date dateAdaptation;
    @Column(name = "date_contrat")
    @Temporal(TemporalType.DATE)
    private Date dateContrat;
    @Size(max = 30)
    @Column(name = "num_contrat", length = 30)
    private String numContrat;
    @Column(name = "indice_p")
    private Integer indiceP;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Size(max = 500)
    @Column(name = "parametre_gps", length = 500)
    private String parametreGps;
    @OneToMany(mappedBy = "codeA")
    private Collection<MhReservation> mhReservationCollection;

    public MhAgence() {
    }

    public MhAgence(String codeA) {
        this.codeA = codeA;
    }

    public String getCodeA() {
        return codeA;
    }

    public void setCodeA(String codeA) {
        this.codeA = codeA;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getCompte() {
        return compte;
    }

    public void setCompte(Boolean compte) {
        this.compte = compte;
    }

    public Date getDateAdaptation() {
        return dateAdaptation;
    }

    public void setDateAdaptation(Date dateAdaptation) {
        this.dateAdaptation = dateAdaptation;
    }

    public Date getDateContrat() {
        return dateContrat;
    }

    public void setDateContrat(Date dateContrat) {
        this.dateContrat = dateContrat;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String numContrat) {
        this.numContrat = numContrat;
    }

    public Integer getIndiceP() {
        return indiceP;
    }

    public void setIndiceP(Integer indiceP) {
        this.indiceP = indiceP;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParametreGps() {
        return parametreGps;
    }

    public void setParametreGps(String parametreGps) {
        this.parametreGps = parametreGps;
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeA != null ? codeA.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhAgence)) {
            return false;
        }
        MhAgence other = (MhAgence) object;
        if ((this.codeA == null && other.codeA != null) || (this.codeA != null && !this.codeA.equals(other.codeA))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhAgence[ codeA=" + codeA + " ]";
    }

    @XmlTransient
    public Collection<MhDemandeConvAgc> getMhDemandeConvAgcCollection() {
        return mhDemandeConvAgcCollection;
    }

    public void setMhDemandeConvAgcCollection(Collection<MhDemandeConvAgc> mhDemandeConvAgcCollection) {
        this.mhDemandeConvAgcCollection = mhDemandeConvAgcCollection;
    }

    @XmlTransient
    public Collection<MhConventionAgc> getMhConventionAgcCollection() {
        return mhConventionAgcCollection;
    }

    public void setMhConventionAgcCollection(Collection<MhConventionAgc> mhConventionAgcCollection) {
        this.mhConventionAgcCollection = mhConventionAgcCollection;
    }

    @XmlTransient
    public Collection<MhAgcPublicationInscrit> getMhAgcPublicationInscritCollection() {
        return mhAgcPublicationInscritCollection;
    }

    public void setMhAgcPublicationInscritCollection(Collection<MhAgcPublicationInscrit> mhAgcPublicationInscritCollection) {
        this.mhAgcPublicationInscritCollection = mhAgcPublicationInscritCollection;
    }

    @XmlTransient
    public Collection<MhAgcPublication> getMhAgcPublicationCollection() {
        return mhAgcPublicationCollection;
    }

    public void setMhAgcPublicationCollection(Collection<MhAgcPublication> mhAgcPublicationCollection) {
        this.mhAgcPublicationCollection = mhAgcPublicationCollection;
    }

    @XmlTransient
    public Collection<MhAgcRsvAnnuler> getMhAgcRsvAnnulerCollection() {
        return mhAgcRsvAnnulerCollection;
    }

    public void setMhAgcRsvAnnulerCollection(Collection<MhAgcRsvAnnuler> mhAgcRsvAnnulerCollection) {
        this.mhAgcRsvAnnulerCollection = mhAgcRsvAnnulerCollection;
    }

    @XmlTransient
    public Collection<MhCompteUserA> getMhCompteUserACollection() {
        return mhCompteUserACollection;
    }

    public void setMhCompteUserACollection(Collection<MhCompteUserA> mhCompteUserACollection) {
        this.mhCompteUserACollection = mhCompteUserACollection;
    }
    
}
