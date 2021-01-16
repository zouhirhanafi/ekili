package ma.ekili.service;

import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import ma.ekili.domain.*; // for static metamodels
import ma.ekili.domain.Prescription;
import ma.ekili.repository.PrescriptionRepository;
import ma.ekili.service.dto.PrescriptionCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Prescription} entities in the database.
 * The main input is a {@link PrescriptionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Prescription} or a {@link Page} of {@link Prescription} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PrescriptionQueryService extends QueryService<Prescription> {
    private final Logger log = LoggerFactory.getLogger(PrescriptionQueryService.class);

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionQueryService(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    /**
     * Return a {@link List} of {@link Prescription} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Prescription> findByCriteria(PrescriptionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Prescription> specification = createSpecification(criteria);
        return prescriptionRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Prescription} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Prescription> findByCriteria(PrescriptionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Prescription> specification = createSpecification(criteria);
        return prescriptionRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PrescriptionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Prescription> specification = createSpecification(criteria);
        return prescriptionRepository.count(specification);
    }

    /**
     * Function to convert {@link PrescriptionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Prescription> createSpecification(PrescriptionCriteria criteria) {
        Specification<Prescription> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Prescription_.id));
            }
            if (criteria.getDuree() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDuree(), Prescription_.duree));
            }
            if (criteria.getCapillaire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCapillaire(), Prescription_.capillaire));
            }
            if (criteria.getRestitutionP() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRestitutionP(), Prescription_.restitutionP));
            }
            if (criteria.getNiveauUrgence() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiveauUrgence(), Prescription_.niveauUrgence));
            }
            if (criteria.getUfTotale() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUfTotale(), Prescription_.ufTotale));
            }
            if (criteria.getRincage() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRincage(), Prescription_.rincage));
            }
            if (criteria.getTransfusion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTransfusion(), Prescription_.transfusion));
            }
            if (criteria.getDatePlanification() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getDatePlanification(), Prescription_.datePlanification));
            }
            if (criteria.getCircuit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCircuit(), Prescription_.circuit));
            }
            if (criteria.getAbordVasculaire() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAbordVasculaire(), Prescription_.abordVasculaire));
            }
            if (criteria.getProfil() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProfil(), Prescription_.profil));
            }
            if (criteria.getConductiviteP() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConductiviteP(), Prescription_.conductiviteP));
            }
            if (criteria.getDebitPompe() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDebitPompe(), Prescription_.debitPompe));
            }
            if (criteria.getTemperatureDialysat() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTemperatureDialysat(), Prescription_.temperatureDialysat));
            }
            if (criteria.getAtc() != null) {
                specification = specification.and(buildSpecification(criteria.getAtc(), Prescription_.atc));
            }
            if (criteria.getHnfh0() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHnfh0(), Prescription_.hnfh0));
            }
            if (criteria.getHnfh2() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHnfh2(), Prescription_.hnfh2));
            }
            if (criteria.getHbpm() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHbpm(), Prescription_.hbpm));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), Prescription_.statut));
            }
            if (criteria.getMotifAnnulation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMotifAnnulation(), Prescription_.motifAnnulation));
            }
            if (criteria.getMotifReport() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMotifReport(), Prescription_.motifReport));
            }
            if (criteria.getObservationP() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservationP(), Prescription_.observationP));
            }
            if (criteria.getTraitementId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTraitementId(),
                            root -> root.join(Prescription_.traitement, JoinType.LEFT).get(TraitementPerdialyse_.id)
                        )
                    );
            }
            if (criteria.getSurveillanceId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getSurveillanceId(),
                            root -> root.join(Prescription_.surveillance, JoinType.LEFT).get(Surveillance_.id)
                        )
                    );
            }
            if (criteria.getPatientId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPatientId(),
                            root -> root.join(Prescription_.patient, JoinType.LEFT).get(DossierPatient_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
