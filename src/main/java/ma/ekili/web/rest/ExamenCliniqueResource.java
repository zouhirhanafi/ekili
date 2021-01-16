package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ma.ekili.domain.ExamenClinique;
import ma.ekili.service.ExamenCliniqueService;
import ma.ekili.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ma.ekili.domain.ExamenClinique}.
 */
@RestController
@RequestMapping("/api")
public class ExamenCliniqueResource {
    private final Logger log = LoggerFactory.getLogger(ExamenCliniqueResource.class);

    private static final String ENTITY_NAME = "examenClinique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExamenCliniqueService examenCliniqueService;

    public ExamenCliniqueResource(ExamenCliniqueService examenCliniqueService) {
        this.examenCliniqueService = examenCliniqueService;
    }

    /**
     * {@code POST  /examen-cliniques} : Create a new examenClinique.
     *
     * @param examenClinique the examenClinique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examenClinique, or with status {@code 400 (Bad Request)} if the examenClinique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examen-cliniques")
    public ResponseEntity<ExamenClinique> createExamenClinique(@RequestBody ExamenClinique examenClinique) throws URISyntaxException {
        log.debug("REST request to save ExamenClinique : {}", examenClinique);
        if (examenClinique.getId() != null) {
            throw new BadRequestAlertException("A new examenClinique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExamenClinique result = examenCliniqueService.save(examenClinique);
        return ResponseEntity
            .created(new URI("/api/examen-cliniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /examen-cliniques} : Updates an existing examenClinique.
     *
     * @param examenClinique the examenClinique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examenClinique,
     * or with status {@code 400 (Bad Request)} if the examenClinique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examenClinique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examen-cliniques")
    public ResponseEntity<ExamenClinique> updateExamenClinique(@RequestBody ExamenClinique examenClinique) throws URISyntaxException {
        log.debug("REST request to update ExamenClinique : {}", examenClinique);
        if (examenClinique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExamenClinique result = examenCliniqueService.save(examenClinique);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, examenClinique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /examen-cliniques} : get all the examenCliniques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examenCliniques in body.
     */
    @GetMapping("/examen-cliniques")
    public List<ExamenClinique> getAllExamenCliniques() {
        log.debug("REST request to get all ExamenCliniques");
        return examenCliniqueService.findAll();
    }

    /**
     * {@code GET  /examen-cliniques/:id} : get the "id" examenClinique.
     *
     * @param id the id of the examenClinique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examenClinique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/examen-cliniques/{id}")
    public ResponseEntity<ExamenClinique> getExamenClinique(@PathVariable Long id) {
        log.debug("REST request to get ExamenClinique : {}", id);
        Optional<ExamenClinique> examenClinique = examenCliniqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(examenClinique);
    }

    /**
     * {@code DELETE  /examen-cliniques/:id} : delete the "id" examenClinique.
     *
     * @param id the id of the examenClinique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examen-cliniques/{id}")
    public ResponseEntity<Void> deleteExamenClinique(@PathVariable Long id) {
        log.debug("REST request to delete ExamenClinique : {}", id);
        examenCliniqueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
