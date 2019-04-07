package controller;

import bean.MhChargeSuppBean;
import bean.MhCltSChBean;
import bean.MhConventionBean;
import bean.MhEntrepriseBean;
import bean.MhHotelBean;
import bean.MhReservationBean;
import bean.MhRsvVueGlobalBean;
import bean.MhTabReservationBean;
import fct.codification;
import fct.nbr;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;
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
import model.MhChambre;
import model.MhChargeSupp;
import model.MhCltFct;
import model.MhConvention;
import model.MhHotel;
import model.MhReservation;
import model.MhRsvvueglobal;
import model.MhTabRsvWOFct;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.event.SelectEvent;
import statistique.bean.comportement.methode;

@ManagedBean
@SessionScoped
public class rsvVueGlobalController extends methode{
 
 
    
    
    private String numFctPdf;    
    
    public String getNumFctPdf() {
        return numFctPdf;
    }

    public void setNumFctPdf(String numFctPdf) {
        this.numFctPdf = numFctPdf;
    }
    //---------------------- 
    
    private MhRsvvueglobal selectedFct; 
    
    public MhRsvvueglobal getSelectedFct() {
        return selectedFct;
    }

    public void setSelectedFct(MhRsvvueglobal selectedFct) {
        this.selectedFct = selectedFct;
    }
    //---------------------- 
    
   //---------------------------------------------------------
    
    private List<MhRsvvueglobal> ListFctOk;

    public List<MhRsvvueglobal> getListFctOk() {
     HttpSession hs = Util.getSession();
     String code_h = (String) hs.getAttribute("code_h");
     

 
     this.ListFctOk = beanRsvList.findAllNative();
     int i = 0;
     for(MhRsvvueglobal lst:this.ListFctOk){
                    int x = code_h.length()+1;
                    int y = lst.getNumFct().length();
                    if (y>x) lst.setNumFct(lst.getNumFct().substring(x, y));
                    
                       try {
                                String  rs = (String) beanEntreprise.singleSelectEntreprise("raison_sociale",lst.getCodeC());
                               // String rs = getCltFct.getRaisonSociale();
                                lst.setCodeC( lst.getCodeC()+" - "+rs); 
                            } catch (Exception e) {
                                lst.setCodeC("_"); 
                            }
                          
                         
               }
     
        List<MhRsvvueglobal> ListFctNew = new ArrayList<MhRsvvueglobal>();
        boolean ok = true;
        
         for(MhRsvvueglobal lst:this.ListFctOk){
             ok = true;
             for(MhRsvvueglobal lstNew: ListFctNew){
                 if(lstNew.getCodeR().equals(lst.getCodeR())){
                     ok = false;
                     break;
                 }else{
                     ok = true;
                 }
              }
             if(ok) ListFctNew.add(lst);
         }
        
        return ListFctNew;
    }

    public void setListFctOk(List<MhRsvvueglobal> ListFctOk) {
        this.ListFctOk = ListFctOk;
    }
    
    

//************************************************    
    private List<MhRsvvueglobal> ListFctSlt;
    public void setListFctSlt(List<MhRsvvueglobal> ListFctSlt) {
        this.ListFctSlt = ListFctSlt;
    }
 
        
    
//---------------------------------------------------------------------  
    
   private String typeP; 
   private boolean fctParRsv;  
   private String raisonSociale;
   private List<MhCltFct> ent;
   private Map<String,String> entreprise = new HashMap<String, String>();
 
   private List<MhReservation> selectedReservation;
   
   

    public String getTypeP() {
        return typeP;
    }

    public void setTypeP(String typeP) {
        this.typeP = typeP;
    }

    public boolean isFctParRsv() {
        return fctParRsv;
    }

    public void setFctParRsv(boolean fctParRsv) {
        this.fctParRsv = fctParRsv;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public List<MhCltFct> getEnt() {
        return ent;
    }

    public void setEnt(List<MhCltFct> ent) {
        this.ent = ent;
    }

    public Map<String, String> getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Map<String, String> entreprise) {
        this.entreprise = entreprise;
    }

    public List<MhReservation> getSelectedReservation() {
        return selectedReservation;
    }

