/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.particulier;

import bean.MhCltSChBean;
import fct.str;
import fct.uploadedFiles;
import java.io.FileNotFoundException;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.MhCltSCh;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class configurationController {
    
    
   private String typePid;
   private String npid;
   private Date pcDate;
   private String pcLieu;
   private String nomPrenom;
   private String lieuNaissance;
   private Date naissanceDt;
   private String nationalite;
   private String adresse;
   private String numMob;
   private String mail;
   
   private String ancPsw;
   private String psw;

    public String getTypePid() {
        return typePid;
    }

    public void setTypePid(String typePid) {
        this.typePid = typePid;
    }

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }

    public Date getPcDate() {
        return pcDate;
    }

    public void setPcDate(Date pcDate) {
        this.pcDate = pcDate;
    }

    public String getPcLieu() {
        return pcLieu;
    }

    public void setPcLieu(String pcLieu) {
        this.pcLieu = pcLieu;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Date getNaissanceDt() {
        return naissanceDt;
    }

    public void setNaissanceDt(Date naissanceDt) {
        this.naissanceDt = naissanceDt;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumMob() {
        return numMob;
    }

    public void setNumMob(String numMob) {
        this.numMob = numMob;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAncPsw() {
        return ancPsw;
    }

    public void setAncPsw(String ancPsw) {
        this.ancPsw = ancPsw;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
   
//-----------------------------------------
    
   @PostConstruct 
    public void Init(){
        
        MhCltSCh clt = beanClt.clt();
        
          typePid = clt.getPieceId();
          npid = clt.getNpid();
          pcDate = clt.getDateP();
          pcLieu = clt.getLieuN();
          nomPrenom = clt.getNomPrenom();
          lieuNaissance = clt.getLieuN();
          naissanceDt = clt.getDateN();
          nationalite = clt.getNationalite();
          adresse = clt.getAdresse();
          numMob = clt.getNum_tel();
          mail = clt.getMail();
    }
    
    
   @EJB
    MhCltSChBean beanClt;  
    
    public void updateInfo(){
         FacesContext context = FacesContext.getCurrentInstance();
         if(beanClt.UPDATE(rempInfo())){
                       context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modification effectuer avec succés sur le client: "+nomPrenom, ""));
                    }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors de mise a jour du clients", ""));
                    }
    }
   
    
    private MhCltSCh rempInfo(){
        MhCltSCh clt = new MhCltSCh();
        str STR = new str();
        clt.setNomPrenom(STR.nameForm(nomPrenom));
                            clt.setDateN(naissanceDt);
                            clt.setLieuN(lieuNaissance);
                            clt.setNationalite(nationalite);
                            clt.setPieceId(typePid);
                            clt.setNpid(npid);
                            clt.setDateP(pcDate);
                            clt.setLienP(lieuNaissance);
                            clt.setAdresse(adresse);
                            clt.setNum_tel(numMob);
                            clt.setMail(mail);
        return clt;
    }
    
    
    public void updatePsw(){
         FacesContext context = FacesContext.getCurrentInstance();
        if(!isAncPswCorrect(ancPsw)){
              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention", "Ancien mot de passe Incorrect !!"));
        }else{
            if(beanClt.updatePsw(beanClt.clt(), psw)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Information", "Modification mot de passe effectuée."));
            }else{
                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur", "Erreur de communication !!"));
            }
        }
    }
    
    
    private boolean isAncPswCorrect(String ancPsw){
        MhCltSCh clt = beanClt.clt();
        System.out.println(clt.getNpid());
        return ancPsw.equals(clt.getPsw());
    }
    
      UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
    
        
        
     public void SingleFileUploadClt(FileUploadEvent event) throws FileNotFoundException {
          file = event.getFile();
          uploadedFiles upf = new uploadedFiles();
            upf.idPhotoReceiver(file, beanClt.clt().getNpid());
            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size:"+event.getFile().getSize());
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
