package ma.ekili.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import ma.ekili.domain.enumeration.StatutSurveillance;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Surveillance.
 */
@Entity
@Table(name = "surveillance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Surveillance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "infirmier", nullable = false)
    private Integer infirmier;

    @Column(name = "poste")
    private Integer poste;

    @Column(name = "generateur")
    private Integer generateur;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutSurveillance statut;

    @Column(name = "poid")
    private Double poid;

    @Column(name = "ufnet")
    private Double ufnet;

    @Column(name = "etat_conscience")
    private Integer etatConscience;

    @Column(name = "eupneique")
    private Integer eupneique;

    @Column(name = "restitution_par")
    private Integer restitutionPar;

    @Column(name = "autre_complication")
    private String autreComplication;

    @Column(name = "observation")
    private String observation;

    @OneToOne
    @JoinColumn(unique = true)
    private TraitementPerdialyse traitement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInfirmier() {
        return infirmier;
    }

    public Surveillance infirmier(Integer infirmier) {
        this.infirmier = infirmier;
        return this;
    }

    public void setInfirmier(Integer infirmier) {
        this.infirmier = infirmier;
    }

    public Integer getPoste() {
        return poste;
    }

    public Surveillance poste(Integer poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(Integer poste) {
        this.poste = poste;
    }

    public Integer getGenerateur() {
        return generateur;
    }

    public Surveillance generateur(Integer generateur) {
        this.generateur = generateur;
        return this;
    }

    public void setGenerateur(Integer generateur) {
        this.generateur = generateur;
    }

    public StatutSurveillance getStatut() {
        return statut;
    }

    public Surveillance statut(StatutSurveillance statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(StatutSurveillance statut) {
        this.statut = statut;
    }

    public Double getPoid() {
        return poid;
    }

    public Surveillance poid(Double poid) {
        this.poid = poid;
        return this;
    }

    public void setPoid(Double poid) {
        this.poid = poid;
    }

    public Double getUfnet() {
        return ufnet;
    }

    public Surveillance ufnet(Double ufnet) {
        this.ufnet = ufnet;
        return this;
    }

    public void setUfnet(Double ufnet) {
        this.ufnet = ufnet;
    }

    public Integer getEtatConscience() {
        return etatConscience;
    }

    public Surveillance etatConscience(Integer etatConscience) {
        this.etatConscience = etatConscience;
        return this;
    }

    public void setEtatConscience(Integer etatConscience) {
        this.etatConscience = etatConscience;
    }

    public Integer getEupneique() {
        return eupneique;
    }

    public Surveillance eupneique(Integer eupneique) {
        this.eupneique = eupneique;
        return this;
    }

    public void setEupneique(Integer eupneique) {
        this.eupneique = eupneique;
    }

    public Integer getRestitutionPar() {
        return restitutionPar;
    }

    public Surveillance restitutionPar(Integer restitutionPar) {
        this.restitutionPar = restitutionPar;
        return this;
    }

    public void setRestitutionPar(Integer restitutionPar) {
        this.restitutionPar = restitutionPar;
    }

    public String getAutreComplication() {
        return autreComplication;
    }

    public Surveillance autreComplication(String autreComplication) {
        this.autreComplication = autreComplication;
        return this;
    }

    public void setAutreComplication(String autreComplication) {
        this.autreComplication = autreComplication;
    }

    public String getObservation() {
        return observation;
    }

    public Surveillance observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public TraitementPerdialyse getTraitement() {
        return traitement;
    }

    public Surveillance traitement(TraitementPerdialyse traitementPerdialyse) {
        this.traitement = traitementPerdialyse;
        return this;
    }

    public void setTraitement(TraitementPerdialyse traitementPerdialyse) {
        this.traitement = traitementPerdialyse;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Surveillance)) {
            return false;
        }
        return id != null && id.equals(((Surveillance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Surveillance{" +
            "id=" + getId() +
            ", infirmier=" + getInfirmier() +
            ", poste=" + getPoste() +
            ", generateur=" + getGenerateur() +
            ", statut='" + getStatut() + "'" +
            ", poid=" + getPoid() +
            ", ufnet=" + getUfnet() +
            ", etatConscience=" + getEtatConscience() +
            ", eupneique=" + getEupneique() +
            ", restitutionPar=" + getRestitutionPar() +
            ", autreComplication='" + getAutreComplication() + "'" +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
