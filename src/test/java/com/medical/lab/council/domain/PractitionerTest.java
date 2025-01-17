package com.medical.lab.council.domain;

import static com.medical.lab.council.domain.PractitionerTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.medical.lab.council.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PractitionerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Practitioner.class);
        Practitioner practitioner1 = getPractitionerSample1();
        Practitioner practitioner2 = new Practitioner();
        assertThat(practitioner1).isNotEqualTo(practitioner2);

        practitioner2.setId(practitioner1.getId());
        assertThat(practitioner1).isEqualTo(practitioner2);

        practitioner2 = getPractitionerSample2();
        assertThat(practitioner1).isNotEqualTo(practitioner2);
    }
}
