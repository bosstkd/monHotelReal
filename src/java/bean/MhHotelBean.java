package bean;

import controller.Util;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhHotel;


@Stateless
public class MhHotelBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    public List<MhHotel> listMhHotel(){
       return em.createQuery("SELECT b FROM MhHotel b").getResultList();
    }
   
    
    public MhHotel getById(Integer id){
        return em.find(MhHotel.class, id);
    }
    
    public MhHotel findByCodeH(String codeH){
       String req = "SELECT m FROM MhHotel m WHERE m.codeH = :codeH";
       Query q = em.createQuery(req);
       q.setParameter("codeH", codeH);
        return (MhHotel) q.getSingleResult();
    }
    
    public List<MhHotel> findByWilaya(String wilaya){
       String req = "SELECT m FROM MhHotel m WHERE m.wilaya = :wilaya and  m.compte = :compte order by m.indiceP desc";
       Query q = em.createQuery(req);
       q.setParameter("wilaya", wilaya);
        q.setParameter("compte", true);
        return q.getResultList();
    }
    
    public List<MhHotel> findByNom(String raisonSocial){
       String req = "SELECT m FROM MhHotel m WHERE m.raisonSocial like concat('%', :raisonSocial,'%') and  m.compte = :compte order by m.indiceP desc";
       Query q = em.createQuery(req);
       q.setParameter("raisonSocial", raisonSocial);
        q.setParameter("compte", true);
        return q.getResultList();
    }
    
    public List<MhHotel> findByNomEtWilaya(String raisonSocial,String wilaya){
       String req = "SELECT m FROM MhHotel m WHERE m.raisonSocial like concat('%', :raisonSocial,'%') and m.wilaya = :wilaya and  m.compte = :compte order by m.indiceP desc";
       Query q = em.createQuery(req);
       q.setParameter("raisonSocial", raisonSocial);
       q.setParameter("wilaya", wilaya);
       q.setParameter("compte", true);
       return q.getResultList();
    }
    
    
      public List<MhHotel> findByRandom(){
       
       String req = "SELECT m FROM MhHotel m where m.compte = :compte order by m.indiceP desc";
       Query q = em.createQuery(req);
       q.setParameter("compte", true);
       q.setMaxResults(100);
        return q.getResultList();
    }
    
    
    public void creer( MhHotel hotel ) {
       try {
                em.persist( hotel );
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public String selectSingle(String condition){
        String req = "select raison_social from mh_hotel where "+condition+" ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  null;
         }
    }
    
     public String selectMail(String mail){
        String req = "select raison_social from mh_hotel where mail like '"+mail+"' ";
        
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  null;
         }
        
        
    }
     
     
     public String SingleSelect(String toFind, String code_h){
        String req = "select "+toFind+" from mh_hotel where code_h like '"+code_h+"' ";
        
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  null;
         }
    }
     
     public List<MhHotel> SingleSelectTab(String code_h){
     
        String req = "SELECT m FROM MhHotel m WHERE m.codeH = :codeH ";
        Query q = em.createQuery(req);
        q.setParameter("codeH", code_h);
         try {
             return  q.getResultList();
         } catch (Exception e) {
             return  null;
         }
    }

      public String selectRaison(String raison){
        String req = "select raison_social from mh_hotel where raison_social like '"+raison+"' ";
        
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  null;
         }
    }
      
      
       public String selectCodeUser(String code_u, String code_h, String psw){
        String req = "select nom from mh_compte_user_h where code_u like '"+code_u+"' and code_h like '"+code_h+"' and psw like '"+psw+"' ";
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
       
       
        public String selectCodeUserEmail(String email, String code_h, String psw){
        String req = "select nom from mh_compte_user_h where email like '"+email+"' and code_h like '"+code_h+"' and psw like '"+psw+"' ";
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
       
       
       public String selectCodeH(String code_h){
        String req = "select code_h from mh_hotel where code_h like '"+code_h+"' ";
         try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  null;
         }
    }
       
       public Object getSelection(String toFind){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            String req = "select "+toFind+" from mh_hotel where code_h like '"+code_h+"' ";
         try {
             return  em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  null;
         }
       }
       
        public MhHotel getMultipleSelection(){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            return findByCodeH(code_h);
       }
       
       
        public void update(Object[] infos){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            String req = "update mh_hotel set adresse = '"+infos[0].toString()+"', nrc = '"+infos[1].toString()+"', nai = '"+infos[2].toString()+"', nif = '"+infos[3].toString()+"', rib = '"+infos[4].toString()+"', tel = '"+infos[5].toString()+"', fax = '"+infos[6].toString()+"', mail = '"+infos[7].toString()+"', tva_s = "+((Boolean)infos[8])+", pension_c = "+((float)infos[9])+", prc_demi_pension = "+((int)infos[10])+", etoile = "+((int)infos[11])+", taxe_sejour = "+((float)infos[12])+", description = '"+infos[13].toString()+"' where code_h like '"+code_h+"'";
            em.createNativeQuery(req).executeUpdate();
    }
    
      public void update(MhHotel htl){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            String req = "update MhHotel set adresse = '"+htl.getAdresse()+"', nrc = '"+htl.getNrc()+"', nai = '"+htl.getNai()+"', nif = '"+htl.getNif()+"', rib = '"+htl.getRib()+"', tel = '"+htl.getTel()+"', fax = '"+htl.getFax()+"', mail = '"+htl.getMail()+"', tvaS = "+htl.getTvaS()+", pensionC = "+htl.getPensionC()+", prcDemiPension = "+htl.getPrcDemiPension()+", etoile = "+htl.getEtoile()+", taxeSejour = "+htl.getTaxeSejour()+", description = '"+htl.getDescription()+"', tva = "+htl.getTva()+", picine = "+htl.getPicine()+", restaurant = "+htl.getRestaurant()+", wifi = "+htl.getWifi()+", cafeteria = "+htl.getCafeteria()+", bar = "+htl.getBar()+" where codeH = :codeH";
            Query q = em.createQuery(req);
            q.setParameter("codeH", code_h);
            
            q.executeUpdate();
    }
     
      
       public MhHotel htl(){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        return findByCodeH(code_h);
    }
       
    
      public float tvaValue(){
          float vl = htl().getTva();
          vl = vl / 100;
          return vl;
      } 
        
}
