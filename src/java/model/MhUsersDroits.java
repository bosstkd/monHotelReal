/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_users_droits", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhUsersDroits.findAll", query = "SELECT m FROM MhUsersDroits m")
    , @NamedQuery(name = "MhUsersDroits.findByCodeU", query = "SELECT m FROM MhUsersDroits m WHERE m.codeU = :codeU")
    , @NamedQuery(name = "MhUsersDroits.findByChambre", query = "SELECT m FROM MhUsersDroits m WHERE m.chambre = :chambre")
    , @NamedQuery(name = "MhUsersDroits.findByCompteUser", query = "SELECT m FROM MhUsersDroits m WHERE m.compteUser = :compteUser")
    , @NamedQuery(name = "MhUsersDroits.findByInformationPerso", query = "SELECT m FROM MhUsersDroits m WHERE m.informationPerso = :informationPerso")
    , @NamedQuery(name = "MhUsersDroits.findByClients", query = "SELECT m FROM MhUsersDroits m WHERE m.clients = :clients")
    , @NamedQuery(name = "MhUsersDroits.findByEntreprises", query = "SELECT m FROM MhUsersDroits m WHERE m.entreprises = :entreprises")
    , @NamedQuery(name = "MhUsersDroits.findByReservation", query = "SELECT m FROM MhUsersDroits m WHERE m.reservation = :reservation")
    , @NamedQuery(name = "MhUsersDroits.findByChargeSupp", query = "SELECT m FROM MhUsersDroits m WHERE m.chargeSupp = :chargeSupp")
    , @NamedQuery(name = "MhUsersDroits.findByLiberation", query = "SELECT m FROM MhUsersDroits m WHERE m.liberation = :liberation")
    , @NamedQuery(name = "MhUsersDroits.findByVersement", query = "SELECT m FROM MhUsersDroits m WHERE m.versement = :versement")
    , @NamedQuery(name = "MhUsersDroits.findByGestionCaisse", query = "SELECT m FROM MhUsersDroits m WHERE m.gestionCaisse = :gestionCaisse")
    , @NamedQuery(name = "MhUsersDroits.findByFacturation", query = "SELECT m FROM MhUsersDroits m WHERE m.facturation = :facturation")
    , @NamedQuery(name = "MhUsersDroits.findByCalendrier", query = "SELECT m FROM MhUsersDroits m WHERE m.calendrier = :calendrier")
    , @NamedQuery(name = "MhUsersDroits.findByStatistique", query = "SELECT m FROM MhUsersDroits m WHERE m.statistique = :statistique")
    , @NamedQuery(name = "MhUsersDroits.findByListePolice", query = "SELECT m FROM MhUsersDroits m WHERE m.listePolice = :listePolice")})
public class MhUsersDroits implements Serializable {

