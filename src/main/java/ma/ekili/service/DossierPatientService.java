package ma.ekili.service;

import java.util.Optional;
import ma.ekili.domain.DossierPatient;
import ma.ekili.repository.DossierPatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link DossierPatient}.
 */
@Service
@Transactional
public class DossierPatientService {
    private final Logger log = LoggerFactory.getLogger(DossierPatientService.class);

    private final DossierPatientRepository dossierPatientRepository;

    public DossierPatientService(DossierPatientRepository dossierPatientRepository) {
        this.dossierPatientRepository = dossierPatientRepository;
    }

    /**
     * Save a dossierPatient.
     *
     * @param dossierPatient the entity to save.
     * @return the persisted entity.
     */
    public DossierPatient save(DossierPatient dossierPatient) {
        log.debug("Request to save DossierPatient : {}", dossierPatient);
        return dossierPatientRepository.save(dossierPatient);
    }

    /**
     * Get all the dossierPatients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DossierPatient> findAll(Pageable pageable) {
        log.debug("Request to get all DossierPatients");
        return dossierPatientRepository.findAll(pageable);
    }

    /**
     * Get one dossierPatient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DossierPatient> findOne(Long id) {
        log.debug("Request to get DossierPatient : {}", id);
        return dossierPatientRepository.findById(id);
    }

    /**
     * Delete the dossierPatient by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DossierPatient : {}", id);
        dossierPatientRepository.deleteById(id);
    }
}
