package ma.ekili.service;

import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import ma.ekili.domain.*; // for static metamodels
import ma.ekili.domain.Surveillance;
import ma.ekili.repository.SurveillanceRepository;
import ma.ekili.service.dto.SurveillanceCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for executing complex queries for {@link Surveillance} entities in the database.
 * The main input is a {@link SurveillanceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Surveillance} or a {@link Page} of {@link Surveillance} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SurveillanceQueryService extends QueryService<Surveillance> {
    private final Logger log = LoggerFactory.getLogger(SurveillanceQueryService.class);

    private final SurveillanceRepository surveillanceRepository;

    public SurveillanceQueryService(SurveillanceRepository surveillanceRepository) {
        this.surveillanceRepository = surveillanceRepository;
    }

    /**
     * Return a {@link List} of {@link Surveillance} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Surveillance> findByCriteria(SurveillanceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Surveillance> specification = createSpecification(criteria);
        return surveillanceRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Surveillance} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Surveillance> findByCriteria(SurveillanceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Surveillance> specification = createSpecification(criteria);
        return surveillanceRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SurveillanceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Surveillance> specification = createSpecification(criteria);
        return surveillanceRepository.count(specification);
    }

    /**
     * Function to convert {@link SurveillanceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Surveillance> createSpecification(SurveillanceCriteria criteria) {
        Specification<Surveillance> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Surveillance_.id));
            }
            if (criteria.getInfirmier() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInfirmier(), Surveillance_.infirmier));
            }
            if (criteria.getPoste() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPoste(), Surveillance_.poste));
            }
            if (criteria.getGenerateur() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGenerateur(), Surveillance_.generateur));
            }
            if (criteria.getStatut() != null) {
                specification = specification.and(buildSpecification(criteria.getStatut(), Surveillance_.statut));
            }
            if (criteria.getPoid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPoid(), Surveillance_.poid));
            }
            if (criteria.getUfnet() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUfnet(), Surveillance_.ufnet));
            }
            if (criteria.getEtatConscience() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEtatConscience(), Surveillance_.etatConscience));
            }
            if (criteria.getEupneique() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEupneique(), Surveillance_.eupneique));
            }
            if (criteria.getRestitutionPar() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRestitutionPar(), Surveillance_.restitutionPar));
            }
            if (criteria.getAutreComplication() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getAutreComplication(), Surveillance_.autreComplication));
            }
            if (criteria.getObservation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getObservation(), Surveillance_.observation));
            }
            if (criteria.getTraitementId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTraitementId(),
                            root -> root.join(Surveillance_.traitement, JoinType.LEFT).get(TraitementPerdialyse_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
