/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_compte_user_h", catalog = "jlvljuzg_monhotel", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code_u"})
    , @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhCompteUserH.findAll", query = "SELECT m FROM MhCompteUserH m")
    , @NamedQuery(name = "MhCompteUserH.findByTypeUser", query = "SELECT m FROM MhCompteUserH m WHERE m.typeUser = :typeUser")
    , @NamedQuery(name = "MhCompteUserH.findByCodeU", query = "SELECT m FROM MhCompteUserH m WHERE m.codeU = :codeU")
    , @NamedQuery(name = "MhCompteUserH.findByNom", query = "SELECT m FROM MhCompteUserH m WHERE m.nom = :nom")
    , @NamedQuery(name = "MhCompteUserH.findByEmail", query = "SELECT m FROM MhCompteUserH m WHERE m.email = :email")
    , @NamedQuery(name = "MhCompteUserH.findByPsw", query = "SELECT m FROM MhCompteUserH m WHERE m.psw = :psw")
    , @NamedQuery(name = "MhCompteUserH.findByAdmin", query = "SELECT m FROM MhCompteUserH m WHERE m.admin = :admin")
    , @NamedQuery(name = "MhCompteUserH.findByTel", query = "SELECT m FROM MhCompteUserH m WHERE m.tel = :tel")})
public class MhCompteUserH implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "mhCompteUserH")
    private MhUsersDroits mhUsersDroits;

    @OneToMany(mappedBy = "codeU")
    private Collection<MhReservation> mhReservationCollection;

    @Size(max = 20)
    @Column(name = "type_user", length = 20)
    private String typeUser;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_u", nullable = false, length = 200)
    private String codeU;
    @Size(max = 200)
    @Column(name = "nom", length = 200)
    private String nom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
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
    @JoinColumn(name = "code_h", referencedColumnName = "code_h")
    @ManyToOne
    private MhHotel codeH;

    public MhCompteUserH() {
    }

    public MhCompteUserH(String codeU) {
        this.codeU = codeU;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getCodeU() {
        return codeU;
    }

    public void setCodeU(String codeU) {
        this.codeU = codeU;
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

    public MhHotel getCodeH() {
        return codeH;
    }

    public void setCodeH(MhHotel codeH) {
        this.codeH = codeH;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeU != null ? codeU.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhCompteUserH)) {
            return false;
        }
        MhCompteUserH other = (MhCompteUserH) object;
        if ((this.codeU == null && other.codeU != null) || (this.codeU != null && !this.codeU.equals(other.codeU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhCompteUserH[ codeU=" + codeU + " ]";
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    public MhUsersDroits getMhUsersDroits() {
        return mhUsersDroits;
    }

    public void setMhUsersDroits(MhUsersDroits mhUsersDroits) {
        this.mhUsersDroits = mhUsersDroits;
    }

  
    
}
