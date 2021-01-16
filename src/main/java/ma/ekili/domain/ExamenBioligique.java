package ma.ekili.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ExamenBioligique.
 */
@Entity
@Table(name = "examen_bioligique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamenBioligique implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "uree")
    private Double uree;

    @Column(name = "creat")
    private Double creat;

    @Column(name = "k")
    private Double k;

    @Column(name = "na")
    private Double na;

    @Column(name = "ca")
    private Double ca;

    @Column(name = "crp")
    private Double crp;

    @Column(name = "hb")
    private Double hb;

    @Column(name = "gb")
    private Double gb;

    @Column(name = "plt")
    private Double plt;

    @Column(name = "ac_hbs")
    private String acHbs;

    @Column(name = "ag_hbs")
    private Double agHbs;

    @Column(name = "hbc")
    private String hbc;

    @Column(name = "ac_hvc")
    private String acHvc;

    @Column(name = "vih")
    private String vih;

    @Column(name = "autre")
    private String autre;

    @ManyToOne
    @JsonIgnoreProperties(value = "examenBioligiques", allowSetters = true)
    private DossierPatient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public ExamenBioligique date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getUree() {
        return uree;
    }

    public ExamenBioligique uree(Double uree) {
        this.uree = uree;
        return this;
    }

    public void setUree(Double uree) {
        this.uree = uree;
    }

    public Double getCreat() {
        return creat;
    }

    public ExamenBioligique creat(Double creat) {
        this.creat = creat;
        return this;
    }

    public void setCreat(Double creat) {
        this.creat = creat;
    }

    public Double getK() {
        return k;
    }

    public ExamenBioligique k(Double k) {
        this.k = k;
        return this;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getNa() {
        return na;
    }

    public ExamenBioligique na(Double na) {
        this.na = na;
        return this;
    }

    public void setNa(Double na) {
        this.na = na;
    }

    public Double getCa() {
        return ca;
    }

    public ExamenBioligique ca(Double ca) {
        this.ca = ca;
        return this;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Double getCrp() {
        return crp;
    }

    public ExamenBioligique crp(Double crp) {
        this.crp = crp;
        return this;
    }

    public void setCrp(Double crp) {
        this.crp = crp;
    }

    public Double getHb() {
        return hb;
    }

    public ExamenBioligique hb(Double hb) {
        this.hb = hb;
        return this;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getGb() {
        return gb;
    }

    public ExamenBioligique gb(Double gb) {
        this.gb = gb;
        return this;
    }

    public void setGb(Double gb) {
        this.gb = gb;
    }

    public Double getPlt() {
        return plt;
    }

    public ExamenBioligique plt(Double plt) {
        this.plt = plt;
        return this;
    }

    public void setPlt(Double plt) {
        this.plt = plt;
    }

    public String getAcHbs() {
        return acHbs;
    }

    public ExamenBioligique acHbs(String acHbs) {
        this.acHbs = acHbs;
        return this;
    }

    public void setAcHbs(String acHbs) {
        this.acHbs = acHbs;
    }

    public Double getAgHbs() {
        return agHbs;
    }

    public ExamenBioligique agHbs(Double agHbs) {
        this.agHbs = agHbs;
        return this;
    }

    public void setAgHbs(Double agHbs) {
        this.agHbs = agHbs;
    }

    public String getHbc() {
        return hbc;
    }

    public ExamenBioligique hbc(String hbc) {
        this.hbc = hbc;
        return this;
    }

    public void setHbc(String hbc) {
        this.hbc = hbc;
    }

    public String getAcHvc() {
        return acHvc;
    }

    public ExamenBioligique acHvc(String acHvc) {
        this.acHvc = acHvc;
        return this;
    }

    public void setAcHvc(String acHvc) {
        this.acHvc = acHvc;
    }

    public String getVih() {
        return vih;
    }

    public ExamenBioligique vih(String vih) {
        this.vih = vih;
        return this;
    }

    public void setVih(String vih) {
        this.vih = vih;
    }

    public String getAutre() {
        return autre;
    }

    public ExamenBioligique autre(String autre) {
        this.autre = autre;
        return this;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public DossierPatient getPatient() {
        return patient;
    }

    public ExamenBioligique patient(DossierPatient dossierPatient) {
        this.patient = dossierPatient;
        return this;
    }

    public void setPatient(DossierPatient dossierPatient) {
        this.patient = dossierPatient;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamenBioligique)) {
            return false;
        }
        return id != null && id.equals(((ExamenBioligique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExamenBioligique{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", uree=" + getUree() +
            ", creat=" + getCreat() +
            ", k=" + getK() +
            ", na=" + getNa() +
            ", ca=" + getCa() +
            ", crp=" + getCrp() +
            ", hb=" + getHb() +
            ", gb=" + getGb() +
            ", plt=" + getPlt() +
            ", acHbs='" + getAcHbs() + "'" +
            ", agHbs=" + getAgHbs() +
            ", hbc='" + getHbc() + "'" +
            ", acHvc='" + getAcHvc() + "'" +
            ", vih='" + getVih() + "'" +
            ", autre='" + getAutre() + "'" +
            "}";
    }
}
