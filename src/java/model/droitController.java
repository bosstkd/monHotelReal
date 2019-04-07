package controller;

import bean.MhDroitBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.MhUsersDroits;

@ManagedBean
@SessionScoped
public class droitController {
    
    @EJB
    MhDroitBean bean;
    List<MhUsersDroits> lstDroit;

    public List<MhUsersDroits> getLstDroit() {
        lstDroit = bean.selectDroit();
        return lstDroit;
    }

    public void setLstDroit(List<MhUsersDroits> lstDroit) {
        this.lstDroit = lstDroit;
    }
    
    
    public void modif(String codeU, int x, boolean value){
        FacesContext context = FacesContext.getCurrentInstance();
        if(bean.update(codeU, x, value)){
             context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Mise à jour effectuer.", ""));
        }else{
             context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication lors de mise a jour.", ""));
        }
    }
    
}
