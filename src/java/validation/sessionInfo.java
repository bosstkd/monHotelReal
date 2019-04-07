package validation;

import bean.MhCltSChBean;
import bean.MhConventionBean;
import bean.MhHotelBean;
import bean.agc.mhAgenceBean;
import controller.Util;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;
import model.MhCltSCh;
import model.MhConvention;
import model.MhHotel;
import model.agc.MhAgence;

@ManagedBean
public class sessionInfo {

 
@EJB    
MhConventionBean beanConv;   

 protected boolean existEnt(){
   MhConvention conv = new MhConvention();
      try {
        conv = beanConv.convention();
     } catch (Exception e) {
         return false;
     }
      return conv != null;
 }
 
 public String returnToEntCon() {
    // System.out.println("session exist : "+existEnt());
     if(!existEnt()){
          return "/connexionEnt.xhtml?faces-redirect=true";
     }else{
          return "";
     }
}
 
 
//---------------------------------- 
 
 @EJB
 MhHotelBean beanHtl;
  protected boolean existHtl(){
   MhHotel htl = new MhHotel();
 try {
        htl = beanHtl.htl();
     } catch (Exception e) {
         return false;
     }
      return htl != null;
 }
 
 
  public String returnToHtlCon() {
    // System.out.println("session exist : "+existEnt());
     if(!existHtl()){
          return "/hotelPanel/connexion/connexion.xhtml?faces-redirect=true";
     }else{
          return "";
     }
} 
  
  
  
 @EJB
 mhAgenceBean beanAgc;
  protected boolean existAgc(){
   MhAgence agc = new MhAgence();
 try {
        agc = beanAgc.agc();
     } catch (Exception e) {
         return false;
     }
      return agc != null;
 }
 
 
  public String returnToAgcCon() {
    // System.out.println("session exist : "+existEnt());
     if(!existAgc()){
          return "/agencePanel/connexion/connexion.xhtml?faces-redirect=true";
     }else{
          return "";
     }
} 
  
  
  
 public void doLogout(){
            HttpSession hs = Util.getSession();
            hs.invalidate();
      }

 
//-------------------------------------

 @EJB
 MhCltSChBean beanCltSCh;
  protected boolean existClt(){
   MhCltSCh clt = new MhCltSCh();
 try {
        clt = beanCltSCh.clt();
     } catch (Exception e) {
         return false;
     }
      return clt != null;
 }
  
   public String returnToPartCon() {
    // System.out.println("session exist : "+existEnt());
     if(!existClt()){
          return "/connexionPart.xhtml?faces-redirect=true";
     }else{
          return "";
     }
}
    
}
