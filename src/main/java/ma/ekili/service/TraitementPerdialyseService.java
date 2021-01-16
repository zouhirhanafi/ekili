package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.TraitementPerdialyse;
import ma.ekili.repository.TraitementPerdialyseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TraitementPerdialyse}.
 */
@Service
@Transactional
public class TraitementPerdialyseService {
    private final Logger log = LoggerFactory.getLogger(TraitementPerdialyseService.class);

    private final TraitementPerdialyseRepository traitementPerdialyseRepository;

    public TraitementPerdialyseService(TraitementPerdialyseRepository traitementPerdialyseRepository) {
        this.traitementPerdialyseRepository = traitementPerdialyseRepository;
    }

    /**
     * Save a traitementPerdialyse.
     *
     * @param traitementPerdialyse the entity to save.
     * @return the persisted entity.
     */
    public TraitementPerdialyse save(TraitementPerdialyse traitementPerdialyse) {
        log.debug("Request to save TraitementPerdialyse : {}", traitementPerdialyse);
        return traitementPerdialyseRepository.save(traitementPerdialyse);
    }

    /**
     * Get all the traitementPerdialyses.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<TraitementPerdialyse> findAll() {
        log.debug("Request to get all TraitementPerdialyses");
        return traitementPerdialyseRepository.findAll();
    }

    /**
     * Get one traitementPerdialyse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TraitementPerdialyse> findOne(Long id) {
        log.debug("Request to get TraitementPerdialyse : {}", id);
        return traitementPerdialyseRepository.findById(id);
    }

    /**
     * Delete the traitementPerdialyse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TraitementPerdialyse : {}", id);
        traitementPerdialyseRepository.deleteById(id);
    }
}
