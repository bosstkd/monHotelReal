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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_users_droits_agc", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhUsersDroitsAgc.findAll", query = "SELECT m FROM MhUsersDroitsAgc m")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByCodeU", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.codeU = :codeU")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByCompteUser", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.compteUser = :compteUser")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByInfos", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.infos = :infos")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByDemandeConv", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.demandeConv = :demandeConv")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByClients", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.clients = :clients")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByReservation", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.reservation = :reservation")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByDemReservation", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.demReservation = :demReservation")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByPublication", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.publication = :publication")
    , @NamedQuery(name = "MhUsersDroitsAgc.findByStatuts", query = "SELECT m FROM MhUsersDroitsAgc m WHERE m.statuts = :statuts")})
public class MhUsersDroitsAgc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_u", nullable = false, length = 200)
    private String codeU;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compteUser", nullable = false)
    private boolean compteUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "infos", nullable = false)
    private boolean infos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "demandeConv", nullable = false)
    private boolean demandeConv;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clients", nullable = false)
    private boolean clients;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reservation", nullable = false)
    private boolean reservation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "demReservation", nullable = false)
    private boolean demReservation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publication", nullable = false)
    private boolean publication;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statuts", nullable = false)
    private boolean statuts;
    @JoinColumn(name = "code_a", referencedColumnName = "code_a", nullable = false)
    @ManyToOne(optional = false)
    private MhAgence codeA;

    public MhUsersDroitsAgc() {
    }

    public MhUsersDroitsAgc(String codeU) {
        this.codeU = codeU;
    }

    public MhUsersDroitsAgc(String codeU, boolean compteUser, boolean infos, boolean demandeConv, boolean clients, boolean reservation, boolean demReservation, boolean publication, boolean statuts) {
        this.codeU = codeU;
        this.compteUser = compteUser;
        this.infos = infos;
        this.demandeConv = demandeConv;
        this.clients = clients;
        this.reservation = reservation;
        this.demReservation = demReservation;
        this.publication = publication;
        this.statuts = statuts;
    }

    public String getCodeU() {
        return codeU;
    }

    public void setCodeU(String codeU) {
        this.codeU = codeU;
    }

    public boolean getCompteUser() {
        return compteUser;
    }

    public void setCompteUser(boolean compteUser) {
        this.compteUser = compteUser;
    }

    public boolean getInfos() {
        return infos;
    }

    public void setInfos(boolean infos) {
        this.infos = infos;
    }

    public boolean getDemandeConv() {
        return demandeConv;
    }

    public void setDemandeConv(boolean demandeConv) {
        this.demandeConv = demandeConv;
    }

    public boolean getClients() {
        return clients;
    }

    public void setClients(boolean clients) {
        this.clients = clients;
    }

    public boolean getReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public boolean getDemReservation() {
        return demReservation;
    }

    public void setDemReservation(boolean demReservation) {
        this.demReservation = demReservation;
    }

    public boolean getPublication() {
        return publication;
    }

    public void setPublication(boolean publication) {
        this.publication = publication;
    }

    public boolean getStatuts() {
        return statuts;
    }

    public void setStatuts(boolean statuts) {
        this.statuts = statuts;
    }

    public MhAgence getCodeA() {
        return codeA;
    }

    public void setCodeA(MhAgence codeA) {
        this.codeA = codeA;
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
        if (!(object instanceof MhUsersDroitsAgc)) {
            return false;
        }
        MhUsersDroitsAgc other = (MhUsersDroitsAgc) object;
        if ((this.codeU == null && other.codeU != null) || (this.codeU != null && !this.codeU.equals(other.codeU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhUsersDroitsAgc[ codeU=" + codeU + " ]";
    }
    
}
