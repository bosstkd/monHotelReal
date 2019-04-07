 package controller;

import bean.MhChambreBean;
import bean.MhCltSChBean;
import bean.MhConventionBean;
import bean.MhDemandeRBean;
import bean.MhEntrepriseBean;
import bean.MhHotelBean;
import bean.MhReservationBean;
import bean.MhUsersHBean;
import fct.codification;
import fct.dt;
import fct.nbr;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.MhChambre;
import model.MhClientSdem;
import model.MhCltFct;
import model.MhCltSCh;
import model.MhConvention;
import model.MhDemandeR;
import model.MhHotel;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import statistique.bean.comportement.methode;

@ManagedBean
@SessionScoped
public class reservationController extends methode{
    
    
    
    private String numCh;
    private String codeC;
    private String codeClt;
    private Date dt_A;
    private Date dt_D;
    private boolean periode_ouv;
    private String pension;
    private float prixU;
    private float total;
    private float ttc;
    private float versment;
    private String type_p;
    private boolean fction; 
    private boolean fctVisibility; 
    
    private boolean vrsBln;
    private String codeR;
    private String numCmd;
    private String codeDm;
    
    private List<MhChambre> listChLibre;
    
    
//-----------------------------------

    public List<MhChambre> getListChLibre() {
        listChLibre = beanCh.listChambreLibreEntCodeHtl(dt_A, dt_D, beanH.htl());
        return listChLibre;
    }

    public void setListChLibre(List<MhChambre> listChLibre) {
        this.listChLibre = listChLibre;
    }

    
    
    public boolean isFction() {
        return fction;
    }

    public void setFction(boolean fction) {
        this.fction = fction;
    }

    public boolean isFctVisibility() {
        return fctVisibility;
    }

    public void setFctVisibility(boolean fctVisibility) {
        this.fctVisibility = fctVisibility;
    }


    

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }
    
    

    public String getCodeClt() {
        return codeClt;
    }

    public void setCodeClt(String codeClt) {
        this.codeClt = codeClt;
    }
    
    
    private List<String> NPID;
    private String textPID;

    public String getTextPID() {
        return textPID;
    }

    public void setTextPID(String textPID) {
        this.textPID = textPID;
    }

    public String getType_p() {
        return type_p;
    }

    public void setType_p(String type_p) {
        this.type_p = type_p;
    }


    public float getTtc() {
        return ttc;
    }

    public void setTtc(float ttc) {
        this.ttc = ttc;
    }

    public boolean isVrsBln() {
        return vrsBln;
    }

    public void setVrsBln(boolean vrsBln) {
        this.vrsBln = vrsBln;
    }
   
    
    
    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }

    public Date getDt_A() {
        return dt_A;
    }

    public void setDt_A(Date dt_A) {
        this.dt_A = dt_A;
    }

    public Date getDt_D() {
        return dt_D;
    }

    public void setDt_D(Date dt_D) {
        this.dt_D = dt_D;
    }

    public boolean isPeriode_ouv() {
        return periode_ouv;
    }

    public void setPeriode_ouv(boolean periode_ouv) {
        this.periode_ouv = periode_ouv;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public float getPrixU() {
        return prixU;
    }

    public void setPrixU(float prixU) {
        this.prixU = prixU;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getVersment() {
        return versment;
    }

    public void setVersment(float versment) {
        this.versment = versment;
    }

    public List<String> getNPID() {
        return NPID;
    }

    public void setNPID(List<String> NPID) {
        this.NPID = NPID;
    }

    public String getNumCmd() {
        return numCmd;
    }

    public void setNumCmd(String numCmd) {
        this.numCmd = numCmd;
    }

    public String getCodeDm() {
        return codeDm;
    }

    public void setCodeDm(String codeDm) {
        this.codeDm = codeDm;
    }
    
    
    
  
@PostConstruct
public void Init(){
    NPID = new ArrayList<String>();
    dt_A = new Date();
    dt sdt = new dt();
    long nb = sdt.addDay(new Date(), 1);
    dt_D = new Date(nb);
    pension = "Demi";
    codeC = "";
    numCh = "";
    type_p= "Espece";
    fction = true;
    vrsBln = false;
    codeClt = "null";
}    
    
//-------------------------------------------------  
@EJB
MhReservationBean bean;

@EJB
MhCltSChBean beanCSCh;

@EJB
MhChambreBean beanCh;

@EJB
MhHotelBean beanH;

@EJB
MhEntrepriseBean beanEnt;

@EJB
MhUsersHBean beanUser;

@EJB
MhDemandeRBean beanDm;

    private List<MhDemandeR> listDemandeRHtl;

    public List<MhDemandeR> getListDemandeRHtl() {
        listDemandeRHtl = beanDm.getListDemandeRHtl();
        return listDemandeRHtl;
    }

    public void setListDemandeRHtl(List<MhDemandeR> listDemandeRHtl) {
        this.listDemandeRHtl = listDemandeRHtl;
    }

//-------------------------------------------------    
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
                prixTotal();
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
        prixTotal();
    }
} 

