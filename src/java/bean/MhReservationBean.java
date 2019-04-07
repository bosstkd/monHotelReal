
package bean;

import controller.Util;
import fct.dt;
import fct.nbr;
import fct.numRote;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhCltFct;
import model.MhDemandeR;
import model.MhHotel;
import model.MhReservation;
import model.MhRsvvueglobal;
import model.MhTabRsvWOFct;

@Stateless
public class MhReservationBean {
    

    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;

    
    public List<MhRsvvueglobal> findAll() {
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        Query q = em.createQuery("SELECT m FROM MhRsvvueglobal m  WHERE m.codeH = :codeH order by m.numFct DESC", MhRsvvueglobal.class);
               q.setParameter("codeH", code_h);
        return q.getResultList();
    }
    
    public List<MhRsvvueglobal> findAllNative() {
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        Query q = em.createNativeQuery("SELECT m.* FROM Mh_Rsvvueglobal m  WHERE m.code_h like '"+code_h+"' order by m.dates DESC", MhRsvvueglobal.class);
        return q.getResultList();
    }
    
    public List<MhRsvvueglobal> findByNumFct(String numFct) {
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            String req = "SELECT m.* FROM Mh_Rsvvueglobal m WHERE m.numFct like '"+numFct+"' and m.code_h like '"+code_h+"' order by m.date_r desc";

            return em.createNativeQuery(req, MhRsvvueglobal.class)
            .getResultList();
     }
    
    
      public List<MhReservation> listNonFct(){
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       return em.createNativeQuery("SELECT b.* FROM mh_reservation b where b.code_h like '"+code_h+"' and b.code_r not in (select code_r from mh_RsvVueGlobal where code_h like '"+code_h+"') order by date_r desc", MhReservation.class).getResultList();
    }
    
     public List<MhReservation> listMhReservation(){
       return em.createQuery("SELECT b FROM MhReservation b").getResultList();
    }
     
     public MhReservation getById(Integer id){
        return em.find(MhReservation.class, id);
    }
     
