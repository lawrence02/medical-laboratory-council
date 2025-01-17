package com.medical.lab.council.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class SurvivorAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSurvivorAllPropertiesEquals(Survivor expected, Survivor actual) {
        assertSurvivorAutoGeneratedPropertiesEquals(expected, actual);
        assertSurvivorAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSurvivorAllUpdatablePropertiesEquals(Survivor expected, Survivor actual) {
        assertSurvivorUpdatableFieldsEquals(expected, actual);
        assertSurvivorUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSurvivorAutoGeneratedPropertiesEquals(Survivor expected, Survivor actual) {
        assertThat(expected)
            .as("Verify Survivor auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSurvivorUpdatableFieldsEquals(Survivor expected, Survivor actual) {
        assertThat(expected)
            .as("Verify Survivor relevant properties")
            .satisfies(e -> assertThat(e.getRegistrationNumber()).as("check registrationNumber").isEqualTo(actual.getRegistrationNumber()))
            .satisfies(e -> assertThat(e.getSurname()).as("check surname").isEqualTo(actual.getSurname()))
            .satisfies(e -> assertThat(e.getForenames()).as("check forenames").isEqualTo(actual.getForenames()))
            .satisfies(e -> assertThat(e.getPreviousSurname()).as("check previousSurname").isEqualTo(actual.getPreviousSurname()))
            .satisfies(e -> assertThat(e.getDob()).as("check dob").isEqualTo(actual.getDob()))
            .satisfies(e -> assertThat(e.getGender()).as("check gender").isEqualTo(actual.getGender()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertSurvivorUpdatableRelationshipsEquals(Survivor expected, Survivor actual) {
        // empty method
    }
}
