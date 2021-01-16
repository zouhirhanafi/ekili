package ma.ekili.repository;

import ma.ekili.domain.Surveillance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Surveillance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveillanceRepository extends JpaRepository<Surveillance, Long>, JpaSpecificationExecutor<Surveillance> {}
