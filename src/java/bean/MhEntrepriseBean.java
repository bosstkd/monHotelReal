package bean;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhCltFct;


@Stateless
public class MhEntrepriseBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    public List<MhCltFct> listCltFct(){
        Query q = em.createQuery("SELECT u FROM MhCltFct u ORDER BY u.raisonSociale ",MhCltFct.class);
       return q.getResultList();
    }
    
    public List<Object> listRaisonSocialeFct(){
        String req ="SELECT u.raison_sociale FROM mh_clt_fct u ORDER BY u.raison_sociale ";
        System.out.println(req);
        Query q = em.createNativeQuery(req, MhCltFct.class);
        return q.getResultList();
    }
    
     public List<MhCltFct> SingleSelectTab(String code_c){
        Query q = em.createQuery("SELECT m FROM MhCltFct m where m.codeC = :code_c ");
        q.setParameter("code_c", code_c);
       return q.getResultList();
    }
    
    public MhCltFct getById(Integer id){
        return em.find(MhCltFct.class, id);
    }
  
    public boolean insert(MhCltFct ent){
        try {
            em.persist(ent);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    
    public boolean update(MhCltFct ent){
        try {
                MhCltFct etp = em.find(MhCltFct.class, ent.getCodeC());
                etp.setRaisonSociale(ent.getRaisonSociale());
                etp.setNrc(ent.getNrc());
                etp.setNai(ent.getNai());
                etp.setNif(ent.getNif());
                etp.setAdresse(ent.getAdresse());
                etp.setTel(ent.getTel());
                etp.setExonore(ent.getExonore());
                etp.setMail(ent.getMail());
        } catch (Exception e) {
            System.err.println(e);
                return false;
        }
        return true;
    }
    
    
    public Object singleSelectEntreprise( String find, String code_c){

        String req = "select "+find+" from mh_clt_fct where code_c like '"+code_c+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
    
    
    public MhCltFct singleSelectEntJPQL(String code_c){

        String req = "select m from MhCltFct m where m.codeC = :code_c ";
              Query q = em.createQuery(req);
              q.setParameter("code_c", code_c);
           try {
            return (MhCltFct) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }     
    }
    
    
    public boolean isCodeClient(String code_c){
        return !singleSelectEntreprise("code_c", code_c).toString().equals("");
    }
    
    public boolean isClientExo(String code_c){
        String req = "select exonore from mh_clt_fct where code_c like '"+code_c+"' ";
        try {
             return  (boolean) em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  false;
         }
    }
    
    public void remove(String code_c){
            String req = "delete from mh_clt_fct where code_c like '"+code_c+"'";
            em.createNativeQuery(req).executeUpdate();
      }
    
}
