package controller;

import bean.MhCltSChBean;
import bean.MhHotelBean;
import bean.MhTabRsvWOFctBean;
import fct.dt;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MhHotel;
import model.MhTabRsvWOFct;
import model.modelEtatPolice;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@ManagedBean
@SessionScoped
public class etatPoliceController {
    
   private List<modelEtatPolice> etPolice; 
    
    
   @EJB
   MhTabRsvWOFctBean bean;
    
   @EJB
   MhCltSChBean beanClt;
   
   private List<MhTabRsvWOFct> lstPolice;

    public List<MhTabRsvWOFct> getLstPolice() {
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        lstPolice = bean.listPolice();
        dt sdt = new dt();
        etPolice = new ArrayList<modelEtatPolice>();
        for(MhTabRsvWOFct lst:lstPolice){
            modelEtatPolice et = new modelEtatPolice();
            lst.setNumCh(lst.getNumCh().substring(code_h.length()+1, lst.getNumCh().length()));
            et.setNumCh(lst.getNumCh());
            et.setDtDu(sdt.form_Aff(lst.getDtA()));
            et.setDtAu(sdt.form_Aff(lst.getDtD()));
            et.setNomPrenom(lst.getNomPrenom());
            et.setDtN(sdt.form_Aff(DateN(lst.getNpid())));
            et.setPieceId(pieceId((lst.getNpid())));
            et.setNpid(lst.getNpid());
            etPolice.add(et);
        }
        return lstPolice;
    }

    public void setLstPolice(List<MhTabRsvWOFct> lstPolice) {
        this.lstPolice = lstPolice;
    }
    
    public Date DateN(String npid){
        return beanClt.getClientByNpid(npid).getDateN();
    }
    
    public String pieceId(String npid){
        return beanClt.getClientByNpid(npid).getPieceId();
    }
   
    
JasperPrint jasperprint;
@EJB
MhHotelBean beanHotel;
    
public void init02() throws JRException{
        
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        

        
        Map<String, Object> parameters = new HashMap<String, Object>(5);
        
        List<MhHotel> tabInfo = beanHotel.SingleSelectTab(code_h);
        for(MhHotel tabInfo1:tabInfo){
            parameters.put("nom_entreprise", tabInfo1.getRaisonSocial());
            parameters.put("adresse", tabInfo1.getAdresse());
            parameters.put("tel", tabInfo1.getTel()+"/"+tabInfo1.getFax());
            parameters.put("ai", tabInfo1.getNai());
            parameters.put("rc", tabInfo1.getNrc());
            parameters.put("mail", tabInfo1.getMail());
            parameters.put("nif", tabInfo1.getNif());
            parameters.put("compte", tabInfo1.getRib());
            parameters.put("etoile", "\\etoile\\"+ tabInfo1.getEtoile()+".jpg");
            break;
        }
        parameters.put("logoLink", "..\\"+code_h+"\\logo\\logo_RS.jpg");
        JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(etPolice);
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\facture\\etatPolice.jasper");
        File f = new File(relativeWebPath); 
        jasperprint = JasperFillManager.fillReport(f.getPath(), parameters, beanDataSource);  
    }    
    
  public void Chrg(ActionEvent event) throws JRException, IOException{
        init02();
        ServletOutputStream servletOutputStream = ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperprint, servletOutputStream);
              
                servletOutputStream.flush();
                servletOutputStream.close();
    }

    
    
}
