/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fct;

import java.util.ArrayList;
import java.util.List;

public class searchModule {
    
    


   public List<String> getQueryList(String phrase){
      List<String> listReq = new ArrayList<String>();
       if(phrase.length() > 3){
           
         String req = "select u.* from mh_pub u where ";
         String composition = "";
         String cum = ""; 
         String glob = "";
         str STR = new str();
         List<String> getWords = STR.searchWordList(phrase);
         boolean x = false;
         
         boolean b1 = false;
         boolean b2 = false;
         boolean b3 = false;
         boolean b4 = false;
         for(String go : getWords){
             composition = "u.titre like '%"+go+"%' or u.detail like '%"+go+"%' or u.wilaya like '%"+go+"%' or u.commune like '%"+go+"%' or u.raison_social like '%"+go+"%' or u.mail like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " and "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length() > 0){
              glob = cum;
              listReq.add(req+cum+" order by u.date_pub");
              b1 = true;
         }
        
         
         List<Double> getDouble = STR.doubleList(phrase);
         x = false;
         for(Double go : getDouble){
             composition = "u.titre like '%"+go+"%' or u.detail like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " or "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length() > 0){
             glob = glob+" or "+cum;
             listReq.add(req+cum+" order by u.date_pub");
             b2 = true;
         }
        
         
         List<Integer> getInts = STR.intList(phrase);
         x = false;
         for(Integer go : getInts){
             composition = "u.titre like '%"+go+"%' or u.detail like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " or "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length() > 0){
             glob = glob+" or "+cum;
             listReq.add(req+cum+" order by u.date_pub");
             b3 = true;
         }
        
         
         
         List<String> getPhone = getPhoneNumbers(phrase);
         x = false;
         for(String go : getPhone){
             composition = "u.tel like '%"+go+"%' or u.fax like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " or "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length()>0){
             glob = glob+" or "+cum;
             listReq.add(req+cum);
             b4 = true;
         }
         
         if(b1 && b2 && b3 && b4)  listReq.add(req + glob);
       }
       
       
         
       return listReq;
   }

    public List<String> getPhoneNumbers(String phrase){
        str STR = new str();
        nbr NBR = new nbr();
        List<String> listPhoneNbr = new ArrayList<String>();
        List<Integer> intlst = STR.intList(phrase);
        String phone = "";
        String cum = "";
        for(int x : intlst){
            if((x+"").length() == 8){
                      phone = (x+"");
                      phone = "(0"+phone.substring(0,2)+") "+phone.substring(2,4)+"-"+phone.substring(4,6)+"-"+phone.substring(6,8);
                      listPhoneNbr.add(phone);
                    }
            
            if((x+"").length() == 9){
                      phone = (x+"");
                      phone = "(0"+phone.substring(0,3)+") "+phone.substring(3,6)+"-"+phone.substring(6,9);
                      listPhoneNbr.add(phone);
                    }
            cum = cum + x;  
            }
        
        int n = (int)(cum.length() / 8);
         List<Integer> intLst2 = new ArrayList<Integer>();
        for(int i = 0; i < n; i=i+8){
            intLst2.add(NBR.toInt(cum.substring(i,i+8)));
        }
        for(int x : intLst2){
            if((x+"").length() == 8){
                      phone = (x+"");
                      phone = "(0"+phone.substring(0,2)+")"+phone.substring(2,4)+"-"+phone.substring(4,6)+"-"+phone.substring(6,8);
                      listPhoneNbr.add(phone);
                    }
            
            if((x+"").length() == 9){
                      phone = (x+"");
                      phone = "(0"+phone.substring(0,3)+")"+phone.substring(3,6)+"-"+phone.substring(6,9);
                      listPhoneNbr.add(phone);
                    }
            }
        
        n = (int)(cum.length() / 9);
        intLst2 = new ArrayList<Integer>();
        for(int i = 0; i < n; i=i+9){
            intLst2.add(NBR.toInt(cum.substring(i,i+9)));
        }
        for(int x : intLst2){
            if((x+"").length() == 8){
                      phone = (x+"");
                      phone = "(0"+phone.substring(0,2)+")"+phone.substring(2,4)+"-"+phone.substring(4,6)+"-"+phone.substring(6,8);
                      listPhoneNbr.add(phone);
                    }
            
            if((x+"").length() == 9){
                      phone = (x+"");
                      phone = "(0"+phone.substring(0,3)+")"+phone.substring(3,6)+"-"+phone.substring(6,9);
                      listPhoneNbr.add(phone);
                    }
            }
        
        return listPhoneNbr;
    }
    
    
      public List<String> getQueryList2(String phrase){
      List<String> listReq = new ArrayList<String>();
       if(phrase.length() > 3){
           
         String req = "select u.* from mh_pub_part_view u where ";
         String composition = "";
         String cum = ""; 
         String glob = "";
         str STR = new str();
         List<String> getWords = STR.searchWordList(phrase);
         boolean x = false;
         
         boolean b1 = false;
         boolean b2 = false;
         boolean b3 = false;
         boolean b4 = false;
         for(String go : getWords){
             composition = "u.type like '%"+go+"%' or u.description like '%"+go+"%' or u.wilaya like '%"+go+"%' or u.adresse like '%"+go+"%' or u.nom_prenom like '%"+go+"%' or u.mail like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " and "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length() > 0){
              glob = cum;
              listReq.add(req+cum+" order by u.date_pub");
              b1 = true;
         }
        
         
         List<Double> getDouble = STR.doubleList(phrase);
         x = false;
         for(Double go : getDouble){
             composition = "u.prix <= "+go+" "; 
             
             if(x)
                 cum = cum + " or "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length() > 0){
             glob = glob+" or "+cum;
             listReq.add(req+cum+" order by u.date_pub");
             b2 = true;
         }
        
         
         List<Integer> getInts = STR.intList(phrase);
         x = false;
         for(Integer go : getInts){
             composition = "u.type like '%"+go+"%' or u.description like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " or "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length() > 0){
             glob = glob+" or "+cum;
             listReq.add(req+cum+" order by u.date_pub");
             b3 = true;
         }
        
         
         
         List<String> getPhone = getPhoneNumbers(phrase);
         x = false;
         for(String go : getPhone){
             composition = "u.num_tel like '%"+go+"%' "; 
             
             if(x)
                 cum = cum + " or "+ composition;
             else{
                 x = true;
                 cum = composition;
             } 
         }
         if(cum.length()>0){
             glob = glob+" or "+cum;
             listReq.add(req+cum);
             b4 = true;
         }
         
         if(b1 && b2 && b3 && b4)  listReq.add(req + glob);
       }
       
       
         
       return listReq;
   }
    
}