    @Column(name = "convention")
    private Boolean convention;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_u", nullable = false, length = 200)
    private String codeU;
    @Basic(optional = false)
    @NotNull
    @Column(name = "chambre", nullable = false)
    private boolean chambre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "compteUser", nullable = false)
    private boolean compteUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "informationPerso", nullable = false)
    private boolean informationPerso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "clients", nullable = false)
    private boolean clients;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entreprises", nullable = false)
    private boolean entreprises;
    @Basic(optional = false)
    @NotNull
    @Column(name = "reservation", nullable = false)
    private boolean reservation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "chargeSupp", nullable = false)
    private boolean chargeSupp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "liberation", nullable = false)
    private boolean liberation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "versement", nullable = false)
    private boolean versement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestionCaisse", nullable = false)
    private boolean gestionCaisse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "facturation", nullable = false)
    private boolean facturation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "calendrier", nullable = false)
    private boolean calendrier;
    @Basic(optional = false)
    @NotNull
    @Column(name = "statistique", nullable = false)
    private boolean statistique;
    @Basic(optional = false)
    @NotNull
    @Column(name = "listePolice", nullable = false)
    private boolean listePolice;
    @JoinColumn(name = "code_u", referencedColumnName = "code_u", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private MhCompteUserH mhCompteUserH;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;

    public MhUsersDroits() {
    }

    public MhUsersDroits(String codeU) {
        this.codeU = codeU;
    }

    public MhUsersDroits(String codeU, boolean chambre, boolean compteUser, boolean informationPerso, boolean clients, boolean entreprises, boolean reservation, boolean chargeSupp, boolean liberation, boolean versement, boolean gestionCaisse, boolean facturation, boolean calendrier, boolean statistique, boolean listePolice) {
        this.codeU = codeU;
        this.chambre = chambre;
        this.compteUser = compteUser;
        this.informationPerso = informationPerso;
        this.clients = clients;
        this.entreprises = entreprises;
        this.reservation = reservation;
        this.chargeSupp = chargeSupp;
        this.liberation = liberation;
        this.versement = versement;
        this.gestionCaisse = gestionCaisse;
        this.facturation = facturation;
        this.calendrier = calendrier;
        this.statistique = statistique;
        this.listePolice = listePolice;
    }

    public String getCodeU() {
        return codeU;
    }

    public void setCodeU(String codeU) {
        this.codeU = codeU;
    }

    public boolean getChambre() {
        return chambre;
    }

    public void setChambre(boolean chambre) {
        this.chambre = chambre;
    }

    public boolean getCompteUser() {
        return compteUser;
    }

    public void setCompteUser(boolean compteUser) {
        this.compteUser = compteUser;
    }

    public boolean getInformationPerso() {
        return informationPerso;
    }

    public void setInformationPerso(boolean informationPerso) {
        this.informationPerso = informationPerso;
    }

    public boolean getClients() {
        return clients;
    }

    public void setClients(boolean clients) {
        this.clients = clients;
    }

    public boolean getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(boolean entreprises) {
        this.entreprises = entreprises;
    }

    public boolean getReservation() {
        return reservation;
    }

    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

    public boolean getChargeSupp() {
        return chargeSupp;
    }

    public void setChargeSupp(boolean chargeSupp) {
        this.chargeSupp = chargeSupp;
    }

    public boolean getLiberation() {
        return liberation;
    }

    public void setLiberation(boolean liberation) {
        this.liberation = liberation;
    }

    public boolean getVersement() {
        return versement;
    }

    public void setVersement(boolean versement) {
        this.versement = versement;
    }

    public boolean getGestionCaisse() {
        return gestionCaisse;
    }

    public void setGestionCaisse(boolean gestionCaisse) {
        this.gestionCaisse = gestionCaisse;
    }

    public boolean getFacturation() {
        return facturation;
    }

    public void setFacturation(boolean facturation) {
        this.facturation = facturation;
    }

    public boolean getCalendrier() {
        return calendrier;
    }

    public void setCalendrier(boolean calendrier) {
        this.calendrier = calendrier;
    }

    public boolean getStatistique() {
        return statistique;
    }

    public void setStatistique(boolean statistique) {
        this.statistique = statistique;
    }

    public boolean getListePolice() {
        return listePolice;
    }

    public void setListePolice(boolean listePolice) {
        this.listePolice = listePolice;
    }

    public MhCompteUserH getMhCompteUserH() {
        return mhCompteUserH;
    }

    public void setMhCompteUserH(MhCompteUserH mhCompteUserH) {
        this.mhCompteUserH = mhCompteUserH;
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
        if (!(object instanceof MhUsersDroits)) {
            return false;
        }
        MhUsersDroits other = (MhUsersDroits) object;
        if ((this.codeU == null && other.codeU != null) || (this.codeU != null && !this.codeU.equals(other.codeU))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhUsersDroits[ codeU=" + codeU + " ]";
    }

    public Boolean getConvention() {
        return convention;
    }

    public void setConvention(Boolean convention) {
        this.convention = convention;
    }
    
}