public void viderListPid(){
        FacesContext context = FacesContext.getCurrentInstance();
        NPID.removeAll(NPID);
        prixTotal();
        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Liste remis à zéro.", ""));
} 

public void OnRowSelect(SelectEvent event) {
        String n_p = ((MhCltSCh) event.getObject()).getNpid();
        textPID = n_p;
    }

public void OnRowSelect02(SelectEvent event) {
        String n_p = ((MhCltFct) event.getObject()).getCodeC();
        codeC = n_p;
    }




public boolean isListNotValid(){
    if(NPID.isEmpty())return false;
    else{
        String code_h = (String) Util.getSession().getAttribute("code_h");
        int nbp =  beanCh.singleSelect(code_h+"_"+numCh).getNbPlace();
        return nbp >= NPID.size();
    }
}

public boolean isListClientExist(){
        for (String NPID1 : NPID) {
            if(bean.isClientInCh(NPID1, dt_A)) return true;
        }
    return false;
}

//-------------------------------------------
public void prixChambre(){
    HttpSession hs = Util.getSession();
    String code_h = (String) hs.getAttribute("code_h");
    prixU = beanCh.singleSelect(code_h+"_"+numCh).getPrix();
    prixTotal();
    
}

public void prdSelected(){
    if(periode_ouv){
        total = 0;
        ttc = 0;
        versment = 0;
        vrsBln = true;
        fction = false;
        fctVisibility = true;
    }else{
        prixTotal();
        vrsBln = false;
        fctVisibility = false;
    }
}

 public String codeR(String numCh, Date dtA, Date dtD){
        codification COD = new codification();
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String code_r = COD.cd_structure(code_h+numCh+dtA+dtD+new Date());
        return code_r;
    }

public void prixTotal(){
           dt sdt = new dt();
            codification COD = new codification();
            nbr NBR = new nbr();
           if(periode_ouv){
               total = 0;
               ttc = 0;
               versment = 0;
               //vrsBln = false;
           }else{
                              
            int nb_nuitee = (int) sdt.nuitee(dt_A, dt_D);
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            MhHotel htl =  beanH.findByCodeH(code_h);    
            double taxe_s = htl.getTaxeSejour();
            double pension_c = htl.getPensionC();
            
           
            boolean exo = false;
            if(!codeC.equals("")){
               exo = beanEnt.isClientExo(codeC);
            }

             String codeConv = COD.cd_convention(code_h, codeC);
             int valC = 0;
                try {
                    MhConvention conv = beanConv.getByCodeConvention(codeConv);
                    if(sdt.isInPeriode(conv.getDu(), conv.getAu(), new Date())){
                       valC = conv.getPrcReduction();
                                   }
               } catch (Exception e) { 
                   valC = 0;
               }
             double th = THT(nb_nuitee, prixU, pension_c, htl.getPrcDemiPension(), pension, 0, NPID.size(), valC);
             total = (float) th;
             double tv = tva(th);
             double dt = dTimbre(tv + th);
             double txSj = TaxeSj(nb_nuitee, taxe_s, NPID.size());
       
            ttc = (float) NBR.db2Digits(TTC(th, tv, txSj, dt, type_p, exo));
            
            vrsRsvTtc();
           }
      

           codeR = codeR(numCh, dt_A, dt_D);
           
           
}


