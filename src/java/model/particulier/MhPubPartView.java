/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.particulier;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "mh_pub_part_view", catalog = "jlvljuzg_monhotel")
@XmlRootElement
public class MhPubPartView  implements Serializable {

  @Id
  @Column(name = "code_pub_particulier")
  private String codePubParticulier;  
  @Column(name = "description")
  private String description;
  @Column(name = "adresse")
  private String adresse;
  @Column(name = "wilaya")
  private String wilaya;
  @Column(name = "type")
  private String type;
  @Column(name = "prix")
  private float prix;
   @Column(name = "date_du", nullable = false)
    @Temporal(TemporalType.DATE)
  private Date dateDu;
    @Column(name = "date_au", nullable = false)
    @Temporal(TemporalType.DATE)
  private Date dateAu;
     @Column(name = "date_pub", nullable = false)
    @Temporal(TemporalType.DATE)
  private Date datePub;
  @Column(name = "nom_prenom")
  private String nomPrenom;
  @Column(name = "num_tel")
  private String numTel;
  @Column(name = "mail")
  private String mail;

    public String getCodePubParticulier() {
        return codePubParticulier;
    }

    public void setCodePubParticulier(String codePubParticulier) {
        this.codePubParticulier = codePubParticulier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateDu() {
        return dateDu;
    }

    public void setDateDu(Date dateDu) {
        this.dateDu = dateDu;
    }

    public Date getDateAu() {
        return dateAu;
    }

    public void setDateAu(Date dateAu) {
        this.dateAu = dateAu;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }


    
    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nom_prenom) {
        this.nomPrenom = nom_prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String num_tel) {
        this.numTel = num_tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    
  
  
}
