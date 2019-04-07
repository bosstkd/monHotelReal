package controller;

import bean.MhHotelBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.MhHotel;

@ManagedBean
@SessionScoped
public class infoPersoController {
    
    
    
     @EJB 
        private MhHotelBean bean;
        private List<MhHotel>  listMhHotel;

            public List<MhHotel> getListMhHotel() {
                    this.listMhHotel = bean.listMhHotel();
                return listMhHotel;
            }

            public void setListMhHotel(List<MhHotel> listMhHotel) {
                this.listMhHotel = listMhHotel;
            }
    
    
    private String adresse;
    private String nrc;
    private String nif;
    private String nai;
    private String rib;
    private String tel;
    private String fax;
    private String mail;
    private int etoil;
    private float pension;
    private int prc_demi_pension;
    private int prc_tva;
    private float tax_sj;
    private boolean tva_s;
    
    private boolean picine;
    private boolean restaurant;
    private boolean cafeteria;
    private boolean bar;
    private boolean wifi;
    
    private String description;
    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNai() {
        return nai;
    }

    public void setNai(String nai) {
        this.nai = nai;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getEtoil() {
        return etoil;
    }

    public void setEtoil(int etoil) {
        this.etoil = etoil;
    }

    public float getPension() {
        return pension;
    }

    public void setPension(float pension) {
        this.pension = pension;
    }

    public int getPrc_demi_pension() {
        return prc_demi_pension;
    }

    public void setPrc_demi_pension(int prc_demi_pension) {
        this.prc_demi_pension = prc_demi_pension;
    }

    public int getPrc_tva() {
        return prc_tva;
    }

    public void setPrc_tva(int prc_tva) {
        this.prc_tva = prc_tva;
    }

    
    
    public float getTax_sj() {
        return tax_sj;
    }

    public void setTax_sj(float tax_sj) {
        this.tax_sj = tax_sj;
    }

    public boolean isTva_s() {
        return tva_s;
    }

    public void setTva_s(boolean tva_s) {
        this.tva_s = tva_s;
    }

    public boolean isPicine() {
        return picine;
    }

    public void setPicine(boolean picine) {
        this.picine = picine;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isCafeteria() {
        return cafeteria;
    }

    public void setCafeteria(boolean cafeteria) {
        this.cafeteria = cafeteria;
    }

    public boolean isBar() {
        return bar;
    }

    public void setBar(boolean bar) {
        this.bar = bar;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
            
    
   @PostConstruct
    public void init(){
        chargement();
    }
    
    public void chargement(){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            MhHotel hotel = bean.findByCodeH(code_h);
        
            adresse = hotel.getAdresse();
            nrc = hotel.getNrc();
            nai = hotel.getNai();
            nif = hotel.getNif();
            rib = hotel.getRib();
            tel = hotel.getTel();
            fax = hotel.getFax();
            mail = hotel.getMail();
            tva_s = hotel.getTvaS();
            pension = hotel.getPensionC();
            prc_demi_pension = hotel.getPrcDemiPension();
            prc_tva = hotel.getTva();
            etoil = hotel.getEtoile();
            tax_sj= hotel.getTaxeSejour();
              
            picine = hotel.getPicine();
            restaurant = hotel.getRestaurant();
            cafeteria = hotel.getCafeteria();
            bar = hotel.getBar();
            wifi = hotel.getWifi();
            
            
            try{
               description = hotel.getDescription();
            }catch(Exception e){
               description = "";
            }
           
   
       
    }
 
    public void modServ(int x){
         switch (x) {
             case 0:
                 picine = !picine;
                 break;
             case 1:
                 restaurant = !restaurant;
                 break;
             case 2:
                 cafeteria = !cafeteria;
                 break;
             case 3:
                 bar = !bar;
                 break;
             default:
                 wifi = !wifi;
                 break;
         }
    }
    
    public String color(boolean btn){
        if(btn){
            return "green";
        }else{
            return "red";
        }
    }
    
    public void modifier(){
      try {
          System.out.println("rahou hna");
          MhHotel hotel = new MhHotel();
          hotel.setAdresse(adresse);
          hotel.setNrc(nrc);
          hotel.setNai(nai);
          hotel.setNif(nif);
          hotel.setRib(rib);
          hotel.setTel(tel);
          hotel.setFax(fax);
          hotel.setMail(mail);
          hotel.setTvaS(tva_s);
          hotel.setPensionC(pension);
          hotel.setPrcDemiPension(prc_demi_pension);
          hotel.setTva(prc_tva);
          hotel.setEtoile(etoil);
          hotel.setTaxeSejour(tax_sj);
          hotel.setBar(bar);
          hotel.setPicine(picine);
          hotel.setRestaurant(restaurant);
          hotel.setCafeteria(cafeteria);
          hotel.setWifi(wifi);
          hotel.setDescription(description.replaceAll("'", " "));
          
       
        bean.update(hotel);
         FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Mise à jour d'information effectué avec succées.", ""));
        } catch (Exception e) {
             FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_WARN,"Erreur de mise à jour veuillez vérifier les informations introduites svp !!", ""));
        }
        
    }
}
