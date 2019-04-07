/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_rsvvueglobal", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhRsvvueglobal.findAll", query = "SELECT m FROM MhRsvvueglobal m")
    , @NamedQuery(name = "MhRsvvueglobal.findByNumCh", query = "SELECT m FROM MhRsvvueglobal m WHERE m.numCh = :numCh")
    , @NamedQuery(name = "MhRsvvueglobal.findByNpid", query = "SELECT m FROM MhRsvvueglobal m WHERE m.npid = :npid")
    , @NamedQuery(name = "MhRsvvueglobal.findByNomPrenom", query = "SELECT m FROM MhRsvvueglobal m WHERE m.nomPrenom = :nomPrenom")
    , @NamedQuery(name = "MhRsvvueglobal.findByDateA", query = "SELECT m FROM MhRsvvueglobal m WHERE m.dateA = :dateA")
    , @NamedQuery(name = "MhRsvvueglobal.findByDateD", query = "SELECT m FROM MhRsvvueglobal m WHERE m.dateD = :dateD")
    , @NamedQuery(name = "MhRsvvueglobal.findByCodeR", query = "SELECT m FROM MhRsvvueglobal m WHERE m.codeR = :codeR")
    , @NamedQuery(name = "MhRsvvueglobal.findByDateR", query = "SELECT m FROM MhRsvvueglobal m WHERE m.dateR = :dateR")
    , @NamedQuery(name = "MhRsvvueglobal.findByNumFct", query = "SELECT m FROM MhRsvvueglobal m WHERE m.numFct = :numFct")
    , @NamedQuery(name = "MhRsvvueglobal.findByTypePaiement", query = "SELECT m FROM MhRsvvueglobal m WHERE m.typePaiement = :typePaiement")
    , @NamedQuery(name = "MhRsvvueglobal.findByDates", query = "SELECT m FROM MhRsvvueglobal m WHERE m.dates = :dates")
    , @NamedQuery(name = "MhRsvvueglobal.findByPrixU", query = "SELECT m FROM MhRsvvueglobal m WHERE m.prixU = :prixU")
    , @NamedQuery(name = "MhRsvvueglobal.findByPension", query = "SELECT m FROM MhRsvvueglobal m WHERE m.pension = :pension")
    , @NamedQuery(name = "MhRsvvueglobal.findByNbNuitee", query = "SELECT m FROM MhRsvvueglobal m WHERE m.nbNuitee = :nbNuitee")
    , @NamedQuery(name = "MhRsvvueglobal.findByVersement", query = "SELECT m FROM MhRsvvueglobal m WHERE m.versement = :versement")
    , @NamedQuery(name = "MhRsvvueglobal.findByCodeH", query = "SELECT m FROM MhRsvvueglobal m WHERE m.codeH = :codeH")
    , @NamedQuery(name = "MhRsvvueglobal.findByCodeC", query = "SELECT m FROM MhRsvvueglobal m WHERE m.codeC = :codeC")
    , @NamedQuery(name = "MhRsvvueglobal.findByTaxeSj", query = "SELECT m FROM MhRsvvueglobal m WHERE m.taxeSj = :taxeSj")
    , @NamedQuery(name = "MhRsvvueglobal.findByPensionC", query = "SELECT m FROM MhRsvvueglobal m WHERE m.pensionC = :pensionC")
    , @NamedQuery(name = "MhRsvvueglobal.findByPrcPension", query = "SELECT m FROM MhRsvvueglobal m WHERE m.prcPension = :prcPension")})
public class MhRsvvueglobal implements Serializable {

    @Column(name = "prcReduction")
    private Integer prcReduction;

    @Size(max = 60)
    @Column(name = "numCheque", length = 60)
    private String numCheque;

    @Size(max = 300)
    @Column(name = "num_ch", length = 300)
    private String numCh;
    
    @Column(name = "remarque")
    private String remarque;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "npid", nullable = false, length = 100)
    private String npid;
    @Size(max = 500)
    @Column(name = "nom_prenom", length = 500)
    private String nomPrenom;
    @Column(name = "date_a")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateA;
    @Column(name = "date_d")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateD;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "code_r", nullable = false, length = 250)
    private String codeR;
    @Column(name = "date_r")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateR;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "numFct", nullable = false, length = 60)
    private String numFct;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "designation", nullable = false, length = 65535)
    private String designation;
    @Size(max = 100)
    @Column(name = "type_paiement", length = 100)
    private String typePaiement;
    @Column(name = "dates")
    @Temporal(TemporalType.DATE)
    private Date dates;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_u", precision = 12)
    private Float prixU;
    @Size(max = 20)
    @Column(name = "pension", length = 20)
    private String pension;
    @Column(name = "nb_nuitee")
    private Integer nbNuitee;
    @Column(name = "versement", precision = 12)
    private Float versement;
    @Size(max = 200)
    @Column(name = "code_h", length = 200)
    private String codeH;
    @Size(max = 100)
    @Column(name = "code_c", length = 100)
    private String codeC;
    @Column(name = "taxe_sj", precision = 12)
    private Float taxeSj;
    @Column(name = "pension_c", precision = 12)
    private Float pensionC;
    @Column(name = "prc_pension")
    private Integer prcPension;

    public MhRsvvueglobal() {
    }

    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
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

    public String getNumFct() {
        return numFct;
    }

    public void setNumFct(String numFct) {
        this.numFct = numFct;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public Float getPrixU() {
        return prixU;
    }

    public void setPrixU(Float prixU) {
        this.prixU = prixU;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public Integer getNbNuitee() {
        return nbNuitee;
    }

    public void setNbNuitee(Integer nbNuitee) {
        this.nbNuitee = nbNuitee;
    }

    public Float getVersement() {
        return versement;
    }

    public void setVersement(Float versement) {
        this.versement = versement;
    }

    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
    }

    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
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

    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    public Integer getPrcReduction() {
        return prcReduction;
    }

    public void setPrcReduction(Integer prcReduction) {
        this.prcReduction = prcReduction;
    }
    
    
    
}
