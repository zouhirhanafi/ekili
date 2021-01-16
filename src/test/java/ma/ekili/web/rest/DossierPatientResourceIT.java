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
import ma.ekili.domain.Antecedent;
import ma.ekili.domain.Diagnostic;
import ma.ekili.domain.DossierPatient;
import ma.ekili.domain.ExamenClinique;
import ma.ekili.domain.IndicationHd;
import ma.ekili.repository.DossierPatientRepository;
import ma.ekili.service.DossierPatientQueryService;
import ma.ekili.service.DossierPatientService;
import ma.ekili.service.dto.DossierPatientCriteria;
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
 * Integration tests for the {@link DossierPatientResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DossierPatientResourceIT {
    private static final Long DEFAULT_IP = 1L;
    private static final Long UPDATED_IP = 2L;
    private static final Long SMALLER_IP = 1L - 1L;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final Integer DEFAULT_GENRE = 1;
    private static final Integer UPDATED_GENRE = 2;
    private static final Integer SMALLER_GENRE = 1 - 1;

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AMO = 1;
    private static final Integer UPDATED_AMO = 2;
    private static final Integer SMALLER_AMO = 1 - 1;

    private static final Integer DEFAULT_TYPE_CENTRE_ORIGINE = 1;
    private static final Integer UPDATED_TYPE_CENTRE_ORIGINE = 2;
    private static final Integer SMALLER_TYPE_CENTRE_ORIGINE = 1 - 1;

    private static final Integer DEFAULT_VILLE_CENTRE_ORIGINE = 1;
    private static final Integer UPDATED_VILLE_CENTRE_ORIGINE = 2;
    private static final Integer SMALLER_VILLE_CENTRE_ORIGINE = 1 - 1;

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NAISSANCE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NAISSANCE = LocalDate.ofEpochDay(-1L);

    @Autowired
    private DossierPatientRepository dossierPatientRepository;

    @Autowired
    private DossierPatientService dossierPatientService;

    @Autowired
    private DossierPatientQueryService dossierPatientQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDossierPatientMockMvc;

    private DossierPatient dossierPatient;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DossierPatient createEntity(EntityManager em) {
        DossierPatient dossierPatient = new DossierPatient()
            .ip(DEFAULT_IP)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .genre(DEFAULT_GENRE)
            .tel(DEFAULT_TEL)
            .adresse(DEFAULT_ADRESSE)
            .amo(DEFAULT_AMO)
            .typeCentreOrigine(DEFAULT_TYPE_CENTRE_ORIGINE)
            .villeCentreOrigine(DEFAULT_VILLE_CENTRE_ORIGINE)
            .observation(DEFAULT_OBSERVATION)
            .naissance(DEFAULT_NAISSANCE);
        return dossierPatient;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DossierPatient createUpdatedEntity(EntityManager em) {
        DossierPatient dossierPatient = new DossierPatient()
            .ip(UPDATED_IP)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .genre(UPDATED_GENRE)
            .tel(UPDATED_TEL)
            .adresse(UPDATED_ADRESSE)
            .amo(UPDATED_AMO)
            .typeCentreOrigine(UPDATED_TYPE_CENTRE_ORIGINE)
            .villeCentreOrigine(UPDATED_VILLE_CENTRE_ORIGINE)
            .observation(UPDATED_OBSERVATION)
            .naissance(UPDATED_NAISSANCE);
        return dossierPatient;
    }

    @BeforeEach
    public void initTest() {
        dossierPatient = createEntity(em);
    }

    @Test
    @Transactional
    public void createDossierPatient() throws Exception {
        int databaseSizeBeforeCreate = dossierPatientRepository.findAll().size();
        // Create the DossierPatient
        restDossierPatientMockMvc
            .perform(
                post("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isCreated());

        // Validate the DossierPatient in the database
        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeCreate + 1);
        DossierPatient testDossierPatient = dossierPatientList.get(dossierPatientList.size() - 1);
        assertThat(testDossierPatient.getIp()).isEqualTo(DEFAULT_IP);
        assertThat(testDossierPatient.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testDossierPatient.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testDossierPatient.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testDossierPatient.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testDossierPatient.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testDossierPatient.getAmo()).isEqualTo(DEFAULT_AMO);
        assertThat(testDossierPatient.getTypeCentreOrigine()).isEqualTo(DEFAULT_TYPE_CENTRE_ORIGINE);
        assertThat(testDossierPatient.getVilleCentreOrigine()).isEqualTo(DEFAULT_VILLE_CENTRE_ORIGINE);
        assertThat(testDossierPatient.getObservation()).isEqualTo(DEFAULT_OBSERVATION);
        assertThat(testDossierPatient.getNaissance()).isEqualTo(DEFAULT_NAISSANCE);
    }

    @Test
    @Transactional
    public void createDossierPatientWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dossierPatientRepository.findAll().size();

        // Create the DossierPatient with an existing ID
        dossierPatient.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDossierPatientMockMvc
            .perform(
                post("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isBadRequest());

        // Validate the DossierPatient in the database
        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIpIsRequired() throws Exception {
        int databaseSizeBeforeTest = dossierPatientRepository.findAll().size();
        // set the field null
        dossierPatient.setIp(null);

        // Create the DossierPatient, which fails.

        restDossierPatientMockMvc
            .perform(
                post("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isBadRequest());

        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = dossierPatientRepository.findAll().size();
        // set the field null
        dossierPatient.setNom(null);

        // Create the DossierPatient, which fails.

        restDossierPatientMockMvc
            .perform(
                post("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isBadRequest());

        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = dossierPatientRepository.findAll().size();
        // set the field null
        dossierPatient.setPrenom(null);

        // Create the DossierPatient, which fails.

        restDossierPatientMockMvc
            .perform(
                post("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isBadRequest());

        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGenreIsRequired() throws Exception {
        int databaseSizeBeforeTest = dossierPatientRepository.findAll().size();
        // set the field null
        dossierPatient.setGenre(null);

        // Create the DossierPatient, which fails.

        restDossierPatientMockMvc
            .perform(
                post("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isBadRequest());

        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDossierPatients() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList
        restDossierPatientMockMvc
            .perform(get("/api/dossier-patients?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossierPatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].amo").value(hasItem(DEFAULT_AMO)))
            .andExpect(jsonPath("$.[*].typeCentreOrigine").value(hasItem(DEFAULT_TYPE_CENTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].villeCentreOrigine").value(hasItem(DEFAULT_VILLE_CENTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)))
            .andExpect(jsonPath("$.[*].naissance").value(hasItem(DEFAULT_NAISSANCE.toString())));
    }

    @Test
    @Transactional
    public void getDossierPatient() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get the dossierPatient
        restDossierPatientMockMvc
            .perform(get("/api/dossier-patients/{id}", dossierPatient.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dossierPatient.getId().intValue()))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP.intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.amo").value(DEFAULT_AMO))
            .andExpect(jsonPath("$.typeCentreOrigine").value(DEFAULT_TYPE_CENTRE_ORIGINE))
            .andExpect(jsonPath("$.villeCentreOrigine").value(DEFAULT_VILLE_CENTRE_ORIGINE))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION))
            .andExpect(jsonPath("$.naissance").value(DEFAULT_NAISSANCE.toString()));
    }

    @Test
    @Transactional
    public void getDossierPatientsByIdFiltering() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        Long id = dossierPatient.getId();

        defaultDossierPatientShouldBeFound("id.equals=" + id);
        defaultDossierPatientShouldNotBeFound("id.notEquals=" + id);

        defaultDossierPatientShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDossierPatientShouldNotBeFound("id.greaterThan=" + id);

        defaultDossierPatientShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDossierPatientShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip equals to DEFAULT_IP
        defaultDossierPatientShouldBeFound("ip.equals=" + DEFAULT_IP);

        // Get all the dossierPatientList where ip equals to UPDATED_IP
        defaultDossierPatientShouldNotBeFound("ip.equals=" + UPDATED_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip not equals to DEFAULT_IP
        defaultDossierPatientShouldNotBeFound("ip.notEquals=" + DEFAULT_IP);

        // Get all the dossierPatientList where ip not equals to UPDATED_IP
        defaultDossierPatientShouldBeFound("ip.notEquals=" + UPDATED_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip in DEFAULT_IP or UPDATED_IP
        defaultDossierPatientShouldBeFound("ip.in=" + DEFAULT_IP + "," + UPDATED_IP);

        // Get all the dossierPatientList where ip equals to UPDATED_IP
        defaultDossierPatientShouldNotBeFound("ip.in=" + UPDATED_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip is not null
        defaultDossierPatientShouldBeFound("ip.specified=true");

        // Get all the dossierPatientList where ip is null
        defaultDossierPatientShouldNotBeFound("ip.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip is greater than or equal to DEFAULT_IP
        defaultDossierPatientShouldBeFound("ip.greaterThanOrEqual=" + DEFAULT_IP);

        // Get all the dossierPatientList where ip is greater than or equal to UPDATED_IP
        defaultDossierPatientShouldNotBeFound("ip.greaterThanOrEqual=" + UPDATED_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip is less than or equal to DEFAULT_IP
        defaultDossierPatientShouldBeFound("ip.lessThanOrEqual=" + DEFAULT_IP);

        // Get all the dossierPatientList where ip is less than or equal to SMALLER_IP
        defaultDossierPatientShouldNotBeFound("ip.lessThanOrEqual=" + SMALLER_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsLessThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip is less than DEFAULT_IP
        defaultDossierPatientShouldNotBeFound("ip.lessThan=" + DEFAULT_IP);

        // Get all the dossierPatientList where ip is less than UPDATED_IP
        defaultDossierPatientShouldBeFound("ip.lessThan=" + UPDATED_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIpIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where ip is greater than DEFAULT_IP
        defaultDossierPatientShouldNotBeFound("ip.greaterThan=" + DEFAULT_IP);

        // Get all the dossierPatientList where ip is greater than SMALLER_IP
        defaultDossierPatientShouldBeFound("ip.greaterThan=" + SMALLER_IP);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNomIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where nom equals to DEFAULT_NOM
        defaultDossierPatientShouldBeFound("nom.equals=" + DEFAULT_NOM);

        // Get all the dossierPatientList where nom equals to UPDATED_NOM
        defaultDossierPatientShouldNotBeFound("nom.equals=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNomIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where nom not equals to DEFAULT_NOM
        defaultDossierPatientShouldNotBeFound("nom.notEquals=" + DEFAULT_NOM);

        // Get all the dossierPatientList where nom not equals to UPDATED_NOM
        defaultDossierPatientShouldBeFound("nom.notEquals=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNomIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where nom in DEFAULT_NOM or UPDATED_NOM
        defaultDossierPatientShouldBeFound("nom.in=" + DEFAULT_NOM + "," + UPDATED_NOM);

        // Get all the dossierPatientList where nom equals to UPDATED_NOM
        defaultDossierPatientShouldNotBeFound("nom.in=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNomIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where nom is not null
        defaultDossierPatientShouldBeFound("nom.specified=true");

        // Get all the dossierPatientList where nom is null
        defaultDossierPatientShouldNotBeFound("nom.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNomContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where nom contains DEFAULT_NOM
        defaultDossierPatientShouldBeFound("nom.contains=" + DEFAULT_NOM);

        // Get all the dossierPatientList where nom contains UPDATED_NOM
        defaultDossierPatientShouldNotBeFound("nom.contains=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNomNotContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where nom does not contain DEFAULT_NOM
        defaultDossierPatientShouldNotBeFound("nom.doesNotContain=" + DEFAULT_NOM);

        // Get all the dossierPatientList where nom does not contain UPDATED_NOM
        defaultDossierPatientShouldBeFound("nom.doesNotContain=" + UPDATED_NOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByPrenomIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where prenom equals to DEFAULT_PRENOM
        defaultDossierPatientShouldBeFound("prenom.equals=" + DEFAULT_PRENOM);

        // Get all the dossierPatientList where prenom equals to UPDATED_PRENOM
        defaultDossierPatientShouldNotBeFound("prenom.equals=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByPrenomIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where prenom not equals to DEFAULT_PRENOM
        defaultDossierPatientShouldNotBeFound("prenom.notEquals=" + DEFAULT_PRENOM);

        // Get all the dossierPatientList where prenom not equals to UPDATED_PRENOM
        defaultDossierPatientShouldBeFound("prenom.notEquals=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByPrenomIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where prenom in DEFAULT_PRENOM or UPDATED_PRENOM
        defaultDossierPatientShouldBeFound("prenom.in=" + DEFAULT_PRENOM + "," + UPDATED_PRENOM);

        // Get all the dossierPatientList where prenom equals to UPDATED_PRENOM
        defaultDossierPatientShouldNotBeFound("prenom.in=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByPrenomIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where prenom is not null
        defaultDossierPatientShouldBeFound("prenom.specified=true");

        // Get all the dossierPatientList where prenom is null
        defaultDossierPatientShouldNotBeFound("prenom.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByPrenomContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where prenom contains DEFAULT_PRENOM
        defaultDossierPatientShouldBeFound("prenom.contains=" + DEFAULT_PRENOM);

        // Get all the dossierPatientList where prenom contains UPDATED_PRENOM
        defaultDossierPatientShouldNotBeFound("prenom.contains=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByPrenomNotContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where prenom does not contain DEFAULT_PRENOM
        defaultDossierPatientShouldNotBeFound("prenom.doesNotContain=" + DEFAULT_PRENOM);

        // Get all the dossierPatientList where prenom does not contain UPDATED_PRENOM
        defaultDossierPatientShouldBeFound("prenom.doesNotContain=" + UPDATED_PRENOM);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre equals to DEFAULT_GENRE
        defaultDossierPatientShouldBeFound("genre.equals=" + DEFAULT_GENRE);

        // Get all the dossierPatientList where genre equals to UPDATED_GENRE
        defaultDossierPatientShouldNotBeFound("genre.equals=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre not equals to DEFAULT_GENRE
        defaultDossierPatientShouldNotBeFound("genre.notEquals=" + DEFAULT_GENRE);

        // Get all the dossierPatientList where genre not equals to UPDATED_GENRE
        defaultDossierPatientShouldBeFound("genre.notEquals=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre in DEFAULT_GENRE or UPDATED_GENRE
        defaultDossierPatientShouldBeFound("genre.in=" + DEFAULT_GENRE + "," + UPDATED_GENRE);

        // Get all the dossierPatientList where genre equals to UPDATED_GENRE
        defaultDossierPatientShouldNotBeFound("genre.in=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre is not null
        defaultDossierPatientShouldBeFound("genre.specified=true");

        // Get all the dossierPatientList where genre is null
        defaultDossierPatientShouldNotBeFound("genre.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre is greater than or equal to DEFAULT_GENRE
        defaultDossierPatientShouldBeFound("genre.greaterThanOrEqual=" + DEFAULT_GENRE);

        // Get all the dossierPatientList where genre is greater than or equal to UPDATED_GENRE
        defaultDossierPatientShouldNotBeFound("genre.greaterThanOrEqual=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre is less than or equal to DEFAULT_GENRE
        defaultDossierPatientShouldBeFound("genre.lessThanOrEqual=" + DEFAULT_GENRE);

        // Get all the dossierPatientList where genre is less than or equal to SMALLER_GENRE
        defaultDossierPatientShouldNotBeFound("genre.lessThanOrEqual=" + SMALLER_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsLessThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre is less than DEFAULT_GENRE
        defaultDossierPatientShouldNotBeFound("genre.lessThan=" + DEFAULT_GENRE);

        // Get all the dossierPatientList where genre is less than UPDATED_GENRE
        defaultDossierPatientShouldBeFound("genre.lessThan=" + UPDATED_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByGenreIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where genre is greater than DEFAULT_GENRE
        defaultDossierPatientShouldNotBeFound("genre.greaterThan=" + DEFAULT_GENRE);

        // Get all the dossierPatientList where genre is greater than SMALLER_GENRE
        defaultDossierPatientShouldBeFound("genre.greaterThan=" + SMALLER_GENRE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTelIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where tel equals to DEFAULT_TEL
        defaultDossierPatientShouldBeFound("tel.equals=" + DEFAULT_TEL);

        // Get all the dossierPatientList where tel equals to UPDATED_TEL
        defaultDossierPatientShouldNotBeFound("tel.equals=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTelIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where tel not equals to DEFAULT_TEL
        defaultDossierPatientShouldNotBeFound("tel.notEquals=" + DEFAULT_TEL);

        // Get all the dossierPatientList where tel not equals to UPDATED_TEL
        defaultDossierPatientShouldBeFound("tel.notEquals=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTelIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where tel in DEFAULT_TEL or UPDATED_TEL
        defaultDossierPatientShouldBeFound("tel.in=" + DEFAULT_TEL + "," + UPDATED_TEL);

        // Get all the dossierPatientList where tel equals to UPDATED_TEL
        defaultDossierPatientShouldNotBeFound("tel.in=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTelIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where tel is not null
        defaultDossierPatientShouldBeFound("tel.specified=true");

        // Get all the dossierPatientList where tel is null
        defaultDossierPatientShouldNotBeFound("tel.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTelContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where tel contains DEFAULT_TEL
        defaultDossierPatientShouldBeFound("tel.contains=" + DEFAULT_TEL);

        // Get all the dossierPatientList where tel contains UPDATED_TEL
        defaultDossierPatientShouldNotBeFound("tel.contains=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTelNotContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where tel does not contain DEFAULT_TEL
        defaultDossierPatientShouldNotBeFound("tel.doesNotContain=" + DEFAULT_TEL);

        // Get all the dossierPatientList where tel does not contain UPDATED_TEL
        defaultDossierPatientShouldBeFound("tel.doesNotContain=" + UPDATED_TEL);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAdresseIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where adresse equals to DEFAULT_ADRESSE
        defaultDossierPatientShouldBeFound("adresse.equals=" + DEFAULT_ADRESSE);

        // Get all the dossierPatientList where adresse equals to UPDATED_ADRESSE
        defaultDossierPatientShouldNotBeFound("adresse.equals=" + UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAdresseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where adresse not equals to DEFAULT_ADRESSE
        defaultDossierPatientShouldNotBeFound("adresse.notEquals=" + DEFAULT_ADRESSE);

        // Get all the dossierPatientList where adresse not equals to UPDATED_ADRESSE
        defaultDossierPatientShouldBeFound("adresse.notEquals=" + UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAdresseIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where adresse in DEFAULT_ADRESSE or UPDATED_ADRESSE
        defaultDossierPatientShouldBeFound("adresse.in=" + DEFAULT_ADRESSE + "," + UPDATED_ADRESSE);

        // Get all the dossierPatientList where adresse equals to UPDATED_ADRESSE
        defaultDossierPatientShouldNotBeFound("adresse.in=" + UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAdresseIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where adresse is not null
        defaultDossierPatientShouldBeFound("adresse.specified=true");

        // Get all the dossierPatientList where adresse is null
        defaultDossierPatientShouldNotBeFound("adresse.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAdresseContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where adresse contains DEFAULT_ADRESSE
        defaultDossierPatientShouldBeFound("adresse.contains=" + DEFAULT_ADRESSE);

        // Get all the dossierPatientList where adresse contains UPDATED_ADRESSE
        defaultDossierPatientShouldNotBeFound("adresse.contains=" + UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAdresseNotContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where adresse does not contain DEFAULT_ADRESSE
        defaultDossierPatientShouldNotBeFound("adresse.doesNotContain=" + DEFAULT_ADRESSE);

        // Get all the dossierPatientList where adresse does not contain UPDATED_ADRESSE
        defaultDossierPatientShouldBeFound("adresse.doesNotContain=" + UPDATED_ADRESSE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo equals to DEFAULT_AMO
        defaultDossierPatientShouldBeFound("amo.equals=" + DEFAULT_AMO);

        // Get all the dossierPatientList where amo equals to UPDATED_AMO
        defaultDossierPatientShouldNotBeFound("amo.equals=" + UPDATED_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo not equals to DEFAULT_AMO
        defaultDossierPatientShouldNotBeFound("amo.notEquals=" + DEFAULT_AMO);

        // Get all the dossierPatientList where amo not equals to UPDATED_AMO
        defaultDossierPatientShouldBeFound("amo.notEquals=" + UPDATED_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo in DEFAULT_AMO or UPDATED_AMO
        defaultDossierPatientShouldBeFound("amo.in=" + DEFAULT_AMO + "," + UPDATED_AMO);

        // Get all the dossierPatientList where amo equals to UPDATED_AMO
        defaultDossierPatientShouldNotBeFound("amo.in=" + UPDATED_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo is not null
        defaultDossierPatientShouldBeFound("amo.specified=true");

        // Get all the dossierPatientList where amo is null
        defaultDossierPatientShouldNotBeFound("amo.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo is greater than or equal to DEFAULT_AMO
        defaultDossierPatientShouldBeFound("amo.greaterThanOrEqual=" + DEFAULT_AMO);

        // Get all the dossierPatientList where amo is greater than or equal to UPDATED_AMO
        defaultDossierPatientShouldNotBeFound("amo.greaterThanOrEqual=" + UPDATED_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo is less than or equal to DEFAULT_AMO
        defaultDossierPatientShouldBeFound("amo.lessThanOrEqual=" + DEFAULT_AMO);

        // Get all the dossierPatientList where amo is less than or equal to SMALLER_AMO
        defaultDossierPatientShouldNotBeFound("amo.lessThanOrEqual=" + SMALLER_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsLessThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo is less than DEFAULT_AMO
        defaultDossierPatientShouldNotBeFound("amo.lessThan=" + DEFAULT_AMO);

        // Get all the dossierPatientList where amo is less than UPDATED_AMO
        defaultDossierPatientShouldBeFound("amo.lessThan=" + UPDATED_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAmoIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where amo is greater than DEFAULT_AMO
        defaultDossierPatientShouldNotBeFound("amo.greaterThan=" + DEFAULT_AMO);

        // Get all the dossierPatientList where amo is greater than SMALLER_AMO
        defaultDossierPatientShouldBeFound("amo.greaterThan=" + SMALLER_AMO);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine equals to DEFAULT_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.equals=" + DEFAULT_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine equals to UPDATED_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.equals=" + UPDATED_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine not equals to DEFAULT_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.notEquals=" + DEFAULT_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine not equals to UPDATED_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.notEquals=" + UPDATED_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine in DEFAULT_TYPE_CENTRE_ORIGINE or UPDATED_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.in=" + DEFAULT_TYPE_CENTRE_ORIGINE + "," + UPDATED_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine equals to UPDATED_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.in=" + UPDATED_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine is not null
        defaultDossierPatientShouldBeFound("typeCentreOrigine.specified=true");

        // Get all the dossierPatientList where typeCentreOrigine is null
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine is greater than or equal to DEFAULT_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.greaterThanOrEqual=" + DEFAULT_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine is greater than or equal to UPDATED_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.greaterThanOrEqual=" + UPDATED_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine is less than or equal to DEFAULT_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.lessThanOrEqual=" + DEFAULT_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine is less than or equal to SMALLER_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.lessThanOrEqual=" + SMALLER_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsLessThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine is less than DEFAULT_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.lessThan=" + DEFAULT_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine is less than UPDATED_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.lessThan=" + UPDATED_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByTypeCentreOrigineIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where typeCentreOrigine is greater than DEFAULT_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("typeCentreOrigine.greaterThan=" + DEFAULT_TYPE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where typeCentreOrigine is greater than SMALLER_TYPE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("typeCentreOrigine.greaterThan=" + SMALLER_TYPE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine equals to DEFAULT_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.equals=" + DEFAULT_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine equals to UPDATED_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.equals=" + UPDATED_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine not equals to DEFAULT_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.notEquals=" + DEFAULT_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine not equals to UPDATED_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.notEquals=" + UPDATED_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine in DEFAULT_VILLE_CENTRE_ORIGINE or UPDATED_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.in=" + DEFAULT_VILLE_CENTRE_ORIGINE + "," + UPDATED_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine equals to UPDATED_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.in=" + UPDATED_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine is not null
        defaultDossierPatientShouldBeFound("villeCentreOrigine.specified=true");

        // Get all the dossierPatientList where villeCentreOrigine is null
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine is greater than or equal to DEFAULT_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.greaterThanOrEqual=" + DEFAULT_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine is greater than or equal to UPDATED_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.greaterThanOrEqual=" + UPDATED_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine is less than or equal to DEFAULT_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.lessThanOrEqual=" + DEFAULT_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine is less than or equal to SMALLER_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.lessThanOrEqual=" + SMALLER_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsLessThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine is less than DEFAULT_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.lessThan=" + DEFAULT_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine is less than UPDATED_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.lessThan=" + UPDATED_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByVilleCentreOrigineIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where villeCentreOrigine is greater than DEFAULT_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldNotBeFound("villeCentreOrigine.greaterThan=" + DEFAULT_VILLE_CENTRE_ORIGINE);

        // Get all the dossierPatientList where villeCentreOrigine is greater than SMALLER_VILLE_CENTRE_ORIGINE
        defaultDossierPatientShouldBeFound("villeCentreOrigine.greaterThan=" + SMALLER_VILLE_CENTRE_ORIGINE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByObservationIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where observation equals to DEFAULT_OBSERVATION
        defaultDossierPatientShouldBeFound("observation.equals=" + DEFAULT_OBSERVATION);

        // Get all the dossierPatientList where observation equals to UPDATED_OBSERVATION
        defaultDossierPatientShouldNotBeFound("observation.equals=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByObservationIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where observation not equals to DEFAULT_OBSERVATION
        defaultDossierPatientShouldNotBeFound("observation.notEquals=" + DEFAULT_OBSERVATION);

        // Get all the dossierPatientList where observation not equals to UPDATED_OBSERVATION
        defaultDossierPatientShouldBeFound("observation.notEquals=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByObservationIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where observation in DEFAULT_OBSERVATION or UPDATED_OBSERVATION
        defaultDossierPatientShouldBeFound("observation.in=" + DEFAULT_OBSERVATION + "," + UPDATED_OBSERVATION);

        // Get all the dossierPatientList where observation equals to UPDATED_OBSERVATION
        defaultDossierPatientShouldNotBeFound("observation.in=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByObservationIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where observation is not null
        defaultDossierPatientShouldBeFound("observation.specified=true");

        // Get all the dossierPatientList where observation is null
        defaultDossierPatientShouldNotBeFound("observation.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByObservationContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where observation contains DEFAULT_OBSERVATION
        defaultDossierPatientShouldBeFound("observation.contains=" + DEFAULT_OBSERVATION);

        // Get all the dossierPatientList where observation contains UPDATED_OBSERVATION
        defaultDossierPatientShouldNotBeFound("observation.contains=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByObservationNotContainsSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where observation does not contain DEFAULT_OBSERVATION
        defaultDossierPatientShouldNotBeFound("observation.doesNotContain=" + DEFAULT_OBSERVATION);

        // Get all the dossierPatientList where observation does not contain UPDATED_OBSERVATION
        defaultDossierPatientShouldBeFound("observation.doesNotContain=" + UPDATED_OBSERVATION);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance equals to DEFAULT_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.equals=" + DEFAULT_NAISSANCE);

        // Get all the dossierPatientList where naissance equals to UPDATED_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.equals=" + UPDATED_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance not equals to DEFAULT_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.notEquals=" + DEFAULT_NAISSANCE);

        // Get all the dossierPatientList where naissance not equals to UPDATED_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.notEquals=" + UPDATED_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsInShouldWork() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance in DEFAULT_NAISSANCE or UPDATED_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.in=" + DEFAULT_NAISSANCE + "," + UPDATED_NAISSANCE);

        // Get all the dossierPatientList where naissance equals to UPDATED_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.in=" + UPDATED_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsNullOrNotNull() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance is not null
        defaultDossierPatientShouldBeFound("naissance.specified=true");

        // Get all the dossierPatientList where naissance is null
        defaultDossierPatientShouldNotBeFound("naissance.specified=false");
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance is greater than or equal to DEFAULT_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.greaterThanOrEqual=" + DEFAULT_NAISSANCE);

        // Get all the dossierPatientList where naissance is greater than or equal to UPDATED_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.greaterThanOrEqual=" + UPDATED_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance is less than or equal to DEFAULT_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.lessThanOrEqual=" + DEFAULT_NAISSANCE);

        // Get all the dossierPatientList where naissance is less than or equal to SMALLER_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.lessThanOrEqual=" + SMALLER_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsLessThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance is less than DEFAULT_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.lessThan=" + DEFAULT_NAISSANCE);

        // Get all the dossierPatientList where naissance is less than UPDATED_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.lessThan=" + UPDATED_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByNaissanceIsGreaterThanSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);

        // Get all the dossierPatientList where naissance is greater than DEFAULT_NAISSANCE
        defaultDossierPatientShouldNotBeFound("naissance.greaterThan=" + DEFAULT_NAISSANCE);

        // Get all the dossierPatientList where naissance is greater than SMALLER_NAISSANCE
        defaultDossierPatientShouldBeFound("naissance.greaterThan=" + SMALLER_NAISSANCE);
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByAntecedentIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);
        Antecedent antecedent = AntecedentResourceIT.createEntity(em);
        em.persist(antecedent);
        em.flush();
        dossierPatient.setAntecedent(antecedent);
        dossierPatientRepository.saveAndFlush(dossierPatient);
        Long antecedentId = antecedent.getId();

        // Get all the dossierPatientList where antecedent equals to antecedentId
        defaultDossierPatientShouldBeFound("antecedentId.equals=" + antecedentId);

        // Get all the dossierPatientList where antecedent equals to antecedentId + 1
        defaultDossierPatientShouldNotBeFound("antecedentId.equals=" + (antecedentId + 1));
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByDiagnosticIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);
        Diagnostic diagnostic = DiagnosticResourceIT.createEntity(em);
        em.persist(diagnostic);
        em.flush();
        dossierPatient.setDiagnostic(diagnostic);
        dossierPatientRepository.saveAndFlush(dossierPatient);
        Long diagnosticId = diagnostic.getId();

        // Get all the dossierPatientList where diagnostic equals to diagnosticId
        defaultDossierPatientShouldBeFound("diagnosticId.equals=" + diagnosticId);

        // Get all the dossierPatientList where diagnostic equals to diagnosticId + 1
        defaultDossierPatientShouldNotBeFound("diagnosticId.equals=" + (diagnosticId + 1));
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByIndicationHdIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);
        IndicationHd indicationHd = IndicationHdResourceIT.createEntity(em);
        em.persist(indicationHd);
        em.flush();
        dossierPatient.setIndicationHd(indicationHd);
        dossierPatientRepository.saveAndFlush(dossierPatient);
        Long indicationHdId = indicationHd.getId();

        // Get all the dossierPatientList where indicationHd equals to indicationHdId
        defaultDossierPatientShouldBeFound("indicationHdId.equals=" + indicationHdId);

        // Get all the dossierPatientList where indicationHd equals to indicationHdId + 1
        defaultDossierPatientShouldNotBeFound("indicationHdId.equals=" + (indicationHdId + 1));
    }

    @Test
    @Transactional
    public void getAllDossierPatientsByExamenCliniqueIsEqualToSomething() throws Exception {
        // Initialize the database
        dossierPatientRepository.saveAndFlush(dossierPatient);
        ExamenClinique examenClinique = ExamenCliniqueResourceIT.createEntity(em);
        em.persist(examenClinique);
        em.flush();
        dossierPatient.setExamenClinique(examenClinique);
        dossierPatientRepository.saveAndFlush(dossierPatient);
        Long examenCliniqueId = examenClinique.getId();

        // Get all the dossierPatientList where examenClinique equals to examenCliniqueId
        defaultDossierPatientShouldBeFound("examenCliniqueId.equals=" + examenCliniqueId);

        // Get all the dossierPatientList where examenClinique equals to examenCliniqueId + 1
        defaultDossierPatientShouldNotBeFound("examenCliniqueId.equals=" + (examenCliniqueId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDossierPatientShouldBeFound(String filter) throws Exception {
        restDossierPatientMockMvc
            .perform(get("/api/dossier-patients?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossierPatient.getId().intValue())))
            .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].amo").value(hasItem(DEFAULT_AMO)))
            .andExpect(jsonPath("$.[*].typeCentreOrigine").value(hasItem(DEFAULT_TYPE_CENTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].villeCentreOrigine").value(hasItem(DEFAULT_VILLE_CENTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION)))
            .andExpect(jsonPath("$.[*].naissance").value(hasItem(DEFAULT_NAISSANCE.toString())));

        // Check, that the count call also returns 1
        restDossierPatientMockMvc
            .perform(get("/api/dossier-patients/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDossierPatientShouldNotBeFound(String filter) throws Exception {
        restDossierPatientMockMvc
            .perform(get("/api/dossier-patients?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDossierPatientMockMvc
            .perform(get("/api/dossier-patients/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    public void getNonExistingDossierPatient() throws Exception {
        // Get the dossierPatient
        restDossierPatientMockMvc.perform(get("/api/dossier-patients/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDossierPatient() throws Exception {
        // Initialize the database
        dossierPatientService.save(dossierPatient);

        int databaseSizeBeforeUpdate = dossierPatientRepository.findAll().size();

        // Update the dossierPatient
        DossierPatient updatedDossierPatient = dossierPatientRepository.findById(dossierPatient.getId()).get();
        // Disconnect from session so that the updates on updatedDossierPatient are not directly saved in db
        em.detach(updatedDossierPatient);
        updatedDossierPatient
            .ip(UPDATED_IP)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .genre(UPDATED_GENRE)
            .tel(UPDATED_TEL)
            .adresse(UPDATED_ADRESSE)
            .amo(UPDATED_AMO)
            .typeCentreOrigine(UPDATED_TYPE_CENTRE_ORIGINE)
            .villeCentreOrigine(UPDATED_VILLE_CENTRE_ORIGINE)
            .observation(UPDATED_OBSERVATION)
            .naissance(UPDATED_NAISSANCE);

        restDossierPatientMockMvc
            .perform(
                put("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDossierPatient))
            )
            .andExpect(status().isOk());

        // Validate the DossierPatient in the database
        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeUpdate);
        DossierPatient testDossierPatient = dossierPatientList.get(dossierPatientList.size() - 1);
        assertThat(testDossierPatient.getIp()).isEqualTo(UPDATED_IP);
        assertThat(testDossierPatient.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testDossierPatient.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testDossierPatient.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testDossierPatient.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testDossierPatient.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testDossierPatient.getAmo()).isEqualTo(UPDATED_AMO);
        assertThat(testDossierPatient.getTypeCentreOrigine()).isEqualTo(UPDATED_TYPE_CENTRE_ORIGINE);
        assertThat(testDossierPatient.getVilleCentreOrigine()).isEqualTo(UPDATED_VILLE_CENTRE_ORIGINE);
        assertThat(testDossierPatient.getObservation()).isEqualTo(UPDATED_OBSERVATION);
        assertThat(testDossierPatient.getNaissance()).isEqualTo(UPDATED_NAISSANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingDossierPatient() throws Exception {
        int databaseSizeBeforeUpdate = dossierPatientRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDossierPatientMockMvc
            .perform(
                put("/api/dossier-patients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(dossierPatient))
            )
            .andExpect(status().isBadRequest());

        // Validate the DossierPatient in the database
        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDossierPatient() throws Exception {
        // Initialize the database
        dossierPatientService.save(dossierPatient);

        int databaseSizeBeforeDelete = dossierPatientRepository.findAll().size();

        // Delete the dossierPatient
        restDossierPatientMockMvc
            .perform(delete("/api/dossier-patients/{id}", dossierPatient.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DossierPatient> dossierPatientList = dossierPatientRepository.findAll();
        assertThat(dossierPatientList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
