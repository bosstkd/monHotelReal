package bean;

import controller.Util;
import fct.codification;
import fct.nbr;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhCompteUserH;
import model.MhHotel;


@Stateless
public class MhUsersHBean {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
     public List<MhCompteUserH> listCompteUsersH(){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
        Query q = em.createNativeQuery("SELECT u.* FROM mh_compte_user_h u JOIN mh_hotel uco ON "
                + "u.code_h = uco.code_h "
                + "WHERE uco.code_h = ?1 ",MhCompteUserH.class);
        q.setParameter(1, code_h);
       return q.getResultList();
    }
    
    public MhCompteUserH getById(Integer id){
        return em.find(MhCompteUserH.class, id);
    }
    

     public void insert(MhCompteUserH user){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            MhHotel htlCode = beanHotel.findByCodeH(code_h);
            user.setCodeH(htlCode);
             try {
                em.persist( user );
            } catch (Exception e) {
                e.printStackTrace();
            }
    } 
    
    //------------a utilisé pour modification d'information ------------------------    
 
     
     public void update (MhCompteUserH user){
            codification COD = new codification();
            String code_u = COD.cd_prs(user.getEmail());
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            MhHotel htlCode = beanHotel.findByCodeH(code_h);

            String req = "update MhCompteUserH h set h.typeUser = '"+user.getTypeUser()+"', h.nom = '"+user.getNom()+"', h.psw = '"+user.getPsw()+"', h.admin = "+user.getAdmin()+", h.tel = '"+user.getTel()+"' where h.codeU = '"+code_u+"' and h.codeH = :codeH ";
            Query q = em.createQuery(req);
            q.setParameter("codeH", htlCode);
            q.executeUpdate();
     }
     
     
    
      
      public boolean updateFirstCompte(MhCompteUserH user){
           try {
               HttpSession hs = Util.getSession();
               String code_h = (String) hs.getAttribute("code_h");
               
               
               String req = "update mh_compte_user_h set nom = '"+user.getNom()+"', code_u = '"+user.getCodeU()+"', psw = '"+user.getPsw()+"', tel = '"+user.getTel()+"' where code_h like '"+code_h+"' and code_u like '"+code_h+"'";
               Query q = em.createNativeQuery(req);
               /*
                MhCompteUserH usr = em.find(MhCompteUserH.class, code_h);
                //System.out.println(user.getCodeU());
                usr.setCodeU(user.getCodeU());
                usr.setNom(user.getNom());
                usr.setPsw(user.getPsw());
                usr.setTel(user.getTel());
                usr.setAdmin(user.getAdmin());
                usr.setEmail(user.getEmail());
                */
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
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String code_u = COD.cd_prs(user[2].toString());
        Query query = em.createNativeQuery("DELETE FROM mh_compte_user_h WHERE code_u like '"+code_u+"' and code_h like '"+code_h+"' ");
        query.executeUpdate();
   }
  
//----------------------------------------------------------------------
     
     
     public String singleSelectUser(String code_u, String find){
           
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           
        String req = "select "+find+" from mh_compte_user_h where code_h like '"+code_h+"' and code_u like '"+code_u+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
     
     @EJB
     MhHotelBean beanHotel;
     
     public MhCompteUserH singleSelect(String code_u){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         
         MhHotel codeH = beanHotel.findByCodeH(code_h);
         
         String req = "select u from MhCompteUserH u where u.codeH = :codeH  and u.codeU = :codeU ";
            Query q = em.createQuery(req);
            q.setParameter("codeH", codeH);
            q.setParameter("codeU", code_u);

         return (MhCompteUserH) q.getSingleResult();
     }
     
      public MhCompteUserH singleSelectByMail(String email){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         
         MhHotel codeH = beanHotel.findByCodeH(code_h);
         
         String req = "select u from MhCompteUserH u where u.codeH = :codeH  and u.email = :email ";
            Query q = em.createQuery(req);
            q.setParameter("codeH", codeH);
            q.setParameter("email", email);

         return (MhCompteUserH) q.getSingleResult();
     }
     
     public String singleSelectUserByMail(String mail, String find){
           
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
           
        String req = "select "+find+" from mh_compte_user_h where code_h like '"+code_h+"' and email like '"+mail+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
     
     public int selectCountJpql(){
           
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        nbr NBR = new nbr();   
        String req = "select count(u.codeU) from MhCompteUserH u where u.codeH = :codeH and u.typeUser = :typeUser ";
        MhHotel codeH = beanHotel.findByCodeH(code_h);
        Query q = em.createQuery(req);
        q.setParameter("codeH", codeH);
        q.setParameter("typeUser", "Administrateur");
        int x = NBR.toInt(q.getSingleResult()+"");
        return  x;
    }  
     
     public MhCompteUserH usr(){
          HttpSession hs = Util.getSession();
          String code_u = (String) hs.getAttribute("code_u");
          return singleSelect(code_u);
     }
    
}
