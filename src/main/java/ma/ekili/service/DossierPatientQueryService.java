package ma.ekili.service;

import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import ma.ekili.domain.*; // for static metamodels
import ma.ekili.domain.DossierPatient;
import ma.ekili.repository.DossierPatientRepository;
import ma.ekili.service.dto.DossierPatientCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link DossierPatient} entities in the database.
 * The main input is a {@link DossierPatientCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DossierPatient} or a {@link Page} of {@link DossierPatient} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DossierPatientQueryService extends QueryService<DossierPatient> {
    private final Logger log = LoggerFactory.getLogger(DossierPatientQueryService.class);

    private final DossierPatientRepository dossierPatientRepository;

    public DossierPatientQueryService(DossierPatientRepository dossierPatientRepository) {
        this.dossierPatientRepository = dossierPatientRepository;
    }

    /**
     * Return a {@link List} of {@link DossierPatient} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DossierPatient> findByCriteria(DossierPatientCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DossierPatient> specification = createSpecification(criteria);
        return dossierPatientRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link DossierPatient} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DossierPatient> findByCriteria(DossierPatientCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DossierPatient> specification = createSpecification(criteria);
        return dossierPatientRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DossierPatientCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DossierPatient> specification = createSpecification(criteria);
        return dossierPatientRepository.count(specification);
    }

    /**
     * Function to convert {@link DossierPatientCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DossierPatient> createSpecification(DossierPatientCriteria criteria) {
        Specification<DossierPatient> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DossierPatient_.id));
            }
            if (criteria.getIp() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIp(), DossierPatient_.ip));
            }
            if (criteria.getNom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNom(), DossierPatient_.nom));
            }
            if (criteria.getPrenom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrenom(), DossierPatient_.prenom));
            }
            if (criteria.getGenre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGenre(), DossierPatient_.genre));
            }
            if (criteria.getTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTel(), DossierPatient_.tel));
            }
            if (criteria.getAdresse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresse(), DossierPatient_.adresse));
            }
            if (criteria.getAmo() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmo(), DossierPatient_.amo));
            }
            if (criteria.getTypeCentreOrigine() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTypeCentreOrigine(), DossierPatient_.typeCentreOrigine));
            }
            if (criteria.getVilleCentreOrigine() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getVilleCentreOrigine(), DossierPatient_.villeCentreOrigine));
            }
            if (criteria.getObservation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservation(), DossierPatient_.observation));
            }
            if (criteria.getNaissance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNaissance(), DossierPatient_.naissance));
            }
            if (criteria.getAntecedentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAntecedentId(),
                            root -> root.join(DossierPatient_.antecedent, JoinType.LEFT).get(Antecedent_.id)
                        )
                    );
            }
            if (criteria.getDiagnosticId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDiagnosticId(),
                            root -> root.join(DossierPatient_.diagnostic, JoinType.LEFT).get(Diagnostic_.id)
                        )
                    );
            }
            if (criteria.getIndicationHdId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getIndicationHdId(),
                            root -> root.join(DossierPatient_.indicationHd, JoinType.LEFT).get(IndicationHd_.id)
                        )
                    );
            }
            if (criteria.getExamenCliniqueId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getExamenCliniqueId(),
                            root -> root.join(DossierPatient_.examenClinique, JoinType.LEFT).get(ExamenClinique_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
