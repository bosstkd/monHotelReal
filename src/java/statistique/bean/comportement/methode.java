/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistique.bean.comportement;

import bean.MhHotelBean;
import javax.ejb.EJB;


/**
 *
 * @author Amine
 */
public class methode {
    
  
    
    public double THT(int nbNuitee, double prix_ch, double pension, int prcPension, String typePension, double chargeSupp, int nbPersonne, int prcReduction){
        double pns = pension;
        if(typePension.equals("Demi")){
            pns = pension*prcPension/100;
        }else if(typePension.equals("Non")){
            pns = 0;
        }
        pns = pns*nbPersonne*nbNuitee;
        double prx = prix_ch*nbNuitee;
        prx = prx + pns + chargeSupp;
        
        if(prcReduction == 0){
            return prx;
        }else{
            double rst = prx*prcReduction;
            rst = rst/100;
            rst = prx - rst;  
            return rst;
        }
    }
    
    public double TTC(double tht, double tva,double TaxeSj, double dTimbre, String typePaiement,boolean clientExoTva){
        double tot = tht + TaxeSj;
        if(! clientExoTva) tot = tot + tva;
        if(typePaiement.equals("Espece")) tot =  tot + dTimbre;
        return tot;    
    }
    
    public double TaxeSj(int nbNuitee,double taxeSj, int nbPersonne){
        double rst = nbNuitee*taxeSj;
        rst = rst * nbPersonne;
        return  rst;
    }
    
    
    @EJB
    MhHotelBean htlBean;
    
    public double tva(double HT){
       double tvaVal = htlBean.tvaValue();
       double rst = HT*tvaVal;
       return  rst;
    }
    
    public double tva_1(double HT, double val){
       double rst = HT*val;
       return  rst;
    }
    
    
    public double dTimbre(double Somme){
        if(Somme*0.01f > 2500) return 2500;
        double rst =  Somme*0.01d;
        return rst;
    }
    
}
