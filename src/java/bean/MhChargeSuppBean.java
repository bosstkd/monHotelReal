package bean;

import controller.Util;
import fct.nbr;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhChargeSupp;

@Stateless
public class MhChargeSuppBean {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
     public List<MhChargeSupp> listChargeSupp(){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         String req = "SELECT u FROM MhChargeSupp u where u.code_h = :code_h order by u.dtChrg desc";
         Query q = em.createQuery(req,MhChargeSupp.class);
         q.setParameter("code_h", code_h);
         
        return q.getResultList();
    }
     
    public List<Object> select(String code_r, String attribut){
        HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         String req = "SELECT "+attribut+" FROM mh_charge_supp u where u.code_h = ?1 and u.code_r like '"+code_r+"' ";
         Query q = em.createNativeQuery(req,MhChargeSupp.class);
         q.setParameter(1, code_h);
        return q.getResultList();
    }
    
    
     public List<MhChargeSupp> select(String code_r){
        HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         String req = "SELECT u FROM MhChargeSupp u where u.code_h = :codeH and u.code_r = :codeR ";
         Query q = em.createQuery(req,MhChargeSupp.class);
         q.setParameter("codeH", code_h);
         q.setParameter("codeR", code_r);
        return q.getResultList();
    }
     
    public double selectSomme(String code_r){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
          double nbrDbl = 0;
          
          nbr NBR = new nbr();
          
         String req = "SELECT SUM(u.prix_ch) AS TotalItemsOrdered FROM mh_charge_supp u where u.code_h like '"+code_h+"' and u.code_r like '"+code_r+"' ";
            
             Query q = em.createNativeQuery(req);
                   Object results = (Object) q.getSingleResult();
                   String str =  results+""; 
                   if(str!=null){
                       try {
                          return  nbrDbl = NBR.toDouble(str);
                       } catch (Exception e) {
                          return  nbrDbl = 0;
                       }
                   }
        return 0;
          }
   //-----------a utilisé pour insertion d'information ------------------------  
   
    public boolean insert(MhChargeSupp charge){
        try {
            em.persist(charge);
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
   //------------a utilisé pour suppriession d'information ------------------------      
     public boolean remove(String code_r, String charge){
         try {
             Query query = em.createQuery("DELETE FROM MhChargeSupp WHERE code_r = :code_r and charge = :charge ");
            query.setParameter("code_r", code_r);
            query.setParameter("charge", charge);
            query.executeUpdate();
         } catch (Exception e) {
             return false;
         }
         return true;
   }
}
