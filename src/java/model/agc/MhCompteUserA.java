/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agc;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "mh_compte_user_a", catalog = "jlvljuzg_monhotel")
@XmlRootElement
public class MhCompteUserA implements Serializable {


    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_u", nullable = false, length = 200)
    private String codeU;

    @Size(max = 20)
    @Column(name = "type_user", length = 20)
    private String typeUser;
    
      @Size(max = 200)
    @Column(name = "nom", length = 200)
    private String nom;
    
      @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;
    
    @Size(max = 100)
    @Column(name = "psw", length = 100)
    private String psw;
    
    @Column(name = "admin")
    private Boolean admin;
    
    @Size(max = 25)
    @Column(name = "tel", length = 25)
    private String tel;
    
    @JoinColumn(name = "code_a", referencedColumnName = "code_a")
    @ManyToOne
    private MhAgence codeA;

    public MhCompteUserA() {
    }

    public MhCompteUserA(String codeU) {
        this.codeU = codeU;
    }

   

    public String getCodeU() {
        return codeU;
    }

    public void setCodeU(String codeU) {
        this.codeU = codeU;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
    }
 //-------------------------------------------
    
   

    
}