    public void setSelectedReservation(List<MhReservation> selectedReservation) {
        this.selectedReservation = selectedReservation;
    }

 
//--------------------------------------------------    
    
   @PostConstruct
    public void init() {
        raisonSociale = "";
        numCheque = "";
        numFctPdf = "";
        ent = beanClt.listCltFct();
        etatNmChq = false;
         List<String> laListString = ent.stream().map(MhCltFct::getRaisonSociale).collect(Collectors.toCollection(ArrayList::new));
         List<String> laListCdC = ent.stream().map(MhCltFct::getCodeC).collect(Collectors.toCollection(ArrayList::new));

         entreprise = new HashMap<String, String>();
         for(int i = 0; i < laListString.size(); i++) entreprise.put(laListString.get(i)+" - "+laListCdC.get(i),laListCdC.get(i));
    }
   
     
//-----------------------------

@EJB
MhRsvVueGlobalBean bean;   

@EJB
MhTabReservationBean beanTab; 

@EJB
MhReservationBean beanRsv; 

@EJB
MhReservationBean beanRsvFct; 

@EJB
MhReservationBean beanRsvList; 

@EJB
MhEntrepriseBean beanClt; 

@EJB
MhCltSChBean beanCSCh;

@EJB
MhChargeSuppBean beanChargeSupp;

@EJB
MhHotelBean beanHotel;

@EJB
MhEntrepriseBean beanEntreprise;

    private List<MhReservation> listFct;

    public List<MhReservation> getListFct() {
        this.listFct = beanRsvFct.listNonFct();
        return listFct;
    }

    public void setListFct(List<MhReservation> listFct) {
        this.listFct = listFct;
    }
    
    
    public String designation(String code_r){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
        MhChambre nmCh = beanRsv.singleSelectJPQL(code_r).getNumCh();
        String numCh = nmCh.getNumCh();
        numCh = numCh.substring(code_h.length() + 1, numCh.length());
        
        String pension = beanRsv.singleSelectJPQL(code_r).getPension();
        
        String cmd = beanRsv.singleSelectJPQL(code_r).getNumCmd();
        
        int nbNtee = beanRsv.singleSelectJPQL(code_r).getNbNuitee();
        
        List<String> NPID = beanRsv.CodeCltSCh(code_r);
        
       return beanRsv.designationGen(code_h, numCh, pension, NPID, cmd, nbNtee);
    }
    
    
    public String clients(String code_r){   
        String cd_c = (String) beanRsv.singleSelectReservation(code_r, "code_c");
        MhCltFct clt = beanClt.singleSelectEntJPQL(cd_c);
        
        if(clt!=null)
             return clt.getRaisonSociale();
        else
             return "";
    }
    
   @EJB
   MhConventionBean beanConv; 
    
