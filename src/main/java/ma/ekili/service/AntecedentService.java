package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.Antecedent;
import ma.ekili.repository.AntecedentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Antecedent}.
 */
@Service
@Transactional
public class AntecedentService {
    private final Logger log = LoggerFactory.getLogger(AntecedentService.class);

    private final AntecedentRepository antecedentRepository;

    public AntecedentService(AntecedentRepository antecedentRepository) {
        this.antecedentRepository = antecedentRepository;
    }

    /**
     * Save a antecedent.
     *
     * @param antecedent the entity to save.
     * @return the persisted entity.
     */
    public Antecedent save(Antecedent antecedent) {
        log.debug("Request to save Antecedent : {}", antecedent);
        return antecedentRepository.save(antecedent);
    }

    /**
     * Get all the antecedents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Antecedent> findAll() {
        log.debug("Request to get all Antecedents");
        return antecedentRepository.findAll();
    }

    /**
     * Get one antecedent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Antecedent> findOne(Long id) {
        log.debug("Request to get Antecedent : {}", id);
        return antecedentRepository.findById(id);
    }

    /**
     * Delete the antecedent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Antecedent : {}", id);
        antecedentRepository.deleteById(id);
    }
}
