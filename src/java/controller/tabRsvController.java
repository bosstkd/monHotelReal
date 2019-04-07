package controller;

import bean.MhChargeSuppBean;
import bean.MhConventionBean;
import bean.MhEntrepriseBean;
import bean.MhHotelBean;
import bean.MhReservationBean;
import bean.MhTabReservationBean;
import bean.MhTabRsvWOFctBean;
import fct.codification;
import fct.dt;
import fct.nbr;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MhCltFct;
import model.MhConvention;
import model.MhHotel;
import model.MhReservation;
import model.MhRsvvueglobal;
import model.MhTabRsvWOFct;
import model.agc.MhAgence;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import statistique.bean.comportement.methode;

@ManagedBean
@SessionScoped
public class tabRsvController extends methode{
    private String numCh;
    private String npid;
    private String nom;
    private Date dt_A;
    private Date dt_D;
    private String codeR;
    private Date dtRsv;
    private String numFct;
    private String designation;
    private String typePaiement;
    private float prixU;
    private String pension;
    private int nbNuitee;
    private float versement;
    private String codeH;
    private String codeC;
    private double ttc;
    
    private String codeRVrs;
    private double vrsVrs;
//-----------------------------------

    public double getVrsVrs() {
        return vrsVrs;
    }

    public void setVrsVrs(double vrsVrs) {
        this.vrsVrs = vrsVrs;
    }

    
    
    public String getCodeRVrs() {
        return codeRVrs;
    }

    public void setCodeRVrs(String codeRVrs) {
        this.codeRVrs = codeRVrs;
    }
    
    
       
    
    public double getTtc() {
        return ttc;
    }

    public void setTtc(double ttc) {
        this.ttc = ttc;
    }
   

    
    
    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }

    public Date getDtRsv() {
        return dtRsv;
    }

    public void setDtRsv(Date dtRsv) {
        this.dtRsv = dtRsv;
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

    public int getNbNuitee() {
        return nbNuitee;
    }

    public void setNbNuitee(int nbNuitee) {
        this.nbNuitee = nbNuitee;
    }

    public float getVersement() {
        return versement;
    }

    public void setVersement(float versement) {
        this.versement = versement;
    }

    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
    }
 //-----------------------------------------
    
     private String code_r;
    private String charge;
    private float prix;

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCode_r() {
        return code_r;
    }

    public void setCode_r(String code_r) {
        this.code_r = code_r;
    }  
    
//------------------le model----------------    
     private MhTabRsvWOFct mdlRsv;
     
     

    public MhTabRsvWOFct getMdlRsv() {
        return mdlRsv;
    }

    public void setMdlRsv(MhTabRsvWOFct mdlRsv) {
        this.mdlRsv = mdlRsv;
    }


//-----------------EJB----------------------
    @EJB
    MhEntrepriseBean beanEnt;
    
    @EJB
    MhHotelBean beanH;
    
    @EJB
    MhTabReservationBean beanTabRsv;
    
    @EJB
    MhReservationBean beanRsv;
    
   @EJB
    MhChargeSuppBean beanCharge;
    
     private List<MhRsvvueglobal> listTabRsv;

    public List<MhRsvvueglobal> getListTabRsv() {
        this.listTabRsv = beanTabRsv.listMhTabRsv();
        return listTabRsv;
    }

    public void setListTabRsv(List<MhRsvvueglobal> listTabRsv) {
        this.listTabRsv = listTabRsv;
    }
    
    @EJB
    MhTabRsvWOFctBean beanTabRsvWOFct;
    
    private List<MhTabRsvWOFct> listTabRsvWOFct;

    public List<MhTabRsvWOFct> getListTabRsvWOFct() {
        this.listTabRsvWOFct = beanTabRsvWOFct.listMhTabRsvWOFctClot();
        HttpSession hs = Util.getSession();
        codeH = (String) hs.getAttribute("code_h");
       
        listTabRsvWOFct.forEach((listTabRsvWOFct1) -> {
            listTabRsvWOFct1.setNumCh(listTabRsvWOFct1.getNumCh().substring(codeH.length()+1, listTabRsvWOFct1.getNumCh().length()));
        });
        
        List<MhTabRsvWOFct> listTab = new ArrayList<MhTabRsvWOFct>();
              
       
        boolean ok = true;
        for(MhTabRsvWOFct elt: listTabRsvWOFct){
            ok = true;
            for(MhTabRsvWOFct eltNew: listTab){
                 if(elt.getCodeR().equals(eltNew.getCodeR())){
                        int i = listTab.indexOf(eltNew);
                        MhTabRsvWOFct element = listTab.get(i);
                        element.setNomPrenom(element.getNomPrenom()+", "+elt.getNomPrenom());
                        listTab.set(i, element);
                     ok = false;
                    break;
                 }
              }
            if(ok) listTab.add(elt);
        }
        
        return listTab;
    }

    public void setListTabRsvWOFct(List<MhTabRsvWOFct> listTabRsvWOFct) {
        this.listTabRsvWOFct = listTabRsvWOFct;
    }
    
    
    
    private List<MhTabRsvWOFct> listTabRsvWOFctAct;

    public List<MhTabRsvWOFct> getListTabRsvWOFctAct() {
        this.listTabRsvWOFctAct = beanTabRsvWOFct.listMhTabRsvWOFctAct();
        HttpSession hs = Util.getSession();
        codeH = (String) hs.getAttribute("code_h");
       
        listTabRsvWOFctAct.forEach((listTabRsvWOFct1) -> {
            listTabRsvWOFct1.setNumCh(listTabRsvWOFct1.getNumCh().substring(codeH.length()+1, listTabRsvWOFct1.getNumCh().length()));
        });
        return listTabRsvWOFctAct;
    }
    
    

    public void setListTabRsvWOFctAct(List<MhTabRsvWOFct> listTabRsvWOFctAct) {
        this.listTabRsvWOFctAct = listTabRsvWOFctAct;
    }
