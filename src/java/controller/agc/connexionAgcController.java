/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;

import bean.MhDemandeCltBean;
import bean.agc.mhAgenceBean;
import controller.Util;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhDemandeClt;

/**
 *
 * @author Amine
 */
@ManagedBean
@SessionScoped
public class connexionAgcController {
    
   @EJB
   private mhAgenceBean bean;
 //------------------------------   
    private String code_a;
    private String code_u;
    private String email;
    private String psw;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    public String getCode_a() {
        return code_a;
    }

    public void setCode_a(String code_a) {
        this.code_a = code_a;
    }

    public String getCode_u() {
        return code_u;
    }

    public void setCode_u(String code_u) {
        this.code_u = code_u;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    
    
    public boolean isUserExist(String code_a, String email, String psw){
        return !bean.selectCodeUserEmail(email, code_a, psw).equals("");
    }
    
    
    public String doLogin(){

        if(!isUserExist(code_a, email, psw)){
             FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier le nom d'utilisateur et le mot de passe svp !!", ""));
            return "";
        }else{
            HttpSession hs = Util.getSession();
            hs.setAttribute("code_a", code_a);
            hs.setAttribute("psw", psw);
            hs.setAttribute("email", email);
            return "/a_application/AgenceAppFirst.xhtml";
        }

    }
    
      public String doLogout(){
            HttpSession hs = Util.getSession();
            hs.invalidate();

            return "/agencePanel/connexion/connexion.xhtml?faces-redirect=true";
      }
      
      
      
     private int number;
 
    public int getNumber() {
        return number;
    }
    
     @EJB
    MhDemandeCltBean beanClt;  
      
    public List<MhDemandeClt> listDemAgc;

    public List<MhDemandeClt> getListDemAgc() {
        listDemAgc = beanClt.getListDemandeAgc();
        return listDemAgc;
    }

    public void setListDemAgc(List<MhDemandeClt> listDemAgc) {
        this.listDemAgc = listDemAgc;
    }
      
      public void increment() {
        getListDemAgc();
        
        if(listDemAgc == null) 
            number = 0;
        else
            number = listDemAgc.size();
        
      
        if(number > 0){
            color = "red";
            ico = "fa fa-bell-o";
        }else{
            color = "black";
            ico = "fa fa-bell";
        }
    }
    
    private String ico;

    public String getIco() {
        return ico;
    }
    
    private String color;

    public String getColor() {
        return color;
    }
    
    @PostConstruct
    public void init(){
        ico = "fa fa-bell";
        color = "black";
        listDemAgc = new ArrayList<MhDemandeClt>();
    }
    
}
