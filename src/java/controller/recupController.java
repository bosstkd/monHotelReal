package controller;

import bean.MhCltSChBean;
import fct.codification;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.MhCltSCh;

@ManagedBean
public class recupController {
    
    private String numPci;
    private String mail;

    public String getNumPci() {
        return numPci;
    }

    public void setNumPci(String numPci) {
        this.numPci = numPci;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    @EJB
    MhCltSChBean bean;
    
    public void  confirmer(){
            FacesContext context = FacesContext.getCurrentInstance();
            MhCltSCh clt = bean.getClientByNpid(numPci);
        try {    
            codification COD = new codification();
            String  psw = bean.getClientByNpid(numPci).getPsw();
            
            String titre = "Mon hotel password";
            String text  = "Bonjour "+clt.getNomPrenom()+", \nL'equipe mon hotel vous remercie pour votre confiance.\n"
                    + "\nIdentifiant: "+clt.getNpid()+"\n"
                    + "Mot de passe: "+psw+"\n"
                    + "\nBonne journée.\n"
                    + "Mon Hotel.";
            
            if(clt.getPsw() == null) bean.updatePsw(clt, psw);
        
            if(clt.getMail() == null){
                bean.updateMail(clt, mail);
               
                if(sendEmail(mail, titre, text)){
                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Email de coordonnées envoyer avec succés.", ""));
                }else{
                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez vérifier votre identifiant ou votre adresse mail.", "Pour plus d'informations veuillez nous contacter."));
                }
                
            }else if(clt.getMail().equals(mail)){
               if(sendEmail(mail, titre, text)){
                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Email de coordonnées envoyer avec succés.", ""));
                }else{
                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez vérifier votre identifiant ou votre adresse mail.", "Pour plus d'informations veuillez nous contacter."));
                 }
            }else{
                context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez vérifier votre identifiant ou votre adresse mail.", "Pour plus d'informations veuillez nous contacter."));
            }
        } catch (Exception e) {
                context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"N° d'identification inconnu !!", ""));
        }
            
            
    }
    
}
