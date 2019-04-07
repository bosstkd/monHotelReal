package controller;

import bean.MhCaisseBean;
import bean.MhUsersHBean;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.MhCaisse;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@SessionScoped
public class caisseController {
    
    private String motif;
    private double somme;

    private BarChartModel animatedModel2;
    
    private Date du;
    private Date au;
    
    private boolean journ;
    
    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }

    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }

    public void setAnimatedModel2(BarChartModel animatedModel2) {
        this.animatedModel2 = animatedModel2;
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

    public boolean isJourn() {
        return journ;
    }

    public void setJourn(boolean journ) {
        this.journ = journ;
    }
    
    
    
    @PostConstruct
    public void init() {
        createAnimatedModels(new Date(), new Date());
    }
    
    
//--------------------------------------  
    
    @EJB
    MhCaisseBean bean;
    
    @EJB
    MhUsersHBean beanUser;
//--------------------------------------
    private List<MhCaisse> listCaisse;

    public List<MhCaisse> getListCaisse() {
        this.listCaisse = bean.listCaisse();
        return listCaisse;
    }

    public void setListCaisse(List<MhCaisse> listCaisse) {
        this.listCaisse = listCaisse;
    }
    
    
//--------------------------------------    


    
    public void Ajouter(){
        FacesContext context = FacesContext.getCurrentInstance();
        String code_u = (String) beanUser.singleSelectUserByMail((String) Util.getSession().getAttribute("email"), "code_u");
        Object[] caisse = new Object[3];
        
        caisse[0] = code_u;
        caisse[1] = motif;
        caisse[2] = somme;
        try {
            bean.insert(caisse);
            context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Ajout effectuer avec succés.", ""));
        } catch (Exception e) {
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur : "+e, ""));
        }
        
    }
    
    
    public void Retirer(){
        FacesContext context = FacesContext.getCurrentInstance();

        String code_u = (String) beanUser.singleSelectUserByMail((String) Util.getSession().getAttribute("email"), "code_u");
        Object[] caisse = new Object[3];
        
        caisse[0] = code_u;
        caisse[1] = motif;
        caisse[2] =(-1)*somme;
        
          try {
            bean.insert(caisse);
            context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Opération effectuer avec succés.", ""));
        } catch (Exception e) {
            context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur : "+e, ""));
        }        
    }
    
    
    //----------------------------------------------
    
    
    private void createAnimatedModels(Date dt1, Date dt2) {
   
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Mouvement de caisse");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        dt sdt = new dt();
        Date dtInf = sdt.getFirstDayOfMonth(dt1);
        Date dtSup = sdt.getLastDayOfMonth(dt2);
        double db = bean.selectSommePNG(dtInf, dtSup, "P");
        
        yAxis.setMin(- db-db/4);
        yAxis.setMax(db+db/4);
        
    }

     private void createAnimatedModels0(Date dt1, Date dt2) {
        animatedModel2.setTitle("Mouvement de caisse");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        Axis yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setTickAngle(-50);
          dt sdt = new dt();
   if(!journ){
      
       animatedModel2 = MonthsBarModel(dt1,dt2);
       
        Date dtInf = sdt.getFirstDayOfMonth(dt1);
        Date dtSup = sdt.getLastDayOfMonth(dt2);
        double db = bean.selectMaxMin(dtInf, dtSup, "MAX");
        
        yAxis.setMin(- db-db/4);
        yAxis.setMax(db+db/4);
       
   }else{
       
       animatedModel2 = daysBarModel(dt1,dt2);
        Date dtInf = sdt.getFirstDayOfMonth(dt1);
        Date dtSup = sdt.getLastDayOfMonth(dt2);
        double db = bean.selectMaxMin(dtInf, dtSup, "MAX");
        
        yAxis.setMin(- db-db/4);
        yAxis.setMax(db+db/4);
       
   }
        
        
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        dt sdt = new dt();
        ChartSeries revenu = new ChartSeries();
        revenu.setLabel("Revenu");
        
        Date dtInf = sdt.getFirstDayOfMonth(new Date());
        Date dtSup = sdt.getLastDayOfMonth(new Date());
        
        double rv = bean.selectSommePNG(dtInf, dtSup, "P");
        revenu.set(sdt.form_Aff(new Date()).substring(3, sdt.form_Aff(new Date()).length()), rv);
        
        
        ChartSeries depense = new ChartSeries();
        depense.setLabel("Dépense");
        
        rv = bean.selectSommePNG(dtInf, dtSup, "N");
        depense.set(sdt.form_Aff(new Date()).substring(3, sdt.form_Aff(new Date()).length()), rv);
       
        
        model.addSeries(revenu);
        model.addSeries(depense);
         
        return model;
    }
    
    
    private BarChartModel MonthsBarModel(Date dtInf, Date dtSup) {
        BarChartModel model = new BarChartModel();
 
        ChartSeries revenu = new ChartSeries();
        revenu.setLabel("Revenu");
        
        Date dtInf0;
        Date dtSup0;
         dt sdt = new dt();
        for(String st:sdt.getMonthsBetween2Date(dtInf, dtSup)){
            dtInf0 = sdt.getFirstDayOfMonth(sdt.strToDate("01/"+st, "dd/MM/yyyy"));
            dtSup0 = sdt.getLastDayOfMonth(sdt.strToDate("01/"+st, "dd/MM/yyyy"));
            
             double rv = bean.selectSommePNG(dtInf0, dtSup0, "P");
             revenu.set(sdt.form_Aff(dtInf0).substring(3, sdt.form_Aff(new Date()).length()), rv);
        }
                
        ChartSeries depense = new ChartSeries();
        depense.setLabel("Dépense");
                
        for(String st:sdt.getMonthsBetween2Date(dtInf, dtSup)){
            dtInf0 = sdt.getFirstDayOfMonth(sdt.strToDate("01/"+st, "dd/MM/yyyy"));
            dtSup0 = sdt.getLastDayOfMonth(sdt.strToDate("01/"+st, "dd/MM/yyyy"));
            
             double rv = bean.selectSommePNG(dtInf0, dtSup0, "N");
             depense.set(sdt.form_Aff(dtInf0).substring(3, sdt.form_Aff(new Date()).length()), rv);
        }
       
        
        model.addSeries(revenu);
        model.addSeries(depense);
         
        return model;
    }
    
    
    private BarChartModel daysBarModel(Date dtInf, Date dtSup) {
        BarChartModel model = new BarChartModel();
 
        ChartSeries revenu = new ChartSeries();
        revenu.setLabel("Revenu");
         dt sdt = new dt();
        /*
        Date dtInf = dt.getFirstDayOfMonth(new Date());
        Date dtSup = dt.getLastDayOfMonth(new Date());
        */
        
        for(Date dts:sdt.getDaysBetween(dtInf, dtSup)){
            double rv = bean.selectSommePN(dts, "P");
            revenu.set(sdt.form_Aff(dts), rv);
            System.out.println(rv);
        }

        ChartSeries depense = new ChartSeries();
        depense.setLabel("Dépense");
        
        for(Date dts:sdt.getDaysBetween(dtInf, dtSup)){
            double rv = bean.selectSommePN(dts, "N");
            depense.set(sdt.form_Aff(dts), rv);
            System.out.println(rv);
        }
        
        model.addSeries(revenu);
        model.addSeries(depense);
         
        return model;
    }
    
    public void calcule(){
            createAnimatedModels0(du, au);
    }
    
}
