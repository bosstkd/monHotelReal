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

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_demande_conv_agc", catalog = "jlvljuzg_monhotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhDemandeConvAgc.findAll", query = "SELECT m FROM MhDemandeConvAgc m")
    , @NamedQuery(name = "MhDemandeConvAgc.findByCodeDmConv", query = "SELECT m FROM MhDemandeConvAgc m WHERE m.codeDmConv = :codeDmConv")
    , @NamedQuery(name = "MhDemandeConvAgc.findByDateDmConv", query = "SELECT m FROM MhDemandeConvAgc m WHERE m.dateDmConv = :dateDmConv")
    , @NamedQuery(name = "MhDemandeConvAgc.findByDateD", query = "SELECT m FROM MhDemandeConvAgc m WHERE m.dateD = :dateD")
    , @NamedQuery(name = "MhDemandeConvAgc.findByDateF", query = "SELECT m FROM MhDemandeConvAgc m WHERE m.dateF = :dateF")
    , @NamedQuery(name = "MhDemandeConvAgc.findByPrixEngagement", query = "SELECT m FROM MhDemandeConvAgc m WHERE m.prixEngagement = :prixEngagement")})
public class MhDemandeConvAgc implements Serializable {

    @Column(name = "dette")
    private Boolean dette;

    @Size(max = 10)
    @Column(name = "typeConv", length = 10)
    private String typeConv;
    @Column(name = "nbChambre")
    private Integer nbChambre;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_dm_conv", nullable = false, length = 200)
    private String codeDmConv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_dm_conv", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDmConv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateD", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateD;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateF", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateF;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prixEngagement", nullable = false)
    private int prixEngagement;
    @JoinColumn(name = "code_a", referencedColumnName = "code_a", nullable = false)
    @ManyToOne(optional = false)
    private MhAgence codeA;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeDmConv")
    private Collection<MhConventionAgc> mhConventionAgcCollection;

    public MhDemandeConvAgc() {
    }

    public MhDemandeConvAgc(String codeDmConv) {
        this.codeDmConv = codeDmConv;
    }

    public MhDemandeConvAgc(String codeDmConv, Date dateDmConv, Date dateD, Date dateF, int prixEngagement) {
        this.codeDmConv = codeDmConv;
        this.dateDmConv = dateDmConv;
        this.dateD = dateD;
        this.dateF = dateF;
        this.prixEngagement = prixEngagement;
    }

    public String getCodeDmConv() {
        return codeDmConv;
    }

    public void setCodeDmConv(String codeDmConv) {
        this.codeDmConv = codeDmConv;
    }

    public Date getDateDmConv() {
        return dateDmConv;
    }

    public void setDateDmConv(Date dateDmConv) {
        this.dateDmConv = dateDmConv;
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

    public int getPrixEngagement() {
        return prixEngagement;
    }

    public void setPrixEngagement(int prixEngagement) {
        this.prixEngagement = prixEngagement;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
    }

    public MhHotel getCodeH() {
        return codeH;
    }

    public void setCodeH(MhHotel codeH) {
        this.codeH = codeH;
    }

    @XmlTransient
    public Collection<MhConventionAgc> getMhConventionAgcCollection() {
        return mhConventionAgcCollection;
    }

    public void setMhConventionAgcCollection(Collection<MhConventionAgc> mhConventionAgcCollection) {
        this.mhConventionAgcCollection = mhConventionAgcCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeDmConv != null ? codeDmConv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhDemandeConvAgc)) {
            return false;
        }
        MhDemandeConvAgc other = (MhDemandeConvAgc) object;
        if ((this.codeDmConv == null && other.codeDmConv != null) || (this.codeDmConv != null && !this.codeDmConv.equals(other.codeDmConv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhDemandeConvAgc[ codeDmConv=" + codeDmConv + " ]";
    }

    public String getTypeConv() {
        return typeConv;
    }

    public void setTypeConv(String typeConv) {
        this.typeConv = typeConv;
    }

    public Integer getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(Integer nbChambre) {
        this.nbChambre = nbChambre;
    }

    public Boolean getDette() {
        return dette;
    }

    public void setDette(Boolean dette) {
        this.dette = dette;
    }
    
}
