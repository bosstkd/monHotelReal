/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mh_charge_supp", catalog = "monHotel", schema = "")
public class MhChargeSupp implements Serializable{
    @Id
    @Size(min = 1, max = 300)
    @Column(name = "code_r", length = 300)
    private String code_r;
 
    @Column(name = "charge")
    private String charge;
    
    @Column(name = "code_h")
    private String code_h;
    
    @Id
    @Column(name = "dt_chrg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtChrg;
    
    @Column(name = "prix_ch")
    private float prix_ch;

    public String getCode_r() {
        return code_r;
    }

    public void setCode_r(String code_r) {
        this.code_r = code_r;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public float getPrix_ch() {
        return prix_ch;
    }

    public void setPrix_ch(float prix_ch) {
        this.prix_ch = prix_ch;
    }

    public String getCode_h() {
        return code_h;
    }

    public void setCode_h(String code_h) {
        this.code_h = code_h;
    }

    public Date getDtChrg() {
        return dtChrg;
    }

    public void setDtChrg(Date dtChrg) {
        this.dtChrg = dtChrg;
    }
    
    
    
}
