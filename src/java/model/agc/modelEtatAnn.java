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
public class modelEtatAnn {
    
   private String design;
   private Date du;
   private Date au;
   private String codeR;
   private float prix;
   private float retenu;

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getRetenu() {
        return retenu;
    }

    public void setRetenu(float retenu) {
        this.retenu = retenu;
    }
    
   
   
}
