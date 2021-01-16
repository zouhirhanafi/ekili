package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.ExamenClinique;
import ma.ekili.repository.ExamenCliniqueRepository;
import ma.ekili.service.ExamenCliniqueService;
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
 * Integration tests for the {@link ExamenCliniqueResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ExamenCliniqueResourceIT {
    private static final Double DEFAULT_GCS = 1D;
    private static final Double UPDATED_GCS = 2D;

    private static final String DEFAULT_PA = "AAAAAAAAAA";
    private static final String UPDATED_PA = "BBBBBBBBBB";

    private static final Integer DEFAULT_DIURESE = 1;
    private static final Integer UPDATED_DIURESE = 2;

    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepository;

    @Autowired
    private ExamenCliniqueService examenCliniqueService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExamenCliniqueMockMvc;

    private ExamenClinique examenClinique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamenClinique createEntity(EntityManager em) {
        ExamenClinique examenClinique = new ExamenClinique().gcs(DEFAULT_GCS).pa(DEFAULT_PA).diurese(DEFAULT_DIURESE).autre(DEFAULT_AUTRE);
        return examenClinique;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExamenClinique createUpdatedEntity(EntityManager em) {
        ExamenClinique examenClinique = new ExamenClinique().gcs(UPDATED_GCS).pa(UPDATED_PA).diurese(UPDATED_DIURESE).autre(UPDATED_AUTRE);
        return examenClinique;
    }

    @BeforeEach
    public void initTest() {
        examenClinique = createEntity(em);
    }

    @Test
    @Transactional
    public void createExamenClinique() throws Exception {
        int databaseSizeBeforeCreate = examenCliniqueRepository.findAll().size();
        // Create the ExamenClinique
        restExamenCliniqueMockMvc
            .perform(
                post("/api/examen-cliniques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenClinique))
            )
            .andExpect(status().isCreated());

        // Validate the ExamenClinique in the database
        List<ExamenClinique> examenCliniqueList = examenCliniqueRepository.findAll();
        assertThat(examenCliniqueList).hasSize(databaseSizeBeforeCreate + 1);
        ExamenClinique testExamenClinique = examenCliniqueList.get(examenCliniqueList.size() - 1);
        assertThat(testExamenClinique.getGcs()).isEqualTo(DEFAULT_GCS);
        assertThat(testExamenClinique.getPa()).isEqualTo(DEFAULT_PA);
        assertThat(testExamenClinique.getDiurese()).isEqualTo(DEFAULT_DIURESE);
        assertThat(testExamenClinique.getAutre()).isEqualTo(DEFAULT_AUTRE);
    }

    @Test
    @Transactional
    public void createExamenCliniqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = examenCliniqueRepository.findAll().size();

        // Create the ExamenClinique with an existing ID
        examenClinique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExamenCliniqueMockMvc
            .perform(
                post("/api/examen-cliniques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenClinique))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExamenClinique in the database
        List<ExamenClinique> examenCliniqueList = examenCliniqueRepository.findAll();
        assertThat(examenCliniqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllExamenCliniques() throws Exception {
        // Initialize the database
        examenCliniqueRepository.saveAndFlush(examenClinique);

        // Get all the examenCliniqueList
        restExamenCliniqueMockMvc
            .perform(get("/api/examen-cliniques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(examenClinique.getId().intValue())))
            .andExpect(jsonPath("$.[*].gcs").value(hasItem(DEFAULT_GCS.doubleValue())))
            .andExpect(jsonPath("$.[*].pa").value(hasItem(DEFAULT_PA)))
            .andExpect(jsonPath("$.[*].diurese").value(hasItem(DEFAULT_DIURESE)))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)));
    }

    @Test
    @Transactional
    public void getExamenClinique() throws Exception {
        // Initialize the database
        examenCliniqueRepository.saveAndFlush(examenClinique);

        // Get the examenClinique
        restExamenCliniqueMockMvc
            .perform(get("/api/examen-cliniques/{id}", examenClinique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(examenClinique.getId().intValue()))
            .andExpect(jsonPath("$.gcs").value(DEFAULT_GCS.doubleValue()))
            .andExpect(jsonPath("$.pa").value(DEFAULT_PA))
            .andExpect(jsonPath("$.diurese").value(DEFAULT_DIURESE))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE));
    }

    @Test
    @Transactional
    public void getNonExistingExamenClinique() throws Exception {
        // Get the examenClinique
        restExamenCliniqueMockMvc.perform(get("/api/examen-cliniques/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExamenClinique() throws Exception {
        // Initialize the database
        examenCliniqueService.save(examenClinique);

        int databaseSizeBeforeUpdate = examenCliniqueRepository.findAll().size();

        // Update the examenClinique
        ExamenClinique updatedExamenClinique = examenCliniqueRepository.findById(examenClinique.getId()).get();
        // Disconnect from session so that the updates on updatedExamenClinique are not directly saved in db
        em.detach(updatedExamenClinique);
        updatedExamenClinique.gcs(UPDATED_GCS).pa(UPDATED_PA).diurese(UPDATED_DIURESE).autre(UPDATED_AUTRE);

        restExamenCliniqueMockMvc
            .perform(
                put("/api/examen-cliniques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedExamenClinique))
            )
            .andExpect(status().isOk());

        // Validate the ExamenClinique in the database
        List<ExamenClinique> examenCliniqueList = examenCliniqueRepository.findAll();
        assertThat(examenCliniqueList).hasSize(databaseSizeBeforeUpdate);
        ExamenClinique testExamenClinique = examenCliniqueList.get(examenCliniqueList.size() - 1);
        assertThat(testExamenClinique.getGcs()).isEqualTo(UPDATED_GCS);
        assertThat(testExamenClinique.getPa()).isEqualTo(UPDATED_PA);
        assertThat(testExamenClinique.getDiurese()).isEqualTo(UPDATED_DIURESE);
        assertThat(testExamenClinique.getAutre()).isEqualTo(UPDATED_AUTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingExamenClinique() throws Exception {
        int databaseSizeBeforeUpdate = examenCliniqueRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamenCliniqueMockMvc
            .perform(
                put("/api/examen-cliniques")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenClinique))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExamenClinique in the database
        List<ExamenClinique> examenCliniqueList = examenCliniqueRepository.findAll();
        assertThat(examenCliniqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExamenClinique() throws Exception {
        // Initialize the database
        examenCliniqueService.save(examenClinique);

        int databaseSizeBeforeDelete = examenCliniqueRepository.findAll().size();

        // Delete the examenClinique
        restExamenCliniqueMockMvc
            .perform(delete("/api/examen-cliniques/{id}", examenClinique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ExamenClinique> examenCliniqueList = examenCliniqueRepository.findAll();
        assertThat(examenCliniqueList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