     public MhReservation findByCodeR(String codeR){
         String req = "Select m from MhReservation m where m.codeR = :codeR";
         Query q = em.createQuery(req,MhReservation.class);
         q.setParameter("codeR", codeR);
         try {
              return (MhReservation) q.getSingleResult();
         } catch (Exception e) {
              return null;
         }
        
    }
     
     
     public MhReservation findByCodeRNative(String codeR){
         String req = "Select m.* from mh_reservation m where m.code_r like '"+codeR+"' ";
         System.out.println(req);
         Query q = em.createNativeQuery(req,MhReservation.class);         
         return (MhReservation) q.getSingleResult();
    }
     
     
@EJB
MhChambreBean beanCh;

@EJB
MhCltSChBean beanCSCh;

@EJB
MhHotelBean beanH;
     
//----------------------Une selection----------------------------------------
    

public Object singleSelectReservation(String code_r, String find){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select "+find+" from mh_reservation where code_r like '"+code_r+"' and code_h like '"+code_h+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }  
  
 
    
    public MhReservation singleSelectJPQL(String code_r){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        MhHotel htl = beanH.findByCodeH(code_h);
        String req = "select r from MhReservation r where r.codeR = :code_r and r.codeH = :code_h";
        Query q = em.createQuery(req);
        q.setParameter("code_h", htl);
        q.setParameter("code_r", code_r);
        try {
             return (MhReservation) q.getSingleResult();
         } catch (Exception e) {
             return  null;
         }
    }  
    
//----------------------test et vérification---------------------------------   
    public boolean isReservationExist(String code_r){
        return (singleSelectJPQL(code_r)!=null);
    }
    
   public boolean isChReserved(String num_ch, Date dt_a, Date dt_d){
        dt sdt = new dt();
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "select date_a, date_d from mh_reservation where code_h like '"+code_h+"' and num_ch like '"+num_ch+"' ";

        Query query = em.createNativeQuery(req);
        List<Object[]> listResult = query.getResultList();
        System.out.println(listResult);
         for (Object[] value : listResult)
    	 {
            if(sdt.dateIntoPeriode((Date)value[0],(Date) value[1], dt_a, dt_d)) return true;
    	 } 
       return false;
   } 
   
    public boolean isChReservedEnt(String num_ch, Date dt_a, Date dt_d){
          dt sdt = new dt();
        String code_h = beanConv.convention().getCodeH().getCodeH();
        
        String req = "select date_a, date_d from mh_reservation where code_h like '"+code_h+"' and num_ch like '"+code_h+"_"+num_ch+"' ";

        Query query = em.createNativeQuery(req);
        List<Object[]> listResult = query.getResultList();
        System.out.println(listResult);
         for (Object[] value : listResult)
    	 {
            if(sdt.dateIntoPeriode((Date)value[0],(Date) value[1], dt_a, dt_d)) return true;
    	 } 
       return false;
   } 
   
   
   public boolean isClientInCh(String npid, Date dt_a){
         dt sdt = new dt();
       HttpSession hs = Util.getSession();
       String code_h = (String) hs.getAttribute("code_h");
       
       String req = "select date_a, date_d, periode_ouverte from rsv_clt_sch_vue where  npid like '"+npid+"' and code_h like '"+code_h+"' ";
       Query query = em.createNativeQuery(req);
          List<Object[]> listDate = query.getResultList();
          
         for (Object[] dt_value : listDate){
             if((sdt.isInPeriode((Date)dt_value[0], (Date)dt_value[1], dt_a)) || ((Boolean) dt_value[2]) )return true;
         }
       return false;
   }
   @EJB
   MhConventionBean beanConv;
      public boolean isClientInChEnt(String npid, Date dt_a){
       String code_h = beanConv.convention().getCodeH().getCodeH();
         dt sdt = new dt();
       String req = "select date_a, date_d, periode_ouverte from rsv_clt_sch_vue where  npid like '"+npid+"' and code_h like '"+code_h+"' ";
       Query query = em.createNativeQuery(req);
          List<Object[]> listDate = query.getResultList();
          
         for (Object[] dt_value : listDate){
             if((sdt.isInPeriode((Date)dt_value[0], (Date)dt_value[1], dt_a)) || ((Boolean) dt_value[2]) )return true;
         }
       return false;
   }
//----------------------------------------------------------------------    
 @EJB      
 MhDemandeRBean beanDem;     
      
 @EJB
 MhCltSChBean beanCSCH;  
//-----------a utilisé pour insertion d'information ------------------------  
    public void insert(Object[] rsv, List<String> NPID){
            HttpSession hs = Util.getSession();
            String code_h = (String) hs.getAttribute("code_h");
            nbr NBR = new nbr();
              dt sdt = new dt();
            //----prix de reservation-----
            double total = NBR.toDouble(rsv[12].toString());
            
            boolean etat_p = false;
            if(NBR.toDouble(rsv[9]+"") == total)etat_p = true;
            
            //-----------------------------
            
          // String [] tab = new String[]{"taxe_sejour", "pension_c","prc_demi_pension"}; 
           MhHotel obj = beanH.getMultipleSelection();
                       
           String codeDem = null;
           if(rsv[16] != null){
               codeDem = "'"+rsv[16]+"'";
           }
           
            String req = "insert into mh_reservation(code_h, num_ch, code_c, code_a, code_u, date_a, date_d, periode_ouverte, nb_nuitee, pension, prix_u, versement, etat_p, facturer, code_r, date_r, taxe_sj, pension_c, prc_pension, numCmd, code_dm)"
                                    + "values('"+code_h+"', '"+code_h+"_"+rsv[0]+"', "+rsv[1]+", "+rsv[2]+", "+rsv[3]+", '"+sdt.form_Insr((Date)rsv[4])+"', '"+sdt.form_Insr((Date)rsv[5])+"', "+Boolean.valueOf(rsv[6].toString())+", "+sdt.nuitee((Date)rsv[4], (Date)rsv[5])+",'"+rsv[7]+"', "+rsv[8]+", "+rsv[9]+","+etat_p+", "+rsv[10]+", '"+rsv[11]+"', NOW(), "+obj.getTaxeSejour()+", "+obj.getPensionC()+", "+obj.getPrcDemiPension()+", '"+rsv[14]+"', "+codeDem+" )";
         
            em.createNativeQuery(req).executeUpdate();
           
           //------ insertion de la liste ------
           insertList(NPID, rsv[11]+"");
           try {
             String rst = rsv[1]+"";
                if(!rst.equals("''")){
                     for(String npd0:NPID){
                         rst = rst.replaceAll("'", "");
                            beanCSCH.updateCodeC(rst, beanCSCH.getClientByNpid(npd0));
                        }
                }
               
        } catch (Exception e) { 
            System.err.println(e);
        }
           
            if(rsv[16] != null){
                  try {
                        MhDemandeR dem = beanDem.getDemandeRByCodeDm(rsv[16]+"");
                        beanDem.updateConf(dem);
                   } catch (Exception e) {
                       System.err.println(e);
                   } 
               }
           
         
           
           //------ Création de facture si OK ------
           if((boolean)rsv[10]){
               
               String numFct = numFct(code_h);
               String Design = designationGen(code_h, rsv[0]+"", rsv[7]+"", NPID, rsv[14]+"", (int)sdt.nuitee((Date)rsv[4], (Date)rsv[5]));
               req = "insert into mh_rsv_fct(numFct, code_r, designation, type_paiement, dates, prcReduction) values ('"+numFct+"','"+rsv[11]+"', '"+Design+"', '"+rsv[13]+"', NOW(), "+rsv[15]+")";
               
               em.createNativeQuery(req).executeUpdate();
           }
           
          
           
    }   
    
    
    public void updatePrcReduc(int prcReduction, String numFct){
        String req = "update mh_rsv_fct set prcReduction = "+prcReduction+" where numFct like '"+numFct+"' ";
        Query q = em.createNativeQuery(req);
        q.executeUpdate();
    }
    
    
    public void updateNative(String code_r, String attribut, String toUpdate){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        String req = "update mh_reservation set "+attribut+" = "+toUpdate+" where code_r like '"+code_r+"' and code_h like '"+code_h+"' ";
        em.createNativeQuery(req).executeUpdate();
    }
    
    public boolean updateJPQL(MhReservation rsvs, String att, Object newValue){
        MhReservation rsv = em.find(MhReservation.class, rsvs.getCodeR()); 
        if(att.equals("DateD")){
            rsv.setDateD((Date)newValue);
            return true;
        }
        
        if(att.equals("PeriodeOuv")){
            rsv.setPeriodeOuverte((boolean)newValue);
            return true;
        }
        
        if(att.equals("nbNuitee")){
            rsv.setNbNuitee((int) newValue);
            return true;
        }
        
         if(att.equals("codeC")){
            rsv.setCodeC((MhCltFct) newValue);
            
            
            return true;
        }
        
        return false;
    }
    
    @EJB
    MhTabRsvWOFctBean bn;
    
    public boolean update(MhReservation rsvs, float somme,String action){
        MhReservation rsv = em.find(MhReservation.class, rsvs.getCodeR());
        MhTabRsvWOFct rsvwo = bn.selectByCodeR(rsv.getCodeR());
        float db = rsv.getVersement();
        if(action.equals("add")){
            db = db + somme;
        }else{
            db = db - somme;
        }
        try {
             rsv.setVersement(db);
             rsvwo.setVersement(db);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    
    public void delete(String codeR){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
        
        try {
             MhReservation rsvs = findByCodeR(codeR);
             if(rsvs.getCodeDm()!=null)
             beanDem.updateAnnule(rsvs.getCodeDm());
        } catch (Exception e) {
            System.err.println(e);
        }
        
        String req = "delete from mh_rsv_fct where code_r like '"+codeR+"' ";
         em.createNativeQuery(req).executeUpdate();
         
         req = "delete from rsv_clt_sch where code_r like '"+codeR+"' ";
         em.createNativeQuery(req).executeUpdate();         
         
         req = "delete from mh_reservation where code_r like '"+codeR+"' and code_h like '"+code_h+"' ";
         em.createNativeQuery(req).executeUpdate();
         
    }
    
    
    public String numFct(String code_h){
            int lgrCd = code_h.length()+1;
             numRote NROTE = new numRote();
              dt sdt = new dt();
            String req = "select numFct from mh_rsv_fct where numFct like '"+code_h+"_%"+sdt.Annee(new Date())+"' ";
            Query q = em.createNativeQuery(req);
            List<String> lst = q.getResultList();
            Vector vct = new Vector();
            
            lst.forEach((lst1) -> {
               boolean bl = false;
                for(int i = 0; i<vct.size(); i++){
                    if(vct.elementAt(i).equals(lst1.substring(lgrCd, lst1.length()))){
                        bl = true;
                        break;
                    }
                }
                if(!bl) vct.addElement(lst1.substring(lgrCd, lst1.length()));
            });
            return code_h+"_"+NROTE.factureNum(vct);
        }
    
 
    
    public String designationGen(String code_h,String numCh, String pension, List<String> NPID, String cmd, int nbNuitee){
       
        String type_ch = beanCh.singleSelect(code_h+"_"+numCh).getTypeCh();
        String listNom = "";
        for(String NPID1:NPID){
            listNom = listNom+", "+beanCSCh.singleSelectClientA("nom_prenom", NPID1);
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
 
    
//----------------------------------------------------------    
    public void insertList(List<String> NPID, String codeR){
        String req;
        for(String NPID1 : NPID){
            req = "insert into rsv_clt_sch (code_r, npid) values ('"+codeR+"', '"+NPID1+"')";
          
             em.createNativeQuery(req).executeUpdate();
        }
    }
    
    
    public List<String> CodeCltSCh (String code_r){
      
        String req = "select npid from rsv_clt_sch where code_r like '"+code_r+"' ";
        try {
             return  em.createNativeQuery(req).getResultList();
         } catch (Exception e) {
             return  null;
         }
    }


}
