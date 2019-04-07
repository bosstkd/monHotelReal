package bean.agc;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.agc.MhAgence;
import model.agc.MhUsersDroitsAgc;

@Stateless
public class MhDroitBeanAgc {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    @EJB
    mhAgenceBean beanA;
    
    public List<MhUsersDroitsAgc> selectDroit(){

          MhAgence codeA = beanA.getByCodeA(beanA.agc().getCodeA());
          em.getEntityManagerFactory().getCache().evictAll();
        String req = "select u from MhUsersDroitsAgc u where u.codeA = :code_a ";
        Query q = em.createQuery(req, MhUsersDroitsAgc.class);
        q.setParameter("code_a", codeA);
        return q.getResultList();
    }
    
    
    public MhUsersDroitsAgc getByCodeU(String codeU){
          MhAgence codeA = beanA.getByCodeA(beanA.agc().getCodeA());
        
         String req = "select u from MhUsersDroitsAgc u where u.codeA = :codeA and u.codeU = :codeU";
         Query q = em.createQuery(req, MhUsersDroitsAgc.class);
         q.setParameter("codeA", codeA);
         q.setParameter("codeU", codeU);
         
        return (MhUsersDroitsAgc) q.getSingleResult();
    }
    
   
    
    public boolean update(String codeU, int x, boolean value){
        MhUsersDroitsAgc usrDroit = getByCodeU(codeU);
        try {
                switch (x){
                    case 1: usrDroit.setCompteUser(value);
                    break;
                    case 2: usrDroit.setInfos(value);
                    break;
                    case 3: usrDroit.setClients(value);
                    break;
                    case 4: usrDroit.setReservation(value);
                    break;
                    case 5: usrDroit.setDemReservation(value);
                    break;
                    case 6: usrDroit.setDemandeConv(value);
                    break;
                    case 7: usrDroit.setPublication(value);
                    break;
                    case 8: usrDroit.setStatuts(value);
                    break;
                }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
     
}
