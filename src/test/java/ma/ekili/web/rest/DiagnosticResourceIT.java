package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.Diagnostic;
import ma.ekili.repository.DiagnosticRepository;
import ma.ekili.service.DiagnosticService;
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
 * Integration tests for the {@link DiagnosticResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DiagnosticResourceIT {
    private static final Integer DEFAULT_HVB = 1;
    private static final Integer UPDATED_HVB = 2;

    private static final Integer DEFAULT_HVC = 1;
    private static final Integer UPDATED_HVC = 2;

    private static final Integer DEFAULT_VIH = 1;
    private static final Integer UPDATED_VIH = 2;

    private static final Double DEFAULT_POID_SEC = 1D;
    private static final Double UPDATED_POID_SEC = 2D;

    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    @Autowired
    private DiagnosticRepository diagnosticRepository;

    @Autowired
    private DiagnosticService diagnosticService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDiagnosticMockMvc;

    private Diagnostic diagnostic;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Diagnostic createEntity(EntityManager em) {
        Diagnostic diagnostic = new Diagnostic()
            .hvb(DEFAULT_HVB)
            .hvc(DEFAULT_HVC)
            .vih(DEFAULT_VIH)
            .poidSec(DEFAULT_POID_SEC)
            .autre(DEFAULT_AUTRE);
        return diagnostic;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Diagnostic createUpdatedEntity(EntityManager em) {
        Diagnostic diagnostic = new Diagnostic()
            .hvb(UPDATED_HVB)
            .hvc(UPDATED_HVC)
            .vih(UPDATED_VIH)
            .poidSec(UPDATED_POID_SEC)
            .autre(UPDATED_AUTRE);
        return diagnostic;
    }

    @BeforeEach
    public void initTest() {
        diagnostic = createEntity(em);
    }

    @Test
    @Transactional
    public void createDiagnostic() throws Exception {
        int databaseSizeBeforeCreate = diagnosticRepository.findAll().size();
        // Create the Diagnostic
        restDiagnosticMockMvc
            .perform(
                post("/api/diagnostics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diagnostic))
            )
            .andExpect(status().isCreated());

        // Validate the Diagnostic in the database
        List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
        assertThat(diagnosticList).hasSize(databaseSizeBeforeCreate + 1);
        Diagnostic testDiagnostic = diagnosticList.get(diagnosticList.size() - 1);
        assertThat(testDiagnostic.getHvb()).isEqualTo(DEFAULT_HVB);
        assertThat(testDiagnostic.getHvc()).isEqualTo(DEFAULT_HVC);
        assertThat(testDiagnostic.getVih()).isEqualTo(DEFAULT_VIH);
        assertThat(testDiagnostic.getPoidSec()).isEqualTo(DEFAULT_POID_SEC);
        assertThat(testDiagnostic.getAutre()).isEqualTo(DEFAULT_AUTRE);
    }

    @Test
    @Transactional
    public void createDiagnosticWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diagnosticRepository.findAll().size();

        // Create the Diagnostic with an existing ID
        diagnostic.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiagnosticMockMvc
            .perform(
                post("/api/diagnostics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diagnostic))
            )
            .andExpect(status().isBadRequest());

        // Validate the Diagnostic in the database
        List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
        assertThat(diagnosticList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDiagnostics() throws Exception {
        // Initialize the database
        diagnosticRepository.saveAndFlush(diagnostic);

        // Get all the diagnosticList
        restDiagnosticMockMvc
            .perform(get("/api/diagnostics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(diagnostic.getId().intValue())))
            .andExpect(jsonPath("$.[*].hvb").value(hasItem(DEFAULT_HVB)))
            .andExpect(jsonPath("$.[*].hvc").value(hasItem(DEFAULT_HVC)))
            .andExpect(jsonPath("$.[*].vih").value(hasItem(DEFAULT_VIH)))
            .andExpect(jsonPath("$.[*].poidSec").value(hasItem(DEFAULT_POID_SEC.doubleValue())))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)));
    }

    @Test
    @Transactional
    public void getDiagnostic() throws Exception {
        // Initialize the database
        diagnosticRepository.saveAndFlush(diagnostic);

        // Get the diagnostic
        restDiagnosticMockMvc
            .perform(get("/api/diagnostics/{id}", diagnostic.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(diagnostic.getId().intValue()))
            .andExpect(jsonPath("$.hvb").value(DEFAULT_HVB))
            .andExpect(jsonPath("$.hvc").value(DEFAULT_HVC))
            .andExpect(jsonPath("$.vih").value(DEFAULT_VIH))
            .andExpect(jsonPath("$.poidSec").value(DEFAULT_POID_SEC.doubleValue()))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE));
    }

    @Test
    @Transactional
    public void getNonExistingDiagnostic() throws Exception {
        // Get the diagnostic
        restDiagnosticMockMvc.perform(get("/api/diagnostics/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDiagnostic() throws Exception {
        // Initialize the database
        diagnosticService.save(diagnostic);

        int databaseSizeBeforeUpdate = diagnosticRepository.findAll().size();

        // Update the diagnostic
        Diagnostic updatedDiagnostic = diagnosticRepository.findById(diagnostic.getId()).get();
        // Disconnect from session so that the updates on updatedDiagnostic are not directly saved in db
        em.detach(updatedDiagnostic);
        updatedDiagnostic.hvb(UPDATED_HVB).hvc(UPDATED_HVC).vih(UPDATED_VIH).poidSec(UPDATED_POID_SEC).autre(UPDATED_AUTRE);

        restDiagnosticMockMvc
            .perform(
                put("/api/diagnostics")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedDiagnostic))
            )
            .andExpect(status().isOk());

        // Validate the Diagnostic in the database
        List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
        assertThat(diagnosticList).hasSize(databaseSizeBeforeUpdate);
        Diagnostic testDiagnostic = diagnosticList.get(diagnosticList.size() - 1);
        assertThat(testDiagnostic.getHvb()).isEqualTo(UPDATED_HVB);
        assertThat(testDiagnostic.getHvc()).isEqualTo(UPDATED_HVC);
        assertThat(testDiagnostic.getVih()).isEqualTo(UPDATED_VIH);
        assertThat(testDiagnostic.getPoidSec()).isEqualTo(UPDATED_POID_SEC);
        assertThat(testDiagnostic.getAutre()).isEqualTo(UPDATED_AUTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingDiagnostic() throws Exception {
        int databaseSizeBeforeUpdate = diagnosticRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiagnosticMockMvc
            .perform(put("/api/diagnostics").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(diagnostic)))
            .andExpect(status().isBadRequest());

        // Validate the Diagnostic in the database
        List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
        assertThat(diagnosticList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDiagnostic() throws Exception {
        // Initialize the database
        diagnosticService.save(diagnostic);

        int databaseSizeBeforeDelete = diagnosticRepository.findAll().size();

        // Delete the diagnostic
        restDiagnosticMockMvc
            .perform(delete("/api/diagnostics/{id}", diagnostic.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Diagnostic> diagnosticList = diagnosticRepository.findAll();
        assertThat(diagnosticList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
