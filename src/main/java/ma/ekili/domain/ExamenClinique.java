package ma.ekili.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ExamenClinique.
 */
@Entity
@Table(name = "examen_clinique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ExamenClinique implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "gcs")
    private Double gcs;

    @Column(name = "pa")
    private String pa;

    @Column(name = "diurese")
    private Integer diurese;

    @Column(name = "autre")
    private String autre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGcs() {
        return gcs;
    }

    public ExamenClinique gcs(Double gcs) {
        this.gcs = gcs;
        return this;
    }

    public void setGcs(Double gcs) {
        this.gcs = gcs;
    }

    public String getPa() {
        return pa;
    }

    public ExamenClinique pa(String pa) {
        this.pa = pa;
        return this;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public Integer getDiurese() {
        return diurese;
    }

    public ExamenClinique diurese(Integer diurese) {
        this.diurese = diurese;
        return this;
    }

    public void setDiurese(Integer diurese) {
        this.diurese = diurese;
    }

    public String getAutre() {
        return autre;
    }

    public ExamenClinique autre(String autre) {
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
        if (!(o instanceof ExamenClinique)) {
            return false;
        }
        return id != null && id.equals(((ExamenClinique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExamenClinique{" +
            "id=" + getId() +
            ", gcs=" + getGcs() +
            ", pa='" + getPa() + "'" +
            ", diurese=" + getDiurese() +
            ", autre='" + getAutre() + "'" +
            "}";
    }
}
