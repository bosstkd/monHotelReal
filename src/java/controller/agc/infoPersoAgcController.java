/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;

import bean.agc.mhAgenceBean;
import controller.Util;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhHotel;
import model.agc.MhAgence;

@ManagedBean
@SessionScoped
public class infoPersoAgcController {
    
    
    
     @EJB 
        private mhAgenceBean bean;
        private List<MhAgence>  listMhAgence;

            public List<MhAgence> getListMhAgence() {
                    this.listMhAgence = bean.getAgcList();
                return listMhAgence;
            }

            public void setListMhAgence(List<MhAgence> listMhAgence) {
                this.listMhAgence = listMhAgence;
            }
    
    
    private String adresse;
    private String nrc;
    private String nif;
    private String nai;
    private String rib;
    private String tel;
    private String fax;
    private String mail;
   

    private String description;
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNai() {
        return nai;
    }

    public void setNai(String nai) {
        this.nai = nai;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
            
    
   @PostConstruct
    public void init(){
        chargement();
    }
    
    public void chargement(){
            
            MhAgence agence = bean.agc();
        
            adresse = agence.getAdresse();
            nrc = agence.getNrc();
            nai = agence.getNai();
            nif = agence.getNif();
            rib = agence.getRib();
            tel = agence.getTel();
            fax = agence.getFax();
            mail = agence.getMail();
         
            
            try{
               description = agence.getDescription();
            }catch(Exception e){
               description = "";
            }
           
   
       
    }
    
    public void modifier(){

          MhAgence agence = new MhAgence();
          agence.setAdresse(adresse);
          agence.setNrc(nrc);
          agence.setNai(nai);
          agence.setNif(nif);
          agence.setRib(rib);
          agence.setTel(tel);
          agence.setFax(fax);
          agence.setMail(mail);
        
          agence.setDescription(description);
          
          FacesContext context = FacesContext.getCurrentInstance();
          if( bean.update(agence)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Mise à jour d'information effectué avec succées.", ""));
          }else{
                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur de mise à jour veuillez vérifier les informations introduites svp !!", ""));
          }
        
    }
}
