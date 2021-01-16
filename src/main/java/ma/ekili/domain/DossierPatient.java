package ma.ekili.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DossierPatient.
 */
@Entity
@Table(name = "dossier_patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DossierPatient implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ip", nullable = false, unique = true)
    private Long ip;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "genre", nullable = false)
    private Integer genre;

    @Column(name = "tel")
    private String tel;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "amo")
    private Integer amo;

    @Column(name = "type_centre_origine")
    private Integer typeCentreOrigine;

    @Column(name = "ville_centre_origine")
    private Integer villeCentreOrigine;

    @Column(name = "observation")
    private String observation;

    @Column(name = "naissance")
    private LocalDate naissance;

    @OneToOne
    @JoinColumn(unique = true)
    private Antecedent antecedent;

    @OneToOne
    @JoinColumn(unique = true)
    private Diagnostic diagnostic;

    @OneToOne
    @JoinColumn(unique = true)
    private IndicationHd indicationHd;

    @OneToOne
    @JoinColumn(unique = true)
    private ExamenClinique examenClinique;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIp() {
        return ip;
    }

    public DossierPatient ip(Long ip) {
        this.ip = ip;
        return this;
    }

    public void setIp(Long ip) {
        this.ip = ip;
    }

    public String getNom() {
        return nom;
    }

    public DossierPatient nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public DossierPatient prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getGenre() {
        return genre;
    }

    public DossierPatient genre(Integer genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public String getTel() {
        return tel;
    }

    public DossierPatient tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public DossierPatient adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getAmo() {
        return amo;
    }

    public DossierPatient amo(Integer amo) {
        this.amo = amo;
        return this;
    }

    public void setAmo(Integer amo) {
        this.amo = amo;
    }

    public Integer getTypeCentreOrigine() {
        return typeCentreOrigine;
    }

    public DossierPatient typeCentreOrigine(Integer typeCentreOrigine) {
        this.typeCentreOrigine = typeCentreOrigine;
        return this;
    }

    public void setTypeCentreOrigine(Integer typeCentreOrigine) {
        this.typeCentreOrigine = typeCentreOrigine;
    }

    public Integer getVilleCentreOrigine() {
        return villeCentreOrigine;
    }

    public DossierPatient villeCentreOrigine(Integer villeCentreOrigine) {
        this.villeCentreOrigine = villeCentreOrigine;
        return this;
    }

    public void setVilleCentreOrigine(Integer villeCentreOrigine) {
        this.villeCentreOrigine = villeCentreOrigine;
    }

    public String getObservation() {
        return observation;
    }

    public DossierPatient observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public DossierPatient naissance(LocalDate naissance) {
        this.naissance = naissance;
        return this;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public Antecedent getAntecedent() {
        return antecedent;
    }

    public DossierPatient antecedent(Antecedent antecedent) {
        this.antecedent = antecedent;
        return this;
    }

    public void setAntecedent(Antecedent antecedent) {
        this.antecedent = antecedent;
    }

    public Diagnostic getDiagnostic() {
        return diagnostic;
    }

    public DossierPatient diagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
        return this;
    }

    public void setDiagnostic(Diagnostic diagnostic) {
        this.diagnostic = diagnostic;
    }

    public IndicationHd getIndicationHd() {
        return indicationHd;
    }

    public DossierPatient indicationHd(IndicationHd indicationHd) {
        this.indicationHd = indicationHd;
        return this;
    }

    public void setIndicationHd(IndicationHd indicationHd) {
        this.indicationHd = indicationHd;
    }

    public ExamenClinique getExamenClinique() {
        return examenClinique;
    }

    public DossierPatient examenClinique(ExamenClinique examenClinique) {
        this.examenClinique = examenClinique;
        return this;
    }

    public void setExamenClinique(ExamenClinique examenClinique) {
        this.examenClinique = examenClinique;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DossierPatient)) {
            return false;
        }
        return id != null && id.equals(((DossierPatient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DossierPatient{" +
            "id=" + getId() +
            ", ip=" + getIp() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", genre=" + getGenre() +
            ", tel='" + getTel() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", amo=" + getAmo() +
            ", typeCentreOrigine=" + getTypeCentreOrigine() +
            ", villeCentreOrigine=" + getVilleCentreOrigine() +
            ", observation='" + getObservation() + "'" +
            ", naissance='" + getNaissance() + "'" +
            "}";
    }
}
