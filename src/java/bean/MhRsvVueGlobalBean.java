/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import controller.Util;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhRsvvueglobal;

/**
 *
 * @author Amine
 */
@Stateless
public class MhRsvVueGlobalBean {

    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;

   
    
     
      public Object singleSelectGlobal(String conditionAttr, String Attr, String toFind){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select "+toFind+" from mh_rsvvueglobal where "+conditionAttr+" like "+Attr+" and code_h like '"+code_h+"' LIMITE 1";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    } 
      
      public List<MhRsvvueglobal> SelectGlobal(String conditionAttr, String Attr, String toFind){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select "+toFind+" from mh_rsvvueglobal where "+conditionAttr+" like "+Attr+" and code_h like '"+code_h+"' order by dates desc";
        try {
             return  em.createNativeQuery(req).getResultList();
         } catch (Exception e) {
             return  null;
         }
    } 
      
      public List<MhRsvvueglobal> SelectGlobalA(){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select u.* from mh_rsvvueglobal u where  u.code_h like '"+code_h+"' order by u.dates desc";
       
        return  em.createNativeQuery(req).getResultList();
    } 
      
      public List<MhRsvvueglobal> SelectByDate(Date dt_d, Date dt_f){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select u from MhRsvvueglobal u where  u.codeH = :code_h and u.dates between :dt1 and :dt2 order by u.dates desc";
        Query q = em.createQuery(req);
        q.setParameter("code_h", code_h);
        q.setParameter("dt1", dt_d);
        q.setParameter("dt2", dt_f);
        
        return  q.getResultList();
    } 
      
      public List<MhRsvvueglobal> SelectGlobalA( String nativeCondition, String toFind){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select "+toFind+" from mh_rsvvueglobal where code_h like '"+code_h+"' "+nativeCondition+" ";
        try {
             return  em.createNativeQuery(req).getResultList();
         } catch (Exception e) {
             return  null;
         }
    } 
      
      @EJB
      MhReservationBean beanRsv;
      
      public void AjouterFct(String code_r, String numFct, String Design, String typePaiement, String numCheque, String remarque){
          remarque = remarque.replaceAll("'", "");
          numCheque = numCheque.replaceAll("'", "");
         String req = "insert into mh_rsv_fct(numFct, code_r, designation, type_paiement, dates, numCheque, remarque) values ('"+numFct+"','"+code_r+"', '"+Design+"', '"+typePaiement+"', NOW(), '"+numCheque+"', '"+remarque+"')";
        
         
         em.createNativeQuery(req).executeUpdate();
      }
    
      
      public void AnnulerFct(String numFct){
         String req = "delete from mh_rsv_fct where numFct like '"+numFct+"' ";
         em.createNativeQuery(req).executeUpdate();
      }

}
