/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;

import bean.MhChambreBean;
import bean.MhCltSChBean;
import bean.agc.MhConventionAgcBean;
import bean.MhHotelBean;
import bean.agc.MhReservationAgcBean;
import bean.agc.mhAgenceBean;
import fct.codification;
import fct.dt;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.MhChambre;
import model.MhCltSCh;
import model.MhHotel;
import model.MhReservation;
import model.agc.MhConventionAgc;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import statistique.bean.comportement.methode;

@ManagedBean
@ViewScoped
public class reservationAgcController extends methode{
 
    
    
    private List<String> NPID;
    private String textPID;
    private List<MhChambre> listChambre;
    
    
    
    private Date dateA;
    private Date dateD;
    private String numCh;
    private String pension;
    private String typeP;
    private int prcGain;
    private int nbPersonne;
    private float prixCh;
    private float prixEncaissement;
    private double tht;
    private double versement;
    private MhHotel codeH;
    private String cdH;
    private String codeR;
    private MhChambre chambre;
    
    private Date minDate;
    private Date maxDate;
    
    private String typeConv;
    
    private int prixEngagement;

    public int getPrixEngagement() {
        prixEngagement = beanConvAgc.getByHotel(codeH, typeConv).getPrixEngagement();
        return prixEngagement;
    }
    
    
    
    public String getTypeConv() {
        return typeConv;
    }

    public void setTypeConv(String typeConv) {
        this.typeConv = typeConv;
    }
    
    
    
    public String getTextPID() {
        return textPID;
    }

    public void setTextPID(String textPID) {
        this.textPID = textPID;
    }
    
    
     public List<String> getNPID() {
        return NPID;
    }

    public void setNPID(List<String> NPID) {
        this.NPID = NPID;
    }
    
      
     public List<MhChambre> getListChambre() {
         
        if(typeConv.equals("chLib")) 
            listChambre = beanCh.listChambreLibreEntCodeHtl(dateA, dateD, codeH);
        else{
            listChambre = beanConvAgc.listChConvRsv(codeH);
        }
        
      
             
        return listChambre;
    }

    public void setListChambre(List<MhChambre> listCalc) {
        this.listChambre = listCalc;
    }
    
     private Map<String,String> listChambreMap = new HashMap<String, String>();

    public Map<String, String> getListChambreMap() {
        return listChambreMap;
    }

    public void setListChambreMap(Map<String, String> listChambreMap) {
        this.listChambreMap = listChambreMap;
    }
     
    
    @EJB
    MhChambreBean beanCh;
    

    public float getPrixEncaissement() {
        float prx = prixCh*prcGain;
        prx = prx / 100;
        prx = prx + prixCh;
        prixEncaissement = prx;
        return prixEncaissement;
    }

    public void setPrixEncaissement(float prixEncaissement) {
        this.prixEncaissement = prixEncaissement;
    }
    
    
    public MhChambre getChambre() {
        return chambre;
    }

    public void setChambre(MhChambre chambre) {
        this.chambre = chambre;
    }

    
    
    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    
    
    
    public String getCodeR() {
        codification COD = new codification();
        String code_r = COD.cd_structure(codeH.getCodeH()+numCh+dateD+dateA+new Date());
        codeR = code_r;
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }

    
    
    
    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
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

    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public String getTypeP() {
        return typeP;
    }

    public void setTypeP(String typeP) {
        this.typeP = typeP;
    }

    public int getPrcGain() {
        return prcGain;
    }

    public void setPrcGain(int prcGain) {
        this.prcGain = prcGain;
    }

    public float getPrixCh() {
        try {
            dt DT = new dt();
            int reduction = beanConvAgc.getByHotel(codeH, typeConv).getPrcReduction();
            
            float prixChambre = chambre.getPrix();
            int prcPension = codeH.getPrcDemiPension();
            float pensionC = codeH.getPensionC();
            int nbNuitee = (int)DT.nuitee(dateA, dateD);
            double th = THT(nbNuitee, prixChambre, pensionC, prcPension, pension, 0, nbPersonne, reduction);
            
            prixCh = (float) th;
        } catch (Exception e) {
            prixCh = 0;
        }
        
        return prixCh;
    }

    public void setPrixCh(float prixCh) {
        this.prixCh = prixCh;
    }

    public double getTht() {
        return tht;
    }

    public void setTht(double tht) {
        this.tht = tht;
    }

    public double getVersement() {
        return versement;
    }

    public void setVersement(double versement) {
        this.versement = versement;
    }
    
    
    @EJB
    MhConventionAgcBean beanConvAgc;
    
