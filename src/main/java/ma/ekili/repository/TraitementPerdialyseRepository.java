package ma.ekili.repository;

import ma.ekili.domain.TraitementPerdialyse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TraitementPerdialyse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TraitementPerdialyseRepository extends JpaRepository<TraitementPerdialyse, Long> {}
