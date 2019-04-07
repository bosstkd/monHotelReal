/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import controller.Util;
import fct.nbr;
import fct.str;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import model.MhCltFct;
import model.MhCltSCh;
import model.MhConvention;


@Stateless
public class MhCltSChBean {
    @PersistenceContext(unitName="monHotelPU")
    private EntityManager em;
    
     public List<MhCltSCh> CltSChlist(){
        Query q = em.createQuery("SELECT u FROM MhCltSCh u "
                + "ORDER BY u.nomPrenom ",MhCltSCh.class);
       return q.getResultList();
    }
    
      public List<MhCltSCh> CltSChlistEnt(){
        Query q = em.createQuery("SELECT u FROM MhCltSCh u where u.codeC = :codeC "
                + "ORDER BY u.nomPrenom ",MhCltSCh.class);
        q.setParameter("codeC", convention().getCodeC());
       return q.getResultList();
    }
      
      @EJB
      MhConventionBean beanConv;
    
    
    public MhConvention convention(){
        HttpSession hs = Util.getSession();
        String codeConv = (String) hs.getAttribute("codeConvention");
        return beanConv.getByCodeConvention(codeConv);
    }
     
     public MhCltSCh getClientByNpid(String npid){
          Query q = em.createQuery("SELECT u FROM MhCltSCh u WHERE u.npid = :npid ",MhCltSCh.class);
          q.setParameter("npid", npid);
          try {
             return (MhCltSCh) q.getSingleResult();
         } catch (Exception e) {
             return null;
         }
       
     }
     
    public int nbClientOnRsv(String code_r){
          nbr NBR = new nbr();
        String req = "select COUNT(npid) AS nbrLigne from rsv_clt_sch where code_r like '"+code_r+"' ";
        
          Query q = em.createNativeQuery(req);
                   Object results = (Object) q.getSingleResult();
                   String str =  results+""; 
                   if(str!=null){
                       try {
                          return   NBR.toInt(str);
                       } catch (Exception e) {
                          return   0;
                       }
                   }
             return  0;
         
    }
    
    
    public MhCltSCh getById(Integer id){
        return em.find(MhCltSCh.class, id);
    }
   
     
     public boolean insert(MhCltSCh client){
         try {
              em.persist(client);
         } catch (Exception e) {
             return false;
         }
             return true;
    } 
     
     
  
       public boolean UPDATE(MhCltSCh client){
           str STR = new str();
            try {
               MhCltSCh clt = em.find(MhCltSCh.class, client.getNpid());
                clt.setNomPrenom(STR.nameForm(client.getNomPrenom()));
                clt.setDateN(client.getDateN());
                clt.setLieuN(client.getLieuN());
                clt.setNationalite(client.getNationalite());
                clt.setDateP(client.getDateP());
                clt.setLienP(client.getLienP());
                clt.setAdresse(client.getAdresse());
                clt.setNum_tel(client.getNum_tel());
                
                try { clt.setMail(client.getMail());  } catch (Exception e) {  }
                try { clt.setListeNoir(client.getListeNoir());  } catch (Exception e) {  }
                try { clt.setRaisonLn(client.getRaisonLn()); } catch (Exception e) { }
                
           } catch (Exception e) {
                return false;
           }
             return true;
      }
       
       
        public boolean UPDATE_ENT(String codeC, MhCltSCh client){
            str STR = new str();
            try {
               MhCltSCh clt = em.find(MhCltSCh.class, client.getNpid());
                clt.setNomPrenom(STR.nameForm(client.getNomPrenom()));
                clt.setDateN(client.getDateN());
                clt.setLieuN(client.getLieuN());
                clt.setNationalite(client.getNationalite());
                clt.setDateP(client.getDateP());
                clt.setLienP(client.getLienP());
                clt.setAdresse(client.getAdresse());
                clt.setNum_tel(client.getNum_tel());
                clt.setListeNoir(client.getListeNoir());
                clt.setRaisonLn(client.getRaisonLn());
                
                 try {
                    MhCltFct ent = beanEnt.singleSelectEntJPQL(codeC);
                     clt.setCodeC(ent);
                } catch (Exception e) { }
                
           } catch (Exception e) {
                return false;
           }
             return true;
      }
        
        public boolean elimineDeEnt(String npid){
             MhCltSCh clt = em.find(MhCltSCh.class, npid);
            try {
                     clt.setCodeC(null);
                } catch (Exception e) { 
                     return false;
                }
            return true;
        }
       
       @EJB
       MhEntrepriseBean beanEnt;
       public boolean updateCodeC(String codeC, MhCltSCh client){
            try {
               MhCltSCh clt = em.find(MhCltSCh.class, client.getNpid());
               MhCltFct ent = beanEnt.singleSelectEntJPQL(codeC);
                clt.setCodeC(ent);
           } catch (Exception e) {
                return false;
           }
           return true;
       }
       
       public boolean updateMail(MhCltSCh client, String mail){
            try {
                MhCltSCh clt = em.find(MhCltSCh.class, client.getNpid());
                clt.setMail(mail);
           } catch (Exception e) {
               return false;
           }
           
           return true;
       }
       
       public boolean updatePsw(MhCltSCh client, String psw){
            try {
                MhCltSCh clt = em.find(MhCltSCh.class, client.getNpid());
                clt.setPsw(psw);
           } catch (Exception e) {
               return false;
           }
           return true;
       }
       
      /*
      public Object singleSelectClient( String find, String pId, String nPId){

        String req = "select "+find+" from mh_clt_s_ch where p_id like '"+pId+"' and n_p_id like '"+nPId+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult().toString();
         } catch (Exception e) {
             return  "";
         }
    }
      */
       
       public MhCltSCh singleSelect(String pId, String nPId){
           String req = "select u from MhCltSCh u where u.pieceId = :pId and u.npid = :nPId ";
           Query q = em.createQuery(req);
           q.setParameter("pId", pId);
           q.setParameter("nPId", nPId);
           try {
               return (MhCltSCh) q.getSingleResult();
           } catch (Exception e) {
               return null;
           }
       }
       
       public Object singleSelectClientA( String find, String nPId){

        String req = "select "+find+" from mh_clt_s_ch where n_p_id like '"+nPId+"' ";
        try {
             return  em.createNativeQuery(req).getSingleResult();
         } catch (Exception e) {
             return  null;
         }
    }
       
       public MhCltSCh clt(){
        HttpSession hs = Util.getSession();
        String npid = (String) hs.getAttribute("npid");
        return getClientByNpid(npid);
    }
    
}
