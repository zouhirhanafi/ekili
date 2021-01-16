package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class ExamenBioligiqueTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExamenBioligique.class);
        ExamenBioligique examenBioligique1 = new ExamenBioligique();
        examenBioligique1.setId(1L);
        ExamenBioligique examenBioligique2 = new ExamenBioligique();
        examenBioligique2.setId(examenBioligique1.getId());
        assertThat(examenBioligique1).isEqualTo(examenBioligique2);
        examenBioligique2.setId(2L);
        assertThat(examenBioligique1).isNotEqualTo(examenBioligique2);
        examenBioligique1.setId(null);
        assertThat(examenBioligique1).isNotEqualTo(examenBioligique2);
    }
}
