package ma.ekili.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Antecedent.
 */
@Entity
@Table(name = "antecedent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Antecedent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "autre")
    private String autre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutre() {
        return autre;
    }

    public Antecedent autre(String autre) {
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
        if (!(o instanceof Antecedent)) {
            return false;
        }
        return id != null && id.equals(((Antecedent) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Antecedent{" +
            "id=" + getId() +
            ", autre='" + getAutre() + "'" +
            "}";
    }
}
