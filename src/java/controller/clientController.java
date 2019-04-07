
package controller;

import bean.MhCltSChBean;
import fct.codification;
import fct.str;
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
import model.MhCltSCh;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class clientController {
    
     private MhCltSCh client;

    public MhCltSCh getClient() {
        return client;
    }

    public void setClient(MhCltSCh client) {
        this.client = client;
    }
    
  private String pieceId;
  private String npid;
  private Date dateP;
  private String LienP;
  private String nomPrenom;   
  private String LieuN;
  private Date dateN;
  private String nationalite; 
  private String adresse;
  private String numTel;
  private boolean listeNoir;
  private String raisonLn;   
  private String mail;
  

    public String getPieceId() {
        return pieceId;
    }

    public void setPieceId(String pId) {
        this.pieceId = pId;
    }

    public String getNpid() {
        return npid;
    }

    public void setNpid(String npid) {
        this.npid = npid;
    }

   
    public Date getDateP() {
        return dateP;
    }

    public void setDateP(Date dateP) {
        this.dateP = dateP;
    }

    public String getLienP() {
        return LienP;
    }

    public void setLienP(String LienP) {
        this.LienP = LienP;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    public String getLieuN() {
        return LieuN;
    }

    public void setLieuN(String LieuN) {
        this.LieuN = LieuN;
    }

    public Date getDateN() {
        return dateN;
    }

    public void setDateN(Date dateN) {
        this.dateN = dateN;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public boolean isListeNoir() {
        return listeNoir;
    }

    public void setListeNoir(boolean listeNoir) {
        this.listeNoir = listeNoir;
    }

    public String getRaisonLn() {
        return raisonLn;
    }

    public void setRaisonLn(String raisonLn) {
        this.raisonLn = raisonLn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    
    
    
 
  //-----------------EJB----------------------
    @EJB
    MhCltSChBean bean;
    
    
   private List<MhCltSCh>  listClientEnt;

    public List<MhCltSCh> getListClientEnt() {
        listClientEnt = bean.CltSChlistEnt();
        return listClientEnt;
    }

    public void setListClientEnt(List<MhCltSCh> listClientEnt) {
        this.listClientEnt = listClientEnt;
    }
   
   
    
    
    
     private List<MhCltSCh> listClient;
    
     public List<MhCltSCh> getListClient() {
         
        this.listClient = bean.CltSChlist();
        return listClient;
    }

    public void setListClient(List<MhCltSCh> listClient) {
        this.listClient = listClient;
    }
 //----------------------------Opérations-----
    
     public void confirme() {
          FacesContext context = FacesContext.getCurrentInstance();
          str STR = new str();
          codification COD = new codification();
         if(nomPrenom.isEmpty() || npid.isEmpty()|| LieuN.isEmpty()|| nationalite.isEmpty()|| LienP.isEmpty()){
              context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez remplir tout les champs svp. !!", ""));
         }else{
             String piece = pieceId;
             String numPiece = npid;
             
             
             if(bean.singleSelect(piece, numPiece)!=null){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client déja existant !!", ""));
             }else{
                 
                 if(!STR.isName(nomPrenom)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez entrer un nom valide svp.", ""));
                 }else{
                     
                     if(listeNoir && raisonLn.length()<10){
                        context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez présiser svp les raisons de mettre ce client en liste noir.", ""));
                     }else{
   
                            MhCltSCh clts = new MhCltSCh();
                            clts.setNomPrenom(STR.nameForm(nomPrenom));
                            clts.setDateN(dateN);
                            clts.setLieuN(LieuN);
                            clts.setNationalite(nationalite);
                            clts.setPieceId(pieceId);
                            clts.setNpid(npid);
                            clts.setDateP(dateP);
                            clts.setLienP(LienP);
                            clts.setAdresse(adresse);
                            clts.setNum_tel(numTel);
                            clts.setPsw((COD.cd_morale(npid).hashCode()+"").replaceAll("-", "F"));
                            clts.setListeNoir(listeNoir);
                            clts.setRaisonLn(raisonLn);
                            clts.setCodeC(null);
                    if(bean.insert(clts)){
                        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Client ajouter avec succés.", ""));
                    }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors d'insertion du clients", ""));
                    }
                    
                 
                     }
                     
                      }
             } 
         }      
    }
     
     
     public void confirmeEnt() {
          FacesContext context = FacesContext.getCurrentInstance();
          str STR = new str();
          codification COD = new codification();
         if(nomPrenom.isEmpty() || npid.isEmpty()|| LieuN.isEmpty()|| nationalite.isEmpty()|| LienP.isEmpty()){
              context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez remplir tout les champs svp. !!", ""));
         }else{
             String piece = pieceId;
             String numPiece = npid;
             
             
             if(bean.singleSelect(piece, numPiece)!=null){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client déja existant!!", ""));
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Veuillez cliqué sur modifier svp.", ""));

             }else{
                 
                 if(!STR.isName(nomPrenom)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez entrer un nom valide svp.", ""));
                 }else{
                     
                     if(listeNoir && raisonLn.length()<10){
                        context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez présiser svp les raisons de mettre ce client en liste noir.", ""));
                     }else{
                            MhCltSCh clts = new MhCltSCh();
                            clts.setNomPrenom(STR.nameForm(nomPrenom));
                            clts.setDateN(dateN);
                            clts.setLieuN(LieuN);
                            clts.setNationalite(nationalite);
                            clts.setPieceId(pieceId);
                            clts.setNpid(npid);
                            clts.setDateP(dateP);
                            clts.setLienP(LienP);
                            clts.setAdresse(adresse);
                            clts.setNum_tel(numTel);
                            clts.setListeNoir(listeNoir);
                            clts.setRaisonLn(raisonLn);
                            clts.setPsw((COD.cd_morale(npid).hashCode()+"").replaceAll("-", "F"));
                            clts.setCodeC(bean.convention().getCodeC());
                    if(bean.insert(clts)){
                        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Client ajouter avec succés.", ""));
                    }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors d'insertion du clients", ""));
                    }
                    
                     }
                     
                      }
             } 
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
     
     
    public void confirmeClt() {
          FacesContext context = FacesContext.getCurrentInstance();
          str STR = new str();
          codification COD = new codification();
         if(nomPrenom.isEmpty() || npid.isEmpty()|| LieuN.isEmpty()|| nationalite.isEmpty()|| LienP.isEmpty()){
              context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez remplir tout les champs svp. !!", ""));
         }else{
             String piece = pieceId;
             String numPiece = npid;
             if(bean.singleSelect(piece, numPiece)!=null){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Inscription déja effectuer !!", "Si c'est votre premiere inscritpion veuillez consulter votre boite mail."));
             }else{
                 if(!STR.isName(nomPrenom)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez entrer un nom valide svp.", ""));
                 }else{
                            MhCltSCh clts = new MhCltSCh();
                            clts.setNomPrenom(STR.nameForm(nomPrenom));
                            clts.setDateN(dateN);
                            clts.setLieuN(LieuN);
                            clts.setNationalite(nationalite);
                            clts.setPieceId(pieceId);
                            clts.setNpid(npid);
                            clts.setDateP(dateP);
                            clts.setLienP(LienP);
                            clts.setAdresse(adresse);
                            clts.setNum_tel(numTel);
                            clts.setPsw((COD.cd_morale(npid).hashCode()+"").replaceAll("-", "F"));
                            clts.setMail(mail);
                            clts.setListeNoir(false);
                            clts.setRaisonLn(null);
                            clts.setCodeC(null);
                    if(bean.insert(clts)){
                        
                        String titre = "Coordonnées Mon Hôtel No-Replay";
                        String text  = "Bonjour M. "+STR.nameForm(nomPrenom)+",\n"+
                                "l'équipe Mon Hôtel vous souhaite la bienvenue, vous coordonnées d'accès :\n\n"+
                                "Identifiant : "+npid+"\n"+
                                "Mot de passe : "+COD.cd_morale(npid).hashCode()+"\n"+
                                "\nCoordialement \nMon Hotel.\n\n"+
                                "Tel : 0670 298 533.";
                        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Insertion effectuer avec succés.", ""));
                       
                        if(sendEmail(mail, titre, text))
                        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Un mail contenant vos coordonées vous est envoyée", ""));
                   
                    }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors d'insertion du clients", ""));
                    }
                     
                      }
             } 
         }      
    } 
     
     
//---------------------Modifier----------------------------  
     public void modifier() {
         str STR = new str();
          FacesContext context = FacesContext.getCurrentInstance();
         if(nomPrenom.isEmpty() || npid.isEmpty()|| LieuN.isEmpty()|| nationalite.isEmpty()|| LienP.isEmpty()){
              context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez remplir tout les champs svp. !!", ""));
         }else{
             String piece = pieceId;
             String numPiece = npid;
             String slt = bean.singleSelect(piece, numPiece).getNpid();
             
             if(!numPiece.equals(slt)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client non existant !!", ""));
             }else{
                 
                 if(!STR.isName(nomPrenom)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez entrer un nom valide svp.", ""));
                 }else{
                     if(listeNoir && raisonLn.length()<10){
                        context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez présiser svp les raisons de mettre ce client en liste noir.", ""));
                     }else{
                
                    MhCltSCh cltsU = new MhCltSCh();
                    cltsU.setNomPrenom(STR.nameForm(nomPrenom));
                    cltsU.setDateN(dateN);
                    cltsU.setLieuN(LieuN);
                    cltsU.setNationalite(nationalite);
                    cltsU.setPieceId(pieceId);
                    cltsU.setNpid(npid);
                    cltsU.setDateP(dateP);
                    cltsU.setLienP(LienP);
                    cltsU.setAdresse(adresse);
                    cltsU.setNum_tel(numTel);
                    cltsU.setListeNoir(listeNoir);
                    cltsU.setRaisonLn(raisonLn);
                    if(bean.UPDATE(cltsU)){
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modification effectuer avec succés sur le client: "+nomPrenom, ""));
                    }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors de mise a jour du clients", ""));
                    }
                    
                     }
                  }
             } 
         }      
    }
     
     
        public void modifierEnt() {
          FacesContext context = FacesContext.getCurrentInstance();
          str STR = new str();
         if(nomPrenom.isEmpty() || npid.isEmpty()|| LieuN.isEmpty()|| nationalite.isEmpty()|| LienP.isEmpty()){
              context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez remplir tout les champs svp. !!", ""));
         }else{
             String piece = pieceId;
             String numPiece = npid;
             String slt = bean.singleSelect(piece, numPiece).getNpid();
             
             if(!numPiece.equals(slt)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client non existant !!", ""));
             }else{
                 
                 if(!STR.isName(nomPrenom)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez entrer un nom valide svp.", ""));
                 }else{
                     if(listeNoir && raisonLn.length()<10){
                        context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez présiser svp les raisons de mettre ce client en liste noir.", ""));
                     }else{
                
                    MhCltSCh cltsU = new MhCltSCh();
                    cltsU.setNomPrenom(STR.nameForm(nomPrenom));
                    cltsU.setDateN(dateN);
                    cltsU.setLieuN(LieuN);
                    cltsU.setNationalite(nationalite);
                    cltsU.setPieceId(pieceId);
                    cltsU.setNpid(npid);
                    cltsU.setDateP(dateP);
                    cltsU.setLienP(LienP);
                    cltsU.setAdresse(adresse);
                    cltsU.setNum_tel(numTel);
                    cltsU.setListeNoir(listeNoir);
                    cltsU.setRaisonLn(raisonLn);
                    if(bean.UPDATE_ENT(bean.convention().getCodeC().getCodeC(), cltsU)){
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modification effectuer avec succés sur le client: "+nomPrenom, ""));
                    }else{
                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur lors de mise a jour du clients", ""));
                    }
                    
                     }
                  }
             } 
         }      
    }
          
          public void annuler(String npidCsCh) {
                   bean.elimineDeEnt(npidCsCh);
            }
     
     
//---------------------------------------------------------     
public void onRowSelect(SelectEvent event) {
        String n_p = ((MhCltSCh) event.getObject()).getNpid();
        String p = ((MhCltSCh) event.getObject()).getPieceId();
        FacesMessage msg = new FacesMessage("Code Client", n_p);
       
          
          npid =  n_p;
          pieceId =  p;
          MhCltSCh clt = bean.singleSelect(p, n_p);
          LienP = clt.getLienP();
          dateP = clt.getDateP();
          
          nomPrenom = clt.getNomPrenom();
          LieuN = clt.getLieuN();
          dateN = clt.getDateN();
          nationalite = clt.getNationalite();
          adresse = clt.getAdresse();
          numTel = clt.getNum_tel();
          listeNoir = clt.getListeNoir();
          raisonLn = clt.getRaisonLn();
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 

 

  
  
}
