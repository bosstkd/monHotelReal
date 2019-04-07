/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import controller.Util;
import fct.dt;
import fct.nbr;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhCaisse;

@Stateless
public class MhCaisseBean {
    
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
    
    public List<MhCaisse> listCaisse(){
         HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         String req = "SELECT u.* FROM mh_caisse u where u.code_h = ?1 order by dates desc";
         Query q = em.createNativeQuery(req,MhCaisse.class);
         q.setParameter(1, code_h);
         
        return q.getResultList();
    }
    
    
    public List<Object> select(Date dt_du, Date dt_au, String toGet){
        dt sdt = new dt();
        HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h");
         String req = "SELECT "+toGet+" FROM mh_caisse u where u.code_h = ?1 and ( u.dates between '"+sdt.form_Insr(dt_du)+"' and '"+sdt.form_Insr(dt_au)+"') ";
         Query q = em.createNativeQuery(req,MhCaisse.class);
         q.setParameter(1, code_h);
        return q.getResultList();
    }
     
    public double selectSomme(Date dt_du, Date dt_au){
        dt sdt = new dt();
        nbr NBR = new nbr();
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
          double nbrDbl = 0;
          
         String req = "SELECT SUM(u.somme) AS TotalItemsOrdered FROM mh_caisse u where u.code_h like '"+code_h+"' and ( u.dates between '"+sdt.form_Insr(dt_du)+"' and '"+sdt.form_Insr(dt_au)+"') ";
            
             Query q = em.createNativeQuery(req);
                   Object results = (Object) q.getSingleResult();
                   String str =  results+""; 
                   if(str!=null){
                       try {
                          return  nbrDbl = NBR.toDouble(str);
                       } catch (Exception e) {
                          return  nbrDbl = 0;
                       }
                   }
        return 0;
          }
    
     public double selectSommePNG(Date dt_du, Date dt_au, String etatPN){
         dt sdt = new dt();
        nbr NBR = new nbr();
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
         
          String signe = ">=";
          if(etatPN.equals("N"))signe = "<";
          
         String req = "SELECT SUM(u.somme) AS TotalItemsOrdered FROM mh_caisse u where u.code_h like '"+code_h+"' and ( u.dates between '"+sdt.form_Insr(dt_du)+"' and '"+sdt.form_Insr(dt_au)+"') and u.somme "+signe+" 0 ";
            
             Query q = em.createNativeQuery(req);
                   Object results = (Object) q.getSingleResult();
                   String str =  results+""; 
                   if(str!=null){
                       try {
                          return  NBR.toDouble(str);
                       } catch (Exception e) {
                          return 0;
                       }
                   }
        return 0;
          }
    
    
    
     public double selectSommePN(Date jour, String etatPN){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");
         
          String signe = ">=";
          if(etatPN.equals("N"))signe = "<";
          dt sdt = new dt();
          nbr NBR = new nbr();
         String req = "SELECT SUM(u.somme) AS TotalItemsOrdered FROM mh_caisse u where u.code_h like '"+code_h+"' and (u.dates between '"+sdt.form_Insr(jour)+"' and '"+sdt.form_Insr(new Date(sdt.addDay(jour, 1)))+"') and u.somme "+signe+" 0 ";
             Query q = em.createNativeQuery(req);
                   Object results = (Object) q.getSingleResult();
                   String str =  results+""; 
                   if(str!=null){
                       try {
                          return  NBR.toDouble(str);
                       } catch (Exception e) {
                          return 0;
                       }
                   }
        return 0;
          }
    
     public double selectMaxMin(Date dt_du, Date dt_au, String max){
          dt sdt = new dt();
          nbr NBR = new nbr();
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h");

         String req = "SELECT "+max+"(u.somme) AS TotalItemsOrdered FROM mh_caisse u where u.code_h like '"+code_h+"' and ( u.dates between '"+sdt.form_Insr(dt_du)+"' and '"+sdt.form_Insr(dt_au)+"') ";
            
             Query q = em.createNativeQuery(req);
                   Object results = (Object) q.getSingleResult();
                   String str =  results+""; 
                   if(str!=null){
                       try {
                          return   NBR.toDouble(str);
                       } catch (Exception e) {
                          return 0;
                       }
                   }
        return 0;
          }
    
    
      //-----------a utilisé pour insertion d'information ------------------------  
    public void insert(Object[] caisse){
        HttpSession hs = Util.getSession();
         String code_h = (String) hs.getAttribute("code_h"); 
         
            String req = "insert into mh_caisse(code_h, code_u, motif, somme, code_r, dates)"
                                    + "values('"+code_h+"', '"+caisse[0]+"', '"+caisse[1]+"', "+caisse[2]+",null, NOW())";
            em.createNativeQuery(req).executeUpdate();
    }
    
   //------------a utilisé pour suppriession d'information ------------------------      
     public void remove(Date dtR, String motif, double somme){
        HttpSession hs = Util.getSession();
        String code_h = (String) hs.getAttribute("code_h"); 
        Query query = em.createNativeQuery("DELETE FROM mh_caisse WHERE dates = "+dtR+" and motif = '"+motif+"' and somme = "+somme+" and code_h = '"+code_h+"' ");
        query.executeUpdate();
   }
    
    
}
