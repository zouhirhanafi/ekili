package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ExamenCliniqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExamenClinique.class);
        ExamenClinique examenClinique1 = new ExamenClinique();
        examenClinique1.setId(1L);
        ExamenClinique examenClinique2 = new ExamenClinique();
        examenClinique2.setId(examenClinique1.getId());
        assertThat(examenClinique1).isEqualTo(examenClinique2);
        examenClinique2.setId(2L);
        assertThat(examenClinique1).isNotEqualTo(examenClinique2);
        examenClinique1.setId(null);
        assertThat(examenClinique1).isNotEqualTo(examenClinique2);
    }
}
