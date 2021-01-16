package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import ma.ekili.domain.Surveillance;
import ma.ekili.service.SurveillanceQueryService;
import ma.ekili.service.SurveillanceService;
import ma.ekili.service.dto.SurveillanceCriteria;
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
 * REST controller for managing {@link ma.ekili.domain.Surveillance}.
 */
@RestController
@RequestMapping("/api")
public class SurveillanceResource {
    private final Logger log = LoggerFactory.getLogger(SurveillanceResource.class);

    private static final String ENTITY_NAME = "surveillance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SurveillanceService surveillanceService;

    private final SurveillanceQueryService surveillanceQueryService;

    public SurveillanceResource(SurveillanceService surveillanceService, SurveillanceQueryService surveillanceQueryService) {
        this.surveillanceService = surveillanceService;
        this.surveillanceQueryService = surveillanceQueryService;
    }

    /**
     * {@code POST  /surveillances} : Create a new surveillance.
     *
     * @param surveillance the surveillance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new surveillance, or with status {@code 400 (Bad Request)} if the surveillance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/surveillances")
    public ResponseEntity<Surveillance> createSurveillance(@Valid @RequestBody Surveillance surveillance) throws URISyntaxException {
        log.debug("REST request to save Surveillance : {}", surveillance);
        if (surveillance.getId() != null) {
            throw new BadRequestAlertException("A new surveillance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Surveillance result = surveillanceService.save(surveillance);
        return ResponseEntity
            .created(new URI("/api/surveillances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /surveillances} : Updates an existing surveillance.
     *
     * @param surveillance the surveillance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated surveillance,
     * or with status {@code 400 (Bad Request)} if the surveillance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the surveillance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/surveillances")
    public ResponseEntity<Surveillance> updateSurveillance(@Valid @RequestBody Surveillance surveillance) throws URISyntaxException {
        log.debug("REST request to update Surveillance : {}", surveillance);
        if (surveillance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Surveillance result = surveillanceService.save(surveillance);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, surveillance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /surveillances} : get all the surveillances.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of surveillances in body.
     */
    @GetMapping("/surveillances")
    public ResponseEntity<List<Surveillance>> getAllSurveillances(SurveillanceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Surveillances by criteria: {}", criteria);
        Page<Surveillance> page = surveillanceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /surveillances/count} : count all the surveillances.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/surveillances/count")
    public ResponseEntity<Long> countSurveillances(SurveillanceCriteria criteria) {
        log.debug("REST request to count Surveillances by criteria: {}", criteria);
        return ResponseEntity.ok().body(surveillanceQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /surveillances/:id} : get the "id" surveillance.
     *
     * @param id the id of the surveillance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the surveillance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/surveillances/{id}")
    public ResponseEntity<Surveillance> getSurveillance(@PathVariable Long id) {
        log.debug("REST request to get Surveillance : {}", id);
        Optional<Surveillance> surveillance = surveillanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(surveillance);
    }

    /**
     * {@code DELETE  /surveillances/:id} : delete the "id" surveillance.
     *
     * @param id the id of the surveillance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/surveillances/{id}")
    public ResponseEntity<Void> deleteSurveillance(@PathVariable Long id) {
        log.debug("REST request to delete Surveillance : {}", id);
        surveillanceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
