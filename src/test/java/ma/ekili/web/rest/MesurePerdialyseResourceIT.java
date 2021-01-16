package ma.ekili.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import javax.persistence.EntityManager;
import ma.ekili.EkiliApp;
import ma.ekili.domain.MesurePerdialyse;
import ma.ekili.repository.MesurePerdialyseRepository;
import ma.ekili.service.MesurePerdialyseService;
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
 * Integration tests for the {@link MesurePerdialyseResource} REST controller.
 */
@SpringBootTest(classes = EkiliApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MesurePerdialyseResourceIT {
    private static final String DEFAULT_HEURE = "AAAAAAAAAA";
    private static final String UPDATED_HEURE = "BBBBBBBBBB";

    private static final Double DEFAULT_POID = 1D;
    private static final Double UPDATED_POID = 2D;

    private static final String DEFAULT_TA = "AAAAAAAAAA";
    private static final String UPDATED_TA = "BBBBBBBBBB";

    private static final Double DEFAULT_TP = 1D;
    private static final Double UPDATED_TP = 2D;

    private static final Double DEFAULT_DEXTRO = 1D;
    private static final Double UPDATED_DEXTRO = 2D;

    private static final String DEFAULT_PA = "AAAAAAAAAA";
    private static final String UPDATED_PA = "BBBBBBBBBB";

    private static final Double DEFAULT_PV = 1D;
    private static final Double UPDATED_PV = 2D;

    private static final Double DEFAULT_PTM = 1D;
    private static final Double UPDATED_PTM = 2D;

    private static final Double DEFAULT_UFH = 1D;
    private static final Double UPDATED_UFH = 2D;

    private static final Double DEFAULT_CONDUCTIVITE = 1D;
    private static final Double UPDATED_CONDUCTIVITE = 2D;

    private static final Double DEFAULT_TD = 1D;
    private static final Double UPDATED_TD = 2D;

    private static final Double DEFAULT_DPS = 1D;
    private static final Double UPDATED_DPS = 2D;

    private static final Double DEFAULT_HEPARINE = 1D;
    private static final Double UPDATED_HEPARINE = 2D;

    private static final Integer DEFAULT_RINCAGE = 1;
    private static final Integer UPDATED_RINCAGE = 2;

    private static final Integer DEFAULT_TRANSFUSION = 1;
    private static final Integer UPDATED_TRANSFUSION = 2;

    private static final Integer DEFAULT_NUM_POCHE = 1;
    private static final Integer UPDATED_NUM_POCHE = 2;

    @Autowired
    private MesurePerdialyseRepository mesurePerdialyseRepository;

    @Autowired
    private MesurePerdialyseService mesurePerdialyseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMesurePerdialyseMockMvc;

    private MesurePerdialyse mesurePerdialyse;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MesurePerdialyse createEntity(EntityManager em) {
        MesurePerdialyse mesurePerdialyse = new MesurePerdialyse()
            .heure(DEFAULT_HEURE)
            .poid(DEFAULT_POID)
            .ta(DEFAULT_TA)
            .tp(DEFAULT_TP)
            .dextro(DEFAULT_DEXTRO)
            .pa(DEFAULT_PA)
            .pv(DEFAULT_PV)
            .ptm(DEFAULT_PTM)
            .ufh(DEFAULT_UFH)
            .conductivite(DEFAULT_CONDUCTIVITE)
            .td(DEFAULT_TD)
            .dps(DEFAULT_DPS)
            .heparine(DEFAULT_HEPARINE)
            .rincage(DEFAULT_RINCAGE)
            .transfusion(DEFAULT_TRANSFUSION)
            .numPoche(DEFAULT_NUM_POCHE);
        return mesurePerdialyse;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MesurePerdialyse createUpdatedEntity(EntityManager em) {
        MesurePerdialyse mesurePerdialyse = new MesurePerdialyse()
            .heure(UPDATED_HEURE)
            .poid(UPDATED_POID)
            .ta(UPDATED_TA)
            .tp(UPDATED_TP)
            .dextro(UPDATED_DEXTRO)
            .pa(UPDATED_PA)
            .pv(UPDATED_PV)
            .ptm(UPDATED_PTM)
            .ufh(UPDATED_UFH)
            .conductivite(UPDATED_CONDUCTIVITE)
            .td(UPDATED_TD)
            .dps(UPDATED_DPS)
            .heparine(UPDATED_HEPARINE)
            .rincage(UPDATED_RINCAGE)
            .transfusion(UPDATED_TRANSFUSION)
            .numPoche(UPDATED_NUM_POCHE);
        return mesurePerdialyse;
    }

    @BeforeEach
    public void initTest() {
        mesurePerdialyse = createEntity(em);
    }

    @Test
    @Transactional
    public void createMesurePerdialyse() throws Exception {
        int databaseSizeBeforeCreate = mesurePerdialyseRepository.findAll().size();
        // Create the MesurePerdialyse
        restMesurePerdialyseMockMvc
            .perform(
                post("/api/mesure-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mesurePerdialyse))
            )
            .andExpect(status().isCreated());

        // Validate the MesurePerdialyse in the database
        List<MesurePerdialyse> mesurePerdialyseList = mesurePerdialyseRepository.findAll();
        assertThat(mesurePerdialyseList).hasSize(databaseSizeBeforeCreate + 1);
        MesurePerdialyse testMesurePerdialyse = mesurePerdialyseList.get(mesurePerdialyseList.size() - 1);
        assertThat(testMesurePerdialyse.getHeure()).isEqualTo(DEFAULT_HEURE);
        assertThat(testMesurePerdialyse.getPoid()).isEqualTo(DEFAULT_POID);
        assertThat(testMesurePerdialyse.getTa()).isEqualTo(DEFAULT_TA);
        assertThat(testMesurePerdialyse.getTp()).isEqualTo(DEFAULT_TP);
        assertThat(testMesurePerdialyse.getDextro()).isEqualTo(DEFAULT_DEXTRO);
        assertThat(testMesurePerdialyse.getPa()).isEqualTo(DEFAULT_PA);
        assertThat(testMesurePerdialyse.getPv()).isEqualTo(DEFAULT_PV);
        assertThat(testMesurePerdialyse.getPtm()).isEqualTo(DEFAULT_PTM);
        assertThat(testMesurePerdialyse.getUfh()).isEqualTo(DEFAULT_UFH);
        assertThat(testMesurePerdialyse.getConductivite()).isEqualTo(DEFAULT_CONDUCTIVITE);
        assertThat(testMesurePerdialyse.getTd()).isEqualTo(DEFAULT_TD);
        assertThat(testMesurePerdialyse.getDps()).isEqualTo(DEFAULT_DPS);
        assertThat(testMesurePerdialyse.getHeparine()).isEqualTo(DEFAULT_HEPARINE);
        assertThat(testMesurePerdialyse.getRincage()).isEqualTo(DEFAULT_RINCAGE);
        assertThat(testMesurePerdialyse.getTransfusion()).isEqualTo(DEFAULT_TRANSFUSION);
        assertThat(testMesurePerdialyse.getNumPoche()).isEqualTo(DEFAULT_NUM_POCHE);
    }

    @Test
    @Transactional
    public void createMesurePerdialyseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mesurePerdialyseRepository.findAll().size();

        // Create the MesurePerdialyse with an existing ID
        mesurePerdialyse.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMesurePerdialyseMockMvc
            .perform(
                post("/api/mesure-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mesurePerdialyse))
            )
            .andExpect(status().isBadRequest());

        // Validate the MesurePerdialyse in the database
        List<MesurePerdialyse> mesurePerdialyseList = mesurePerdialyseRepository.findAll();
        assertThat(mesurePerdialyseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMesurePerdialyses() throws Exception {
        // Initialize the database
        mesurePerdialyseRepository.saveAndFlush(mesurePerdialyse);

        // Get all the mesurePerdialyseList
        restMesurePerdialyseMockMvc
            .perform(get("/api/mesure-perdialyses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mesurePerdialyse.getId().intValue())))
            .andExpect(jsonPath("$.[*].heure").value(hasItem(DEFAULT_HEURE)))
            .andExpect(jsonPath("$.[*].poid").value(hasItem(DEFAULT_POID.doubleValue())))
            .andExpect(jsonPath("$.[*].ta").value(hasItem(DEFAULT_TA)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].dextro").value(hasItem(DEFAULT_DEXTRO.doubleValue())))
            .andExpect(jsonPath("$.[*].pa").value(hasItem(DEFAULT_PA)))
            .andExpect(jsonPath("$.[*].pv").value(hasItem(DEFAULT_PV.doubleValue())))
            .andExpect(jsonPath("$.[*].ptm").value(hasItem(DEFAULT_PTM.doubleValue())))
            .andExpect(jsonPath("$.[*].ufh").value(hasItem(DEFAULT_UFH.doubleValue())))
            .andExpect(jsonPath("$.[*].conductivite").value(hasItem(DEFAULT_CONDUCTIVITE.doubleValue())))
            .andExpect(jsonPath("$.[*].td").value(hasItem(DEFAULT_TD.doubleValue())))
            .andExpect(jsonPath("$.[*].dps").value(hasItem(DEFAULT_DPS.doubleValue())))
            .andExpect(jsonPath("$.[*].heparine").value(hasItem(DEFAULT_HEPARINE.doubleValue())))
            .andExpect(jsonPath("$.[*].rincage").value(hasItem(DEFAULT_RINCAGE)))
            .andExpect(jsonPath("$.[*].transfusion").value(hasItem(DEFAULT_TRANSFUSION)))
            .andExpect(jsonPath("$.[*].numPoche").value(hasItem(DEFAULT_NUM_POCHE)));
    }

    @Test
    @Transactional
    public void getMesurePerdialyse() throws Exception {
        // Initialize the database
        mesurePerdialyseRepository.saveAndFlush(mesurePerdialyse);

        // Get the mesurePerdialyse
        restMesurePerdialyseMockMvc
            .perform(get("/api/mesure-perdialyses/{id}", mesurePerdialyse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mesurePerdialyse.getId().intValue()))
            .andExpect(jsonPath("$.heure").value(DEFAULT_HEURE))
            .andExpect(jsonPath("$.poid").value(DEFAULT_POID.doubleValue()))
            .andExpect(jsonPath("$.ta").value(DEFAULT_TA))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.dextro").value(DEFAULT_DEXTRO.doubleValue()))
            .andExpect(jsonPath("$.pa").value(DEFAULT_PA))
            .andExpect(jsonPath("$.pv").value(DEFAULT_PV.doubleValue()))
            .andExpect(jsonPath("$.ptm").value(DEFAULT_PTM.doubleValue()))
            .andExpect(jsonPath("$.ufh").value(DEFAULT_UFH.doubleValue()))
            .andExpect(jsonPath("$.conductivite").value(DEFAULT_CONDUCTIVITE.doubleValue()))
            .andExpect(jsonPath("$.td").value(DEFAULT_TD.doubleValue()))
            .andExpect(jsonPath("$.dps").value(DEFAULT_DPS.doubleValue()))
            .andExpect(jsonPath("$.heparine").value(DEFAULT_HEPARINE.doubleValue()))
            .andExpect(jsonPath("$.rincage").value(DEFAULT_RINCAGE))
            .andExpect(jsonPath("$.transfusion").value(DEFAULT_TRANSFUSION))
            .andExpect(jsonPath("$.numPoche").value(DEFAULT_NUM_POCHE));
    }

    @Test
    @Transactional
    public void getNonExistingMesurePerdialyse() throws Exception {
        // Get the mesurePerdialyse
        restMesurePerdialyseMockMvc.perform(get("/api/mesure-perdialyses/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMesurePerdialyse() throws Exception {
        // Initialize the database
        mesurePerdialyseService.save(mesurePerdialyse);

        int databaseSizeBeforeUpdate = mesurePerdialyseRepository.findAll().size();

        // Update the mesurePerdialyse
        MesurePerdialyse updatedMesurePerdialyse = mesurePerdialyseRepository.findById(mesurePerdialyse.getId()).get();
        // Disconnect from session so that the updates on updatedMesurePerdialyse are not directly saved in db
        em.detach(updatedMesurePerdialyse);
        updatedMesurePerdialyse
            .heure(UPDATED_HEURE)
            .poid(UPDATED_POID)
            .ta(UPDATED_TA)
            .tp(UPDATED_TP)
            .dextro(UPDATED_DEXTRO)
            .pa(UPDATED_PA)
            .pv(UPDATED_PV)
            .ptm(UPDATED_PTM)
            .ufh(UPDATED_UFH)
            .conductivite(UPDATED_CONDUCTIVITE)
            .td(UPDATED_TD)
            .dps(UPDATED_DPS)
            .heparine(UPDATED_HEPARINE)
            .rincage(UPDATED_RINCAGE)
            .transfusion(UPDATED_TRANSFUSION)
            .numPoche(UPDATED_NUM_POCHE);

        restMesurePerdialyseMockMvc
            .perform(
                put("/api/mesure-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedMesurePerdialyse))
            )
            .andExpect(status().isOk());

        // Validate the MesurePerdialyse in the database
        List<MesurePerdialyse> mesurePerdialyseList = mesurePerdialyseRepository.findAll();
        assertThat(mesurePerdialyseList).hasSize(databaseSizeBeforeUpdate);
        MesurePerdialyse testMesurePerdialyse = mesurePerdialyseList.get(mesurePerdialyseList.size() - 1);
        assertThat(testMesurePerdialyse.getHeure()).isEqualTo(UPDATED_HEURE);
        assertThat(testMesurePerdialyse.getPoid()).isEqualTo(UPDATED_POID);
        assertThat(testMesurePerdialyse.getTa()).isEqualTo(UPDATED_TA);
        assertThat(testMesurePerdialyse.getTp()).isEqualTo(UPDATED_TP);
        assertThat(testMesurePerdialyse.getDextro()).isEqualTo(UPDATED_DEXTRO);
        assertThat(testMesurePerdialyse.getPa()).isEqualTo(UPDATED_PA);
        assertThat(testMesurePerdialyse.getPv()).isEqualTo(UPDATED_PV);
        assertThat(testMesurePerdialyse.getPtm()).isEqualTo(UPDATED_PTM);
        assertThat(testMesurePerdialyse.getUfh()).isEqualTo(UPDATED_UFH);
        assertThat(testMesurePerdialyse.getConductivite()).isEqualTo(UPDATED_CONDUCTIVITE);
        assertThat(testMesurePerdialyse.getTd()).isEqualTo(UPDATED_TD);
        assertThat(testMesurePerdialyse.getDps()).isEqualTo(UPDATED_DPS);
        assertThat(testMesurePerdialyse.getHeparine()).isEqualTo(UPDATED_HEPARINE);
        assertThat(testMesurePerdialyse.getRincage()).isEqualTo(UPDATED_RINCAGE);
        assertThat(testMesurePerdialyse.getTransfusion()).isEqualTo(UPDATED_TRANSFUSION);
        assertThat(testMesurePerdialyse.getNumPoche()).isEqualTo(UPDATED_NUM_POCHE);
    }

    @Test
    @Transactional
    public void updateNonExistingMesurePerdialyse() throws Exception {
        int databaseSizeBeforeUpdate = mesurePerdialyseRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMesurePerdialyseMockMvc
            .perform(
                put("/api/mesure-perdialyses")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(mesurePerdialyse))
            )
            .andExpect(status().isBadRequest());

        // Validate the MesurePerdialyse in the database
        List<MesurePerdialyse> mesurePerdialyseList = mesurePerdialyseRepository.findAll();
        assertThat(mesurePerdialyseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMesurePerdialyse() throws Exception {
        // Initialize the database
        mesurePerdialyseService.save(mesurePerdialyse);

        int databaseSizeBeforeDelete = mesurePerdialyseRepository.findAll().size();

        // Delete the mesurePerdialyse
        restMesurePerdialyseMockMvc
            .perform(delete("/api/mesure-perdialyses/{id}", mesurePerdialyse.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MesurePerdialyse> mesurePerdialyseList = mesurePerdialyseRepository.findAll();
        assertThat(mesurePerdialyseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
