package bean;

import controller.Util;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhRsvvueglobal;

@Stateless
public class MhTabReservationBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    
    
    
     public List<MhRsvvueglobal> listMhTabRsv(){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       Query q = em.createNativeQuery("SELECT b.* FROM mh_rsvvueglobal b where b.code_h like '"+code_h+"' ORDER BY dates desc", MhRsvvueglobal.class);
       return q.getResultList();
    }
    
     public List<MhRsvvueglobal> selectFct(String numFct){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       Query q = em.createNativeQuery("SELECT b.* FROM mh_rsvvueglobal b where b.code_h like '"+code_h+"' and numFct like '"+numFct+"' ORDER BY dates ", MhRsvvueglobal.class);
       return q.getResultList();
    }
    
     public MhRsvvueglobal getById(Integer id){
        return em.find(MhRsvvueglobal.class, id);
    }

     public Object[] selection(String find[], String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           String search = "";
                   
           for(String find1:find){
               search = search+find1+", ";
           }
           search = search.substring(0, search.length()-2);
        String req = "select "+search+" from mh_rsvvueglobal where code_h like '"+code_h+"' and code_r like '"+codeR+"' LIMIT 1 ";
        try {
             return  (Object[]) em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  null;
         }
     }
     /*
     public Object selectionA(String find, String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           
        String req = "select "+find+" from mh_RsvVueGlobal where code_h like '"+code_h+"' and code_r like '"+codeR+"' LIMIT 1 ";
        
        try {
             return   em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  null;
         }
     }
     */
     
      public MhRsvvueglobal selectionA(String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           
        String req = "select b from MhRsvvueglobal b where b.codeH = :code_h and b.codeR = :codeR ";
        Query q = em.createQuery(req);
        q.setParameter("code_h", code_h);
         q.setParameter("codeR", codeR);
         q.setMaxResults(1);
        try {
             return (MhRsvvueglobal)  q.getSingleResult();
         } catch (Exception e) {
             return  null;
         }
     }
      
      public MhRsvvueglobal selectionAH(String codeR, String code_h){

           
        String req = "select b from MhRsvvueglobal b where b.codeH = :code_h and b.codeR = :codeR ";
        Query q = em.createQuery(req);
        q.setParameter("code_h", code_h);
         q.setParameter("codeR", codeR);
         q.setMaxResults(1);
        try {
             return (MhRsvvueglobal)  q.getSingleResult();
         } catch (Exception e) {
             return  null;
         }
     }
     
}