//--------------------------------------------------------------------


    private MhRsvvueglobal fctInfo;
     
     

    public MhRsvvueglobal getFctInfo() {
        return fctInfo;
    }

    public void setFctInfo(MhRsvvueglobal fctInfo) {
        this.fctInfo = fctInfo;
    }
    
    
    private List<MhRsvvueglobal> listFacture;

    public List<MhRsvvueglobal> getListFacture() {
        
        this.listFacture = beanTabRsv.listMhTabRsv();
        
        HttpSession hs = Util.getSession();
        String codeHs = (String) hs.getAttribute("code_h");

        listFacture.forEach((listFacture1) -> {
            listFacture1.setNumFct(listFacture1.getNumFct().substring(codeHs.length()+1, listFacture1.getNumFct().length()));
        });
        
        return listFacture;
    }

    public void setListFacture(List<MhRsvvueglobal> listFacture) {
        this.listFacture = listFacture;
    }
    
    
//-----------------------------------------------------------------------
    
    public String getInfo(String codeR, String find){
            try {
            switch (find) {
                            case "code_c":  return beanTabRsv.selectionA(codeR).getCodeC();
                            case "designation":  return beanTabRsv.selectionA(codeR).getDesignation();
                            case "numFct":  return beanTabRsv.selectionA(codeR).getNumFct().substring(beanH.htl().getCodeH().length() + 1 , beanTabRsv.selectionA(codeR).getNumFct().length());
                            case "type_paiement":  return beanTabRsv.selectionA(codeR).getTypePaiement();
                            default: return "/";
                           }
                } catch (Exception e) {
                     return "/";
                }
    } 
    
    public MhTabRsvWOFct getInfoA(String codeR){
            return (MhTabRsvWOFct) beanTabRsvWOFct.selectionA(codeR);
    }
    
 
 @EJB   
 MhConventionBean beanConv;   
    
    public double Tht(String codeR){
        
        MhTabRsvWOFct obj = beanTabRsvWOFct.selectionA(codeR);
        MhRsvvueglobal rSvGlob = beanTabRsv.selectionA(codeR);
          String typeP = "" ;
          codification COD = new codification();
          dt sdt = new dt();
          nbr NBR = new nbr();
        try {
             typeP = rSvGlob.getTypePaiement();
        } catch (Exception e) {
        }
      
         boolean exo = false;
            MhCltFct clt = null;
            try {
             clt = beanRsv.findByCodeR(codeR).getCodeC();
             codeC = clt.getCodeC();
            } catch (Exception e) {
                
            }
           
            if(codeC != null){
               exo = beanEnt.isClientExo(codeC);
            }       
            
            if(!exo){
                exo = !beanH.htl().getTvaS();
            }
            
            float chrg = (float) beanCharge.selectSomme(codeR);
            int nbPersonne = (int) beanTabRsvWOFct.countSelection(codeR);
            String typePension = obj.getPension();
            
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            
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
            
            try {
              valC = rSvGlob.getPrcReduction();
            } catch (Exception e) {  }
    
            
            
            
       double th = THT(obj.getNbNuitee(), obj.getPrixU(), obj.getPensionC(), obj.getPrcPension(), typePension, chrg, nbPersonne, valC);
       double tv = tva(th);
       double dt = dTimbre(tv + th);
       double txSj = TaxeSj(obj.getNbNuitee(), obj.getTaxeSj(), nbPersonne);
       
       ttc = NBR.db2Digits(TTC(th, tv, txSj, dt, typeP, exo));
       return NBR.db2Digits(th) ;
    }
    
 
    
    
