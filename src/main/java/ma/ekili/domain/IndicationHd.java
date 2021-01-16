package ma.ekili.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A IndicationHd.
 */
@Entity
@Table(name = "indication_hd")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IndicationHd implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "service")
    private Integer service;

    @Column(name = "autre")
    private String autre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getService() {
        return service;
    }

    public IndicationHd service(Integer service) {
        this.service = service;
        return this;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getAutre() {
        return autre;
    }

    public IndicationHd autre(String autre) {
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
        if (!(o instanceof IndicationHd)) {
            return false;
        }
        return id != null && id.equals(((IndicationHd) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndicationHd{" +
            "id=" + getId() +
            ", service=" + getService() +
            ", autre='" + getAutre() + "'" +
            "}";
    }
}
