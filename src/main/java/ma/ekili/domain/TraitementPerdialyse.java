package ma.ekili.domain;

import java.io.Serializable;
import javax.persistence.*;
import ma.ekili.domain.enumeration.TypeTraitementPerdialyse;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TraitementPerdialyse.
 */
@Entity
@Table(name = "traitement_perdialyse")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TraitementPerdialyse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "autre")
    private String autre;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeTraitementPerdialyse type;

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

    public TraitementPerdialyse autre(String autre) {
        this.autre = autre;
        return this;
    }

    public void setAutre(String autre) {
        this.autre = autre;
    }

    public TypeTraitementPerdialyse getType() {
        return type;
    }

    public TraitementPerdialyse type(TypeTraitementPerdialyse type) {
        this.type = type;
        return this;
    }

    public void setType(TypeTraitementPerdialyse type) {
        this.type = type;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TraitementPerdialyse)) {
            return false;
        }
        return id != null && id.equals(((TraitementPerdialyse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TraitementPerdialyse{" +
            "id=" + getId() +
            ", autre='" + getAutre() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
