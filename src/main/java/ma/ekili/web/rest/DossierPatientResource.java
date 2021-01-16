package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import ma.ekili.domain.DossierPatient;
import ma.ekili.service.DossierPatientQueryService;
import ma.ekili.service.DossierPatientService;
import ma.ekili.service.dto.DossierPatientCriteria;
import ma.ekili.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link ma.ekili.domain.DossierPatient}.
 */
@RestController
@RequestMapping("/api")
public class DossierPatientResource {
    private final Logger log = LoggerFactory.getLogger(DossierPatientResource.class);

    private static final String ENTITY_NAME = "dossierPatient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DossierPatientService dossierPatientService;

    private final DossierPatientQueryService dossierPatientQueryService;

    public DossierPatientResource(DossierPatientService dossierPatientService, DossierPatientQueryService dossierPatientQueryService) {
        this.dossierPatientService = dossierPatientService;
        this.dossierPatientQueryService = dossierPatientQueryService;
    }

    /**
     * {@code POST  /dossier-patients} : Create a new dossierPatient.
     *
     * @param dossierPatient the dossierPatient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dossierPatient, or with status {@code 400 (Bad Request)} if the dossierPatient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/dossier-patients")
    public ResponseEntity<DossierPatient> createDossierPatient(@Valid @RequestBody DossierPatient dossierPatient)
        throws URISyntaxException {
        log.debug("REST request to save DossierPatient : {}", dossierPatient);
        if (dossierPatient.getId() != null) {
            throw new BadRequestAlertException("A new dossierPatient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DossierPatient result = dossierPatientService.save(dossierPatient);
        return ResponseEntity
            .created(new URI("/api/dossier-patients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /dossier-patients} : Updates an existing dossierPatient.
     *
     * @param dossierPatient the dossierPatient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dossierPatient,
     * or with status {@code 400 (Bad Request)} if the dossierPatient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dossierPatient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/dossier-patients")
    public ResponseEntity<DossierPatient> updateDossierPatient(@Valid @RequestBody DossierPatient dossierPatient)
        throws URISyntaxException {
        log.debug("REST request to update DossierPatient : {}", dossierPatient);
        if (dossierPatient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DossierPatient result = dossierPatientService.save(dossierPatient);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, dossierPatient.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /dossier-patients} : get all the dossierPatients.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dossierPatients in body.
     */
    @GetMapping("/dossier-patients")
    public ResponseEntity<List<DossierPatient>> getAllDossierPatients(DossierPatientCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DossierPatients by criteria: {}", criteria);
        Page<DossierPatient> page = dossierPatientQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /dossier-patients/count} : count all the dossierPatients.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/dossier-patients/count")
    public ResponseEntity<Long> countDossierPatients(DossierPatientCriteria criteria) {
        log.debug("REST request to count DossierPatients by criteria: {}", criteria);
        return ResponseEntity.ok().body(dossierPatientQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /dossier-patients/:id} : get the "id" dossierPatient.
     *
     * @param id the id of the dossierPatient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dossierPatient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dossier-patients/{id}")
    public ResponseEntity<DossierPatient> getDossierPatient(@PathVariable Long id) {
        log.debug("REST request to get DossierPatient : {}", id);
        Optional<DossierPatient> dossierPatient = dossierPatientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dossierPatient);
    }

    /**
     * {@code DELETE  /dossier-patients/:id} : delete the "id" dossierPatient.
     *
     * @param id the id of the dossierPatient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/dossier-patients/{id}")
    public ResponseEntity<Void> deleteDossierPatient(@PathVariable Long id) {
        log.debug("REST request to delete DossierPatient : {}", id);
        dossierPatientService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
