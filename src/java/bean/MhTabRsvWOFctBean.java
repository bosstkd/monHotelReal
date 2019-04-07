package bean;

import controller.Util;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhTabRsvWOFct;

@Stateless
public class MhTabRsvWOFctBean {
     
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;

    public List <MhTabRsvWOFct> modelRsvWOFct(String code_r){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       Query q = em.createNativeQuery("SELECT b.* FROM mhrsvvue_woutfct b where b.code_h like '"+code_h+"' and code_r like '"+code_r+"' ",MhTabRsvWOFct.class);
       return  q.getResultList();
    }
    
    public List <MhTabRsvWOFct> modelRsvWOFctH(String code_r, String code_h){
       Query q = em.createNativeQuery("SELECT b.* FROM mhrsvvue_woutfct b where b.code_h like '"+code_h+"' and code_r like '"+code_r+"' ",MhTabRsvWOFct.class);
       return  q.getResultList();
    }
    
     public List<MhTabRsvWOFct> listMhTabRsvWOFctClot(){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       em.getEntityManagerFactory().getCache().evictAll();
       Query q = em.createQuery("SELECT b FROM MhTabRsvWOFct b where b.codeH = :code_h ORDER BY b.DateR desc",MhTabRsvWOFct.class);
       q.setParameter("code_h", code_h);
       return q.getResultList();
    }
     /*
      public List<MhTabRsvWOFct> listMhTabRsvWOFctNative(){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       String req = "SELECT b.* FROM mhrsvvue_woutfct b where b.code_h like '"+code_h+"' ORDER BY b.date_r desc";
       System.out.println(req);
       Query q = em.createNativeQuery(req,MhTabRsvWOFct.class);
       return q.getResultList();
    }
     */
     
     public List<MhTabRsvWOFct> listRsvYear(){
         dt sdt = new dt();
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       String year = sdt.Annee(new Date());
       Query q = em.createQuery("SELECT b FROM MhTabRsvWOFct b where b.codeH = :code_h  and b.dtA >= :year  and b.dtD <= :year1 ",MhTabRsvWOFct.class);
       q.setParameter("code_h", code_h);
       Date dts = sdt.strToDate(year+"-01-01", "yyyy-MM-dd");
       q.setParameter("year", dts);
       dts = sdt.strToDate(year+"-12-30", "yyyy-MM-dd");
       q.setParameter("year1", dts);
       return q.getResultList();
    }
 
     public List<MhTabRsvWOFct> listMhTabRsvWOFctAct(){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       em.getEntityManagerFactory().getCache().evict(MhTabRsvWOFct.class);
       Query q = em.createQuery("SELECT b FROM MhTabRsvWOFct b where b.codeH = :codeH and ((b.dtA <= :dtA and b.dtD > :dtD) or b.periodeOuverte = :po) ORDER BY b.DateR desc",MhTabRsvWOFct.class);
       q.setParameter("codeH", code_h);
       q.setParameter("dtA", new Date());
       q.setParameter("dtD", new Date());
       q.setParameter("po", true);
       return q.getResultList();
    }
     
     
     public List<MhTabRsvWOFct> listPolice(){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       em.getEntityManagerFactory().getCache().evict(MhTabRsvWOFct.class);
       Query q = em.createQuery("SELECT b FROM MhTabRsvWOFct b where b.codeH = :codeH and ( b.dtA <= :dtA and b.dtD > :dtD ) ORDER BY b.dtA desc",MhTabRsvWOFct.class);
       q.setParameter("codeH", code_h);
       q.setParameter("dtA", new Date());
       q.setParameter("dtD", new Date());
       return q.getResultList();
    }
    
    
    public MhTabRsvWOFct getById(Integer id){
        return em.find(MhTabRsvWOFct.class, id);
    }
 
     public MhTabRsvWOFct selectionA(String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select b from MhTabRsvWOFct b where b.codeH = :code_h and b.codeR = :code_r";
        Query q = em.createQuery(req);
        q.setParameter("code_h", code_h);
        q.setParameter("code_r", codeR);
        q.setMaxResults(1);
        
         try {
              return (MhTabRsvWOFct) q.getSingleResult();
         } catch (Exception e) {
             return null;
         }
       
     }
     
     public long countSelection(String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select count(b.npid) from MhTabRsvWOFct b where b.codeH = :code_h and b.codeR = :code_r";
        Query q = em.createQuery(req);
        q.setParameter("code_h", code_h);
        q.setParameter("code_r", codeR);
        
         try {
              return (long) q.getSingleResult();
         } catch (Exception e) {
             e.printStackTrace();
             return 0L;
         }
       
     }
     
     
     /*
     public Object selectionA(String find, String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           
        String req = "select "+find+" from mhrsvvue_woutfct where code_h like '"+code_h+"' and code_r like '"+codeR+"' LIMIT 1";
        try {
             return   em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  null;
         }
     }
     */
     
     public Object[] selectionCondition(String find, String condition){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           
        String req = "select "+find+" from mhrsvvue_woutfct where code_h like '"+code_h+"' and "+condition+" LIMIT 1";
     
        try {
             return (Object[]) em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  null;
         }
     }
     
     public MhTabRsvWOFct selectByCodeR(String codeR){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         String req = "select u from MhTabRsvWOFct u where u.codeR = :codeR and u.codeH = :codeH";
         Query q = em.createQuery(req);
         q.setParameter("codeR", codeR);
         q.setParameter("codeH", code_h);
         q.setMaxResults(1);
         return (MhTabRsvWOFct) q.getSingleResult();
     }
    
}