    @EJB
    MhHotelBean beanHtl;
    
    @EJB
    MhCltSChBean beanCSCh;
    
    @PostConstruct
    public void init(){
        NPID = new ArrayList<String>();
        dt DT = new dt();
        cdH = getCode();
        typeConv = getTypeConvTo();
        pension = "Demi";
        MhConventionAgc convAgc = beanConvAgc.getByHotel(beanHtl.findByCodeH(cdH), typeConv);
        
        minDate = convAgc.getDateD();
        maxDate = convAgc.getDateF();
        
        if(DT.superieur(minDate, new Date())) dateA = new Date();
        else  dateA = minDate;
       
        dateD = DT.longToDate(DT.addDay(dateA, 1));
        codeH = beanHtl.findByCodeH(cdH);
        nbPersonne = 0;
        prcGain = 5;
        
        lstCh();
        chambre = beanCh.singleSelect(numCh);
        getPrixCh();
        getPrixEncaissement();
        getPrixEngagement();
    }
    
    public String getCode(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("cdH");
    }
    
    public String getTypeConvTo(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("typeConv");
    }
    
    
    
    public void lstCh(){
         List<String> laListnumCh = new ArrayList<String>();
          List<String> laListChCode = new ArrayList<String>();

         List<MhChambre> listChambreTt = getListChambre();
         boolean ok = true;
         if(listChambreTt != null)
         for(MhChambre ch:listChambreTt){
             laListChCode.add(ch.getNumCh());
             laListnumCh.add(ch.getNumchApp());
             if(ok){
                 ok = false;
                 numCh = ch.getNumCh();
             }
         }
         
         listChambreMap = new HashMap<String, String>();
         for(int i = 0; i < laListChCode.size(); i++) listChambreMap.put(laListnumCh.get(i),laListChCode.get(i));
    
    }
    
    public void infoChambre(){
        chambre = beanCh.singleSelect(numCh);
    }
    
   
   public void ajtListPid(){
   FacesContext context = FacesContext.getCurrentInstance();
    if(textPID.isEmpty()){
         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez précisé le <<N°PID Client>> svp !!", ""));
    }else{
        boolean bl = true;
        for (String value : NPID)
    	 {
             if(value.equals(textPID)){
                          bl = false;
                          context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client déja affecter a la liste !!", ""));
                          break;
             }
         }
        if(bl){
            if((Boolean) beanCSCh.singleSelectClientA("liste_noir", textPID)){
                context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Attention: *Ce client est en liste noir. Veuillez le débloquer pour lui prendre une réservation.", ""));
            }else{
                NPID.add(textPID);
                nbPersonne = NPID.size();
            }
        }
    }
}    

public void suppListPid(){
   FacesContext context = FacesContext.getCurrentInstance();
 
    if(textPID.isEmpty()){
         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez précisé le <<N°PID Client>> svp !!", ""));
    }else{
        NPID.remove(textPID);
        nbPersonne = NPID.size();
    }
} 

public void viderListPid(){
        FacesContext context = FacesContext.getCurrentInstance();
        NPID.removeAll(NPID);
        nbPersonne = NPID.size();
        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Liste remis à zéro.", ""));
} 


private boolean skip;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

 public String onFlowProcess(FlowEvent event) {
      FacesContext context = FacesContext.getCurrentInstance();
     if(nbPersonne > 0){
          if(skip) {
            skip = false;   //reset in case user goes back
            return "confirmRsv";
        }
        else {
            return event.getNewStep();
        }
     }else{
         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Liste N°PIDs ne peut pas etre vide.", ""));
         return event.getOldStep();
     }
       
    }
 
 public void OnRowSelect(SelectEvent event) {
        String n_p = ((MhCltSCh) event.getObject()).getNpid();
        textPID = n_p;
    }
    //-------------------------------------
 
 @EJB
 mhAgenceBean beanAgc;
 
