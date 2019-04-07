package statistique.bean;

import statistique.bean.comportement.*;

public abstract class beanForTaxe {


   protected calcul clc = new espece();
   
   
   public beanForTaxe(){}
   public beanForTaxe(calcul clc){this.clc = clc;} 
   
    
     //Méthode de calcul de facture
     public double total(String typePaiement, float charge, float prixCh, int nbNuitee, int nbPersonne, float pension,int prcPension, String typePension,boolean clientExoTva, float prixTx, int prcReduction, double valTva){
	    //On utilise les objets de calcul de façon polymorphe
            if(typePaiement.equals("Espece"))clc = new espece();
                                            else
                                             clc = new notEspece();
           return clc.calculer(typePaiement, charge,  prixCh,  nbNuitee,  nbPersonne,  pension, prcPension, typePension, clientExoTva, prixTx,  prcReduction, valTva);
     }
     
      public double taxe(String typePaiement, float charge, float prixCh, int nbNuitee, int nbPersonne, float pension,int prcPension, String typePension,boolean clientExoTva, float prixTx, int prcReduction, double valTva){
	    //On utilise les objets de calcul de façon polymorphe
	    if(typePaiement.equals("Espece"))clc = new espece();
                                            else
                                             clc = new notEspece();
            return clc.taxe(typePaiement, charge,  prixCh,  nbNuitee,  nbPersonne,  pension, prcPension, typePension, clientExoTva, prixTx, prcReduction, valTva);
     }
 

    public calcul getClc() {
        return clc;
    }

    public void setClc(calcul clc) {
        this.clc = clc;
    }
  
   
}
