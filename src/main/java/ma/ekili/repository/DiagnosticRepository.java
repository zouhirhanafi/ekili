package ma.ekili.repository;

import ma.ekili.domain.Diagnostic;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Diagnostic entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {}
