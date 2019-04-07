/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fct;

/**
 *
 * @author Amine
 */
public class calc {
    public  void main(String[] args) {
            System.out.println(ThtReduction(15000, 10));
    }
    
    public  double THT(int nbNuitee, double prix_ch, double pension, int prcPension, String typePension, double chargeSupp, int nbPersonne){        
        double pns = pension;
        if(typePension.equals("Demi")){
            pns = pension*prcPension/100;
        }else if(typePension.equals("Non")){
            pns = 0;
        }
        pns = pns*nbPersonne*nbNuitee;
        double prx = prix_ch*nbNuitee;
        prx = prx + pns + chargeSupp;
        return prx;
    }
    
    public  double ThtReduction(double tht, int prcReduction){
        double rst = tht*prcReduction;
        rst = rst/100;
        rst = tht - rst;
        return rst;
    }
    
    public  double TTC(double tht, double tva,double TaxeSj, double dTimbre, String typePaiement,boolean clientExoTva){
        double tot = tht + TaxeSj;
        if(! clientExoTva) tot = tot + tva;
        if(typePaiement.equals("Espece")) tot =  tot + dTimbre;
        return tot;
    }
    
    public  double TaxeSj(int nbNuitee,double taxeSj, int nbPersonne){
        double rst = nbNuitee*taxeSj;
        rst = rst * nbPersonne;
        return  rst;
    }
    
    public  double tva(double HT){
        double rst = HT*0.09d;
        return  rst;
    }
    
    public  double dTimbre(double Somme){
        if(Somme*0.01f > 2500) return 2500;
        double rst =  Somme*0.01d;
        return rst;
    }
    
}
