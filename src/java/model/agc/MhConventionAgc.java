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
import model.MhHotel;
import model.MhReservation;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_convention_agc", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhConventionAgc.findAll", query = "SELECT m FROM MhConventionAgc m")
    , @NamedQuery(name = "MhConventionAgc.findByCodeConvAgc", query = "SELECT m FROM MhConventionAgc m WHERE m.codeConvAgc = :codeConvAgc")
    , @NamedQuery(name = "MhConventionAgc.findByDateConv", query = "SELECT m FROM MhConventionAgc m WHERE m.dateConv = :dateConv")
    , @NamedQuery(name = "MhConventionAgc.findByDateD", query = "SELECT m FROM MhConventionAgc m WHERE m.dateD = :dateD")
    , @NamedQuery(name = "MhConventionAgc.findByDateF", query = "SELECT m FROM MhConventionAgc m WHERE m.dateF = :dateF")
    , @NamedQuery(name = "MhConventionAgc.findByPrcReduction", query = "SELECT m FROM MhConventionAgc m WHERE m.prcReduction = :prcReduction")
    , @NamedQuery(name = "MhConventionAgc.findByPrixEngagement", query = "SELECT m FROM MhConventionAgc m WHERE m.prixEngagement = :prixEngagement")
    , @NamedQuery(name = "MhConventionAgc.findByReservationACouvert", query = "SELECT m FROM MhConventionAgc m WHERE m.reservationACouvert = :reservationACouvert")})
public class MhConventionAgc implements Serializable {

    @Column(name = "dette")
    private Boolean dette;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeConventionAgc")
    private Collection<MhConventionAgcChambre> mhConventionAgcChambreCollection;

    @Size(max = 10)
    @Column(name = "typeConv", length = 10)
    private String typeConv;

    @OneToMany(mappedBy = "codeConvAgc")
    private Collection<MhReservation> mhReservationCollection;

    @Column(name = "prc_quotion_annule")
    private Integer prcQuotionAnnule;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_conv_agc", nullable = false, length = 200)
    private String codeConvAgc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_conv", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateConv;
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
    @Column(name = "prc_reduction", nullable = false)
    private int prcReduction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prix_Engagement", nullable = false)
    private int prixEngagement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reservation_a_couvert", nullable = false)
    private boolean reservationACouvert;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;
    @JoinColumn(name = "code_a", referencedColumnName = "code_a", nullable = false)
    @ManyToOne(optional = false)
    private MhAgence codeA;
    @JoinColumn(name = "code_dm_conv", referencedColumnName = "code_dm_conv", nullable = false)
    @ManyToOne(optional = false)
    private MhDemandeConvAgc codeDmConv;

    public MhConventionAgc() {
    }

    public MhConventionAgc(String codeConvAgc) {
        this.codeConvAgc = codeConvAgc;
    }

    public MhConventionAgc(String codeConvAgc, Date dateConv, Date dateD, Date dateF, int prcReduction, int prixEngagement, boolean reservationACouvert) {
        this.codeConvAgc = codeConvAgc;
        this.dateConv = dateConv;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prcReduction = prcReduction;
        this.prixEngagement = prixEngagement;
        this.reservationACouvert = reservationACouvert;
    }

    public String getCodeConvAgc() {
        return codeConvAgc;
    }

    public void setCodeConvAgc(String codeConvAgc) {
        this.codeConvAgc = codeConvAgc;
    }

    public Date getDateConv() {
        return dateConv;
    }

    public void setDateConv(Date dateConv) {
        this.dateConv = dateConv;
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

    public int getPrcReduction() {
        return prcReduction;
    }

    public void setPrcReduction(int prcReduction) {
        this.prcReduction = prcReduction;
    }

    public int getPrixEngagement() {
        return prixEngagement;
    }

    public void setPrixEngagement(int prixEngagement) {
        this.prixEngagement = prixEngagement;
    }

    public boolean getReservationACouvert() {
        return reservationACouvert;
    }

    public void setReservationACouvert(boolean reservationACouvert) {
        this.reservationACouvert = reservationACouvert;
    }

    public MhHotel getCodeH() {
        return codeH;
    }

    public void setCodeH(MhHotel codeH) {
        this.codeH = codeH;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
    }

    public MhDemandeConvAgc getCodeDmConv() {
        return codeDmConv;
    }

    public void setCodeDmConv(MhDemandeConvAgc codeDmConv) {
        this.codeDmConv = codeDmConv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeConvAgc != null ? codeConvAgc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhConventionAgc)) {
            return false;
        }
        MhConventionAgc other = (MhConventionAgc) object;
        if ((this.codeConvAgc == null && other.codeConvAgc != null) || (this.codeConvAgc != null && !this.codeConvAgc.equals(other.codeConvAgc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhConventionAgc[ codeConvAgc=" + codeConvAgc + " ]";
    }

    public Integer getPrcQuotionAnnule() {
        return prcQuotionAnnule;
    }

    public void setPrcQuotionAnnule(Integer prcQuotionAnnule) {
        this.prcQuotionAnnule = prcQuotionAnnule;
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    public String getTypeConv() {
        return typeConv;
    }

    public void setTypeConv(String typeConv) {
        this.typeConv = typeConv;
    }

    @XmlTransient
    public Collection<MhConventionAgcChambre> getMhConventionAgcChambreCollection() {
        return mhConventionAgcChambreCollection;
    }

    public void setMhConventionAgcChambreCollection(Collection<MhConventionAgcChambre> mhConventionAgcChambreCollection) {
        this.mhConventionAgcChambreCollection = mhConventionAgcChambreCollection;
    }

    public Boolean getDette() {
        return dette;
    }

    public void setDette(Boolean dette) {
        this.dette = dette;
    }
    
}
