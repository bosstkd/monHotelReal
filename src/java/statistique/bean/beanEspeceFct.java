package statistique.bean;

import bean.MhHotelBean;
import java.util.Date;
import javax.ejb.EJB;


public class beanEspeceFct extends beanForTaxe{

    public beanEspeceFct() {
    }

    public beanEspeceFct(String numFct, Date dates, String codeC,String typePaiement, float charge, float prixCh, int nbNuitee, int nbPersonne, float pension, int prcPension, String typePension, boolean clientExoTva, float prixTx, int prcReduction) {
        this.numFct = numFct;
        this.dates = dates;
        this.CodeC = codeC;
        this.typePaiement = typePaiement;
        this.charge = charge;
        this.prixCh = prixCh;
        this.nbNuitee = nbNuitee;
        this.nbPersonne = nbPersonne;
        this.pension = pension;
        this.prcPension = prcPension;
        this.typePension = typePension;
        this.clientExoTva = clientExoTva;
        this.prixTx = prixTx;
        this.prcReduction = prcReduction;
    }

  
    
    
  
   
    private String numFct;
    private Date dates;
    private String CodeC;
    private String typePaiement;
    private float charge; 
    private float prixCh;
    private int nbNuitee; 
    private int nbPersonne;
    private float pension;
    private int prcPension;
    private String typePension;
    private boolean clientExoTva;
    private float prixTx;
    private int prcReduction;

    public String getTypePaiement() {
        return typePaiement;
    }

    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public float getPrixCh() {
        return prixCh;
    }

    public void setPrixCh(float prixCh) {
        this.prixCh = prixCh;
    }

    public int getNbNuitee() {
        return nbNuitee;
    }

    public void setNbNuitee(int nbNuitee) {
        this.nbNuitee = nbNuitee;
    }

    public int getNbPersonne() {
        return nbPersonne;
    }

    public void setNbPersonne(int nbPersonne) {
        this.nbPersonne = nbPersonne;
    }

    public float getPension() {
        return pension;
    }

    public void setPension(float pension) {
        this.pension = pension;
    }

    public int getPrcPension() {
        return prcPension;
    }

    public void setPrcPension(int prcPension) {
        this.prcPension = prcPension;
    }

    public String getTypePension() {
        return typePension;
    }

    public void setTypePension(String typePension) {
        this.typePension = typePension;
    }

    public boolean isClientExoTva() {
        return clientExoTva;
    }

    public void setClientExoTva(boolean clientExoTva) {
        this.clientExoTva = clientExoTva;
    }

    public float getPrixTx() {
        return prixTx;
    }

    public void setPrixTx(float prixTx) {
        this.prixTx = prixTx;
    }

    public String getNumFct() {
        return numFct;
    }

    public void setNumFct(String numFct) {
        this.numFct = numFct;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getCodeC() {
        return CodeC;
    }

    public void setCodeC(String CodeC) {
        this.CodeC = CodeC;
    }

    public int getPrcReduction() {
        return prcReduction;
    }

    public void setPrcReduction(int prcReduction) {
        this.prcReduction = prcReduction;
    }

    
   
    
    public double getTotal(double valTva) {
        return super.total(typePaiement, charge, prixCh, nbNuitee, nbPersonne, pension, prcPension, typePension, clientExoTva, prixTx, prcReduction, valTva);
    }

    public double getTaxe(double valTva) {
        return super.taxe(typePaiement, charge, prixCh, nbNuitee, nbPersonne, pension, prcPension, typePension, clientExoTva, prixTx, prcReduction, valTva);
    }

}
