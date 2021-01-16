package ma.ekili.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import ma.ekili.domain.enumeration.StatutPrescription;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Prescription.
 */
@Entity
@Table(name = "prescription")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prescription implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "duree")
    private Integer duree;

    @Column(name = "capillaire")
    private Integer capillaire;

    @Column(name = "restitution_p")
    private Integer restitutionP;

    @Column(name = "niveau_urgence")
    private Integer niveauUrgence;

    @Column(name = "uf_totale")
    private Double ufTotale;

    @Column(name = "rincage")
    private Integer rincage;

    @Column(name = "transfusion")
    private Integer transfusion;

    @Column(name = "date_planification")
    private LocalDate datePlanification;

    @Column(name = "circuit")
    private Integer circuit;

    @Column(name = "abord_vasculaire")
    private Integer abordVasculaire;

    @Column(name = "profil")
    private Integer profil;

    @Column(name = "conductivite_p")
    private Double conductiviteP;

    @Column(name = "debit_pompe")
    private Double debitPompe;

    @Column(name = "temperature_dialysat")
    private Double temperatureDialysat;

    @Column(name = "atc")
    private Boolean atc;

    @Column(name = "hnfh_0")
    private Double hnfh0;

    @Column(name = "hnfh_2")
    private Double hnfh2;

    @Column(name = "hbpm")
    private Double hbpm;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut")
    private StatutPrescription statut;

    @Column(name = "motif_annulation")
    private Integer motifAnnulation;

    @Column(name = "motif_report")
    private Integer motifReport;

    @Column(name = "observation_p")
    private String observationP;

    @OneToOne
    @JoinColumn(unique = true)
    private TraitementPerdialyse traitement;

    @OneToOne
    @JoinColumn(unique = true)
    private Surveillance surveillance;

    @ManyToOne
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private DossierPatient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuree() {
        return duree;
    }

    public Prescription duree(Integer duree) {
        this.duree = duree;
        return this;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getCapillaire() {
        return capillaire;
    }

    public Prescription capillaire(Integer capillaire) {
        this.capillaire = capillaire;
        return this;
    }

    public void setCapillaire(Integer capillaire) {
        this.capillaire = capillaire;
    }

    public Integer getRestitutionP() {
        return restitutionP;
    }

    public Prescription restitutionP(Integer restitutionP) {
        this.restitutionP = restitutionP;
        return this;
    }

    public void setRestitutionP(Integer restitutionP) {
        this.restitutionP = restitutionP;
    }

    public Integer getNiveauUrgence() {
        return niveauUrgence;
    }

    public Prescription niveauUrgence(Integer niveauUrgence) {
        this.niveauUrgence = niveauUrgence;
        return this;
    }

    public void setNiveauUrgence(Integer niveauUrgence) {
        this.niveauUrgence = niveauUrgence;
    }

    public Double getUfTotale() {
        return ufTotale;
    }

    public Prescription ufTotale(Double ufTotale) {
        this.ufTotale = ufTotale;
        return this;
    }

    public void setUfTotale(Double ufTotale) {
        this.ufTotale = ufTotale;
    }

    public Integer getRincage() {
        return rincage;
    }

    public Prescription rincage(Integer rincage) {
        this.rincage = rincage;
        return this;
    }

    public void setRincage(Integer rincage) {
        this.rincage = rincage;
    }

    public Integer getTransfusion() {
        return transfusion;
    }

    public Prescription transfusion(Integer transfusion) {
        this.transfusion = transfusion;
        return this;
    }

    public void setTransfusion(Integer transfusion) {
        this.transfusion = transfusion;
    }

    public LocalDate getDatePlanification() {
        return datePlanification;
    }

    public Prescription datePlanification(LocalDate datePlanification) {
        this.datePlanification = datePlanification;
        return this;
    }

    public void setDatePlanification(LocalDate datePlanification) {
        this.datePlanification = datePlanification;
    }

    public Integer getCircuit() {
        return circuit;
    }

    public Prescription circuit(Integer circuit) {
        this.circuit = circuit;
        return this;
    }

    public void setCircuit(Integer circuit) {
        this.circuit = circuit;
    }

    public Integer getAbordVasculaire() {
        return abordVasculaire;
    }

    public Prescription abordVasculaire(Integer abordVasculaire) {
        this.abordVasculaire = abordVasculaire;
        return this;
    }

    public void setAbordVasculaire(Integer abordVasculaire) {
        this.abordVasculaire = abordVasculaire;
    }

    public Integer getProfil() {
        return profil;
    }

    public Prescription profil(Integer profil) {
        this.profil = profil;
        return this;
    }

    public void setProfil(Integer profil) {
        this.profil = profil;
    }

    public Double getConductiviteP() {
        return conductiviteP;
    }

    public Prescription conductiviteP(Double conductiviteP) {
        this.conductiviteP = conductiviteP;
        return this;
    }

    public void setConductiviteP(Double conductiviteP) {
        this.conductiviteP = conductiviteP;
    }

    public Double getDebitPompe() {
        return debitPompe;
    }

    public Prescription debitPompe(Double debitPompe) {
        this.debitPompe = debitPompe;
        return this;
    }

    public void setDebitPompe(Double debitPompe) {
        this.debitPompe = debitPompe;
    }

    public Double getTemperatureDialysat() {
        return temperatureDialysat;
    }

    public Prescription temperatureDialysat(Double temperatureDialysat) {
        this.temperatureDialysat = temperatureDialysat;
        return this;
    }

    public void setTemperatureDialysat(Double temperatureDialysat) {
        this.temperatureDialysat = temperatureDialysat;
    }

    public Boolean isAtc() {
        return atc;
    }

    public Prescription atc(Boolean atc) {
        this.atc = atc;
        return this;
    }

    public void setAtc(Boolean atc) {
        this.atc = atc;
    }

    public Double getHnfh0() {
        return hnfh0;
    }

    public Prescription hnfh0(Double hnfh0) {
        this.hnfh0 = hnfh0;
        return this;
    }

    public void setHnfh0(Double hnfh0) {
        this.hnfh0 = hnfh0;
    }

    public Double getHnfh2() {
        return hnfh2;
    }

    public Prescription hnfh2(Double hnfh2) {
        this.hnfh2 = hnfh2;
        return this;
    }

    public void setHnfh2(Double hnfh2) {
        this.hnfh2 = hnfh2;
    }

    public Double getHbpm() {
        return hbpm;
    }

    public Prescription hbpm(Double hbpm) {
        this.hbpm = hbpm;
        return this;
    }

    public void setHbpm(Double hbpm) {
        this.hbpm = hbpm;
    }

    public StatutPrescription getStatut() {
        return statut;
    }

    public Prescription statut(StatutPrescription statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(StatutPrescription statut) {
        this.statut = statut;
    }

    public Integer getMotifAnnulation() {
        return motifAnnulation;
    }

    public Prescription motifAnnulation(Integer motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
        return this;
    }

    public void setMotifAnnulation(Integer motifAnnulation) {
        this.motifAnnulation = motifAnnulation;
    }

    public Integer getMotifReport() {
        return motifReport;
    }

    public Prescription motifReport(Integer motifReport) {
        this.motifReport = motifReport;
        return this;
    }

    public void setMotifReport(Integer motifReport) {
        this.motifReport = motifReport;
    }

    public String getObservationP() {
        return observationP;
    }

    public Prescription observationP(String observationP) {
        this.observationP = observationP;
        return this;
    }

    public void setObservationP(String observationP) {
        this.observationP = observationP;
    }

    public TraitementPerdialyse getTraitement() {
        return traitement;
    }

    public Prescription traitement(TraitementPerdialyse traitementPerdialyse) {
        this.traitement = traitementPerdialyse;
        return this;
    }

    public void setTraitement(TraitementPerdialyse traitementPerdialyse) {
        this.traitement = traitementPerdialyse;
    }

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public Prescription surveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
        return this;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public DossierPatient getPatient() {
        return patient;
    }

    public Prescription patient(DossierPatient dossierPatient) {
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
        if (!(o instanceof Prescription)) {
            return false;
        }
        return id != null && id.equals(((Prescription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prescription{" +
            "id=" + getId() +
            ", duree=" + getDuree() +
            ", capillaire=" + getCapillaire() +
            ", restitutionP=" + getRestitutionP() +
            ", niveauUrgence=" + getNiveauUrgence() +
            ", ufTotale=" + getUfTotale() +
            ", rincage=" + getRincage() +
            ", transfusion=" + getTransfusion() +
            ", datePlanification='" + getDatePlanification() + "'" +
            ", circuit=" + getCircuit() +
            ", abordVasculaire=" + getAbordVasculaire() +
            ", profil=" + getProfil() +
            ", conductiviteP=" + getConductiviteP() +
            ", debitPompe=" + getDebitPompe() +
            ", temperatureDialysat=" + getTemperatureDialysat() +
            ", atc='" + isAtc() + "'" +
            ", hnfh0=" + getHnfh0() +
            ", hnfh2=" + getHnfh2() +
            ", hbpm=" + getHbpm() +
            ", statut='" + getStatut() + "'" +
            ", motifAnnulation=" + getMotifAnnulation() +
            ", motifReport=" + getMotifReport() +
            ", observationP='" + getObservationP() + "'" +
            "}";
    }
}
