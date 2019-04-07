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
import model.MhChambre;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_convention_agc_chambre", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhConventionAgcChambre.findAll", query = "SELECT m FROM MhConventionAgcChambre m")
    , @NamedQuery(name = "MhConventionAgcChambre.findByCodeAc", query = "SELECT m FROM MhConventionAgcChambre m WHERE m.codeAc = :codeAc")})
public class MhConventionAgcChambre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_ac", nullable = false, length = 200)
    private String codeAc;
    @JoinColumn(name = "num_ch", referencedColumnName = "num_ch", nullable = false)
    @ManyToOne(optional = false)
    private MhChambre numCh;
    @JoinColumn(name = "code_convention_agc", referencedColumnName = "code_conv_agc", nullable = false)
    @ManyToOne(optional = false)
    private MhConventionAgc codeConventionAgc;

    public MhConventionAgcChambre() {
    }

    public MhConventionAgcChambre(String codeAc) {
        this.codeAc = codeAc;
    }

    public String getCodeAc() {
        return codeAc;
    }

    public void setCodeAc(String codeAc) {
        this.codeAc = codeAc;
    }

    public MhChambre getNumCh() {
        return numCh;
    }

    public void setNumCh(MhChambre numCh) {
        this.numCh = numCh;
    }

    public MhConventionAgc getCodeConventionAgc() {
        return codeConventionAgc;
    }

    public void setCodeConventionAgc(MhConventionAgc codeConventionAgc) {
        this.codeConventionAgc = codeConventionAgc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeAc != null ? codeAc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhConventionAgcChambre)) {
            return false;
        }
        MhConventionAgcChambre other = (MhConventionAgcChambre) object;
        if ((this.codeAc == null && other.codeAc != null) || (this.codeAc != null && !this.codeAc.equals(other.codeAc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.agc.MhConventionAgcChambre[ codeAc=" + codeAc + " ]";
    }
    
}
