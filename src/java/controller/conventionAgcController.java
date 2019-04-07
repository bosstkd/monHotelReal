/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.agc.MhAgence;
import model.agc.MhConventionAgc;
import model.agc.MhDemandeConvAgc;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class conventionAgcController {
    
    
    private MhAgence agc;

    public MhAgence getAgc() {
        return agc;
    }

    public void setAgc(MhAgence agc) {
        this.agc = agc;
    }
    
    
    private String codeA;
    private String codeConv;
    private Date dateConv;
    private Date dateD;
    private Date dateF;
    private int quotion;
    private int reduction;
    private int prixEngagement;
    private String codeDmConv;
    private String typeConv;
    private String typeConvForRech;
    private boolean cautionVisiblity;
   
    private String codeConvPut;

    private boolean dette;

    public boolean isDette() {
        return dette;
    }

    public void setDette(boolean dette) {
        this.dette = dette;
    }
    
    
    public boolean isCautionVisiblity() {
        return cautionVisiblity;
    }

    public void setCautionVisiblity(boolean cautionVisiblity) {
        this.cautionVisiblity = cautionVisiblity;
    }

    private MhConventionAgc convSelected;

    public MhConventionAgc getConvSelected() {
        return convSelected;
    }

    public void setConvSelected(MhConventionAgc convSelected) {
        this.convSelected = convSelected;
    }
    
    
    private String conSelectedCodeStr;

    public String getConSelectedCodeStr() {
        return conSelectedCodeStr;
    }

    public void setConSelectedCodeStr(String conSelectedCodeStr) {
        this.conSelectedCodeStr = conSelectedCodeStr;
    }
    
    
    
    public String getTypeConvForRech() {
        return typeConvForRech;
    }

    public void setTypeConvForRech(String typeConvForRech) {
        this.typeConvForRech = typeConvForRech;
    }

    
    
    
    public String getCodeConvPut() {
        return codeConvPut;
    }

    public void setCodeConvPut(String codeConvPut) {
        this.codeConvPut = codeConvPut;
    }
    
    
      
    
    
    
    public String getTypeConv() {
        return typeConv;
    }

    public void setTypeConv(String typeConv) {
        this.typeConv = typeConv;
    }
    
    

    public String getCodeA() {
        return codeA;
    }

    public void setCodeA(String codeA) {
        this.codeA = codeA;
    }

    
    
    
    public String getCodeConv() {
        return codeConv;
    }

    public void setCodeConv(String codeConv) {
        this.codeConv = codeConv;
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

    public int getQuotion() {
        return quotion;
    }

    public void setQuotion(int quotion) {
        this.quotion = quotion;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public int getPrixEngagement() {
        return prixEngagement;
    }

    public void setPrixEngagement(int prixEngagement) {
        this.prixEngagement = prixEngagement;
    }

    public String getCodeDmConv() {
        return codeDmConv;
    }

    public void setCodeDmConv(String codeDmConv) {
        this.codeDmConv = codeDmConv;
    }
    
    public void selectedConvention(SelectEvent event){
        String CA = ((MhAgence) event.getObject()).getCodeA();
        codeA = CA;
    }
    
    public void selectConv2(SelectEvent event){
        String CC = ((MhConventionAgc) event.getObject()).getCodeConvAgc();
        conSelectedCodeStr = CC;
        
        MhConventionAgc conv = bean.getByCodeConv(conSelectedCodeStr);
        codeA = conv.getCodeA().getCodeA();
        try {
            codeDmConv = conv.getCodeDmConv().getCodeDmConv();
            if(codeDmConv == null)codeDmConv = "";
        } catch (Exception e) {
            codeDmConv = "";
        }
        dateD = conv.getDateD();
        dateF = conv.getDateF();
        
        prixEngagement = conv.getPrixEngagement();
        typeConv = conv.getTypeConv();
        
        reduction = conv.getPrcReduction();
        quotion = conv.getPrcQuotionAnnule();
        
        if(typeConv.equals("chLib"))
            cautionVisiblity = false;
        else
            cautionVisiblity = true;
     
        
        
    }
    
     public void selectedConvention1(SelectEvent event){
        String CC = ((MhConventionAgc) event.getObject()).getCodeConvAgc();
        codeConvPut = CC;
    }
    
    @EJB
    mhAgenceBean beanAgc;
    
    @EJB
    MhHotelBean beanHtl;
    
    @EJB
    MhDemandeConvAgcBean beanDmAgc;
    
    @EJB
    MhConventionAgcBean bean;
    
    List<MhConventionAgc> listConvAgcHtl;

    public List<MhConventionAgc> getListConvAgcHtl() {
        listConvAgcHtl = bean.listConvAgcHtl(typeConvForRech);
        return listConvAgcHtl;
    }

    public void setListConvAgcHtl(List<MhConventionAgc> listConvAgcHtl) {
        this.listConvAgcHtl = listConvAgcHtl;
    }
    
     List<MhConventionAgc> listConvAgcHtlRsv;

    public List<MhConventionAgc> getListConvAgcHtlRsv() {
        if(!typeConvForRech.equals("chLib"))
         listConvAgcHtlRsv = bean.listConvAgcHtl(typeConvForRech);
         return listConvAgcHtlRsv;
    }

    public void setListConvAgcHtlRsv(List<MhConventionAgc> listConvAgcHtlRsv) {
        this.listConvAgcHtlRsv = listConvAgcHtlRsv;
    }

   
    

    @PostConstruct
    public void init(){
        typeConv = "chLib";
        typeConvForRech = "chLib";
        cautionVisiblity = false;
    }
    
    
    
   public void supprimer(String codeConvAgc){
              FacesContext context = FacesContext.getCurrentInstance();

       if(bean.delete(codeConvAgc)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Convention supprimer avec succès.", ""));
       }else{
                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur:", "Erreur de suppression de convention."));
       }
   } 
    
   public void confirme(){
     
       dt DT = new dt();
       FacesContext context = FacesContext.getCurrentInstance();

       codification COD = new codification();
      
       codeConv = COD.cd_convention(beanHtl.htl().getCodeH(), codeA+typeConv);
       
       MhDemandeConvAgc demande = beanDmAgc.getByCodeDm(codeDmConv);
       if(demande == null && codeDmConv.length() > 0){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier le code de demande de convention svp !!", ""));
       }else if(!DT.isValidDateRes(dateD, dateF, new Date())){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates entrées svp !!", ""));
       }else if(bean.isConventionExist(codeConv)){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention !!", "Convention déja existante."));
       }else if(prixEngagement < 5000 && !dette){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention !!", "Veuillez indiquer un prix raisonnalble svp!!"));
       }else{
             MhConventionAgc conv = new MhConventionAgc();
                conv.setCodeA(beanAgc.getByCodeA(codeA));
                conv.setCodeH(beanHtl.htl());
                conv.setCodeConvAgc(codeConv);
                conv.setDateConv(dateConv);
                conv.setDateD(dateD);
                conv.setDateF(dateF);
                conv.setCodeDmConv(demande);
                conv.setDateConv(new Date());
                conv.setPrcReduction(reduction);
                conv.setPrcQuotionAnnule(quotion);
                conv.setPrixEngagement(prixEngagement);
                conv.setTypeConv(typeConv);
                conv.setReservationACouvert(false);
                conv.setDette(dette);
                
                if(bean.insert(conv)){
                                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Convention créer avec succès.", ""));
                }else{
                                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur:", "Erreur de création de convention."));
                }
                
       }
   }
   
   public void modif(){
        MhConventionAgc conv = bean.getByCodeConv(conSelectedCodeStr);
        FacesContext context = FacesContext.getCurrentInstance();
        dt DT = new dt();

       MhDemandeConvAgc demande = beanDmAgc.getByCodeDm(codeDmConv);
       if(demande == null && codeDmConv.length() > 0){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier le code de demande de convention svp !!", ""));
       }else if(!DT.isValidDateRes(dateD, dateF, new Date())){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates entrées svp !!", ""));
       }else if(prixEngagement < 5000 && !dette){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Attention !!", "Veuillez indiquer un prix raisonnalble svp!!"));
       }else{
             conv.setDateD(dateD);
                conv.setDateF(dateF);
                conv.setTypeConv(typeConv);
                conv.setPrcQuotionAnnule(quotion);
                conv.setPrcReduction(reduction);
                conv.setPrixEngagement(prixEngagement);
                conv.setDette(dette);
           if(bean.updateConvAgc(conv)){
            context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Mise a jour de la convention effectuée avec succés.", ""));
           }else{
            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur:", "Erreur de modification de convention."));
           }
           
       }
        
   }
   
   public void basculer(MhDemandeConvAgc demande){
       codeA = demande.getCodeA().getCodeA();
       codeDmConv = demande.getCodeDmConv();
       dateD = demande.getDateD();
       dateF = demande.getDateF();
       prixEngagement = demande.getPrixEngagement();
       typeConv = demande.getTypeConv();
       dette = demande.getDette();
   }
   
   public void typeConvChange(){
          if(typeConv.equals("chLib"))
              cautionVisiblity = false;
          else{
              quotion = 0;
              cautionVisiblity = true;
          } 
           
   }
   
   public void prixZero(){
       if(dette){
           prixEngagement = 0;
           typeConv = "chLib";
           cautionVisiblity = false;
       }
   }
    
}
