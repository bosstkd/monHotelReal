/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.particulier;

import bean.MhCltSChBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class partMenuController {
    
    
    private String iFrameUrl;  

    public String getiFrameUrl() {
        return iFrameUrl;
    }

    public void setiFrameUrl(String iFrameUrl) {
        this.iFrameUrl = iFrameUrl;
    }

    
     private String panelTitle = "";

    public String getPanelTitle() {
        return panelTitle;
    }

    public void setPanelTitle(String panelTitle) {
        this.panelTitle = panelTitle;
    }
    
    
    
    @EJB
    MhCltSChBean beanClt;

    public String getNomPrenom() {
        return beanClt.clt().getNomPrenom();
    }
    
    public String getNpid() {
        return beanClt.clt().getNpid();
    }

//------------------------------------------
        
 
    
     public String iFrameUrl(String x){
        switch (x) {
            case "jrnl":
                iFrameUrl = "http://www.pressealgerie.fr/Lecteur-journaux/index.html";
                panelTitle = "Journaux";
                break;
            case "meteo":
                iFrameUrl = "https://www.tameteo.com/meteo-Afrique-Algerie-1-5-63.html";
                panelTitle = "Météo";
                break;
            case "config":
                iFrameUrl = "particulier/configuration/configuration.xhtml";
                panelTitle = "Configuration";
                break;
            case "rsv":
                iFrameUrl = "particulier/demandeR/demandeRsv.xhtml";
                panelTitle = "Demande de réservation";
                break;
            case "voyage":
                iFrameUrl = "particulier/voyage/indexACon.xhtml";
                panelTitle = "Inscription voyage";  
                break;
            case "publication":
                iFrameUrl = "particulier/publicationPart/publicationPart.xhtml";
                panelTitle = "Création de publication";  
                break; 
            case "rnbInsc":
                iFrameUrl = "particulier/inscPartPub/inscPartPub.xhtml";
                panelTitle = "RnB inscription";  
                break; 
            case "mouv":
                iFrameUrl = "particulier/demRClt/demandeRCltPart.xhtml";
                panelTitle = "Mes Mouvements";  
                break; 
            case "liste":
                iFrameUrl = "particulier/demRClt/insPart2Part.xhtml";
                panelTitle = "Liste des inscrits à mes publications";  
                break; 
            case "home":
                iFrameUrl = "particulier/accueil/accueil.xhtml";
                panelTitle = "Accueil";  
                break;   
            case "Premium":
                iFrameUrl = "particulier/premium/premium.xhtml";
                panelTitle = "Premium";  
                break;     
            default:
                break;
        }
         
         return iFrameUrl;
  }
    
     
     
     @PostConstruct
     public void init(){
          iFrameUrl = "particulier/accueil/accueil.xhtml";
          panelTitle = "Accueil"; 
     }
    
}
