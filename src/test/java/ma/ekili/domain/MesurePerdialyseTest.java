package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class MesurePerdialyseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MesurePerdialyse.class);
        MesurePerdialyse mesurePerdialyse1 = new MesurePerdialyse();
        mesurePerdialyse1.setId(1L);
        MesurePerdialyse mesurePerdialyse2 = new MesurePerdialyse();
        mesurePerdialyse2.setId(mesurePerdialyse1.getId());
        assertThat(mesurePerdialyse1).isEqualTo(mesurePerdialyse2);
        mesurePerdialyse2.setId(2L);
        assertThat(mesurePerdialyse1).isNotEqualTo(mesurePerdialyse2);
        mesurePerdialyse1.setId(null);
        assertThat(mesurePerdialyse1).isNotEqualTo(mesurePerdialyse2);
    }
}
