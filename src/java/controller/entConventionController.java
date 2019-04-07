/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MhConventionBean;
import bean.MhEntrepriseBean;
import bean.MhHotelBean;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.MhConvention;
import model.MhHotel;

@ManagedBean
@SessionScoped
public class entConventionController {
    
    
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
    
    
    private String numCh;
    private Date du;
    private Date au;
    private String codeConvention;
    private String codeH;
    private String codeC;

    
    
    
    
    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public Date getDu() {
        return du;
    }

    public void setDu(Date du) {
        this.du = du;
    }

    public Date getAu() {
        return au;
    }

    public void setAu(Date au) {
        this.au = au;
    }

    public String getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(String codeConvention) {
        this.codeConvention = codeConvention;
    }

    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
    }

    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }
    
    
    @EJB 
    MhConventionBean beanConv;
    
    @EJB
    MhEntrepriseBean beanEnt;
    
    @EJB
    MhHotelBean beanH;

//------------------------------------------
    
    public String raisonSociale(){
         MhConvention conv = convention();
        return conv.getCodeC().getRaisonSociale();
    }
    
    public String nomHotel(){
         MhConvention conv = convention();
        return conv.getCodeH().getRaisonSocial();
    }
    
    public String codeHotel(){
         MhConvention conv = convention();
        return conv.getCodeH().getCodeH();
    }
    
    
    public MhConvention convention(){
        HttpSession hs = Util.getSession();
        String codeConv = (String) hs.getAttribute("codeConvention");
        return beanConv.getByCodeConvention(codeConv);
    }
    
    public MhHotel hotel(String codeH){
        return convention().getCodeH();
    }
    
     public String iFrameUrl(int x){
         if(x==1){
             iFrameUrl = "e_interface/demandeR/demandeR.xhtml";
             panelTitle = "Demande de réservation";
         }else{
             iFrameUrl = "e_interface/clientEnt/clientSChambreEnt.xhtml";
             panelTitle = "Inscription";
         }
         
         return iFrameUrl;
  }
    
     
    
}
