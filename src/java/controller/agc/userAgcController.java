package controller.agc;

import bean.agc.MhUsersABean;
import controller.Util;
import fct.codification;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.agc.MhCompteUserA;
import org.primefaces.event.SelectEvent;


@ManagedBean
@SessionScoped
public class userAgcController {
    
    
    private MhCompteUserA utlisateur;

    public MhCompteUserA getUtlisateur() {
        return utlisateur;
    }

    public void setUtlisateur(MhCompteUserA utlisateur) {
        this.utlisateur = utlisateur;
    }
    
 //-----------------Liste variable utilisateur----------------
    
    private String code_a;
    private String type_user;
    private String code_u;
    private String nom;
    private String email;
    private String psw;
   // private String admin;
    private String tel;

    public String getCode_u() {
        return code_u;
    }

    public void setCode_u(String code_u) {
        this.code_u = code_u;
    }

    
    
    
    public String getCode_a() {
        return code_a;
    }

    public void setCode_a(String code_a) {
        this.code_a = code_a;
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
     //-------------liste Users----------------//  
    @EJB
    MhUsersABean bean;
    
     private List<MhCompteUserA> listUsers;
    
     public List<MhCompteUserA> getListUsers() {
         
        this.listUsers = bean.listCompteUsersA();
        return listUsers;
    }

    public void setListUsers(List<MhCompteUserA> listUsers) {
        this.listUsers = listUsers;
    }
    
 //--------------------Opérations---------------------    
    public void confirme() {
            
        codification COD = new codification();
        //  dt sdt = new dt();
        //  nbr NBR = new nbr();
        
           if(nom.equals("")||email.equals("")){
                FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez insérer le Nom et l'émail svp !!.", ""));
           }else{
               
               String slt;
            try {
                code_u = COD.cd_prs(email);
                     slt = bean.singleSelectUser(COD.cd_prs(email),"code_u");
                } catch (Exception e) { 
                     slt = "";
                                       }
            
             if((COD.cd_prs(email)).equals(slt)){
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Utilisateur déja existante.", ""));
            }else{
                 code_u = COD.cd_prs(email);
                 MhCompteUserA user = new MhCompteUserA();
                 user.setTypeUser(type_user);
                 user.setCodeU(code_u);
                 user.setNom(nom);
                 user.setEmail(email);
                 user.setPsw(psw);
                 user.setTel(tel);
                 boolean ok = false;
                 if(type_user.equals("Administrateur"))ok = true;
                 user.setAdmin(ok);
                 bean.insert(user);
               
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Utilisateur ajouter avec succés.", ""));
         }
           }
    }
    
//---------------------------------------------------

public void modifier (){
            codification COD = new codification();

        FacesContext context = FacesContext.getCurrentInstance();
       if(nom.equals("")||email.equals("")){
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez insérer le Nom et l'émail svp !!.", ""));
           }else{
                String slt;
               HttpSession hs = Util.getSession();
            try {
                     slt = bean.singleSelectUser(COD.cd_prs(email),"code_u");
                } catch (Exception e) { 
                     slt = "";
                                       }
             if((COD.cd_prs(email)).equals(slt)){
                   code_u = COD.cd_prs(email);  
                 
                    MhCompteUserA usr = new MhCompteUserA();
                    usr.setCodeU(code_u);
                    usr.setEmail(email);
                    usr.setTypeUser(type_user);
                    usr.setTel(tel);
                    boolean ok = false;
                    if(type_user.equals("Administrateur"))ok=true;
                    usr.setAdmin(ok);
                    usr.setNom(nom);
                    usr.setPsw(psw);
                    
                    bean.update(usr);
                    
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Utilisateur modifier avec succés.", ""));

             }else if((hs.getAttribute("psw").toString()).equals(hs.getAttribute("code_a").toString())){
                
                 
                    code_u = COD.cd_prs(email);  
                 
                    MhCompteUserA usr = new MhCompteUserA();
                    usr.setCodeU(code_u);
                    usr.setEmail(email);
                    usr.setTypeUser(type_user);
                    usr.setTel(tel);
                    boolean ok = false;
                    if(type_user.equals("Administrateur"))ok=true;
                    usr.setAdmin(ok);
                    usr.setNom(nom);
                    usr.setPsw(psw);
                    
                    if(bean.updateFirstCompte(usr)){
                           context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Utilisateur modifier avec succés.", ""));
                    }else{
                           context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur sur la modification.", ""));
                    }

             }else{
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Utilisateur non existant !!", ""));
             }
             
           }
        
         
    }    
 
//-----------------------------------------

public void suppUser(){
      codification COD = new codification();
     FacesContext context = FacesContext.getCurrentInstance();
             if(nom.equals("")||email.equals("")){
               
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Veuillez insérer le Nom et l'émail svp !!.", ""));
           }else{
                   String slt;
                 int i = bean.selectCountJpql();
                 if(i<2 && type_user.equals("Administrateur")){
                      context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible il doit y'avoir au moins un compte Administrateur !!", ""));
                 }else{
                       
                         try {
                                  slt = bean.singleSelectUser(COD.cd_prs(email),"code_u");
                             } catch (Exception e) { 
                                  slt = "";
                                                    }

                          if((COD.cd_prs(email)).equals(slt)){
                             
                              code_u = COD.cd_prs(email);
                                Object[] user = new Object[6];
                                user[0] = type_user;
                                user[1] = nom;
                                user[2] = email;
                                user[3] = psw;
                                user[4] = type_user;
                                user[5] = tel;
                                  try {
                                   bean.remove(user);
                                      context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Utilisateur supprimer avec succés.", ""));
                                    } catch (Exception e) {
                                         context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Suppression impossible Utilsateur en action.", ""));
                                    }
                      
                          }else{
                                 context.addMessage("Error", new FacesMessage(FacesMessage.SEVERITY_WARN,"Utilisateur non existant !!", ""));
                          } 
                 }
                 
                
           }
    }
//------------------fin opérations-----------------------    
    

public void onRowSelect(SelectEvent event) {
        String str = ((MhCompteUserA) event.getObject()).getCodeU();
        FacesMessage msg = new FacesMessage("Code User", str);
       
          MhCompteUserA user = bean.singleSelect(str);
          nom = user.getNom();
          type_user = user.getTypeUser();
          email = user.getEmail();
          psw = user.getPsw();
          tel = user.getTel();
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 



 //----------------------------------------------------
    
}
