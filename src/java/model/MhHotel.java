/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
import model.agc.MhAgcRsvAnnuler;
import model.agc.MhConventionAgc;
import model.agc.MhDemandeConvAgc;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_hotel", catalog = "jlvljuzg_monhotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"num_contrat"})
    , @UniqueConstraint(columnNames = {"code_h"})
    , @UniqueConstraint(columnNames = {"nrc"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhHotel.findAll", query = "SELECT m FROM MhHotel m")
    , @NamedQuery(name = "MhHotel.findByCodeH", query = "SELECT m FROM MhHotel m WHERE m.codeH = :codeH")
    , @NamedQuery(name = "MhHotel.findByRaisonSocial", query = "SELECT m FROM MhHotel m WHERE m.raisonSocial = :raisonSocial")
    , @NamedQuery(name = "MhHotel.findByAdresse", query = "SELECT m FROM MhHotel m WHERE m.adresse = :adresse")
    , @NamedQuery(name = "MhHotel.findByCommune", query = "SELECT m FROM MhHotel m WHERE m.commune = :commune")
    , @NamedQuery(name = "MhHotel.findByWilaya", query = "SELECT m FROM MhHotel m WHERE m.wilaya = :wilaya")
    , @NamedQuery(name = "MhHotel.findByCodePostal", query = "SELECT m FROM MhHotel m WHERE m.codePostal = :codePostal")
    , @NamedQuery(name = "MhHotel.findByNrc", query = "SELECT m FROM MhHotel m WHERE m.nrc = :nrc")
    , @NamedQuery(name = "MhHotel.findByNif", query = "SELECT m FROM MhHotel m WHERE m.nif = :nif")
    , @NamedQuery(name = "MhHotel.findByNai", query = "SELECT m FROM MhHotel m WHERE m.nai = :nai")
    , @NamedQuery(name = "MhHotel.findByRib", query = "SELECT m FROM MhHotel m WHERE m.rib = :rib")
    , @NamedQuery(name = "MhHotel.findByTel", query = "SELECT m FROM MhHotel m WHERE m.tel = :tel")
    , @NamedQuery(name = "MhHotel.findByFax", query = "SELECT m FROM MhHotel m WHERE m.fax = :fax")
    , @NamedQuery(name = "MhHotel.findByMail", query = "SELECT m FROM MhHotel m WHERE m.mail = :mail")
    , @NamedQuery(name = "MhHotel.findByCompte", query = "SELECT m FROM MhHotel m WHERE m.compte = :compte")
    , @NamedQuery(name = "MhHotel.findByDateAdaptation", query = "SELECT m FROM MhHotel m WHERE m.dateAdaptation = :dateAdaptation")
    , @NamedQuery(name = "MhHotel.findByDateContrat", query = "SELECT m FROM MhHotel m WHERE m.dateContrat = :dateContrat")
    , @NamedQuery(name = "MhHotel.findByNumContrat", query = "SELECT m FROM MhHotel m WHERE m.numContrat = :numContrat")
    , @NamedQuery(name = "MhHotel.findByIndiceP", query = "SELECT m FROM MhHotel m WHERE m.indiceP = :indiceP")
    , @NamedQuery(name = "MhHotel.findByTvaS", query = "SELECT m FROM MhHotel m WHERE m.tvaS = :tvaS")
    , @NamedQuery(name = "MhHotel.findByPensionC", query = "SELECT m FROM MhHotel m WHERE m.pensionC = :pensionC")
    , @NamedQuery(name = "MhHotel.findByPrcDemiPension", query = "SELECT m FROM MhHotel m WHERE m.prcDemiPension = :prcDemiPension")
    , @NamedQuery(name = "MhHotel.findByTaxeSejour", query = "SELECT m FROM MhHotel m WHERE m.taxeSejour = :taxeSejour")
    , @NamedQuery(name = "MhHotel.findByEtoile", query = "SELECT m FROM MhHotel m WHERE m.etoile = :etoile")
    , @NamedQuery(name = "MhHotel.findByParametreGps", query = "SELECT m FROM MhHotel m WHERE m.parametreGps = :parametreGps")})
public class MhHotel implements Serializable {

    @Size(max = 100)
    @Column(name = "pay", length = 100)
    private String pay;

    @Column(name = "bar")
    private Boolean bar;

    @Column(name = "tva")
    private Integer tva;
    @Column(name = "picine")
    private Boolean picine;
    @Column(name = "restaurant")
    private Boolean restaurant;
    @Column(name = "wifi")
    private Boolean wifi;
    @Column(name = "cafeteria")
    private Boolean cafeteria;
   

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhAgcRsvAnnuler> mhAgcRsvAnnulerCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhDemandeConvAgc> mhDemandeConvAgcCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhConventionAgc> mhConventionAgcCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhDemandeClt> mhDemandeCltCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhDemandeR> mhDemandeRCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhConvention> mhConventionCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeH")
    private Collection<MhUsersDroits> mhUsersDroitsCollection;

