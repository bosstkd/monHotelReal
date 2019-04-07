/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistique.bean.comportement;

/**
 *
 * @author Amine
 */
public class espece  extends methode implements calcul{

   

    @Override
    public double calculer(String TypePaiement, double charge, double prixCh, int nbNuitee, int nbPersonne, double pension,int prcPension, String typePension,boolean clientExoTva, double prixTx, int prcReduction, double val) {
        double tht = THT(nbNuitee, prixCh, pension, prcPension, typePension, charge, nbPersonne, prcReduction);
        double taxeSj = TaxeSj(nbNuitee, prixTx, nbPersonne);
        double tva = tva_1(tht+taxeSj, val);
        double dTimbre = dTimbre(tht+taxeSj+tva);
        return TTC(tht, tva, taxeSj, dTimbre, "Espece", clientExoTva);              
    }
    
    @Override
    public double taxe(String TypePaiement, double charge, double prixCh, int nbNuitee, int nbPersonne, double pension,int prcPension, String typePension,boolean clientExoTva, double prixTx, int prcReduction, double valTva) {
                double tht = THT(nbNuitee, prixCh, pension, prcPension, typePension, charge, nbPersonne, prcReduction);
                double taxeSj = TaxeSj(nbNuitee, prixTx, nbPersonne);
                double rst = tht+taxeSj;
                double tva = tva_1(rst, valTva);
                if(clientExoTva)tva=0;
                rst = tht+taxeSj+tva;
                double dTimbre = dTimbre(rst);
                double tx = tva + taxeSj + dTimbre;
                return tx;
    }

}
