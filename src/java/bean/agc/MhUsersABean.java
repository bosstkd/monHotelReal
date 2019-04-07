package bean.agc;

import fct.codification;
import fct.nbr;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.agc.MhAgence;
import model.agc.MhCompteUserA;


@Stateless
public class MhUsersABean {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
     public List<MhCompteUserA> listCompteUsersA(){
        String code_a = beanAgence.agc().getCodeA();
        
        Query q = em.createNativeQuery("SELECT u.* FROM mh_compte_user_a u JOIN mh_agence uco ON "
                + "u.code_a = uco.code_a "
                + "WHERE uco.code_a = ?1 ",MhCompteUserA.class);
        q.setParameter(1, code_a);
       return q.getResultList();
    }
    
    public MhCompteUserA getById(Integer id){
        return em.find(MhCompteUserA.class, id);
    }
    

     public void insert(MhCompteUserA user){
            MhAgence agcCode = beanAgence.agc();     
            user.setCodeA(agcCode);
        try {
                em.persist( user );
            } catch (Exception e) {
                e.printStackTrace();
            }
    } 
    
    //------------a utilisé pour modification d'information ------------------------    
 
     
     public void update (MhCompteUserA user){
            codification COD = new codification();
            String code_u = COD.cd_prs(user.getEmail());
           
            MhAgence agcCode = beanAgence.agc();

            String req = "update MhCompteUserA h set h.typeUser = '"+user.getTypeUser()+"', h.nom = '"+user.getNom()+"', h.psw = '"+user.getPsw()+"', h.admin = "+user.getAdmin()+", h.tel = '"+user.getTel()+"' where h.codeU = '"+code_u+"' and h.codeA = :codeA ";
            Query q = em.createQuery(req);
            q.setParameter("codeA", agcCode);
            q.executeUpdate();
     }
     
     
    
      
      public boolean updateFirstCompte(MhCompteUserA user){
           try {
               String code_a = beanAgence.agc().getCodeA();               
               String req = "update mh_compte_user_a set nom = '"+user.getNom()+"', code_u = '"+user.getCodeU()+"', psw = '"+user.getPsw()+"', tel = '"+user.getTel()+"' where code_a like '"+code_a+"' and code_u like '"+code_a+"'";
               Query q = em.createNativeQuery(req);
              
               q.executeUpdate();
          } catch (Exception e) {
              System.err.println(e);
               return false;
          }
          return true;
      }
      
//------------a utilisé pour suppriession d'information ------------------------      
     public void remove(Object[] user){
         codification COD = new codification();
        String code_a = beanAgence.agc().getCodeA();
        String code_u = COD.cd_prs(user[2].toString());
        Query query = em.createNativeQuery("DELETE FROM mh_compte_user_a WHERE code_u like '"+code_u+"' and code_a like '"+code_a+"' ");
        query.executeUpdate();
   }
  
//----------------------------------------------------------------------
     
     
     public String singleSelectUser(String code_u, String find){
           
        String code_a = beanAgence.agc().getCodeA();
           
        String req = "select "+find+" from mh_compte_user_a where code_a like '"+code_a+"' and code_u like '"+code_u+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
     
     @EJB
     mhAgenceBean beanAgence;
     
     public MhCompteUserA singleSelect(String code_u){         
         MhAgence codeA = beanAgence.agc();
         
         String req = "select u from MhCompteUserA u where u.codeA = :codeA  and u.codeU = :codeU ";
            Query q = em.createQuery(req);
            q.setParameter("codeA", codeA);
            q.setParameter("codeU", code_u);

         return (MhCompteUserA) q.getSingleResult();
     }
     
      public MhCompteUserA singleSelectByMail(String email){
     
         MhAgence codeA = beanAgence.agc();
         
         String req = "select u from MhCompteUserA u where u.codeA = :codeA  and u.email = :email ";
            Query q = em.createQuery(req);
            q.setParameter("codeA", codeA);
            q.setParameter("email", email);

         return (MhCompteUserA) q.getSingleResult();
     }
     
     public String singleSelectUserByMail(String mail, String find){
           
       String code_a = beanAgence.agc().getCodeA();
           
        String req = "select "+find+" from mh_compte_user_a where code_a like '"+code_a+"' and email like '"+mail+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
     
     public int selectCountJpql(){
           
        nbr NBR = new nbr();   
        String req = "select count(u.codeU) from MhCompteUserA u where u.codeA = :codeA and u.typeUser = :typeUser ";
        MhAgence codeA = beanAgence.agc();
        Query q = em.createQuery(req);
        q.setParameter("codeA", codeA);
        q.setParameter("typeUser", "Administrateur");
        int x = NBR.toInt(q.getSingleResult()+"");
        return  x;
    }  
    
}
