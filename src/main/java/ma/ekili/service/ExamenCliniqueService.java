package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.ExamenClinique;
import ma.ekili.repository.ExamenCliniqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExamenClinique}.
 */
@Service
@Transactional
public class ExamenCliniqueService {
    private final Logger log = LoggerFactory.getLogger(ExamenCliniqueService.class);

    private final ExamenCliniqueRepository examenCliniqueRepository;

    public ExamenCliniqueService(ExamenCliniqueRepository examenCliniqueRepository) {
        this.examenCliniqueRepository = examenCliniqueRepository;
    }

    /**
     * Save a examenClinique.
     *
     * @param examenClinique the entity to save.
     * @return the persisted entity.
     */
    public ExamenClinique save(ExamenClinique examenClinique) {
        log.debug("Request to save ExamenClinique : {}", examenClinique);
        return examenCliniqueRepository.save(examenClinique);
    }

    /**
     * Get all the examenCliniques.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ExamenClinique> findAll() {
        log.debug("Request to get all ExamenCliniques");
        return examenCliniqueRepository.findAll();
    }

    /**
     * Get one examenClinique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExamenClinique> findOne(Long id) {
        log.debug("Request to get ExamenClinique : {}", id);
        return examenCliniqueRepository.findById(id);
    }

    /**
     * Delete the examenClinique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExamenClinique : {}", id);
        examenCliniqueRepository.deleteById(id);
    }
}