    public void Ajouter(){
 
        
           // dt sdt = new dt();
            codification COD = new codification();
        
            FacesContext context = FacesContext.getCurrentInstance();
            List<String> laListString = selectedReservation.stream().map(MhReservation::getCodeR).collect(Collectors.toCollection(ArrayList::new));        
           
            if(raisonSociale == null) raisonSociale = "";
            
            if(raisonSociale.equals("")){
                List<MhCltFct> laListClt = selectedReservation.stream().map(MhReservation::getCodeC).collect(Collectors.toCollection(ArrayList::new));        
                String cdClientSansRs = "";
                if(laListClt != null)
                for(MhCltFct cltFct:laListClt){
                    try {
                         cdClientSansRs = cltFct.getCodeC();
                    } catch (Exception e) {
                         cdClientSansRs = "";
                    }
                   
                    break;
                }
                if(!cdClientSansRs.equals("")) {
                    raisonSociale = cdClientSansRs;
                }
            }
            
            
            if(laListString.isEmpty()){
                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier la selection svp !!", ""));
            }else{
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            boolean confirme = false;
            
            if(!fctParRsv){
            String cd_c = "";
            boolean ok = true;
                 for(String cdR:laListString){
                     if(ok){
                          cd_c = (String) beanRsv.singleSelectReservation(cdR, "code_c");
                          ok = false;
                     }
                     if(!cd_c.equals((String) beanRsv.singleSelectReservation(cdR, "code_c"))){
                         confirme = true;
                         break;
                     }
                     cd_c = (String) beanRsv.singleSelectReservation(cdR, "code_c");
                 }
            }
            if(confirme){
                   context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier la selection svp !!", ""));
            }else{
                    String numFct = beanRsvFct.numFct(code_h);
                    String Design;
                    boolean repeatIt = true;
                    for(String cdR:laListString){
                        if(fctParRsv){
                                numFct = beanRsvFct.numFct(code_h);
                                repeatIt = true;
                        }
                           System.out.println(raisonSociale);
                            Design = designation(cdR);
                            bean.AjouterFct(cdR, numFct, Design, typeP, numCheque, remarque);
                            MhCltFct cdC = beanClt.singleSelectEntJPQL(raisonSociale);
                            MhReservation rsvs = beanRsv.findByCodeR(cdR);
                            
                            if(!raisonSociale.equals("")) beanRsvFct.updateJPQL(rsvs, "codeC", cdC);
                            
                             if(!raisonSociale.equals("") && repeatIt){
                                 MhConvention conv = beanConv.getByCodeConvention(COD.cd_convention(code_h, raisonSociale));
                                if(conv != null) beanRsv.updatePrcReduc(conv.getPrcReduction(), numFct);
                                 repeatIt = false;
                                }
                            
                    }
                    
                    raisonSociale = "";           
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Facture crée avec succès.", ""));
                 }
            }
           
            
            }

     public void annulerFct(){
         FacesContext context = FacesContext.getCurrentInstance();
         try {
             
             bean.AnnulerFct(beanHotel.htl().getCodeH()+"_"+numFctPdf);  
             context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Facture Annuler avec succès.", ""));
         } catch (Exception e) {
             context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur:"+e, ""));
         }
         
     } 
     
//++++++++++++++++++++++++++LE PDF+++++++++++++++++++++++++++++++++++

    
    
 JasperPrint jasperprint;
    
    public void init01() throws JRException{
        
          // codification COD = new codification();
            nbr NBR = new nbr();
        
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
        String str = beanHotel.htl().getCodeH()+"_"+numFctPdf;
        ListFctSlt = beanRsvFct.findByNumFct(str);
        
        Date dtFct = new Date();
        String num_fct = "";
        String num_chq= "";
        String rmq= "";
        String code_client = "";
        double prix = 0;
        double prixSReduction = 0;
        int prcRed = 0;

        
        for(MhRsvvueglobal ListFctSlt1:ListFctSlt){
           dtFct = ListFctSlt1.getDates();
           num_fct = ListFctSlt1.getNumFct();
           if(ListFctSlt1.getCodeC()!=null)
           code_client = ListFctSlt1.getCodeC();
           num_chq = ListFctSlt1.getNumCheque();
           rmq = ListFctSlt1.getRemarque(); 
           break;
        }
        
        String verif="";
        Vector vct = new Vector();
        double totHt = 0;
        float ttCharge = 0;
        float txsj = 0;
        int nbPrs = 0;
        int nbNuitee = 0;
        int indice = 0;
        
        
        String typePaiement = "";
        boolean cltExonor = false;
        
        for(MhRsvvueglobal ListFctSlt1:ListFctSlt){
            if(verif.equals(ListFctSlt1.getCodeR())){
                vct.add(indice);
            }else{
                int nbPersonne = beanCSCh.nbClientOnRsv(ListFctSlt1.getCodeR());
                nbPrs = nbPrs + nbPersonne;
                nbNuitee = nbNuitee + ListFctSlt1.getNbNuitee();
                txsj = ListFctSlt1.getTaxeSj();
                typePaiement = ListFctSlt1.getTypePaiement();
                float charge =(float) beanChargeSupp.selectSomme(ListFctSlt1.getCodeR());
                ttCharge = ttCharge + charge;
                prix = THT(ListFctSlt1.getNbNuitee(), ListFctSlt1.getPrixU(), ListFctSlt1.getPensionC(), ListFctSlt1.getPrcPension(), ListFctSlt1.getPension(),0,nbPersonne, ListFctSlt1.getPrcReduction() );
               
                prixSReduction = THT(ListFctSlt1.getNbNuitee(), ListFctSlt1.getPrixU(), ListFctSlt1.getPensionC(), ListFctSlt1.getPrcPension(), ListFctSlt1.getPension(),0,nbPersonne, 0);
                prcRed = ListFctSlt1.getPrcReduction() ;
                                
                ListFctSlt1.setPrixU((float)prix);
                totHt = totHt + prix ;
                verif = ListFctSlt1.getCodeR();
                ListFctSlt1.setPrixU((float)prixSReduction);
                indice++;
            }
        }
        
        for(int i = 0; i<vct.size(); i++){
          
        
            int x = NBR.toInt(vct.elementAt(i)+"");
            ListFctSlt.remove(x);
        }
        
        
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(ListFctSlt);
        
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        List<MhHotel> tabInfo = beanHotel.SingleSelectTab(code_h);
        boolean soumisTva = false;
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
            soumisTva = tabInfo1.getTvaS();
            break;
        }
        parameters.put("logoLink", "..\\"+code_h+"\\logo\\logo_RS.jpg");
        
        
        List<MhCltFct> tabInfoClt = beanEntreprise.SingleSelectTab(code_client);
        
