/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MhChambreBean;
import bean.MhCltSChBean;
import bean.MhDemandeCltBean;
import bean.MhHotelBean;
import bean.agc.MhPublicationBean;
import bean.agc.mhAgenceBean;
import bean.particulier.MhPartPublicationBean;
import bean.particulier.MhPubPartInscritBean;
import fct.codification;
import fct.dt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.MhChambre;
import model.MhCltSCh;
import model.MhDemandeClt;
import model.MhHotel;
import model.agc.MhAgcPublicationInscrit;
import model.particulier.MhPartPubInscrit;

@ManagedBean
@ViewScoped
public class connexionCltController {
    
    private String code_h;
    
    private String code_a;
    private String code_pub;
    
    
    private String code_npid;
    private String code_pubPart;
    
    
    private String npid;
    private String psw;
    
    private String code_dm;
    private Date du;
    private Date au;
    
    private String typeChambre;
    private Date date_dm;
    
    private String commentaire;
    
    private int nbPlace;
    private MhHotel htl;
    



    
    

    public String getCode_a() {
        return code_a;
    }
    public void setCode_a(String code_a) {
        this.code_a = code_a;
    }
    public String getCode_pub() {
        return code_pub;
    }

    public void setCode_pub(String code_pub) {
        this.code_pub = code_pub;
    }

    public String getCode_npid() {
        return code_npid;
    }

    public void setCode_npid(String code_npid) {
        this.code_npid = code_npid;
    }

    public String getCode_pubPart() {
        return code_pubPart;
    }

    public void setCode_pubPart(String code_pubPart) {
        this.code_pubPart = code_pubPart;
    }

    
    
    
    
        
    public String getCode_h() {
        return code_h;
    }

    public void setCode_h(String code_h) {
        this.code_h = code_h;
    }

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public MhHotel getHtl() {
        return htl;
    }

    public void setHtl(MhHotel htl) {
        this.htl = htl;
    }

    public String getCode_dm() {
        return code_dm;
    }

    public void setCode_dm(String code_dm) {
        this.code_dm = code_dm;
    }

    public Date getDu() {
        return du;
    }

    public void setDu(Date du) {
        this.du = du;
    }

    public Date getAu() {
        return au;
    }

    public void setAu(Date au) {
        this.au = au;
    }

    public String getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(String typeChambre) {
        this.typeChambre = typeChambre;
    }

    public Date getDate_dm() {
        return date_dm;
    }