//-------------------------------------------------------    
    public double ttl(String code_r){
        double th = Tht(code_r);
        MhRsvvueglobal rsvGlb = beanTabRsv.selectionA(code_r);
        String numFct = "";
        try {
            numFct = rsvGlb.getNumFct();
        } catch (Exception e) {
        }
        if(!numFct.equals("")){
            return ttc;
        }else
            return th;
    }
//-------------------------------------------------------    
    public void annuler(String code_r){
   
        FacesContext context = FacesContext.getCurrentInstance();
          dt sdt = new dt();
        Date dtA = beanRsv.singleSelectJPQL(code_r).getDateR();
        MhAgence agc;
        try {
             agc = beanRsv.singleSelectJPQL(code_r).getCodeA();
        } catch (Exception e) {
            agc = null;
        }
        
        if(agc == null){
             if(sdt.superieurORE(new Date() ,sdt.longToDate(sdt.addDay(dtA, 1)))){
            try {
                 beanRsv.delete(code_r);
                 context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Annulation de reservation effectuer avec succès.", ""));
            } catch (Exception e) {
                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez supprimer les charges supplémentaire du client svp pour que vous puissiez annuler la réservation.", ""));
            }
           }else{
            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Annulation impossible la réservation a dépassé les 24h.", ""));
         }     
        }else{
            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Annulation impossible:", "Réservation créer par l'agence: "+agc.getRaisonSocial()));
        }
        
           
    
    }
//--------------------------------------------------------    
    public void liberation(String code_r){
   
        FacesContext context = FacesContext.getCurrentInstance();
       MhReservation rsv = beanRsv.findByCodeR(code_r);
       
       MhAgence agc;
       
        try {
            agc = rsv.getCodeA();
        } catch (Exception e) {
            agc = null;
        }
        if(agc != null){
             context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Libération impossible:", "Réservation effectuer par l'agence "+agc.getRaisonSocial()+"."));
        }else{
             Date dtA = beanRsv.singleSelectJPQL(code_r).getDateA();
          dt sdt = new dt();
        String str = sdt.form_Insr(dtA);
        long nbNt= sdt.nuitee(dtA, new Date());
       
        if(str.equals(sdt.form_Insr(new Date()))){
            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible veuillez annuler la réservation.", ""));
        }else{
             MhReservation rsvs = beanRsv.findByCodeR(code_r);
             if(
                    beanRsv.updateJPQL(rsvs, "DateD", new Date()) &&
                    beanRsv.updateJPQL(rsvs, "PeriodeOuv", false) &&
                    beanRsv.updateJPQL(rsvs, "nbNuitee", (int)nbNt)
                ){
                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Libération de chambre effectuer avec succès.", ""));
                  }else{
                     context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur de liaison de libération.", ""));
                 }
            
        }
        }
       
        
    }
//--------------------------------------------------------      
    public void verser(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(codeRVrs != null && !codeRVrs.equals("")){
                        double tlt = ttl(codeRVrs);
                        double vrs = beanRsv.singleSelectJPQL(codeRVrs).getVersement();
                        vrs = vrs + vrsVrs;

                        if(vrs > tlt+10){
                           context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier la somme introduite svp.", ""));
                        }else{       
                            MhReservation rsvs = beanRsv.findByCodeR(codeRVrs);
                                if(beanRsv.update(rsvs,(float) vrsVrs, "add")){
                                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Versement effectuer avec succés.", ""));
                                }else{
                                     context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de connexion. niveau JPQL", ""));
                                }  
                        }
        }else{
                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez choisir une réservation depuis le tableau svp.", ""));
        }
   }
