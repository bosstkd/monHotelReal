
package controller;

import bean.MhEntrepriseBean;
import fct.codification;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.MhCltFct;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class entrepriseController {
     private MhCltFct entreprise;

    public MhCltFct getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(MhCltFct entreprise) {
        this.entreprise = entreprise;
    }
     
     private String raisonSocial;
     private String nrc;
     private String nif;
     private String nai;
     private String adresse;
     private String tel;
     private boolean exonore;
     private String mail;
     private String code_c;

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isExonore() {
        return exonore;
    }

    public void setExonore(boolean exonore) {
        this.exonore = exonore;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode_c() {
        return code_c;
    }

    public void setCode_c(String code_c) {
        this.code_c = code_c;
    }
     
     //-----------------EJB----------------------
    @EJB
    MhEntrepriseBean bean;
    
     private List<MhCltFct> listEtp;
    
     public List<MhCltFct> getListEtp() {
        this.listEtp = bean.listCltFct();
        return listEtp;
    }

    public void setListEtp(List<MhCltFct> listEtp) {
        this.listEtp = listEtp;
    }
    
  //----------------------------Opérations-----
    
     public void confirme() {
          FacesContext context = FacesContext.getCurrentInstance();
          codification COD = new codification();
             String slt = bean.singleSelectEntreprise("code_c", COD.cd_prs(nrc)).toString();
             if(COD.cd_prs(nrc).equals(slt)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Entreprise déja existante !!", ""));
             }else{
                 
                 MhCltFct entp = new MhCltFct();
                     entp.setRaisonSociale(raisonSocial);
                     entp.setNrc(nrc);
                     entp.setNif(nif);
                     entp.setNai(nai);
                     entp.setAdresse(adresse);
                     entp.setTel(tel);
                     entp.setExonore(exonore);
                     entp.setMail(mail);
                     entp.setCodeC(COD.cd_prs(nrc));
                     
                     
                     if(bean.insert(entp)){
                              context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Entreprise ajouter avec succés.", ""));
                     }else{
                              context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération non effectuer. Erreur de communication", ""));
                     }
                     
                } 
    }   
     
     
     public void modifier() {
          FacesContext context = FacesContext.getCurrentInstance();
           codification COD = new codification();
          String slt = bean.singleSelectEntreprise("code_c", COD.cd_prs(nrc)).toString();

             
             if(!(COD.cd_prs(nrc)).equals(slt)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client non existant !!", ""));
             }else{
                 
              MhCltFct entp = new MhCltFct();
                     entp.setRaisonSociale(raisonSocial);
                     entp.setNrc(nrc);
                     entp.setNif(nif);
                     entp.setNai(nai);
                     entp.setAdresse(adresse);
                     entp.setTel(tel);
                     entp.setExonore(exonore);
                     entp.setMail(mail);
                     entp.setCodeC(COD.cd_prs(nrc));
             
                     if(bean.update(entp)){
                                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modification effectuer avec succés sur l'entreprise: "+raisonSocial, ""));
                               }else{
                                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération non effectuer. Erreur de communication", ""));
                     }
                     
             } 
    }
   
   public void supp(){
        codification COD = new codification();
        FacesContext context = FacesContext.getCurrentInstance();
          String slt = bean.singleSelectEntreprise("code_c", COD.cd_prs(nrc)).toString();

       if(!(COD.cd_prs(nrc)).equals(slt)){
                    context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client non existant !!", ""));
             }else{
                          try {
                                        bean.remove(slt);
                                        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Suppression effectuer avec succés sur l'entreprise: "+raisonSocial, ""));
                               } catch (Exception e) {
                                        context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Suppression impossible (Informations entreprise utiliser dans des opérations.)", ""));
                            }
                    } 
   }  
     
//---------------------------------------------------------     
public void onRowSelect(SelectEvent event) {
        String codeC = ((MhCltFct) event.getObject()).getCodeC();
        FacesMessage msg = new FacesMessage("Code Client", codeC);
     
       
       MhCltFct etp = bean.singleSelectEntJPQL(codeC);
       
       raisonSocial = etp.getRaisonSociale();
         nrc = etp.getNrc();
         nif = etp.getNif();
         nai = etp.getNai();
         adresse = etp.getAdresse();
         tel = etp.getTel();
         exonore = etp.getExonore();
         mail = etp.getMail();
       
       
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
