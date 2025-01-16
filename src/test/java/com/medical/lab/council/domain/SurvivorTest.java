package com.medical.lab.council.domain;

import static com.medical.lab.council.domain.ResourceTestSamples.*;
import static com.medical.lab.council.domain.SurvivorTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.medical.lab.council.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SurvivorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Survivor.class);
        Survivor survivor1 = getSurvivorSample1();
        Survivor survivor2 = new Survivor();
        assertThat(survivor1).isNotEqualTo(survivor2);

        survivor2.setId(survivor1.getId());
        assertThat(survivor1).isEqualTo(survivor2);

        survivor2 = getSurvivorSample2();
        assertThat(survivor1).isNotEqualTo(survivor2);
    }

    @Test
    void resourceTest() {
        Survivor survivor = getSurvivorRandomSampleGenerator();
        Resource resourceBack = getResourceRandomSampleGenerator();

        survivor.addResource(resourceBack);
        assertThat(survivor.getResources()).containsOnly(resourceBack);
        assertThat(resourceBack.getSurvivor()).isEqualTo(survivor);

        survivor.removeResource(resourceBack);
        assertThat(survivor.getResources()).doesNotContain(resourceBack);
        assertThat(resourceBack.getSurvivor()).isNull();

        survivor.resources(new HashSet<>(Set.of(resourceBack)));
        assertThat(survivor.getResources()).containsOnly(resourceBack);
        assertThat(resourceBack.getSurvivor()).isEqualTo(survivor);

        survivor.setResources(new HashSet<>());
        assertThat(survivor.getResources()).doesNotContain(resourceBack);
        assertThat(resourceBack.getSurvivor()).isNull();
    }
}
