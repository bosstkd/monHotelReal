/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MhConventionBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class connectionEntController {
    
    @EJB
    MhConventionBean bean;
    
    
    private String codeConvention;
    private String password;

    public String getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(String codeConvention) {
        this.codeConvention = codeConvention;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
     public boolean isUserExist(String codeConv){
        return bean.isConventionValide(codeConv);
    }
    
     public String doLogin(){
        FacesContext context = FacesContext.getCurrentInstance();
        if(!isUserExist(codeConvention)){
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Informations d'accées non valide !!", ""));
        }else{
            HttpSession hs = Util.getSession();
            hs.setAttribute("codeConvention", codeConvention);
            hs.setAttribute("psw", password);
            return "particulier/partApp.xhtml";
        }
        return "";
    }
    
      public String doLogout(){
            HttpSession hs = Util.getSession();
            hs.invalidate();
            return "/connexionEnt.xhtml?faces-redirect=true";
    }
    
    
}
