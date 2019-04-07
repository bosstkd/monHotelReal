/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;

import bean.agc.MhPublicationBean;
import bean.agc.mhAgenceBean;
import fct.codification;
import fct.dt;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.agc.MhAgcPublication;
import model.agc.MhAgcPublicationInscrit;
import org.primefaces.event.SelectEvent;
import org.apache.commons.io.FileUtils;


@ManagedBean
@SessionScoped
public class publicationController {
    private String titre;
    private Date dateD;
    private Date dateF;
    private String detail;
    private boolean visible;

    private MhAgcPublication pubSelected;

    private List<MhAgcPublication> pubRealiser;

    public List<MhAgcPublication> getPubRealiser() {
        pubRealiser = bean.pubList();
        return pubRealiser;
    }

    public void setPubRealiser(List<MhAgcPublication> pubRealiser) {
        this.pubRealiser = pubRealiser;
    }
    
    
    
    
    
    private List<MhAgcPublicationInscrit> pubInscRealiser;

    public List<MhAgcPublicationInscrit> getPubInscRealiser() {
        pubInscRealiser = bean.pubListInscrit();
        return pubInscRealiser;
    }

    public void setPubInscRealiser(List<MhAgcPublicationInscrit> pubInscRealiser) {
        this.pubInscRealiser = pubInscRealiser;
    }
    
    
    private List<MhAgcPublicationInscrit> pubInscPart;

    public List<MhAgcPublicationInscrit> getPubInscPart() {
        pubInscPart = bean.pubListPart();
        return pubInscPart;
    }

    public void setPubInscPart(List<MhAgcPublicationInscrit> pubInscPart) {
        this.pubInscPart = pubInscPart;
    }

    
    
    
    public MhAgcPublication getPubSelected() {
        return pubSelected;
    }

    public void setPubSelected(MhAgcPublication pubSelected) {
        this.pubSelected = pubSelected;
    }
    
    
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    @PostConstruct
    public void init(){
        visible = true;
        dt DT = new dt();
        
        dateD = new Date();
        dateF = DT.longToDate(DT.addDay(dateD, 1));
    }
    
    @EJB
    mhAgenceBean beanAgc;
    
    @EJB
    MhPublicationBean bean;
    public void publie(){
        MhAgcPublication pub = new MhAgcPublication();
         FacesContext context = FacesContext.getCurrentInstance();
         codification COD = new codification();
         dt DT = new dt();
        if(DT.isValidDateRes(dateD, dateF, new Date())){
            pub.setCodePub(COD.cd_prs(titre+beanAgc.agc()+new Date()));
            pub.setCodeA(beanAgc.agc());
            pub.setTitre(titre);
            pub.setDateD(dateD);
            pub.setDateF(dateF);
            pub.setValide(visible);
            pub.setDetail(detail);
            pub.setDatePub(new Date());

            if(bean.insert(pub)){
                 context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Publication effectuer.", ""));
                }else{
                 context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication lors de la publication.", ""));
            }
        }else{
                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates svp !!", ""));
        }
         
        
        
    }
    
    public void modifier(){
        MhAgcPublication pub = pubSelected;
         FacesContext context = FacesContext.getCurrentInstance();
         dt DT = new dt();
        if(DT.isValidDateRes(dateF, dateD)){
            pub.setTitre(titre);
            pub.setDateD(dateD);
            pub.setDateF(dateF);
            pub.setValide(visible);
            pub.setDetail(detail);

            if(bean.update(pub)){
                 context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Publication effectuer.", ""));
                }else{
                 context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication lors de la publication.", ""));
            }
        }else{
                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates svp !!", ""));
        }
    }
    
    public void onRowSelect(SelectEvent event) {
        String n_p = ((MhAgcPublication) event.getObject()).getCodePub();
        pubSelected = bean.getByCodePub(n_p);
        
        titre = pubSelected.getTitre();
        detail = pubSelected.getDetail();
        dateD = pubSelected.getDateD();
        dateF = pubSelected.getDateF();
        visible = pubSelected.getValide();
        
    }
    
    public void annule(String codePub){
         FacesContext context = FacesContext.getCurrentInstance();
         System.out.println(codePub);
        if(bean.delete(codePub)){
            
             try {
                 folderDelete(codePub);
             } catch (IOException ex) {
                 Logger.getLogger(publicationController.class.getName()).log(Level.SEVERE, null, ex);
             }
             context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Publication Annuler.", ""));
          }else{
             context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération non efféctuée", "Veuillez supprimer tout les inscrits sur la promotion svp."));
        }
    }
    
    
    public void folderDelete(String codePub) throws IOException{
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\publications\\"+codePub);
           
            File f = new File(relativeWebPath);
            FileUtils.deleteDirectory(f);
    }
    
    
    
    public void annuleInsc(String numInsc){
         FacesContext context = FacesContext.getCurrentInstance();
        
        if(bean.deleteInsc(numInsc)){
             context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Publication Annuler.", ""));
          }else{
             context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération non efféctuée", "Veuillez supprimer tout les inscrits sur la promotion svp."));
        }
    }
    
   
}
