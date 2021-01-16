package ma.ekili.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import ma.ekili.domain.IndicationHd;
import ma.ekili.service.IndicationHdService;
import ma.ekili.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link ma.ekili.domain.IndicationHd}.
 */
@RestController
@RequestMapping("/api")
public class IndicationHdResource {
    private final Logger log = LoggerFactory.getLogger(IndicationHdResource.class);

    private static final String ENTITY_NAME = "indicationHd";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IndicationHdService indicationHdService;

    public IndicationHdResource(IndicationHdService indicationHdService) {
        this.indicationHdService = indicationHdService;
    }

    /**
     * {@code POST  /indication-hds} : Create a new indicationHd.
     *
     * @param indicationHd the indicationHd to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new indicationHd, or with status {@code 400 (Bad Request)} if the indicationHd has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/indication-hds")
    public ResponseEntity<IndicationHd> createIndicationHd(@RequestBody IndicationHd indicationHd) throws URISyntaxException {
        log.debug("REST request to save IndicationHd : {}", indicationHd);
        if (indicationHd.getId() != null) {
            throw new BadRequestAlertException("A new indicationHd cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndicationHd result = indicationHdService.save(indicationHd);
        return ResponseEntity
            .created(new URI("/api/indication-hds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /indication-hds} : Updates an existing indicationHd.
     *
     * @param indicationHd the indicationHd to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated indicationHd,
     * or with status {@code 400 (Bad Request)} if the indicationHd is not valid,
     * or with status {@code 500 (Internal Server Error)} if the indicationHd couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/indication-hds")
    public ResponseEntity<IndicationHd> updateIndicationHd(@RequestBody IndicationHd indicationHd) throws URISyntaxException {
        log.debug("REST request to update IndicationHd : {}", indicationHd);
        if (indicationHd.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IndicationHd result = indicationHdService.save(indicationHd);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, indicationHd.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /indication-hds} : get all the indicationHds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of indicationHds in body.
     */
    @GetMapping("/indication-hds")
    public List<IndicationHd> getAllIndicationHds() {
        log.debug("REST request to get all IndicationHds");
        return indicationHdService.findAll();
    }

    /**
     * {@code GET  /indication-hds/:id} : get the "id" indicationHd.
     *
     * @param id the id of the indicationHd to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the indicationHd, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/indication-hds/{id}")
    public ResponseEntity<IndicationHd> getIndicationHd(@PathVariable Long id) {
        log.debug("REST request to get IndicationHd : {}", id);
        Optional<IndicationHd> indicationHd = indicationHdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(indicationHd);
    }

    /**
     * {@code DELETE  /indication-hds/:id} : delete the "id" indicationHd.
     *
     * @param id the id of the indicationHd to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/indication-hds/{id}")
    public ResponseEntity<Void> deleteIndicationHd(@PathVariable Long id) {
        log.debug("REST request to delete IndicationHd : {}", id);
        indicationHdService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
