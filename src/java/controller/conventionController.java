package controller;

import bean.MhConventionBean;
import bean.MhEntrepriseBean;
import bean.MhHotelBean;
import fct.codification;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhCltFct;
import model.MhConvention;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class conventionController {
    
    
    
    @EJB
    MhEntrepriseBean beanEnt;
    
    @EJB
    MhHotelBean beanHtl;
    
    @EJB
    MhConventionBean bean;
    
    List<MhConvention> listEnt;
    
   

    public List<MhConvention> getListEnt() {
        listEnt = bean.conventionList();
        return listEnt;
    }

    public void setListEnt(List<MhConvention> listEnt) {
        this.listEnt = listEnt;
    }
    
    private String codeC;
    private Date dtDu;
    private Date dtAu;
    private int prcReduction;
    private MhConvention entreprise;
    
    public String getCodeC() {
        return codeC;
    }

    public void setCodeC(String codeC) {
        this.codeC = codeC;
    }

    public Date getDtDu() {
        return dtDu;
    }

    public void setDtDu(Date dtDu) {
        this.dtDu = dtDu;
    }

    public Date getDtAu() {
        return dtAu;
    }

    public void setDtAu(Date dtAu) {
        this.dtAu = dtAu;
    }

    public int getPrcReduction() {
        return prcReduction;
    }

    public void setPrcReduction(int prcReduction) {
        this.prcReduction = prcReduction;
    }
    
    public MhConvention getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(MhConvention entreprise) {
        this.entreprise = entreprise;
    }
//----------------------------------------------
    
    public String getRaison(MhCltFct clt){
        return clt.getRaisonSociale();
    }
    
    
    public void selectedConvention(SelectEvent event) {
        String CC = ((MhCltFct) event.getObject()).getCodeC();
        codeC = CC;
    }
    
    public void confirme(){
                    FacesContext context = FacesContext.getCurrentInstance();
                    dt sdt = new dt();
                    codification COD = new codification();
                    if(!sdt.superieur(dtDu, dtAu)){
                         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates svp !!", ""));
                    }else{
                         if(codeC == null) codeC = "";
                         
                        if(codeC.equals("")){
                             context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Le code client ne peut etre vide.", ""));
                        } else{
                             HttpSession hs = Util.getSession();
                            String code_h = (String) hs.getAttribute("code_h");
                            MhConvention entC = new MhConvention();
                                entC.setCodeConvention(COD.cd_convention(code_h, codeC));
                                entC.setCle((""+COD.cd_convention(code_h, codeC).hashCode()).replaceAll("-", "A"));
                                entC.setCodeC(beanEnt.singleSelectEntJPQL(codeC));
                                entC.setCodeH(beanHtl.findByCodeH(code_h));
                                entC.setDu(dtDu);
                                entC.setAu(dtAu);
                                entC.setPrcReduction(prcReduction);
                           try {
                                if(bean.insert(entC)){
                                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Opération effectuer", ""));
                                 }else{
                                     context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'insertion", ""));
                                 }
                            } catch (Exception e) {
                                     context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Convention déja existante. Pour modifier les termes contractuels cliqué sur Modifier.", ""));
                            }
                           
                        }
                       
                    }
    }
    
    public void modifier(){
        FacesContext context = FacesContext.getCurrentInstance();
        dt sdt = new dt();
        codification COD = new codification();
                    if(!sdt.superieur(dtDu, dtAu)){
                         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veillez vérifier les dates svp !!", ""));
                    }else{
                         if(codeC == null) codeC = "";
                         
                        if(codeC.equals("")){
                             context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Le code client ne peut etre vide.", ""));
                        } else{
                             HttpSession hs = Util.getSession();
                            String code_h = (String) hs.getAttribute("code_h");
                            MhConvention entC = new MhConvention();
                                entC.setCodeConvention(COD.cd_convention(code_h, codeC));
                                entC.setCle((""+COD.cd_convention(code_h, codeC).hashCode()).replaceAll("-", "A"));
                                entC.setCodeC(beanEnt.singleSelectEntJPQL(codeC));
                                entC.setCodeH(beanHtl.findByCodeH(code_h));
                                entC.setDu(dtDu);
                                entC.setAu(dtAu);
                                entC.setPrcReduction(prcReduction);

                                if(bean.update(entC)){
                                     context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Mise a jour effectuer", ""));
                                 }else{
                                     context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de modification", ""));
                                 }
                         
                        }
                       
                    }
    }
    
    public void supprimer(String codeConvention){
         FacesContext context = FacesContext.getCurrentInstance();
                if(bean.remove(codeConvention)){
                      context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Convention annuler.", ""));
                }else{
                      context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'annulation.", ""));
                }
    }
    
}
