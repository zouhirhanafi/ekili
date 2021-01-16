package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ma.ekili.domain.TraitementPerdialyse;
import ma.ekili.service.TraitementPerdialyseService;
import ma.ekili.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ma.ekili.domain.TraitementPerdialyse}.
 */
@RestController
@RequestMapping("/api")
public class TraitementPerdialyseResource {
    private final Logger log = LoggerFactory.getLogger(TraitementPerdialyseResource.class);

    private static final String ENTITY_NAME = "traitementPerdialyse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TraitementPerdialyseService traitementPerdialyseService;

    public TraitementPerdialyseResource(TraitementPerdialyseService traitementPerdialyseService) {
        this.traitementPerdialyseService = traitementPerdialyseService;
    }

    /**
     * {@code POST  /traitement-perdialyses} : Create a new traitementPerdialyse.
     *
     * @param traitementPerdialyse the traitementPerdialyse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new traitementPerdialyse, or with status {@code 400 (Bad Request)} if the traitementPerdialyse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/traitement-perdialyses")
    public ResponseEntity<TraitementPerdialyse> createTraitementPerdialyse(@RequestBody TraitementPerdialyse traitementPerdialyse)
        throws URISyntaxException {
        log.debug("REST request to save TraitementPerdialyse : {}", traitementPerdialyse);
        if (traitementPerdialyse.getId() != null) {
            throw new BadRequestAlertException("A new traitementPerdialyse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TraitementPerdialyse result = traitementPerdialyseService.save(traitementPerdialyse);
        return ResponseEntity
            .created(new URI("/api/traitement-perdialyses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /traitement-perdialyses} : Updates an existing traitementPerdialyse.
     *
     * @param traitementPerdialyse the traitementPerdialyse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated traitementPerdialyse,
     * or with status {@code 400 (Bad Request)} if the traitementPerdialyse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the traitementPerdialyse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/traitement-perdialyses")
    public ResponseEntity<TraitementPerdialyse> updateTraitementPerdialyse(@RequestBody TraitementPerdialyse traitementPerdialyse)
        throws URISyntaxException {
        log.debug("REST request to update TraitementPerdialyse : {}", traitementPerdialyse);
        if (traitementPerdialyse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TraitementPerdialyse result = traitementPerdialyseService.save(traitementPerdialyse);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, traitementPerdialyse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /traitement-perdialyses} : get all the traitementPerdialyses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of traitementPerdialyses in body.
     */
    @GetMapping("/traitement-perdialyses")
    public List<TraitementPerdialyse> getAllTraitementPerdialyses() {
        log.debug("REST request to get all TraitementPerdialyses");
        return traitementPerdialyseService.findAll();
    }

    /**
     * {@code GET  /traitement-perdialyses/:id} : get the "id" traitementPerdialyse.
     *
     * @param id the id of the traitementPerdialyse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the traitementPerdialyse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/traitement-perdialyses/{id}")
    public ResponseEntity<TraitementPerdialyse> getTraitementPerdialyse(@PathVariable Long id) {
        log.debug("REST request to get TraitementPerdialyse : {}", id);
        Optional<TraitementPerdialyse> traitementPerdialyse = traitementPerdialyseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(traitementPerdialyse);
    }

    /**
     * {@code DELETE  /traitement-perdialyses/:id} : delete the "id" traitementPerdialyse.
     *
     * @param id the id of the traitementPerdialyse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/traitement-perdialyses/{id}")
    public ResponseEntity<Void> deleteTraitementPerdialyse(@PathVariable Long id) {
        log.debug("REST request to delete TraitementPerdialyse : {}", id);
        traitementPerdialyseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
