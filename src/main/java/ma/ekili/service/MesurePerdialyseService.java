package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.MesurePerdialyse;
import ma.ekili.repository.MesurePerdialyseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link MesurePerdialyse}.
 */
@Service
@Transactional
public class MesurePerdialyseService {
    private final Logger log = LoggerFactory.getLogger(MesurePerdialyseService.class);

    private final MesurePerdialyseRepository mesurePerdialyseRepository;

    public MesurePerdialyseService(MesurePerdialyseRepository mesurePerdialyseRepository) {
        this.mesurePerdialyseRepository = mesurePerdialyseRepository;
    }

    /**
     * Save a mesurePerdialyse.
     *
     * @param mesurePerdialyse the entity to save.
     * @return the persisted entity.
     */
    public MesurePerdialyse save(MesurePerdialyse mesurePerdialyse) {
        log.debug("Request to save MesurePerdialyse : {}", mesurePerdialyse);
        return mesurePerdialyseRepository.save(mesurePerdialyse);
    }

    /**
     * Get all the mesurePerdialyses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MesurePerdialyse> findAll() {
        log.debug("Request to get all MesurePerdialyses");
        return mesurePerdialyseRepository.findAll();
    }

    /**
     * Get one mesurePerdialyse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MesurePerdialyse> findOne(Long id) {
        log.debug("Request to get MesurePerdialyse : {}", id);
        return mesurePerdialyseRepository.findById(id);
    }

    /**
     * Delete the mesurePerdialyse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MesurePerdialyse : {}", id);
        mesurePerdialyseRepository.deleteById(id);
    }
}
