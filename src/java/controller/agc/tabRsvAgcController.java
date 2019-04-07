package controller.agc;

import bean.MhHotelBean;
import bean.MhReservationBean;
import bean.MhTabReservationBean;
import bean.MhTabRsvWOFctBean;
import bean.agc.MhReservationAgcBean;
import bean.agc.mhAgenceBean;
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
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.MhCltFct;
import model.MhHotel;
import model.MhReservation;
import model.MhRsvvueglobal;
import model.MhTabRsvWOFct;
import model.agc.MhAgcRsvAnnuler;
import model.agc.MhAgence;
import model.agc.modelEtatAnn;
import model.agc.modelEtatRsv;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import statistique.bean.comportement.methode;

@ManagedBean
@ViewScoped
public class tabRsvAgcController extends methode{
    
    private List<MhReservation> listRsvForAgc;
    private MhReservation select00;
    private String numRsvSelected;

    private float totRet;
    private float totHt;
//---------------------------------------------    
    private Date dateD;
    private Date dateF;

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
    
//---------------------------------------------
    

    
    public String getNumRsvSelected() {
        return numRsvSelected;
    }

    public void setNumRsvSelected(String numRsvSelected) {
        this.numRsvSelected = numRsvSelected;
    }
    
    public MhReservation getSelect00() {
        return select00;
    }

    public void setSelect00(MhReservation select00) {
        this.select00 = select00;
    }
    
    private MhReservation mdlRsv;
     
    public MhReservation getMdlRsv() {
        return mdlRsv;
    }

    public void setMdlRsv(MhReservation mdlRsv) {
        this.mdlRsv = mdlRsv;
    }

     @EJB
     MhReservationAgcBean bean;
    
     @EJB
     MhHotelBean beanHtl;
    public List<MhReservation> getListRsvForAgc() {
        if(selectedHtl == null) selectedHtl = "";
        if(selectedHtl.equals(""))
           listRsvForAgc = bean.findForAgc(dateD, dateF);
        else{
           MhHotel htl = beanHtl.findByCodeH(selectedHtl);
           listRsvForAgc = bean.findForAgcByHtl(htl, dateD, dateF);
           etRsv = new ArrayList<modelEtatRsv>();
           totHt = 0;
           for(MhReservation rsv : listRsvForAgc){
               modelEtatRsv etatRsv = new modelEtatRsv();
               etatRsv.setCodeR(rsv.getCodeR());
               etatRsv.setNom(listPersonne(rsv.getCodeR()));
               etatRsv.setDateR(rsv.getDateR());
               etatRsv.setDu(rsv.getDateA());
               etatRsv.setAu(rsv.getDateD());
               etatRsv.setPrix(rsv.getVersement());
               etatRsv.setNumCh(rsv.getNumCh().getNumchApp());
               if(!etRsv.contains(etatRsv)){
                   totHt = totHt + rsv.getVersement();
                   etRsv.add(etatRsv);
               }
           }

        }
        
        
        
        return listRsvForAgc;
    }

    public void setListRsvForAgc(List<MhReservation> listRsvForAgc) {
        this.listRsvForAgc = listRsvForAgc;
    }
    
 //------------Liste annuler----------------   
    private List<MhAgcRsvAnnuler> listRsvAnnuler;

    public List<MhAgcRsvAnnuler> getListRsvAnnuler() {
         if(selectedHtl == null) selectedHtl = "";
        if(selectedHtl.equals(""))
           listRsvAnnuler = bean.findRsvAnnuler(dateD, dateF);
        else{
           MhHotel htl = beanHtl.findByCodeH(selectedHtl);
           listRsvAnnuler = bean.findRsvAnnulerByHtl(htl, dateD, dateF);
           etAnn = new ArrayList<modelEtatAnn>();
           totRet = 0;
           for(MhAgcRsvAnnuler ann : listRsvAnnuler){
               modelEtatAnn etatAnn = new modelEtatAnn();
               etatAnn.setCodeR(ann.getCodeR());
               etatAnn.setDesign(ann.getDesignation());
               etatAnn.setDu(ann.getDateA());
               etatAnn.setAu(ann.getDateD());
               etatAnn.setPrix(ann.getMontant());
               etatAnn.setRetenu(ann.getRetenue());
               if(!etAnn.contains(etatAnn)) {
                   totRet = ann.getRetenue()+totRet;
                   etAnn.add(etatAnn);
               }
           }
           
        }
        return listRsvAnnuler;
    }