         parameters.put("nbl", num_fct.substring(num_fct.length()-9, num_fct.length()));
         parameters.put("dts", new SimpleDateFormat("dd/MM/yyyy").format(dtFct));
         parameters.put("typePaiement", typePaiement);
         
         for(MhCltFct tabInfoClt1:tabInfoClt){
            parameters.put("rc_client", tabInfoClt1.getNrc());
            parameters.put("nai_client", tabInfoClt1.getNai());
            parameters.put("nif_client", tabInfoClt1.getNif());
            parameters.put("cd_client", code_client);
            parameters.put("nm_client", tabInfoClt1.getRaisonSociale());
            
            if(!typePaiement.equals("Cheque"))num_chq = "/";
            parameters.put("n_cmd", num_chq); 
            parameters.put("remarque",rmq);
            parameters.put("adresse_client", tabInfoClt1.getAdresse());
            cltExonor = tabInfoClt1.getExonore();
            break;
         }
         
         parameters.put("chargeSp", NBR.dbToDf(ttCharge)+" Da.");
         //----------parametre HT---------------------- 
         totHt = totHt + ttCharge;
         parameters.put("Tht", NBR.dbToDf(totHt)+" Da.");
         //----------parametre Taxe de séjour----------- 
        
         
        //----------parametre TVA----------------------  
         double tva = totHt;
         double tvaValeur = 0;
         
         if(!soumisTva){
             parameters.put("tva", "Non Soumis.");
         }else
         if(!cltExonor) {
             tvaValeur = tva(tva);
             parameters.put("tva", NBR.dbToDf(tvaValeur)+" Da.");
         }else
           parameters.put("tva", "Client Exonoré.");
         
           parameters.put("TaxeSj", NBR.dbToDf(TaxeSj(nbNuitee, txsj, nbPrs))+" Da.");
        //----------parametre Droit de timbre----------------------  
         double dtimbre = tva +  tvaValeur + TaxeSj(nbNuitee, txsj, nbPrs);
         double dtimbreValue = 0;
         
