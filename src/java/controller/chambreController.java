/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MhChambreBean;
import bean.MhHotelBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhChambre;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class chambreController {
    
    
    private MhChambre chambre;

    public MhChambre getChambre() {
        return chambre;
    }

    public void setChambre(MhChambre chambre) {
        this.chambre = chambre;
    }
    
    //------------liste des variable chambre----//
    private String code_h;
    private String num_ch;
    private int nb_place;
    private float prix;
    private int prc_gain_agc;
    private String type_ch;
    private boolean visible;

    public String getCode_h() {
        return code_h;
    }

    public void setCode_h(String code_h) {
        this.code_h = code_h;
    }

    public String getNum_ch() {
        return num_ch;
    }

    public void setNum_ch(String num_ch) {
        this.num_ch = num_ch;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getPrc_gain_agc() {
        return prc_gain_agc;
    }

    public void setPrc_gain_agc(int prc_gain_agc) {
        this.prc_gain_agc = prc_gain_agc;
    }

    public String getType_ch() {
        return type_ch;
    }

    public void setType_ch(String type_ch) {
        this.type_ch = type_ch;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
    
    
    
    //-------------liste chambre----------------//  
    @EJB
    MhChambreBean bean;
    
    @EJB
    MhHotelBean htlBean;
    
     private List<MhChambre> listChambre;
    
     public List<MhChambre> getListChambre() {
         
        this.listChambre = bean.listChambre();
        return listChambre;
    }

    public void setListChambre(List<MhChambre> listCalc) {
        this.listChambre = listCalc;
    }
 //--------------------Opérations---------------------
    
    public void confirme() {
            
           if(num_ch.equals("")){
                FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez insérer le N° de chambre svp !!.", ""));
           }else{
               
               String slt;
               HttpSession hs = Util.getSession();
                
            try {
                     slt = bean.singleSelect((String) hs.getAttribute("code_h")+"_"+num_ch).getNumCh();
                } catch (Exception e) { 
                     slt = "";
                                       }
            
             if(((String) hs.getAttribute("code_h")+"_"+num_ch).equals(slt)){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Chambre déja existante.", ""));
            }else{
                 
                 String codeH = (String) hs.getAttribute("code_h");
                 
                   MhChambre chm = new MhChambre();
                    
                  chm.setCodeH(htlBean.findByCodeH(codeH));
                   chm.setNumCh(codeH+"_"+num_ch);
                    chm.setNbPlace(nb_place);
                     chm.setPrix(prix);
                      chm.setPrcGainAgc(prc_gain_agc);
                       chm.setTypeCh(type_ch);
                        chm.setVisible(visible);
                         chm.setNumchApp(num_ch);
                         
                        bean.insert(chm);
            

                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Chambre ajouter avec succés.", ""));
         }
           }
    }
    
    
    public void modifier (){
        
        if(num_ch.equals("")){
                FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez insérer le N° de chambre svp !!.", ""));
           }else{
                 String slt;
               HttpSession hs = Util.getSession();
            try {
                     slt = bean.singleSelect((String) hs.getAttribute("code_h")+"_"+num_ch).getNumCh();
                } catch (Exception e) { 
                     slt = "";
                                       }
            
             if(((String) hs.getAttribute("code_h")+"_"+num_ch).equals(slt)){
                 
            String codeH = (String) hs.getAttribute("code_h");
                    
                    MhChambre chm = new MhChambre();
                    
                  chm.setCodeH(htlBean.findByCodeH(codeH));
                   chm.setNumCh(codeH+"_"+num_ch);
                    chm.setNbPlace(nb_place);
                     chm.setPrix(prix);
                      chm.setPrcGainAgc(prc_gain_agc);
                       chm.setTypeCh(type_ch);
                        chm.setVisible(visible);
                         chm.setNumchApp(num_ch);
                         
                        bean.update(chm);
                    
                    
                     FacesContext context = FacesContext.getCurrentInstance();
                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Chambre modifier avec succés.", ""));

             }else{
                 FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Numéro de chambre non existant !!", ""));
             }
           }
        
         
    }
    
    
    public void suppChambre(){
             if(num_ch.equals("")){
                FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez insérer le N° de chambre svp !!.", ""));
           }else{
                 String slt;
               HttpSession hs = Util.getSession();
            try {
                     slt = bean.singleSelect((String) hs.getAttribute("code_h")+"_"+num_ch).getNumCh();
                } catch (Exception e) { 
                     slt = "";
                                       }
            
             if(((String) hs.getAttribute("code_h")+"_"+num_ch).equals(slt)){
                   
                     
                        MhChambre chm = new MhChambre();
                        String codeH = (String)hs.getAttribute("code_h");
                        chm.setCodeH(htlBean.findByCodeH(codeH));
                         chm.setNumCh(codeH+"_"+num_ch);
                          chm.setNbPlace(nb_place);
                           chm.setPrix(prix);
                            chm.setPrcGainAgc(prc_gain_agc);
                             chm.setTypeCh(type_ch);
                              chm.setVisible(visible);
                               chm.setNumchApp(num_ch);
                         
                     FacesContext context = FacesContext.getCurrentInstance();
                 try {
                     if( bean.remove(chm)){
                        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Chambre supprimer avec succés.", ""));
                     }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Suppression impossible chambre utilisé.", ""));
                     }
                 } catch (Exception e) {
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Suppression impossible chambre utilisé.", ""));
                 }
                     
              
             }else{
                 FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Numéro de chambre non existant !!", ""));
             }
           }
    }
     
//----------------------------------------------------   
    
    @EJB
    MhHotelBean beanHtl;
    
    public void onRowSelect(SelectEvent event) {
        int taille = beanHtl.htl().getCodeH().length();
        String str = ((MhChambre) event.getObject()).getNumCh();
         
         FacesMessage msg = new FacesMessage("Chambre Selectionée :", str.substring(taille + 1, str.length()));
         
         num_ch = str.substring(taille+1, str.length());
         MhChambre chInfo = bean.singleSelect(str);
    
         nb_place = chInfo.getNbPlace();
         prix = chInfo.getPrix();
         prc_gain_agc = chInfo.getPrcGainAgc();
         type_ch = chInfo.getTypeCh();
         visible = chInfo.getVisible();
          
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    
 //----------------------------------------------------
    
}
