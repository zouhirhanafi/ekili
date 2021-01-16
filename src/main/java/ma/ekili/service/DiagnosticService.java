package ma.ekili.service;

import java.util.List;
import java.util.Optional;
import ma.ekili.domain.Diagnostic;
import ma.ekili.repository.DiagnosticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Diagnostic}.
 */
@Service
@Transactional
public class DiagnosticService {
    private final Logger log = LoggerFactory.getLogger(DiagnosticService.class);

    private final DiagnosticRepository diagnosticRepository;

    public DiagnosticService(DiagnosticRepository diagnosticRepository) {
        this.diagnosticRepository = diagnosticRepository;
    }

    /**
     * Save a diagnostic.
     *
     * @param diagnostic the entity to save.
     * @return the persisted entity.
     */
    public Diagnostic save(Diagnostic diagnostic) {
        log.debug("Request to save Diagnostic : {}", diagnostic);
        return diagnosticRepository.save(diagnostic);
    }

    /**
     * Get all the diagnostics.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Diagnostic> findAll() {
        log.debug("Request to get all Diagnostics");
        return diagnosticRepository.findAll();
    }

    /**
     * Get one diagnostic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Diagnostic> findOne(Long id) {
        log.debug("Request to get Diagnostic : {}", id);
        return diagnosticRepository.findById(id);
    }

    /**
     * Delete the diagnostic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Diagnostic : {}", id);
        diagnosticRepository.deleteById(id);
    }
}
