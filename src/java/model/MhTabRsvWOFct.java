package model;

import groovy.lang.Immutable;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;


@Entity
@Immutable
@Table(name = "mhrsvvue_woutfct", catalog = "monHotel", schema = "")
public class MhTabRsvWOFct implements Serializable{
    @Id
    @Size(max = 450)
    @Column(name = "ident", length = 450)
    private String ident;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_u", precision = 12)
    private Float prixU;
    @Column(name = "versement", precision = 12)
    private Float versement;
    @Column(name = "taxe_sj", precision = 12)
    private Float taxeSj;
    @Column(name = "pension_c", precision = 12)
    private Float pensionC;
    @Column(name = "prc_pension")
    private Integer prcPension;
    @Column(name = "num_ch")
    private String numCh;
    @Column(name = "nom_prenom")
    private String nomPrenom;
    @Column(name = "date_a")
    @Temporal(TemporalType.DATE)
    private Date dtA;
    
    @Column(name = "date_d")
    @Temporal(TemporalType.DATE)
    private Date dtD;
    /*
     @EmbeddedId
     private MyKeyRscWOFct myKey;
    */
    
    @Column(name = "date_r")
    @Temporal(TemporalType.TIMESTAMP)
    private Date DateR;
    
    
    
    @Size(max = 20)
    @Column(name = "pension", length = 20)
    private String pension;
    
    @Column(name = "nb_nuitee")
    private Integer nbNuitee;
    
    
    @Column(name = "code_h", length = 250)
    private String codeH;
    
    @Column(name = "periode_ouverte")
    private Boolean periodeOuverte;

     
    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }
    
    public Date getDtA() {
        return dtA;
    }

    public void setDtA(Date dtA) {
        this.dtA = dtA;
    }

    public Date getDtD() {
        return dtD;
    }

    public void setDtD(Date dtD) {
        this.dtD = dtD;
    }

    public Date getDateR() {
        return DateR;
    }

    public void setDateR(Date DateR) {
        this.DateR = DateR;
    }


    public float getPrixU() {
        return prixU;
    }

    public void setPrixU(float prixU) {
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


    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
    }

    public Boolean getPeriodeOuverte() {
        return periodeOuverte;
    }

    public void setPeriodeOuverte(Boolean periodeOuverte) {
        this.periodeOuverte = periodeOuverte;
    }

    public MhTabRsvWOFct() {
    }

    

    public Float getVersement() {
        return versement;
    }

    public void setVersement(Float versement) {
        this.versement = versement;
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

 
  
//----------------------------------    
  @Column(name = "code_r", length = 250)
    private String codeR;

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }
//******    
    @Column(name = "npid") 
     private String npid;

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }
 //----------------------------------     

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
  
    
}
