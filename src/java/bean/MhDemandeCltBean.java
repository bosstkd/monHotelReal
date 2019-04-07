package bean;

import bean.agc.mhAgenceBean;
import fct.dt;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhCltSCh;
import model.MhDemandeClt;
import model.MhHotel;
import model.agc.MhAgence;
import model.agc.MhConventionAgc;

@Stateless
public class MhDemandeCltBean {
     @PersistenceContext(unitName="monHotelPU")
     private EntityManager em;
      
    @EJB
    MhHotelBean beanHtl;
   public List<MhDemandeClt> getListDemande(){
       
       MhHotel code_h = beanHtl.htl();
       String req = "select u from MhDemandeClt u where u.codeH = :code_h order by u.dateDm desc";
       Query q = em.createQuery(req);
       q.setParameter("code_h", code_h);
       
       return q.getResultList();
   }  
   
    public List<MhDemandeClt> getListDemandeA(){
       
       MhHotel code_h = beanHtl.htl();
       String req = "select u from MhDemandeClt u where u.codeH = :code_h and u.accepte = false order by u.dateDm desc";
       Query q = em.createQuery(req);
       q.setParameter("code_h", code_h);
       
       return q.getResultList();
   }  
    
    public MhDemandeClt getDemandeByCode (String codeD){
          String req = "select u from MhDemandeClt u where u.codeDmClt like :code_d ";
          Query q = em.createQuery(req);
          q.setParameter("code_d", codeD);
        try {
              return (MhDemandeClt) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
       
     
    }
    
    public boolean isDemandeAccepted(String codeD){
          String req = "select u.accepte from MhDemandeClt u where u.codeDmClt like :code_d ";
          Query q = em.createQuery(req);
          q.setParameter("code_d", codeD);
          try {
             return (boolean) q.getSingleResult();
        } catch (Exception e) {
            return true;
        }
       
    }
   
   @EJB
   mhAgenceBean beanAgc;
   public List<MhDemandeClt> getListDemandeAgc(){
         List<MhDemandeClt> listDmd = new ArrayList<MhDemandeClt>();
         List<MhHotel> htlList =  listHotelConv();
         if(htlList != null)
              for(MhHotel code_h: htlList ){
                    String req = "select u from MhDemandeClt u where u.codeH = :code_h  and u.accepte = false order by u.dateDm desc";
                     Query  q = em.createQuery(req, MhDemandeClt.class);
                       q.setParameter("code_h", code_h);
                       List<MhDemandeClt> dmList = q.getResultList();
                               for(MhDemandeClt dem: dmList){
                                     if(!listDmd.contains(dem))
                                            listDmd.add(dem);
                                         }
                }
       return listDmd;
   }  
   
   
   @EJB
   MhCltSChBean beanClt;
   public List<MhDemandeClt> getListDemandePart(){
       
       MhCltSCh clt = beanClt.clt();
       String req = "select u from MhDemandeClt u where u.npid = :clt order by u.dateDm desc";
       Query q = em.createQuery(req);
       q.setParameter("clt", clt);
       
       return q.getResultList();
   }  
     
   private List<MhConventionAgc> listAgcConv(){
       MhAgence codeA = beanAgc.agc();
       String req = "select u from MhConventionAgc u where u.codeA = :codeA";
        Query q = em.createQuery(req);
         q.setParameter("codeA", codeA);
         try {
           return q.getResultList();
       } catch (Exception e) {
           return null;
       }
   }
   
   private List<MhHotel> listHotelConv(){
       List<MhConventionAgc> conv = listAgcConv();
       List<MhHotel> htlList = new ArrayList<MhHotel>();
       if(conv != null){
                for(MhConventionAgc cnv : conv){
                    if(! htlList.contains(cnv.getCodeH())) htlList.add(cnv.getCodeH());
                }
       }
       return htlList;
   }
   
   
   public boolean remove(String codeDm){
       try {
            String req = "delete from MhDemandeClt where codeDmClt like :codeDm";
            Query q = em.createQuery(req);
            q.setParameter("codeDm", codeDm);
            q.executeUpdate();
       } catch (Exception e) {
           return false;
       }
      
       return true;
   }
   
    public boolean update(String codeDm){
       try {
            String req = "update MhDemandeClt set accepte = true where codeDmClt like :codeDm";
            Query q = em.createQuery(req);
            q.setParameter("codeDm", codeDm);
            q.executeUpdate();
       } catch (Exception e) {
           return false;
       }
      
       return true;
   }
   
      
    public boolean insert(MhDemandeClt demande){
        try {
              dt sdt = new dt();
            String req = "insert into mh_demande_clt (code_dm_clt, npid, du, au, type_chambre, nb_place, code_h, date_dm, commentaire, accepte) "
                                + "values ('"+demande.getCodeDmClt()+"','"+demande.getNpid().getNpid()+"', '"+sdt.form_Insr(demande.getDu())+"', '"+sdt.form_Insr(demande.getAu())+"', '"+demande.getTypeChambre()+"', "+demande.getNbPlace()+", '"+demande.getCodeH().getCodeH()+"', '"+sdt.form_Insr(demande.getDateDm())+"', '"+demande.getCommentaire()+"', false)";
            Query q = em.createNativeQuery(req);
            q.executeUpdate();
            
           // em.persist(demande);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        
        return true;
    }
    
    
    
    
}
