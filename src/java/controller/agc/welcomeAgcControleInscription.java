
package controller.agc;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class welcomeAgcControleInscription {
    
    //--------------- Frame Url ----------------------------  
   private String iFrameUrl = "agencePanel/inscriptionAgence.xhtml";  

   
   public String getiFrameUrl() {
        return iFrameUrl;
    }

    public void setiFrameUrl(String iFrameUrl) {
        this.iFrameUrl = iFrameUrl;
    }
 
  public void iFrameUrl(int i){
       switch (i) {
           case 0:
               iFrameUrl = "agencePanel/inscriptionAgence.xhtml";
               break;
           case 1:
               iFrameUrl = "agencePanel/connexion/connexion.xhtml";
               break;
           case 2:
               iFrameUrl = "";
               break;
           default:
               iFrameUrl = "";
               break;
       }
  }
 //---------------------------------------------------- 
    
    
}
