/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.agc;

import bean.MhHotelBean;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.agc.MhDemandeConvAgc;

@Stateless
public class MhDemandeConvAgcBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    
    @EJB
    mhAgenceBean beanAgc;
    public List<MhDemandeConvAgc> listDemandeConvAgc(){
                
              String req = "select u from MhDemandeConvAgc u where u.codeA = :codeA order by u.dateDmConv desc ";
              Query q = em.createQuery(req);
              q.setParameter("codeA", beanAgc.agc());
              
             return q.getResultList();
            }    
    
     @EJB
     MhHotelBean beanHtl;
     public List<MhDemandeConvAgc> listDemandeConvHtl(){
                
              String req = "select u from MhDemandeConvAgc u where u.codeH = :codeH order by u.dateDmConv desc ";
              Query q = em.createQuery(req);
              q.setParameter("codeH", beanHtl.htl());
              
             return q.getResultList();
            }    
    
     public MhDemandeConvAgc getByCodeDm(String codeDmConv){
              String req = "select u from MhDemandeConvAgc u where u.codeDmConv = :codeDmConv ";
              Query q = em.createQuery(req);
             try {
              q.setParameter("codeDmConv", codeDmConv);
              return (MhDemandeConvAgc) q.getSingleResult();
         } catch (Exception e) {
             return null;
         }
             
     }
    
    public boolean insert(MhDemandeConvAgc demande){
        try {
            em.persist(demande);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
    
    public boolean delete(String codeDem){
        String req = "delete from MhDemandeConvAgc where codeDmConv like :CodeDmConv ";
        try {
            Query q = em.createQuery(req);
            q.setParameter("CodeDmConv", codeDem);
            try {
                q.executeUpdate();
            } catch (Exception e0) {
                return false;
            }
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
    
}
