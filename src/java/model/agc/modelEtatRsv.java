/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agc;

import java.util.Date;

/**
 *
 * @author Amine
 */
public class modelEtatRsv {
    private String numCh;
    private String nom;
    private Date du;
    private Date au;
    private String codeR;
    private Date dateR;
    private float prix;

    public String getNumCh() {
        return numCh;
    }

    public void setNumCh(String numCh) {
        this.numCh = numCh;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDu() {
        return du;
    }

    public void setDu(Date du) {
        this.du = du;
    }

    public Date getAu() {
        return au;
    }

    public void setAu(Date au) {
        this.au = au;
    }

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }

    public Date getDateR() {
        return dateR;
    }

    public void setDateR(Date dateR) {
        this.dateR = dateR;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    
}
