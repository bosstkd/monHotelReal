/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agc;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "mh_pub", catalog = "monHotel")
public class MhPub  implements Serializable {

    
    @Column(name = "titre")
   
    private String titre;
    @Column(name = "detail")
    private String detail;
    @Column(name = "date_d", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateD;
    @Column(name = "date_f", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateF;
    @Column(name = "date_pub", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePub;
    @Column(name = "valide")
    private boolean valide;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "commune")
    private String commune;
    @Column(name = "wilaya")
    private String wilaya;
    @Column(name = "mail")
    private String mail;
    @Column(name = "tel")
    private String tel;
    @Column(name = "fax")
    private String fax;
     @Id
     @Column(name = "code_a")
    private String codeA;
      @Id
      @Column(name = "code_pub")
    private String codePub;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public Date getDateF() {
        return dateF;
    }

    public void setDateF(Date dateF) {
        this.dateF = dateF;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

 
    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCodeA() {
        return codeA;
    }

    public void setCodeA(String codeA) {
        this.codeA = codeA;
    }

    public String getCodePub() {
        return codePub;
    }

    public void setCodePub(String codePub) {
        this.codePub = codePub;
    }
    
    
}
