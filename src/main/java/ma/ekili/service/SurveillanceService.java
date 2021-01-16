package ma.ekili.service;

import java.util.Optional;
import ma.ekili.domain.Surveillance;
import ma.ekili.repository.SurveillanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Surveillance}.
 */
@Service
@Transactional
public class SurveillanceService {
    private final Logger log = LoggerFactory.getLogger(SurveillanceService.class);

    private final SurveillanceRepository surveillanceRepository;

    public SurveillanceService(SurveillanceRepository surveillanceRepository) {
        this.surveillanceRepository = surveillanceRepository;
    }

    /**
     * Save a surveillance.
     *
     * @param surveillance the entity to save.
     * @return the persisted entity.
     */
    public Surveillance save(Surveillance surveillance) {
        log.debug("Request to save Surveillance : {}", surveillance);
        return surveillanceRepository.save(surveillance);
    }

    /**
     * Get all the surveillances.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Surveillance> findAll(Pageable pageable) {
        log.debug("Request to get all Surveillances");
        return surveillanceRepository.findAll(pageable);
    }

    /**
     * Get one surveillance by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Surveillance> findOne(Long id) {
        log.debug("Request to get Surveillance : {}", id);
        return surveillanceRepository.findById(id);
    }

    /**
     * Delete the surveillance by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Surveillance : {}", id);
        surveillanceRepository.deleteById(id);
    }
}
