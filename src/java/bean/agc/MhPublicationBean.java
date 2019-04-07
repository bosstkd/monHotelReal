/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.agc;

import bean.MhCltSChBean;
import fct.searchModule;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhCltSCh;
import model.agc.MhAgcPublication;
import model.agc.MhAgcPublicationInscrit;
import model.agc.MhAgence;
import model.agc.MhPub;

@Stateless
public class MhPublicationBean {
    
     @PersistenceContext(unitName="monHotelPU")
     private EntityManager em;
    
     
     public MhAgcPublication getByCodePub(String codePub){
         
         String req = "select u from MhAgcPublication u where u.codePub like :codePub";
         Query q = em.createQuery(req);
         q.setParameter("codePub", codePub);
         try {
              return (MhAgcPublication) q.getSingleResult();
         } catch (Exception e) {
             return null;
         }
        
     }
     
     @EJB
     mhAgenceBean beanAgc;
     public List<MhAgcPublication> pubList(){
         MhAgence agc = beanAgc.agc();
        String req = "select u from MhAgcPublication u where u.codeA = :agc order by u.datePub desc";
         Query q = em.createQuery(req);
         q.setParameter("agc", agc);
         return q.getResultList();
     }
     
       public List<MhAgcPublication> pubListAll(){
        String req = "select u from MhAgcPublication u order by u.datePub desc";
         Query q = em.createQuery(req);
         return q.getResultList();
     }
     
     public List<MhAgcPublication> pubListPhrase(String phrase){
          List<MhPub> listGlobal = new ArrayList<MhPub>();   
          List<MhAgcPublication> rstList = new ArrayList<MhAgcPublication>();  
          searchModule STR = new searchModule();
                List<String> listMot = STR.getQueryList(phrase);
                for(String mot:listMot){
                     Query q = em.createNativeQuery(mot, MhPub.class);
                     listGlobal.addAll(q.getResultList());
                }
                for(MhPub pub:listGlobal){
                    if(!rstList.contains(getByCodePub(pub.getCodePub())))rstList.add(getByCodePub(pub.getCodePub()));
                } 

         return rstList;
     }
       
       
       
      public List<MhAgcPublicationInscrit> pubListInscrit(){
         MhAgence agc = beanAgc.agc();
        String req = "select u from MhAgcPublicationInscrit u where u.codeA = :agc order by u.dateInsc desc";
         Query q = em.createQuery(req);
         q.setParameter("agc", agc);
         return q.getResultList();
     }
     
      @EJB
      MhCltSChBean beanClt;
      
      public List<MhAgcPublicationInscrit> pubListPart(){
         MhCltSCh clt = beanClt.clt();
        String req = "select u from MhAgcPublicationInscrit u where u.npid = :clt order by u.dateInsc desc";
         Query q = em.createQuery(req);
         q.setParameter("clt", clt);
         return q.getResultList();
     }
      
     
     public boolean insert(MhAgcPublication pub){
         try {
             em.persist(pub);
         } catch (Exception e) {
             return false;
         }
         return true;
     }
     
     public boolean update(MhAgcPublication pub){
         try {
             MhAgcPublication toPub = getByCodePub(pub.getCodePub());
            
             toPub.setDateD(pub.getDateD());
             toPub.setDateF(pub.getDateF());
             toPub.setDetail(pub.getDetail());
             toPub.setTitre(pub.getTitre());
             toPub.setValide(pub.getValide());
             toPub.setDatePub(new Date());
             
         } catch (Exception e) {
             return false;
         }
         return true;
     }
     
     public boolean delete (String codePub){
         String req = "delete from MhAgcPublication u where u.codePub like :codePub";
         try {
              Query q = em.createQuery(req);
              q.setParameter("codePub", codePub);
              q.executeUpdate();
         } catch (Exception e) {
             return false;
         }
         return true;
     }
     
     public boolean deleteInsc (String numInsc){
          String req = "delete from MhAgcPublicationInscrit u where u.numInsc like :numInsc";
         try {
              Query q = em.createQuery(req);
              q.setParameter("numInsc", numInsc);
              q.executeUpdate();
         } catch (Exception e) {
             return false;
         }
         return true;
     }
     
     public boolean insertInsc(MhAgcPublicationInscrit inscPub){
         try {
             em.persist(inscPub);
         } catch (Exception e) {
             return false;
         }
         return true;
     }
     
     public List<MhAgcPublicationInscrit> getInscByCodePub(MhAgcPublication codePub){
          String req = "select u from MhAgcPublicationInscrit u where u.codePub = :codePub";
         Query q = em.createQuery(req);
         q.setParameter("codePub", codePub);
         try {
             return q.getResultList();
         } catch (Exception e) {
           //  System.err.println(e);
             return null;
         }
     }
     
      public MhAgcPublicationInscrit getInscByCodeInsc(String codeInsc){
          String req = "select u from MhAgcPublicationInscrit u where u.numInsc like :codeInsc";
         Query q = em.createQuery(req);
         q.setParameter("codeInsc", codeInsc);
         try {
             return(MhAgcPublicationInscrit) q.getSingleResult();
         } catch (Exception e) {
           //  System.err.println(e);
             return null;
         }
     }
      
      
     
     
}
