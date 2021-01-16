package ma.ekili.domain;

import static org.assertj.core.api.Assertions.assertThat;

import ma.ekili.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

public class DossierPatientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DossierPatient.class);
        DossierPatient dossierPatient1 = new DossierPatient();
        dossierPatient1.setId(1L);
        DossierPatient dossierPatient2 = new DossierPatient();
        dossierPatient2.setId(dossierPatient1.getId());
        assertThat(dossierPatient1).isEqualTo(dossierPatient2);
        dossierPatient2.setId(2L);
        assertThat(dossierPatient1).isNotEqualTo(dossierPatient2);
        dossierPatient1.setId(null);
        assertThat(dossierPatient1).isNotEqualTo(dossierPatient2);
    }
}
