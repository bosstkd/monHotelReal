package org.primefaces.showcase.view.data;
 
import bean.MhTabReservationBean;
import bean.MhTabRsvWOFctBean;
import fct.dt;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.MhRsvvueglobal;
import model.MhTabRsvWOFct;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {
 
     
    private ScheduleModel lazyEventModel;
 
    @EJB
    MhTabRsvWOFctBean bean;
 
private String client;
private int nuitee;
private String designation;
private String numfct;
private String typePaiement;
private String pension;
private float versement;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getNuitee() {
        return nuitee;
    }

    public void setNuitee(int nuitee) {
        this.nuitee = nuitee;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNumfct() {
        return numfct;
    }

    public void setNumfct(String numfct) {
        this.numfct = numfct;
    }

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public float getVersement() {
        return versement;
    }

    public void setVersement(float versement) {
        this.versement = versement;
    }



    
    @PostConstruct
    public void init() {
       
        lazyEventModel = new DefaultScheduleModel();
        
        List<MhTabRsvWOFct> rsvInfo = bean.listRsvYear();
        dt sdt = new dt();
        for(MhTabRsvWOFct rsvInfo1 : rsvInfo){
            rsvInfo1.setNumCh(rsvInfo1.getNumCh().substring(rsvInfo1.getCodeH().length()+1, rsvInfo1.getNumCh().length()));
            DefaultScheduleEvent evento = new DefaultScheduleEvent(rsvInfo1.getNumCh()+" "+rsvInfo1.getNomPrenom()+". code:"+rsvInfo1.getCodeR(), sdt.longToDate(sdt.addDay(rsvInfo1.getDtA(), 1)), sdt.longToDate(sdt.addDay(rsvInfo1.getDtD(), 1)));
            lazyEventModel.addEvent(evento);
        }
        
        
    }
     
     
    
    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }
    
    
    //------------------le model----------------    
     private List<MhTabRsvWOFct> mdlRsv;

    public List<MhTabRsvWOFct> getMdlRsv() {
        return mdlRsv;
    }

    public void setMdlRsv(List<MhTabRsvWOFct> mdlRsv) {
        this.mdlRsv = mdlRsv;
    }
     
    
    public MhRsvvueglobal getInfo(String codeR){
        try {
            return beanModel.selectionA(codeR);
        } catch (Exception e) {
            return null;
        }
    }

    
    @EJB
    MhTabRsvWOFctBean beanWoFct;
    
    @EJB
    MhTabReservationBean beanModel;
    
    private ScheduleEvent event = new DefaultScheduleEvent();
    
     public ScheduleEvent getEvent() {
        return event;
    }
 
    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }
    
    
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        String str = event.getTitle();
        String code_R = str.substring(str.indexOf(":")+1, str.length());
        mdlRsv = beanWoFct.modelRsvWOFct(code_R);
        for(MhTabRsvWOFct mdlRsv1:mdlRsv){
          client = getInfo(mdlRsv1.getCodeR()).getCodeC();
          
          nuitee = mdlRsv1.getNbNuitee();
          pension = mdlRsv1.getPension();
          versement = mdlRsv1.getVersement();
          
          designation = getInfo(mdlRsv1.getCodeR()).getDesignation();
          numfct = getInfo(mdlRsv1.getCodeR()).getNumFct();
          numfct = numfct.substring(numfct.length() - 9, numfct.length());
          typePaiement = getInfo(mdlRsv1.getCodeR()).getTypePaiement();
        }
       

    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
     
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
         
        addMessage(message);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        addMessage(message);
    }
 
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}