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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "mh_client_sdem", catalog = "monHotel", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MhClientSdem.findAll", query = "SELECT m FROM MhClientSdem m")
    , @NamedQuery(name = "MhClientSdem.findByCodeCltdem", query = "SELECT m FROM MhClientSdem m WHERE m.codeCltdem = :codeCltdem")})
public class MhClientSdem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "code_cltdem", nullable = false, length = 200)
    private String codeCltdem;
    @JoinColumn(name = "npid", referencedColumnName = "n_p_id", nullable = false)
    @ManyToOne(optional = false)
    private MhCltSCh npid;
    @JoinColumn(name = "code_dm", referencedColumnName = "code_dm", nullable = false)
    @ManyToOne(optional = false)
    private MhDemandeR codeDm;

    public MhClientSdem() {
    }

    public MhClientSdem(String codeCltdem) {
        this.codeCltdem = codeCltdem;
    }

    public String getCodeCltdem() {
        return codeCltdem;
    }

    public void setCodeCltdem(String codeCltdem) {
        this.codeCltdem = codeCltdem;
    }

    public MhCltSCh getNpid() {
        return npid;
    }

    public void setNpid(MhCltSCh npid) {
        this.npid = npid;
    }

    public MhDemandeR getCodeDm() {
        return codeDm;
    }

    public void setCodeDm(MhDemandeR codeDm) {
        this.codeDm = codeDm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeCltdem != null ? codeCltdem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MhClientSdem)) {
            return false;
        }
        MhClientSdem other = (MhClientSdem) object;
        if ((this.codeCltdem == null && other.codeCltdem != null) || (this.codeCltdem != null && !this.codeCltdem.equals(other.codeCltdem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.MhClientSdem[ codeCltdem=" + codeCltdem + " ]";
    }
    
}
