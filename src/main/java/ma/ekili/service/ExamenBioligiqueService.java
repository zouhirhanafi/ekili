package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.ExamenBioligique;
import ma.ekili.repository.ExamenBioligiqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExamenBioligique}.
 */
@Service
@Transactional
public class ExamenBioligiqueService {
    private final Logger log = LoggerFactory.getLogger(ExamenBioligiqueService.class);

    private final ExamenBioligiqueRepository examenBioligiqueRepository;

    public ExamenBioligiqueService(ExamenBioligiqueRepository examenBioligiqueRepository) {
        this.examenBioligiqueRepository = examenBioligiqueRepository;
    }

    /**
     * Save a examenBioligique.
     *
     * @param examenBioligique the entity to save.
     * @return the persisted entity.
     */
    public ExamenBioligique save(ExamenBioligique examenBioligique) {
        log.debug("Request to save ExamenBioligique : {}", examenBioligique);
        return examenBioligiqueRepository.save(examenBioligique);
    }

    /**
     * Get all the examenBioligiques.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ExamenBioligique> findAll() {
        log.debug("Request to get all ExamenBioligiques");
        return examenBioligiqueRepository.findAll();
    }

    /**
     * Get one examenBioligique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExamenBioligique> findOne(Long id) {
        log.debug("Request to get ExamenBioligique : {}", id);
        return examenBioligiqueRepository.findById(id);
    }

    /**
     * Delete the examenBioligique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExamenBioligique : {}", id);
        examenBioligiqueRepository.deleteById(id);
    }
}
