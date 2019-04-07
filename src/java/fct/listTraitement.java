/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fct;

import java.util.ArrayList;
import java.util.List;
import model.MhChambre;


public class listTraitement {
  
    public List listMinsList(List lstGrand, List lstPetit){
        if(lstGrand == null) return lstPetit;
        if(lstPetit == null) return lstGrand;
        List lstRst = new ArrayList();
          int x = lstGrand.size();
          int y = lstPetit.size();
          
          if(x >= y){
              lstGrand.removeAll(lstPetit);
              return lstGrand;
          }else{
              return null;
          }
    }
    
    
    public List listCompatibleItems(List lst1, List lst2){
       if(lst1 == null || lst2 == null) return null;
          List lstRst = new ArrayList();
       for(Object obj:lst1){
           if(lst2.contains(obj)) lstRst.add(obj);
       }
       return lstRst;
        
    }
    
    public int listSize(List list){
        return list.size();
    }
    
    public Object getItem(List list, int position){
           if(position > listSize(list) || position < 0)return null;
        else
           return list.get(position);
    }
    
    public boolean equalList(List lst1, List lst2){
        return lst1.equals(lst2);
    }
    
    public boolean itemExist(List lst, MhChambre item){
        return lst.contains(item);
    }

 
    
}