//--------------------------------------------------------     
   public void rembourser(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(codeRVrs != null && !codeRVrs.equals("")){

                    double vrs = beanRsv.singleSelectJPQL(codeRVrs).getVersement();
                    vrs = vrs - vrsVrs;
                        if(vrs < 0){
                             context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible veuillez vérifier le remboursement déja effectuer.", ""));
                        }else{
                             MhReservation rsvs = beanRsv.findByCodeR(codeRVrs);
                            if(beanRsv.update(rsvs,(float) vrsVrs, "sub")){
                                 context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Remboursement effectuer avec succés.", ""));
                            }else{
                                 context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de connexion. niveau JPQL", ""));
                            }  
                        }

        }else{
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez choisir une réservation depuis le tableau svp.", ""));
        }
   }
    
//-------------------------------------------------

   public void OnRowSelect(SelectEvent event) {
        String n_p = ((MhTabRsvWOFct) event.getObject()).getCodeR();
        codeRVrs = n_p;
        
        double tlt = ttl(codeRVrs);

        double vrs = beanRsv.singleSelectJPQL(codeRVrs).getVersement();

        if(vrs > tlt)  vrsVrs = vrs - tlt;
        else vrsVrs = tlt - vrs ;
    }
   
//-------------------------------------------------

   @PostConstruct
   public void init(){
       numRsvSelected = "";
   }
   
   
JasperPrint jasperprint;  
   