    public void setListRsvAnnuler(List<MhAgcRsvAnnuler> listRsvAnnuler) {
        this.listRsvAnnuler = listRsvAnnuler;
    }
 //------------------------------------------   
     
     
     
    
     public void OnRowSelectForPdf(SelectEvent event) {
        String n_p = ((MhReservation) event.getObject()).getCodeR();
        numRsvSelected = n_p;
    }
 
  
  public String listPersonne(String code_r){
      return bean.listPersonneRsv(code_r);
  }
    
  
  private String selectedHtl;

    public String getSelectedHtl() {
        return selectedHtl;
    }

    public void setSelectedHtl(String selectedHtl) {
        this.selectedHtl = selectedHtl;
    }
  
  
  
   private List<MhHotel> htl;

    public List<MhHotel> getHtl() {
        return htl;
    }

    public void setHtl(List<MhHotel> htl) {
        this.htl = htl;
    }
   
   
  
  private Map<String,String> hotel = new HashMap<String, String>();

    public Map<String, String> getHotel() {
        return hotel;
    }

    public void setHotel(Map<String, String> hotel) {
        this.hotel = hotel;
    }

  
  
@EJB
 MhReservationBean beanRsv;
 public void supprimer(String code_r){
     FacesContext context = FacesContext.getCurrentInstance();
     MhReservation rsv = beanRsv.findByCodeR(code_r);
     dt sdt = new dt();
     if(sdt.superieurORE(new Date() ,sdt.longToDate(sdt.addDay(rsv.getDateR(), 1)))){
         if(bean.delete(code_r)){
           context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Annulation effectuée avec succès.", ""));
         }else{
           context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'annulation",""));
         }
     }else{
        context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Annulation impossible:", "la réservation a dépassé les 24h !!"));
     }
     
 }
 
 //------------PDF----------------------
 
 @EJB
 MhTabRsvWOFctBean beanTabRsvWOFct;
 
 @EJB
 MhTabReservationBean beanTabRsv;
    
JasperPrint jasperprint;  



@PostConstruct
public void init(){
    
    dt DT = new dt();
   
    dateD = DT.getFirstDayOfMonth(new Date());
    dateF = DT.getLastDayOfMonth(new Date());
    
    
    
      List<MhReservation> rsvLst = getListRsvForAgc();
      htl = new ArrayList<MhHotel>();
      selectedHtl = "";
      for(MhReservation rsv : rsvLst){
          htl.add(rsv.getCodeH());
      }
      
     List<String> laListString = htl.stream().map(MhHotel::getRaisonSocial).collect(Collectors.toCollection(ArrayList::new));
         List<String> laListCdC = htl.stream().map(MhHotel::getCodeH).collect(Collectors.toCollection(ArrayList::new));

         hotel = new HashMap<String, String>();
         for(int i = 0; i < laListString.size(); i++) hotel.put(laListString.get(i),laListCdC.get(i));
}
   
public void init02() throws JRException{    
        
          nbr NBR = new nbr();
    
       String code_h = beanRsv.findByCodeR(numRsvSelected).getCodeH().getCodeH();
       
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
        List<MhTabRsvWOFct> rsv = beanTabRsvWOFct.modelRsvWOFctH(numRsvSelected, code_h);
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
            
            design = beanRsv.designationGen(code_h, rsvs.getNumCh(), rsvs.getPension(), NPID, "", nbNuitee);
            rsvs.setNomPrenom(design);
            rsv0.add(rsvs);
            break;
        }
        rsv = rsv0;
          //public double THT(int nbNuitee, double prix_ch, double pension, int prcPension, String typePension, double chargeSupp, int nbPersonne, int prcReduction){
          int prcRed = 0;
      try {
            MhRsvvueglobal rSvGlob = beanTabRsv.selectionAH(code_r, code_h);
            prcRed = rSvGlob.getPrcReduction();
        } catch (Exception e) {
            prcRed = 0;
        }
         