@EJB
MhConventionBean beanConv;



public void vrsRsvTtc(){
         versment = ttc;
        if(!fction)versment = total; 
        
}
//-------------------------------------------

private boolean skip;

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }


 public String onFlowProcess(FlowEvent event) {
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirmRsv";
        }
        else {
            return event.getNewStep();
        }
    }
 

//-------------------------------------------------    

public void reserver(){
    FacesContext context = FacesContext.getCurrentInstance();
            dt sdt = new dt();
            codification COD = new codification();
            nbr NBR = new nbr();
            MhChambre chbr = beanCh.singleSelect(beanH.htl().getCodeH()+"_"+numCh);
    if(((!periode_ouv) && !sdt.isValidDateRes(dt_A, dt_D, new Date()))|| (periode_ouv) && !sdt.isValidDateRes(dt_A, new Date())){   
               context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduites svp !!", ""));
            }else if((!codeC.equals("")) && (!beanEnt.isCodeClient(codeC))){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier le code (Entreprise) svp !!", ""));
                    }else if(!isListNotValid()){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"La liste des PIDs Client ne peut pas etre vide et ne doit pas dépassé le nombre de place de la chambre !!", ""));
                            }else if(bean.isChReserved(beanH.htl().getCodeH()+"_"+numCh, dt_A, dt_D)){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible : *la chambre "+numCh+" est déja reserver!!", ""));
                            }else if(isListClientExist()){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Réservation déja existante. Veuillez vérifier la liste PIDs!!", ""));
                            }else if(fction && versment > ttc){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Le versement ne peut pas etre superieur au TTC !!", ""));
                            }else if(!fction && versment > total){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Le versement ne peut pas etre superieur au THT !!", ""));
                            }else if(beanCh.isChambreSousConv(chbr)){
                              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible : ", "*la chambre "+numCh+" est sous convention !!"));
                            }else{                                   
                               Object[] rsv= new Object[17]; 
                               rsv[0] = numCh;
                               rsv[1] = "'"+codeC+"'";
                               rsv[14] = numCmd;
                               if(codeC.equals("")){
                                   rsv[1] = null;
                                   rsv[14] = null;
                               }
                               rsv[2] = "null";
                               rsv[3] = "'"+beanUser.singleSelectUserByMail((String) Util.getSession().getAttribute("email"), "code_u")+"'";
                               rsv[4] = dt_A;
                               rsv[5] = dt_D;
                               rsv[6] = periode_ouv;
                               rsv[7] = pension;
                               rsv[8] = prixU;
                               rsv[9] = versment;
                               rsv[10] = fction;
                               rsv[11] = codeR;
                               rsv[12] = ttc;
                               if(!fction) rsv[12] = total;
                               rsv[13] = type_p;
                               
                                HttpSession hs = Util.getSession();
                                String code_h = (String) hs.getAttribute("code_h");
                                
                                String codeConv = COD.cd_convention(code_h, codeC);
                                try {
                                MhConvention conv = beanConv.getByCodeConvention(codeConv);
                                            rsv[15] = conv.getPrcReduction();
                                } catch (Exception e) {
                                            rsv[15] = 0;
                                }
                                if(codeDm != null)
                                rsv[16] = codeDm;
                                
                               bean.insert(rsv, NPID);
                              codeDm = null;
                              context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Réservation effectuer avec succée.", ""));
                            }

                        
    }


public void basculer(MhDemandeR dem){
    numCh = dem.getNumch().getNumchApp();
    codeC = dem.getCodeC().getCodeC();
    dt_A = dem.getDu();
    dt_D = dem.getAu();
    pension = dem.getTypePension();
    codeDm = dem.getCodeDm();
    NPID.removeAll(NPID);
    
    List<MhClientSdem> cltDem = beanDm.getListCltSCh(dem);
    List<String> npident = new ArrayList<String>();
    for(MhClientSdem dm0:cltDem){
        npident.add(dm0.getNpid().getNpid());
    }
    NPID = npident;
    
}
   
}



