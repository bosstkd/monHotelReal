/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.particulier;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import model.MhCltSCh;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_pub_particulier", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhPubParticulier.findAll", query = "SELECT m FROM MhPubParticulier m")
    , @NamedQuery(name = "MhPubParticulier.findByCodePubParticulier", query = "SELECT m FROM MhPubParticulier m WHERE m.codePubParticulier = :codePubParticulier")
    , @NamedQuery(name = "MhPubParticulier.findByWifi", query = "SELECT m FROM MhPubParticulier m WHERE m.wifi = :wifi")
    , @NamedQuery(name = "MhPubParticulier.findByMeuble", query = "SELECT m FROM MhPubParticulier m WHERE m.meuble = :meuble")
    , @NamedQuery(name = "MhPubParticulier.findByCuisine", query = "SELECT m FROM MhPubParticulier m WHERE m.cuisine = :cuisine")
    , @NamedQuery(name = "MhPubParticulier.findByGarage", query = "SELECT m FROM MhPubParticulier m WHERE m.garage = :garage")
    , @NamedQuery(name = "MhPubParticulier.findByWilaya", query = "SELECT m FROM MhPubParticulier m WHERE m.wilaya = :wilaya")
    , @NamedQuery(name = "MhPubParticulier.findByDatePub", query = "SELECT m FROM MhPubParticulier m WHERE m.datePub = :datePub")
    , @NamedQuery(name = "MhPubParticulier.findByType", query = "SELECT m FROM MhPubParticulier m WHERE m.type = :type")
    , @NamedQuery(name = "MhPubParticulier.findByNbPiece", query = "SELECT m FROM MhPubParticulier m WHERE m.nbPiece = :nbPiece")
    , @NamedQuery(name = "MhPubParticulier.findByPrix", query = "SELECT m FROM MhPubParticulier m WHERE m.prix = :prix")})
public class MhPubParticulier implements Serializable {

    @OneToMany(mappedBy = "codePubPart")
    private Collection<MhPartPubInscrit> mhPartPubInscritCollection;

    @Column(name = "date_du")
    @Temporal(TemporalType.DATE)
    private Date dateDu;
    @Column(name = "date_au")
    @Temporal(TemporalType.DATE)
    private Date dateAu;

    @Column(name = "active")
    private Boolean active;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_pub_particulier", nullable = false, length = 200)
    private String codePubParticulier;
    @Column(name = "wifi")
    private Boolean wifi;
    @Column(name = "meuble")
    private Boolean meuble;
    @Column(name = "cuisine")
    private Boolean cuisine;
    @Column(name = "garage")
    private Boolean garage;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @Lob
    @Size(max = 65535)
    @Column(name = "adresse", length = 65535)
    private String adresse;
    @Size(max = 150)
    @Column(name = "wilaya", length = 150)
    private String wilaya;
    @Column(name = "date_pub")
    @Temporal(TemporalType.DATE)
    private Date datePub;
    @Size(max = 100)
    @Column(name = "type", length = 100)
    private String type;
    @Column(name = "nb_piece")
    private Integer nbPiece;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix", precision = 12)
    private Float prix;
    @JoinColumn(name = "n_p_id", referencedColumnName = "n_p_id")
    @ManyToOne
    private MhCltSCh nPId;

    public MhPubParticulier() {
    }

    public MhPubParticulier(String codePubParticulier) {
        this.codePubParticulier = codePubParticulier;
    }

    public String getCodePubParticulier() {
        return codePubParticulier;
    }

    public void setCodePubParticulier(String codePubParticulier) {
        this.codePubParticulier = codePubParticulier;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getMeuble() {
        return meuble;
    }

    public void setMeuble(Boolean meuble) {
        this.meuble = meuble;
    }

    public Boolean getCuisine() {
        return cuisine;
    }

    public void setCuisine(Boolean cuisine) {
        this.cuisine = cuisine;
    }

    public Boolean getGarage() {
        return garage;
    }

    public void setGarage(Boolean garage) {
        this.garage = garage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(Integer nbPiece) {
        this.nbPiece = nbPiece;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public MhCltSCh getNPId() {
        return nPId;
    }

    public void setNPId(MhCltSCh nPId) {
        this.nPId = nPId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codePubParticulier != null ? codePubParticulier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhPubParticulier)) {
            return false;
        }
        MhPubParticulier other = (MhPubParticulier) object;
        if ((this.codePubParticulier == null && other.codePubParticulier != null) || (this.codePubParticulier != null && !this.codePubParticulier.equals(other.codePubParticulier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.particuler.MhPubParticulier[ codePubParticulier=" + codePubParticulier + " ]";
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateDu() {
        return dateDu;
    }

    public void setDateDu(Date dateDu) {
        this.dateDu = dateDu;
    }

    public Date getDateAu() {
        return dateAu;
    }

    public void setDateAu(Date dateAu) {
        this.dateAu = dateAu;
    }

    @XmlTransient
    public Collection<MhPartPubInscrit> getMhPartPubInscritCollection() {
        return mhPartPubInscritCollection;
    }

    public void setMhPartPubInscritCollection(Collection<MhPartPubInscrit> mhPartPubInscritCollection) {
        this.mhPartPubInscritCollection = mhPartPubInscritCollection;
    }
    
}