 @EJB
 MhReservationAgcBean bean;
 public void reserver(){
    FacesContext context = FacesContext.getCurrentInstance();
    dt DT = new dt();
    
    codification COD = new codification();
    String codeConv = COD.cd_convention(cdH, beanAgc.agc().getCodeA()+typeConv);

    MhConventionAgc convAgc = beanConvAgc.getByCodeConv(codeConv);
    boolean dette = convAgc.getDette();

    
    if(dette){
        if(!DT.isValidDateRes(dateA, dateD, new Date())){
                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduites svp !!", ""));
                }else if(isListClientExist()){
                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Réservation déja existante !!", ""));
                }else{

                   try {
                     MhReservation rsv = new MhReservation();
                     rsv.setCodeH(codeH);
                     rsv.setNumCh(chambre);
                     rsv.setCodeC(null);
                     rsv.setCodeA(beanAgc.agc());
                     rsv.setCodeU(null);
                     rsv.setDateA(dateA);
                     rsv.setDateD(dateD);
                     rsv.setPeriodeOuverte(false);
                     rsv.setNbNuitee((int)DT.nuitee(dateA, dateD));
                     rsv.setPension(pension);
                     rsv.setPrixU((float)reductionCalc(chambre.getPrix(), beanConvAgc.getByHotel(codeH, typeConv).getPrcReduction()));
                     rsv.setVersement(prixCh);
                     rsv.setEtatP(true);
                     rsv.setFacturer(false);
                     rsv.setCodeR(codeR);
                     rsv.setDateR(new Date());
                     rsv.setTaxeSj(codeH.getTaxeSejour());
                     rsv.setPensionC((float)reductionCalc(codeH.getPensionC(), beanConvAgc.getByHotel(codeH, typeConv).getPrcReduction()));
                     rsv.setPrcPension(codeH.getPrcDemiPension());
                     rsv.setCloturer(false);
                     rsv.setNumCmd(null);
                     rsv.setCodeDm(null);
                     rsv.setVersementAgc((int) prixEncaissement);
                     rsv.setCodeConvAgc(beanConvAgc.getByHotel(codeH, typeConv));
                    
                        if(bean.reserver(rsv, NPID, typeConv)){
                          context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Réservation effectuée avec succès.", ""));
                        }else{
                          context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de réservation.", ""));
                        }
                    
                    } catch (Exception e) {
                          context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de réservation.", "Veuillez choisir une chambre svp !!"));
                    }

                }
    }else{
                        if(prixEngagement >= prixCh ){
                                if(!DT.isValidDateRes(dateA, dateD, new Date())){
                                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduites svp !!", ""));
                                }else if(isListClientExist()){
                                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Réservation déja existante !!", ""));
                                }else{

                                   try {
                                     MhReservation rsv = new MhReservation();
                                     rsv.setCodeH(codeH);
                                     rsv.setNumCh(chambre);
                                     rsv.setCodeC(null);
                                     rsv.setCodeA(beanAgc.agc());
                                     rsv.setCodeU(null);
                                     rsv.setDateA(dateA);
                                     rsv.setDateD(dateD);
                                     rsv.setPeriodeOuverte(false);
                                     rsv.setNbNuitee((int)DT.nuitee(dateA, dateD));
                                     rsv.setPension(pension);
                                     rsv.setPrixU((float)reductionCalc(chambre.getPrix(), beanConvAgc.getByHotel(codeH, typeConv).getPrcReduction()));
                                     rsv.setVersement(prixCh);
                                     rsv.setEtatP(true);
                                     rsv.setFacturer(false);
                                     rsv.setCodeR(codeR);
                                     rsv.setDateR(new Date());
                                     rsv.setTaxeSj(codeH.getTaxeSejour());
                                     rsv.setPensionC((float)reductionCalc(codeH.getPensionC(), beanConvAgc.getByHotel(codeH, typeConv).getPrcReduction()));
                                     rsv.setPrcPension(codeH.getPrcDemiPension());
                                     rsv.setCloturer(false);
                                     rsv.setNumCmd(null);
                                     rsv.setCodeDm(null);
                                     rsv.setVersementAgc((int) prixEncaissement);
                                     rsv.setCodeConvAgc(beanConvAgc.getByHotel(codeH, typeConv));

                                        if(bean.reserver(rsv, NPID, typeConv)){
                                          context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Réservation effectuée avec succès.", ""));
                                        }else{
                                          context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de réservation.", ""));
                                        }

                                    } catch (Exception e) {
                                          context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de réservation.", "Veuillez choisir une chambre svp !!"));
                                    }

                                }
                    }else{
                                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Total Compte CVN inférieur au prix HT.", ""));
                    }
    }
    
    
        
    }

 
 public boolean isListClientExist(){
        for (String NPID1 : NPID) {
            if(bean.isClientInCh(NPID1, dateA)) return true;
        }
    return false;
}
 
 private double reductionCalc(double somme, int prcRdc){
     double rst = somme;
       rst = rst*prcRdc;
       rst = rst / 100;
       rst = somme - rst;
     return rst;
 }
    
 
 
  
  
}
