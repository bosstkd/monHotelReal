package bean.agc;

import controller.Util;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.agc.MhAgence;

@Stateless
public class mhAgenceBean {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    public MhAgence getByCodeA(String codeA){
        String req = "select u from MhAgence u where u.codeA like :codeA";
        Query q = em.createQuery(req);
        q.setParameter("codeA", codeA);
        try {
            return (MhAgence) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    } 
    
     public List<MhAgence> getAgcList(){
        String req = "select u from MhAgence u ";
        Query q = em.createQuery(req);
        return q.getResultList();
    } 
    
     
    public MhAgence getByMail(String mail){
        String req = "select u from MhAgence u where u.mail like :mail";
        Query q = em.createQuery(req);
        q.setParameter("mail", mail);
        try {
            return (MhAgence) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }  
     
    public boolean insert(MhAgence agc){
        try {
            em.persist(agc);    
        } catch (Exception e) {
            return false;
        }
        return true;
    } 
     
    
     public MhAgence agc(){
        HttpSession hs = Util.getSession();
        String code_a = (String) hs.getAttribute("code_a");
        return getByCodeA(code_a);
    }
    
     public String selectCodeUserEmail(String email, String code_a, String psw){
        String req = "select nom from mh_compte_user_a where email like '"+email+"' and code_a like '"+code_a+"' and psw like '"+psw+"' ";
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
     
     public boolean update(MhAgence agc){
         try {
             String code_a = agc().getCodeA();
                String req = "update MhAgence set adresse = '"+agc.getAdresse()+"', nrc = '"+agc.getNrc()+"', nai = '"+agc.getNai()+"', nif = '"+agc.getNif()+"', rib = '"+agc.getRib()+"', tel = '"+agc.getTel()+"', fax = '"+agc.getFax()+"', mail = '"+agc.getMail()+"', description = '"+agc.getDescription()+"' where codeA = :codeA";
                Query q = em.createQuery(req);
                q.setParameter("codeA", code_a);
                q.executeUpdate();
         } catch (Exception e) {
             return false;
         }
            return true;
    }
     
     
}