        totHt =  (float) THT(nbNuitee, prixCh, pensionC, prcPension, typeP, 0, nbPersonne, 0);
        totHtRed = (float) THT(nbNuitee, prixCh, pensionC, prcPension, typeP, 0, nbPersonne, prcRed);
       
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(rsv);
        
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        MhHotel tabInfo = beanRsv.findByCodeR(numRsvSelected).getCodeH();
       
            parameters.put("nom_entreprise", tabInfo.getRaisonSocial());
            parameters.put("adresse", tabInfo.getAdresse());
            parameters.put("tel", tabInfo.getTel()+"/"+tabInfo.getFax());
            parameters.put("ai", tabInfo.getNai());
            parameters.put("rc", tabInfo.getNrc());
            parameters.put("mail", tabInfo.getMail());
            parameters.put("nif", tabInfo.getNif());
            parameters.put("compte", tabInfo.getRib());
            parameters.put("etoile", "\\etoile\\"+ tabInfo.getEtoile()+".jpg");
         
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
  
 //*************************Les Etat R et Ann **********************************// 
  
  
  private List<modelEtatRsv> etRsv; 

  private List<modelEtatAnn> etAnn; 
  
  @EJB
  mhAgenceBean beanAgc;
  public void initR() throws JRException{
        
        
      
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        MhAgence tabInfo1 = beanAgc.agc();

            parameters.put("nom_entreprise", tabInfo1.getRaisonSocial());
            parameters.put("adresse", tabInfo1.getAdresse());
            parameters.put("tel", tabInfo1.getTel()+"/"+tabInfo1.getFax());
            parameters.put("ai", tabInfo1.getNai());
            parameters.put("rc", tabInfo1.getNrc());
            parameters.put("mail", tabInfo1.getMail());
            parameters.put("nif", tabInfo1.getNif());
            parameters.put("compte", tabInfo1.getRib());
            parameters.put("totRet", totRet);
            parameters.put("totHt", totHt - totRet);
            parameters.put("hotel", beanHtl.findByCodeH(selectedHtl).getRaisonSocial());
           
        parameters.put("logoLink", "..\\"+tabInfo1.getCodeA()+"\\logo\\logo_RS.jpg");
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(etRsv);
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\facture\\etatRsvAgc.jasper");
        File f = new File(relativeWebPath); 
        jasperprint = JasperFillManager.fillReport(f.getPath(), parameters, beanDataSource);  
    }
  
  public void initAnn() throws JRException{
        
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        MhAgence tabInfo1 = beanAgc.agc();

            parameters.put("nom_entreprise", tabInfo1.getRaisonSocial());
            parameters.put("adresse", tabInfo1.getAdresse());
            parameters.put("tel", tabInfo1.getTel()+"/"+tabInfo1.getFax());
            parameters.put("ai", tabInfo1.getNai());
            parameters.put("rc", tabInfo1.getNrc());
            parameters.put("mail", tabInfo1.getMail());
            parameters.put("nif", tabInfo1.getNif());
            parameters.put("compte", tabInfo1.getRib());
            parameters.put("tot", totRet);
            parameters.put("hotel", beanHtl.findByCodeH(selectedHtl).getRaisonSocial());
        parameters.put("logoLink", "..\\"+tabInfo1.getCodeA()+"\\logo\\logo_RS.jpg");
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(etAnn);
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\facture\\etatAnnAgc.jasper");
        File f = new File(relativeWebPath); 
        jasperprint = JasperFillManager.fillReport(f.getPath(), parameters, beanDataSource);  
    }
  
  
  
    public void etatR(ActionEvent event) throws JRException, IOException{
        FacesContext context = FacesContext.getCurrentInstance();
        if(selectedHtl.equals("")){
                       context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez selectionner un hotel svp !!", ""));
       }else{
              initR();
                ServletOutputStream servletOutputStream = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).getOutputStream();
                JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);

                        servletOutputStream.flush();
                        servletOutputStream.close();
        }
      
    }

   public void etatAnn(ActionEvent event) throws JRException, IOException{
       FacesContext context = FacesContext.getCurrentInstance();
       if(selectedHtl.equals("")){
                       context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez selectionner un hotel svp !!", ""));
       }else{
            initAnn();
            ServletOutputStream servletOutputStream = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);

                    servletOutputStream.flush();
                    servletOutputStream.close();
       }
       
    }

  
}
