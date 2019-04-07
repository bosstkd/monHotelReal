package bean;

import controller.Util;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhConvention;
import model.MhHotel;



@Stateless
public class MhConventionBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    @EJB
    MhHotelBean beanHtl; 
    public List<MhConvention> conventionList(){
         HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        MhHotel htl = beanHtl.findByCodeH(code_h);
        Query q = em.createQuery("SELECT u FROM MhConvention u where u.codeH = :codeH",MhConvention.class);
        q.setParameter("codeH", htl);
       return q.getResultList();
    }
    
    public MhConvention getByCodeConvention(String codeConvention){
        Query q = em.createQuery("SELECT u FROM MhConvention u where u.codeConvention = :codeConvention",MhConvention.class);
        q.setParameter("codeConvention", codeConvention);
        try {
             return (MhConvention) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
       
    }
    
    
    
    public boolean isConventionValide(String codeConvention){
        Query q = em.createQuery("SELECT u FROM MhConvention u where u.codeConvention = :codeConvention",MhConvention.class);
        q.setParameter("codeConvention", codeConvention);
          dt sdt = new dt();
        try {
                    MhConvention cvt = getByCodeConvention(codeConvention);
                     if(sdt.isInPeriode(cvt.getDu(), cvt.getAu(), new Date())){
                            return true;
                        }else{
                            return false;
                        }
        
            } catch (Exception e) {
                return false;
            }
    }
    
    public boolean insert(MhConvention convention){
        try {
            em.persist(convention);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
     public boolean update(MhConvention convention){
        try {
            MhConvention conv = getByCodeConvention(convention.getCodeConvention());
            conv.setPrcReduction(convention.getPrcReduction());
            conv.setDu(convention.getDu());
            conv.setAu(convention.getAu());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
     
     public boolean remove(String convention){
        try {
           Query q = em.createQuery("delete from MhConvention where codeConvention like :conv", MhConvention.class);
           q.setParameter("conv", convention);
           q.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
     
      public MhConvention convention(){
        HttpSession hs = Util.getSession();
        String codeConv = (String) hs.getAttribute("codeConvention");
        return getByCodeConvention(codeConv);
    }
      
    
}
