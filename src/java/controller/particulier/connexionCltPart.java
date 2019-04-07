/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.particulier;

import controller.*;
import bean.MhCltSChBean;
import bean.MhDemandeCltBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhCltSCh;

@ManagedBean
@ViewScoped
public class connexionCltPart {
    

    
    private String npid;
    private String psw;
    
 
    
  


  

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @EJB
    MhCltSChBean beanClt;
    
    private boolean verifClt(String npid, String psw){
        MhCltSCh  clt = beanClt.getClientByNpid(npid);
         if(clt == null) return false;
         if(clt.getPsw() == null) return false;
        
         return clt.getPsw().equals(psw);
    }
    
 
        
     @EJB
     MhDemandeCltBean bean;
    public String doLogin(){
       FacesContext context = FacesContext.getCurrentInstance();
  
     if(!verifClt(npid, psw)){
                   context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les informations d'identification svp !!", ""));
        }else {
          HttpSession hs = Util.getSession();
            hs.setAttribute("npid", npid);
            hs.setAttribute("psw", psw);
            return "particulier/partApp.xhtml";
        }
            return "";         
    }
    
     public String doLogout(){
            HttpSession hs = Util.getSession();
            hs.invalidate();
            return "/connexionPart.xhtml?faces-redirect=true";
    }

     
}
