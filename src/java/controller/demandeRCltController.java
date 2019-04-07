package controller;

import bean.MhDemandeCltBean;
import fct.dt;
import java.util.Date;
import java.util.List;
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
import model.MhDemandeClt;

@ManagedBean
@SessionScoped
public class demandeRCltController {
    
    
    private MhDemandeClt demClt;

    public MhDemandeClt getDemClt() {
        return demClt;
    }

    public void setDemClt(MhDemandeClt demClt) {
        this.demClt = demClt;
    }
    
    
    
    @EJB
    MhDemandeCltBean bean;
    
    public List<MhDemandeClt> listDem;

    public List<MhDemandeClt> getListDem() {
        listDem = bean.getListDemande();
        return listDem;
    }

    public void setListDem(List<MhDemandeClt> listDem) {
        this.listDem = listDem;
    }
    
    

  
    
     public List<MhDemandeClt> listDemAgc;

    public List<MhDemandeClt> getListDemAgc() {
        listDemAgc = bean.getListDemandeAgc();
        return listDemAgc;
    }

    public void setListDemAgc(List<MhDemandeClt> listDemAgc) {
        this.listDemAgc = listDemAgc;
    }
    
    public List<MhDemandeClt> listDemPart;

    public List<MhDemandeClt> getListDemPart() {
        listDemPart = bean.getListDemandePart();
        return listDemPart;
    }

    public void setListDemPart(List<MhDemandeClt> listDemPart) {
        this.listDemPart = listDemPart;
    }
    
    

    public void supprimer(String codeDem){
               FacesContext context = FacesContext.getCurrentInstance();
              
                 MhDemandeClt dm = bean.getDemandeByCode(codeDem);
                     String nomClt = dm.getNpid().getNomPrenom();
                     String mail = dm.getNpid().getMail();
               
               if(bean.remove(codeDem)){
                   
                   
                    
                     dt DTS = new dt();
                     
                     String titre = "Mon Hôtel Demande Réservation rejeter";
                        String text  =  "Bonjour M. "+nomClt+",\n"+
                                        "L'équipe monhotel-dz.com vous annonce que votre réservation sur:\n"+
                                        dm.getCodeH().getRaisonSocial()+"\n"+
                                        "Du: "+DTS.form_Aff(dm.getDu())+" Au: "+DTS.form_Aff(dm.getAu())+" a été REJETER.\n"+
                                        "\nCoordialement \nMon Hotel.\n\n"+
                                        "Tel : 0670 298 533.\n"
                                      + "Service commercial: 0795 180 736.";
                       
                 if(sendEmail(mail, titre, text))
                  context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Information", "Suppression effectuer avec succés."));
                        
               }else{
                  context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de suppression de demande.", ""));
               }
               
    }
    
      public void accepter(String codeDem){
              FacesContext context = FacesContext.getCurrentInstance();
              if(bean.isDemandeAccepted(codeDem)){
                  context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Demande déja accepter.", ""));
              }else{
               
                if(bean.update(codeDem)){
                    
                     MhDemandeClt dm = bean.getDemandeByCode(codeDem);
                     String nomClt = dm.getNpid().getNomPrenom();
                     String mail = dm.getNpid().getMail();
                    
                     dt DTS = new dt();
                     
                     String titre = "Mon Hôtel Demande Réservation accepter";
                        String text  =  "Bonjour M. "+nomClt+",\n"+
                                        "L'équipe monhotel-dz.com vous annonce que votre réservation sur:\n"+
                                        dm.getCodeH().getRaisonSocial()+"\n"+
                                        "Du: "+DTS.form_Aff(dm.getDu())+" Au: "+DTS.form_Aff(dm.getAu())+" a été CONFIRMER.\n"+
                                        "\nCoordialement \nMon Hotel.\n\n"+
                                        "Tel : 0670 298 533.\n"
                                      + "Service commercial: 0795 180 736.";
                       
                        if(sendEmail(mail, titre, text))
                         context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Information", "Acceptation effectuer avec succès."));
                        
                 }else{
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de d'acceptation de demande.", ""));
                 } 
                
              }
    }
      
      public boolean isAccepted(String codeDem){
          return bean.isDemandeAccepted(codeDem);
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
      
      
}
