package ma.ekili.repository;

import ma.ekili.domain.ExamenClinique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExamenClinique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamenCliniqueRepository extends JpaRepository<ExamenClinique, Long> {}
