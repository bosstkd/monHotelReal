/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import controller.Util;
import fct.listTraitement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhChambre;
import model.MhConvention;
import model.MhHotel;
import model.MhReservation;
import model.agc.MhConventionAgc;
import model.agc.MhConventionAgcChambre;

/**
 *
 * @author Amine
 */

@Stateless
public class MhChambreBean {
     @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
     
    public List<MhChambre> listChambre(){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
        Query q = em.createNativeQuery("SELECT u.* FROM mh_chambre u JOIN mh_hotel uco ON "
                + "u.code_h = uco.code_h "
                + "WHERE uco.code_h = ?1 ",MhChambre.class);
        q.setParameter(1, code_h);
       // em.createQuery("SELECT b FROM MhChambre b")
       return q.getResultList();
    }
    
    public List<MhChambre> listChambre(MhHotel codeH){        
        Query q = em.createQuery("SELECT u FROM MhChambre u where u.codeH = :codeH ",MhChambre.class);
        q.setParameter("codeH", codeH);
       return q.getResultList();
    }
    
    @EJB
    MhConventionBean beanConv;
    
    public List<MhChambre> listChambreLibreEnt(Date du, Date au){
        MhConvention conv = beanConv.convention();
        MhHotel code_h = conv.getCodeH();
        Query q = em.createQuery("SELECT u FROM MhChambre u where u.codeH = :code_h and u.visible = :visible",MhChambre.class);
        q.setParameter("code_h", code_h);
        q.setParameter("visible", true);
        List<MhChambre> listTout = q.getResultList();
        
        q = em.createQuery("SELECT u FROM MhReservation u where ( u.dateA < :dtD and u.dateD > :dtD ) OR ( u.dateA <= :dtA and u.dateD > :dtA ) and u.codeH = :code_h ",MhChambre.class);
        q.setParameter("code_h", code_h);
         q.setParameter("dtA", du);
          q.setParameter("dtD", au);
        List<MhReservation> listReservation = q.getResultList();
        
        List<MhChambre> listOccuper = new ArrayList<MhChambre>();
        
        listReservation.forEach((rsv) -> {
            listOccuper.add(rsv.getNumCh());
         });
        List<MhChambre> listResultat = new ArrayList<MhChambre>();
        boolean ok = false;
        for(MhChambre chTt : listTout){
               ok = false;
               for(MhChambre chOcp : listOccuper){
                   if(chTt.getNumCh().equals(chOcp.getNumCh())){
                       ok = true;
                       break;
                   }
               }
               if(!ok){
                   listResultat.add(chTt);
               }
            }
        
        listTraitement LST = new listTraitement();
        List<MhChambre> lstRst = new ArrayList<MhChambre>();
        
        lstRst = LST.listMinsList(listResultat, chambreExitConv());
        
        return lstRst;
    }
    
    
    public List<MhChambre> listChambreLibreEntCodeHtl(Date du, Date au, MhHotel code_h){

                
        Query q = em.createQuery("SELECT u FROM MhChambre u where u.codeH = :code_h ",MhChambre.class);
        q.setParameter("code_h", code_h);
        List<MhChambre> listTout = q.getResultList();
        
        q = em.createQuery("SELECT u FROM MhReservation u where ( u.dateA < :dtD and u.dateD > :dtD ) OR ( u.dateA <= :dtA and u.dateD > :dtA ) and u.codeH = :code_h ",MhChambre.class);
        q.setParameter("code_h", code_h);
         q.setParameter("dtA", du);
          q.setParameter("dtD", au);
        List<MhReservation> listReservation = q.getResultList();
        
        List<MhChambre> listOccuper = new ArrayList<MhChambre>();
        
        listReservation.forEach((rsv) -> {
            listOccuper.add(rsv.getNumCh());
         });
        List<MhChambre> listResultat = new ArrayList<MhChambre>();
        boolean ok = false;
        for(MhChambre chTt : listTout){
               ok = false;
               for(MhChambre chOcp : listOccuper){
                   if(chTt.getNumCh().equals(chOcp.getNumCh())){
                       ok = true;
                       break;
                   }
               }
               if(!ok){
                   listResultat.add(chTt);
               }
            }
        
        listTraitement LST = new listTraitement();
        List<MhChambre> lstRst = new ArrayList<MhChambre>();
        
        lstRst = LST.listMinsList(listResultat, chambreExitConv());
        
        return lstRst;
    }
    
    public MhHotel getById(Integer id){
        return em.find(MhHotel.class, id);
    }
    
    public List<MhChambre> listMinPrix(float prix){
        String req = "select u from MhChambre u where u.prix >= :prix";
        Query q = em.createQuery(req, MhChambre.class);
        q.setParameter("prix", prix);
        return q.getResultList();
    }
    
     public List<MhChambre> listMaxPrix(float prix){
        String req = "select u from MhChambre u where u.prix <= :prix";
        Query q = em.createQuery(req, MhChambre.class);
        q.setParameter("prix", prix);
        return q.getResultList();
    }
     
