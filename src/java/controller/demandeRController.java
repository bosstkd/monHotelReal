
package controller;

import bean.MhChambreBean;
import bean.MhConventionBean;
import bean.MhDemandeRBean;
import bean.MhHotelBean;
import bean.MhReservationBean;
import fct.codification;
import fct.dt;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.MhChambre;
import model.MhCltSCh;
import model.MhDemandeR;
import model.MhHotel;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class demandeRController {
    
    
    @EJB
    MhDemandeRBean bean;
    private List<MhDemandeR> listDemandeR;

    public List<MhDemandeR> getListDemandeR() {
        listDemandeR = bean.getListDemandeR();
        return listDemandeR;
    }

    public void setListDemandeR(List<MhDemandeR> listDemandeR) {
        this.listDemandeR = listDemandeR;
    }
    

    
    
    private List<String> NPID;

    public List<String> getNPID() {
        return NPID;
    }

    public void setNPID(List<String> NPID) {
        this.NPID = NPID;
    }
    
  
@PostConstruct
public void Init(){    
        NPID = new ArrayList<String>();
        du = new Date();
        au = new Date();   
}   

    

    
    private String textPID;

    public String getTextPID() {
        return textPID;
    }

    public void setTextPID(String textPID) {
        this.textPID = textPID;
    }
    
    public void OnRowSelect(SelectEvent event) {
        String n_p = ((MhCltSCh) event.getObject()).getNpid();
        textPID = n_p;
    }
//------------------------------------

//----------Demandes--------

MhDemandeR demandeR;

    public MhDemandeR getDemandeR() {
        return demandeR;
    }

    public void setDemandeR(MhDemandeR demandeR) {
        this.demandeR = demandeR;
    }


//-----partie chambre--------

private String num_ch;
private String typeP;
private Date du;
private Date au;

    public String getNum_ch() {
        return num_ch;
    }

    public void setNum_ch(String num_ch) {
        this.num_ch = num_ch;
    }

    public String getTypeP() {
        return typeP;
    }

    public void setTypeP(String typeP) {
        this.typeP = typeP;
    }

    

    public Date getDu() {
        return du;
    }

    public void setDu(Date du) {
        this.du = du;
    }

    public Date getAu() {
        return au;
    }

    public void setAu(Date au) {
        this.au = au;
    }

    
    private MhChambre chambre;

    public MhChambre getChambre() {
        return chambre;
    }

    public void setChambre(MhChambre chambre) {
        this.chambre = chambre;
    }

    @EJB
    MhChambreBean beanCh;
    
    @EJB
    MhHotelBean htlBean;
    
    @EJB
    MhReservationBean beanRsv;
    
     private List<MhChambre> listChambre;
    
     public List<MhChambre> getListChambre() {
        listChambre = beanCh.listChambreLibreEnt(du, au);
        return listChambre;
    }

    public void setListChambre(List<MhChambre> listCalc) {
        this.listChambre = listCalc;
    }
    
    @EJB
    MhConventionBean beanConv;
    
    public void onRowSelect0(SelectEvent event) {
        String str = ((MhChambre) event.getObject()).getNumCh();
        String code_h = beanConv.convention().getCodeH().getCodeH();
        int cd_hLng = code_h.length();
        chambre = beanCh.singleSelect(str);
        num_ch = str.substring(cd_hLng+1, str.length()); 
    }

public void ajtListPid(){
   FacesContext context = FacesContext.getCurrentInstance();
    if(textPID.isEmpty()){
         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez précisé le <<N°PID Client>> svp !!", ""));
    }else{
        boolean bl = true;
        for (String value : NPID)
    	 {
             if(value.equals(textPID)){
                          bl = false;
                          context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Client déja affecter a la liste !!", ""));
                          break;
             }
         }
        if(bl){
                NPID.add(textPID);
        }
    }
}    
    

public void suppListPid(){
   FacesContext context = FacesContext.getCurrentInstance();
 
    if(textPID.isEmpty()){
         context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez précisé le <<N°PID Client>> svp !!", ""));
    }else{
        NPID.remove(textPID);
       // prixTotal();
    }
} 

public void viderListPid(){
        FacesContext context = FacesContext.getCurrentInstance();
        NPID.removeAll(NPID);
       // prixTotal();
        context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Liste remis à zéro.", ""));
} 

public boolean isListNotValid(){
    if(NPID.isEmpty())return false;
    else{
        String code_h = beanConv.convention().getCodeH().getCodeH();
        int nbp =  beanCh.singleSelect(code_h+"_"+num_ch).getNbPlace();
        return nbp >= NPID.size();
    }
}

public boolean isListClientExist(){
        for (String NPID1 : NPID) {
            if(beanRsv.isClientInChEnt(NPID1, du)) return true;
        }
    return false;
}

    public void confirme(){
        FacesContext context = FacesContext.getCurrentInstance();
        dt sdt = new dt();
        codification COD = new codification();
        if(!sdt.isValidDateRes(du, au, new Date())){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates introduites svp !!", ""));
        }else if(!isListNotValid()){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"La liste des employées ne peut pas etre vide et ne doit pas dépassé le nombre de place de la chambre !!", ""));
        }else if(isListClientExist()){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Demande déja existante. Veuillez vérifier la liste des emplyées !!", ""));
        }else if(beanRsv.isChReservedEnt(num_ch, du, au)){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible : *la chambre "+num_ch+" est reserver !!", ""));
        }else{
            
            MhDemandeR dem = new MhDemandeR();
            dem.setDateDm(new Date());
            dem.setDu(du);
            dem.setAu(au);
            MhHotel htl = beanConv.convention().getCodeH();
            dem.setCodeH(htl);
            dem.setCodeC(beanConv.convention().getCodeC());
            dem.setEtatDm(false);
            dem.setNumch(chambre);
            dem.setTypePension(typeP);
            dem.setCodeDm(COD.cd_convention(new Date()+"", htl.getCodeH()+beanConv.convention().getCodeC().getCodeC()));
            if(bean.insert(dem, NPID)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Demande de réservation effectuée avec succées.", ""));
            }else{
               context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur de Demande de réservation!!", ""));
            }
        }
    }
    
    public void annuler(String codeDm){
      FacesContext context = FacesContext.getCurrentInstance();
      if(bean.getDemandeRByCodeDm(codeDm).getEtatDm()){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible demande confirmer !!", ""));
      }else
       if( !bean.delete(bean.getDemandeRByCodeDm(codeDm))){
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur de suppression de demande de réservation !!", ""));
       }
    }
    
}