    @OneToMany(mappedBy = "codeH")
    private Collection<MhReservation> mhReservationCollection;

 

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_h", nullable = false, length = 200)
    private String codeH;
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
    @Column(name = "tva_s")
    private Boolean tvaS;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pension_c", precision = 12)
    private Float pensionC;
    @Column(name = "prc_demi_pension")
    private Integer prcDemiPension;
    @Column(name = "taxe_sejour", precision = 12)
    private Float taxeSejour;
    @Column(name = "etoile")
    private Integer etoile;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Size(max = 500)
    @Column(name = "parametre_gps", length = 500)
    private String parametreGps;
    @OneToMany(mappedBy = "codeH")
    private Collection<MhCompteUserH> mhCompteUserHCollection;
    @OneToMany(mappedBy = "codeH")
    private Collection<MhChambre> mhChambreCollection;

    public MhHotel() {
    }

    public MhHotel(String codeH) {
        this.codeH = codeH;
    }

    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
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

    public Boolean getTvaS() {
        return tvaS;
    }

    public void setTvaS(Boolean tvaS) {
        this.tvaS = tvaS;
    }

    public Float getPensionC() {
        return pensionC;
    }

    public void setPensionC(Float pensionC) {
        this.pensionC = pensionC;
    }

    public Integer getPrcDemiPension() {
        return prcDemiPension;
    }

    public void setPrcDemiPension(Integer prcDemiPension) {
        this.prcDemiPension = prcDemiPension;
    }

    public Float getTaxeSejour() {
        return taxeSejour;
    }

    public void setTaxeSejour(Float taxeSejour) {
        this.taxeSejour = taxeSejour;
    }

    public Integer getEtoile() {
        return etoile;
    }

    public void setEtoile(Integer etoile) {
        this.etoile = etoile;
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
    public Collection<MhCompteUserH> getMhCompteUserHCollection() {
        return mhCompteUserHCollection;
    }

    public void setMhCompteUserHCollection(Collection<MhCompteUserH> mhCompteUserHCollection) {
        this.mhCompteUserHCollection = mhCompteUserHCollection;
    }

    @XmlTransient
    public Collection<MhChambre> getMhChambreCollection() {
        return mhChambreCollection;
    }

    public void setMhChambreCollection(Collection<MhChambre> mhChambreCollection) {
        this.mhChambreCollection = mhChambreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeH != null ? codeH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhHotel)) {
            return false;
        }
        MhHotel other = (MhHotel) object;
        if ((this.codeH == null && other.codeH != null) || (this.codeH != null && !this.codeH.equals(other.codeH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhHotel[ codeH=" + codeH + " ]";
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    @XmlTransient
    public Collection<MhUsersDroits> getMhUsersDroitsCollection() {
        return mhUsersDroitsCollection;
    }

    public void setMhUsersDroitsCollection(Collection<MhUsersDroits> mhUsersDroitsCollection) {
        this.mhUsersDroitsCollection = mhUsersDroitsCollection;
    }

    @XmlTransient
    public Collection<MhConvention> getMhConventionCollection() {
        return mhConventionCollection;
    }

    public void setMhConventionCollection(Collection<MhConvention> mhConventionCollection) {
        this.mhConventionCollection = mhConventionCollection;
    }

    @XmlTransient
    public Collection<MhDemandeR> getMhDemandeRCollection() {
        return mhDemandeRCollection;
    }

    public void setMhDemandeRCollection(Collection<MhDemandeR> mhDemandeRCollection) {
        this.mhDemandeRCollection = mhDemandeRCollection;
    }

    @XmlTransient
    public Collection<MhDemandeClt> getMhDemandeCltCollection() {
        return mhDemandeCltCollection;
    }

    public void setMhDemandeCltCollection(Collection<MhDemandeClt> mhDemandeCltCollection) {
        this.mhDemandeCltCollection = mhDemandeCltCollection;
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
    public Collection<MhAgcRsvAnnuler> getMhAgcRsvAnnulerCollection() {
        return mhAgcRsvAnnulerCollection;
    }

    public void setMhAgcRsvAnnulerCollection(Collection<MhAgcRsvAnnuler> mhAgcRsvAnnulerCollection) {
        this.mhAgcRsvAnnulerCollection = mhAgcRsvAnnulerCollection;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public Boolean getPicine() {
        return picine;
    }

    public void setPicine(Boolean picine) {
        this.picine = picine;
    }

    public Boolean getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Boolean restaurant) {
        this.restaurant = restaurant;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(Boolean cafeteria) {
        this.cafeteria = cafeteria;
    }

    public Boolean getBar() {
        return bar;
    }

    public void setBar(Boolean bar) {
        this.bar = bar;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

 

   
    
}
