package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class IndicationHdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndicationHd.class);
        IndicationHd indicationHd1 = new IndicationHd();
        indicationHd1.setId(1L);
        IndicationHd indicationHd2 = new IndicationHd();
        indicationHd2.setId(indicationHd1.getId());
        assertThat(indicationHd1).isEqualTo(indicationHd2);
        indicationHd2.setId(2L);
        assertThat(indicationHd1).isNotEqualTo(indicationHd2);
        indicationHd1.setId(null);
        assertThat(indicationHd1).isNotEqualTo(indicationHd2);
    }
}
