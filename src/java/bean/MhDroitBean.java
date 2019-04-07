package bean;

import controller.Util;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhHotel;
import model.MhUsersDroits;

@Stateless
public class MhDroitBean {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    @EJB
    MhHotelBean beanH;
    
    public List<MhUsersDroits> selectDroit(){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
          MhHotel codeH = beanH.findByCodeH(code_h);
          em.getEntityManagerFactory().getCache().evictAll();
        String req = "select u from MhUsersDroits u where u.codeH = :code_h ";
        Query q = em.createQuery(req, MhUsersDroits.class);
        q.setParameter("code_h", codeH);
        return q.getResultList();
    }
    
    
    public MhUsersDroits getByCodeU(String codeU){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         MhHotel codeH = beanH.findByCodeH(code_h);
        
         String req = "select u from MhUsersDroits u where u.codeH = :codeH and u.codeU = :codeU";
         Query q = em.createQuery(req, MhUsersDroits.class);
         q.setParameter("codeH", codeH);
         q.setParameter("codeU", codeU);
         
        return (MhUsersDroits) q.getSingleResult();
    }
    
   
    
    public boolean update(String codeU, int x, boolean value){
        MhUsersDroits usrDroit = getByCodeU(codeU);
        try {
                switch (x){
                    case 1: usrDroit.setChambre(value);
                    break;
                    case 2: usrDroit.setCompteUser(value);
                    break;
                    case 3: usrDroit.setInformationPerso(value);
                    break;
                    case 4: usrDroit.setClients(value);
                    break;
                    case 5: usrDroit.setEntreprises(value);
                    break;
                    case 6: usrDroit.setReservation(value);
                    break;
                    case 7: usrDroit.setChargeSupp(value);
                    break;
                    case 8: usrDroit.setLiberation(value);
                    break;
                    case 9: usrDroit.setVersement(value);
                    break;
                    case 10: usrDroit.setGestionCaisse(value);
                    break;
                    case 11: usrDroit.setFacturation(value);
                    break;
                    case 12: usrDroit.setCalendrier(value);
                    break;
                    case 13: usrDroit.setStatistique(value);
                    break;
                    case 14: usrDroit.setListePolice(value);
                    break;
                    case 15: usrDroit.setConvention(value);
                    break;
                }
        } catch (Exception e) {
            return false;
        }
        
        
        return true;
    }
     
}
