package ma.ekili.repository;

import ma.ekili.domain.ExamenBioligique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ExamenBioligique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamenBioligiqueRepository extends JpaRepository<ExamenBioligique, Long> {}
