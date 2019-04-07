package controller;

import bean.MhChargeSuppBean;
import bean.MhCltSChBean;
import bean.MhEntrepriseBean;
import bean.MhHotelBean;
import bean.MhRsvVueGlobalBean;
import com.linkedList.linkedList;
import com.planertree.ArbrePlaner;
import fct.dt;
import fct.nbr;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ejb.EJB;
import javax.servlet.http.HttpSession;
import model.MhCltFct;
import model.MhHotel;
import model.MhRsvvueglobal;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;
import statistique.bean.beanEspeceFct;

@ManagedBean
@SessionScoped
public class statController {
    
    
//-------------------Chart----------------------------    

    private LineChartModel multiAxisModel;

    public LineChartModel getMultiAxisModel() {
        return multiAxisModel;
    }

    public void setMultiAxisModel(LineChartModel multiAxisModel) {
        this.multiAxisModel = multiAxisModel;
    }
    
    
    private LineChartModel multiAxisModel2;

    public LineChartModel getMultiAxisModel2() {
        return multiAxisModel2;
    }

    public void setMultiAxisModel2(LineChartModel multiAxisModel2) {
        this.multiAxisModel2 = multiAxisModel2;
    }
    
    
//-------------------------------------------------------    
    

    private Date du;
    private Date au;
    private boolean journ;
    
 

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

    public boolean isJourn() {
        return journ;
    }

    public void setJourn(boolean journ) {
        this.journ = journ;
    }
 
    
//-----------------------------------------    
    @EJB 
    MhRsvVueGlobalBean bean;
    
    @EJB
    MhCltSChBean beanCSCh;
   
    @EJB
    MhChargeSuppBean beanChargeSupp;
    
    @EJB
    MhEntrepriseBean beanEntreprise;
    
    @EJB
    MhHotelBean beanHotel;
  
    @PostConstruct
    public void init(){
        dt sdt = new dt();
        createMultiAxisModel(sdt.getFirstDayOfMonth(new Date()), sdt.getLastDayOfMonth(new Date()));
        createMultiAxisModel2(sdt.getFirstDayOfMonth(new Date()), sdt.getLastDayOfMonth(new Date()));
     }
     
