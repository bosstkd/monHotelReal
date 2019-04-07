/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_demande_clt", catalog = "jlvljuzg_monhotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhDemandeClt.findAll", query = "SELECT m FROM MhDemandeClt m")
    , @NamedQuery(name = "MhDemandeClt.findByCodeDmClt", query = "SELECT m FROM MhDemandeClt m WHERE m.codeDmClt = :codeDmClt")
    , @NamedQuery(name = "MhDemandeClt.findByDu", query = "SELECT m FROM MhDemandeClt m WHERE m.du = :du")
    , @NamedQuery(name = "MhDemandeClt.findByAu", query = "SELECT m FROM MhDemandeClt m WHERE m.au = :au")
    , @NamedQuery(name = "MhDemandeClt.findByTypeChambre", query = "SELECT m FROM MhDemandeClt m WHERE m.typeChambre = :typeChambre")
    , @NamedQuery(name = "MhDemandeClt.findByNbPlace", query = "SELECT m FROM MhDemandeClt m WHERE m.nbPlace = :nbPlace")
    , @NamedQuery(name = "MhDemandeClt.findByDateDm", query = "SELECT m FROM MhDemandeClt m WHERE m.dateDm = :dateDm")})
public class MhDemandeClt implements Serializable {

    @Column(name = "Accepte")
    private Boolean accepte;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_dm_clt", nullable = false, length = 200)
    private String codeDmClt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "du", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date du;
    @Basic(optional = false)
    @NotNull
    @Column(name = "au", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date au;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type_chambre", nullable = false, length = 100)
    private String typeChambre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nb_place", nullable = false)
    private int nbPlace;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_dm", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDm;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "commentaire", nullable = false, length = 65535)
    private String commentaire;
    @JoinColumn(name = "npid", referencedColumnName = "n_p_id", nullable = false)
    @ManyToOne(optional = false)
    private MhCltSCh npid;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;

    public MhDemandeClt() {
    }

    public MhDemandeClt(String codeDmClt) {
        this.codeDmClt = codeDmClt;
    }

    public MhDemandeClt(String codeDmClt, Date du, Date au, String typeChambre, int nbPlace, Date dateDm, String commentaire) {
        this.codeDmClt = codeDmClt;
        this.du = du;
        this.au = au;
        this.typeChambre = typeChambre;
        this.nbPlace = nbPlace;
        this.dateDm = dateDm;
        this.commentaire = commentaire;
    }

    public String getCodeDmClt() {
        return codeDmClt;
    }

    public void setCodeDmClt(String codeDmClt) {
        this.codeDmClt = codeDmClt;
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

    public String getTypeChambre() {
        return typeChambre;
    }

    public void setTypeChambre(String typeChambre) {
        this.typeChambre = typeChambre;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Date getDateDm() {
        return dateDm;
    }

    public void setDateDm(Date dateDm) {
        this.dateDm = dateDm;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public MhCltSCh getNpid() {
        return npid;
    }

    public void setNpid(MhCltSCh npid) {
        this.npid = npid;
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
        hash += (codeDmClt != null ? codeDmClt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhDemandeClt)) {
            return false;
        }
        MhDemandeClt other = (MhDemandeClt) object;
        if ((this.codeDmClt == null && other.codeDmClt != null) || (this.codeDmClt != null && !this.codeDmClt.equals(other.codeDmClt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhDemandeClt[ codeDmClt=" + codeDmClt + " ]";
    }

    public Boolean getAccepte() {
        return accepte;
    }

    public void setAccepte(Boolean accepte) {
        this.accepte = accepte;
    }
    
}
