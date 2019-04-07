/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.particulier;

import bean.MhCltSChBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhCltSCh;
import model.particulier.MhPartPubInscrit;
import model.particulier.MhPubParticulier;

@Stateless
public class MhPubPartInscritBean {
     @PersistenceContext(unitName="monHotelPU")
     private EntityManager em;
     
    public List<MhPartPubInscrit> getListPubInscByCodePub (MhPubParticulier codePub){
         
         String req = "select u from MhPartPubInscrit u where u.codePubPart = :codePub";
         Query q = em.createQuery(req);
         q.setParameter("codePub", codePub);
        try {
             return q.getResultList();
        } catch (Exception e) {
             return null;
        }
     }
    
    @EJB
    MhCltSChBean beanClt;
    public List<MhPartPubInscrit> getListInscritPartPub(){
         MhCltSCh clt = beanClt.clt();
         String req = "select u from MhPartPubInscrit u where u.npidp = :clt";
         Query q = em.createQuery(req);
         q.setParameter("clt", clt);
        try {
             return q.getResultList();
        } catch (Exception e) {
             return null;
        }
     }
    
    
    public MhPartPubInscrit getInscByNumInsc(String numInsc){
         String req = "select u from MhPartPubInscrit u where u.numInscription like :numInsc";
         Query q = em.createQuery(req);
         q.setParameter("numInsc", numInsc);
        try {
            return (MhPartPubInscrit) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
         
    }
     
    public boolean insertInscPart(MhPartPubInscrit inscription){
        try {
            em.persist(inscription);
            return true;
        } catch (Exception e) {
        }
        return false;
    }
     
}
