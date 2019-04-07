package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import model.agc.MhAgcPublicationInscrit;
import model.particulier.MhPartPubInscrit;
import model.particulier.MhPubParticulier;


@Entity
@Table(name = "mh_clt_s_ch", catalog = "monHotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"n_p_id"})})

public class MhCltSCh implements Serializable{

    @OneToMany(mappedBy = "npidins")
    private Collection<MhPartPubInscrit> mhPartPubInscritCollection;
    @OneToMany(mappedBy = "npidp")
    private Collection<MhPartPubInscrit> mhPartPubInscritCollection1;

    @OneToMany(mappedBy = "nPId")
    private Collection<MhPubParticulier> mhPubParticulierCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "npid")
    private Collection<MhAgcPublicationInscrit> mhAgcPublicationInscritCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "npid")
    private Collection<MhDemandeClt> mhDemandeCltCollection;

    @Size(max = 150)
    @Column(name = "psw", length = 150)
    private String psw;

    @Size(max = 110)
    @Column(name = "mail", length = 110)
    private String mail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "npid")
    private Collection<MhClientSdem> mhClientSdemCollection;

    @JoinColumn(name = "code_c", referencedColumnName = "code_c")
    @ManyToOne
    private MhCltFct codeC;

  

    @Column(name = "date_n")
    @Temporal(TemporalType.DATE)
    private Date dateN;
    @Column(name = "date_p")
    @Temporal(TemporalType.DATE)
    private Date dateP;

   
    @Size(max = 500)
    @Column(name = "raison_ln", length = 500)
    private String raisonLn;


    @Id
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "n_p_id", nullable = false, length = 200)
    private String npid;
    
     @Size(max = 200)
    @Column(name = "nom_prenom", length = 200)
    private String nomPrenom;
 
    @Size(max = 200)
    @Column(name = "lieu_n", length = 200)
    private String lieuN;
    
    @Size(max = 50)
    @Column(name = "nationalite", length = 50)
    private String nationalite;
    
    @Size(max = 50)
    @Column(name = "p_id", length = 50)
    private String pieceId;
    
    
    @Size(max = 200)
    @Column(name = "lien_p", length = 200)
    private String lienP;
    
    @Size(max = 4000)
    @Column(name = "adresse", length = 4000)
    private String adresse;
    
    @Size(max = 30)
    @Column(name = "num_tel", length = 30)
    private String num_tel;

    
    @Column(name = "liste_noir")
    private Boolean listeNoir;

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }

    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pieceId) {
        this.pieceId = pieceId;
    }

   

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

   

  

    public String getLieuN() {
        return lieuN;
    }

    public void setLieuN(String lieuN) {
        this.lieuN = lieuN;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }


 

    public String getLienP() {
        return lienP;
    }

    public void setLienP(String lienP) {
        this.lienP = lienP;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }



    public Boolean getListeNoir() {
        return listeNoir;
    }

    public void setListeNoir(Boolean listeNoir) {
        this.listeNoir = listeNoir;
    }

    public String getRaisonLn() {
        return raisonLn;
    }

    public void setRaisonLn(String raisonLn) {
        this.raisonLn = raisonLn;
    }

    public MhCltSCh() {
    }

    public Date getDateN() {
        return dateN;
    }

    public void setDateN(Date dateN) {
        this.dateN = dateN;
    }

    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }


    public MhCltFct getCodeC() {
        return codeC;
    }

    public void setCodeC(MhCltFct codeC) {
        this.codeC = codeC;
    }

    @XmlTransient
    public Collection<MhClientSdem> getMhClientSdemCollection() {
        return mhClientSdemCollection;
    }

    public void setMhClientSdemCollection(Collection<MhClientSdem> mhClientSdemCollection) {
        this.mhClientSdemCollection = mhClientSdemCollection;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @XmlTransient
    public Collection<MhDemandeClt> getMhDemandeCltCollection() {
        return mhDemandeCltCollection;
    }

    public void setMhDemandeCltCollection(Collection<MhDemandeClt> mhDemandeCltCollection) {
        this.mhDemandeCltCollection = mhDemandeCltCollection;
    }

    @XmlTransient
    public Collection<MhAgcPublicationInscrit> getMhAgcPublicationInscritCollection() {
        return mhAgcPublicationInscritCollection;
    }

    public void setMhAgcPublicationInscritCollection(Collection<MhAgcPublicationInscrit> mhAgcPublicationInscritCollection) {
        this.mhAgcPublicationInscritCollection = mhAgcPublicationInscritCollection;
    }

    @XmlTransient
    public Collection<MhPubParticulier> getMhPubParticulierCollection() {
        return mhPubParticulierCollection;
    }

    public void setMhPubParticulierCollection(Collection<MhPubParticulier> mhPubParticulierCollection) {
        this.mhPubParticulierCollection = mhPubParticulierCollection;
    }

    @XmlTransient
    public Collection<MhPartPubInscrit> getMhPartPubInscritCollection() {
        return mhPartPubInscritCollection;
    }

    public void setMhPartPubInscritCollection(Collection<MhPartPubInscrit> mhPartPubInscritCollection) {
        this.mhPartPubInscritCollection = mhPartPubInscritCollection;
    }

    @XmlTransient
    public Collection<MhPartPubInscrit> getMhPartPubInscritCollection1() {
        return mhPartPubInscritCollection1;
    }

    public void setMhPartPubInscritCollection1(Collection<MhPartPubInscrit> mhPartPubInscritCollection1) {
        this.mhPartPubInscritCollection1 = mhPartPubInscritCollection1;
    }
    
   
    
}
