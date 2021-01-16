package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.IndicationHd;
import ma.ekili.repository.IndicationHdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link IndicationHd}.
 */
@Service
@Transactional
public class IndicationHdService {
    private final Logger log = LoggerFactory.getLogger(IndicationHdService.class);

    private final IndicationHdRepository indicationHdRepository;

    public IndicationHdService(IndicationHdRepository indicationHdRepository) {
        this.indicationHdRepository = indicationHdRepository;
    }

    /**
     * Save a indicationHd.
     *
     * @param indicationHd the entity to save.
     * @return the persisted entity.
     */
    public IndicationHd save(IndicationHd indicationHd) {
        log.debug("Request to save IndicationHd : {}", indicationHd);
        return indicationHdRepository.save(indicationHd);
    }

    /**
     * Get all the indicationHds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IndicationHd> findAll() {
        log.debug("Request to get all IndicationHds");
        return indicationHdRepository.findAll();
    }

    /**
     * Get one indicationHd by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IndicationHd> findOne(Long id) {
        log.debug("Request to get IndicationHd : {}", id);
        return indicationHdRepository.findById(id);
    }

    /**
     * Delete the indicationHd by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete IndicationHd : {}", id);
        indicationHdRepository.deleteById(id);
    }
}
