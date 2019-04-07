package controller;



import bean.MhDroitBean;
import bean.MhHotelBean;
import bean.MhUsersHBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhCompteUserH;
import model.MhUsersDroits;
 
@ManagedBean
public class menuController {

    
    private boolean chambre;
    private boolean compteUser;
    private boolean informationPerso;
    private boolean clients;
    private boolean entreprises;
    private boolean reservation;
    private boolean chargeSupp;
    private boolean liberation;
    private boolean versement;
    private boolean gestionCaisse;
    private boolean facturation;
    private boolean calendrier;
    private boolean statistique;
    private boolean listePolice;
    private boolean convention;
    
    private String hotel;

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
       
   
    public boolean isChambre() {
        return chambre;
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

    public boolean isEntreprises() {
        return entreprises;
    }

    public boolean isReservation() {
        return reservation;
    }

    public boolean isChargeSupp() {
        return chargeSupp;
    }

    public boolean isLiberation() {
        return liberation;
    }

    public boolean isVersement() {
        return versement;
    }

    public boolean isGestionCaisse() {
        return gestionCaisse;
    }

    public boolean isFacturation() {
        return facturation;
    }

    public boolean isCalendrier() {
        return calendrier;
    }

    public boolean isStatistique() {
        return statistique;
    }

    public boolean isListePolice() {
        return listePolice;
    }

    public boolean isConvention() {
        return convention;
    }

    public void setConvention(boolean convention) {
        this.convention = convention;
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
MhDroitBean beanDroit;
@EJB
MhUsersHBean beanUser;
@EJB
MhHotelBean beanHtl;    
private String iFrameUrl;  
@PostConstruct
public void init(){
    hotel = beanHtl.htl().getRaisonSocial();
    panelTitle = "BIENVENUE";
    iFrameUrl = "../../h_application/style/vide.xhtml";
    
                HttpSession hs = Util.getSession();
                String mail = (String) hs.getAttribute("email");
                MhCompteUserH user = beanUser.singleSelectByMail(mail);
                if(!user.getTypeUser().equals("Opérateur")){
                    chambre= false;
                    compteUser= false;
                    informationPerso= false;
                    clients= false;
                    entreprises= false;
                    reservation= false;
                    chargeSupp= false;
                    liberation= false;
                    versement= false;
                    gestionCaisse= false;
                    facturation= false;
                    calendrier= false;
                    statistique= false;
                    listePolice= false;
                    convention = false;
                }else{
                    MhUsersDroits usr = beanDroit.getByCodeU(user.getCodeU());
                    chambre= !usr.getChambre();
                    compteUser= !usr.getCompteUser();
                    informationPerso= !usr.getInformationPerso();
                    clients= !usr.getClients();
                    entreprises= !usr.getEntreprises();
                    reservation= !usr.getReservation();
                    chargeSupp= !usr.getChargeSupp();
                    liberation= !usr.getLiberation();
                    versement= !usr.getVersement();
                    gestionCaisse= !usr.getGestionCaisse();
                    facturation= !usr.getFacturation();
                    calendrier= !usr.getCalendrier();
                    statistique= !usr.getStatistique();
                    listePolice= !usr.getListePolice();
                    convention = !usr.getConvention();
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
           case "chambre":
               iFrameUrl = "../../h_application/chambre/chambre.xhtml";
               panelTitle = "Configuration chambres";
               break;
           case "utilisateur":
               iFrameUrl = "../../h_application/compteUser/users.xhtml";
               panelTitle = "Configuration utilisateurs";
               break;
           case "infos":
               iFrameUrl = "../../h_application/infoPerso/infoPerso.xhtml";
               panelTitle = "Configuration informations personnels";
               break;
           case "entreprise":
               iFrameUrl = "../../h_application/entreprise/entreprise.xhtml";
               panelTitle = "Configuration entreprise";
               break;
           case "convention":
               iFrameUrl = "../../h_application/convention/convention.xhtml";
               panelTitle = "Configuration conventions Entreprises";
               break;    
           case "conventionAgc":
               iFrameUrl = "../../h_application/convention/conventionAgc.xhtml";
               panelTitle = "Configuration conventions Agences";
               break;    
           case "clientSCh":
               iFrameUrl = "../../h_application/clients/clientSChambre.xhtml";
               panelTitle = "Configuration Client sur chambre";
               break; 
           case "rsv":
               iFrameUrl = "../../h_application/reservation/reservation.xhtml";
               panelTitle = "Reservation de chambre";
               break;   
               
           case "chrg":
               iFrameUrl = "../../h_application/reservation/chargeSupp.xhtml";
               panelTitle = "Charge supplémentaire";
               break;  
               
            case "lbr":
               iFrameUrl = "../../h_application/liberation/liberation.xhtml";
               panelTitle = "Libération";
               break;   
               
            case "vrs":
               iFrameUrl = "../../h_application/versement/versement.xhtml";
               panelTitle = "Gestion des versements";
               break; 
              
            case "cas":
               iFrameUrl = "../../h_application/gestionCaisse/caisse.xhtml";
               panelTitle = "Gestion de caisse";
               break; 
               
            case "fct":
               iFrameUrl = "../../h_application/facture/facturation.xhtml";
               panelTitle = "Facturation";
               break;
             
            case "cld":
               iFrameUrl = "../../h_application/calendrier/calendrier.xhtml";
               panelTitle = "Calendrier des réservations";
               break; 
               
            case "stat":
               iFrameUrl = "../../h_application/statistique/statistique.xhtml";
               panelTitle = "Statistique";
               break; 
               
            case "polc":
               iFrameUrl = "../../h_application/etatPolice/etatPolice.xhtml";
               panelTitle = "Liste Police";
               break; 
            case "demPart":
               iFrameUrl = "../../h_application/demRClt/demandeRClt.xhtml";
               panelTitle = "Demandes réservation particuliers";
               break; 
           default:
               iFrameUrl = "../../h_application/style/vide.xhtml";
               panelTitle = "Configuration utilisateurs";
               break;
       }
  }
    
  
    
    
}