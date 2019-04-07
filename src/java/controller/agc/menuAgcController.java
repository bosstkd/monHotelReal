package controller.agc;



import bean.agc.MhDroitBeanAgc;
import bean.agc.MhUsersABean;
import bean.agc.mhAgenceBean;
import controller.Util;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.agc.MhCompteUserA;
import model.agc.MhUsersDroitsAgc;
 
@ManagedBean
public class menuAgcController {

    
    private boolean compteUser;
    private boolean informationPerso;
    private boolean clients;
    private boolean reservation;
    private boolean publication;
    private boolean demandeRsv;
    private boolean demandeCvt;
    private boolean statuts;
    private boolean demandeRsvPart;
   
   private String agcence;

    public String getAgcence() {
        return agcence;
    }

    public void setAgcence(String agcence) {
        this.agcence = agcence;
    }
   
   

    public boolean isCompteUser() {
        return compteUser;
    }

    public boolean isInformationPerso() {
        return informationPerso;
    }

    public boolean isClients() {
        return clients;
    }


    public boolean isReservation() {
        return reservation;
    }

    public boolean isPublication() {
        return publication;
    }

    public void setPublication(boolean publication) {
        this.publication = publication;
    }

    public boolean isDemandeRsv() {
        return demandeRsv;
    }

    public void setDemandeRsv(boolean demandeRsv) {
        this.demandeRsv = demandeRsv;
    }

    public boolean isDemandeCvt() {
        return demandeCvt;
    }

    public void setDemandeCvt(boolean demandeCvt) {
        this.demandeCvt = demandeCvt;
    }

    public boolean isStatuts() {
        return statuts;
    }

    public void setStatuts(boolean statuts) {
        this.statuts = statuts;
    }

    public boolean isDemandeRsvPart() {
        return demandeRsvPart;
    }

    public void setDemandeRsvPart(boolean demandeRsvPart) {
        this.demandeRsvPart = demandeRsvPart;
    }

   
    
    
    
//---------------------------------------------------------    
    public void save() {
        addMessage("Success", "Data saved");
    }
     
    public void update() {
        addMessage("Success", "Data updated");
    }
     
    public void delete() {
        addMessage("Success", "Data deleted");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    private String panelTitle = "";

    public String getPanelTitle() {
        return panelTitle;
    }

    public void setPanelTitle(String panelTitle) {
        this.panelTitle = panelTitle;
    }
    
    
    
@EJB
MhDroitBeanAgc beanDroit;
@EJB
MhUsersABean beanUser;
@EJB
mhAgenceBean beanAgc;    

private String iFrameUrl;  
@PostConstruct
public void init(){
    agcence = beanAgc.agc().getRaisonSocial();
    panelTitle = "BIENVENUE";
    iFrameUrl = "../../a_application/style/vide.xhtml";
    
                HttpSession hs = Util.getSession();
                String mail = (String) hs.getAttribute("email");
                MhCompteUserA user = beanUser.singleSelectByMail(mail);
                if(!user.getTypeUser().equals("Opérateur")){
                    compteUser= false;
                    informationPerso= false;
                    clients= false;
                    reservation= false;
                    publication= false;
                    demandeRsv= false;
                    demandeCvt= false;
                    statuts= false;
                    demandeRsvPart= false;
   
                 
                }else{
                    MhUsersDroitsAgc usr = beanDroit.getByCodeU(user.getCodeU());
                    compteUser= !usr.getCompteUser();
                    informationPerso= !usr.getInfos();
                    clients= !usr.getClients();
                    reservation= !usr.getReservation();
                    publication= !usr.getPublication();
                    demandeRsv= !usr.getDemReservation();
                    demandeCvt= !usr.getDemandeConv();
                    statuts= !usr.getStatuts();
                    demandeRsvPart= false;
                }

}    
   
   public String getiFrameUrl() {
        return iFrameUrl;
    }

    public void setiFrameUrl(String iFrameUrl) {
        this.iFrameUrl = iFrameUrl;
    }
    
    public void iFrameUrl(String str){
       switch (str) {
          
           case "utilisateur":
               iFrameUrl = "../../a_application/compteUser/users.xhtml";
               panelTitle = "Configuration utilisateurs";
               break;
           case "infos":
               iFrameUrl = "../../a_application/infoPerso/infoPerso.xhtml";
               panelTitle = "Configuration informations personnels";
               break;
          
          
           case "clientSCh":
               iFrameUrl = "../../a_application/clients/clientSChambre.xhtml";
               panelTitle = "Configuration Clients";
               break; 
           case "rsv":
               iFrameUrl = "../../a_application/reservation/rsv.xhtml";
               panelTitle = "Liste des Hôtels conventionnées";
               break;   
               
           case "demRsv":
               iFrameUrl = "../../a_application/demandeRsv/demandeRsv.xhtml";
               panelTitle = "Demande de réservation";
               break;  
               
            case "demCvt":
               iFrameUrl = "../../a_application/demandeCvt/demandeCvt.xhtml";
               panelTitle = "Demande convention";
               break;   
               
            case "pub":
               iFrameUrl = "../../a_application/publication/publication.xhtml";
               panelTitle = "Publication";
               break; 
              
            case "statuts":
               iFrameUrl = "../../a_application/statuts/statuts.xhtml";
               panelTitle = "Statuts";
               break; 
               
            case "demPart":
               iFrameUrl = "../../a_application/demRClt/demandeRClt.xhtml";
               panelTitle = "Demande réservation particulier";
               break;
             
           default:
               iFrameUrl = "../../a_application/style/vide.xhtml";
               panelTitle = "Configuration utilisateurs";
               break;
       }
  }
    
  
    
    
}