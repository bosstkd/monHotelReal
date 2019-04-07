
package controller.agc;

import bean.MhChambreBean;
import bean.MhHotelBean;
import bean.agc.MhConventionAgcBean;
import fct.codification;
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
import model.agc.MhConventionAgc;
import model.agc.MhConventionAgcChambre;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@ManagedBean
@SessionScoped
public class PickListView {

     
    private DualListModel<String> numChambre;
    
    @EJB
    MhChambreBean beanCh;
    
     private String codeConvPut;
      private MhConventionAgc conv;
      
      public MhConventionAgc getConv() {
        return conv;
    }

    public void setConv(MhConventionAgc conv) {
        this.conv = conv;
    }

    public String getCodeConvPut() {
        return codeConvPut;
    }

    public void setCodeConvPut(String codeConvPut) {
        this.codeConvPut = codeConvPut;
    }
     
     
     
    @PostConstruct
    public void init() {
        listChambreListed();
    }
 
    public DualListModel<String> getNumChambre() {
        return numChambre;
    }
 
    public void setNumChambre(DualListModel<String> numChambre) {
        this.numChambre = numChambre;
    }
 
  @EJB
  MhHotelBean beanHtl;
  
    public void onTransfer(TransferEvent event) {
      //  StringBuilder builder = new StringBuilder();
    
        String code_h = beanHtl.htl().getCodeH();
         List<String> lst = (List<String>) event.getItems();
         
         codification COD = new codification();
        String chambreMouvment = "";
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
         if(event.isAdd()){
             MhConventionAgcChambre convAgcCh = new MhConventionAgcChambre();
               for(String ls:lst){
                   convAgcCh.setNumCh(beanCh.singleSelect(code_h+"_"+ls));
                   convAgcCh.setCodeConventionAgc(beanConv.getByCodeConvAgc(codeConvPut));
                   convAgcCh.setCodeAc(COD.cd_structure(code_h+"_"+ls+codeConvPut));
                    if(beanConv.insertChConv(convAgcCh)){
                        chambreMouvment = chambreMouvment+", "+ls;
                    }
                }
               msg.setSummary("Ajout: "+chambreMouvment);
         }else{
             for(String ls:lst){
                    String str = COD.cd_structure(code_h+"_"+ls+codeConvPut);
                    if(beanConv.deleteChConv(str)){
                        chambreMouvment = chambreMouvment+", "+ls;
                    }
                }
             msg.setSummary("Suppression: "+chambreMouvment);
         }
      //  msg.setDetail(builder.toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  

    @EJB
    MhConventionAgcBean beanConv;
    public void selectedConvention1(SelectEvent event){
        String CC = ((MhConventionAgc) event.getObject()).getCodeConvAgc();
        codeConvPut = CC;
        listChambreListed();
    }
    
    
    public void listChambreListed(){
        
         List<String> citiesSource = new ArrayList<String>();
         List<String> citiesTarget = new ArrayList<String>();
        if(codeConvPut == null)codeConvPut="";
        
        if(!codeConvPut.equals("")){
           List<MhChambre> lstCh = beanCh.chambreDejaConv(beanConv.getByCodeConvAgc(codeConvPut));
            Date dtD =  beanConv.getByCodeConvAgc(codeConvPut).getDateD();
            Date dtF =  beanConv.getByCodeConvAgc(codeConvPut).getDateF();
            List<MhChambre> lstChHtl = beanCh.listChToUseConvList(dtD, dtF, beanConv.getByCodeConvAgc(codeConvPut));

          

           for(MhChambre ch:lstChHtl){
               citiesSource.add(ch.getNumchApp());
           }

           for(MhChambre ch:lstCh){
               citiesTarget.add(ch.getNumchApp());
           }

        }
        
         numChambre = new DualListModel<String>(citiesSource, citiesTarget); 
        
    }
    
    
}