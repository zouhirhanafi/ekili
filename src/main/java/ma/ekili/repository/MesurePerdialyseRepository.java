package ma.ekili.repository;

import ma.ekili.domain.MesurePerdialyse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MesurePerdialyse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MesurePerdialyseRepository extends JpaRepository<MesurePerdialyse, Long> {}
