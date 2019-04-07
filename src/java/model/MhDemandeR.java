/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_demande_r", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhDemandeR.findAll", query = "SELECT m FROM MhDemandeR m")
    , @NamedQuery(name = "MhDemandeR.findByCodeDm", query = "SELECT m FROM MhDemandeR m WHERE m.codeDm = :codeDm")
    , @NamedQuery(name = "MhDemandeR.findByDu", query = "SELECT m FROM MhDemandeR m WHERE m.du = :du")
    , @NamedQuery(name = "MhDemandeR.findByAu", query = "SELECT m FROM MhDemandeR m WHERE m.au = :au")
    , @NamedQuery(name = "MhDemandeR.findByEtatDm", query = "SELECT m FROM MhDemandeR m WHERE m.etatDm = :etatDm")
    , @NamedQuery(name = "MhDemandeR.findByDateDm", query = "SELECT m FROM MhDemandeR m WHERE m.dateDm = :dateDm")})
public class MhDemandeR implements Serializable {

    @Size(max = 200)
    @Column(name = "TypePension", length = 200)
    private String typePension;

    @OneToMany(mappedBy = "codeDm")
    private Collection<MhReservation> mhReservationCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeDm")
    private Collection<MhClientSdem> mhClientSdemCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 210)
    @Column(name = "code_dm", nullable = false, length = 210)
    private String codeDm;
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
    @Column(name = "etat_dm", nullable = false)
    private boolean etatDm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_dm", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDm;
    @JoinColumn(name = "numch", referencedColumnName = "num_ch", nullable = false)
    @ManyToOne(optional = false)
    private MhChambre numch;
    @JoinColumn(name = "code_c", referencedColumnName = "code_c", nullable = false)
    @ManyToOne(optional = false)
    private MhCltFct codeC;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;

    public MhDemandeR() {
    }

    public MhDemandeR(String codeDm) {
        this.codeDm = codeDm;
    }

    public MhDemandeR(String codeDm, Date du, Date au, boolean etatDm, Date dateDm) {
        this.codeDm = codeDm;
        this.du = du;
        this.au = au;
        this.etatDm = etatDm;
        this.dateDm = dateDm;
    }

    public String getCodeDm() {
        return codeDm;
    }

    public void setCodeDm(String codeDm) {
        this.codeDm = codeDm;
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

    public boolean getEtatDm() {
        return etatDm;
    }

    public void setEtatDm(boolean etatDm) {
        this.etatDm = etatDm;
    }

    public Date getDateDm() {
        return dateDm;
    }

    public void setDateDm(Date dateDm) {
        this.dateDm = dateDm;
    }

    public MhChambre getNumch() {
        return numch;
    }

    public void setNumch(MhChambre numch) {
        this.numch = numch;
    }

    public MhCltFct getCodeC() {
        return codeC;
    }

    public void setCodeC(MhCltFct codeC) {
        this.codeC = codeC;
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
        hash += (codeDm != null ? codeDm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhDemandeR)) {
            return false;
        }
        MhDemandeR other = (MhDemandeR) object;
        if ((this.codeDm == null && other.codeDm != null) || (this.codeDm != null && !this.codeDm.equals(other.codeDm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhDemandeR[ codeDm=" + codeDm + " ]";
    }

    @XmlTransient
    public Collection<MhClientSdem> getMhClientSdemCollection() {
        return mhClientSdemCollection;
    }

    public void setMhClientSdemCollection(Collection<MhClientSdem> mhClientSdemCollection) {
        this.mhClientSdemCollection = mhClientSdemCollection;
    }

    @XmlTransient
    public Collection<MhReservation> getMhReservationCollection() {
        return mhReservationCollection;
    }

    public void setMhReservationCollection(Collection<MhReservation> mhReservationCollection) {
        this.mhReservationCollection = mhReservationCollection;
    }

    public String getTypePension() {
        return typePension;
    }

    public void setTypePension(String typePension) {
        this.typePension = typePension;
    }
    
}
