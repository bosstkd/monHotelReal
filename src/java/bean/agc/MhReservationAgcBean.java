/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.agc;

import bean.MhChambreBean;
import bean.MhCltSChBean;
import bean.MhHotelBean;
import bean.MhReservationBean;
import fct.codification;
import fct.dt;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.MhHotel;
import model.MhReservation;
import model.agc.MhAgcRsvAnnuler;
import model.agc.MhAgence;

@Stateless
public class MhReservationAgcBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    @EJB
    mhAgenceBean beanAgc;
    
    public List<MhReservation> findForAgc(Date dtD, Date dtF) {
        MhAgence agc = beanAgc.agc();
        Query q = em.createQuery("SELECT m FROM MhReservation m  WHERE m.codeA = :codeA and m.dateR between :dtD and :dtF order by m.dateR desc", MhReservation.class);
               q.setParameter("codeA", agc);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
               
        return q.getResultList();
    }
    
     public List<MhReservation> findForAgcByHtl(MhHotel htl, Date dtD, Date dtF) {
        MhAgence agc = beanAgc.agc();
        Query q = em.createQuery("SELECT m FROM MhReservation m  WHERE m.codeA = :codeA and m.codeH = :codeH  and m.dateR between :dtD and :dtF order by m.dateR desc", MhReservation.class);
               q.setParameter("codeA", agc);
               q.setParameter("codeH", htl);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
    
     
     public List<MhAgcRsvAnnuler> findRsvAnnuler(Date dtD, Date dtF) {
        MhAgence agc = beanAgc.agc();
        Query q = em.createQuery("SELECT m FROM MhAgcRsvAnnuler m  WHERE m.codeA = :codeA and m.dates between :dtD and :dtF order by m.dates desc", MhAgcRsvAnnuler.class);
               q.setParameter("codeA", agc);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
    
     public List<MhAgcRsvAnnuler> findRsvAnnulerByHtl(MhHotel htl, Date dtD, Date dtF) {
        MhAgence agc = beanAgc.agc();
        Query q = em.createQuery("SELECT m FROM MhAgcRsvAnnuler m  WHERE m.codeA = :codeA and m.codeH = :codeH and m.dates between :dtD and :dtF order by m.dates desc", MhAgcRsvAnnuler.class);
               q.setParameter("codeA", agc);
               q.setParameter("codeH", htl);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
    
 //-----------Partie pour les hotels-------------//    
     
     public List<MhReservation> findForHtl(Date dtD, Date dtF) {
         MhHotel htl = beanHtl.htl();
        Query q = em.createQuery("SELECT m FROM MhReservation m  WHERE m.codeH = :codeH and m.dateR between :dtD and :dtF order by m.dateR desc", MhReservation.class);
               q.setParameter("codeH", htl);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
     
     @EJB
     MhHotelBean beanHtl;
     public List<MhReservation> findForHtlByAgc(MhAgence agc, Date dtD, Date dtF) {
        MhHotel htl = beanHtl.htl();
        Query q = em.createQuery("SELECT m FROM MhReservation m  WHERE m.codeA = :codeA and m.codeH = :codeH and m.dateR between :dtD and :dtF order by m.dateR desc", MhReservation.class);
               q.setParameter("codeA", agc);
               q.setParameter("codeH", htl);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
    
     
     public List<MhAgcRsvAnnuler> findRsvAnnulerHtl(Date dtD, Date dtF) {
        MhHotel htl = beanHtl.htl();
        Query q = em.createQuery("SELECT m FROM MhAgcRsvAnnuler m  WHERE m.codeH = :codeH and m.dates between :dtD and :dtF order by m.dates desc", MhAgcRsvAnnuler.class);
               q.setParameter("codeH", htl);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
    
     public List<MhAgcRsvAnnuler> findRsvAnnulerByAgc(MhAgence agc, Date dtD, Date dtF) {
           MhHotel htl = beanHtl.htl();
        Query q = em.createQuery("SELECT m FROM MhAgcRsvAnnuler m  WHERE m.codeA = :codeA and m.codeH = :codeH and m.dates between :dtD and :dtF order by m.dates desc", MhAgcRsvAnnuler.class);
               q.setParameter("codeA", agc);
               q.setParameter("codeH", htl);
               q.setParameter("dtD", dtD);
               q.setParameter("dtF", dtF);
        return q.getResultList();
    }
     
//----------------------------------------------//     
     
     
    @EJB
    MhConventionAgcBean beanConv;
    public boolean insertRsv(MhReservation rsv, String typeConv){
        try {
            em.persist(rsv);
            if(typeConv.equals("chLib")){
                 float x = rsv.getVersement();
                 beanConv.consomePrixEng(rsv.getCodeConvAgc().getCodeConvAgc(),(int) x);
            }
           
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    
    public boolean reserver(MhReservation rsv, List<String> NPID, String typeConv){
       
        if(insertRsv(rsv, typeConv)){
            insertList(NPID, rsv.getCodeR());
        }else{
            return false;
        }
        return true;
    }
    
    @EJB
    MhCltSChBean beanCltSCh;
    public void insertList(List<String> NPID, String codeR){
        String req;
        for(String NPID1 : NPID){
            beanCltSCh.elimineDeEnt(NPID1);
            req = "insert into rsv_clt_sch (code_r, npid) values ('"+codeR+"', '"+NPID1+"')";
            em.createNativeQuery(req).executeUpdate();
        }
    }
    
    
    public boolean isClientInCh(String npid, Date dt_a){
         dt sdt = new dt();

       String req = "select date_a, date_d, periode_ouverte from rsv_clt_sch_vue where  npid like '"+npid+"' ";
       
       Query query = em.createNativeQuery(req);
          List<Object[]> listDate = query.getResultList();
          
         for (Object[] dt_value : listDate){
             if((sdt.isInPeriode((Date)dt_value[0], (Date)dt_value[1], dt_a)) || ((Boolean) dt_value[2]) )return true;
         }
       return false;
   }
    
    

    public String listPersonneRsv(String codeR){
        String list = "";
         String req = "select npid from rsv_clt_sch_vue where  code_r like '"+codeR+"' ";
         Query q = em.createNativeQuery(req);
         List<String> lstStr = q.getResultList();
         for(String str:lstStr){
             list = list+", "+beanCltSCh.getClientByNpid(str).getNomPrenom();
         }
         list = list.substring(2, list.length());
        return list;
    }
    
   @EJB
   MhReservationBean beanRsv;   
   
   @EJB
   MhConventionAgcBean beanConvAgc; 
   
   public boolean deleteFromNpid(String codeR){
       String req = "delete from rsv_clt_sch where code_r like '"+codeR+"'";
       try {
             Query q = em.createNativeQuery(req);
             q.executeUpdate();
       } catch (Exception e) {
           return false;
       }
     
       return true;
   }
   
    public boolean delete(String codeR){
        List<String> NPID = listNpidByCodeR(codeR);
        if(deleteFromNpid(codeR)){
            MhReservation rsv = beanRsv.findByCodeR(codeR);
            float x = rsv.getVersement();
           if(beanConvAgc.reintegrePrixEng(rsv.getCodeConvAgc().getCodeConvAgc(), (int) x)){
               if(beanConvAgc.getByCodeConv(rsv.getCodeConvAgc().getCodeConvAgc()).getPrcQuotionAnnule()>0){
                   MhAgcRsvAnnuler an = new MhAgcRsvAnnuler();
                    an.setCodeA(rsv.getCodeA());
                    an.setCodeH(rsv.getCodeH());
                    an.setDateA(rsv.getDateA());
                    an.setDateD(rsv.getDateD());
                    an.setCodeR(codeR);
                    String design = designationGen(rsv.getCodeH().getCodeH(), rsv.getNumCh().getNumchApp(), rsv.getPension(), NPID, "", rsv.getNbNuitee());
                    an.setDesignation(design);
                    codification COD = new codification();
                    an.setCodeAnnule(COD.cd_prs(codeR));
                    float fl = rsv.getVersement();
                    an.setMontant((int) fl);
                    an.setRetenue(beanConvAgc.montant(rsv.getCodeConvAgc().getCodeConvAgc(), (int) x));
                    an.setPrcQuotion(beanConvAgc.getByCodeConv(rsv.getCodeConvAgc().getCodeConvAgc()).getPrcQuotionAnnule());
                    an.setDates(new Date());
                    em.persist(an);
               }
                    String req = "delete from MhReservation u where u.codeR like :codeR";
               try {
                    Query q = em.createQuery(req);
                    q.setParameter("codeR", codeR);
                    q.executeUpdate();
                    
                   
               } catch (Exception e) {
                   return false;
               }
           }else{
               return false;
           }            
        }else{
            return false;
        }
        return true;
    }
    
    
    private List<String> listNpidByCodeR(String codeR){
           String req = "select u.npid from rsv_clt_sch u where u.code_r like '"+codeR+"' ";
           System.out.println(req);
           Query q = em.createNativeQuery(req);
           List <String> str = q.getResultList();
           
           for(String st:str){
               System.out.println(st);
           }
           
           return str;
    }
     
    
    @EJB
    MhChambreBean beanCh;
   
    
    public String designationGen(String code_h,String numCh, String pension, List<String> NPID, String cmd, int nbNuitee){
       
        String type_ch = beanCh.singleSelect(code_h+"_"+numCh).getTypeCh();
        String listNom = "";
        for(String NPID1:NPID){
            listNom = listNom+", "+beanCltSCh.singleSelectClientA("nom_prenom", NPID1);
        }
        try {
            if(!cmd.equals("null"))
                cmd = "; commande N°: "+cmd;
               else if(cmd.length()<2){
                   cmd = "";
               }else{
                   cmd = "";
               }
                
        } catch (Exception e) {
             cmd = "";
        }
        
        String result = "";

        if(type_ch.contains("Salle")){
                     result = type_ch+" "+numCh+", pension "+pension+", "+nbNuitee+" Jours pour"+listNom+cmd+".";
        }else{
             result = "Chambre "+type_ch+" N°"+numCh+", pension "+pension+", "+nbNuitee+" nuitée pour"+listNom+cmd+".";
               if(pension.equals("Demi")){
                   result = "Chambre "+type_ch+" N°"+numCh+", "+pension+" pension, "+nbNuitee+" nuitée pour"+listNom+cmd+".";
               }else if(pension.equals("Non")){
                   result = "Chambre "+type_ch+" N°"+numCh+" "+nbNuitee+" nuitée pour"+listNom+cmd+".";
               }
        }
        
        
      return result;  
    }
    
}