     public List<MhChambre> listMinMaxPrix(float prixMin, float prixMax){
        String req = "select u from MhChambre u where u.prix <= :prixMax and u.prix >= :prixMin";
        Query q = em.createQuery(req, MhChambre.class);
        q.setParameter("prixMin", prixMin);
        q.setParameter("prixMax", prixMax);
        return q.getResultList();
    }
    
 
 //-----------a utilisé pour insertion d'information ------------------------  
    
    public void insert(MhChambre chambre){
           try {
                em.persist( chambre );
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
//------------a utilisé pour modification d'information ------------------------    

     public void update(MhChambre chambre){           
            String req = "update MhChambre U set U.nbPlace = "+chambre.getNbPlace()+", U.prix = "+chambre.getPrix()+", U.prcGainAgc = "+chambre.getPrcGainAgc()+", U.typeCh = '"+chambre.getTypeCh()+"', U.visible = "+chambre.getVisible()+" where U.numCh = '"+chambre.getNumCh()+"' ";
            Query q = em.createQuery(req);
            q.executeUpdate();
    }
     
//------------a utilisé pour suppriession d'information ------------------------      
     
     public boolean remove(MhChambre chambre){
         
                            if (!em.contains(chambre)){
                                chambre = em.merge(chambre);
                            }else{
                                return false;
                            }
                            
                        try {
                            em.remove(chambre);
                            return true;
                        } catch (Exception e) {
                            return false;
                        }
                }
     
//----------------------------------------------------------------------
 
       public MhChambre singleSelect(String numCh){
           String req = "SELECT m FROM MhChambre m WHERE m.numCh = :numCh";
               Query q = em.createQuery(req);
              
               q.setParameter("numCh", numCh);
               try {
               return (MhChambre) q.getSingleResult();
           } catch (Exception e) {
               return null;
           }
               
       }
       
       @EJB
       MhHotelBean beanHtl;
       public List<MhChambre> chambreVisibleConv(){
           MhHotel codeH = beanHtl.htl();
           String req = "select u from MhChambre u where u.codeH = :codeH and u.visible = :visible";
           Query q = em.createQuery(req);
           q.setParameter("codeH", codeH);
            q.setParameter("visible", true);
           return q.getResultList();
       }
      
       
       public List<MhChambre> chambreDejaConv(MhConventionAgc convMhCh){
           List<MhConventionAgcChambre> conAgcCh = new ArrayList<MhConventionAgcChambre>();
            String req = "select u from MhConventionAgcChambre u where u.codeConventionAgc = :convMhCh";
                Query q = em.createQuery(req);
                            q.setParameter("convMhCh", convMhCh);
                    List<MhChambre> ch = new ArrayList<MhChambre>();
                        conAgcCh = q.getResultList();
                            for(MhConventionAgcChambre obj:conAgcCh){
                                ch.add(obj.getNumCh());
                            }
           return ch;
       }
       
       public List<MhChambre> chambreExitConv(){
           List<MhConventionAgcChambre> conAgcCh = new ArrayList<MhConventionAgcChambre>();
            String req = "select u from MhConventionAgcChambre u ";
                Query q = em.createQuery(req);
                    List<MhChambre> ch = new ArrayList<MhChambre>();
                        conAgcCh = q.getResultList();
                            for(MhConventionAgcChambre obj:conAgcCh){
                                ch.add(obj.getNumCh());
                            }
           return ch;
       }
       
       public boolean isChambreSousConv(MhChambre chambre){
           List<MhChambre> ch = chambreExitConv();
           
           if(ch.contains(chambre)) return true;
           return false;
       }
       
       public List<MhChambre> chambreOcpPeriode(Date dateD, Date dateF){
          MhHotel code_h = beanHtl.htl();
         Query q = em.createQuery("SELECT u FROM MhReservation u where ( u.dateA < :dtD and u.dateD > :dtD ) OR ( u.dateA <= :dtA and u.dateD > :dtA ) and u.codeH = :code_h ",MhChambre.class);
            
          q.setParameter("code_h", code_h);
          q.setParameter("dtA", dateD);
          q.setParameter("dtD", dateF);
         
          List<MhReservation> rsvList = q.getResultList();
          List<MhChambre> ch = new ArrayList<MhChambre>();
          for(MhReservation rsv: rsvList){
              ch.add(rsv.getNumCh());
          }
          return ch;
       }
        
       
       public List<MhChambre> listChToUseConvList (Date dateD, Date dateF, MhConventionAgc convMhCh){
            List<MhChambre> ch = new ArrayList<MhChambre>();
            List<MhChambre> ch1 = chambreVisibleConv();
            List<MhChambre> ch2 = chambreExitConv();
            List<MhChambre> ch3 = chambreOcpPeriode(dateD, dateF);
            
            listTraitement LST = new listTraitement();
            ch = LST.listMinsList(ch1, ch2);
            ch = LST.listMinsList(ch, ch3);
            
            return ch;
       }
       
       
        
       
}
