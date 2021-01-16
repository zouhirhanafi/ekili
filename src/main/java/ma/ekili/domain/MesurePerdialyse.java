package ma.ekili.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MesurePerdialyse.
 */
@Entity
@Table(name = "mesure_perdialyse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MesurePerdialyse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "heure")
    private String heure;

    @Column(name = "poid")
    private Double poid;

    @Column(name = "ta")
    private String ta;

    @Column(name = "tp")
    private Double tp;

    @Column(name = "dextro")
    private Double dextro;

    @Column(name = "pa")
    private String pa;

    @Column(name = "pv")
    private Double pv;

    @Column(name = "ptm")
    private Double ptm;

    @Column(name = "ufh")
    private Double ufh;

    @Column(name = "conductivite")
    private Double conductivite;

    @Column(name = "td")
    private Double td;

    @Column(name = "dps")
    private Double dps;

    @Column(name = "heparine")
    private Double heparine;

    @Column(name = "rincage")
    private Integer rincage;

    @Column(name = "transfusion")
    private Integer transfusion;

    @Column(name = "num_poche")
    private Integer numPoche;

    @ManyToOne
    @JsonIgnoreProperties(value = "mesurePerdialyses", allowSetters = true)
    private Surveillance surveillance;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeure() {
        return heure;
    }

    public MesurePerdialyse heure(String heure) {
        this.heure = heure;
        return this;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Double getPoid() {
        return poid;
    }

    public MesurePerdialyse poid(Double poid) {
        this.poid = poid;
        return this;
    }

    public void setPoid(Double poid) {
        this.poid = poid;
    }

    public String getTa() {
        return ta;
    }

    public MesurePerdialyse ta(String ta) {
        this.ta = ta;
        return this;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public Double getTp() {
        return tp;
    }

    public MesurePerdialyse tp(Double tp) {
        this.tp = tp;
        return this;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getDextro() {
        return dextro;
    }

    public MesurePerdialyse dextro(Double dextro) {
        this.dextro = dextro;
        return this;
    }

    public void setDextro(Double dextro) {
        this.dextro = dextro;
    }

    public String getPa() {
        return pa;
    }

    public MesurePerdialyse pa(String pa) {
        this.pa = pa;
        return this;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public Double getPv() {
        return pv;
    }

    public MesurePerdialyse pv(Double pv) {
        this.pv = pv;
        return this;
    }

    public void setPv(Double pv) {
        this.pv = pv;
    }

    public Double getPtm() {
        return ptm;
    }

    public MesurePerdialyse ptm(Double ptm) {
        this.ptm = ptm;
        return this;
    }

    public void setPtm(Double ptm) {
        this.ptm = ptm;
    }

    public Double getUfh() {
        return ufh;
    }

    public MesurePerdialyse ufh(Double ufh) {
        this.ufh = ufh;
        return this;
    }

    public void setUfh(Double ufh) {
        this.ufh = ufh;
    }

    public Double getConductivite() {
        return conductivite;
    }

    public MesurePerdialyse conductivite(Double conductivite) {
        this.conductivite = conductivite;
        return this;
    }

    public void setConductivite(Double conductivite) {
        this.conductivite = conductivite;
    }

    public Double getTd() {
        return td;
    }

    public MesurePerdialyse td(Double td) {
        this.td = td;
        return this;
    }

    public void setTd(Double td) {
        this.td = td;
    }

    public Double getDps() {
        return dps;
    }

    public MesurePerdialyse dps(Double dps) {
        this.dps = dps;
        return this;
    }

    public void setDps(Double dps) {
        this.dps = dps;
    }

    public Double getHeparine() {
        return heparine;
    }

    public MesurePerdialyse heparine(Double heparine) {
        this.heparine = heparine;
        return this;
    }

    public void setHeparine(Double heparine) {
        this.heparine = heparine;
    }

    public Integer getRincage() {
        return rincage;
    }

    public MesurePerdialyse rincage(Integer rincage) {
        this.rincage = rincage;
        return this;
    }

    public void setRincage(Integer rincage) {
        this.rincage = rincage;
    }

    public Integer getTransfusion() {
        return transfusion;
    }

    public MesurePerdialyse transfusion(Integer transfusion) {
        this.transfusion = transfusion;
        return this;
    }

    public void setTransfusion(Integer transfusion) {
        this.transfusion = transfusion;
    }

    public Integer getNumPoche() {
        return numPoche;
    }

    public MesurePerdialyse numPoche(Integer numPoche) {
        this.numPoche = numPoche;
        return this;
    }

    public void setNumPoche(Integer numPoche) {
        this.numPoche = numPoche;
    }

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public MesurePerdialyse surveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
        return this;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MesurePerdialyse)) {
            return false;
        }
        return id != null && id.equals(((MesurePerdialyse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MesurePerdialyse{" +
            "id=" + getId() +
            ", heure='" + getHeure() + "'" +
            ", poid=" + getPoid() +
            ", ta='" + getTa() + "'" +
            ", tp=" + getTp() +
            ", dextro=" + getDextro() +
            ", pa='" + getPa() + "'" +
            ", pv=" + getPv() +
            ", ptm=" + getPtm() +
            ", ufh=" + getUfh() +
            ", conductivite=" + getConductivite() +
            ", td=" + getTd() +
            ", dps=" + getDps() +
            ", heparine=" + getHeparine() +
            ", rincage=" + getRincage() +
            ", transfusion=" + getTransfusion() +
            ", numPoche=" + getNumPoche() +
            "}";
    }
}
