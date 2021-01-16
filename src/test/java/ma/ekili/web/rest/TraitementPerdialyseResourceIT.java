package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.TraitementPerdialyse;
import ma.ekili.domain.enumeration.TypeTraitementPerdialyse;
import ma.ekili.repository.TraitementPerdialyseRepository;
import ma.ekili.service.TraitementPerdialyseService;
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
 * Integration tests for the {@link TraitementPerdialyseResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TraitementPerdialyseResourceIT {
    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    private static final TypeTraitementPerdialyse DEFAULT_TYPE = TypeTraitementPerdialyse.P;
    private static final TypeTraitementPerdialyse UPDATED_TYPE = TypeTraitementPerdialyse.S;

    @Autowired
    private TraitementPerdialyseRepository traitementPerdialyseRepository;

    @Autowired
    private TraitementPerdialyseService traitementPerdialyseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTraitementPerdialyseMockMvc;

    private TraitementPerdialyse traitementPerdialyse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TraitementPerdialyse createEntity(EntityManager em) {
        TraitementPerdialyse traitementPerdialyse = new TraitementPerdialyse().autre(DEFAULT_AUTRE).type(DEFAULT_TYPE);
        return traitementPerdialyse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TraitementPerdialyse createUpdatedEntity(EntityManager em) {
        TraitementPerdialyse traitementPerdialyse = new TraitementPerdialyse().autre(UPDATED_AUTRE).type(UPDATED_TYPE);
        return traitementPerdialyse;
    }

    @BeforeEach
    public void initTest() {
        traitementPerdialyse = createEntity(em);
    }

    @Test
    @Transactional
    public void createTraitementPerdialyse() throws Exception {
        int databaseSizeBeforeCreate = traitementPerdialyseRepository.findAll().size();
        // Create the TraitementPerdialyse
        restTraitementPerdialyseMockMvc
            .perform(
                post("/api/traitement-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traitementPerdialyse))
            )
            .andExpect(status().isCreated());

        // Validate the TraitementPerdialyse in the database
        List<TraitementPerdialyse> traitementPerdialyseList = traitementPerdialyseRepository.findAll();
        assertThat(traitementPerdialyseList).hasSize(databaseSizeBeforeCreate + 1);
        TraitementPerdialyse testTraitementPerdialyse = traitementPerdialyseList.get(traitementPerdialyseList.size() - 1);
        assertThat(testTraitementPerdialyse.getAutre()).isEqualTo(DEFAULT_AUTRE);
        assertThat(testTraitementPerdialyse.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createTraitementPerdialyseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = traitementPerdialyseRepository.findAll().size();

        // Create the TraitementPerdialyse with an existing ID
        traitementPerdialyse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTraitementPerdialyseMockMvc
            .perform(
                post("/api/traitement-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traitementPerdialyse))
            )
            .andExpect(status().isBadRequest());

        // Validate the TraitementPerdialyse in the database
        List<TraitementPerdialyse> traitementPerdialyseList = traitementPerdialyseRepository.findAll();
        assertThat(traitementPerdialyseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTraitementPerdialyses() throws Exception {
        // Initialize the database
        traitementPerdialyseRepository.saveAndFlush(traitementPerdialyse);

        // Get all the traitementPerdialyseList
        restTraitementPerdialyseMockMvc
            .perform(get("/api/traitement-perdialyses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(traitementPerdialyse.getId().intValue())))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTraitementPerdialyse() throws Exception {
        // Initialize the database
        traitementPerdialyseRepository.saveAndFlush(traitementPerdialyse);

        // Get the traitementPerdialyse
        restTraitementPerdialyseMockMvc
            .perform(get("/api/traitement-perdialyses/{id}", traitementPerdialyse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(traitementPerdialyse.getId().intValue()))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTraitementPerdialyse() throws Exception {
        // Get the traitementPerdialyse
        restTraitementPerdialyseMockMvc.perform(get("/api/traitement-perdialyses/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTraitementPerdialyse() throws Exception {
        // Initialize the database
        traitementPerdialyseService.save(traitementPerdialyse);

        int databaseSizeBeforeUpdate = traitementPerdialyseRepository.findAll().size();

        // Update the traitementPerdialyse
        TraitementPerdialyse updatedTraitementPerdialyse = traitementPerdialyseRepository.findById(traitementPerdialyse.getId()).get();
        // Disconnect from session so that the updates on updatedTraitementPerdialyse are not directly saved in db
        em.detach(updatedTraitementPerdialyse);
        updatedTraitementPerdialyse.autre(UPDATED_AUTRE).type(UPDATED_TYPE);

        restTraitementPerdialyseMockMvc
            .perform(
                put("/api/traitement-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedTraitementPerdialyse))
            )
            .andExpect(status().isOk());

        // Validate the TraitementPerdialyse in the database
        List<TraitementPerdialyse> traitementPerdialyseList = traitementPerdialyseRepository.findAll();
        assertThat(traitementPerdialyseList).hasSize(databaseSizeBeforeUpdate);
        TraitementPerdialyse testTraitementPerdialyse = traitementPerdialyseList.get(traitementPerdialyseList.size() - 1);
        assertThat(testTraitementPerdialyse.getAutre()).isEqualTo(UPDATED_AUTRE);
        assertThat(testTraitementPerdialyse.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTraitementPerdialyse() throws Exception {
        int databaseSizeBeforeUpdate = traitementPerdialyseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTraitementPerdialyseMockMvc
            .perform(
                put("/api/traitement-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(traitementPerdialyse))
            )
            .andExpect(status().isBadRequest());

        // Validate the TraitementPerdialyse in the database
        List<TraitementPerdialyse> traitementPerdialyseList = traitementPerdialyseRepository.findAll();
        assertThat(traitementPerdialyseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTraitementPerdialyse() throws Exception {
        // Initialize the database
        traitementPerdialyseService.save(traitementPerdialyse);

        int databaseSizeBeforeDelete = traitementPerdialyseRepository.findAll().size();

        // Delete the traitementPerdialyse
        restTraitementPerdialyseMockMvc
            .perform(delete("/api/traitement-perdialyses/{id}", traitementPerdialyse.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TraitementPerdialyse> traitementPerdialyseList = traitementPerdialyseRepository.findAll();
        assertThat(traitementPerdialyseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