public void init02() throws JRException{    
        
          nbr NBR = new nbr();
    
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
       
        Date dtRsv0 = new Date();

        MhCltFct code_client = new MhCltFct();        
        float totHt = 0;
        float totHtRed = 0;
        float prixCh = 0;
        float prixPension = 0;
        int nbNuitee = 0;
        int prcPension = 0;
        float pensionC = 0;
        float totPrixCh = 0;
        float totPension = 0;
        float versement = 0;
        float versementAgc = 0;
        int prcGainAgc = 0;
        String codeConvagc = "";
        String agcRaisonSociale = "";
        
        
        String typeP = "";
        String design = "";
        String code_r="";
        List<MhTabRsvWOFct> rsv = beanTabRsvWOFct.modelRsvWOFct(numRsvSelected);
        int nbPersonne = rsv.size();
        
         List<String> NPID = new ArrayList<String>();
         for(MhTabRsvWOFct rsvs:rsv){
             NPID.add(rsvs.getNpid());
         }
        
          List<MhTabRsvWOFct> rsv0 = new ArrayList<MhTabRsvWOFct>();
         
        for(MhTabRsvWOFct rsvs:rsv){
            code_r = rsvs.getCodeR();
            rsvs.setNumCh(rsvs.getNumCh().substring(code_h.length()+1, rsvs.getNumCh().length()));
            dtRsv0 = rsvs.getDateR();
            prixCh = rsvs.getPrixU();
            nbNuitee = rsvs.getNbNuitee();
            pensionC = rsvs.getPensionC();
            prcPension = rsvs.getPrcPension();
            versement = rsvs.getVersement();
            typeP = rsvs.getPension();
            totPrixCh = prixCh*nbNuitee;
            totPension= nbPersonne*nbNuitee;
            float db = 0;
            if(typeP.equals("Demi")){
                db = pensionC * prcPension;
                db = db / 100.0f;
            }else if(typeP.equals("Non")){
                db = 0;
            }else{
                db = pensionC;
            }
            prixPension = db;
            totPension = totPension * db;
            
            design = beanRsv.designationGen(code_h, rsvs.getNumCh(), rsvs.getPension(), NPID, npid, nbNuitee);
            rsvs.setNomPrenom(design);
            rsv0.add(rsvs);
            break;
        }
        rsv = rsv0;
          //public double THT(int nbNuitee, double prix_ch, double pension, int prcPension, String typePension, double chargeSupp, int nbPersonne, int prcReduction){
          int prcRed = 0;
      try {
            MhRsvvueglobal rSvGlob = beanTabRsv.selectionA(code_r);
            prcRed = rSvGlob.getPrcReduction();
        } catch (Exception e) {
            prcRed = 0;
        }
         
        totHt = (float) THT(nbNuitee, prixCh, pensionC, prcPension, typeP, 0, nbPersonne, 0);
        totHtRed = (float) THT(nbNuitee, prixCh, pensionC, prcPension, typeP, 0, nbPersonne, prcRed);
       
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(rsv);
        
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        List<MhHotel> tabInfo = beanH.SingleSelectTab(code_h);
        for(MhHotel tabInfo1:tabInfo){
            parameters.put("nom_entreprise", tabInfo1.getRaisonSocial());
            parameters.put("adresse", tabInfo1.getAdresse());
            parameters.put("tel", tabInfo1.getTel()+"/"+tabInfo1.getFax());
            parameters.put("ai", tabInfo1.getNai());
            parameters.put("rc", tabInfo1.getNrc());
            parameters.put("mail", tabInfo1.getMail());
            parameters.put("nif", tabInfo1.getNif());
            parameters.put("compte", tabInfo1.getRib());
            parameters.put("etoile", "\\etoile\\"+ tabInfo1.getEtoile()+".jpg");
            break;
        }
        parameters.put("logoLink", "..\\"+code_h+"\\logo\\logo_RS.jpg");
        
        
        MhReservation gRsvCc = beanRsv.findByCodeR(numRsvSelected);
        code_client = gRsvCc.getCodeC();
        
       
        
        try {
        parameters.put("cd_client", code_client.getCodeC());
            parameters.put("nm_client", code_client.getRaisonSociale());
    } catch (Exception e) {
        parameters.put("cd_client", "");
            parameters.put("nm_client", "");
    }
        try {
        agcRaisonSociale = gRsvCc.getCodeA().getRaisonSocial();    
    } catch (Exception e) {
        agcRaisonSociale = null;
    }
        
        
        if(agcRaisonSociale != null){
             versementAgc = gRsvCc.getVersementAgc();
            float x = versementAgc - gRsvCc.getVersement();
            x =  x / gRsvCc.getVersement();
            x = x * 100;
            prcGainAgc = (int) x;
            codeConvagc = gRsvCc.getCodeConvAgc().getCodeConvAgc();
            
            parameters.put("agc", agcRaisonSociale+" code convention:"+codeConvagc+".");
            parameters.put("droitAgc", "Droit agence: "+prcGainAgc+"% Total à payer: "+NBR.dbToDf(versementAgc)+" Da.");
        }
        
       
        

         parameters.put("nbl", numRsvSelected);
          parameters.put("dts", new SimpleDateFormat("dd/MM/yyyy").format(dtRsv0));
           parameters.put("prixCh", NBR.dbToDf(prixCh)+" Da");
            parameters.put("pensionC", NBR.dbToDf(pensionC)+" Da");
             parameters.put("prcPension", NBR.dbToDf(prcPension)+" %");
              parameters.put("prixPension", NBR.dbToDf(prixPension)+" Da");
                parameters.put("nbNuitee", nbNuitee+" Nuit(s).");
                 parameters.put("totPrixCh",NBR.dbToDf(totPrixCh)+" Da");
                  parameters.put("totPension", NBR.dbToDf(totPension)+" Da");
                   parameters.put("totHt", NBR.dbToDf(totHt)+" Da");
                  
                   if(agcRaisonSociale != null){
                    parameters.put("versement", NBR.dbToDf(versementAgc)+" Da");
                   }else{
                    parameters.put("versement", NBR.dbToDf(versement)+" Da");
                   }                     
                   if(prcRed > 0)parameters.put("prcRed", "Une réduction de "+prcRed+"% est appliquée au total hors taxes. HT:"+NBR.dbToDf(totHtRed)+" Da." );

                    if(versement > totHt){
                         parameters.put("facturer", " reservation facturé.");
                    }else{
                                                 parameters.put("facturer", " reservation non facturé.");
                    }
        // parameters.put("Tht", nbr.dbToDf(totHt)+" Da.");
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\facture\\BonR.jasper");
        File f = new File(relativeWebPath); 
        jasperprint = JasperFillManager.fillReport(f.getPath(), parameters, beanDataSource);
    }    
    
  public void Chrg(ActionEvent event) throws JRException, IOException{
      if(!numRsvSelected.equals("")){
            init02();
            ServletOutputStream servletOutputStream = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);
                    servletOutputStream.flush();
                    servletOutputStream.close();
      }
        
    }
  
     //----------------------
    
    private String numRsvSelected;

    public String getNumRsvSelected() {
        return numRsvSelected;
    }

    public void setNumRsvSelected(String numRsvSelected) {
        this.numRsvSelected = numRsvSelected;
    }
    
    //*******************************    
    MhTabRsvWOFct selection00;

    public MhTabRsvWOFct getSelection00() {
        return selection00;
    }

    public void setSelection00(MhTabRsvWOFct selection00) {
        this.selection00 = selection00;
    }
//******************************
    
    //------------------------

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Selected Réservation ", ((MhTabRsvWOFct)event.getObject()).getCodeR());
       
        numRsvSelected = ((MhTabRsvWOFct)event.getObject()).getCodeR();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
