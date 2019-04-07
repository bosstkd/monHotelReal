package controller;

import bean.MhChargeSuppBean;
import bean.MhReservationBean;
import bean.MhTabRsvWOFctBean;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhChargeSupp;
import model.MhTabRsvWOFct;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class chargeSuppController {
    
    private String code_r;
    private String design;
    private float prixCh;

    public String getCode_r() {
        return code_r;
    }

    public void setCode_r(String code_r) {
        this.code_r = code_r;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public float getPrixCh() {
        return prixCh;
    }

    public void setPrixCh(float prixCh) {
        this.prixCh = prixCh;
    }
 //---------------------------------
    private MhChargeSupp charge;

    public MhChargeSupp getCharge() {
        return charge;
    }

    public void setCharge(MhChargeSupp charge) {
        this.charge = charge;
    }
    

 //---------------------------------   
    
    @EJB
    MhChargeSuppBean beanChSupp;
  
    
    @EJB
    MhReservationBean beanRsv;
    
    private List<MhChargeSupp> listChargeSupp;

    public List<MhChargeSupp> getListChargeSupp() {
        this.listChargeSupp = beanChSupp.listChargeSupp();
        return listChargeSupp;
    }

    public void setListChargeSupp(List<MhChargeSupp> listChargeSupp) {
        this.listChargeSupp = listChargeSupp;
    }

  
    
    public void ajoutCharge(){
        FacesContext context = FacesContext.getCurrentInstance();
      
        HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h"); 
         
        MhChargeSupp chrge = new MhChargeSupp();
        chrge.setCode_h(code_h);
        chrge.setCode_r(code_r);
        chrge.setDtChrg(new Date());
        chrge.setCharge(design);
        chrge.setPrix_ch(prixCh);
        if(beanChSupp.insert(chrge)){
             context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Charge supplémentaire effectuer avec succès.", ""));
        }else{
           context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération impossible erreur d'insertion.", ""));
         }
        
    }
    
    
    
    @EJB
    MhTabRsvWOFctBean beanTabRsv;
    
    public void annulerCharge(String codeR, String design){
        FacesContext context = FacesContext.getCurrentInstance();
        MhTabRsvWOFct woFct = beanTabRsv.selectByCodeR(codeR);
        Date dtA = woFct.getDtA();
        Date dtD = woFct.getDtD();
        dt sdt = new dt();
        if(sdt.isInPeriode(dtA, dtD, new Date())){
          
           if( beanChSupp.remove(codeR, design)){
                context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Annulation effectuer avec succès.", ""));
            }else{
                context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération impossible erreur de suppression. ", ""));
           }
            
        }else{
              context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Opération impossible réservation expiré.", ""));
        }
        
        
    }
    
    
    public void onRowSelect(SelectEvent event) {
        String cdr = ((MhTabRsvWOFct) event.getObject()).getCodeR();
        code_r = cdr;
    }
    
    
    
    
}