    public void setDate_dm(Date date_dm) {
        this.date_dm = date_dm;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
    
    
    @EJB
    MhChambreBean beanCh;
    private List<MhChambre> listChambre;

    public List<MhChambre> getListChambre() {
        listChambre = beanCh.listChambre(beanHtl.findByCodeH(leCode));
                
        
        List<MhChambre> listRst = new ArrayList<MhChambre>(); 
        boolean ok = true;
        for(MhChambre c:listChambre){
            ok = true;
            
             if(c.getTypeCh().equals("Senior") && c.getNbPlace() == 1) c.setTypeCh("Single");
                if(c.getTypeCh().equals("Senior") && c.getNbPlace() == 2) c.setTypeCh("Double");
                  if(c.getTypeCh().equals("Senior") && c.getNbPlace() == 3) c.setTypeCh("Triple");
            
            for(MhChambre l:listRst){
                if(l.getTypeCh().equals(c.getTypeCh())){
                    ok = false;
                    break;
                }
            }
            if(ok){
                listRst.add(c);
            }
        }
        
        return listRst;
    }

    public void setListChambre(List<MhChambre> listChambre) {
        this.listChambre = listChambre;
    }
    
    
    
    private String leCode;
    private String leCodeAgc;
    private String codePub;
    
    private String leCodeNpid;
    private String codePubPart;

    @PostConstruct
    public void init(){
        dt sdt = new dt();
        du = new Date();
        au = sdt.longToDate(sdt.addDay(du, 1));
        leCode = getCode();
        leCodeAgc = getCodeAgc();
        codePub = getCodePub();
        leCodeNpid = getCodeNpid();
        codePubPart = getCodePubPart();
    }

    @EJB
    MhHotelBean beanHtl;

    @EJB
    MhCltSChBean beanClt;
    
    private boolean verifClt(String npid, String psw){
        MhCltSCh  clt = beanClt.getClientByNpid(npid);
         if(clt == null) return false;
         if(clt.getPsw() == null) return false;
        
         return clt.getPsw().equals(psw);
    }
    
    public String getCode(){
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdH");
    }
    public String getCodeAgc(){
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdA");
    }
    public String  getCodePub(){
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdPub");
    }
    
    
    public String getCodeNpid(){
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("npidp");
    }
  
    public String  getCodePubPart(){
            return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdPubPart");
    }  
        
     @EJB
     MhDemandeCltBean bean;
    public void confirme(){
       FacesContext context = FacesContext.getCurrentInstance();
              
       code_h = leCode;
       dt sdt = new dt();
       codification COD = new codification();
     if(!verifClt(npid, psw)){
                   context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les informations d'identification svp !!", ""));
        }else if(!sdt.isValidDateRes(du, au, new Date())){
                   context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduite svp !!", ""));
        }else{
           
            code_dm = COD.cd_structure(npid + new Date() +code_h);
            date_dm = new Date();
            
            MhDemandeClt demande = new MhDemandeClt();
            demande.setCodeDmClt(code_dm);
            htl = beanHtl.findByCodeH(code_h);
            demande.setCodeH(htl);
            demande.setNpid(beanClt.getClientByNpid(npid));
            demande.setDateDm(date_dm);
            demande.setTypeChambre(typeChambre);
            demande.setDu(du);
            demande.setAu(au);
            demande.setCommentaire(commentaire.replaceAll("'", ""));
            demande.setNbPlace(nbPlace);
            
            if(bean.insert(demande)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Demande de réservation envoyer avec succès.", ""));
            }else{
                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur sur la demande de réservation.", ""));
            }
            
        }  
                     
    }
    
//------------------------------------
    
      public void confirmeCltConnected(){
       FacesContext context = FacesContext.getCurrentInstance();
              
       code_h = leCode;
       dt sdt = new dt();
       codification COD = new codification();
   
       String npid01 = beanClt.clt().getNpid();
       
         if(!sdt.isValidDateRes(du, au, new Date())){
                   context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduite svp !!", ""));
        }else{
           
            code_dm = COD.cd_structure(npid01 + new Date() +code_h);
            date_dm = new Date();
            
            MhDemandeClt demande = new MhDemandeClt();
            demande.setCodeDmClt(code_dm);
            htl = beanHtl.findByCodeH(code_h);
            demande.setCodeH(htl);
            demande.setNpid(beanClt.getClientByNpid(npid01));
            demande.setDateDm(date_dm);
            demande.setTypeChambre(typeChambre);
            demande.setDu(du);
            demande.setAu(au);
            demande.setCommentaire(commentaire.replaceAll("'", ""));
            demande.setNbPlace(nbPlace);
            
            if(bean.insert(demande)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Demande de réservation envoyer avec succès.", ""));
            }else{
                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur sur la demande de réservation.", ""));
            }
            
        }  
                     
    }
//------------------------
      
    @EJB
    MhPublicationBean beanPub;
    
    @EJB
    mhAgenceBean beanAgc;
     public void confirmeAgc(){
         FacesContext context = FacesContext.getCurrentInstance();

         MhAgcPublicationInscrit inscPub = new MhAgcPublicationInscrit();
         List<MhAgcPublicationInscrit> cdp = beanPub.getInscByCodePub(beanPub.getByCodePub(codePub));
         MhAgcPublicationInscrit insc = beanPub.getInscByCodeInsc(npid+"-"+codePub);
         
                
             if(cdp.contains(insc)){
                  context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Inscription déja effectuer !!", ""));
             }else{
                    inscPub.setCodePub(beanPub.getByCodePub(codePub));
                    inscPub.setNpid(beanClt.getClientByNpid(npid));
                    inscPub.setCodeA(beanAgc.getByCodeA(leCodeAgc));
                    inscPub.setDateInsc(new Date());
                    inscPub.setNumInsc(npid+"-"+codePub);
                 if(beanPub.insertInsc(inscPub)){
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Inscription effectuer avec succès.", ""));
                 }else{
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'inscription.", ""));
                 }
             }
                
         
         
         
     }
     
     
  
     
      public void confirmeAgcConnected(String codeAgence, String codePublicite){
         FacesContext context = FacesContext.getCurrentInstance();
         String npid_1 = beanClt.clt().getNpid();
         System.out.println("code Agence: "+codeAgence);
         MhAgcPublicationInscrit inscPub = new MhAgcPublicationInscrit();
         List<MhAgcPublicationInscrit> cdp = beanPub.getInscByCodePub(beanPub.getByCodePub(codePublicite));
         MhAgcPublicationInscrit insc = beanPub.getInscByCodeInsc(npid_1+"-"+codePublicite);
         
                
             if(cdp.contains(insc)){
                  context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Inscription déja effectuer !!", ""));
             }else{
                    inscPub.setCodePub(beanPub.getByCodePub(codePublicite));
                    inscPub.setNpid(beanClt.getClientByNpid(npid_1));
                    inscPub.setCodeA(beanAgc.getByCodeA(codeAgence));
                    inscPub.setDateInsc(new Date());
                    inscPub.setNumInsc(npid_1+"-"+codePublicite);
                 if(beanPub.insertInsc(inscPub)){
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Inscription effectuer avec succès.", ""));
                 }else{
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'inscription.", ""));
                 }
             }

     }
      
      
      
     
//------------------------------------------------     
     
    @EJB
    MhPartPublicationBean beanPubP;
      
    @EJB 
    MhPubPartInscritBean beanPubPart; 
      
     public void confirmePart(){
         FacesContext context = FacesContext.getCurrentInstance();

         MhPartPubInscrit inscPub = new MhPartPubInscrit();
         List<MhPartPubInscrit> cdp = beanPubPart.getListPubInscByCodePub(beanPubP.getByCodePub(codePubPart));
         MhPartPubInscrit insc = beanPubPart.getInscByNumInsc(npid+"-"+leCodeNpid+"-"+codePubPart);
         
             if(cdp.contains(insc)){
                  context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Inscription déja effectuer !!", ""));
             }else{
                 
                 
                 
                    inscPub.setCodePubPart(beanPubP.getByCodePub(codePubPart));
                    inscPub.setNpidins(beanClt.getClientByNpid(npid));
                    inscPub.setNpidp(beanClt.getClientByNpid(leCodeNpid));
                    inscPub.setDateInscription(new Date());
                    inscPub.setNumInscription(npid+"-"+leCodeNpid+"-"+codePubPart);
                 if(beanPubPart.insertInscPart(inscPub)){
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Inscription effectuer avec succès.", ""));
                 }else{
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'inscription.", ""));
                 }
             }

     }
      
       
    
  
     
}
