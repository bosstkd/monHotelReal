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
@Table(name = "mh_convention", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhConvention.findAll", query = "SELECT m FROM MhConvention m")
    , @NamedQuery(name = "MhConvention.findByCodeConvention", query = "SELECT m FROM MhConvention m WHERE m.codeConvention = :codeConvention")
    , @NamedQuery(name = "MhConvention.findByCle", query = "SELECT m FROM MhConvention m WHERE m.cle = :cle")
    , @NamedQuery(name = "MhConvention.findByPrcReduction", query = "SELECT m FROM MhConvention m WHERE m.prcReduction = :prcReduction")})
public class MhConvention implements Serializable {

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

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_convention", nullable = false, length = 200)
    private String codeConvention;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cle", nullable = false, length = 200)
    private String cle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prc_reduction", nullable = false)
    private int prcReduction;
    @JoinColumn(name = "code_c", referencedColumnName = "code_c", nullable = false)
    @ManyToOne(optional = false)
    private MhCltFct codeC;
    @JoinColumn(name = "code_h", referencedColumnName = "code_h", nullable = false)
    @ManyToOne(optional = false)
    private MhHotel codeH;

    public MhConvention() {
    }

    public MhConvention(String codeConvention) {
        this.codeConvention = codeConvention;
    }

    public MhConvention(String codeConvention, String cle, int prcReduction) {
        this.codeConvention = codeConvention;
        this.cle = cle;
        this.prcReduction = prcReduction;
    }

    public String getCodeConvention() {
        return codeConvention;
    }

    public void setCodeConvention(String codeConvention) {
        this.codeConvention = codeConvention;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public int getPrcReduction() {
        return prcReduction;
    }

    public void setPrcReduction(int prcReduction) {
        this.prcReduction = prcReduction;
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
        hash += (codeConvention != null ? codeConvention.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhConvention)) {
            return false;
        }
        MhConvention other = (MhConvention) object;
        if ((this.codeConvention == null && other.codeConvention != null) || (this.codeConvention != null && !this.codeConvention.equals(other.codeConvention))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhConvention[ codeConvention=" + codeConvention + " ]";
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
    
}
