/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.agc.MhAgence;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import model.agc.MhConventionAgc;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_reservation", catalog = "jlvljuzg_monhotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_r"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhReservation.findAll", query = "SELECT m FROM MhReservation m")
    , @NamedQuery(name = "MhReservation.findByDateA", query = "SELECT m FROM MhReservation m WHERE m.dateA = :dateA")
    , @NamedQuery(name = "MhReservation.findByDateD", query = "SELECT m FROM MhReservation m WHERE m.dateD = :dateD")
    , @NamedQuery(name = "MhReservation.findByPeriodeOuverte", query = "SELECT m FROM MhReservation m WHERE m.periodeOuverte = :periodeOuverte")
    , @NamedQuery(name = "MhReservation.findByNbNuitee", query = "SELECT m FROM MhReservation m WHERE m.nbNuitee = :nbNuitee")
    , @NamedQuery(name = "MhReservation.findByPension", query = "SELECT m FROM MhReservation m WHERE m.pension = :pension")
    , @NamedQuery(name = "MhReservation.findByPrixU", query = "SELECT m FROM MhReservation m WHERE m.prixU = :prixU")
    , @NamedQuery(name = "MhReservation.findByVersement", query = "SELECT m FROM MhReservation m WHERE m.versement = :versement")
    , @NamedQuery(name = "MhReservation.findByEtatP", query = "SELECT m FROM MhReservation m WHERE m.etatP = :etatP")
    , @NamedQuery(name = "MhReservation.findByFacturer", query = "SELECT m FROM MhReservation m WHERE m.facturer = :facturer")
    , @NamedQuery(name = "MhReservation.findByCodeR", query = "SELECT m FROM MhReservation m WHERE m.codeR = :codeR")
    , @NamedQuery(name = "MhReservation.findByDateR", query = "SELECT m FROM MhReservation m WHERE m.dateR = :dateR")})
public class MhReservation implements Serializable {

    @Column(name = "versementAgc")
    private Integer versementAgc;

    @JoinColumn(name = "code_conv_agc", referencedColumnName = "code_conv_agc")
    @ManyToOne
    private MhConventionAgc codeConvAgc;

    @JoinColumn(name = "code_dm", referencedColumnName = "code_dm")
    @ManyToOne
    private MhDemandeR codeDm;

    @JoinColumn(name = "code_a", referencedColumnName = "code_a")
    @ManyToOne
    private MhAgence codeA;
    @JoinColumn(name = "code_c", referencedColumnName = "code_c")
    @ManyToOne
    private MhCltFct codeC;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h")
    @ManyToOne
    private MhHotel codeH;
    @JoinColumn(name = "code_u", referencedColumnName = "code_u")
    @ManyToOne
    private MhCompteUserH codeU;
    @JoinColumn(name = "num_ch", referencedColumnName = "num_ch")
    @ManyToOne
    private MhChambre numCh;

    @Column(name = "taxe_sj", precision = 12)
    private Float taxeSj;
    @Column(name = "pension_c", precision = 12)
    private Float pensionC;
    @Column(name = "prc_pension")
    private Integer prcPension;
    @Column(name = "cloturer")
    private Boolean cloturer;

    private static final long serialVersionUID = 1L;
    @Column(name = "date_a")
    @Temporal(TemporalType.DATE)
    private Date dateA;
    @Column(name = "date_d")
    @Temporal(TemporalType.DATE)
    private Date dateD;
    @Column(name = "periode_ouverte")
    private Boolean periodeOuverte;
    @Column(name = "nb_nuitee")
    private Integer nbNuitee;
    @Size(max = 20)
    @Column(name = "pension", length = 20)
    private String pension;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_u", precision = 12)
    private Float prixU;
    @Column(name = "versement", precision = 12)
    private Float versement;
    @Column(name = "etat_p")
    private Boolean etatP;
    
    
    @Column(name = "facturer")
    private Boolean facturer;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "code_r", nullable = false, length = 250)
    private String codeR;
    @Column(name = "date_r")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateR;
    
    @Size(max = 100)
    @Column(name = "numCmd", length = 100)
    private String numCmd;

    public String getNumCmd() {
        return numCmd;
    }

    public void setNumCmd(String numCmd) {
        this.numCmd = numCmd;
    }

    public MhReservation() {
    }

    public MhReservation(String codeR) {
        this.codeR = codeR;
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

    public Boolean getPeriodeOuverte() {
        return periodeOuverte;
    }

    public void setPeriodeOuverte(Boolean periodeOuverte) {
        this.periodeOuverte = periodeOuverte;
    }

    public Integer getNbNuitee() {
        return nbNuitee;
    }

    public void setNbNuitee(Integer nbNuitee) {
        this.nbNuitee = nbNuitee;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public Float getPrixU() {
        return prixU;
    }

    public void setPrixU(Float prixU) {
        this.prixU = prixU;
    }

    public Float getVersement() {
        return versement;
    }

    public void setVersement(Float versement) {
        this.versement = versement;
    }

    public Boolean getEtatP() {
        return etatP;
    }

    public void setEtatP(Boolean etatP) {
        this.etatP = etatP;
    }

    public Boolean getFacturer() {
        return facturer;
    }

    public void setFacturer(Boolean facturer) {
        this.facturer = facturer;
    }

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }

    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeR != null ? codeR.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhReservation)) {
            return false;
        }
        MhReservation other = (MhReservation) object;
        if ((this.codeR == null && other.codeR != null) || (this.codeR != null && !this.codeR.equals(other.codeR))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhReservation[ codeR=" + codeR + " ]";
    }

    public Float getTaxeSj() {
        return taxeSj;
    }

    public void setTaxeSj(Float taxeSj) {
        this.taxeSj = taxeSj;
    }

    public Float getPensionC() {
        return pensionC;
    }

    public void setPensionC(Float pensionC) {
        this.pensionC = pensionC;
    }

    public Integer getPrcPension() {
        return prcPension;
    }

    public void setPrcPension(Integer prcPension) {
        this.prcPension = prcPension;
    }

    public Boolean getCloturer() {
        return cloturer;
    }

    public void setCloturer(Boolean cloturer) {
        this.cloturer = cloturer;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
    }

    public MhCltFct getCodeC() {
        return codeC;
    }

    public void setCodeC(MhCltFct codeC) {
        this.codeC = codeC;
    }

    public MhHotel getCodeH() {
        return codeH;
    }

    public void setCodeH(MhHotel codeH) {
        this.codeH = codeH;
    }

    public MhCompteUserH getCodeU() {
        return codeU;
    }

    public void setCodeU(MhCompteUserH codeU) {
        this.codeU = codeU;
    }

    public MhChambre getNumCh() {
        return numCh;
    }

    public void setNumCh(MhChambre numCh) {
        this.numCh = numCh;
    }

   

    public MhDemandeR getCodeDm() {
        return codeDm;
    }

    public void setCodeDm(MhDemandeR codeDm) {
        this.codeDm = codeDm;
    }

    public MhConventionAgc getCodeConvAgc() {
        return codeConvAgc;
    }

    public void setCodeConvAgc(MhConventionAgc codeConvAgc) {
        this.codeConvAgc = codeConvAgc;
    }

    public Integer getVersementAgc() {
        return versementAgc;
    }

    public void setVersementAgc(Integer versementAgc) {
        this.versementAgc = versementAgc;
    }
    
}
