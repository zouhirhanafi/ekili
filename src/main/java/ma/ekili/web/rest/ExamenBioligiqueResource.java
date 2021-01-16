package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ma.ekili.domain.ExamenBioligique;
import ma.ekili.service.ExamenBioligiqueService;
import ma.ekili.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ma.ekili.domain.ExamenBioligique}.
 */
@RestController
@RequestMapping("/api")
public class ExamenBioligiqueResource {
    private final Logger log = LoggerFactory.getLogger(ExamenBioligiqueResource.class);

    private static final String ENTITY_NAME = "examenBioligique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExamenBioligiqueService examenBioligiqueService;

    public ExamenBioligiqueResource(ExamenBioligiqueService examenBioligiqueService) {
        this.examenBioligiqueService = examenBioligiqueService;
    }

    /**
     * {@code POST  /examen-bioligiques} : Create a new examenBioligique.
     *
     * @param examenBioligique the examenBioligique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new examenBioligique, or with status {@code 400 (Bad Request)} if the examenBioligique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/examen-bioligiques")
    public ResponseEntity<ExamenBioligique> createExamenBioligique(@RequestBody ExamenBioligique examenBioligique)
        throws URISyntaxException {
        log.debug("REST request to save ExamenBioligique : {}", examenBioligique);
        if (examenBioligique.getId() != null) {
            throw new BadRequestAlertException("A new examenBioligique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExamenBioligique result = examenBioligiqueService.save(examenBioligique);
        return ResponseEntity
            .created(new URI("/api/examen-bioligiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /examen-bioligiques} : Updates an existing examenBioligique.
     *
     * @param examenBioligique the examenBioligique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated examenBioligique,
     * or with status {@code 400 (Bad Request)} if the examenBioligique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the examenBioligique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/examen-bioligiques")
    public ResponseEntity<ExamenBioligique> updateExamenBioligique(@RequestBody ExamenBioligique examenBioligique)
        throws URISyntaxException {
        log.debug("REST request to update ExamenBioligique : {}", examenBioligique);
        if (examenBioligique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExamenBioligique result = examenBioligiqueService.save(examenBioligique);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, examenBioligique.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /examen-bioligiques} : get all the examenBioligiques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of examenBioligiques in body.
     */
    @GetMapping("/examen-bioligiques")
    public List<ExamenBioligique> getAllExamenBioligiques() {
        log.debug("REST request to get all ExamenBioligiques");
        return examenBioligiqueService.findAll();
    }

    /**
     * {@code GET  /examen-bioligiques/:id} : get the "id" examenBioligique.
     *
     * @param id the id of the examenBioligique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the examenBioligique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/examen-bioligiques/{id}")
    public ResponseEntity<ExamenBioligique> getExamenBioligique(@PathVariable Long id) {
        log.debug("REST request to get ExamenBioligique : {}", id);
        Optional<ExamenBioligique> examenBioligique = examenBioligiqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(examenBioligique);
    }

    /**
     * {@code DELETE  /examen-bioligiques/:id} : delete the "id" examenBioligique.
     *
     * @param id the id of the examenBioligique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/examen-bioligiques/{id}")
    public ResponseEntity<Void> deleteExamenBioligique(@PathVariable Long id) {
        log.debug("REST request to delete ExamenBioligique : {}", id);
        examenBioligiqueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
