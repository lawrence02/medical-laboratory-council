package com.medical.lab.council.web.rest;

import static com.medical.lab.council.domain.SurvivorAsserts.*;
import static com.medical.lab.council.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medical.lab.council.IntegrationTest;
import com.medical.lab.council.domain.Survivor;
import com.medical.lab.council.domain.enumeration.Gender;
import com.medical.lab.council.repository.SurvivorRepository;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SurvivorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SurvivorResourceIT {

    private static final String DEFAULT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_SURNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FORENAMES = "AAAAAAAAAA";
    private static final String UPDATED_FORENAMES = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUS_SURNAME = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUS_SURNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final Gender DEFAULT_GENDER = Gender.Male;
    private static final Gender UPDATED_GENDER = Gender.Female;

    private static final String ENTITY_API_URL = "/api/survivors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SurvivorRepository survivorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSurvivorMockMvc;

    private Survivor survivor;

    private Survivor insertedSurvivor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Survivor createEntity() {
        return new Survivor()
            .registrationNumber(DEFAULT_REGISTRATION_NUMBER)
            .surname(DEFAULT_SURNAME)
            .forenames(DEFAULT_FORENAMES)
            .previousSurname(DEFAULT_PREVIOUS_SURNAME)
            .dob(DEFAULT_DOB)
            .gender(DEFAULT_GENDER);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Survivor createUpdatedEntity() {
        return new Survivor()
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .previousSurname(UPDATED_PREVIOUS_SURNAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER);
    }

    @BeforeEach
    public void initTest() {
        survivor = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedSurvivor != null) {
            survivorRepository.delete(insertedSurvivor);
            insertedSurvivor = null;
        }
    }

    @Test
    @Transactional
    void createSurvivor() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Survivor
        var returnedSurvivor = om.readValue(
            restSurvivorMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Survivor.class
        );

        // Validate the Survivor in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSurvivorUpdatableFieldsEquals(returnedSurvivor, getPersistedSurvivor(returnedSurvivor));

        insertedSurvivor = returnedSurvivor;
    }

    @Test
    @Transactional
    void createSurvivorWithExistingId() throws Exception {
        // Create the Survivor with an existing ID
        survivor.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSurvivorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isBadRequest());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkRegistrationNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        survivor.setRegistrationNumber(null);

        // Create the Survivor, which fails.

        restSurvivorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSurnameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        survivor.setSurname(null);

        // Create the Survivor, which fails.

        restSurvivorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkForenamesIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        survivor.setForenames(null);

        // Create the Survivor, which fails.

        restSurvivorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDobIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        survivor.setDob(null);

        // Create the Survivor, which fails.

        restSurvivorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkGenderIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        survivor.setGender(null);

        // Create the Survivor, which fails.

        restSurvivorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSurvivors() throws Exception {
        // Initialize the database
        insertedSurvivor = survivorRepository.saveAndFlush(survivor);

        // Get all the survivorList
        restSurvivorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(survivor.getId().intValue())))
            .andExpect(jsonPath("$.[*].registrationNumber").value(hasItem(DEFAULT_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].forenames").value(hasItem(DEFAULT_FORENAMES)))
            .andExpect(jsonPath("$.[*].previousSurname").value(hasItem(DEFAULT_PREVIOUS_SURNAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())));
    }

    @Test
    @Transactional
    void getSurvivor() throws Exception {
        // Initialize the database
        insertedSurvivor = survivorRepository.saveAndFlush(survivor);

        // Get the survivor
        restSurvivorMockMvc
            .perform(get(ENTITY_API_URL_ID, survivor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(survivor.getId().intValue()))
            .andExpect(jsonPath("$.registrationNumber").value(DEFAULT_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.forenames").value(DEFAULT_FORENAMES))
            .andExpect(jsonPath("$.previousSurname").value(DEFAULT_PREVIOUS_SURNAME))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSurvivor() throws Exception {
        // Get the survivor
        restSurvivorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSurvivor() throws Exception {
        // Initialize the database
        insertedSurvivor = survivorRepository.saveAndFlush(survivor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the survivor
        Survivor updatedSurvivor = survivorRepository.findById(survivor.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSurvivor are not directly saved in db
        em.detach(updatedSurvivor);
        updatedSurvivor
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .previousSurname(UPDATED_PREVIOUS_SURNAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER);

        restSurvivorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSurvivor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSurvivor))
            )
            .andExpect(status().isOk());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSurvivorToMatchAllProperties(updatedSurvivor);
    }

    @Test
    @Transactional
    void putNonExistingSurvivor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        survivor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSurvivorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, survivor.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSurvivor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        survivor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSurvivorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(survivor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSurvivor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        survivor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSurvivorMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSurvivorWithPatch() throws Exception {
        // Initialize the database
        insertedSurvivor = survivorRepository.saveAndFlush(survivor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the survivor using partial update
        Survivor partialUpdatedSurvivor = new Survivor();
        partialUpdatedSurvivor.setId(survivor.getId());

        partialUpdatedSurvivor.surname(UPDATED_SURNAME).previousSurname(UPDATED_PREVIOUS_SURNAME).dob(UPDATED_DOB);

        restSurvivorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSurvivor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSurvivor))
            )
            .andExpect(status().isOk());

        // Validate the Survivor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSurvivorUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedSurvivor, survivor), getPersistedSurvivor(survivor));
    }

    @Test
    @Transactional
    void fullUpdateSurvivorWithPatch() throws Exception {
        // Initialize the database
        insertedSurvivor = survivorRepository.saveAndFlush(survivor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the survivor using partial update
        Survivor partialUpdatedSurvivor = new Survivor();
        partialUpdatedSurvivor.setId(survivor.getId());

        partialUpdatedSurvivor
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .previousSurname(UPDATED_PREVIOUS_SURNAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER);

        restSurvivorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSurvivor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSurvivor))
            )
            .andExpect(status().isOk());

        // Validate the Survivor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSurvivorUpdatableFieldsEquals(partialUpdatedSurvivor, getPersistedSurvivor(partialUpdatedSurvivor));
    }

    @Test
    @Transactional
    void patchNonExistingSurvivor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        survivor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSurvivorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, survivor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(survivor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSurvivor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        survivor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSurvivorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(survivor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSurvivor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        survivor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSurvivorMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(survivor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Survivor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSurvivor() throws Exception {
        // Initialize the database
        insertedSurvivor = survivorRepository.saveAndFlush(survivor);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the survivor
        restSurvivorMockMvc
            .perform(delete(ENTITY_API_URL_ID, survivor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return survivorRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Survivor getPersistedSurvivor(Survivor survivor) {
        return survivorRepository.findById(survivor.getId()).orElseThrow();
    }

    protected void assertPersistedSurvivorToMatchAllProperties(Survivor expectedSurvivor) {
        assertSurvivorAllPropertiesEquals(expectedSurvivor, getPersistedSurvivor(expectedSurvivor));
    }

    protected void assertPersistedSurvivorToMatchUpdatableProperties(Survivor expectedSurvivor) {
        assertSurvivorAllUpdatablePropertiesEquals(expectedSurvivor, getPersistedSurvivor(expectedSurvivor));
    }
}