         if(typePaiement.equals("Espece")) {
             dtimbreValue = dTimbre(dtimbre);
             nbr.majorDb(dtimbreValue);
         }
            parameters.put("dTimbre", NBR.dbToDf(dtimbreValue)+" Da.");
            if(prcRed > 0)parameters.put("prcRed", "Une réduction de "+prcRed+"% est appliquée au total hors taxes." );
            parameters.put("totLettre", NBR.EnLettre(NBR.db2Digits(TTC(totHt, tvaValeur, TaxeSj(nbNuitee, txsj, nbPrs), dtimbreValue, typePaiement, cltExonor)), "dinars Algeriens")); 
        //----------parametre TTC----------------------     
            parameters.put("ttc", NBR.dbToDf(TTC(totHt, tvaValeur, TaxeSj(nbNuitee, txsj, nbPrs), dtimbreValue, typePaiement, cltExonor))+" Da.");
        
            
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\facture\\report.jasper");
        File f = new File(relativeWebPath); 
        jasperprint = JasperFillManager.fillReport(f.getPath(), parameters, beanDataSource);    
    }
    

    public void PDF(ActionEvent event) throws JRException, IOException{
             FacesContext context = FacesContext.getCurrentInstance();
          if(numFctPdf.equals("")){
                       context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veillez choisir une facture svp !!", ""));
            }else{
                init01();
                 ServletOutputStream servletOutputStream = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).getOutputStream();
                 JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);

                         servletOutputStream.flush();
                         servletOutputStream.close();
          }
        
      
    }
    
    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Selected Invoice", ((MhRsvvueglobal) event.getObject()).getNumFct());
       
        numFctPdf = ((MhRsvvueglobal) event.getObject()).getNumFct();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
//-------------------------Fin PDF-----------------------------------  
    private String numCheque;
    private String remarque;
    private boolean etatNmChq;

    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    
    
    public String getNumCheque() {
        return numCheque;
    }

    public void setNumCheque(String numCheque) {
        this.numCheque = numCheque;
    }

    public boolean isEtatNmChq() {
        return etatNmChq;
    }

    public void setEtatNmChq(boolean etatNmChq) {
        this.etatNmChq = etatNmChq;
    }
    
    public void chqSelected(){
         etatNmChq = typeP.equals("Cheque");
         numCheque = "";
         remarque = "";
    }

//-******************************************-
   public void init02() throws JRException{
        
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
          // codification COD = new codification();
            nbr NBR = new nbr();
        
        String str = beanHotel.htl().getCodeH()+"_"+numFctPdf;
        ListFctSlt = beanRsvFct.findByNumFct(str);
        List<MhChargeSupp> lstCharge = new ArrayList<MhChargeSupp>();
        Date dtFct = new Date();
        String num_fct = "";
        String code_client = "";        
        
        for(MhRsvvueglobal ListFctSlt1:ListFctSlt){
           dtFct = ListFctSlt1.getDates();
           num_fct = ListFctSlt1.getNumFct();
           if(ListFctSlt1.getCodeC()!=null)
           code_client = ListFctSlt1.getCodeC();
           break;
        }
        
        float totHt = 0;
        for(MhRsvvueglobal ListFctSlt1:ListFctSlt){
             // List<MhChargeSupp> lstChargeA = null;
                try {
                    lstCharge.addAll(beanChargeSupp.select(ListFctSlt1.getCodeR()));
                } catch (Exception e) {
                    System.err.println(e);
                }
        }
        
        for(MhChargeSupp lstch:lstCharge){
            totHt = totHt + lstch.getPrix_ch();
        }
        
        
        
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(lstCharge);
        
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        List<MhHotel> tabInfo = beanHotel.SingleSelectTab(code_h);
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
        
        
        List<MhCltFct> tabInfoClt = beanEntreprise.SingleSelectTab(code_client);
        
         parameters.put("nbl", num_fct.substring(num_fct.length()-9, num_fct.length()));
         parameters.put("dts", new SimpleDateFormat("dd/MM/yyyy").format(dtFct));
         
         for(MhCltFct tabInfoClt1:tabInfoClt){
            parameters.put("cd_client", code_client);
            parameters.put("nm_client", tabInfoClt1.getRaisonSociale());
            break;
         }
             
         parameters.put("Tht", NBR.dbToDf(totHt)+" Da.");
         
         
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\facture\\charge.jasper");
        File f = new File(relativeWebPath); 
        jasperprint = JasperFillManager.fillReport(f.getPath(), parameters, beanDataSource);
        
    }    
    
  public void Chrg(ActionEvent event) throws JRException, IOException{
     FacesContext context = FacesContext.getCurrentInstance();

      if(numFctPdf.equals("")){
                       context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veillez choisir une facture svp !!", ""));
      }else{
         init02();
        ServletOutputStream servletOutputStream = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);
              
                servletOutputStream.flush();
                servletOutputStream.close();  
      }
      
       
    }
}
