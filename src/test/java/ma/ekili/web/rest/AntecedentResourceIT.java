package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.Antecedent;
import ma.ekili.repository.AntecedentRepository;
import ma.ekili.service.AntecedentService;
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
 * Integration tests for the {@link AntecedentResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AntecedentResourceIT {
    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    @Autowired
    private AntecedentRepository antecedentRepository;

    @Autowired
    private AntecedentService antecedentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAntecedentMockMvc;

    private Antecedent antecedent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Antecedent createEntity(EntityManager em) {
        Antecedent antecedent = new Antecedent().autre(DEFAULT_AUTRE);
        return antecedent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Antecedent createUpdatedEntity(EntityManager em) {
        Antecedent antecedent = new Antecedent().autre(UPDATED_AUTRE);
        return antecedent;
    }

    @BeforeEach
    public void initTest() {
        antecedent = createEntity(em);
    }

    @Test
    @Transactional
    public void createAntecedent() throws Exception {
        int databaseSizeBeforeCreate = antecedentRepository.findAll().size();
        // Create the Antecedent
        restAntecedentMockMvc
            .perform(
                post("/api/antecedents").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(antecedent))
            )
            .andExpect(status().isCreated());

        // Validate the Antecedent in the database
        List<Antecedent> antecedentList = antecedentRepository.findAll();
        assertThat(antecedentList).hasSize(databaseSizeBeforeCreate + 1);
        Antecedent testAntecedent = antecedentList.get(antecedentList.size() - 1);
        assertThat(testAntecedent.getAutre()).isEqualTo(DEFAULT_AUTRE);
    }

    @Test
    @Transactional
    public void createAntecedentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = antecedentRepository.findAll().size();

        // Create the Antecedent with an existing ID
        antecedent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAntecedentMockMvc
            .perform(
                post("/api/antecedents").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(antecedent))
            )
            .andExpect(status().isBadRequest());

        // Validate the Antecedent in the database
        List<Antecedent> antecedentList = antecedentRepository.findAll();
        assertThat(antecedentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAntecedents() throws Exception {
        // Initialize the database
        antecedentRepository.saveAndFlush(antecedent);

        // Get all the antecedentList
        restAntecedentMockMvc
            .perform(get("/api/antecedents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(antecedent.getId().intValue())))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)));
    }

    @Test
    @Transactional
    public void getAntecedent() throws Exception {
        // Initialize the database
        antecedentRepository.saveAndFlush(antecedent);

        // Get the antecedent
        restAntecedentMockMvc
            .perform(get("/api/antecedents/{id}", antecedent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(antecedent.getId().intValue()))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE));
    }

    @Test
    @Transactional
    public void getNonExistingAntecedent() throws Exception {
        // Get the antecedent
        restAntecedentMockMvc.perform(get("/api/antecedents/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAntecedent() throws Exception {
        // Initialize the database
        antecedentService.save(antecedent);

        int databaseSizeBeforeUpdate = antecedentRepository.findAll().size();

        // Update the antecedent
        Antecedent updatedAntecedent = antecedentRepository.findById(antecedent.getId()).get();
        // Disconnect from session so that the updates on updatedAntecedent are not directly saved in db
        em.detach(updatedAntecedent);
        updatedAntecedent.autre(UPDATED_AUTRE);

        restAntecedentMockMvc
            .perform(
                put("/api/antecedents")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedAntecedent))
            )
            .andExpect(status().isOk());

        // Validate the Antecedent in the database
        List<Antecedent> antecedentList = antecedentRepository.findAll();
        assertThat(antecedentList).hasSize(databaseSizeBeforeUpdate);
        Antecedent testAntecedent = antecedentList.get(antecedentList.size() - 1);
        assertThat(testAntecedent.getAutre()).isEqualTo(UPDATED_AUTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingAntecedent() throws Exception {
        int databaseSizeBeforeUpdate = antecedentRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAntecedentMockMvc
            .perform(put("/api/antecedents").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(antecedent)))
            .andExpect(status().isBadRequest());

        // Validate the Antecedent in the database
        List<Antecedent> antecedentList = antecedentRepository.findAll();
        assertThat(antecedentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAntecedent() throws Exception {
        // Initialize the database
        antecedentService.save(antecedent);

        int databaseSizeBeforeDelete = antecedentRepository.findAll().size();

        // Delete the antecedent
        restAntecedentMockMvc
            .perform(delete("/api/antecedents/{id}", antecedent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Antecedent> antecedentList = antecedentRepository.findAll();
        assertThat(antecedentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
