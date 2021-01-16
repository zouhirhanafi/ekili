package ma.ekili.repository;

import ma.ekili.domain.DossierPatient;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DossierPatient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DossierPatientRepository extends JpaRepository<DossierPatient, Long>, JpaSpecificationExecutor<DossierPatient> {}
