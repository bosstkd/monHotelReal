
package bean;

import controller.Util;
import fct.codification;
import fct.dt;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhClientSdem;
import model.MhDemandeR;
import model.MhHotel;


@Stateless
public class MhDemandeRBean {
    
     @PersistenceContext(unitName="monHotelPU")
     private EntityManager em;
    
     
     @EJB
     MhConventionBean beanConv;
     
    public List<MhDemandeR> getListDemandeR(){
        String req = "select u from MhDemandeR u where u.codeH = :codeH and u.codeC = :codeC order by u.dateDm desc";
        Query q = em.createQuery(req, MhDemandeR.class);
        
        q.setParameter("codeH", beanConv.convention().getCodeH());
        q.setParameter("codeC", beanConv.convention().getCodeC());
        return q.getResultList();
    }
    
    public List<MhClientSdem> getListCltSCh(MhDemandeR dem){
        String req = "select u  from MhClientSdem u where u.codeDm = :codeDm";
        Query q = em.createQuery(req, MhClientSdem.class);
        q.setParameter("codeDm", dem);
        return q.getResultList();
    }
    
    
    @EJB
    MhHotelBean beanHtl;
    
    @EJB
    MhEntrepriseBean beanEnt;
    
    public List<MhDemandeR> getListDemandeRHtl(){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         MhHotel htl = beanHtl.findByCodeH(code_h);
        String req = "select u from MhDemandeR u where u.codeH = :codeH order by u.dateDm desc";
        Query q = em.createQuery(req, MhDemandeR.class);
                
        q.setParameter("codeH", htl);
        return q.getResultList();
    }
    
    
     public MhDemandeR getDemandeRByCodeDm(String codeDem){
        String req = "select u from MhDemandeR u where u.codeDm like :codeDm";
        Query q = em.createQuery(req, MhDemandeR.class);
        q.setParameter("codeDm", codeDem);
        return (MhDemandeR) q.getSingleResult();
    }
    
     
     @EJB
     MhCltSChBean beanClt;
    public boolean insert(MhDemandeR dem, List<String> NPID){
        try {
            //em.persist(dem);
            dt sdt = new dt();
            codification COD = new codification();
            String req = "insert into mh_demande_r (code_dm, code_h, code_c, numch, du, au, etat_dm, date_dm, TypePension)"
                    + "values('"+dem.getCodeDm()+"', '"+dem.getCodeH().getCodeH()+"', '"+dem.getCodeC().getCodeC()+"', '"+dem.getNumch().getNumCh()+"', '"+sdt.form_Insr(dem.getDu())+"', '"+sdt.form_Insr(dem.getAu())+"', "+dem.getEtatDm()+",'"+sdt.form_Insr(dem.getDateDm())+"', '"+dem.getTypePension()+"')";
           System.out.println(req);
            Query q = em.createNativeQuery(req);
            try {
                 q.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            
            
            MhClientSdem cltDem = new MhClientSdem();
            for(String pid:NPID){
                cltDem.setCodeCltdem(COD.cd_convention(dem.getCodeDm(), pid));
                cltDem.setCodeDm(dem);
                cltDem.setNpid(beanClt.getClientByNpid(pid));
                insertNpid(cltDem);
            }
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean delete(MhDemandeR dem){
        try {
            Query q = em.createQuery("delete from MhClientSdem where codeDm = :codeDm", MhClientSdem.class);
            q.setParameter("codeDm", dem);
            q.executeUpdate();
             
            q = em.createQuery("delete from MhDemandeR where codeDm like :codeDm", MhDemandeR.class);
            q.setParameter("codeDm", dem.getCodeDm());
            q.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public void updateConf(MhDemandeR dem){
        MhDemandeR demande = getDemandeRByCodeDm(dem.getCodeDm());
        demande.setEtatDm(true);
    }
    
    public void updateAnnule(MhDemandeR dem){
        MhDemandeR demande = getDemandeRByCodeDm(dem.getCodeDm());
        demande.setEtatDm(false);
    }
    
    public void insertNpid(MhClientSdem clientDem){
      //  em.persist(clientDem);
        String req = "insert into mh_client_sdem(code_dm, npid, code_cltdem)values('"+clientDem.getCodeDm().getCodeDm()+"', '"+clientDem.getNpid().getNpid()+"', '"+clientDem.getCodeCltdem()+"')";
        System.out.println(req);
        Query q = em.createNativeQuery(req);
        try {
          q.executeUpdate();    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
