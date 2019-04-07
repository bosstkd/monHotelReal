package statistique.bean.comportement;
public interface calcul {
    public double calculer(String TypePaiement, double charge, double prixCh, int nbNuitee, int nbPersonne, double pension,int prcPension, String typePension,boolean clientExoTva, double prixTx, int prcReduction, double valTva);
    public double taxe(String TypePaiement, double charge, double prixCh, int nbNuitee, int nbPersonne, double pension,int prcPension, String typePension,boolean clientExoTva, double prixTx, int prcReduction, double valTva);
}
