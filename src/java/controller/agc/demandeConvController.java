/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;

import bean.agc.MhConventionAgcBean;
import bean.MhHotelBean;
import bean.agc.MhDemandeConvAgcBean;
import bean.agc.mhAgenceBean;
import fct.codification;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.agc.MhConventionAgc;
import model.agc.MhDemandeConvAgc;

@ManagedBean
@ViewScoped
public class demandeConvController {
    
    private Date dateD;
    private Date dateF;
    private int prixConv;
    private String codeH;
    private String codeA;
    
    private String codeToGet;

    private String typeConv;

    private boolean etatNbCh;
    
    private boolean dette;

    public boolean isDette() {
        return dette;
    }

    public void setDette(boolean dette) {
        this.dette = dette;
    }

    
    
    public boolean isEtatNbCh() {
        return etatNbCh;
    }

    public void setEtatNbCh(boolean etatNbCh) {
        this.etatNbCh = etatNbCh;
    }
    
    
    public String getTypeConv() {
        return typeConv;
    }

    public void setTypeConv(String typeConv) {
        this.typeConv = typeConv;
    }
    
    private int nbChambre;

    public int getNbChambre() {
        return nbChambre;
    }

    public void setNbChambre(int nbChambre) {
        this.nbChambre = nbChambre;
    }
    
    
    
    private List<MhDemandeConvAgc> listDemandeAgc;

    public List<MhDemandeConvAgc> getListDemandeAgc() {
        listDemandeAgc = bean.listDemandeConvAgc();
        return listDemandeAgc;
    }

    public void setListDemandeAgc(List<MhDemandeConvAgc> listDemandeAgc) {
        this.listDemandeAgc = listDemandeAgc;
    }
    
    
    private List<MhDemandeConvAgc> listDemandeHtl;

    public List<MhDemandeConvAgc> getListDemandeHtl() {
        listDemandeHtl = bean.listDemandeConvHtl();
        return listDemandeHtl;
    }

    public void setListDemandeHtl(List<MhDemandeConvAgc> listDemandeHtl) {
        this.listDemandeHtl = listDemandeHtl;
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

    public int getPrixConv() {
        return prixConv;
    }

    public void setPrixConv(int prixConv) {
        this.prixConv = prixConv;
    }

    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
    }

    public String getCodeA() {
        return codeA;
    }

    public void setCodeA(String codeA) {
        this.codeA = codeA;
    }
    
    
    
    
    
    @PostConstruct
    public void init(){
        dt sdt = new dt();
        dateD = new Date();
        dateF = sdt.longToDate(sdt.addDay(dateD, 1));
        typeConv = "chLib";
        etatNbCh = true;
        nbChambre = 0;
        codeToGet = getCode();
    }
    
     public String getCode(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdH");
    }
    
     
     @EJB
     mhAgenceBean beanAgc;
     
     @EJB
     MhHotelBean beanHtl;
     
     @EJB
     MhDemandeConvAgcBean bean;
    public void confirmer(){
        codeH = codeToGet;
        FacesContext context = FacesContext.getCurrentInstance();
        codification COD = new codification();
        dt DT = new dt();
        String codeDmConv = COD.cd_convention(codeH + new Date(), beanAgc.agc().getCodeA());
        if(!DT.superieur(dateD, dateF)){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduites svp !!.", ""));
        }else if(prixConv < 5000 && !dette){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez précisé un prix raisonnable svp !!.", ""));
        }else{
            MhDemandeConvAgc demande = new MhDemandeConvAgc();
            demande.setCodeA(beanAgc.agc());
            demande.setCodeH(beanHtl.findByCodeH(codeH));
            demande.setCodeDmConv(codeDmConv);
            demande.setDateDmConv(new Date());
            demande.setPrixEngagement(prixConv);
            demande.setDateD(dateD);
            demande.setDateF(dateF);
            demande.setTypeConv(typeConv);
            demande.setNbChambre(nbChambre);
            demande.setDette(dette);
            
            
            if(bean.insert(demande)){
                            context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Demande effectuée avec succès !!.", ""));
            }else{
                            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur sur la demande de convention !!.", ""));
            }
        }
        
    }
    
    @EJB
    MhConventionAgcBean beanConvAgc;
    public void annuler(String codeDemande){
          FacesContext context = FacesContext.getCurrentInstance();
  
          MhConventionAgc bca = beanConvAgc.getByCodeDmConv(bean.getByCodeDm(codeDemande));
          
          if(bca != null){
                            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Suppression impossible:", "Demande de convention consommée."));
          }else{
               if(bean.delete(codeDemande)){
                            context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Annulation de demande effectuée avec succès !!.", ""));
            }else{
                            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur sur l'annulation de la demande de convention !!.", ""));
            }
          }
          
    }
    
   public void prixZero(){
       if(dette){
           prixConv = 0;
           typeConv = "chLib";
           etatNbCh = true;
           nbChambre = 0;
       }
   }

   
    public void typeAndCh(){
        if(typeConv.equals("chLib")){
            etatNbCh = true;
            nbChambre = 0;
        }else{
            etatNbCh = false;
        }
    }
    
}
