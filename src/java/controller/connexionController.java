/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MhDemandeCltBean;
import bean.MhHotelBean;
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
public class connexionController {
    
   @EJB
   private MhHotelBean bean;
 //------------------------------   
    private String code_h;
    private String code_u;
    private String email;
    private String psw;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    public String getCode_h() {
        return code_h;
    }

    public void setCode_h(String code_h) {
        this.code_h = code_h;
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
    
    
    
    public boolean isUserExist(String code_h, String email, String psw){
        return !bean.selectCodeUserEmail(email, code_h, psw).equals("");
    }
    
    
    public String doLogin(){

        if(!isUserExist(code_h, email, psw)){
             FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier le nom d'utilisateur et le mot de passe svp !!", ""));
            return "";
        }else{
            HttpSession hs = Util.getSession();
            hs.setAttribute("code_h", code_h);
            hs.setAttribute("psw", psw);
            hs.setAttribute("email", email);
            return "/h_application/hotelAppFirst.xhtml";
        }

    }
    
    private int number;
 
    public int getNumber() {
        return number;
    }
    
    @EJB
    MhDemandeCltBean beanClt;
    
    public List<MhDemandeClt> listDem;

    public List<MhDemandeClt> getListDem() {
        listDem = beanClt.getListDemandeA();
        return listDem;
    }
 
    public void increment() {
        getListDem();
        
        if(listDem == null) 
            number = 0;
        else
            number = listDem.size();
        
      
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
        listDem = new ArrayList<MhDemandeClt>();
    }
    
    
      public String doLogout(){
            HttpSession hs = Util.getSession();
            hs.invalidate();

            return "/hotelPanel/connexion/connexion.xhtml?faces-redirect=true";
      }
    
}
