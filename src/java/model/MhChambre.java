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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.agc.MhConventionAgcChambre;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_chambre", catalog = "jlvljuzg_monhotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"num_ch"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhChambre.findAll", query = "SELECT m FROM MhChambre m")
    , @NamedQuery(name = "MhChambre.findByNumCh", query = "SELECT m FROM MhChambre m WHERE m.numCh = :numCh")
    , @NamedQuery(name = "MhChambre.findByNbPlace", query = "SELECT m FROM MhChambre m WHERE m.nbPlace = :nbPlace")
    , @NamedQuery(name = "MhChambre.findByPrix", query = "SELECT m FROM MhChambre m WHERE m.prix = :prix")
    , @NamedQuery(name = "MhChambre.findByPrcGainAgc", query = "SELECT m FROM MhChambre m WHERE m.prcGainAgc = :prcGainAgc")
    , @NamedQuery(name = "MhChambre.findByTypeCh", query = "SELECT m FROM MhChambre m WHERE m.typeCh = :typeCh")
    , @NamedQuery(name = "MhChambre.findByVisible", query = "SELECT m FROM MhChambre m WHERE m.visible = :visible")
    , @NamedQuery(name = "MhChambre.findByNumchApp", query = "SELECT m FROM MhChambre m WHERE m.numchApp = :numchApp")})
public class MhChambre implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCh")
    private Collection<MhConventionAgcChambre> mhConventionAgcChambreCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numch")
    private Collection<MhDemandeR> mhDemandeRCollection;

    @OneToMany(mappedBy = "numCh")
    private Collection<MhReservation> mhReservationCollection;



    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "num_ch", nullable = false, length = 300)
    private String numCh;
    @Column(name = "nb_place")
    private Integer nbPlace;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix", precision = 12)
    private Float prix;
    @Column(name = "prc_gain_agc")
    private Integer prcGainAgc;
    @Size(max = 100)
    @Column(name = "type_ch", length = 100)
    private String typeCh;
    @Column(name = "visible")
    private Boolean visible;
    @Size(max = 20)
    @Column(name = "num_ch_App", length = 20)
    private String numchApp;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h")
    @ManyToOne
    private MhHotel codeH;

    public MhChambre() {
    }

    public MhChambre(String numCh) {
        this.numCh = numCh;
    }

    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Integer getPrcGainAgc() {
        return prcGainAgc;
    }

    public void setPrcGainAgc(Integer prcGainAgc) {
        this.prcGainAgc = prcGainAgc;
    }

    public String getTypeCh() {
        return typeCh;
    }

    public void setTypeCh(String typeCh) {
        this.typeCh = typeCh;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getNumchApp() {
        return numchApp;
    }

    public void setNumchApp(String numchApp) {
        this.numchApp = numchApp;
    }

    public MhHotel getCodeH() {
        return codeH;
    }

    public void setCodeH(MhHotel codeH) {
        this.codeH = codeH;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCh != null ? numCh.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhChambre)) {
            return false;
        }
        MhChambre other = (MhChambre) object;
        if ((this.numCh == null && other.numCh != null) || (this.numCh != null && !this.numCh.equals(other.numCh))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhChambre[ numCh=" + numCh + " ]";
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    @XmlTransient
    public Collection<MhDemandeR> getMhDemandeRCollection() {
        return mhDemandeRCollection;
    }

    public void setMhDemandeRCollection(Collection<MhDemandeR> mhDemandeRCollection) {
        this.mhDemandeRCollection = mhDemandeRCollection;
    }

    @XmlTransient
    public Collection<MhConventionAgcChambre> getMhConventionAgcChambreCollection() {
        return mhConventionAgcChambreCollection;
    }

    public void setMhConventionAgcChambreCollection(Collection<MhConventionAgcChambre> mhConventionAgcChambreCollection) {
        this.mhConventionAgcChambreCollection = mhConventionAgcChambreCollection;
    }

  
    
}
