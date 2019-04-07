
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mh_caisse", catalog = "monHotel", schema = "")
public class MhCaisse implements Serializable{
    
    @Id
    @Column(name = "dates")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dates;
    
    @Column(name = "code_h")
    private String codeH;
    
    @Column(name = "code_u")
    private String codeU;
    
    @Column(name = "code_r")
    private String codeR;
    
    @Column(name = "motif")
    private String motif;
    
    @Column(name = "somme")
    private double somme;

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }

    public String getCodeH() {
        return codeH;
    }

    public void setCodeH(String codeH) {
        this.codeH = codeH;
    }

    public String getCodeU() {
        return codeU;
    }

    public void setCodeU(String codeU) {
        this.codeU = codeU;
    }

    public String getCodeR() {
        return codeR;
    }

    public void setCodeR(String codeR) {
        this.codeR = codeR;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public double getSomme() {
        return somme;
    }

    public void setSomme(double somme) {
        this.somme = somme;
    }
    
    
    
}
