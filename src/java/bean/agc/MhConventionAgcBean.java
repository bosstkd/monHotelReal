/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.agc;

import bean.MhHotelBean;
import bean.agc.mhAgenceBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhChambre;
import model.MhHotel;
import model.agc.MhConventionAgc;
import model.agc.MhConventionAgcChambre;
import model.agc.MhDemandeConvAgc;

@Stateless
public class MhConventionAgcBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    @EJB
    MhHotelBean beanHtl;
    
    public List<MhConventionAgc> listConvAgcHtl(String typeConv){
        
        String req = "select u from MhConventionAgc u where u.codeH = :codeH and u.typeConv like :typeConv order by u.dateConv desc";
        Query q = em.createQuery(req);
        q.setParameter("codeH", beanHtl.htl());
        q.setParameter("typeConv", typeConv);
        return q.getResultList();
    }
    
    @EJB
    mhAgenceBean beanAgc;
    public List<MhConventionAgc> listConvAgcAgc(String typeConv){
        
        String req = "select u from MhConventionAgc u where u.codeA = :codeA and u.typeConv like :typeConv order by u.dateConv desc";
        Query q = em.createQuery(req);
        q.setParameter("codeA", beanAgc.agc());
        q.setParameter("typeConv", typeConv);
        return q.getResultList();
        
    }
    
    public MhConventionAgc getByHotel(MhHotel htl, String typeConv){
      
        String req = "select u from MhConventionAgc u where u.typeConv like :typeConv and u.codeH = :codeH and u.codeA = :codeA and u.dateD <= :dts and u.dateF >= :dts";
        Query q = em.createQuery(req);
        q.setParameter("typeConv", typeConv);
        q.setParameter("codeA", beanAgc.agc());
         q.setParameter("codeH", htl);
         q.setParameter("dts", new Date());
        try {
            return (MhConventionAgc) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        
    }
    
    public MhConventionAgc getByCodeConv(String codeConvAgc){
        
        String req = "select u from MhConventionAgc u where u.codeConvAgc like :codeConvAgc";
        Query q = em.createQuery(req);
        q.setParameter("codeConvAgc", codeConvAgc);
        try {
            return (MhConventionAgc) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<MhChambre> listChConvRsv (MhHotel htl){
        MhConventionAgc convAgc = getByHotel(htl,"chRsv");
        
        String req = "select u from MhConventionAgcChambre u where u.codeConventionAgc = :convAgc";
        Query q = em.createQuery(req);
        q.setParameter("convAgc", convAgc);
        
        List<MhChambre> lstChambre = new ArrayList<MhChambre>();
        List<MhConventionAgcChambre> lstCAC = q.getResultList();
        
        for(MhConventionAgcChambre lst:lstCAC){
            lstChambre.add(lst.getNumCh());
        }
        
        return lstChambre;
    }
    
    
    public boolean insert(MhConventionAgc conv){
        try {
            em.persist(conv);
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }
    
    public boolean delete(String codeConvAgc){
        try {
            String req = "delete from MhConventionAgc where codeConvAgc like :codeConvAgc";
            Query q = em.createQuery(req);
            q.setParameter("codeConvAgc", codeConvAgc);
            q.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public MhConventionAgc getByCodeConvAgc(String codeConvAgc){
         String req = "select u from MhConventionAgc u where u.codeConvAgc = :codeConvAgc ";
         Query q = em.createQuery(req);
         q.setParameter("codeConvAgc", codeConvAgc);
        try {
                 return (MhConventionAgc) q.getSingleResult();
        } catch (Exception e) {
                return null;
        }
    }
    
    public boolean consomePrixEng(String codeConvAgc, int consommation){
        MhConventionAgc convAgc = getByCodeConvAgc(codeConvAgc);
        try {
            convAgc.setPrixEngagement(convAgc.getPrixEngagement() - consommation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
     public boolean reintegrePrixEng(String codeConvAgc, int versement){
        MhConventionAgc convAgc = getByCodeConvAgc(codeConvAgc);
        int prcCaution = convAgc.getPrcQuotionAnnule();
        
        float x = versement * prcCaution;
        x = x / 100;
        x = versement - x;
        try {
            convAgc.setPrixEngagement(convAgc.getPrixEngagement() + (int) x);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
     
     public int montant(String codeConvAgc, int versement){
        MhConventionAgc convAgc = getByCodeConvAgc(codeConvAgc);
        int prcCaution = convAgc.getPrcQuotionAnnule();
        
        float x = versement * prcCaution;
        x = x / 100;
        return (int) x; 
    }
     
    
     public MhConventionAgc getByCodeDmConv(MhDemandeConvAgc dem){
         String req = "select u from MhConventionAgc u where u.codeDmConv = :codeDmConv ";
         Query q = em.createQuery(req);
         q.setParameter("codeDmConv", dem);
        try {
                 return (MhConventionAgc) q.getSingleResult();
        } catch (Exception e) {
                return null;
        }
    }
    
    public boolean isConventionExist(String codeConvAgc){
        return getByCodeConvAgc(codeConvAgc)!=null;
    }
    
    
    
    public List<MhConventionAgcChambre> getByCodeConv(MhConventionAgc convAgc){
        String req = "select u from MhConventionAgcChambre u where u.codeConventionAgc = :convAgc";
        Query q = em.createQuery(req);
        q.setParameter("convAgc", convAgc);
        return q.getResultList();
    }
    
    
    public boolean insertChConv(MhConventionAgcChambre convAgcCh){
        try {
            em.persist(convAgcCh);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public boolean updateConvAgc(MhConventionAgc conv){
        try {
                MhConventionAgc convGet = getByCodeConv(conv.getCodeConvAgc());
                convGet.setDateD(conv.getDateD());
                convGet.setDateF(conv.getDateF());
                convGet.setTypeConv(conv.getTypeConv());
                convGet.setPrcQuotionAnnule(conv.getPrcQuotionAnnule());
                convGet.setPrcReduction(conv.getPrcReduction());
                convGet.setPrixEngagement(conv.getPrixEngagement());
                convGet.setDateConv(new Date());
        } catch (Exception e) {
            return false;
        }
      
        
        return true;
    }
    
    public boolean deleteChConv(String codeAc){
        String req = "delete from MhConventionAgcChambre where codeAc like :codeAc";
        Query q = em.createQuery(req);
        q.setParameter("codeAc", codeAc);
        try {
            q.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    
}
