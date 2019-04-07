/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.particulier;

import bean.MhCltSChBean;
import bean.particulier.MhPartPublicationBean;
import bean.particulier.MhPubPartInscritBean;
import controller.agc.publicationController;
import fct.codification;
import fct.dt;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.particulier.MhPartPubInscrit;
import model.particulier.MhPubParticulier;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class publicationPartController {
    
private boolean wifi;
private boolean meuble;
private boolean cuisine;
private boolean garage;
private String description;
private String adresse;
private String wilaya;
private Date datePub;
private String type;
private int nbPiece;
private float prix;
private boolean active;
private Date dateDu;
private Date dateAu;

private String searchWord;

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }



    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isMeuble() {
        return meuble;
    }

    public void setMeuble(boolean meuble) {
        this.meuble = meuble;
    }

    public boolean isCuisine() {
        return cuisine;
    }

    public void setCuisine(boolean cuisine) {
        this.cuisine = cuisine;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public Date getDateDu() {
        return dateDu;
    }

    public void setDateDu(Date dateDu) {
        this.dateDu = dateDu;
    }

    public Date getDateAu() {
        return dateAu;
    }

    public void setDateAu(Date dateAu) {
        this.dateAu = dateAu;
    } 
  
//----------------------------------------------

   public void modServ(String x){
       
         switch (x) {
             case "a":
                 wifi = !wifi;
                 break;
             case "b":
                 meuble = !meuble;
                 break;
             case "c":
                 cuisine = !cuisine;
                 break;
             default:
                 garage = !garage;
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

List<MhPubParticulier> listPartPub;

@EJB
MhPartPublicationBean beanPub;

    public List<MhPubParticulier> getListPartPub() {
        listPartPub = beanPub.pubList();
        return listPartPub;
    }

    public void setListPartPub(List<MhPubParticulier> listPartPub) {
        this.listPartPub = listPartPub;
    }
     
    
List<MhPubParticulier> listPartPubAll;

    public List<MhPubParticulier> getListPartPubAll() {
                    if(searchWord.length() > 3){
                          listPartPubAll = beanPub.pubListPhrase(searchWord);
                    }else{
                          listPartPubAll = beanPub.pubListAll();
                    }
        return listPartPubAll;
    }

    public void setListPartPubAll(List<MhPubParticulier> listPartPubAll) {
        this.listPartPubAll = listPartPubAll;
    }


    

    
    
 private MhPubParticulier pubSelected;   

    public MhPubParticulier getPubSelected() {
        return pubSelected;
    }

    public void setPubSelected(MhPubParticulier pubSelected) {
        this.pubSelected = pubSelected;
    }
    
 public void onRowSelect(SelectEvent event) {
        String n_p = ((MhPubParticulier) event.getObject()).getCodePubParticulier();
        pubSelected = beanPub.getByCodePub(n_p);

            wifi = pubSelected.getWifi();
            meuble = pubSelected.getMeuble();
            cuisine = pubSelected.getCuisine();
            garage = pubSelected.getGarage();
            description = pubSelected.getDescription();
            adresse = pubSelected.getAdresse();
            wilaya = pubSelected.getWilaya();
            datePub = pubSelected.getDatePub();
            type = pubSelected.getType();
            nbPiece = pubSelected.getNbPiece();
            prix = pubSelected.getPrix();
            active = pubSelected.getActive();
            dateDu = pubSelected.getDateDu();
            dateAu = pubSelected.getDateAu();
        
    } 
    
 
   public void annule(String codePub){
         FacesContext context = FacesContext.getCurrentInstance();
         System.out.println(codePub);
        if(beanPub.delete(codePub)){
            
             try {
                 folderDelete(codePub);
             } catch (IOException ex) {
                 Logger.getLogger(publicationController.class.getName()).log(Level.SEVERE, null, ex);
             }
             context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Publication Annuler.", ""));
          }else{
             context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Opération non efféctuée", "Veuillez supprimer tout les inscrits sur la publication svp."));
        }
    }
    
     public void folderDelete(String codePub) throws IOException{
        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\resources\\uploadTest\\publications\\"+codePub);
           
            File f = new File(relativeWebPath);
            FileUtils.deleteDirectory(f);
    }
   
   @EJB
   MhCltSChBean beanClt;  
   
        public void publie(){
        MhPubParticulier pub = new MhPubParticulier();
         FacesContext context = FacesContext.getCurrentInstance();
         codification COD = new codification();
         dt DT = new dt();
        if(DT.isValidDateRes(dateDu, dateAu, new Date())){
            pub.setCodePubParticulier(COD.cd_prs(type+beanClt.clt()+new Date()));
            pub.setNPId(beanClt.clt());
            pub.setType(type);
            pub.setDateDu(dateDu);
            pub.setDateAu(dateAu);
            pub.setActive(active);
            pub.setDescription(description);
            pub.setDatePub(new Date());
            pub.setWifi(wifi);
            pub.setCuisine(cuisine);
            pub.setGarage(garage);
            pub.setMeuble(meuble);
            pub.setWilaya(wilaya);
            pub.setAdresse(adresse);
            pub.setNbPiece(nbPiece);
            pub.setPrix(prix);

            if(beanPub.insert(pub)){
                 context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Publication effectuer.", ""));
                }else{
                 context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication lors de la publication.", ""));
            }
        }else{
                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates svp !!", ""));
        }
    }
        
        
    public void modifier(){
         MhPubParticulier pub = pubSelected;
         FacesContext context = FacesContext.getCurrentInstance();
         dt DT = new dt();
        if(DT.isValidDateRes(dateAu, dateDu)){
          
            pub.setType(type);
            pub.setDateDu(dateDu);
            pub.setDateAu(dateAu);
            pub.setActive(active);
            pub.setDescription(description);
            pub.setWifi(wifi);
            pub.setCuisine(cuisine);
            pub.setGarage(garage);
            pub.setMeuble(meuble);
            pub.setWilaya(wilaya);
            pub.setAdresse(adresse);
            pub.setPrix(prix);
            pub.setNbPiece(nbPiece);

            if(beanPub.update(pub)){
                 context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Modification effectuer.", ""));
                }else{
                 context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de communication lors de la publication.", ""));
            }
        }else{
                 context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Veuillez vérifier les dates svp !!", ""));
        }
    }
     
    
    @PostConstruct
    public void init(){
        wifi = false;
        cuisine = false;
        meuble = false;
        garage = false;
        active = true;
        searchWord = "";
    }
    
    
    //-----------function de position---------//
     public String posit(MhPubParticulier pub){
       
       int i = listPartPubAll.indexOf(pub) + 1;
       if( i%3 == 0 ) return "right bottom";
       return "bottom";
   }
    
    @EJB 
    MhPubPartInscritBean beanPubPart; 
    
    public void confirmePartConnected(String npidp, String codePubParticulier){
         FacesContext context = FacesContext.getCurrentInstance();
         String npid_1 = beanClt.clt().getNpid();

         MhPartPubInscrit inscPub = new MhPartPubInscrit();
         List<MhPartPubInscrit> cdp = beanPubPart.getListPubInscByCodePub(beanPub.getByCodePub(codePubParticulier));
         MhPartPubInscrit insc = beanPubPart.getInscByNumInsc(npid_1+"-"+npidp+"-"+codePubParticulier);
         
                
             if(cdp.contains(insc)){
                  context.addMessage("Attention", new FacesMessage(FacesMessage.SEVERITY_WARN,"Inscription déja effectuer !!", ""));
             }else{
                    inscPub.setCodePubPart(beanPub.getByCodePub(codePubParticulier));
                    inscPub.setNpidins(beanClt.clt());
                    inscPub.setNpidp(beanClt.getClientByNpid(npidp));
                    inscPub.setDateInscription(new Date());
                    inscPub.setNumInscription(npid_1+"-"+npidp+"-"+codePubParticulier);
                 if(beanPubPart.insertInscPart(inscPub)){
                    context.addMessage("Information", new FacesMessage(FacesMessage.SEVERITY_INFO,"Inscription effectuer avec succès.", ""));
                 }else{
                    context.addMessage("Erreur", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur d'inscription.", ""));
                 }
             }

     }  
     
   private List<MhPartPubInscrit> pubInscRealiser;

    public List<MhPartPubInscrit> getPubInscRealiser() {
        pubInscRealiser = beanPubPart.getListInscritPartPub();
        return pubInscRealiser;
    }

    public void setPubInscRealiser(List<MhPartPubInscrit> pubInscRealiser) {
        this.pubInscRealiser = pubInscRealiser;
    }
     
     
}
