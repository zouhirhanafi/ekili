package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class TraitementPerdialyseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TraitementPerdialyse.class);
        TraitementPerdialyse traitementPerdialyse1 = new TraitementPerdialyse();
        traitementPerdialyse1.setId(1L);
        TraitementPerdialyse traitementPerdialyse2 = new TraitementPerdialyse();
        traitementPerdialyse2.setId(traitementPerdialyse1.getId());
        assertThat(traitementPerdialyse1).isEqualTo(traitementPerdialyse2);
        traitementPerdialyse2.setId(2L);
        assertThat(traitementPerdialyse1).isNotEqualTo(traitementPerdialyse2);
        traitementPerdialyse1.setId(null);
        assertThat(traitementPerdialyse1).isNotEqualTo(traitementPerdialyse2);
    }
}
