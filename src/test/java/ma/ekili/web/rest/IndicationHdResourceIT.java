package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.IndicationHd;
import ma.ekili.repository.IndicationHdRepository;
import ma.ekili.service.IndicationHdService;
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
 * Integration tests for the {@link IndicationHdResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class IndicationHdResourceIT {
    private static final Integer DEFAULT_SERVICE = 1;
    private static final Integer UPDATED_SERVICE = 2;

    private static final String DEFAULT_AUTRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE = "BBBBBBBBBB";

    @Autowired
    private IndicationHdRepository indicationHdRepository;

    @Autowired
    private IndicationHdService indicationHdService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIndicationHdMockMvc;

    private IndicationHd indicationHd;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndicationHd createEntity(EntityManager em) {
        IndicationHd indicationHd = new IndicationHd().service(DEFAULT_SERVICE).autre(DEFAULT_AUTRE);
        return indicationHd;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndicationHd createUpdatedEntity(EntityManager em) {
        IndicationHd indicationHd = new IndicationHd().service(UPDATED_SERVICE).autre(UPDATED_AUTRE);
        return indicationHd;
    }

    @BeforeEach
    public void initTest() {
        indicationHd = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndicationHd() throws Exception {
        int databaseSizeBeforeCreate = indicationHdRepository.findAll().size();
        // Create the IndicationHd
        restIndicationHdMockMvc
            .perform(
                post("/api/indication-hds").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(indicationHd))
            )
            .andExpect(status().isCreated());

        // Validate the IndicationHd in the database
        List<IndicationHd> indicationHdList = indicationHdRepository.findAll();
        assertThat(indicationHdList).hasSize(databaseSizeBeforeCreate + 1);
        IndicationHd testIndicationHd = indicationHdList.get(indicationHdList.size() - 1);
        assertThat(testIndicationHd.getService()).isEqualTo(DEFAULT_SERVICE);
        assertThat(testIndicationHd.getAutre()).isEqualTo(DEFAULT_AUTRE);
    }

    @Test
    @Transactional
    public void createIndicationHdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = indicationHdRepository.findAll().size();

        // Create the IndicationHd with an existing ID
        indicationHd.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndicationHdMockMvc
            .perform(
                post("/api/indication-hds").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(indicationHd))
            )
            .andExpect(status().isBadRequest());

        // Validate the IndicationHd in the database
        List<IndicationHd> indicationHdList = indicationHdRepository.findAll();
        assertThat(indicationHdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIndicationHds() throws Exception {
        // Initialize the database
        indicationHdRepository.saveAndFlush(indicationHd);

        // Get all the indicationHdList
        restIndicationHdMockMvc
            .perform(get("/api/indication-hds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicationHd.getId().intValue())))
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE)))
            .andExpect(jsonPath("$.[*].autre").value(hasItem(DEFAULT_AUTRE)));
    }

    @Test
    @Transactional
    public void getIndicationHd() throws Exception {
        // Initialize the database
        indicationHdRepository.saveAndFlush(indicationHd);

        // Get the indicationHd
        restIndicationHdMockMvc
            .perform(get("/api/indication-hds/{id}", indicationHd.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(indicationHd.getId().intValue()))
            .andExpect(jsonPath("$.service").value(DEFAULT_SERVICE))
            .andExpect(jsonPath("$.autre").value(DEFAULT_AUTRE));
    }

    @Test
    @Transactional
    public void getNonExistingIndicationHd() throws Exception {
        // Get the indicationHd
        restIndicationHdMockMvc.perform(get("/api/indication-hds/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndicationHd() throws Exception {
        // Initialize the database
        indicationHdService.save(indicationHd);

        int databaseSizeBeforeUpdate = indicationHdRepository.findAll().size();

        // Update the indicationHd
        IndicationHd updatedIndicationHd = indicationHdRepository.findById(indicationHd.getId()).get();
        // Disconnect from session so that the updates on updatedIndicationHd are not directly saved in db
        em.detach(updatedIndicationHd);
        updatedIndicationHd.service(UPDATED_SERVICE).autre(UPDATED_AUTRE);

        restIndicationHdMockMvc
            .perform(
                put("/api/indication-hds")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedIndicationHd))
            )
            .andExpect(status().isOk());

        // Validate the IndicationHd in the database
        List<IndicationHd> indicationHdList = indicationHdRepository.findAll();
        assertThat(indicationHdList).hasSize(databaseSizeBeforeUpdate);
        IndicationHd testIndicationHd = indicationHdList.get(indicationHdList.size() - 1);
        assertThat(testIndicationHd.getService()).isEqualTo(UPDATED_SERVICE);
        assertThat(testIndicationHd.getAutre()).isEqualTo(UPDATED_AUTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingIndicationHd() throws Exception {
        int databaseSizeBeforeUpdate = indicationHdRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIndicationHdMockMvc
            .perform(
                put("/api/indication-hds").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(indicationHd))
            )
            .andExpect(status().isBadRequest());

        // Validate the IndicationHd in the database
        List<IndicationHd> indicationHdList = indicationHdRepository.findAll();
        assertThat(indicationHdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIndicationHd() throws Exception {
        // Initialize the database
        indicationHdService.save(indicationHd);

        int databaseSizeBeforeDelete = indicationHdRepository.findAll().size();

        // Delete the indicationHd
        restIndicationHdMockMvc
            .perform(delete("/api/indication-hds/{id}", indicationHd.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IndicationHd> indicationHdList = indicationHdRepository.findAll();
        assertThat(indicationHdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
