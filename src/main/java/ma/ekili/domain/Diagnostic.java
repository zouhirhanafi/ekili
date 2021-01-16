package ma.ekili.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Diagnostic.
 */
@Entity
@Table(name = "diagnostic")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Diagnostic implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "hvb")
    private Integer hvb;

    @Column(name = "hvc")
    private Integer hvc;

    @Column(name = "vih")
    private Integer vih;

    @Column(name = "poid_sec")
    private Double poidSec;

    @Column(name = "autre")
    private String autre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHvb() {
        return hvb;
    }

    public Diagnostic hvb(Integer hvb) {
        this.hvb = hvb;
        return this;
    }

    public void setHvb(Integer hvb) {
        this.hvb = hvb;
    }

    public Integer getHvc() {
        return hvc;
    }

    public Diagnostic hvc(Integer hvc) {
        this.hvc = hvc;
        return this;
    }

    public void setHvc(Integer hvc) {
        this.hvc = hvc;
    }

    public Integer getVih() {
        return vih;
    }

    public Diagnostic vih(Integer vih) {
        this.vih = vih;
        return this;
    }

    public void setVih(Integer vih) {
        this.vih = vih;
    }

    public Double getPoidSec() {
        return poidSec;
    }

    public Diagnostic poidSec(Double poidSec) {
        this.poidSec = poidSec;
        return this;
    }

    public void setPoidSec(Double poidSec) {
        this.poidSec = poidSec;
    }

    public String getAutre() {
        return autre;
    }

    public Diagnostic autre(String autre) {
        this.autre = autre;
        return this;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diagnostic)) {
            return false;
        }
        return id != null && id.equals(((Diagnostic) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Diagnostic{" +
            "id=" + getId() +
            ", hvb=" + getHvb() +
            ", hvc=" + getHvc() +
            ", vih=" + getVih() +
            ", poidSec=" + getPoidSec() +
            ", autre='" + getAutre() + "'" +
            "}";
    }
}
