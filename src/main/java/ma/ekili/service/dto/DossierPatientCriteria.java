package ma.ekili.service.dto;

import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LocalDateFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import java.io.Serializable;
import java.util.Objects;

/**
 * Criteria class for the {@link ma.ekili.domain.DossierPatient} entity. This class is used
 * in {@link ma.ekili.web.rest.DossierPatientResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /dossier-patients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DossierPatientCriteria implements Serializable, Criteria {
    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter ip;

    private StringFilter nom;

    private StringFilter prenom;

    private IntegerFilter genre;

    private StringFilter tel;

    private StringFilter adresse;

    private IntegerFilter amo;

    private IntegerFilter typeCentreOrigine;

    private IntegerFilter villeCentreOrigine;

    private StringFilter observation;

    private LocalDateFilter naissance;

    private LongFilter antecedentId;

    private LongFilter diagnosticId;

    private LongFilter indicationHdId;

    private LongFilter examenCliniqueId;

    public DossierPatientCriteria() {}

    public DossierPatientCriteria(DossierPatientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ip = other.ip == null ? null : other.ip.copy();
        this.nom = other.nom == null ? null : other.nom.copy();
        this.prenom = other.prenom == null ? null : other.prenom.copy();
        this.genre = other.genre == null ? null : other.genre.copy();
        this.tel = other.tel == null ? null : other.tel.copy();
        this.adresse = other.adresse == null ? null : other.adresse.copy();
        this.amo = other.amo == null ? null : other.amo.copy();
        this.typeCentreOrigine = other.typeCentreOrigine == null ? null : other.typeCentreOrigine.copy();
        this.villeCentreOrigine = other.villeCentreOrigine == null ? null : other.villeCentreOrigine.copy();
        this.observation = other.observation == null ? null : other.observation.copy();
        this.naissance = other.naissance == null ? null : other.naissance.copy();
        this.antecedentId = other.antecedentId == null ? null : other.antecedentId.copy();
        this.diagnosticId = other.diagnosticId == null ? null : other.diagnosticId.copy();
        this.indicationHdId = other.indicationHdId == null ? null : other.indicationHdId.copy();
        this.examenCliniqueId = other.examenCliniqueId == null ? null : other.examenCliniqueId.copy();
    }

    @Override
    public DossierPatientCriteria copy() {
        return new DossierPatientCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getIp() {
        return ip;
    }

    public void setIp(LongFilter ip) {
        this.ip = ip;
    }

    public StringFilter getNom() {
        return nom;
    }

    public void setNom(StringFilter nom) {
        this.nom = nom;
    }

    public StringFilter getPrenom() {
        return prenom;
    }

    public void setPrenom(StringFilter prenom) {
        this.prenom = prenom;
    }

    public IntegerFilter getGenre() {
        return genre;
    }

    public void setGenre(IntegerFilter genre) {
        this.genre = genre;
    }

    public StringFilter getTel() {
        return tel;
    }

    public void setTel(StringFilter tel) {
        this.tel = tel;
    }

    public StringFilter getAdresse() {
        return adresse;
    }

    public void setAdresse(StringFilter adresse) {
        this.adresse = adresse;
    }

    public IntegerFilter getAmo() {
        return amo;
    }

    public void setAmo(IntegerFilter amo) {
        this.amo = amo;
    }

    public IntegerFilter getTypeCentreOrigine() {
        return typeCentreOrigine;
    }

    public void setTypeCentreOrigine(IntegerFilter typeCentreOrigine) {
        this.typeCentreOrigine = typeCentreOrigine;
    }

    public IntegerFilter getVilleCentreOrigine() {
        return villeCentreOrigine;
    }

    public void setVilleCentreOrigine(IntegerFilter villeCentreOrigine) {
        this.villeCentreOrigine = villeCentreOrigine;
    }

    public StringFilter getObservation() {
        return observation;
    }

    public void setObservation(StringFilter observation) {
        this.observation = observation;
    }

    public LocalDateFilter getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDateFilter naissance) {
        this.naissance = naissance;
    }

    public LongFilter getAntecedentId() {
        return antecedentId;
    }

    public void setAntecedentId(LongFilter antecedentId) {
        this.antecedentId = antecedentId;
    }

    public LongFilter getDiagnosticId() {
        return diagnosticId;
    }

    public void setDiagnosticId(LongFilter diagnosticId) {
        this.diagnosticId = diagnosticId;
    }

    public LongFilter getIndicationHdId() {
        return indicationHdId;
    }

    public void setIndicationHdId(LongFilter indicationHdId) {
        this.indicationHdId = indicationHdId;
    }

    public LongFilter getExamenCliniqueId() {
        return examenCliniqueId;
    }

    public void setExamenCliniqueId(LongFilter examenCliniqueId) {
        this.examenCliniqueId = examenCliniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DossierPatientCriteria that = (DossierPatientCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(ip, that.ip) &&
            Objects.equals(nom, that.nom) &&
            Objects.equals(prenom, that.prenom) &&
            Objects.equals(genre, that.genre) &&
            Objects.equals(tel, that.tel) &&
            Objects.equals(adresse, that.adresse) &&
            Objects.equals(amo, that.amo) &&
            Objects.equals(typeCentreOrigine, that.typeCentreOrigine) &&
            Objects.equals(villeCentreOrigine, that.villeCentreOrigine) &&
            Objects.equals(observation, that.observation) &&
            Objects.equals(naissance, that.naissance) &&
            Objects.equals(antecedentId, that.antecedentId) &&
            Objects.equals(diagnosticId, that.diagnosticId) &&
            Objects.equals(indicationHdId, that.indicationHdId) &&
            Objects.equals(examenCliniqueId, that.examenCliniqueId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            ip,
            nom,
            prenom,
            genre,
            tel,
            adresse,
            amo,
            typeCentreOrigine,
            villeCentreOrigine,
            observation,
            naissance,
            antecedentId,
            diagnosticId,
            indicationHdId,
            examenCliniqueId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DossierPatientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ip != null ? "ip=" + ip + ", " : "") +
                (nom != null ? "nom=" + nom + ", " : "") +
                (prenom != null ? "prenom=" + prenom + ", " : "") +
                (genre != null ? "genre=" + genre + ", " : "") +
                (tel != null ? "tel=" + tel + ", " : "") +
                (adresse != null ? "adresse=" + adresse + ", " : "") +
                (amo != null ? "amo=" + amo + ", " : "") +
                (typeCentreOrigine != null ? "typeCentreOrigine=" + typeCentreOrigine + ", " : "") +
                (villeCentreOrigine != null ? "villeCentreOrigine=" + villeCentreOrigine + ", " : "") +
                (observation != null ? "observation=" + observation + ", " : "") +
                (naissance != null ? "naissance=" + naissance + ", " : "") +
                (antecedentId != null ? "antecedentId=" + antecedentId + ", " : "") +
                (diagnosticId != null ? "diagnosticId=" + diagnosticId + ", " : "") +
                (indicationHdId != null ? "indicationHdId=" + indicationHdId + ", " : "") +
                (examenCliniqueId != null ? "examenCliniqueId=" + examenCliniqueId + ", " : "") +
            "}";
    }
}
