package controller.agc;

import bean.MhChambreBean;
import bean.agc.MhConventionAgcBean;
import bean.MhHotelBean;
import bean.agc.MhPublicationBean;
import fct.uploadedFiles;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.MhChambre;
import model.MhHotel;
import model.agc.MhAgcPublication;
import model.agc.MhConventionAgc;

@ManagedBean
@ViewScoped
public class indexControllerAgc {
     
    
    private boolean paginatory0;

    public boolean isPaginatory0() {
        return paginatory0;
    }

    public void setPaginatory0(boolean paginatory0) {
        this.paginatory0 = paginatory0;
    }
    
    private boolean paginatory1;

    public boolean isPaginatory1() {
        return paginatory1;
    }

    public void setPaginatory1(boolean paginatory1) {
        this.paginatory1 = paginatory1;
    }
    
    

    private String recherche;

    public String getRecherche() {
        return recherche;
    }

    public void setRecherche(String recherche) {
        this.recherche = recherche;
    }
    
    private int activeIndex;

    public int getActiveIndex() {
        return activeIndex;
    }
    
    private String rechIndex;

    public String getRechIndex() {
        return rechIndex;
    }

    public void setRechIndex(String rechIndex) {
        this.rechIndex = rechIndex;
    }
    
    

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }
    
    
    
   private String raisonSociale;

    public String getRaisonSociale() {
        if(rechIndex.equals("htlRech")){
            engine(raisonSociale);
        }
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
   
    private String typeConv;

    public String getTypeConv() {
        return typeConv;
    }

    public void setTypeConv(String typeConv) {
        this.typeConv = typeConv;
    }
    
    
   
    private float prixMin;
    private float prixMax;

    public float getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(float prixMin) {
        this.prixMin = prixMin;
    }

    public float getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(float prixMax) {
        this.prixMax = prixMax;
    }
    
 //-----------------------------------------   
    
    private String Wilaya;
     public String getWilaya() {
        return Wilaya;
    }

    public void setWilaya(String Wilaya) {
        this.Wilaya = Wilaya;
    }
    
    private MhHotel selectedHtl;

    public MhHotel getSelectedHtl() {
        return selectedHtl;
    }

    public void setSelectedHtl(MhHotel selectedHtl) {
        this.selectedHtl = selectedHtl;
    }
    
    @EJB
    MhChambreBean beanCh;
    List<MhChambre> listChambre;
    
    @EJB
    MhPublicationBean beanPub;
    List<MhAgcPublication> listPub;

    public List<MhAgcPublication> getListPub() {
         if(!rechIndex.equals("htlRech")){
                    if(raisonSociale.length() > 3){
                        listPub = beanPub.pubListPhrase(raisonSociale);
                    }else{
                        listPub = beanPub.pubListAll();
                    }
         }
      if(listPub == null) paginatory1 = false;
      else {
           if(listPub.size()>10){
                    paginatory1 = true;
                }else{
                    paginatory1 = false;
                }
      }
     
        
        return listPub;
    }

    public void setListPub(List<MhAgcPublication> listPub) {
        this.listPub = listPub;
    }

   
    
    
    
    @EJB
    MhHotelBean bean;
    List<MhHotel> listHotel;

    public List<MhHotel> getListHotel() {
        if(rechIndex.equals("htlRech")){
                if(Wilaya == null) Wilaya = "";
                if(raisonSociale == null) raisonSociale = "";

                if(prixMin > 0 && prixMax < 1){
                    listChambre = beanCh.listMinPrix(prixMin);
                }else if(prixMin < 1 && prixMax > 0){
                    listChambre = beanCh.listMaxPrix(prixMax);
                }else if(prixMin > 0 && prixMax > 0){
                    listChambre = beanCh.listMinMaxPrix(prixMin, prixMax);  
                }

                if(listChambre != null){
                   List<MhHotel> listHotelCh  = new ArrayList<MhHotel>();
                   MhHotel hotelCh0 = new MhHotel();
                    for(MhChambre chm: listChambre){
                         if(!chm.getCodeH().getCodeH().equals(hotelCh0.getCodeH())){
                                listHotelCh.add(chm.getCodeH());
                                hotelCh0 = chm.getCodeH();
                         }

                    }

                    if(!Wilaya.equals("") && raisonSociale.equals("")){
                        listHotel = bean.findByWilaya(Wilaya);
                    }else if(Wilaya.equals("") && !raisonSociale.equals("")){
                        listHotel = bean.findByNom(raisonSociale);
                    }else{
                        listHotel = bean.findByNomEtWilaya(raisonSociale, Wilaya);
                    }            
                    if(listHotel != null){
                        if(listHotel.size()>0){
                            List<MhHotel> listGetHotel  = new ArrayList<MhHotel>();
                            for(MhHotel htl:listHotel){
                                if(listHotelCh.contains(htl)) listGetHotel.add(htl);
                            }
                            if(listGetHotel.size()>0) listHotel = listGetHotel;
                            else listHotel = listHotelCh;
                        }else{
                            listHotel = listHotelCh;
                        }
                    }else{
                            listHotel = listHotelCh;
                        }

                }else{
                  if(Wilaya.equals("") && raisonSociale.equals("")){
                        listHotel = bean.findByRandom();
                    }else if(!Wilaya.equals("") && raisonSociale.equals("")){
                        listHotel = bean.findByWilaya(Wilaya);
                    }else if(Wilaya.equals("") && !raisonSociale.equals("")){
                        listHotel = bean.findByNom(raisonSociale);
                    }else{
                        listHotel = bean.findByNomEtWilaya(raisonSociale, Wilaya);
                    }  


                }
                    listHotel.forEach((htl) -> {
                      htl.setRaisonSocial(htl.getRaisonSocial().toUpperCase());
                    });
        }
        
             if(listHotel == null) paginatory0 = false;
             else {
                      if(listHotel.size()>10) 
                                 paginatory0 = true;
                         else
                                 paginatory0 = false; 
                  }
        
       
        
        return listHotel;
    }

    public void setListHotel(List<MhHotel> listHotel) {
        this.listHotel = listHotel;
    }
    
    
     List<MhHotel> listHotelConv;
    
    @EJB
    MhConventionAgcBean beanConvAgc;
    public List<MhHotel> getListHotelConv() {
         List<MhHotel> lstHtlToRet = new ArrayList<MhHotel>();
        List<MhHotel> lstHtl = getListHotel();
         
         for(MhHotel htl:lstHtl){
             MhConventionAgc conv = beanConvAgc.getByHotel(htl, typeConv);
             if(conv != null) lstHtlToRet.add(htl);
         }
         
        listHotelConv = lstHtlToRet;
        return listHotelConv;
    }


    
    public void setListHotelConv(List<MhHotel> listHotelConv) {
        this.listHotelConv = listHotelConv;
    }
 

     
    @PostConstruct
    void init(){
        listPub = new ArrayList<MhAgcPublication>();
         Wilaya = "";
         raisonSociale = "";
         typeConv = "chLib";
         recherche = "";
         activeIndex = 1;
         rechIndex = "agcRech";
    }
    
    
    public void engine(String req){
        recherche = req;
        System.out.println(req);
    }
    
    public void procedRech(){
        if(rechIndex.equals("htlRech")){
             activeIndex = 0;
        }else{
             activeIndex = 1;
        }
    }
    
   
    
   public String img(String codeH){
         String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/uploadTest/"+codeH+"/photos/");
         System.out.println(relativeWebPath);
          
         try {
            uploadedFiles UPF = new uploadedFiles();
            List<String> imgs = UPF.getFileName(relativeWebPath);
            List<String> images = new ArrayList<String>();

            for(String img:imgs){
                images.add(img);
            }

            int i = (int )(Math. random() * images.size());
            System.out.println(images.get(i));
            if(images.get(i) == null) 
                return "";
            else
                return images.get(i);
       } catch (Exception e) {
           return "";
       }
        
    }
   
   public String posit(MhHotel htl){
       
       int i = listHotel.indexOf(htl) + 1;
       if( i%3 == 0 ) return "right bottom";
       return "bottom";
   }
    
}
