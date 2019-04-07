package controller;

import bean.MhHotelBean;
import fct.codification;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.MhHotel;


@ManagedBean
@SessionScoped
public class MhHotelController {
    
    public MhHotel htlBean;
    
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
    
    private int etoil;
    private float pension_c;
    private int prc_demi_pension;
    private float taxe_sj;
    private boolean tva_s = true;
    
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

    public int getEtoil() {
        return etoil;
    }

    public void setEtoil(int etoil) {
        this.etoil = etoil;
    }

    public float getPension_c() {
        return pension_c;
    }

    public void setPension_c(float pension_c) {
        this.pension_c = pension_c;
    }

    public int getPrc_demi_pension() {
        return prc_demi_pension;
    }

    public void setPrc_demi_pension(int prc_demi_pension) {
        this.prc_demi_pension = prc_demi_pension;
    }

    public float getTaxe_sj() {
        return taxe_sj;
    }

    public void setTaxe_sj(float taxe_sj) {
        this.taxe_sj = taxe_sj;
    }

    public boolean isTva_s() {
        return tva_s;
    }

    public void setTva_s(boolean tva_s) {
        this.tva_s = tva_s;
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
            
            String slt;
            codification cod = new codification();
            try {
                     slt = bean.selectSingle(" code_h like '"+cod.cd_structure(raison_Social)+"' ");
                } catch (Exception e) { 
                     slt = "";
                                       }
           
            
            if(raison_Social.equals(slt)){
              
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Raison sociaele déja existante.", ""));
                    
            }else{
                    MhHotel ben = new MhHotel();
                    ben.setCodeH(cod.cd_structure(raison_Social));
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
                    ben.setTvaS(tva_s);
                    ben.setTva(9);
                    ben.setPrcDemiPension(prc_demi_pension);
                    ben.setPensionC(pension_c);
                    ben.setTaxeSejour(taxe_sj);
                    ben.setEtoile(etoil);
                    ben.setRestaurant(false);
                    ben.setBar(false);
                    ben.setCafeteria(false);
                    ben.setWifi(false);
                    ben.setPicine(false);

                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("confirmation/confirmation.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(MhHotelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                  htlBean = ben;
         }
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
              String text  = "Bonjour "+raison_Social+", \nBienvenue sur Mon Hôtel.\nLe système vous permet une meilleure gestion, vision et visibilité de votre Hôtel.\n"
                           + "Une fois la confirmation de vos informations effectuée au plus tard 48H le système vous transmettera un mail de confirmation contenant votre code hotel, identifiant ainsi que votre mot de passe initial.\n"
                           + "Remarque:\nUne fois connecter au système merci de bien vouloir changer le mot de passe par défaut et de bien garder votre code hotel.\n"
                           + "Cordialement Mon Hotel.\n\n"
                           + "Mob : 0670 298 533 / 0670 191 260."
                           + "\nAdresse: \nEl-Tarf Centre en face du superette le cerf.";
              
              if(sendEmail(mail,titre,text)){
                  bean.creer(htlBean);
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
   private MhHotelBean bean;
   private List<MhHotel>  listMhHotel;

    public List<MhHotel> getListMhHotel() {
            this.listMhHotel = bean.listMhHotel();
        return listMhHotel;
    }

    public void setListMhHotel(List<MhHotel> listMhHotel) {
        this.listMhHotel = listMhHotel;
    }
  
 //------------------------------------------


   
}
