package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ma.ekili.domain.MesurePerdialyse;
import ma.ekili.service.MesurePerdialyseService;
import ma.ekili.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ma.ekili.domain.MesurePerdialyse}.
 */
@RestController
@RequestMapping("/api")
public class MesurePerdialyseResource {
    private final Logger log = LoggerFactory.getLogger(MesurePerdialyseResource.class);

    private static final String ENTITY_NAME = "mesurePerdialyse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MesurePerdialyseService mesurePerdialyseService;

    public MesurePerdialyseResource(MesurePerdialyseService mesurePerdialyseService) {
        this.mesurePerdialyseService = mesurePerdialyseService;
    }

    /**
     * {@code POST  /mesure-perdialyses} : Create a new mesurePerdialyse.
     *
     * @param mesurePerdialyse the mesurePerdialyse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mesurePerdialyse, or with status {@code 400 (Bad Request)} if the mesurePerdialyse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mesure-perdialyses")
    public ResponseEntity<MesurePerdialyse> createMesurePerdialyse(@RequestBody MesurePerdialyse mesurePerdialyse)
        throws URISyntaxException {
        log.debug("REST request to save MesurePerdialyse : {}", mesurePerdialyse);
        if (mesurePerdialyse.getId() != null) {
            throw new BadRequestAlertException("A new mesurePerdialyse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MesurePerdialyse result = mesurePerdialyseService.save(mesurePerdialyse);
        return ResponseEntity
            .created(new URI("/api/mesure-perdialyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mesure-perdialyses} : Updates an existing mesurePerdialyse.
     *
     * @param mesurePerdialyse the mesurePerdialyse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mesurePerdialyse,
     * or with status {@code 400 (Bad Request)} if the mesurePerdialyse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mesurePerdialyse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mesure-perdialyses")
    public ResponseEntity<MesurePerdialyse> updateMesurePerdialyse(@RequestBody MesurePerdialyse mesurePerdialyse)
        throws URISyntaxException {
        log.debug("REST request to update MesurePerdialyse : {}", mesurePerdialyse);
        if (mesurePerdialyse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MesurePerdialyse result = mesurePerdialyseService.save(mesurePerdialyse);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mesurePerdialyse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mesure-perdialyses} : get all the mesurePerdialyses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mesurePerdialyses in body.
     */
    @GetMapping("/mesure-perdialyses")
    public List<MesurePerdialyse> getAllMesurePerdialyses() {
        log.debug("REST request to get all MesurePerdialyses");
        return mesurePerdialyseService.findAll();
    }

    /**
     * {@code GET  /mesure-perdialyses/:id} : get the "id" mesurePerdialyse.
     *
     * @param id the id of the mesurePerdialyse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mesurePerdialyse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mesure-perdialyses/{id}")
    public ResponseEntity<MesurePerdialyse> getMesurePerdialyse(@PathVariable Long id) {
        log.debug("REST request to get MesurePerdialyse : {}", id);
        Optional<MesurePerdialyse> mesurePerdialyse = mesurePerdialyseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mesurePerdialyse);
    }

    /**
     * {@code DELETE  /mesure-perdialyses/:id} : delete the "id" mesurePerdialyse.
     *
     * @param id the id of the mesurePerdialyse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mesure-perdialyses/{id}")
    public ResponseEntity<Void> deleteMesurePerdialyse(@PathVariable Long id) {
        log.debug("REST request to delete MesurePerdialyse : {}", id);
        mesurePerdialyseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
