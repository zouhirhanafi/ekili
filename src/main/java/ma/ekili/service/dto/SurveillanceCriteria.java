package ma.ekili.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;
import ma.ekili.domain.enumeration.StatutSurveillance;

/**
 * Criteria class for the {@link ma.ekili.domain.Surveillance} entity. This class is used
 * in {@link ma.ekili.web.rest.SurveillanceResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /surveillances?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SurveillanceCriteria implements Serializable, Criteria {

    /**
     * Class for filtering StatutSurveillance
     */
    public static class StatutSurveillanceFilter extends Filter<StatutSurveillance> {

        public StatutSurveillanceFilter() {}

        public StatutSurveillanceFilter(StatutSurveillanceFilter filter) {
            super(filter);
        }

        @Override
        public StatutSurveillanceFilter copy() {
            return new StatutSurveillanceFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter infirmier;

    private IntegerFilter poste;

    private IntegerFilter generateur;

    private StatutSurveillanceFilter statut;

    private DoubleFilter poid;

    private DoubleFilter ufnet;

    private IntegerFilter etatConscience;

    private IntegerFilter eupneique;

    private IntegerFilter restitutionPar;

    private StringFilter autreComplication;

    private StringFilter observation;

    private LongFilter traitementId;

    public SurveillanceCriteria() {}

    public SurveillanceCriteria(SurveillanceCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.infirmier = other.infirmier == null ? null : other.infirmier.copy();
        this.poste = other.poste == null ? null : other.poste.copy();
        this.generateur = other.generateur == null ? null : other.generateur.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.poid = other.poid == null ? null : other.poid.copy();
        this.ufnet = other.ufnet == null ? null : other.ufnet.copy();
        this.etatConscience = other.etatConscience == null ? null : other.etatConscience.copy();
        this.eupneique = other.eupneique == null ? null : other.eupneique.copy();
        this.restitutionPar = other.restitutionPar == null ? null : other.restitutionPar.copy();
        this.autreComplication = other.autreComplication == null ? null : other.autreComplication.copy();
        this.observation = other.observation == null ? null : other.observation.copy();
        this.traitementId = other.traitementId == null ? null : other.traitementId.copy();
    }

    @Override
    public SurveillanceCriteria copy() {
        return new SurveillanceCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getInfirmier() {
        return infirmier;
    }

    public void setInfirmier(IntegerFilter infirmier) {
        this.infirmier = infirmier;
    }

    public IntegerFilter getPoste() {
        return poste;
    }

    public void setPoste(IntegerFilter poste) {
        this.poste = poste;
    }

    public IntegerFilter getGenerateur() {
        return generateur;
    }

    public void setGenerateur(IntegerFilter generateur) {
        this.generateur = generateur;
    }

    public StatutSurveillanceFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutSurveillanceFilter statut) {
        this.statut = statut;
    }

    public DoubleFilter getPoid() {
        return poid;
    }

    public void setPoid(DoubleFilter poid) {
        this.poid = poid;
    }

    public DoubleFilter getUfnet() {
        return ufnet;
    }

    public void setUfnet(DoubleFilter ufnet) {
        this.ufnet = ufnet;
    }

    public IntegerFilter getEtatConscience() {
        return etatConscience;
    }

    public void setEtatConscience(IntegerFilter etatConscience) {
        this.etatConscience = etatConscience;
    }

    public IntegerFilter getEupneique() {
        return eupneique;
    }

    public void setEupneique(IntegerFilter eupneique) {
        this.eupneique = eupneique;
    }

    public IntegerFilter getRestitutionPar() {
        return restitutionPar;
    }

    public void setRestitutionPar(IntegerFilter restitutionPar) {
        this.restitutionPar = restitutionPar;
    }

    public StringFilter getAutreComplication() {
        return autreComplication;
    }

    public void setAutreComplication(StringFilter autreComplication) {
        this.autreComplication = autreComplication;
    }

    public StringFilter getObservation() {
        return observation;
    }

    public void setObservation(StringFilter observation) {
        this.observation = observation;
    }

    public LongFilter getTraitementId() {
        return traitementId;
    }

    public void setTraitementId(LongFilter traitementId) {
        this.traitementId = traitementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SurveillanceCriteria that = (SurveillanceCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(infirmier, that.infirmier) &&
            Objects.equals(poste, that.poste) &&
            Objects.equals(generateur, that.generateur) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(poid, that.poid) &&
            Objects.equals(ufnet, that.ufnet) &&
            Objects.equals(etatConscience, that.etatConscience) &&
            Objects.equals(eupneique, that.eupneique) &&
            Objects.equals(restitutionPar, that.restitutionPar) &&
            Objects.equals(autreComplication, that.autreComplication) &&
            Objects.equals(observation, that.observation) &&
            Objects.equals(traitementId, that.traitementId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            infirmier,
            poste,
            generateur,
            statut,
            poid,
            ufnet,
            etatConscience,
            eupneique,
            restitutionPar,
            autreComplication,
            observation,
            traitementId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SurveillanceCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (infirmier != null ? "infirmier=" + infirmier + ", " : "") +
                (poste != null ? "poste=" + poste + ", " : "") +
                (generateur != null ? "generateur=" + generateur + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (poid != null ? "poid=" + poid + ", " : "") +
                (ufnet != null ? "ufnet=" + ufnet + ", " : "") +
                (etatConscience != null ? "etatConscience=" + etatConscience + ", " : "") +
                (eupneique != null ? "eupneique=" + eupneique + ", " : "") +
                (restitutionPar != null ? "restitutionPar=" + restitutionPar + ", " : "") +
                (autreComplication != null ? "autreComplication=" + autreComplication + ", " : "") +
                (observation != null ? "observation=" + observation + ", " : "") +
                (traitementId != null ? "traitementId=" + traitementId + ", " : "") +
            "}";
    }
}
