package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.DossierPatient;
import ma.ekili.domain.Prescription;
import ma.ekili.domain.Surveillance;
import ma.ekili.domain.TraitementPerdialyse;
import ma.ekili.domain.enumeration.StatutPrescription;
import ma.ekili.repository.PrescriptionRepository;
import ma.ekili.service.PrescriptionQueryService;
import ma.ekili.service.PrescriptionService;
import ma.ekili.service.dto.PrescriptionCriteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PrescriptionResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrescriptionResourceIT {
    private static final Integer DEFAULT_DUREE = 1;
    private static final Integer UPDATED_DUREE = 2;
    private static final Integer SMALLER_DUREE = 1 - 1;

    private static final Integer DEFAULT_CAPILLAIRE = 1;
    private static final Integer UPDATED_CAPILLAIRE = 2;
    private static final Integer SMALLER_CAPILLAIRE = 1 - 1;

    private static final Integer DEFAULT_RESTITUTION_P = 1;
    private static final Integer UPDATED_RESTITUTION_P = 2;
    private static final Integer SMALLER_RESTITUTION_P = 1 - 1;

    private static final Integer DEFAULT_NIVEAU_URGENCE = 1;
    private static final Integer UPDATED_NIVEAU_URGENCE = 2;
    private static final Integer SMALLER_NIVEAU_URGENCE = 1 - 1;

    private static final Double DEFAULT_UF_TOTALE = 1D;
    private static final Double UPDATED_UF_TOTALE = 2D;
    private static final Double SMALLER_UF_TOTALE = 1D - 1D;

    private static final Integer DEFAULT_RINCAGE = 1;
    private static final Integer UPDATED_RINCAGE = 2;
    private static final Integer SMALLER_RINCAGE = 1 - 1;

    private static final Integer DEFAULT_TRANSFUSION = 1;
    private static final Integer UPDATED_TRANSFUSION = 2;
    private static final Integer SMALLER_TRANSFUSION = 1 - 1;

    private static final LocalDate DEFAULT_DATE_PLANIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PLANIFICATION = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_PLANIFICATION = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_CIRCUIT = 1;
    private static final Integer UPDATED_CIRCUIT = 2;
    private static final Integer SMALLER_CIRCUIT = 1 - 1;

    private static final Integer DEFAULT_ABORD_VASCULAIRE = 1;
    private static final Integer UPDATED_ABORD_VASCULAIRE = 2;
    private static final Integer SMALLER_ABORD_VASCULAIRE = 1 - 1;

    private static final Integer DEFAULT_PROFIL = 1;
    private static final Integer UPDATED_PROFIL = 2;
    private static final Integer SMALLER_PROFIL = 1 - 1;

    private static final Double DEFAULT_CONDUCTIVITE_P = 1D;
    private static final Double UPDATED_CONDUCTIVITE_P = 2D;
    private static final Double SMALLER_CONDUCTIVITE_P = 1D - 1D;

    private static final Double DEFAULT_DEBIT_POMPE = 1D;
    private static final Double UPDATED_DEBIT_POMPE = 2D;
    private static final Double SMALLER_DEBIT_POMPE = 1D - 1D;

    private static final Double DEFAULT_TEMPERATURE_DIALYSAT = 1D;
    private static final Double UPDATED_TEMPERATURE_DIALYSAT = 2D;
    private static final Double SMALLER_TEMPERATURE_DIALYSAT = 1D - 1D;

    private static final Boolean DEFAULT_ATC = false;
    private static final Boolean UPDATED_ATC = true;

    private static final Double DEFAULT_HNFH_0 = 1D;
    private static final Double UPDATED_HNFH_0 = 2D;
    private static final Double SMALLER_HNFH_0 = 1D - 1D;

    private static final Double DEFAULT_HNFH_2 = 1D;
    private static final Double UPDATED_HNFH_2 = 2D;
    private static final Double SMALLER_HNFH_2 = 1D - 1D;

    private static final Double DEFAULT_HBPM = 1D;
    private static final Double UPDATED_HBPM = 2D;
    private static final Double SMALLER_HBPM = 1D - 1D;

    private static final StatutPrescription DEFAULT_STATUT = StatutPrescription.TERMINEE;
    private static final StatutPrescription UPDATED_STATUT = StatutPrescription.AVENIR;

    private static final Integer DEFAULT_MOTIF_ANNULATION = 1;
    private static final Integer UPDATED_MOTIF_ANNULATION = 2;
    private static final Integer SMALLER_MOTIF_ANNULATION = 1 - 1;

    private static final Integer DEFAULT_MOTIF_REPORT = 1;
    private static final Integer UPDATED_MOTIF_REPORT = 2;
    private static final Integer SMALLER_MOTIF_REPORT = 1 - 1;

    private static final String DEFAULT_OBSERVATION_P = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION_P = "BBBBBBBBBB";

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PrescriptionQueryService prescriptionQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPrescriptionMockMvc;

    private Prescription prescription;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prescription createEntity(EntityManager em) {
        Prescription prescription = new Prescription()
            .duree(DEFAULT_DUREE)
            .capillaire(DEFAULT_CAPILLAIRE)
            .restitutionP(DEFAULT_RESTITUTION_P)
            .niveauUrgence(DEFAULT_NIVEAU_URGENCE)
            .ufTotale(DEFAULT_UF_TOTALE)
            .rincage(DEFAULT_RINCAGE)
            .transfusion(DEFAULT_TRANSFUSION)
            .datePlanification(DEFAULT_DATE_PLANIFICATION)
            .circuit(DEFAULT_CIRCUIT)
            .abordVasculaire(DEFAULT_ABORD_VASCULAIRE)
            .profil(DEFAULT_PROFIL)
            .conductiviteP(DEFAULT_CONDUCTIVITE_P)
            .debitPompe(DEFAULT_DEBIT_POMPE)
            .temperatureDialysat(DEFAULT_TEMPERATURE_DIALYSAT)
            .atc(DEFAULT_ATC)
            .hnfh0(DEFAULT_HNFH_0)
            .hnfh2(DEFAULT_HNFH_2)
            .hbpm(DEFAULT_HBPM)
            .statut(DEFAULT_STATUT)
            .motifAnnulation(DEFAULT_MOTIF_ANNULATION)
            .motifReport(DEFAULT_MOTIF_REPORT)
            .observationP(DEFAULT_OBSERVATION_P);
        return prescription;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prescription createUpdatedEntity(EntityManager em) {
        Prescription prescription = new Prescription()
            .duree(UPDATED_DUREE)
            .capillaire(UPDATED_CAPILLAIRE)
            .restitutionP(UPDATED_RESTITUTION_P)
            .niveauUrgence(UPDATED_NIVEAU_URGENCE)
            .ufTotale(UPDATED_UF_TOTALE)
            .rincage(UPDATED_RINCAGE)
            .transfusion(UPDATED_TRANSFUSION)
            .datePlanification(UPDATED_DATE_PLANIFICATION)
            .circuit(UPDATED_CIRCUIT)
            .abordVasculaire(UPDATED_ABORD_VASCULAIRE)
            .profil(UPDATED_PROFIL)
            .conductiviteP(UPDATED_CONDUCTIVITE_P)
            .debitPompe(UPDATED_DEBIT_POMPE)
            .temperatureDialysat(UPDATED_TEMPERATURE_DIALYSAT)
            .atc(UPDATED_ATC)
            .hnfh0(UPDATED_HNFH_0)
            .hnfh2(UPDATED_HNFH_2)
            .hbpm(UPDATED_HBPM)
            .statut(UPDATED_STATUT)
            .motifAnnulation(UPDATED_MOTIF_ANNULATION)
            .motifReport(UPDATED_MOTIF_REPORT)
            .observationP(UPDATED_OBSERVATION_P);
        return prescription;
    }

    @BeforeEach
    public void initTest() {
        prescription = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrescription() throws Exception {
        int databaseSizeBeforeCreate = prescriptionRepository.findAll().size();
        // Create the Prescription
        restPrescriptionMockMvc
            .perform(
                post("/api/prescriptions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(prescription))
            )
            .andExpect(status().isCreated());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeCreate + 1);
        Prescription testPrescription = prescriptionList.get(prescriptionList.size() - 1);
        assertThat(testPrescription.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testPrescription.getCapillaire()).isEqualTo(DEFAULT_CAPILLAIRE);
        assertThat(testPrescription.getRestitutionP()).isEqualTo(DEFAULT_RESTITUTION_P);
        assertThat(testPrescription.getNiveauUrgence()).isEqualTo(DEFAULT_NIVEAU_URGENCE);
        assertThat(testPrescription.getUfTotale()).isEqualTo(DEFAULT_UF_TOTALE);
        assertThat(testPrescription.getRincage()).isEqualTo(DEFAULT_RINCAGE);
        assertThat(testPrescription.getTransfusion()).isEqualTo(DEFAULT_TRANSFUSION);
        assertThat(testPrescription.getDatePlanification()).isEqualTo(DEFAULT_DATE_PLANIFICATION);
        assertThat(testPrescription.getCircuit()).isEqualTo(DEFAULT_CIRCUIT);
        assertThat(testPrescription.getAbordVasculaire()).isEqualTo(DEFAULT_ABORD_VASCULAIRE);
        assertThat(testPrescription.getProfil()).isEqualTo(DEFAULT_PROFIL);
        assertThat(testPrescription.getConductiviteP()).isEqualTo(DEFAULT_CONDUCTIVITE_P);
        assertThat(testPrescription.getDebitPompe()).isEqualTo(DEFAULT_DEBIT_POMPE);
        assertThat(testPrescription.getTemperatureDialysat()).isEqualTo(DEFAULT_TEMPERATURE_DIALYSAT);
        assertThat(testPrescription.isAtc()).isEqualTo(DEFAULT_ATC);
        assertThat(testPrescription.getHnfh0()).isEqualTo(DEFAULT_HNFH_0);
        assertThat(testPrescription.getHnfh2()).isEqualTo(DEFAULT_HNFH_2);
        assertThat(testPrescription.getHbpm()).isEqualTo(DEFAULT_HBPM);
        assertThat(testPrescription.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testPrescription.getMotifAnnulation()).isEqualTo(DEFAULT_MOTIF_ANNULATION);
        assertThat(testPrescription.getMotifReport()).isEqualTo(DEFAULT_MOTIF_REPORT);
        assertThat(testPrescription.getObservationP()).isEqualTo(DEFAULT_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void createPrescriptionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prescriptionRepository.findAll().size();

        // Create the Prescription with an existing ID
        prescription.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrescriptionMockMvc
            .perform(
                post("/api/prescriptions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(prescription))
            )
            .andExpect(status().isBadRequest());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPrescriptions() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList
        restPrescriptionMockMvc
            .perform(get("/api/prescriptions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE)))
            .andExpect(jsonPath("$.[*].capillaire").value(hasItem(DEFAULT_CAPILLAIRE)))
            .andExpect(jsonPath("$.[*].restitutionP").value(hasItem(DEFAULT_RESTITUTION_P)))
            .andExpect(jsonPath("$.[*].niveauUrgence").value(hasItem(DEFAULT_NIVEAU_URGENCE)))
            .andExpect(jsonPath("$.[*].ufTotale").value(hasItem(DEFAULT_UF_TOTALE.doubleValue())))
            .andExpect(jsonPath("$.[*].rincage").value(hasItem(DEFAULT_RINCAGE)))
            .andExpect(jsonPath("$.[*].transfusion").value(hasItem(DEFAULT_TRANSFUSION)))
            .andExpect(jsonPath("$.[*].datePlanification").value(hasItem(DEFAULT_DATE_PLANIFICATION.toString())))
            .andExpect(jsonPath("$.[*].circuit").value(hasItem(DEFAULT_CIRCUIT)))
            .andExpect(jsonPath("$.[*].abordVasculaire").value(hasItem(DEFAULT_ABORD_VASCULAIRE)))
            .andExpect(jsonPath("$.[*].profil").value(hasItem(DEFAULT_PROFIL)))
            .andExpect(jsonPath("$.[*].conductiviteP").value(hasItem(DEFAULT_CONDUCTIVITE_P.doubleValue())))
            .andExpect(jsonPath("$.[*].debitPompe").value(hasItem(DEFAULT_DEBIT_POMPE.doubleValue())))
            .andExpect(jsonPath("$.[*].temperatureDialysat").value(hasItem(DEFAULT_TEMPERATURE_DIALYSAT.doubleValue())))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC.booleanValue())))
            .andExpect(jsonPath("$.[*].hnfh0").value(hasItem(DEFAULT_HNFH_0.doubleValue())))
            .andExpect(jsonPath("$.[*].hnfh2").value(hasItem(DEFAULT_HNFH_2.doubleValue())))
            .andExpect(jsonPath("$.[*].hbpm").value(hasItem(DEFAULT_HBPM.doubleValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].motifAnnulation").value(hasItem(DEFAULT_MOTIF_ANNULATION)))
            .andExpect(jsonPath("$.[*].motifReport").value(hasItem(DEFAULT_MOTIF_REPORT)))
            .andExpect(jsonPath("$.[*].observationP").value(hasItem(DEFAULT_OBSERVATION_P)));
    }

    @Test
    @Transactional
    public void getPrescription() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get the prescription
        restPrescriptionMockMvc
            .perform(get("/api/prescriptions/{id}", prescription.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(prescription.getId().intValue()))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE))
            .andExpect(jsonPath("$.capillaire").value(DEFAULT_CAPILLAIRE))
            .andExpect(jsonPath("$.restitutionP").value(DEFAULT_RESTITUTION_P))
            .andExpect(jsonPath("$.niveauUrgence").value(DEFAULT_NIVEAU_URGENCE))
            .andExpect(jsonPath("$.ufTotale").value(DEFAULT_UF_TOTALE.doubleValue()))
            .andExpect(jsonPath("$.rincage").value(DEFAULT_RINCAGE))
            .andExpect(jsonPath("$.transfusion").value(DEFAULT_TRANSFUSION))
            .andExpect(jsonPath("$.datePlanification").value(DEFAULT_DATE_PLANIFICATION.toString()))
            .andExpect(jsonPath("$.circuit").value(DEFAULT_CIRCUIT))
            .andExpect(jsonPath("$.abordVasculaire").value(DEFAULT_ABORD_VASCULAIRE))
            .andExpect(jsonPath("$.profil").value(DEFAULT_PROFIL))
            .andExpect(jsonPath("$.conductiviteP").value(DEFAULT_CONDUCTIVITE_P.doubleValue()))
            .andExpect(jsonPath("$.debitPompe").value(DEFAULT_DEBIT_POMPE.doubleValue()))
            .andExpect(jsonPath("$.temperatureDialysat").value(DEFAULT_TEMPERATURE_DIALYSAT.doubleValue()))
            .andExpect(jsonPath("$.atc").value(DEFAULT_ATC.booleanValue()))
            .andExpect(jsonPath("$.hnfh0").value(DEFAULT_HNFH_0.doubleValue()))
            .andExpect(jsonPath("$.hnfh2").value(DEFAULT_HNFH_2.doubleValue()))
            .andExpect(jsonPath("$.hbpm").value(DEFAULT_HBPM.doubleValue()))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.motifAnnulation").value(DEFAULT_MOTIF_ANNULATION))
            .andExpect(jsonPath("$.motifReport").value(DEFAULT_MOTIF_REPORT))
            .andExpect(jsonPath("$.observationP").value(DEFAULT_OBSERVATION_P));
    }

    @Test
    @Transactional
    public void getPrescriptionsByIdFiltering() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        Long id = prescription.getId();

        defaultPrescriptionShouldBeFound("id.equals=" + id);
        defaultPrescriptionShouldNotBeFound("id.notEquals=" + id);

        defaultPrescriptionShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPrescriptionShouldNotBeFound("id.greaterThan=" + id);

        defaultPrescriptionShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPrescriptionShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree equals to DEFAULT_DUREE
        defaultPrescriptionShouldBeFound("duree.equals=" + DEFAULT_DUREE);

        // Get all the prescriptionList where duree equals to UPDATED_DUREE
        defaultPrescriptionShouldNotBeFound("duree.equals=" + UPDATED_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree not equals to DEFAULT_DUREE
        defaultPrescriptionShouldNotBeFound("duree.notEquals=" + DEFAULT_DUREE);

        // Get all the prescriptionList where duree not equals to UPDATED_DUREE
        defaultPrescriptionShouldBeFound("duree.notEquals=" + UPDATED_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree in DEFAULT_DUREE or UPDATED_DUREE
        defaultPrescriptionShouldBeFound("duree.in=" + DEFAULT_DUREE + "," + UPDATED_DUREE);

        // Get all the prescriptionList where duree equals to UPDATED_DUREE
        defaultPrescriptionShouldNotBeFound("duree.in=" + UPDATED_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree is not null
        defaultPrescriptionShouldBeFound("duree.specified=true");

        // Get all the prescriptionList where duree is null
        defaultPrescriptionShouldNotBeFound("duree.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree is greater than or equal to DEFAULT_DUREE
        defaultPrescriptionShouldBeFound("duree.greaterThanOrEqual=" + DEFAULT_DUREE);

        // Get all the prescriptionList where duree is greater than or equal to UPDATED_DUREE
        defaultPrescriptionShouldNotBeFound("duree.greaterThanOrEqual=" + UPDATED_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree is less than or equal to DEFAULT_DUREE
        defaultPrescriptionShouldBeFound("duree.lessThanOrEqual=" + DEFAULT_DUREE);

        // Get all the prescriptionList where duree is less than or equal to SMALLER_DUREE
        defaultPrescriptionShouldNotBeFound("duree.lessThanOrEqual=" + SMALLER_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree is less than DEFAULT_DUREE
        defaultPrescriptionShouldNotBeFound("duree.lessThan=" + DEFAULT_DUREE);

        // Get all the prescriptionList where duree is less than UPDATED_DUREE
        defaultPrescriptionShouldBeFound("duree.lessThan=" + UPDATED_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDureeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where duree is greater than DEFAULT_DUREE
        defaultPrescriptionShouldNotBeFound("duree.greaterThan=" + DEFAULT_DUREE);

        // Get all the prescriptionList where duree is greater than SMALLER_DUREE
        defaultPrescriptionShouldBeFound("duree.greaterThan=" + SMALLER_DUREE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire equals to DEFAULT_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.equals=" + DEFAULT_CAPILLAIRE);

        // Get all the prescriptionList where capillaire equals to UPDATED_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.equals=" + UPDATED_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire not equals to DEFAULT_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.notEquals=" + DEFAULT_CAPILLAIRE);

        // Get all the prescriptionList where capillaire not equals to UPDATED_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.notEquals=" + UPDATED_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire in DEFAULT_CAPILLAIRE or UPDATED_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.in=" + DEFAULT_CAPILLAIRE + "," + UPDATED_CAPILLAIRE);

        // Get all the prescriptionList where capillaire equals to UPDATED_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.in=" + UPDATED_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire is not null
        defaultPrescriptionShouldBeFound("capillaire.specified=true");

        // Get all the prescriptionList where capillaire is null
        defaultPrescriptionShouldNotBeFound("capillaire.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire is greater than or equal to DEFAULT_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.greaterThanOrEqual=" + DEFAULT_CAPILLAIRE);

        // Get all the prescriptionList where capillaire is greater than or equal to UPDATED_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.greaterThanOrEqual=" + UPDATED_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire is less than or equal to DEFAULT_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.lessThanOrEqual=" + DEFAULT_CAPILLAIRE);

        // Get all the prescriptionList where capillaire is less than or equal to SMALLER_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.lessThanOrEqual=" + SMALLER_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire is less than DEFAULT_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.lessThan=" + DEFAULT_CAPILLAIRE);

        // Get all the prescriptionList where capillaire is less than UPDATED_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.lessThan=" + UPDATED_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCapillaireIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where capillaire is greater than DEFAULT_CAPILLAIRE
        defaultPrescriptionShouldNotBeFound("capillaire.greaterThan=" + DEFAULT_CAPILLAIRE);

        // Get all the prescriptionList where capillaire is greater than SMALLER_CAPILLAIRE
        defaultPrescriptionShouldBeFound("capillaire.greaterThan=" + SMALLER_CAPILLAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP equals to DEFAULT_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.equals=" + DEFAULT_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP equals to UPDATED_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.equals=" + UPDATED_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP not equals to DEFAULT_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.notEquals=" + DEFAULT_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP not equals to UPDATED_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.notEquals=" + UPDATED_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP in DEFAULT_RESTITUTION_P or UPDATED_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.in=" + DEFAULT_RESTITUTION_P + "," + UPDATED_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP equals to UPDATED_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.in=" + UPDATED_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP is not null
        defaultPrescriptionShouldBeFound("restitutionP.specified=true");

        // Get all the prescriptionList where restitutionP is null
        defaultPrescriptionShouldNotBeFound("restitutionP.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP is greater than or equal to DEFAULT_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.greaterThanOrEqual=" + DEFAULT_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP is greater than or equal to UPDATED_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.greaterThanOrEqual=" + UPDATED_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP is less than or equal to DEFAULT_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.lessThanOrEqual=" + DEFAULT_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP is less than or equal to SMALLER_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.lessThanOrEqual=" + SMALLER_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP is less than DEFAULT_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.lessThan=" + DEFAULT_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP is less than UPDATED_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.lessThan=" + UPDATED_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRestitutionPIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where restitutionP is greater than DEFAULT_RESTITUTION_P
        defaultPrescriptionShouldNotBeFound("restitutionP.greaterThan=" + DEFAULT_RESTITUTION_P);

        // Get all the prescriptionList where restitutionP is greater than SMALLER_RESTITUTION_P
        defaultPrescriptionShouldBeFound("restitutionP.greaterThan=" + SMALLER_RESTITUTION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence equals to DEFAULT_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.equals=" + DEFAULT_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence equals to UPDATED_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.equals=" + UPDATED_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence not equals to DEFAULT_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.notEquals=" + DEFAULT_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence not equals to UPDATED_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.notEquals=" + UPDATED_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence in DEFAULT_NIVEAU_URGENCE or UPDATED_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.in=" + DEFAULT_NIVEAU_URGENCE + "," + UPDATED_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence equals to UPDATED_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.in=" + UPDATED_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence is not null
        defaultPrescriptionShouldBeFound("niveauUrgence.specified=true");

        // Get all the prescriptionList where niveauUrgence is null
        defaultPrescriptionShouldNotBeFound("niveauUrgence.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence is greater than or equal to DEFAULT_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.greaterThanOrEqual=" + DEFAULT_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence is greater than or equal to UPDATED_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.greaterThanOrEqual=" + UPDATED_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence is less than or equal to DEFAULT_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.lessThanOrEqual=" + DEFAULT_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence is less than or equal to SMALLER_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.lessThanOrEqual=" + SMALLER_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence is less than DEFAULT_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.lessThan=" + DEFAULT_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence is less than UPDATED_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.lessThan=" + UPDATED_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByNiveauUrgenceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where niveauUrgence is greater than DEFAULT_NIVEAU_URGENCE
        defaultPrescriptionShouldNotBeFound("niveauUrgence.greaterThan=" + DEFAULT_NIVEAU_URGENCE);

        // Get all the prescriptionList where niveauUrgence is greater than SMALLER_NIVEAU_URGENCE
        defaultPrescriptionShouldBeFound("niveauUrgence.greaterThan=" + SMALLER_NIVEAU_URGENCE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale equals to DEFAULT_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.equals=" + DEFAULT_UF_TOTALE);

        // Get all the prescriptionList where ufTotale equals to UPDATED_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.equals=" + UPDATED_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale not equals to DEFAULT_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.notEquals=" + DEFAULT_UF_TOTALE);

        // Get all the prescriptionList where ufTotale not equals to UPDATED_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.notEquals=" + UPDATED_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale in DEFAULT_UF_TOTALE or UPDATED_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.in=" + DEFAULT_UF_TOTALE + "," + UPDATED_UF_TOTALE);

        // Get all the prescriptionList where ufTotale equals to UPDATED_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.in=" + UPDATED_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale is not null
        defaultPrescriptionShouldBeFound("ufTotale.specified=true");

        // Get all the prescriptionList where ufTotale is null
        defaultPrescriptionShouldNotBeFound("ufTotale.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale is greater than or equal to DEFAULT_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.greaterThanOrEqual=" + DEFAULT_UF_TOTALE);

        // Get all the prescriptionList where ufTotale is greater than or equal to UPDATED_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.greaterThanOrEqual=" + UPDATED_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale is less than or equal to DEFAULT_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.lessThanOrEqual=" + DEFAULT_UF_TOTALE);

        // Get all the prescriptionList where ufTotale is less than or equal to SMALLER_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.lessThanOrEqual=" + SMALLER_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale is less than DEFAULT_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.lessThan=" + DEFAULT_UF_TOTALE);

        // Get all the prescriptionList where ufTotale is less than UPDATED_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.lessThan=" + UPDATED_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByUfTotaleIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where ufTotale is greater than DEFAULT_UF_TOTALE
        defaultPrescriptionShouldNotBeFound("ufTotale.greaterThan=" + DEFAULT_UF_TOTALE);

        // Get all the prescriptionList where ufTotale is greater than SMALLER_UF_TOTALE
        defaultPrescriptionShouldBeFound("ufTotale.greaterThan=" + SMALLER_UF_TOTALE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage equals to DEFAULT_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.equals=" + DEFAULT_RINCAGE);

        // Get all the prescriptionList where rincage equals to UPDATED_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.equals=" + UPDATED_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage not equals to DEFAULT_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.notEquals=" + DEFAULT_RINCAGE);

        // Get all the prescriptionList where rincage not equals to UPDATED_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.notEquals=" + UPDATED_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage in DEFAULT_RINCAGE or UPDATED_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.in=" + DEFAULT_RINCAGE + "," + UPDATED_RINCAGE);

        // Get all the prescriptionList where rincage equals to UPDATED_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.in=" + UPDATED_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage is not null
        defaultPrescriptionShouldBeFound("rincage.specified=true");

        // Get all the prescriptionList where rincage is null
        defaultPrescriptionShouldNotBeFound("rincage.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage is greater than or equal to DEFAULT_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.greaterThanOrEqual=" + DEFAULT_RINCAGE);

        // Get all the prescriptionList where rincage is greater than or equal to UPDATED_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.greaterThanOrEqual=" + UPDATED_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage is less than or equal to DEFAULT_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.lessThanOrEqual=" + DEFAULT_RINCAGE);

        // Get all the prescriptionList where rincage is less than or equal to SMALLER_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.lessThanOrEqual=" + SMALLER_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage is less than DEFAULT_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.lessThan=" + DEFAULT_RINCAGE);

        // Get all the prescriptionList where rincage is less than UPDATED_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.lessThan=" + UPDATED_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByRincageIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where rincage is greater than DEFAULT_RINCAGE
        defaultPrescriptionShouldNotBeFound("rincage.greaterThan=" + DEFAULT_RINCAGE);

        // Get all the prescriptionList where rincage is greater than SMALLER_RINCAGE
        defaultPrescriptionShouldBeFound("rincage.greaterThan=" + SMALLER_RINCAGE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion equals to DEFAULT_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.equals=" + DEFAULT_TRANSFUSION);

        // Get all the prescriptionList where transfusion equals to UPDATED_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.equals=" + UPDATED_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion not equals to DEFAULT_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.notEquals=" + DEFAULT_TRANSFUSION);

        // Get all the prescriptionList where transfusion not equals to UPDATED_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.notEquals=" + UPDATED_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion in DEFAULT_TRANSFUSION or UPDATED_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.in=" + DEFAULT_TRANSFUSION + "," + UPDATED_TRANSFUSION);

        // Get all the prescriptionList where transfusion equals to UPDATED_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.in=" + UPDATED_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion is not null
        defaultPrescriptionShouldBeFound("transfusion.specified=true");

        // Get all the prescriptionList where transfusion is null
        defaultPrescriptionShouldNotBeFound("transfusion.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion is greater than or equal to DEFAULT_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.greaterThanOrEqual=" + DEFAULT_TRANSFUSION);

        // Get all the prescriptionList where transfusion is greater than or equal to UPDATED_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.greaterThanOrEqual=" + UPDATED_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion is less than or equal to DEFAULT_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.lessThanOrEqual=" + DEFAULT_TRANSFUSION);

        // Get all the prescriptionList where transfusion is less than or equal to SMALLER_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.lessThanOrEqual=" + SMALLER_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion is less than DEFAULT_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.lessThan=" + DEFAULT_TRANSFUSION);

        // Get all the prescriptionList where transfusion is less than UPDATED_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.lessThan=" + UPDATED_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTransfusionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where transfusion is greater than DEFAULT_TRANSFUSION
        defaultPrescriptionShouldNotBeFound("transfusion.greaterThan=" + DEFAULT_TRANSFUSION);

        // Get all the prescriptionList where transfusion is greater than SMALLER_TRANSFUSION
        defaultPrescriptionShouldBeFound("transfusion.greaterThan=" + SMALLER_TRANSFUSION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification equals to DEFAULT_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.equals=" + DEFAULT_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification equals to UPDATED_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.equals=" + UPDATED_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification not equals to DEFAULT_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.notEquals=" + DEFAULT_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification not equals to UPDATED_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.notEquals=" + UPDATED_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification in DEFAULT_DATE_PLANIFICATION or UPDATED_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.in=" + DEFAULT_DATE_PLANIFICATION + "," + UPDATED_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification equals to UPDATED_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.in=" + UPDATED_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification is not null
        defaultPrescriptionShouldBeFound("datePlanification.specified=true");

        // Get all the prescriptionList where datePlanification is null
        defaultPrescriptionShouldNotBeFound("datePlanification.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification is greater than or equal to DEFAULT_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.greaterThanOrEqual=" + DEFAULT_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification is greater than or equal to UPDATED_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.greaterThanOrEqual=" + UPDATED_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification is less than or equal to DEFAULT_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.lessThanOrEqual=" + DEFAULT_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification is less than or equal to SMALLER_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.lessThanOrEqual=" + SMALLER_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification is less than DEFAULT_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.lessThan=" + DEFAULT_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification is less than UPDATED_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.lessThan=" + UPDATED_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDatePlanificationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where datePlanification is greater than DEFAULT_DATE_PLANIFICATION
        defaultPrescriptionShouldNotBeFound("datePlanification.greaterThan=" + DEFAULT_DATE_PLANIFICATION);

        // Get all the prescriptionList where datePlanification is greater than SMALLER_DATE_PLANIFICATION
        defaultPrescriptionShouldBeFound("datePlanification.greaterThan=" + SMALLER_DATE_PLANIFICATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit equals to DEFAULT_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.equals=" + DEFAULT_CIRCUIT);

        // Get all the prescriptionList where circuit equals to UPDATED_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.equals=" + UPDATED_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit not equals to DEFAULT_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.notEquals=" + DEFAULT_CIRCUIT);

        // Get all the prescriptionList where circuit not equals to UPDATED_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.notEquals=" + UPDATED_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit in DEFAULT_CIRCUIT or UPDATED_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.in=" + DEFAULT_CIRCUIT + "," + UPDATED_CIRCUIT);

        // Get all the prescriptionList where circuit equals to UPDATED_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.in=" + UPDATED_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit is not null
        defaultPrescriptionShouldBeFound("circuit.specified=true");

        // Get all the prescriptionList where circuit is null
        defaultPrescriptionShouldNotBeFound("circuit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit is greater than or equal to DEFAULT_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.greaterThanOrEqual=" + DEFAULT_CIRCUIT);

        // Get all the prescriptionList where circuit is greater than or equal to UPDATED_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.greaterThanOrEqual=" + UPDATED_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit is less than or equal to DEFAULT_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.lessThanOrEqual=" + DEFAULT_CIRCUIT);

        // Get all the prescriptionList where circuit is less than or equal to SMALLER_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.lessThanOrEqual=" + SMALLER_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit is less than DEFAULT_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.lessThan=" + DEFAULT_CIRCUIT);

        // Get all the prescriptionList where circuit is less than UPDATED_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.lessThan=" + UPDATED_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByCircuitIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where circuit is greater than DEFAULT_CIRCUIT
        defaultPrescriptionShouldNotBeFound("circuit.greaterThan=" + DEFAULT_CIRCUIT);

        // Get all the prescriptionList where circuit is greater than SMALLER_CIRCUIT
        defaultPrescriptionShouldBeFound("circuit.greaterThan=" + SMALLER_CIRCUIT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire equals to DEFAULT_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.equals=" + DEFAULT_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire equals to UPDATED_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.equals=" + UPDATED_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire not equals to DEFAULT_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.notEquals=" + DEFAULT_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire not equals to UPDATED_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.notEquals=" + UPDATED_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire in DEFAULT_ABORD_VASCULAIRE or UPDATED_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.in=" + DEFAULT_ABORD_VASCULAIRE + "," + UPDATED_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire equals to UPDATED_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.in=" + UPDATED_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire is not null
        defaultPrescriptionShouldBeFound("abordVasculaire.specified=true");

        // Get all the prescriptionList where abordVasculaire is null
        defaultPrescriptionShouldNotBeFound("abordVasculaire.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire is greater than or equal to DEFAULT_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.greaterThanOrEqual=" + DEFAULT_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire is greater than or equal to UPDATED_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.greaterThanOrEqual=" + UPDATED_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire is less than or equal to DEFAULT_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.lessThanOrEqual=" + DEFAULT_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire is less than or equal to SMALLER_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.lessThanOrEqual=" + SMALLER_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire is less than DEFAULT_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.lessThan=" + DEFAULT_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire is less than UPDATED_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.lessThan=" + UPDATED_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAbordVasculaireIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where abordVasculaire is greater than DEFAULT_ABORD_VASCULAIRE
        defaultPrescriptionShouldNotBeFound("abordVasculaire.greaterThan=" + DEFAULT_ABORD_VASCULAIRE);

        // Get all the prescriptionList where abordVasculaire is greater than SMALLER_ABORD_VASCULAIRE
        defaultPrescriptionShouldBeFound("abordVasculaire.greaterThan=" + SMALLER_ABORD_VASCULAIRE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil equals to DEFAULT_PROFIL
        defaultPrescriptionShouldBeFound("profil.equals=" + DEFAULT_PROFIL);

        // Get all the prescriptionList where profil equals to UPDATED_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.equals=" + UPDATED_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil not equals to DEFAULT_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.notEquals=" + DEFAULT_PROFIL);

        // Get all the prescriptionList where profil not equals to UPDATED_PROFIL
        defaultPrescriptionShouldBeFound("profil.notEquals=" + UPDATED_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil in DEFAULT_PROFIL or UPDATED_PROFIL
        defaultPrescriptionShouldBeFound("profil.in=" + DEFAULT_PROFIL + "," + UPDATED_PROFIL);

        // Get all the prescriptionList where profil equals to UPDATED_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.in=" + UPDATED_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil is not null
        defaultPrescriptionShouldBeFound("profil.specified=true");

        // Get all the prescriptionList where profil is null
        defaultPrescriptionShouldNotBeFound("profil.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil is greater than or equal to DEFAULT_PROFIL
        defaultPrescriptionShouldBeFound("profil.greaterThanOrEqual=" + DEFAULT_PROFIL);

        // Get all the prescriptionList where profil is greater than or equal to UPDATED_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.greaterThanOrEqual=" + UPDATED_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil is less than or equal to DEFAULT_PROFIL
        defaultPrescriptionShouldBeFound("profil.lessThanOrEqual=" + DEFAULT_PROFIL);

        // Get all the prescriptionList where profil is less than or equal to SMALLER_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.lessThanOrEqual=" + SMALLER_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil is less than DEFAULT_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.lessThan=" + DEFAULT_PROFIL);

        // Get all the prescriptionList where profil is less than UPDATED_PROFIL
        defaultPrescriptionShouldBeFound("profil.lessThan=" + UPDATED_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByProfilIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where profil is greater than DEFAULT_PROFIL
        defaultPrescriptionShouldNotBeFound("profil.greaterThan=" + DEFAULT_PROFIL);

        // Get all the prescriptionList where profil is greater than SMALLER_PROFIL
        defaultPrescriptionShouldBeFound("profil.greaterThan=" + SMALLER_PROFIL);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP equals to DEFAULT_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.equals=" + DEFAULT_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP equals to UPDATED_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.equals=" + UPDATED_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP not equals to DEFAULT_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.notEquals=" + DEFAULT_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP not equals to UPDATED_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.notEquals=" + UPDATED_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP in DEFAULT_CONDUCTIVITE_P or UPDATED_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.in=" + DEFAULT_CONDUCTIVITE_P + "," + UPDATED_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP equals to UPDATED_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.in=" + UPDATED_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP is not null
        defaultPrescriptionShouldBeFound("conductiviteP.specified=true");

        // Get all the prescriptionList where conductiviteP is null
        defaultPrescriptionShouldNotBeFound("conductiviteP.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP is greater than or equal to DEFAULT_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.greaterThanOrEqual=" + DEFAULT_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP is greater than or equal to UPDATED_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.greaterThanOrEqual=" + UPDATED_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP is less than or equal to DEFAULT_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.lessThanOrEqual=" + DEFAULT_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP is less than or equal to SMALLER_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.lessThanOrEqual=" + SMALLER_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP is less than DEFAULT_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.lessThan=" + DEFAULT_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP is less than UPDATED_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.lessThan=" + UPDATED_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByConductivitePIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where conductiviteP is greater than DEFAULT_CONDUCTIVITE_P
        defaultPrescriptionShouldNotBeFound("conductiviteP.greaterThan=" + DEFAULT_CONDUCTIVITE_P);

        // Get all the prescriptionList where conductiviteP is greater than SMALLER_CONDUCTIVITE_P
        defaultPrescriptionShouldBeFound("conductiviteP.greaterThan=" + SMALLER_CONDUCTIVITE_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe equals to DEFAULT_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.equals=" + DEFAULT_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe equals to UPDATED_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.equals=" + UPDATED_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe not equals to DEFAULT_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.notEquals=" + DEFAULT_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe not equals to UPDATED_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.notEquals=" + UPDATED_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe in DEFAULT_DEBIT_POMPE or UPDATED_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.in=" + DEFAULT_DEBIT_POMPE + "," + UPDATED_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe equals to UPDATED_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.in=" + UPDATED_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe is not null
        defaultPrescriptionShouldBeFound("debitPompe.specified=true");

        // Get all the prescriptionList where debitPompe is null
        defaultPrescriptionShouldNotBeFound("debitPompe.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe is greater than or equal to DEFAULT_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.greaterThanOrEqual=" + DEFAULT_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe is greater than or equal to UPDATED_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.greaterThanOrEqual=" + UPDATED_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe is less than or equal to DEFAULT_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.lessThanOrEqual=" + DEFAULT_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe is less than or equal to SMALLER_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.lessThanOrEqual=" + SMALLER_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe is less than DEFAULT_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.lessThan=" + DEFAULT_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe is less than UPDATED_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.lessThan=" + UPDATED_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByDebitPompeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where debitPompe is greater than DEFAULT_DEBIT_POMPE
        defaultPrescriptionShouldNotBeFound("debitPompe.greaterThan=" + DEFAULT_DEBIT_POMPE);

        // Get all the prescriptionList where debitPompe is greater than SMALLER_DEBIT_POMPE
        defaultPrescriptionShouldBeFound("debitPompe.greaterThan=" + SMALLER_DEBIT_POMPE);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat equals to DEFAULT_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.equals=" + DEFAULT_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat equals to UPDATED_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.equals=" + UPDATED_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat not equals to DEFAULT_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.notEquals=" + DEFAULT_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat not equals to UPDATED_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.notEquals=" + UPDATED_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat in DEFAULT_TEMPERATURE_DIALYSAT or UPDATED_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.in=" + DEFAULT_TEMPERATURE_DIALYSAT + "," + UPDATED_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat equals to UPDATED_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.in=" + UPDATED_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat is not null
        defaultPrescriptionShouldBeFound("temperatureDialysat.specified=true");

        // Get all the prescriptionList where temperatureDialysat is null
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat is greater than or equal to DEFAULT_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.greaterThanOrEqual=" + DEFAULT_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat is greater than or equal to UPDATED_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.greaterThanOrEqual=" + UPDATED_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat is less than or equal to DEFAULT_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.lessThanOrEqual=" + DEFAULT_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat is less than or equal to SMALLER_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.lessThanOrEqual=" + SMALLER_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat is less than DEFAULT_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.lessThan=" + DEFAULT_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat is less than UPDATED_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.lessThan=" + UPDATED_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTemperatureDialysatIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where temperatureDialysat is greater than DEFAULT_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldNotBeFound("temperatureDialysat.greaterThan=" + DEFAULT_TEMPERATURE_DIALYSAT);

        // Get all the prescriptionList where temperatureDialysat is greater than SMALLER_TEMPERATURE_DIALYSAT
        defaultPrescriptionShouldBeFound("temperatureDialysat.greaterThan=" + SMALLER_TEMPERATURE_DIALYSAT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAtcIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where atc equals to DEFAULT_ATC
        defaultPrescriptionShouldBeFound("atc.equals=" + DEFAULT_ATC);

        // Get all the prescriptionList where atc equals to UPDATED_ATC
        defaultPrescriptionShouldNotBeFound("atc.equals=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAtcIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where atc not equals to DEFAULT_ATC
        defaultPrescriptionShouldNotBeFound("atc.notEquals=" + DEFAULT_ATC);

        // Get all the prescriptionList where atc not equals to UPDATED_ATC
        defaultPrescriptionShouldBeFound("atc.notEquals=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAtcIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where atc in DEFAULT_ATC or UPDATED_ATC
        defaultPrescriptionShouldBeFound("atc.in=" + DEFAULT_ATC + "," + UPDATED_ATC);

        // Get all the prescriptionList where atc equals to UPDATED_ATC
        defaultPrescriptionShouldNotBeFound("atc.in=" + UPDATED_ATC);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByAtcIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where atc is not null
        defaultPrescriptionShouldBeFound("atc.specified=true");

        // Get all the prescriptionList where atc is null
        defaultPrescriptionShouldNotBeFound("atc.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 equals to DEFAULT_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.equals=" + DEFAULT_HNFH_0);

        // Get all the prescriptionList where hnfh0 equals to UPDATED_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.equals=" + UPDATED_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 not equals to DEFAULT_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.notEquals=" + DEFAULT_HNFH_0);

        // Get all the prescriptionList where hnfh0 not equals to UPDATED_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.notEquals=" + UPDATED_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 in DEFAULT_HNFH_0 or UPDATED_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.in=" + DEFAULT_HNFH_0 + "," + UPDATED_HNFH_0);

        // Get all the prescriptionList where hnfh0 equals to UPDATED_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.in=" + UPDATED_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 is not null
        defaultPrescriptionShouldBeFound("hnfh0.specified=true");

        // Get all the prescriptionList where hnfh0 is null
        defaultPrescriptionShouldNotBeFound("hnfh0.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 is greater than or equal to DEFAULT_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.greaterThanOrEqual=" + DEFAULT_HNFH_0);

        // Get all the prescriptionList where hnfh0 is greater than or equal to UPDATED_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.greaterThanOrEqual=" + UPDATED_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 is less than or equal to DEFAULT_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.lessThanOrEqual=" + DEFAULT_HNFH_0);

        // Get all the prescriptionList where hnfh0 is less than or equal to SMALLER_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.lessThanOrEqual=" + SMALLER_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 is less than DEFAULT_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.lessThan=" + DEFAULT_HNFH_0);

        // Get all the prescriptionList where hnfh0 is less than UPDATED_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.lessThan=" + UPDATED_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh0IsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh0 is greater than DEFAULT_HNFH_0
        defaultPrescriptionShouldNotBeFound("hnfh0.greaterThan=" + DEFAULT_HNFH_0);

        // Get all the prescriptionList where hnfh0 is greater than SMALLER_HNFH_0
        defaultPrescriptionShouldBeFound("hnfh0.greaterThan=" + SMALLER_HNFH_0);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 equals to DEFAULT_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.equals=" + DEFAULT_HNFH_2);

        // Get all the prescriptionList where hnfh2 equals to UPDATED_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.equals=" + UPDATED_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 not equals to DEFAULT_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.notEquals=" + DEFAULT_HNFH_2);

        // Get all the prescriptionList where hnfh2 not equals to UPDATED_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.notEquals=" + UPDATED_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 in DEFAULT_HNFH_2 or UPDATED_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.in=" + DEFAULT_HNFH_2 + "," + UPDATED_HNFH_2);

        // Get all the prescriptionList where hnfh2 equals to UPDATED_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.in=" + UPDATED_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 is not null
        defaultPrescriptionShouldBeFound("hnfh2.specified=true");

        // Get all the prescriptionList where hnfh2 is null
        defaultPrescriptionShouldNotBeFound("hnfh2.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 is greater than or equal to DEFAULT_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.greaterThanOrEqual=" + DEFAULT_HNFH_2);

        // Get all the prescriptionList where hnfh2 is greater than or equal to UPDATED_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.greaterThanOrEqual=" + UPDATED_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 is less than or equal to DEFAULT_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.lessThanOrEqual=" + DEFAULT_HNFH_2);

        // Get all the prescriptionList where hnfh2 is less than or equal to SMALLER_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.lessThanOrEqual=" + SMALLER_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 is less than DEFAULT_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.lessThan=" + DEFAULT_HNFH_2);

        // Get all the prescriptionList where hnfh2 is less than UPDATED_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.lessThan=" + UPDATED_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHnfh2IsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hnfh2 is greater than DEFAULT_HNFH_2
        defaultPrescriptionShouldNotBeFound("hnfh2.greaterThan=" + DEFAULT_HNFH_2);

        // Get all the prescriptionList where hnfh2 is greater than SMALLER_HNFH_2
        defaultPrescriptionShouldBeFound("hnfh2.greaterThan=" + SMALLER_HNFH_2);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm equals to DEFAULT_HBPM
        defaultPrescriptionShouldBeFound("hbpm.equals=" + DEFAULT_HBPM);

        // Get all the prescriptionList where hbpm equals to UPDATED_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.equals=" + UPDATED_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm not equals to DEFAULT_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.notEquals=" + DEFAULT_HBPM);

        // Get all the prescriptionList where hbpm not equals to UPDATED_HBPM
        defaultPrescriptionShouldBeFound("hbpm.notEquals=" + UPDATED_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm in DEFAULT_HBPM or UPDATED_HBPM
        defaultPrescriptionShouldBeFound("hbpm.in=" + DEFAULT_HBPM + "," + UPDATED_HBPM);

        // Get all the prescriptionList where hbpm equals to UPDATED_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.in=" + UPDATED_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm is not null
        defaultPrescriptionShouldBeFound("hbpm.specified=true");

        // Get all the prescriptionList where hbpm is null
        defaultPrescriptionShouldNotBeFound("hbpm.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm is greater than or equal to DEFAULT_HBPM
        defaultPrescriptionShouldBeFound("hbpm.greaterThanOrEqual=" + DEFAULT_HBPM);

        // Get all the prescriptionList where hbpm is greater than or equal to UPDATED_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.greaterThanOrEqual=" + UPDATED_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm is less than or equal to DEFAULT_HBPM
        defaultPrescriptionShouldBeFound("hbpm.lessThanOrEqual=" + DEFAULT_HBPM);

        // Get all the prescriptionList where hbpm is less than or equal to SMALLER_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.lessThanOrEqual=" + SMALLER_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm is less than DEFAULT_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.lessThan=" + DEFAULT_HBPM);

        // Get all the prescriptionList where hbpm is less than UPDATED_HBPM
        defaultPrescriptionShouldBeFound("hbpm.lessThan=" + UPDATED_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByHbpmIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where hbpm is greater than DEFAULT_HBPM
        defaultPrescriptionShouldNotBeFound("hbpm.greaterThan=" + DEFAULT_HBPM);

        // Get all the prescriptionList where hbpm is greater than SMALLER_HBPM
        defaultPrescriptionShouldBeFound("hbpm.greaterThan=" + SMALLER_HBPM);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByStatutIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where statut equals to DEFAULT_STATUT
        defaultPrescriptionShouldBeFound("statut.equals=" + DEFAULT_STATUT);

        // Get all the prescriptionList where statut equals to UPDATED_STATUT
        defaultPrescriptionShouldNotBeFound("statut.equals=" + UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByStatutIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where statut not equals to DEFAULT_STATUT
        defaultPrescriptionShouldNotBeFound("statut.notEquals=" + DEFAULT_STATUT);

        // Get all the prescriptionList where statut not equals to UPDATED_STATUT
        defaultPrescriptionShouldBeFound("statut.notEquals=" + UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByStatutIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where statut in DEFAULT_STATUT or UPDATED_STATUT
        defaultPrescriptionShouldBeFound("statut.in=" + DEFAULT_STATUT + "," + UPDATED_STATUT);

        // Get all the prescriptionList where statut equals to UPDATED_STATUT
        defaultPrescriptionShouldNotBeFound("statut.in=" + UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByStatutIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where statut is not null
        defaultPrescriptionShouldBeFound("statut.specified=true");

        // Get all the prescriptionList where statut is null
        defaultPrescriptionShouldNotBeFound("statut.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation equals to DEFAULT_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.equals=" + DEFAULT_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation equals to UPDATED_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.equals=" + UPDATED_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation not equals to DEFAULT_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.notEquals=" + DEFAULT_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation not equals to UPDATED_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.notEquals=" + UPDATED_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation in DEFAULT_MOTIF_ANNULATION or UPDATED_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.in=" + DEFAULT_MOTIF_ANNULATION + "," + UPDATED_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation equals to UPDATED_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.in=" + UPDATED_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation is not null
        defaultPrescriptionShouldBeFound("motifAnnulation.specified=true");

        // Get all the prescriptionList where motifAnnulation is null
        defaultPrescriptionShouldNotBeFound("motifAnnulation.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation is greater than or equal to DEFAULT_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.greaterThanOrEqual=" + DEFAULT_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation is greater than or equal to UPDATED_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.greaterThanOrEqual=" + UPDATED_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation is less than or equal to DEFAULT_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.lessThanOrEqual=" + DEFAULT_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation is less than or equal to SMALLER_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.lessThanOrEqual=" + SMALLER_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation is less than DEFAULT_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.lessThan=" + DEFAULT_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation is less than UPDATED_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.lessThan=" + UPDATED_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifAnnulationIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifAnnulation is greater than DEFAULT_MOTIF_ANNULATION
        defaultPrescriptionShouldNotBeFound("motifAnnulation.greaterThan=" + DEFAULT_MOTIF_ANNULATION);

        // Get all the prescriptionList where motifAnnulation is greater than SMALLER_MOTIF_ANNULATION
        defaultPrescriptionShouldBeFound("motifAnnulation.greaterThan=" + SMALLER_MOTIF_ANNULATION);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport equals to DEFAULT_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.equals=" + DEFAULT_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport equals to UPDATED_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.equals=" + UPDATED_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport not equals to DEFAULT_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.notEquals=" + DEFAULT_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport not equals to UPDATED_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.notEquals=" + UPDATED_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport in DEFAULT_MOTIF_REPORT or UPDATED_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.in=" + DEFAULT_MOTIF_REPORT + "," + UPDATED_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport equals to UPDATED_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.in=" + UPDATED_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport is not null
        defaultPrescriptionShouldBeFound("motifReport.specified=true");

        // Get all the prescriptionList where motifReport is null
        defaultPrescriptionShouldNotBeFound("motifReport.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport is greater than or equal to DEFAULT_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.greaterThanOrEqual=" + DEFAULT_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport is greater than or equal to UPDATED_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.greaterThanOrEqual=" + UPDATED_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport is less than or equal to DEFAULT_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.lessThanOrEqual=" + DEFAULT_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport is less than or equal to SMALLER_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.lessThanOrEqual=" + SMALLER_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsLessThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport is less than DEFAULT_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.lessThan=" + DEFAULT_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport is less than UPDATED_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.lessThan=" + UPDATED_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByMotifReportIsGreaterThanSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where motifReport is greater than DEFAULT_MOTIF_REPORT
        defaultPrescriptionShouldNotBeFound("motifReport.greaterThan=" + DEFAULT_MOTIF_REPORT);

        // Get all the prescriptionList where motifReport is greater than SMALLER_MOTIF_REPORT
        defaultPrescriptionShouldBeFound("motifReport.greaterThan=" + SMALLER_MOTIF_REPORT);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByObservationPIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where observationP equals to DEFAULT_OBSERVATION_P
        defaultPrescriptionShouldBeFound("observationP.equals=" + DEFAULT_OBSERVATION_P);

        // Get all the prescriptionList where observationP equals to UPDATED_OBSERVATION_P
        defaultPrescriptionShouldNotBeFound("observationP.equals=" + UPDATED_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByObservationPIsNotEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where observationP not equals to DEFAULT_OBSERVATION_P
        defaultPrescriptionShouldNotBeFound("observationP.notEquals=" + DEFAULT_OBSERVATION_P);

        // Get all the prescriptionList where observationP not equals to UPDATED_OBSERVATION_P
        defaultPrescriptionShouldBeFound("observationP.notEquals=" + UPDATED_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByObservationPIsInShouldWork() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where observationP in DEFAULT_OBSERVATION_P or UPDATED_OBSERVATION_P
        defaultPrescriptionShouldBeFound("observationP.in=" + DEFAULT_OBSERVATION_P + "," + UPDATED_OBSERVATION_P);

        // Get all the prescriptionList where observationP equals to UPDATED_OBSERVATION_P
        defaultPrescriptionShouldNotBeFound("observationP.in=" + UPDATED_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByObservationPIsNullOrNotNull() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where observationP is not null
        defaultPrescriptionShouldBeFound("observationP.specified=true");

        // Get all the prescriptionList where observationP is null
        defaultPrescriptionShouldNotBeFound("observationP.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByObservationPContainsSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where observationP contains DEFAULT_OBSERVATION_P
        defaultPrescriptionShouldBeFound("observationP.contains=" + DEFAULT_OBSERVATION_P);

        // Get all the prescriptionList where observationP contains UPDATED_OBSERVATION_P
        defaultPrescriptionShouldNotBeFound("observationP.contains=" + UPDATED_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByObservationPNotContainsSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);

        // Get all the prescriptionList where observationP does not contain DEFAULT_OBSERVATION_P
        defaultPrescriptionShouldNotBeFound("observationP.doesNotContain=" + DEFAULT_OBSERVATION_P);

        // Get all the prescriptionList where observationP does not contain UPDATED_OBSERVATION_P
        defaultPrescriptionShouldBeFound("observationP.doesNotContain=" + UPDATED_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByTraitementIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);
        TraitementPerdialyse traitement = TraitementPerdialyseResourceIT.createEntity(em);
        em.persist(traitement);
        em.flush();
        prescription.setTraitement(traitement);
        prescriptionRepository.saveAndFlush(prescription);
        Long traitementId = traitement.getId();

        // Get all the prescriptionList where traitement equals to traitementId
        defaultPrescriptionShouldBeFound("traitementId.equals=" + traitementId);

        // Get all the prescriptionList where traitement equals to traitementId + 1
        defaultPrescriptionShouldNotBeFound("traitementId.equals=" + (traitementId + 1));
    }

    @Test
    @Transactional
    public void getAllPrescriptionsBySurveillanceIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);
        Surveillance surveillance = SurveillanceResourceIT.createEntity(em);
        em.persist(surveillance);
        em.flush();
        prescription.setSurveillance(surveillance);
        prescriptionRepository.saveAndFlush(prescription);
        Long surveillanceId = surveillance.getId();

        // Get all the prescriptionList where surveillance equals to surveillanceId
        defaultPrescriptionShouldBeFound("surveillanceId.equals=" + surveillanceId);

        // Get all the prescriptionList where surveillance equals to surveillanceId + 1
        defaultPrescriptionShouldNotBeFound("surveillanceId.equals=" + (surveillanceId + 1));
    }

    @Test
    @Transactional
    public void getAllPrescriptionsByPatientIsEqualToSomething() throws Exception {
        // Initialize the database
        prescriptionRepository.saveAndFlush(prescription);
        DossierPatient patient = DossierPatientResourceIT.createEntity(em);
        em.persist(patient);
        em.flush();
        prescription.setPatient(patient);
        prescriptionRepository.saveAndFlush(prescription);
        Long patientId = patient.getId();

        // Get all the prescriptionList where patient equals to patientId
        defaultPrescriptionShouldBeFound("patientId.equals=" + patientId);

        // Get all the prescriptionList where patient equals to patientId + 1
        defaultPrescriptionShouldNotBeFound("patientId.equals=" + (patientId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPrescriptionShouldBeFound(String filter) throws Exception {
        restPrescriptionMockMvc
            .perform(get("/api/prescriptions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prescription.getId().intValue())))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE)))
            .andExpect(jsonPath("$.[*].capillaire").value(hasItem(DEFAULT_CAPILLAIRE)))
            .andExpect(jsonPath("$.[*].restitutionP").value(hasItem(DEFAULT_RESTITUTION_P)))
            .andExpect(jsonPath("$.[*].niveauUrgence").value(hasItem(DEFAULT_NIVEAU_URGENCE)))
            .andExpect(jsonPath("$.[*].ufTotale").value(hasItem(DEFAULT_UF_TOTALE.doubleValue())))
            .andExpect(jsonPath("$.[*].rincage").value(hasItem(DEFAULT_RINCAGE)))
            .andExpect(jsonPath("$.[*].transfusion").value(hasItem(DEFAULT_TRANSFUSION)))
            .andExpect(jsonPath("$.[*].datePlanification").value(hasItem(DEFAULT_DATE_PLANIFICATION.toString())))
            .andExpect(jsonPath("$.[*].circuit").value(hasItem(DEFAULT_CIRCUIT)))
            .andExpect(jsonPath("$.[*].abordVasculaire").value(hasItem(DEFAULT_ABORD_VASCULAIRE)))
            .andExpect(jsonPath("$.[*].profil").value(hasItem(DEFAULT_PROFIL)))
            .andExpect(jsonPath("$.[*].conductiviteP").value(hasItem(DEFAULT_CONDUCTIVITE_P.doubleValue())))
            .andExpect(jsonPath("$.[*].debitPompe").value(hasItem(DEFAULT_DEBIT_POMPE.doubleValue())))
            .andExpect(jsonPath("$.[*].temperatureDialysat").value(hasItem(DEFAULT_TEMPERATURE_DIALYSAT.doubleValue())))
            .andExpect(jsonPath("$.[*].atc").value(hasItem(DEFAULT_ATC.booleanValue())))
            .andExpect(jsonPath("$.[*].hnfh0").value(hasItem(DEFAULT_HNFH_0.doubleValue())))
            .andExpect(jsonPath("$.[*].hnfh2").value(hasItem(DEFAULT_HNFH_2.doubleValue())))
            .andExpect(jsonPath("$.[*].hbpm").value(hasItem(DEFAULT_HBPM.doubleValue())))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].motifAnnulation").value(hasItem(DEFAULT_MOTIF_ANNULATION)))
            .andExpect(jsonPath("$.[*].motifReport").value(hasItem(DEFAULT_MOTIF_REPORT)))
            .andExpect(jsonPath("$.[*].observationP").value(hasItem(DEFAULT_OBSERVATION_P)));

        // Check, that the count call also returns 1
        restPrescriptionMockMvc
            .perform(get("/api/prescriptions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPrescriptionShouldNotBeFound(String filter) throws Exception {
        restPrescriptionMockMvc
            .perform(get("/api/prescriptions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPrescriptionMockMvc
            .perform(get("/api/prescriptions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingPrescription() throws Exception {
        // Get the prescription
        restPrescriptionMockMvc.perform(get("/api/prescriptions/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrescription() throws Exception {
        // Initialize the database
        prescriptionService.save(prescription);

        int databaseSizeBeforeUpdate = prescriptionRepository.findAll().size();

        // Update the prescription
        Prescription updatedPrescription = prescriptionRepository.findById(prescription.getId()).get();
        // Disconnect from session so that the updates on updatedPrescription are not directly saved in db
        em.detach(updatedPrescription);
        updatedPrescription
            .duree(UPDATED_DUREE)
            .capillaire(UPDATED_CAPILLAIRE)
            .restitutionP(UPDATED_RESTITUTION_P)
            .niveauUrgence(UPDATED_NIVEAU_URGENCE)
            .ufTotale(UPDATED_UF_TOTALE)
            .rincage(UPDATED_RINCAGE)
            .transfusion(UPDATED_TRANSFUSION)
            .datePlanification(UPDATED_DATE_PLANIFICATION)
            .circuit(UPDATED_CIRCUIT)
            .abordVasculaire(UPDATED_ABORD_VASCULAIRE)
            .profil(UPDATED_PROFIL)
            .conductiviteP(UPDATED_CONDUCTIVITE_P)
            .debitPompe(UPDATED_DEBIT_POMPE)
            .temperatureDialysat(UPDATED_TEMPERATURE_DIALYSAT)
            .atc(UPDATED_ATC)
            .hnfh0(UPDATED_HNFH_0)
            .hnfh2(UPDATED_HNFH_2)
            .hbpm(UPDATED_HBPM)
            .statut(UPDATED_STATUT)
            .motifAnnulation(UPDATED_MOTIF_ANNULATION)
            .motifReport(UPDATED_MOTIF_REPORT)
            .observationP(UPDATED_OBSERVATION_P);

        restPrescriptionMockMvc
            .perform(
                put("/api/prescriptions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedPrescription))
            )
            .andExpect(status().isOk());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeUpdate);
        Prescription testPrescription = prescriptionList.get(prescriptionList.size() - 1);
        assertThat(testPrescription.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testPrescription.getCapillaire()).isEqualTo(UPDATED_CAPILLAIRE);
        assertThat(testPrescription.getRestitutionP()).isEqualTo(UPDATED_RESTITUTION_P);
        assertThat(testPrescription.getNiveauUrgence()).isEqualTo(UPDATED_NIVEAU_URGENCE);
        assertThat(testPrescription.getUfTotale()).isEqualTo(UPDATED_UF_TOTALE);
        assertThat(testPrescription.getRincage()).isEqualTo(UPDATED_RINCAGE);
        assertThat(testPrescription.getTransfusion()).isEqualTo(UPDATED_TRANSFUSION);
        assertThat(testPrescription.getDatePlanification()).isEqualTo(UPDATED_DATE_PLANIFICATION);
        assertThat(testPrescription.getCircuit()).isEqualTo(UPDATED_CIRCUIT);
        assertThat(testPrescription.getAbordVasculaire()).isEqualTo(UPDATED_ABORD_VASCULAIRE);
        assertThat(testPrescription.getProfil()).isEqualTo(UPDATED_PROFIL);
        assertThat(testPrescription.getConductiviteP()).isEqualTo(UPDATED_CONDUCTIVITE_P);
        assertThat(testPrescription.getDebitPompe()).isEqualTo(UPDATED_DEBIT_POMPE);
        assertThat(testPrescription.getTemperatureDialysat()).isEqualTo(UPDATED_TEMPERATURE_DIALYSAT);
        assertThat(testPrescription.isAtc()).isEqualTo(UPDATED_ATC);
        assertThat(testPrescription.getHnfh0()).isEqualTo(UPDATED_HNFH_0);
        assertThat(testPrescription.getHnfh2()).isEqualTo(UPDATED_HNFH_2);
        assertThat(testPrescription.getHbpm()).isEqualTo(UPDATED_HBPM);
        assertThat(testPrescription.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testPrescription.getMotifAnnulation()).isEqualTo(UPDATED_MOTIF_ANNULATION);
        assertThat(testPrescription.getMotifReport()).isEqualTo(UPDATED_MOTIF_REPORT);
        assertThat(testPrescription.getObservationP()).isEqualTo(UPDATED_OBSERVATION_P);
    }

    @Test
    @Transactional
    public void updateNonExistingPrescription() throws Exception {
        int databaseSizeBeforeUpdate = prescriptionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrescriptionMockMvc
            .perform(
                put("/api/prescriptions").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(prescription))
            )
            .andExpect(status().isBadRequest());

        // Validate the Prescription in the database
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrescription() throws Exception {
        // Initialize the database
        prescriptionService.save(prescription);

        int databaseSizeBeforeDelete = prescriptionRepository.findAll().size();

        // Delete the prescription
        restPrescriptionMockMvc
            .perform(delete("/api/prescriptions/{id}", prescription.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prescription> prescriptionList = prescriptionRepository.findAll();
        assertThat(prescriptionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
