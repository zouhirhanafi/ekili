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
import ma.ekili.domain.enumeration.StatutPrescription;

/**
 * Criteria class for the {@link ma.ekili.domain.Prescription} entity. This class is used
 * in {@link ma.ekili.web.rest.PrescriptionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /prescriptions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PrescriptionCriteria implements Serializable, Criteria {

    /**
     * Class for filtering StatutPrescription
     */
    public static class StatutPrescriptionFilter extends Filter<StatutPrescription> {

        public StatutPrescriptionFilter() {}

        public StatutPrescriptionFilter(StatutPrescriptionFilter filter) {
            super(filter);
        }

        @Override
        public StatutPrescriptionFilter copy() {
            return new StatutPrescriptionFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter duree;

    private IntegerFilter capillaire;

    private IntegerFilter restitutionP;

    private IntegerFilter niveauUrgence;

    private DoubleFilter ufTotale;

    private IntegerFilter rincage;

    private IntegerFilter transfusion;

    private LocalDateFilter datePlanification;

    private IntegerFilter circuit;

    private IntegerFilter abordVasculaire;

    private IntegerFilter profil;

    private DoubleFilter conductiviteP;

    private DoubleFilter debitPompe;

    private DoubleFilter temperatureDialysat;

    private BooleanFilter atc;

    private DoubleFilter hnfh0;

    private DoubleFilter hnfh2;

    private DoubleFilter hbpm;

    private StatutPrescriptionFilter statut;

    private IntegerFilter motifAnnulation;

    private IntegerFilter motifReport;

    private StringFilter observationP;

    private LongFilter traitementId;

    private LongFilter surveillanceId;

    private LongFilter patientId;

    public PrescriptionCriteria() {}

    public PrescriptionCriteria(PrescriptionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.duree = other.duree == null ? null : other.duree.copy();
        this.capillaire = other.capillaire == null ? null : other.capillaire.copy();
        this.restitutionP = other.restitutionP == null ? null : other.restitutionP.copy();
        this.niveauUrgence = other.niveauUrgence == null ? null : other.niveauUrgence.copy();
        this.ufTotale = other.ufTotale == null ? null : other.ufTotale.copy();
        this.rincage = other.rincage == null ? null : other.rincage.copy();
        this.transfusion = other.transfusion == null ? null : other.transfusion.copy();
        this.datePlanification = other.datePlanification == null ? null : other.datePlanification.copy();
        this.circuit = other.circuit == null ? null : other.circuit.copy();
        this.abordVasculaire = other.abordVasculaire == null ? null : other.abordVasculaire.copy();
        this.profil = other.profil == null ? null : other.profil.copy();
        this.conductiviteP = other.conductiviteP == null ? null : other.conductiviteP.copy();
        this.debitPompe = other.debitPompe == null ? null : other.debitPompe.copy();
        this.temperatureDialysat = other.temperatureDialysat == null ? null : other.temperatureDialysat.copy();
        this.atc = other.atc == null ? null : other.atc.copy();
        this.hnfh0 = other.hnfh0 == null ? null : other.hnfh0.copy();
        this.hnfh2 = other.hnfh2 == null ? null : other.hnfh2.copy();
        this.hbpm = other.hbpm == null ? null : other.hbpm.copy();
        this.statut = other.statut == null ? null : other.statut.copy();
        this.motifAnnulation = other.motifAnnulation == null ? null : other.motifAnnulation.copy();
        this.motifReport = other.motifReport == null ? null : other.motifReport.copy();
        this.observationP = other.observationP == null ? null : other.observationP.copy();
        this.traitementId = other.traitementId == null ? null : other.traitementId.copy();
        this.surveillanceId = other.surveillanceId == null ? null : other.surveillanceId.copy();
        this.patientId = other.patientId == null ? null : other.patientId.copy();
    }

    @Override
    public PrescriptionCriteria copy() {
        return new PrescriptionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getDuree() {
        return duree;
    }

    public void setDuree(IntegerFilter duree) {
        this.duree = duree;
    }

    public IntegerFilter getCapillaire() {
        return capillaire;
    }

    public void setCapillaire(IntegerFilter capillaire) {
        this.capillaire = capillaire;
    }

    public IntegerFilter getRestitutionP() {
        return restitutionP;
    }

    public void setRestitutionP(IntegerFilter restitutionP) {
        this.restitutionP = restitutionP;
    }

    public IntegerFilter getNiveauUrgence() {
        return niveauUrgence;
    }

    public void setNiveauUrgence(IntegerFilter niveauUrgence) {
        this.niveauUrgence = niveauUrgence;
    }

    public DoubleFilter getUfTotale() {
        return ufTotale;
    }

    public void setUfTotale(DoubleFilter ufTotale) {
        this.ufTotale = ufTotale;
    }

    public IntegerFilter getRincage() {
        return rincage;
    }

    public void setRincage(IntegerFilter rincage) {
        this.rincage = rincage;
    }

    public IntegerFilter getTransfusion() {
        return transfusion;
    }

    public void setTransfusion(IntegerFilter transfusion) {
        this.transfusion = transfusion;
    }

    public LocalDateFilter getDatePlanification() {
        return datePlanification;
    }

    public void setDatePlanification(LocalDateFilter datePlanification) {
        this.datePlanification = datePlanification;
    }

    public IntegerFilter getCircuit() {
        return circuit;
    }

    public void setCircuit(IntegerFilter circuit) {
        this.circuit = circuit;
    }

    public IntegerFilter getAbordVasculaire() {
        return abordVasculaire;
    }

    public void setAbordVasculaire(IntegerFilter abordVasculaire) {
        this.abordVasculaire = abordVasculaire;
    }

    public IntegerFilter getProfil() {
        return profil;
    }

    public void setProfil(IntegerFilter profil) {
        this.profil = profil;
    }

    public DoubleFilter getConductiviteP() {
        return conductiviteP;
    }

    public void setConductiviteP(DoubleFilter conductiviteP) {
        this.conductiviteP = conductiviteP;
    }

    public DoubleFilter getDebitPompe() {
        return debitPompe;
    }

    public void setDebitPompe(DoubleFilter debitPompe) {
        this.debitPompe = debitPompe;
    }

    public DoubleFilter getTemperatureDialysat() {
        return temperatureDialysat;
    }

    public void setTemperatureDialysat(DoubleFilter temperatureDialysat) {
        this.temperatureDialysat = temperatureDialysat;
    }

    public BooleanFilter getAtc() {
        return atc;
    }

    public void setAtc(BooleanFilter atc) {
        this.atc = atc;
    }

    public DoubleFilter getHnfh0() {
        return hnfh0;
    }

    public void setHnfh0(DoubleFilter hnfh0) {
        this.hnfh0 = hnfh0;
    }

    public DoubleFilter getHnfh2() {
        return hnfh2;
    }

    public void setHnfh2(DoubleFilter hnfh2) {
        this.hnfh2 = hnfh2;
    }

    public DoubleFilter getHbpm() {
        return hbpm;
    }

    public void setHbpm(DoubleFilter hbpm) {
        this.hbpm = hbpm;
    }

    public StatutPrescriptionFilter getStatut() {
        return statut;
    }

    public void setStatut(StatutPrescriptionFilter statut) {
        this.statut = statut;
    }

    public IntegerFilter getMotifAnnulation() {
        return motifAnnulation;
    }

    public void setMotifAnnulation(IntegerFilter motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    public IntegerFilter getMotifReport() {
        return motifReport;
    }

    public void setMotifReport(IntegerFilter motifReport) {
        this.motifReport = motifReport;
    }

    public StringFilter getObservationP() {
        return observationP;
    }

    public void setObservationP(StringFilter observationP) {
        this.observationP = observationP;
    }

    public LongFilter getTraitementId() {
        return traitementId;
    }

    public void setTraitementId(LongFilter traitementId) {
        this.traitementId = traitementId;
    }

    public LongFilter getSurveillanceId() {
        return surveillanceId;
    }

    public void setSurveillanceId(LongFilter surveillanceId) {
        this.surveillanceId = surveillanceId;
    }

    public LongFilter getPatientId() {
        return patientId;
    }

    public void setPatientId(LongFilter patientId) {
        this.patientId = patientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PrescriptionCriteria that = (PrescriptionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(duree, that.duree) &&
            Objects.equals(capillaire, that.capillaire) &&
            Objects.equals(restitutionP, that.restitutionP) &&
            Objects.equals(niveauUrgence, that.niveauUrgence) &&
            Objects.equals(ufTotale, that.ufTotale) &&
            Objects.equals(rincage, that.rincage) &&
            Objects.equals(transfusion, that.transfusion) &&
            Objects.equals(datePlanification, that.datePlanification) &&
            Objects.equals(circuit, that.circuit) &&
            Objects.equals(abordVasculaire, that.abordVasculaire) &&
            Objects.equals(profil, that.profil) &&
            Objects.equals(conductiviteP, that.conductiviteP) &&
            Objects.equals(debitPompe, that.debitPompe) &&
            Objects.equals(temperatureDialysat, that.temperatureDialysat) &&
            Objects.equals(atc, that.atc) &&
            Objects.equals(hnfh0, that.hnfh0) &&
            Objects.equals(hnfh2, that.hnfh2) &&
            Objects.equals(hbpm, that.hbpm) &&
            Objects.equals(statut, that.statut) &&
            Objects.equals(motifAnnulation, that.motifAnnulation) &&
            Objects.equals(motifReport, that.motifReport) &&
            Objects.equals(observationP, that.observationP) &&
            Objects.equals(traitementId, that.traitementId) &&
            Objects.equals(surveillanceId, that.surveillanceId) &&
            Objects.equals(patientId, that.patientId)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            duree,
            capillaire,
            restitutionP,
            niveauUrgence,
            ufTotale,
            rincage,
            transfusion,
            datePlanification,
            circuit,
            abordVasculaire,
            profil,
            conductiviteP,
            debitPompe,
            temperatureDialysat,
            atc,
            hnfh0,
            hnfh2,
            hbpm,
            statut,
            motifAnnulation,
            motifReport,
            observationP,
            traitementId,
            surveillanceId,
            patientId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrescriptionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (duree != null ? "duree=" + duree + ", " : "") +
                (capillaire != null ? "capillaire=" + capillaire + ", " : "") +
                (restitutionP != null ? "restitutionP=" + restitutionP + ", " : "") +
                (niveauUrgence != null ? "niveauUrgence=" + niveauUrgence + ", " : "") +
                (ufTotale != null ? "ufTotale=" + ufTotale + ", " : "") +
                (rincage != null ? "rincage=" + rincage + ", " : "") +
                (transfusion != null ? "transfusion=" + transfusion + ", " : "") +
                (datePlanification != null ? "datePlanification=" + datePlanification + ", " : "") +
                (circuit != null ? "circuit=" + circuit + ", " : "") +
                (abordVasculaire != null ? "abordVasculaire=" + abordVasculaire + ", " : "") +
                (profil != null ? "profil=" + profil + ", " : "") +
                (conductiviteP != null ? "conductiviteP=" + conductiviteP + ", " : "") +
                (debitPompe != null ? "debitPompe=" + debitPompe + ", " : "") +
                (temperatureDialysat != null ? "temperatureDialysat=" + temperatureDialysat + ", " : "") +
                (atc != null ? "atc=" + atc + ", " : "") +
                (hnfh0 != null ? "hnfh0=" + hnfh0 + ", " : "") +
                (hnfh2 != null ? "hnfh2=" + hnfh2 + ", " : "") +
                (hbpm != null ? "hbpm=" + hbpm + ", " : "") +
                (statut != null ? "statut=" + statut + ", " : "") +
                (motifAnnulation != null ? "motifAnnulation=" + motifAnnulation + ", " : "") +
                (motifReport != null ? "motifReport=" + motifReport + ", " : "") +
                (observationP != null ? "observationP=" + observationP + ", " : "") +
                (traitementId != null ? "traitementId=" + traitementId + ", " : "") +
                (surveillanceId != null ? "surveillanceId=" + surveillanceId + ", " : "") +
                (patientId != null ? "patientId=" + patientId + ", " : "") +
            "}";
    }
}
