package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.Surveillance;
import ma.ekili.domain.TraitementPerdialyse;
import ma.ekili.domain.enumeration.StatutSurveillance;
import ma.ekili.repository.SurveillanceRepository;
import ma.ekili.service.SurveillanceQueryService;
import ma.ekili.service.SurveillanceService;
import ma.ekili.service.dto.SurveillanceCriteria;
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
 * Integration tests for the {@link SurveillanceResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SurveillanceResourceIT {
    private static final Integer DEFAULT_INFIRMIER = 1;
    private static final Integer UPDATED_INFIRMIER = 2;
    private static final Integer SMALLER_INFIRMIER = 1 - 1;

    private static final Integer DEFAULT_POSTE = 1;
    private static final Integer UPDATED_POSTE = 2;
    private static final Integer SMALLER_POSTE = 1 - 1;

    private static final Integer DEFAULT_GENERATEUR = 1;
    private static final Integer UPDATED_GENERATEUR = 2;
    private static final Integer SMALLER_GENERATEUR = 1 - 1;

    private static final StatutSurveillance DEFAULT_STATUT = StatutSurveillance.ENCOURS;
    private static final StatutSurveillance UPDATED_STATUT = StatutSurveillance.VALIDEE;

    private static final Double DEFAULT_POID = 1D;
    private static final Double UPDATED_POID = 2D;
    private static final Double SMALLER_POID = 1D - 1D;

    private static final Double DEFAULT_UFNET = 1D;
    private static final Double UPDATED_UFNET = 2D;
    private static final Double SMALLER_UFNET = 1D - 1D;

    private static final Integer DEFAULT_ETAT_CONSCIENCE = 1;
    private static final Integer UPDATED_ETAT_CONSCIENCE = 2;
    private static final Integer SMALLER_ETAT_CONSCIENCE = 1 - 1;

    private static final Integer DEFAULT_EUPNEIQUE = 1;
    private static final Integer UPDATED_EUPNEIQUE = 2;
    private static final Integer SMALLER_EUPNEIQUE = 1 - 1;

    private static final Integer DEFAULT_RESTITUTION_PAR = 1;
    private static final Integer UPDATED_RESTITUTION_PAR = 2;
    private static final Integer SMALLER_RESTITUTION_PAR = 1 - 1;

    private static final String DEFAULT_AUTRE_COMPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_COMPLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    @Autowired
    private SurveillanceRepository surveillanceRepository;

    @Autowired
    private SurveillanceService surveillanceService;

    @Autowired
    private SurveillanceQueryService surveillanceQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSurveillanceMockMvc;

    private Surveillance surveillance;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Surveillance createEntity(EntityManager em) {
        Surveillance surveillance = new Surveillance()
            .infirmier(DEFAULT_INFIRMIER)
            .poste(DEFAULT_POSTE)
            .generateur(DEFAULT_GENERATEUR)
            .statut(DEFAULT_STATUT)
            .poid(DEFAULT_POID)
            .ufnet(DEFAULT_UFNET)
            .etatConscience(DEFAULT_ETAT_CONSCIENCE)
            .eupneique(DEFAULT_EUPNEIQUE)
            .restitutionPar(DEFAULT_RESTITUTION_PAR)
            .autreComplication(DEFAULT_AUTRE_COMPLICATION)
            .observation(DEFAULT_OBSERVATION);
        return surveillance;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Surveillance createUpdatedEntity(EntityManager em) {
        Surveillance surveillance = new Surveillance()
            .infirmier(UPDATED_INFIRMIER)
            .poste(UPDATED_POSTE)
            .generateur(UPDATED_GENERATEUR)
            .statut(UPDATED_STATUT)
            .poid(UPDATED_POID)
            .ufnet(UPDATED_UFNET)
            .etatConscience(UPDATED_ETAT_CONSCIENCE)
            .eupneique(UPDATED_EUPNEIQUE)
            .restitutionPar(UPDATED_RESTITUTION_PAR)
            .autreComplication(UPDATED_AUTRE_COMPLICATION)
            .observation(UPDATED_OBSERVATION);
        return surveillance;
    }

    @BeforeEach
    public void initTest() {
        surveillance = createEntity(em);
    }

    @Test
    @Transactional
    public void createSurveillance() throws Exception {
        int databaseSizeBeforeCreate = surveillanceRepository.findAll().size();
        // Create the Surveillance
        restSurveillanceMockMvc
            .perform(
                post("/api/surveillances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(surveillance))
            )
            .andExpect(status().isCreated());

        // Validate the Surveillance in the database
        List<Surveillance> surveillanceList = surveillanceRepository.findAll();
        assertThat(surveillanceList).hasSize(databaseSizeBeforeCreate + 1);
        Surveillance testSurveillance = surveillanceList.get(surveillanceList.size() - 1);
        assertThat(testSurveillance.getInfirmier()).isEqualTo(DEFAULT_INFIRMIER);
        assertThat(testSurveillance.getPoste()).isEqualTo(DEFAULT_POSTE);
        assertThat(testSurveillance.getGenerateur()).isEqualTo(DEFAULT_GENERATEUR);
        assertThat(testSurveillance.getStatut()).isEqualTo(DEFAULT_STATUT);
        assertThat(testSurveillance.getPoid()).isEqualTo(DEFAULT_POID);
        assertThat(testSurveillance.getUfnet()).isEqualTo(DEFAULT_UFNET);
        assertThat(testSurveillance.getEtatConscience()).isEqualTo(DEFAULT_ETAT_CONSCIENCE);
        assertThat(testSurveillance.getEupneique()).isEqualTo(DEFAULT_EUPNEIQUE);
        assertThat(testSurveillance.getRestitutionPar()).isEqualTo(DEFAULT_RESTITUTION_PAR);
        assertThat(testSurveillance.getAutreComplication()).isEqualTo(DEFAULT_AUTRE_COMPLICATION);
        assertThat(testSurveillance.getObservation()).isEqualTo(DEFAULT_OBSERVATION);
    }

    @Test
    @Transactional
    public void createSurveillanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = surveillanceRepository.findAll().size();

        // Create the Surveillance with an existing ID
        surveillance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurveillanceMockMvc
            .perform(
                post("/api/surveillances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(surveillance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Surveillance in the database
        List<Surveillance> surveillanceList = surveillanceRepository.findAll();
        assertThat(surveillanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInfirmierIsRequired() throws Exception {
        int databaseSizeBeforeTest = surveillanceRepository.findAll().size();
        // set the field null
        surveillance.setInfirmier(null);

        // Create the Surveillance, which fails.

        restSurveillanceMockMvc
            .perform(
                post("/api/surveillances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(surveillance))
            )
            .andExpect(status().isBadRequest());

        List<Surveillance> surveillanceList = surveillanceRepository.findAll();
        assertThat(surveillanceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSurveillances() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList
        restSurveillanceMockMvc
            .perform(get("/api/surveillances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveillance.getId().intValue())))
            .andExpect(jsonPath("$.[*].infirmier").value(hasItem(DEFAULT_INFIRMIER)))
            .andExpect(jsonPath("$.[*].poste").value(hasItem(DEFAULT_POSTE)))
            .andExpect(jsonPath("$.[*].generateur").value(hasItem(DEFAULT_GENERATEUR)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].poid").value(hasItem(DEFAULT_POID.doubleValue())))
            .andExpect(jsonPath("$.[*].ufnet").value(hasItem(DEFAULT_UFNET.doubleValue())))
            .andExpect(jsonPath("$.[*].etatConscience").value(hasItem(DEFAULT_ETAT_CONSCIENCE)))
            .andExpect(jsonPath("$.[*].eupneique").value(hasItem(DEFAULT_EUPNEIQUE)))
            .andExpect(jsonPath("$.[*].restitutionPar").value(hasItem(DEFAULT_RESTITUTION_PAR)))
            .andExpect(jsonPath("$.[*].autreComplication").value(hasItem(DEFAULT_AUTRE_COMPLICATION)))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)));
    }

    @Test
    @Transactional
    public void getSurveillance() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get the surveillance
        restSurveillanceMockMvc
            .perform(get("/api/surveillances/{id}", surveillance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(surveillance.getId().intValue()))
            .andExpect(jsonPath("$.infirmier").value(DEFAULT_INFIRMIER))
            .andExpect(jsonPath("$.poste").value(DEFAULT_POSTE))
            .andExpect(jsonPath("$.generateur").value(DEFAULT_GENERATEUR))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT.toString()))
            .andExpect(jsonPath("$.poid").value(DEFAULT_POID.doubleValue()))
            .andExpect(jsonPath("$.ufnet").value(DEFAULT_UFNET.doubleValue()))
            .andExpect(jsonPath("$.etatConscience").value(DEFAULT_ETAT_CONSCIENCE))
            .andExpect(jsonPath("$.eupneique").value(DEFAULT_EUPNEIQUE))
            .andExpect(jsonPath("$.restitutionPar").value(DEFAULT_RESTITUTION_PAR))
            .andExpect(jsonPath("$.autreComplication").value(DEFAULT_AUTRE_COMPLICATION))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION));
    }

    @Test
    @Transactional
    public void getSurveillancesByIdFiltering() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        Long id = surveillance.getId();

        defaultSurveillanceShouldBeFound("id.equals=" + id);
        defaultSurveillanceShouldNotBeFound("id.notEquals=" + id);

        defaultSurveillanceShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSurveillanceShouldNotBeFound("id.greaterThan=" + id);

        defaultSurveillanceShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSurveillanceShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier equals to DEFAULT_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.equals=" + DEFAULT_INFIRMIER);

        // Get all the surveillanceList where infirmier equals to UPDATED_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.equals=" + UPDATED_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier not equals to DEFAULT_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.notEquals=" + DEFAULT_INFIRMIER);

        // Get all the surveillanceList where infirmier not equals to UPDATED_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.notEquals=" + UPDATED_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier in DEFAULT_INFIRMIER or UPDATED_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.in=" + DEFAULT_INFIRMIER + "," + UPDATED_INFIRMIER);

        // Get all the surveillanceList where infirmier equals to UPDATED_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.in=" + UPDATED_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier is not null
        defaultSurveillanceShouldBeFound("infirmier.specified=true");

        // Get all the surveillanceList where infirmier is null
        defaultSurveillanceShouldNotBeFound("infirmier.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier is greater than or equal to DEFAULT_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.greaterThanOrEqual=" + DEFAULT_INFIRMIER);

        // Get all the surveillanceList where infirmier is greater than or equal to UPDATED_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.greaterThanOrEqual=" + UPDATED_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier is less than or equal to DEFAULT_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.lessThanOrEqual=" + DEFAULT_INFIRMIER);

        // Get all the surveillanceList where infirmier is less than or equal to SMALLER_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.lessThanOrEqual=" + SMALLER_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier is less than DEFAULT_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.lessThan=" + DEFAULT_INFIRMIER);

        // Get all the surveillanceList where infirmier is less than UPDATED_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.lessThan=" + UPDATED_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByInfirmierIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where infirmier is greater than DEFAULT_INFIRMIER
        defaultSurveillanceShouldNotBeFound("infirmier.greaterThan=" + DEFAULT_INFIRMIER);

        // Get all the surveillanceList where infirmier is greater than SMALLER_INFIRMIER
        defaultSurveillanceShouldBeFound("infirmier.greaterThan=" + SMALLER_INFIRMIER);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste equals to DEFAULT_POSTE
        defaultSurveillanceShouldBeFound("poste.equals=" + DEFAULT_POSTE);

        // Get all the surveillanceList where poste equals to UPDATED_POSTE
        defaultSurveillanceShouldNotBeFound("poste.equals=" + UPDATED_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste not equals to DEFAULT_POSTE
        defaultSurveillanceShouldNotBeFound("poste.notEquals=" + DEFAULT_POSTE);

        // Get all the surveillanceList where poste not equals to UPDATED_POSTE
        defaultSurveillanceShouldBeFound("poste.notEquals=" + UPDATED_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste in DEFAULT_POSTE or UPDATED_POSTE
        defaultSurveillanceShouldBeFound("poste.in=" + DEFAULT_POSTE + "," + UPDATED_POSTE);

        // Get all the surveillanceList where poste equals to UPDATED_POSTE
        defaultSurveillanceShouldNotBeFound("poste.in=" + UPDATED_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste is not null
        defaultSurveillanceShouldBeFound("poste.specified=true");

        // Get all the surveillanceList where poste is null
        defaultSurveillanceShouldNotBeFound("poste.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste is greater than or equal to DEFAULT_POSTE
        defaultSurveillanceShouldBeFound("poste.greaterThanOrEqual=" + DEFAULT_POSTE);

        // Get all the surveillanceList where poste is greater than or equal to UPDATED_POSTE
        defaultSurveillanceShouldNotBeFound("poste.greaterThanOrEqual=" + UPDATED_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste is less than or equal to DEFAULT_POSTE
        defaultSurveillanceShouldBeFound("poste.lessThanOrEqual=" + DEFAULT_POSTE);

        // Get all the surveillanceList where poste is less than or equal to SMALLER_POSTE
        defaultSurveillanceShouldNotBeFound("poste.lessThanOrEqual=" + SMALLER_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste is less than DEFAULT_POSTE
        defaultSurveillanceShouldNotBeFound("poste.lessThan=" + DEFAULT_POSTE);

        // Get all the surveillanceList where poste is less than UPDATED_POSTE
        defaultSurveillanceShouldBeFound("poste.lessThan=" + UPDATED_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPosteIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poste is greater than DEFAULT_POSTE
        defaultSurveillanceShouldNotBeFound("poste.greaterThan=" + DEFAULT_POSTE);

        // Get all the surveillanceList where poste is greater than SMALLER_POSTE
        defaultSurveillanceShouldBeFound("poste.greaterThan=" + SMALLER_POSTE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur equals to DEFAULT_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.equals=" + DEFAULT_GENERATEUR);

        // Get all the surveillanceList where generateur equals to UPDATED_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.equals=" + UPDATED_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur not equals to DEFAULT_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.notEquals=" + DEFAULT_GENERATEUR);

        // Get all the surveillanceList where generateur not equals to UPDATED_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.notEquals=" + UPDATED_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur in DEFAULT_GENERATEUR or UPDATED_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.in=" + DEFAULT_GENERATEUR + "," + UPDATED_GENERATEUR);

        // Get all the surveillanceList where generateur equals to UPDATED_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.in=" + UPDATED_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur is not null
        defaultSurveillanceShouldBeFound("generateur.specified=true");

        // Get all the surveillanceList where generateur is null
        defaultSurveillanceShouldNotBeFound("generateur.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur is greater than or equal to DEFAULT_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.greaterThanOrEqual=" + DEFAULT_GENERATEUR);

        // Get all the surveillanceList where generateur is greater than or equal to UPDATED_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.greaterThanOrEqual=" + UPDATED_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur is less than or equal to DEFAULT_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.lessThanOrEqual=" + DEFAULT_GENERATEUR);

        // Get all the surveillanceList where generateur is less than or equal to SMALLER_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.lessThanOrEqual=" + SMALLER_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur is less than DEFAULT_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.lessThan=" + DEFAULT_GENERATEUR);

        // Get all the surveillanceList where generateur is less than UPDATED_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.lessThan=" + UPDATED_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByGenerateurIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where generateur is greater than DEFAULT_GENERATEUR
        defaultSurveillanceShouldNotBeFound("generateur.greaterThan=" + DEFAULT_GENERATEUR);

        // Get all the surveillanceList where generateur is greater than SMALLER_GENERATEUR
        defaultSurveillanceShouldBeFound("generateur.greaterThan=" + SMALLER_GENERATEUR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByStatutIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where statut equals to DEFAULT_STATUT
        defaultSurveillanceShouldBeFound("statut.equals=" + DEFAULT_STATUT);

        // Get all the surveillanceList where statut equals to UPDATED_STATUT
        defaultSurveillanceShouldNotBeFound("statut.equals=" + UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByStatutIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where statut not equals to DEFAULT_STATUT
        defaultSurveillanceShouldNotBeFound("statut.notEquals=" + DEFAULT_STATUT);

        // Get all the surveillanceList where statut not equals to UPDATED_STATUT
        defaultSurveillanceShouldBeFound("statut.notEquals=" + UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByStatutIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where statut in DEFAULT_STATUT or UPDATED_STATUT
        defaultSurveillanceShouldBeFound("statut.in=" + DEFAULT_STATUT + "," + UPDATED_STATUT);

        // Get all the surveillanceList where statut equals to UPDATED_STATUT
        defaultSurveillanceShouldNotBeFound("statut.in=" + UPDATED_STATUT);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByStatutIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where statut is not null
        defaultSurveillanceShouldBeFound("statut.specified=true");

        // Get all the surveillanceList where statut is null
        defaultSurveillanceShouldNotBeFound("statut.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid equals to DEFAULT_POID
        defaultSurveillanceShouldBeFound("poid.equals=" + DEFAULT_POID);

        // Get all the surveillanceList where poid equals to UPDATED_POID
        defaultSurveillanceShouldNotBeFound("poid.equals=" + UPDATED_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid not equals to DEFAULT_POID
        defaultSurveillanceShouldNotBeFound("poid.notEquals=" + DEFAULT_POID);

        // Get all the surveillanceList where poid not equals to UPDATED_POID
        defaultSurveillanceShouldBeFound("poid.notEquals=" + UPDATED_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid in DEFAULT_POID or UPDATED_POID
        defaultSurveillanceShouldBeFound("poid.in=" + DEFAULT_POID + "," + UPDATED_POID);

        // Get all the surveillanceList where poid equals to UPDATED_POID
        defaultSurveillanceShouldNotBeFound("poid.in=" + UPDATED_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid is not null
        defaultSurveillanceShouldBeFound("poid.specified=true");

        // Get all the surveillanceList where poid is null
        defaultSurveillanceShouldNotBeFound("poid.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid is greater than or equal to DEFAULT_POID
        defaultSurveillanceShouldBeFound("poid.greaterThanOrEqual=" + DEFAULT_POID);

        // Get all the surveillanceList where poid is greater than or equal to UPDATED_POID
        defaultSurveillanceShouldNotBeFound("poid.greaterThanOrEqual=" + UPDATED_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid is less than or equal to DEFAULT_POID
        defaultSurveillanceShouldBeFound("poid.lessThanOrEqual=" + DEFAULT_POID);

        // Get all the surveillanceList where poid is less than or equal to SMALLER_POID
        defaultSurveillanceShouldNotBeFound("poid.lessThanOrEqual=" + SMALLER_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid is less than DEFAULT_POID
        defaultSurveillanceShouldNotBeFound("poid.lessThan=" + DEFAULT_POID);

        // Get all the surveillanceList where poid is less than UPDATED_POID
        defaultSurveillanceShouldBeFound("poid.lessThan=" + UPDATED_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByPoidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where poid is greater than DEFAULT_POID
        defaultSurveillanceShouldNotBeFound("poid.greaterThan=" + DEFAULT_POID);

        // Get all the surveillanceList where poid is greater than SMALLER_POID
        defaultSurveillanceShouldBeFound("poid.greaterThan=" + SMALLER_POID);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet equals to DEFAULT_UFNET
        defaultSurveillanceShouldBeFound("ufnet.equals=" + DEFAULT_UFNET);

        // Get all the surveillanceList where ufnet equals to UPDATED_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.equals=" + UPDATED_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet not equals to DEFAULT_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.notEquals=" + DEFAULT_UFNET);

        // Get all the surveillanceList where ufnet not equals to UPDATED_UFNET
        defaultSurveillanceShouldBeFound("ufnet.notEquals=" + UPDATED_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet in DEFAULT_UFNET or UPDATED_UFNET
        defaultSurveillanceShouldBeFound("ufnet.in=" + DEFAULT_UFNET + "," + UPDATED_UFNET);

        // Get all the surveillanceList where ufnet equals to UPDATED_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.in=" + UPDATED_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet is not null
        defaultSurveillanceShouldBeFound("ufnet.specified=true");

        // Get all the surveillanceList where ufnet is null
        defaultSurveillanceShouldNotBeFound("ufnet.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet is greater than or equal to DEFAULT_UFNET
        defaultSurveillanceShouldBeFound("ufnet.greaterThanOrEqual=" + DEFAULT_UFNET);

        // Get all the surveillanceList where ufnet is greater than or equal to UPDATED_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.greaterThanOrEqual=" + UPDATED_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet is less than or equal to DEFAULT_UFNET
        defaultSurveillanceShouldBeFound("ufnet.lessThanOrEqual=" + DEFAULT_UFNET);

        // Get all the surveillanceList where ufnet is less than or equal to SMALLER_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.lessThanOrEqual=" + SMALLER_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet is less than DEFAULT_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.lessThan=" + DEFAULT_UFNET);

        // Get all the surveillanceList where ufnet is less than UPDATED_UFNET
        defaultSurveillanceShouldBeFound("ufnet.lessThan=" + UPDATED_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByUfnetIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where ufnet is greater than DEFAULT_UFNET
        defaultSurveillanceShouldNotBeFound("ufnet.greaterThan=" + DEFAULT_UFNET);

        // Get all the surveillanceList where ufnet is greater than SMALLER_UFNET
        defaultSurveillanceShouldBeFound("ufnet.greaterThan=" + SMALLER_UFNET);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience equals to DEFAULT_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.equals=" + DEFAULT_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience equals to UPDATED_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.equals=" + UPDATED_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience not equals to DEFAULT_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.notEquals=" + DEFAULT_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience not equals to UPDATED_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.notEquals=" + UPDATED_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience in DEFAULT_ETAT_CONSCIENCE or UPDATED_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.in=" + DEFAULT_ETAT_CONSCIENCE + "," + UPDATED_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience equals to UPDATED_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.in=" + UPDATED_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience is not null
        defaultSurveillanceShouldBeFound("etatConscience.specified=true");

        // Get all the surveillanceList where etatConscience is null
        defaultSurveillanceShouldNotBeFound("etatConscience.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience is greater than or equal to DEFAULT_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.greaterThanOrEqual=" + DEFAULT_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience is greater than or equal to UPDATED_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.greaterThanOrEqual=" + UPDATED_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience is less than or equal to DEFAULT_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.lessThanOrEqual=" + DEFAULT_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience is less than or equal to SMALLER_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.lessThanOrEqual=" + SMALLER_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience is less than DEFAULT_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.lessThan=" + DEFAULT_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience is less than UPDATED_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.lessThan=" + UPDATED_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEtatConscienceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where etatConscience is greater than DEFAULT_ETAT_CONSCIENCE
        defaultSurveillanceShouldNotBeFound("etatConscience.greaterThan=" + DEFAULT_ETAT_CONSCIENCE);

        // Get all the surveillanceList where etatConscience is greater than SMALLER_ETAT_CONSCIENCE
        defaultSurveillanceShouldBeFound("etatConscience.greaterThan=" + SMALLER_ETAT_CONSCIENCE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique equals to DEFAULT_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.equals=" + DEFAULT_EUPNEIQUE);

        // Get all the surveillanceList where eupneique equals to UPDATED_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.equals=" + UPDATED_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique not equals to DEFAULT_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.notEquals=" + DEFAULT_EUPNEIQUE);

        // Get all the surveillanceList where eupneique not equals to UPDATED_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.notEquals=" + UPDATED_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique in DEFAULT_EUPNEIQUE or UPDATED_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.in=" + DEFAULT_EUPNEIQUE + "," + UPDATED_EUPNEIQUE);

        // Get all the surveillanceList where eupneique equals to UPDATED_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.in=" + UPDATED_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique is not null
        defaultSurveillanceShouldBeFound("eupneique.specified=true");

        // Get all the surveillanceList where eupneique is null
        defaultSurveillanceShouldNotBeFound("eupneique.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique is greater than or equal to DEFAULT_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.greaterThanOrEqual=" + DEFAULT_EUPNEIQUE);

        // Get all the surveillanceList where eupneique is greater than or equal to UPDATED_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.greaterThanOrEqual=" + UPDATED_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique is less than or equal to DEFAULT_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.lessThanOrEqual=" + DEFAULT_EUPNEIQUE);

        // Get all the surveillanceList where eupneique is less than or equal to SMALLER_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.lessThanOrEqual=" + SMALLER_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique is less than DEFAULT_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.lessThan=" + DEFAULT_EUPNEIQUE);

        // Get all the surveillanceList where eupneique is less than UPDATED_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.lessThan=" + UPDATED_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByEupneiqueIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where eupneique is greater than DEFAULT_EUPNEIQUE
        defaultSurveillanceShouldNotBeFound("eupneique.greaterThan=" + DEFAULT_EUPNEIQUE);

        // Get all the surveillanceList where eupneique is greater than SMALLER_EUPNEIQUE
        defaultSurveillanceShouldBeFound("eupneique.greaterThan=" + SMALLER_EUPNEIQUE);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar equals to DEFAULT_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.equals=" + DEFAULT_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar equals to UPDATED_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.equals=" + UPDATED_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar not equals to DEFAULT_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.notEquals=" + DEFAULT_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar not equals to UPDATED_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.notEquals=" + UPDATED_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar in DEFAULT_RESTITUTION_PAR or UPDATED_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.in=" + DEFAULT_RESTITUTION_PAR + "," + UPDATED_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar equals to UPDATED_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.in=" + UPDATED_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar is not null
        defaultSurveillanceShouldBeFound("restitutionPar.specified=true");

        // Get all the surveillanceList where restitutionPar is null
        defaultSurveillanceShouldNotBeFound("restitutionPar.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar is greater than or equal to DEFAULT_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.greaterThanOrEqual=" + DEFAULT_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar is greater than or equal to UPDATED_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.greaterThanOrEqual=" + UPDATED_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar is less than or equal to DEFAULT_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.lessThanOrEqual=" + DEFAULT_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar is less than or equal to SMALLER_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.lessThanOrEqual=" + SMALLER_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsLessThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar is less than DEFAULT_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.lessThan=" + DEFAULT_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar is less than UPDATED_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.lessThan=" + UPDATED_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByRestitutionParIsGreaterThanSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where restitutionPar is greater than DEFAULT_RESTITUTION_PAR
        defaultSurveillanceShouldNotBeFound("restitutionPar.greaterThan=" + DEFAULT_RESTITUTION_PAR);

        // Get all the surveillanceList where restitutionPar is greater than SMALLER_RESTITUTION_PAR
        defaultSurveillanceShouldBeFound("restitutionPar.greaterThan=" + SMALLER_RESTITUTION_PAR);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByAutreComplicationIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where autreComplication equals to DEFAULT_AUTRE_COMPLICATION
        defaultSurveillanceShouldBeFound("autreComplication.equals=" + DEFAULT_AUTRE_COMPLICATION);

        // Get all the surveillanceList where autreComplication equals to UPDATED_AUTRE_COMPLICATION
        defaultSurveillanceShouldNotBeFound("autreComplication.equals=" + UPDATED_AUTRE_COMPLICATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByAutreComplicationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where autreComplication not equals to DEFAULT_AUTRE_COMPLICATION
        defaultSurveillanceShouldNotBeFound("autreComplication.notEquals=" + DEFAULT_AUTRE_COMPLICATION);

        // Get all the surveillanceList where autreComplication not equals to UPDATED_AUTRE_COMPLICATION
        defaultSurveillanceShouldBeFound("autreComplication.notEquals=" + UPDATED_AUTRE_COMPLICATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByAutreComplicationIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where autreComplication in DEFAULT_AUTRE_COMPLICATION or UPDATED_AUTRE_COMPLICATION
        defaultSurveillanceShouldBeFound("autreComplication.in=" + DEFAULT_AUTRE_COMPLICATION + "," + UPDATED_AUTRE_COMPLICATION);

        // Get all the surveillanceList where autreComplication equals to UPDATED_AUTRE_COMPLICATION
        defaultSurveillanceShouldNotBeFound("autreComplication.in=" + UPDATED_AUTRE_COMPLICATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByAutreComplicationIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where autreComplication is not null
        defaultSurveillanceShouldBeFound("autreComplication.specified=true");

        // Get all the surveillanceList where autreComplication is null
        defaultSurveillanceShouldNotBeFound("autreComplication.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByAutreComplicationContainsSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where autreComplication contains DEFAULT_AUTRE_COMPLICATION
        defaultSurveillanceShouldBeFound("autreComplication.contains=" + DEFAULT_AUTRE_COMPLICATION);

        // Get all the surveillanceList where autreComplication contains UPDATED_AUTRE_COMPLICATION
        defaultSurveillanceShouldNotBeFound("autreComplication.contains=" + UPDATED_AUTRE_COMPLICATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByAutreComplicationNotContainsSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where autreComplication does not contain DEFAULT_AUTRE_COMPLICATION
        defaultSurveillanceShouldNotBeFound("autreComplication.doesNotContain=" + DEFAULT_AUTRE_COMPLICATION);

        // Get all the surveillanceList where autreComplication does not contain UPDATED_AUTRE_COMPLICATION
        defaultSurveillanceShouldBeFound("autreComplication.doesNotContain=" + UPDATED_AUTRE_COMPLICATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByObservationIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where observation equals to DEFAULT_OBSERVATION
        defaultSurveillanceShouldBeFound("observation.equals=" + DEFAULT_OBSERVATION);

        // Get all the surveillanceList where observation equals to UPDATED_OBSERVATION
        defaultSurveillanceShouldNotBeFound("observation.equals=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByObservationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where observation not equals to DEFAULT_OBSERVATION
        defaultSurveillanceShouldNotBeFound("observation.notEquals=" + DEFAULT_OBSERVATION);

        // Get all the surveillanceList where observation not equals to UPDATED_OBSERVATION
        defaultSurveillanceShouldBeFound("observation.notEquals=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByObservationIsInShouldWork() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where observation in DEFAULT_OBSERVATION or UPDATED_OBSERVATION
        defaultSurveillanceShouldBeFound("observation.in=" + DEFAULT_OBSERVATION + "," + UPDATED_OBSERVATION);

        // Get all the surveillanceList where observation equals to UPDATED_OBSERVATION
        defaultSurveillanceShouldNotBeFound("observation.in=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByObservationIsNullOrNotNull() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where observation is not null
        defaultSurveillanceShouldBeFound("observation.specified=true");

        // Get all the surveillanceList where observation is null
        defaultSurveillanceShouldNotBeFound("observation.specified=false");
    }

    @Test
    @Transactional
    public void getAllSurveillancesByObservationContainsSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where observation contains DEFAULT_OBSERVATION
        defaultSurveillanceShouldBeFound("observation.contains=" + DEFAULT_OBSERVATION);

        // Get all the surveillanceList where observation contains UPDATED_OBSERVATION
        defaultSurveillanceShouldNotBeFound("observation.contains=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByObservationNotContainsSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);

        // Get all the surveillanceList where observation does not contain DEFAULT_OBSERVATION
        defaultSurveillanceShouldNotBeFound("observation.doesNotContain=" + DEFAULT_OBSERVATION);

        // Get all the surveillanceList where observation does not contain UPDATED_OBSERVATION
        defaultSurveillanceShouldBeFound("observation.doesNotContain=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllSurveillancesByTraitementIsEqualToSomething() throws Exception {
        // Initialize the database
        surveillanceRepository.saveAndFlush(surveillance);
        TraitementPerdialyse traitement = TraitementPerdialyseResourceIT.createEntity(em);
        em.persist(traitement);
        em.flush();
        surveillance.setTraitement(traitement);
        surveillanceRepository.saveAndFlush(surveillance);
        Long traitementId = traitement.getId();

        // Get all the surveillanceList where traitement equals to traitementId
        defaultSurveillanceShouldBeFound("traitementId.equals=" + traitementId);

        // Get all the surveillanceList where traitement equals to traitementId + 1
        defaultSurveillanceShouldNotBeFound("traitementId.equals=" + (traitementId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSurveillanceShouldBeFound(String filter) throws Exception {
        restSurveillanceMockMvc
            .perform(get("/api/surveillances?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(surveillance.getId().intValue())))
            .andExpect(jsonPath("$.[*].infirmier").value(hasItem(DEFAULT_INFIRMIER)))
            .andExpect(jsonPath("$.[*].poste").value(hasItem(DEFAULT_POSTE)))
            .andExpect(jsonPath("$.[*].generateur").value(hasItem(DEFAULT_GENERATEUR)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT.toString())))
            .andExpect(jsonPath("$.[*].poid").value(hasItem(DEFAULT_POID.doubleValue())))
            .andExpect(jsonPath("$.[*].ufnet").value(hasItem(DEFAULT_UFNET.doubleValue())))
            .andExpect(jsonPath("$.[*].etatConscience").value(hasItem(DEFAULT_ETAT_CONSCIENCE)))
            .andExpect(jsonPath("$.[*].eupneique").value(hasItem(DEFAULT_EUPNEIQUE)))
            .andExpect(jsonPath("$.[*].restitutionPar").value(hasItem(DEFAULT_RESTITUTION_PAR)))
            .andExpect(jsonPath("$.[*].autreComplication").value(hasItem(DEFAULT_AUTRE_COMPLICATION)))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)));

        // Check, that the count call also returns 1
        restSurveillanceMockMvc
            .perform(get("/api/surveillances/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSurveillanceShouldNotBeFound(String filter) throws Exception {
        restSurveillanceMockMvc
            .perform(get("/api/surveillances?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSurveillanceMockMvc
            .perform(get("/api/surveillances/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingSurveillance() throws Exception {
        // Get the surveillance
        restSurveillanceMockMvc.perform(get("/api/surveillances/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSurveillance() throws Exception {
        // Initialize the database
        surveillanceService.save(surveillance);

        int databaseSizeBeforeUpdate = surveillanceRepository.findAll().size();

        // Update the surveillance
        Surveillance updatedSurveillance = surveillanceRepository.findById(surveillance.getId()).get();
        // Disconnect from session so that the updates on updatedSurveillance are not directly saved in db
        em.detach(updatedSurveillance);
        updatedSurveillance
            .infirmier(UPDATED_INFIRMIER)
            .poste(UPDATED_POSTE)
            .generateur(UPDATED_GENERATEUR)
            .statut(UPDATED_STATUT)
            .poid(UPDATED_POID)
            .ufnet(UPDATED_UFNET)
            .etatConscience(UPDATED_ETAT_CONSCIENCE)
            .eupneique(UPDATED_EUPNEIQUE)
            .restitutionPar(UPDATED_RESTITUTION_PAR)
            .autreComplication(UPDATED_AUTRE_COMPLICATION)
            .observation(UPDATED_OBSERVATION);

        restSurveillanceMockMvc
            .perform(
                put("/api/surveillances")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSurveillance))
            )
            .andExpect(status().isOk());

        // Validate the Surveillance in the database
        List<Surveillance> surveillanceList = surveillanceRepository.findAll();
        assertThat(surveillanceList).hasSize(databaseSizeBeforeUpdate);
        Surveillance testSurveillance = surveillanceList.get(surveillanceList.size() - 1);
        assertThat(testSurveillance.getInfirmier()).isEqualTo(UPDATED_INFIRMIER);
        assertThat(testSurveillance.getPoste()).isEqualTo(UPDATED_POSTE);
        assertThat(testSurveillance.getGenerateur()).isEqualTo(UPDATED_GENERATEUR);
        assertThat(testSurveillance.getStatut()).isEqualTo(UPDATED_STATUT);
        assertThat(testSurveillance.getPoid()).isEqualTo(UPDATED_POID);
        assertThat(testSurveillance.getUfnet()).isEqualTo(UPDATED_UFNET);
        assertThat(testSurveillance.getEtatConscience()).isEqualTo(UPDATED_ETAT_CONSCIENCE);
        assertThat(testSurveillance.getEupneique()).isEqualTo(UPDATED_EUPNEIQUE);
        assertThat(testSurveillance.getRestitutionPar()).isEqualTo(UPDATED_RESTITUTION_PAR);
        assertThat(testSurveillance.getAutreComplication()).isEqualTo(UPDATED_AUTRE_COMPLICATION);
        assertThat(testSurveillance.getObservation()).isEqualTo(UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void updateNonExistingSurveillance() throws Exception {
        int databaseSizeBeforeUpdate = surveillanceRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSurveillanceMockMvc
            .perform(
                put("/api/surveillances").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(surveillance))
            )
            .andExpect(status().isBadRequest());

        // Validate the Surveillance in the database
        List<Surveillance> surveillanceList = surveillanceRepository.findAll();
        assertThat(surveillanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSurveillance() throws Exception {
        // Initialize the database
        surveillanceService.save(surveillance);

        int databaseSizeBeforeDelete = surveillanceRepository.findAll().size();

        // Delete the surveillance
        restSurveillanceMockMvc
            .perform(delete("/api/surveillances/{id}", surveillance.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Surveillance> surveillanceList = surveillanceRepository.findAll();
        assertThat(surveillanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
