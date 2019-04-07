/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.particulier;

import bean.MhCltSChBean;
import fct.searchModule;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhCltSCh;
import model.particulier.MhPubPartView;
import model.particulier.MhPubParticulier;

@Stateless
public class MhPartPublicationBean {
    
     @PersistenceContext(unitName="monHotelPU")
     private EntityManager em;
    
     
     public MhPubParticulier getByCodePub(String codePubParticulier){
         
         String req = "select u from MhPubParticulier u where u.codePubParticulier like :codePubParticulier";
         Query q = em.createQuery(req);
         q.setParameter("codePubParticulier", codePubParticulier);
         try {
              return (MhPubParticulier) q.getSingleResult();
         } catch (Exception e) {
             return null;
         }
        
     }
     
     @EJB
     MhCltSChBean beanClt;
     public List<MhPubParticulier> pubList(){
         MhCltSCh clt = beanClt.clt();
        String req = "select u from MhPubParticulier u where u.nPId = :clt order by u.datePub desc";
         Query q = em.createQuery(req);
         q.setParameter("clt", clt);
         return q.getResultList();
     }
     
       public List<MhPubParticulier> pubListAll(){
        String req = "select u from MhPubParticulier u order by u.datePub desc";
         Query q = em.createQuery(req);
         return q.getResultList();
        }
     
       public boolean delete (String codePubParticulier){
         
           String req = "delete from MhPubParticulier u where u.codePubParticulier like :codePubParticulier";
         try {
              Query q = em.createQuery(req);
              q.setParameter("codePubParticulier", codePubParticulier);
              q.executeUpdate();
         } catch (Exception e) {
             return false;
         }
         return true;
     }

        public boolean insert(MhPubParticulier pub){
         try {
             em.persist(pub);
         } catch (Exception e) {
             return false;
         }
         return true;
     }
        
    public boolean update(MhPubParticulier pub){
         try {
             MhPubParticulier toPub = getByCodePub(pub.getCodePubParticulier());
            
             toPub.setDateDu(pub.getDateDu());
             toPub.setDateAu(pub.getDateAu());
             toPub.setDescription(pub.getDescription());
             toPub.setType(pub.getType());
             toPub.setActive(pub.getActive());
             
             toPub.setWilaya(pub.getWilaya());
             toPub.setAdresse(pub.getAdresse());
            
             toPub.setWifi(pub.getWifi());
             toPub.setCuisine(pub.getCuisine());
             toPub.setGarage(pub.getGarage());
             toPub.setMeuble(pub.getMeuble());
             
             toPub.setNbPiece(pub.getNbPiece());
             toPub.setPrix(pub.getPrix());
             
         } catch (Exception e) {
             return false;
         }
         return true;
     }
       
    
    
    
     public List<MhPubParticulier> pubListPhrase(String phrase){
          List<MhPubPartView> listGlobal = new ArrayList<MhPubPartView>();   
          List<MhPubParticulier> rstList = new ArrayList<MhPubParticulier>();  
          searchModule STR = new searchModule();
                List<String> listMot = STR.getQueryList2(phrase);
                for(String mot:listMot){
                     Query q = em.createNativeQuery(mot, MhPubPartView.class);
                     listGlobal.addAll(q.getResultList());
                }
                for(MhPubPartView pub:listGlobal){
                    if(!rstList.contains(getByCodePub(pub.getCodePubParticulier())))rstList.add(getByCodePub(pub.getCodePubParticulier()));
                } 

         return rstList;
     }
    
    
       
}
