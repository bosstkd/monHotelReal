/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.MhCltSChBean;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean
@ViewScoped
public class contactsController {
    
  @Resource(lookup = "mail/finafor")
   private Session mailSession;

  
  private String mail;
  private String sujet;
  private String text;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
  
  
  
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
    @EJB
    MhCltSChBean bean;
    
    public void  confirmer(){
            FacesContext context = FacesContext.getCurrentInstance();
        try {    
            
            String titre = sujet;
            String text  = this.text;

               if(sendEmail("a-ek@hotmail.fr", titre, "Mail de :"+mail+".\n"+text)){
                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Email envoyer avec succés.", ""));
                     sujet = "";
                     this.text = "";
                    
               }else{
                     context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication", ""));
               }
           
        } catch (Exception e) {
                     context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication", ""));
        }
            
            
    }
    
}
