
package controller;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class welcomeControle {
    
    //--------------- Frame Url ----------------------------  
   private String iFrameUrl = "hotelPanel/connexion/connexion.xhtml";  

   
   public String getiFrameUrl() {
        return iFrameUrl;
    }

    public void setiFrameUrl(String iFrameUrl) {
        this.iFrameUrl = iFrameUrl;
    }
 
  public void iFrameUrl(int i){
       switch (i) {
           case 0:
               iFrameUrl = "hotelPanel/inscriptionHotel.xhtml";
               break;
           case 1:
               iFrameUrl = "hotelPanel/connexion/connexion.xhtml";
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
