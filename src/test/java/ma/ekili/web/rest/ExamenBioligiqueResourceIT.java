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
import ma.ekili.domain.ExamenBioligique;
import ma.ekili.repository.ExamenBioligiqueRepository;
import ma.ekili.service.ExamenBioligiqueService;
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
 * Integration tests for the {@link ExamenBioligiqueResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExamenBioligiqueResourceIT {
    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_UREE = 1D;
    private static final Double UPDATED_UREE = 2D;

    private static final Double DEFAULT_CREAT = 1D;
    private static final Double UPDATED_CREAT = 2D;

    private static final Double DEFAULT_K = 1D;
    private static final Double UPDATED_K = 2D;

    private static final Double DEFAULT_NA = 1D;
    private static final Double UPDATED_NA = 2D;

    private static final Double DEFAULT_CA = 1D;
    private static final Double UPDATED_CA = 2D;

    private static final Double DEFAULT_CRP = 1D;
    private static final Double UPDATED_CRP = 2D;

    private static final Double DEFAULT_HB = 1D;
    private static final Double UPDATED_HB = 2D;

    private static final Double DEFAULT_GB = 1D;
    private static final Double UPDATED_GB = 2D;

    private static final Double DEFAULT_PLT = 1D;
    private static final Double UPDATED_PLT = 2D;

    private static final String DEFAULT_AC_HBS = "AAAAAAAAAA";
    private static final String UPDATED_AC_HBS = "BBBBBBBBBB";

    private static final Double DEFAULT_AG_HBS = 1D;
    private static final Double UPDATED_AG_HBS = 2D;

    private static final String DEFAULT_HBC = "AAAAAAAAAA";
    private static final String UPDATED_HBC = "BBBBBBBBBB";

    private static final String DEFAULT_AC_HVC = "AAAAAAAAAA";
    private static final String UPDATED_AC_HVC = "BBBBBBBBBB";

    private static final String DEFAULT_VIH = "AAAAAAAAAA";
    private static final String UPDATED_VIH = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    @Autowired
    private ExamenBioligiqueRepository examenBioligiqueRepository;

    @Autowired
    private ExamenBioligiqueService examenBioligiqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExamenBioligiqueMockMvc;

    private ExamenBioligique examenBioligique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamenBioligique createEntity(EntityManager em) {
        ExamenBioligique examenBioligique = new ExamenBioligique()
            .date(DEFAULT_DATE)
            .uree(DEFAULT_UREE)
            .creat(DEFAULT_CREAT)
            .k(DEFAULT_K)
            .na(DEFAULT_NA)
            .ca(DEFAULT_CA)
            .crp(DEFAULT_CRP)
            .hb(DEFAULT_HB)
            .gb(DEFAULT_GB)
            .plt(DEFAULT_PLT)
            .acHbs(DEFAULT_AC_HBS)
            .agHbs(DEFAULT_AG_HBS)
            .hbc(DEFAULT_HBC)
            .acHvc(DEFAULT_AC_HVC)
            .vih(DEFAULT_VIH)
            .autre(DEFAULT_AUTRE);
        return examenBioligique;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamenBioligique createUpdatedEntity(EntityManager em) {
        ExamenBioligique examenBioligique = new ExamenBioligique()
            .date(UPDATED_DATE)
            .uree(UPDATED_UREE)
            .creat(UPDATED_CREAT)
            .k(UPDATED_K)
            .na(UPDATED_NA)
            .ca(UPDATED_CA)
            .crp(UPDATED_CRP)
            .hb(UPDATED_HB)
            .gb(UPDATED_GB)
            .plt(UPDATED_PLT)
            .acHbs(UPDATED_AC_HBS)
            .agHbs(UPDATED_AG_HBS)
            .hbc(UPDATED_HBC)
            .acHvc(UPDATED_AC_HVC)
            .vih(UPDATED_VIH)
            .autre(UPDATED_AUTRE);
        return examenBioligique;
    }

    @BeforeEach
    public void initTest() {
        examenBioligique = createEntity(em);
    }

    @Test
    @Transactional
    public void createExamenBioligique() throws Exception {
        int databaseSizeBeforeCreate = examenBioligiqueRepository.findAll().size();
        // Create the ExamenBioligique
        restExamenBioligiqueMockMvc
            .perform(
                post("/api/examen-bioligiques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenBioligique))
            )
            .andExpect(status().isCreated());

        // Validate the ExamenBioligique in the database
        List<ExamenBioligique> examenBioligiqueList = examenBioligiqueRepository.findAll();
        assertThat(examenBioligiqueList).hasSize(databaseSizeBeforeCreate + 1);
        ExamenBioligique testExamenBioligique = examenBioligiqueList.get(examenBioligiqueList.size() - 1);
        assertThat(testExamenBioligique.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testExamenBioligique.getUree()).isEqualTo(DEFAULT_UREE);
        assertThat(testExamenBioligique.getCreat()).isEqualTo(DEFAULT_CREAT);
        assertThat(testExamenBioligique.getK()).isEqualTo(DEFAULT_K);
        assertThat(testExamenBioligique.getNa()).isEqualTo(DEFAULT_NA);
        assertThat(testExamenBioligique.getCa()).isEqualTo(DEFAULT_CA);
        assertThat(testExamenBioligique.getCrp()).isEqualTo(DEFAULT_CRP);
        assertThat(testExamenBioligique.getHb()).isEqualTo(DEFAULT_HB);
        assertThat(testExamenBioligique.getGb()).isEqualTo(DEFAULT_GB);
        assertThat(testExamenBioligique.getPlt()).isEqualTo(DEFAULT_PLT);
        assertThat(testExamenBioligique.getAcHbs()).isEqualTo(DEFAULT_AC_HBS);
        assertThat(testExamenBioligique.getAgHbs()).isEqualTo(DEFAULT_AG_HBS);
        assertThat(testExamenBioligique.getHbc()).isEqualTo(DEFAULT_HBC);
        assertThat(testExamenBioligique.getAcHvc()).isEqualTo(DEFAULT_AC_HVC);
        assertThat(testExamenBioligique.getVih()).isEqualTo(DEFAULT_VIH);
        assertThat(testExamenBioligique.getAutre()).isEqualTo(DEFAULT_AUTRE);
    }

    @Test
    @Transactional
    public void createExamenBioligiqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = examenBioligiqueRepository.findAll().size();

        // Create the ExamenBioligique with an existing ID
        examenBioligique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExamenBioligiqueMockMvc
            .perform(
                post("/api/examen-bioligiques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenBioligique))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExamenBioligique in the database
        List<ExamenBioligique> examenBioligiqueList = examenBioligiqueRepository.findAll();
        assertThat(examenBioligiqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExamenBioligiques() throws Exception {
        // Initialize the database
        examenBioligiqueRepository.saveAndFlush(examenBioligique);

        // Get all the examenBioligiqueList
        restExamenBioligiqueMockMvc
            .perform(get("/api/examen-bioligiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(examenBioligique.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].uree").value(hasItem(DEFAULT_UREE.doubleValue())))
            .andExpect(jsonPath("$.[*].creat").value(hasItem(DEFAULT_CREAT.doubleValue())))
            .andExpect(jsonPath("$.[*].k").value(hasItem(DEFAULT_K.doubleValue())))
            .andExpect(jsonPath("$.[*].na").value(hasItem(DEFAULT_NA.doubleValue())))
            .andExpect(jsonPath("$.[*].ca").value(hasItem(DEFAULT_CA.doubleValue())))
            .andExpect(jsonPath("$.[*].crp").value(hasItem(DEFAULT_CRP.doubleValue())))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].gb").value(hasItem(DEFAULT_GB.doubleValue())))
            .andExpect(jsonPath("$.[*].plt").value(hasItem(DEFAULT_PLT.doubleValue())))
            .andExpect(jsonPath("$.[*].acHbs").value(hasItem(DEFAULT_AC_HBS)))
            .andExpect(jsonPath("$.[*].agHbs").value(hasItem(DEFAULT_AG_HBS.doubleValue())))
            .andExpect(jsonPath("$.[*].hbc").value(hasItem(DEFAULT_HBC)))
            .andExpect(jsonPath("$.[*].acHvc").value(hasItem(DEFAULT_AC_HVC)))
            .andExpect(jsonPath("$.[*].vih").value(hasItem(DEFAULT_VIH)))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)));
    }

    @Test
    @Transactional
    public void getExamenBioligique() throws Exception {
        // Initialize the database
        examenBioligiqueRepository.saveAndFlush(examenBioligique);

        // Get the examenBioligique
        restExamenBioligiqueMockMvc
            .perform(get("/api/examen-bioligiques/{id}", examenBioligique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(examenBioligique.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.uree").value(DEFAULT_UREE.doubleValue()))
            .andExpect(jsonPath("$.creat").value(DEFAULT_CREAT.doubleValue()))
            .andExpect(jsonPath("$.k").value(DEFAULT_K.doubleValue()))
            .andExpect(jsonPath("$.na").value(DEFAULT_NA.doubleValue()))
            .andExpect(jsonPath("$.ca").value(DEFAULT_CA.doubleValue()))
            .andExpect(jsonPath("$.crp").value(DEFAULT_CRP.doubleValue()))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.gb").value(DEFAULT_GB.doubleValue()))
            .andExpect(jsonPath("$.plt").value(DEFAULT_PLT.doubleValue()))
            .andExpect(jsonPath("$.acHbs").value(DEFAULT_AC_HBS))
            .andExpect(jsonPath("$.agHbs").value(DEFAULT_AG_HBS.doubleValue()))
            .andExpect(jsonPath("$.hbc").value(DEFAULT_HBC))
            .andExpect(jsonPath("$.acHvc").value(DEFAULT_AC_HVC))
            .andExpect(jsonPath("$.vih").value(DEFAULT_VIH))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE));
    }

    @Test
    @Transactional
    public void getNonExistingExamenBioligique() throws Exception {
        // Get the examenBioligique
        restExamenBioligiqueMockMvc.perform(get("/api/examen-bioligiques/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExamenBioligique() throws Exception {
        // Initialize the database
        examenBioligiqueService.save(examenBioligique);

        int databaseSizeBeforeUpdate = examenBioligiqueRepository.findAll().size();

        // Update the examenBioligique
        ExamenBioligique updatedExamenBioligique = examenBioligiqueRepository.findById(examenBioligique.getId()).get();
        // Disconnect from session so that the updates on updatedExamenBioligique are not directly saved in db
        em.detach(updatedExamenBioligique);
        updatedExamenBioligique
            .date(UPDATED_DATE)
            .uree(UPDATED_UREE)
            .creat(UPDATED_CREAT)
            .k(UPDATED_K)
            .na(UPDATED_NA)
            .ca(UPDATED_CA)
            .crp(UPDATED_CRP)
            .hb(UPDATED_HB)
            .gb(UPDATED_GB)
            .plt(UPDATED_PLT)
            .acHbs(UPDATED_AC_HBS)
            .agHbs(UPDATED_AG_HBS)
            .hbc(UPDATED_HBC)
            .acHvc(UPDATED_AC_HVC)
            .vih(UPDATED_VIH)
            .autre(UPDATED_AUTRE);

        restExamenBioligiqueMockMvc
            .perform(
                put("/api/examen-bioligiques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedExamenBioligique))
            )
            .andExpect(status().isOk());

        // Validate the ExamenBioligique in the database
        List<ExamenBioligique> examenBioligiqueList = examenBioligiqueRepository.findAll();
        assertThat(examenBioligiqueList).hasSize(databaseSizeBeforeUpdate);
        ExamenBioligique testExamenBioligique = examenBioligiqueList.get(examenBioligiqueList.size() - 1);
        assertThat(testExamenBioligique.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testExamenBioligique.getUree()).isEqualTo(UPDATED_UREE);
        assertThat(testExamenBioligique.getCreat()).isEqualTo(UPDATED_CREAT);
        assertThat(testExamenBioligique.getK()).isEqualTo(UPDATED_K);
        assertThat(testExamenBioligique.getNa()).isEqualTo(UPDATED_NA);
        assertThat(testExamenBioligique.getCa()).isEqualTo(UPDATED_CA);
        assertThat(testExamenBioligique.getCrp()).isEqualTo(UPDATED_CRP);
        assertThat(testExamenBioligique.getHb()).isEqualTo(UPDATED_HB);
        assertThat(testExamenBioligique.getGb()).isEqualTo(UPDATED_GB);
        assertThat(testExamenBioligique.getPlt()).isEqualTo(UPDATED_PLT);
        assertThat(testExamenBioligique.getAcHbs()).isEqualTo(UPDATED_AC_HBS);
        assertThat(testExamenBioligique.getAgHbs()).isEqualTo(UPDATED_AG_HBS);
        assertThat(testExamenBioligique.getHbc()).isEqualTo(UPDATED_HBC);
        assertThat(testExamenBioligique.getAcHvc()).isEqualTo(UPDATED_AC_HVC);
        assertThat(testExamenBioligique.getVih()).isEqualTo(UPDATED_VIH);
        assertThat(testExamenBioligique.getAutre()).isEqualTo(UPDATED_AUTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingExamenBioligique() throws Exception {
        int databaseSizeBeforeUpdate = examenBioligiqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamenBioligiqueMockMvc
            .perform(
                put("/api/examen-bioligiques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenBioligique))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExamenBioligique in the database
        List<ExamenBioligique> examenBioligiqueList = examenBioligiqueRepository.findAll();
        assertThat(examenBioligiqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExamenBioligique() throws Exception {
        // Initialize the database
        examenBioligiqueService.save(examenBioligique);

        int databaseSizeBeforeDelete = examenBioligiqueRepository.findAll().size();

        // Delete the examenBioligique
        restExamenBioligiqueMockMvc
            .perform(delete("/api/examen-bioligiques/{id}", examenBioligique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExamenBioligique> examenBioligiqueList = examenBioligiqueRepository.findAll();
        assertThat(examenBioligiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
