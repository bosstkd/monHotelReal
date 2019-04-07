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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import model.MhHotel;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_agc_rsv_annuler", catalog = "jlvljuzg_monhotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhAgcRsvAnnuler.findAll", query = "SELECT m FROM MhAgcRsvAnnuler m")
    , @NamedQuery(name = "MhAgcRsvAnnuler.findByCodeR", query = "SELECT m FROM MhAgcRsvAnnuler m WHERE m.codeR = :codeR")
    , @NamedQuery(name = "MhAgcRsvAnnuler.findByMontant", query = "SELECT m FROM MhAgcRsvAnnuler m WHERE m.montant = :montant")
    , @NamedQuery(name = "MhAgcRsvAnnuler.findByPrcQuotion", query = "SELECT m FROM MhAgcRsvAnnuler m WHERE m.prcQuotion = :prcQuotion")
    , @NamedQuery(name = "MhAgcRsvAnnuler.findByRetenue", query = "SELECT m FROM MhAgcRsvAnnuler m WHERE m.retenue = :retenue")
    , @NamedQuery(name = "MhAgcRsvAnnuler.findByCodeAnnule", query = "SELECT m FROM MhAgcRsvAnnuler m WHERE m.codeAnnule = :codeAnnule")})
public class MhAgcRsvAnnuler implements Serializable {

    @Column(name = "date_a")
    @Temporal(TemporalType.DATE)
    private Date dateA;
    @Column(name = "date_d")
    @Temporal(TemporalType.DATE)
    private Date dateD;

    @Column(name = "dates")
    @Temporal(TemporalType.DATE)
    private Date dates;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_r", nullable = false, length = 200)
    private String codeR;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "designation", nullable = false, length = 65535)
    private String designation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant", nullable = false)
    private int montant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prc_quotion", nullable = false)
    private int prcQuotion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "retenue", nullable = false)
    private int retenue;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_annule", nullable = false, length = 200)
    private String codeAnnule;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;
    @JoinColumn(name = "code_a", referencedColumnName = "code_a", nullable = false)
    @ManyToOne(optional = false)
    private MhAgence codeA;

    public MhAgcRsvAnnuler() {
    }

    public MhAgcRsvAnnuler(String codeAnnule) {
        this.codeAnnule = codeAnnule;
    }

    public MhAgcRsvAnnuler(String codeAnnule, String codeR, String designation, int montant, int prcQuotion, int retenue) {
        this.codeAnnule = codeAnnule;
        this.codeR = codeR;
        this.designation = designation;
        this.montant = montant;
        this.prcQuotion = prcQuotion;
        this.retenue = retenue;
    }

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getPrcQuotion() {
        return prcQuotion;
    }

    public void setPrcQuotion(int prcQuotion) {
        this.prcQuotion = prcQuotion;
    }

    public int getRetenue() {
        return retenue;
    }

    public void setRetenue(int retenue) {
        this.retenue = retenue;
    }

    public String getCodeAnnule() {
        return codeAnnule;
    }

    public void setCodeAnnule(String codeAnnule) {
        this.codeAnnule = codeAnnule;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeAnnule != null ? codeAnnule.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhAgcRsvAnnuler)) {
            return false;
        }
        MhAgcRsvAnnuler other = (MhAgcRsvAnnuler) object;
        if ((this.codeAnnule == null && other.codeAnnule != null) || (this.codeAnnule != null && !this.codeAnnule.equals(other.codeAnnule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhAgcRsvAnnuler[ codeAnnule=" + codeAnnule + " ]";
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Date getDateA() {
        return dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }
    
}
