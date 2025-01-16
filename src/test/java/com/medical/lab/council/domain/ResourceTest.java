package com.medical.lab.council.domain;

import static com.medical.lab.council.domain.ResourceTestSamples.*;
import static com.medical.lab.council.domain.SurvivorTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.medical.lab.council.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resource.class);
        Resource resource1 = getResourceSample1();
        Resource resource2 = new Resource();
        assertThat(resource1).isNotEqualTo(resource2);

        resource2.setId(resource1.getId());
        assertThat(resource1).isEqualTo(resource2);

        resource2 = getResourceSample2();
        assertThat(resource1).isNotEqualTo(resource2);
    }

    @Test
    void survivorTest() {
        Resource resource = getResourceRandomSampleGenerator();
        Survivor survivorBack = getSurvivorRandomSampleGenerator();

        resource.setSurvivor(survivorBack);
        assertThat(resource.getSurvivor()).isEqualTo(survivorBack);

        resource.survivor(null);
        assertThat(resource.getSurvivor()).isNull();
    }
}
