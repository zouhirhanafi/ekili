package ma.ekili.repository;

import ma.ekili.domain.IndicationHd;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the IndicationHd entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndicationHdRepository extends JpaRepository<IndicationHd, Long> {}
