/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.agc;

import bean.agc.mhAgenceBean;
import controller.MhHotelController;
import fct.codification;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.agc.MhAgence;
import model.MhHotel;

@ManagedBean
@SessionScoped
public class mhAgenceController {
      public MhAgence agcBean;
    
//--------------------------
    
    private boolean accept; 
    private boolean activate = false; 

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

 
    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
 
    
    
    public void addMessage() {
        activate = !accept;
    }
    
//------------------------------------------  
    private String code_h;
    private String raison_Social;
    private String adresse;
    private String commune;
    private String wilaya;
    private String codePostal;
    
    private String nrc;
    private String nif;
    private String nai; 
    private String rib;
    
    private String tel;
    private String fax;
    private String mail;
       
    private boolean compte;
    private Date dtAdaptation;
    private Date dtContrat;
    private String numContrat;
    private int indice;
    
       
    private String description;
    

    public String getRaison_Social() {
        return raison_Social;
    }

    public void setRaison_Social(String raison_Social) {
        this.raison_Social = raison_Social;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
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

    public boolean isCompte() {
        return compte;
    }

    public void setCompte(boolean compte) {
        this.compte = compte;
    }

    public Date getDtAdaptation() {
        return dtAdaptation;
    }

    public void setDtAdaptation(Date dtAdaptation) {
        this.dtAdaptation = dtAdaptation;
    }

    public Date getDtContrat() {
        return dtContrat;
    }

    public void setDtContrat(Date dtContrat) {
        this.dtContrat = dtContrat;
    }

    public String getNumContrat() {
        return numContrat;
    }

    public void setNumContrat(String numContrat) {
        this.numContrat = numContrat;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getCode_h() {
        return code_h;
    }

    public void setCode_h(String code_h) {
        this.code_h = code_h;
    }
    

     public void confirme() {
            
        //    MhAgence slt;
            codification cod = new codification();
        
                    MhAgence ben = new MhAgence();
                    ben.setCodeA(cod.cd_structure(mail));
                    ben.setRaisonSocial(raison_Social);
                    ben.setAdresse(adresse);
                    ben.setCommune(commune);
                    ben.setWilaya(wilaya);
                    ben.setCodePostal(codePostal);
                    ben.setNrc(nrc);
                    ben.setNai(nai);
                    ben.setNif(nif);
                    ben.setRib(rib);
                    ben.setTel(tel);
                    ben.setFax(fax);
                    ben.setMail(mail);
                    ben.setCompte(false);

                    ben.setDateAdaptation(new Date());
                    ben.setDateContrat(null);
                    ben.setIndiceP(0);
                   
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("confirmation/confirmation.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(MhHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                  agcBean = ben;
         
    }
   
     @Resource(lookup = "mail/finafor")
   private Session mailSession;

    public boolean sendEmail(String to, String subject, String body) {

        MimeMessage message = new MimeMessage(mailSession);

        try {

            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
            InternetAddress[] address = {new InternetAddress(to)};
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setText(body);
            Transport.send(message);

        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
     
      public void save() {        
              System.out.println("start to send");
             
              String titre = "Mon Hotel Enregistrement No-Replay";
              String text  = "Bonjour "+raison_Social+", \nBienvenue sur Mon Hôtel.\nLe système vous permet une meilleure gestion, vision et visibilité de votre Agence.\n"
                           + "Une fois la confirmation de vos informations effectuée au plus tard 48H le système vous transmettera un mail de confirmation contenant votre code agence, identifiant ainsi que votre mot de passe initial.\n"
                           + "Remarque:\nUne fois connecter au système merci de bien vouloir changer le mot de passe par défaut et de bien garder votre code agence.\n"
                           + "Cordialement Mon Hotel.\n"
                           + "Mob : 0670 29 85 33."
                           + "\nAdresse: ..........";
              
              if(sendEmail(mail,titre,text)){
                  bean.insert(agcBean);
              }
              System.out.println("finish sending");
                        

             try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("../connexion/connexion.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(MhHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

//------------------------------------------    
    private MhHotel hotel;

    public MhHotel getHotel() {
        return hotel;
    }

    public void setHotel(MhHotel hotel) {
        this.hotel = hotel;
    }
//------------------------------------------    
    
   @EJB 
   private mhAgenceBean bean;
   private List<MhAgence>  listMhAgence;

    public List<MhAgence> getListMhAgence() {
            this.listMhAgence = bean.getAgcList();
            List<MhAgence> lst = new ArrayList<MhAgence>();
            for(MhAgence agc : listMhAgence){
                if(!agc.getNrc().contains("nean")){
                    lst.add(agc);
                }
            }
            listMhAgence = lst;
        return listMhAgence;
    }

    public void setListMhAgence(List<MhAgence> listMhAgence) {
        this.listMhAgence = listMhAgence;
    }
    
    
  
 //------------------------------------------


}