    public ArbrePlaner arbre(Date dt1, Date dt2){
        
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
         ArbrePlaner pr = new ArbrePlaner();
         List<MhRsvvueglobal> lst = bean.SelectByDate(dt1, dt2);
         
         for(MhRsvvueglobal lst1:lst){
                List<MhCltFct> tabInfoClt = beanEntreprise.SingleSelectTab(lst1.getCodeC());
                boolean cltExonor = false;

                    for(MhCltFct tabInfoClt1:tabInfoClt){
                      cltExonor = tabInfoClt1.getExonore();
                      break;
                    }
                    if(!cltExonor){
                         List<MhHotel> tabInfo = beanHotel.SingleSelectTab(code_h);
                           for(MhHotel tabInfo1:tabInfo){
                             cltExonor = !tabInfo1.getTvaS();
                             break;
                          }
                    }
                    
                beanEspeceFct sts = null; 
                long id = 1L;
                int prcReduction = 0;
                if(lst1.getCodeC()!=null){
                      id = lst1.getCodeC().hashCode();
                      
                      sts = new beanEspeceFct(lst1.getNumFct(),lst1.getDates(),lst1.getCodeC(),lst1.getTypePaiement(),(float)beanChargeSupp.selectSomme(lst1.getCodeR()), lst1.getPrixU(), lst1.getNbNuitee(), beanCSCh.nbClientOnRsv(lst1.getCodeR()), lst1.getPensionC(), lst1.getPrcPension(), lst1.getPension(), cltExonor, lst1.getTaxeSj(), prcReduction);
                }else{
                      sts = new beanEspeceFct(lst1.getNumFct(),lst1.getDates(),"INC",lst1.getTypePaiement(),(float)beanChargeSupp.selectSomme(lst1.getCodeR()), lst1.getPrixU(), lst1.getNbNuitee(), beanCSCh.nbClientOnRsv(lst1.getCodeR()), lst1.getPensionC(), lst1.getPrcPension(), lst1.getPension(), cltExonor, lst1.getTaxeSj(), 0);
                }
               
               boolean ok = false;
                
               
                   linkedList Ll = pr.getListDataById(id);
                   if(Ll!=null){
                       String stsId =  sts.getNumFct();
                        for(int i=0; i<Ll.getSize(); i++){
                            beanEspeceFct LId = (beanEspeceFct) Ll.getElementAt(i);
                            if(LId.getNumFct().equals(stsId)){
                                ok = true;
                                break;
                            }
                        }
                   }
                   
               
               
                    if(!ok) pr.add(id, sts);
                

            }
        return pr;
    }
    
//*************Chart functions*********************
 @EJB
 MhHotelBean htlBean;
 
private void createMultiAxisModel(Date dt1, Date dt2) {
    nbr NBR = new nbr();
    ArbrePlaner pr = arbre(dt1, dt2);
    multiAxisModel = new LineChartModel();
 
     BarChartSeries total = new BarChartSeries();
     total.setLabel("ttc");
    
     LineChartSeries taxe = new LineChartSeries();
     taxe.setLabel("Taxe");
     taxe.setXaxis(AxisType.X2);
     taxe.setYaxis(AxisType.Y2);
    
     double max = 0;
     double taxeMax = 0;
     
     double Tttc = 0;
     double Ttaxe = 0;
     
        for(int i = 0; i < pr.getSize(); i++){
                            long id = (long) pr.getIdAt(i);
                            linkedList Ll = pr.getListDataById(id);
                      
                             String Iden = "";
                             String Entreprise = "";
                             double ttc = 0;
                             double  tx= 0;
                              for(int j = 0; j < Ll.getSize(); j++){
                                  beanEspeceFct st = (beanEspeceFct) Ll.getElementAt(j);
                                  
                                  ttc = ttc+st.getTotal(htlBean.tvaValue());
                                  tx = tx + st.getTaxe(htlBean.tvaValue());
                                  Iden = st.getCodeC();
                                  if(Iden!=null)
                                      Entreprise = (String)beanEntreprise.singleSelectEntreprise("raison_sociale", Iden);
                                  else
                                      Entreprise = "INC";
                              }
                              Tttc = Tttc + ttc;
                              Ttaxe = Ttaxe + tx;
                              if(ttc > max) max = ttc;
                              if(tx > taxeMax) taxeMax = tx;
                              total.set(Iden, ttc);
                              taxe.set(Entreprise, tx);
                        }
    

        multiAxisModel.addSeries(total);
        multiAxisModel.addSeries(taxe);
        
        multiAxisModel.setTitle("Présentation graphique Meilleur Client. TTC: "+NBR.dbToDf(Tttc)+" Da. Total des Taxes: "+NBR.dbToDf(Ttaxe)+" Da.");
        multiAxisModel.setMouseoverHighlight(false);
 
        multiAxisModel.getAxes().put(AxisType.X, new CategoryAxis("Identifiants Entreprises"));
        multiAxisModel.getAxes().put(AxisType.X2, new CategoryAxis("_______________________________"));
 
        Axis yAxis = multiAxisModel.getAxis(AxisType.Y);
        yAxis.setLabel("TTC en Da.");
        yAxis.setMin(0);
        yAxis.setMax(max*(1.1));
 
        Axis y2Axis = new LinearAxis("Taxe en Da.");
        y2Axis.setMin(0);
        y2Axis.setMax(taxeMax*(1.4));
 
        multiAxisModel.getAxes().put(AxisType.Y2, y2Axis);
    }


private void createMultiAxisModel2(Date dt1, Date dt2) {
    
    ArbrePlaner pr = arbre(dt1, dt2);
    multiAxisModel2 = new LineChartModel();
 
     BarChartSeries totalnt = new BarChartSeries();
     totalnt.setLabel("nbNuitee");
    
     LineChartSeries totalprs = new LineChartSeries();
     totalprs.setLabel("nbPersonne");
     totalprs.setXaxis(AxisType.X2);
     totalprs.setYaxis(AxisType.Y2);
    
     int max = 0;
     int taxeMax = 0;
     
     int TnbNuitee = 0;
     int TnbPersonne = 0;
     
        for(int i = 0; i < pr.getSize(); i++){
                            long id = (long) pr.getIdAt(i);
                            linkedList Ll = pr.getListDataById(id);
                      
                             String Iden = "";
                             String Entreprise = "";
                             int nbNuitee = 0;
                             int  nbPersonne= 0;
                              for(int j = 0; j < Ll.getSize(); j++){
                                  beanEspeceFct st = (beanEspeceFct) Ll.getElementAt(j);
                             
                                  nbNuitee = nbNuitee+st.getNbNuitee();
                                  nbPersonne = nbPersonne + st.getNbPersonne();
                                  Iden = st.getCodeC();
                                  if(Iden!=null)
                                      Entreprise = (String)beanEntreprise.singleSelectEntreprise("raison_sociale", Iden);
                                  else
                                      Entreprise = "INC";
                              }
                              TnbNuitee = TnbNuitee + nbNuitee;
                              TnbPersonne = TnbPersonne + nbPersonne;
                              if(nbNuitee > max) max = nbNuitee;
                              if(nbPersonne > taxeMax) taxeMax = nbPersonne;
                              totalnt.set(Iden, nbNuitee);
                              totalprs.set(Entreprise, nbPersonne);
                        }
    

        multiAxisModel2.addSeries(totalnt);
        multiAxisModel2.addSeries(totalprs);
 
        multiAxisModel2.setTitle("Présentation graphique des Clients par nombres nuitées et personnes.");
        multiAxisModel2.setMouseoverHighlight(false);
 
        multiAxisModel2.getAxes().put(AxisType.X, new CategoryAxis("Identifiants Entreprises"));
        multiAxisModel2.getAxes().put(AxisType.X2, new CategoryAxis("_______________________________"));
 
        Axis yAxis = multiAxisModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre de nuitée");
        yAxis.setMin(0);
        yAxis.setMax(max + 10);
 
        Axis y2Axis = new LinearAxis("Nombre de personne");
        y2Axis.setMin(0);
        y2Axis.setMax(taxeMax + 14);
 
        multiAxisModel2.getAxes().put(AxisType.Y2, y2Axis);
    }


    
    public void calcule(){
            createMultiAxisModel(du, au);
            createMultiAxisModel2(du, au);
    }
    
}
