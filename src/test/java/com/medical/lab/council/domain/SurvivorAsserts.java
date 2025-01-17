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
            .satisfies(e -> assertThat(e.getPractitionerType()).as("check practitionerType").isEqualTo(actual.getPractitionerType()))
            .satisfies(e -> assertThat(e.getRegistrationNumber()).as("check registrationNumber").isEqualTo(actual.getRegistrationNumber()))
            .satisfies(e -> assertThat(e.getTitle()).as("check title").isEqualTo(actual.getTitle()))
            .satisfies(e -> assertThat(e.getSurname()).as("check surname").isEqualTo(actual.getSurname()))
            .satisfies(e -> assertThat(e.getForenames()).as("check forenames").isEqualTo(actual.getForenames()))
            .satisfies(e -> assertThat(e.getPreviousSurname()).as("check previousSurname").isEqualTo(actual.getPreviousSurname()))
            .satisfies(e -> assertThat(e.getDob()).as("check dob").isEqualTo(actual.getDob()))
            .satisfies(e -> assertThat(e.getGender()).as("check gender").isEqualTo(actual.getGender()))
            .satisfies(e -> assertThat(e.getPlaceOfBirthTown()).as("check placeOfBirthTown").isEqualTo(actual.getPlaceOfBirthTown()))
            .satisfies(e ->
                assertThat(e.getPlaceOfBirthCountry()).as("check placeOfBirthCountry").isEqualTo(actual.getPlaceOfBirthCountry())
            )
            .satisfies(e -> assertThat(e.getNationality()).as("check nationality").isEqualTo(actual.getNationality()))
            .satisfies(e -> assertThat(e.getNationalId()).as("check nationalId").isEqualTo(actual.getNationalId()))
            .satisfies(e -> assertThat(e.getMaritalStatus()).as("check maritalStatus").isEqualTo(actual.getMaritalStatus()))
            .satisfies(e ->
                assertThat(e.getResidentialAddress1()).as("check residentialAddress1").isEqualTo(actual.getResidentialAddress1())
            )
            .satisfies(e ->
                assertThat(e.getResidentialAddress2()).as("check residentialAddress2").isEqualTo(actual.getResidentialAddress2())
            )
            .satisfies(e ->
                assertThat(e.getResidentialAddress3()).as("check residentialAddress3").isEqualTo(actual.getResidentialAddress3())
            )
            .satisfies(e -> assertThat(e.getHomePhone()).as("check homePhone").isEqualTo(actual.getHomePhone()))
            .satisfies(e -> assertThat(e.getWorkPhone()).as("check workPhone").isEqualTo(actual.getWorkPhone()))
            .satisfies(e -> assertThat(e.getCellPhone()).as("check cellPhone").isEqualTo(actual.getCellPhone()))
            .satisfies(e -> assertThat(e.getEmailAddress()).as("check emailAddress").isEqualTo(actual.getEmailAddress()))
            .satisfies(e ->
                assertThat(e.getNameOfPlaceOfEmployment())
                    .as("check nameOfPlaceOfEmployment")
                    .isEqualTo(actual.getNameOfPlaceOfEmployment())
            )
            .satisfies(e -> assertThat(e.getEmployerAddress()).as("check employerAddress").isEqualTo(actual.getEmployerAddress()))
            .satisfies(e -> assertThat(e.getEmployerEmail()).as("check employerEmail").isEqualTo(actual.getEmployerEmail()))
            .satisfies(e -> assertThat(e.getDateOfEmployment()).as("check dateOfEmployment").isEqualTo(actual.getDateOfEmployment()))
            .satisfies(e ->
                assertThat(e.getReasonForNonEmployment()).as("check reasonForNonEmployment").isEqualTo(actual.getReasonForNonEmployment())
            )
            .satisfies(e -> assertThat(e.getDateOfApplication()).as("check dateOfApplication").isEqualTo(actual.getDateOfApplication()))
            .satisfies(e -> assertThat(e.getApplicationFee()).as("check applicationFee").isEqualTo(actual.getApplicationFee()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getReasonNotApproved()).as("check reasonNotApproved").isEqualTo(actual.getReasonNotApproved()));
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
