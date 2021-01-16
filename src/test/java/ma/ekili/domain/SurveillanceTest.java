package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class SurveillanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Surveillance.class);
        Surveillance surveillance1 = new Surveillance();
        surveillance1.setId(1L);
        Surveillance surveillance2 = new Surveillance();
        surveillance2.setId(surveillance1.getId());
        assertThat(surveillance1).isEqualTo(surveillance2);
        surveillance2.setId(2L);
        assertThat(surveillance1).isNotEqualTo(surveillance2);
        surveillance1.setId(null);
        assertThat(surveillance1).isNotEqualTo(surveillance2);
    }
}
