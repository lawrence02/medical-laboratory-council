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
import com.medical.lab.council.domain.enumeration.AreaOfEmployment;
import com.medical.lab.council.domain.enumeration.EmploymentStatus;
import com.medical.lab.council.domain.enumeration.Gender;
import com.medical.lab.council.domain.enumeration.MaritalStatus;
import com.medical.lab.council.domain.enumeration.PractitionerType;
import com.medical.lab.council.domain.enumeration.Province;
import com.medical.lab.council.domain.enumeration.Title;
import com.medical.lab.council.domain.enumeration.TypeOfInstitution;
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

    private static final PractitionerType DEFAULT_PRACTITIONER_TYPE = PractitionerType.ClinicalScientist;
    private static final PractitionerType UPDATED_PRACTITIONER_TYPE = PractitionerType.MedicalResearchScientist;

    private static final String DEFAULT_REGISTRATION_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REGISTRATION_NUMBER = "BBBBBBBBBB";

    private static final Title DEFAULT_TITLE = Title.Mr;
    private static final Title UPDATED_TITLE = Title.Mrs;

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

    private static final String DEFAULT_PLACE_OF_BIRTH_TOWN = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH_TOWN = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE_OF_BIRTH_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_OF_BIRTH_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_NATIONAL_ID = "BBBBBBBBBB";

    private static final MaritalStatus DEFAULT_MARITAL_STATUS = MaritalStatus.Married;
    private static final MaritalStatus UPDATED_MARITAL_STATUS = MaritalStatus.Single;

    private static final String DEFAULT_RESIDENTIAL_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENTIAL_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENTIAL_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_HOME_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_WORK_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CELL_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_CELL_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_OF_PLACE_OF_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_NAME_OF_PLACE_OF_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_EMPLOYMENT = "BBBBBBBBBB";

    private static final AreaOfEmployment DEFAULT_AREA_OF_EMPLOYMENT = AreaOfEmployment.Government;
    private static final AreaOfEmployment UPDATED_AREA_OF_EMPLOYMENT = AreaOfEmployment.Mission;

    private static final EmploymentStatus DEFAULT_EMPLOYMENT_STATUS = EmploymentStatus.FullTime;
    private static final EmploymentStatus UPDATED_EMPLOYMENT_STATUS = EmploymentStatus.PartTime;

    private static final TypeOfInstitution DEFAULT_TYPE_OF_INSTITUTION = TypeOfInstitution.Hospital;
    private static final TypeOfInstitution UPDATED_TYPE_OF_INSTITUTION = TypeOfInstitution.Clinic;

    private static final Province DEFAULT_PROVINCE_EMPLOYED = Province.Bulawayo;
    private static final Province UPDATED_PROVINCE_EMPLOYED = Province.Harare;

    private static final String DEFAULT_REASON_FOR_NON_EMPLOYMENT = "AAAAAAAAAA";
    private static final String UPDATED_REASON_FOR_NON_EMPLOYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_APPLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION_FEE = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_FEE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REASON_NOT_APPROVED = "AAAAAAAAAA";
    private static final String UPDATED_REASON_NOT_APPROVED = "BBBBBBBBBB";

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
            .practitionerType(DEFAULT_PRACTITIONER_TYPE)
            .registrationNumber(DEFAULT_REGISTRATION_NUMBER)
            .title(DEFAULT_TITLE)
            .surname(DEFAULT_SURNAME)
            .forenames(DEFAULT_FORENAMES)
            .previousSurname(DEFAULT_PREVIOUS_SURNAME)
            .dob(DEFAULT_DOB)
            .gender(DEFAULT_GENDER)
            .placeOfBirthTown(DEFAULT_PLACE_OF_BIRTH_TOWN)
            .placeOfBirthCountry(DEFAULT_PLACE_OF_BIRTH_COUNTRY)
            .nationality(DEFAULT_NATIONALITY)
            .nationalId(DEFAULT_NATIONAL_ID)
            .maritalStatus(DEFAULT_MARITAL_STATUS)
            .residentialAddress1(DEFAULT_RESIDENTIAL_ADDRESS_1)
            .residentialAddress2(DEFAULT_RESIDENTIAL_ADDRESS_2)
            .residentialAddress3(DEFAULT_RESIDENTIAL_ADDRESS_3)
            .homePhone(DEFAULT_HOME_PHONE)
            .workPhone(DEFAULT_WORK_PHONE)
            .cellPhone(DEFAULT_CELL_PHONE)
            .emailAddress(DEFAULT_EMAIL_ADDRESS)
            .nameOfPlaceOfEmployment(DEFAULT_NAME_OF_PLACE_OF_EMPLOYMENT)
            .employerAddress(DEFAULT_EMPLOYER_ADDRESS)
            .employerEmail(DEFAULT_EMPLOYER_EMAIL)
            .dateOfEmployment(DEFAULT_DATE_OF_EMPLOYMENT)
            .areaOfEmployment(DEFAULT_AREA_OF_EMPLOYMENT)
            .employmentStatus(DEFAULT_EMPLOYMENT_STATUS)
            .typeOfInstitution(DEFAULT_TYPE_OF_INSTITUTION)
            .provinceEmployed(DEFAULT_PROVINCE_EMPLOYED)
            .reasonForNonEmployment(DEFAULT_REASON_FOR_NON_EMPLOYMENT)
            .dateOfApplication(DEFAULT_DATE_OF_APPLICATION)
            .applicationFee(DEFAULT_APPLICATION_FEE)
            .status(DEFAULT_STATUS)
            .reasonNotApproved(DEFAULT_REASON_NOT_APPROVED);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Survivor createUpdatedEntity() {
        return new Survivor()
            .practitionerType(UPDATED_PRACTITIONER_TYPE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .title(UPDATED_TITLE)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .previousSurname(UPDATED_PREVIOUS_SURNAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .placeOfBirthTown(UPDATED_PLACE_OF_BIRTH_TOWN)
            .placeOfBirthCountry(UPDATED_PLACE_OF_BIRTH_COUNTRY)
            .nationality(UPDATED_NATIONALITY)
            .nationalId(UPDATED_NATIONAL_ID)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .residentialAddress1(UPDATED_RESIDENTIAL_ADDRESS_1)
            .residentialAddress2(UPDATED_RESIDENTIAL_ADDRESS_2)
            .residentialAddress3(UPDATED_RESIDENTIAL_ADDRESS_3)
            .homePhone(UPDATED_HOME_PHONE)
            .workPhone(UPDATED_WORK_PHONE)
            .cellPhone(UPDATED_CELL_PHONE)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .nameOfPlaceOfEmployment(UPDATED_NAME_OF_PLACE_OF_EMPLOYMENT)
            .employerAddress(UPDATED_EMPLOYER_ADDRESS)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .dateOfEmployment(UPDATED_DATE_OF_EMPLOYMENT)
            .areaOfEmployment(UPDATED_AREA_OF_EMPLOYMENT)
            .employmentStatus(UPDATED_EMPLOYMENT_STATUS)
            .typeOfInstitution(UPDATED_TYPE_OF_INSTITUTION)
            .provinceEmployed(UPDATED_PROVINCE_EMPLOYED)
            .reasonForNonEmployment(UPDATED_REASON_FOR_NON_EMPLOYMENT)
            .dateOfApplication(UPDATED_DATE_OF_APPLICATION)
            .applicationFee(UPDATED_APPLICATION_FEE)
            .status(UPDATED_STATUS)
            .reasonNotApproved(UPDATED_REASON_NOT_APPROVED);
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
            .andExpect(jsonPath("$.[*].practitionerType").value(hasItem(DEFAULT_PRACTITIONER_TYPE.toString())))
            .andExpect(jsonPath("$.[*].registrationNumber").value(hasItem(DEFAULT_REGISTRATION_NUMBER)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].surname").value(hasItem(DEFAULT_SURNAME)))
            .andExpect(jsonPath("$.[*].forenames").value(hasItem(DEFAULT_FORENAMES)))
            .andExpect(jsonPath("$.[*].previousSurname").value(hasItem(DEFAULT_PREVIOUS_SURNAME)))
            .andExpect(jsonPath("$.[*].dob").value(hasItem(DEFAULT_DOB.toString())))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].placeOfBirthTown").value(hasItem(DEFAULT_PLACE_OF_BIRTH_TOWN)))
            .andExpect(jsonPath("$.[*].placeOfBirthCountry").value(hasItem(DEFAULT_PLACE_OF_BIRTH_COUNTRY)))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].nationalId").value(hasItem(DEFAULT_NATIONAL_ID)))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS.toString())))
            .andExpect(jsonPath("$.[*].residentialAddress1").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].residentialAddress2").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].residentialAddress3").value(hasItem(DEFAULT_RESIDENTIAL_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].homePhone").value(hasItem(DEFAULT_HOME_PHONE)))
            .andExpect(jsonPath("$.[*].workPhone").value(hasItem(DEFAULT_WORK_PHONE)))
            .andExpect(jsonPath("$.[*].cellPhone").value(hasItem(DEFAULT_CELL_PHONE)))
            .andExpect(jsonPath("$.[*].emailAddress").value(hasItem(DEFAULT_EMAIL_ADDRESS)))
            .andExpect(jsonPath("$.[*].nameOfPlaceOfEmployment").value(hasItem(DEFAULT_NAME_OF_PLACE_OF_EMPLOYMENT)))
            .andExpect(jsonPath("$.[*].employerAddress").value(hasItem(DEFAULT_EMPLOYER_ADDRESS)))
            .andExpect(jsonPath("$.[*].employerEmail").value(hasItem(DEFAULT_EMPLOYER_EMAIL)))
            .andExpect(jsonPath("$.[*].dateOfEmployment").value(hasItem(DEFAULT_DATE_OF_EMPLOYMENT)))
            .andExpect(jsonPath("$.[*].areaOfEmployment").value(hasItem(DEFAULT_AREA_OF_EMPLOYMENT.toString())))
            .andExpect(jsonPath("$.[*].employmentStatus").value(hasItem(DEFAULT_EMPLOYMENT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].typeOfInstitution").value(hasItem(DEFAULT_TYPE_OF_INSTITUTION.toString())))
            .andExpect(jsonPath("$.[*].provinceEmployed").value(hasItem(DEFAULT_PROVINCE_EMPLOYED.toString())))
            .andExpect(jsonPath("$.[*].reasonForNonEmployment").value(hasItem(DEFAULT_REASON_FOR_NON_EMPLOYMENT)))
            .andExpect(jsonPath("$.[*].dateOfApplication").value(hasItem(DEFAULT_DATE_OF_APPLICATION)))
            .andExpect(jsonPath("$.[*].applicationFee").value(hasItem(DEFAULT_APPLICATION_FEE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].reasonNotApproved").value(hasItem(DEFAULT_REASON_NOT_APPROVED)));
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
            .andExpect(jsonPath("$.practitionerType").value(DEFAULT_PRACTITIONER_TYPE.toString()))
            .andExpect(jsonPath("$.registrationNumber").value(DEFAULT_REGISTRATION_NUMBER))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.surname").value(DEFAULT_SURNAME))
            .andExpect(jsonPath("$.forenames").value(DEFAULT_FORENAMES))
            .andExpect(jsonPath("$.previousSurname").value(DEFAULT_PREVIOUS_SURNAME))
            .andExpect(jsonPath("$.dob").value(DEFAULT_DOB.toString()))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.placeOfBirthTown").value(DEFAULT_PLACE_OF_BIRTH_TOWN))
            .andExpect(jsonPath("$.placeOfBirthCountry").value(DEFAULT_PLACE_OF_BIRTH_COUNTRY))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.nationalId").value(DEFAULT_NATIONAL_ID))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS.toString()))
            .andExpect(jsonPath("$.residentialAddress1").value(DEFAULT_RESIDENTIAL_ADDRESS_1))
            .andExpect(jsonPath("$.residentialAddress2").value(DEFAULT_RESIDENTIAL_ADDRESS_2))
            .andExpect(jsonPath("$.residentialAddress3").value(DEFAULT_RESIDENTIAL_ADDRESS_3))
            .andExpect(jsonPath("$.homePhone").value(DEFAULT_HOME_PHONE))
            .andExpect(jsonPath("$.workPhone").value(DEFAULT_WORK_PHONE))
            .andExpect(jsonPath("$.cellPhone").value(DEFAULT_CELL_PHONE))
            .andExpect(jsonPath("$.emailAddress").value(DEFAULT_EMAIL_ADDRESS))
            .andExpect(jsonPath("$.nameOfPlaceOfEmployment").value(DEFAULT_NAME_OF_PLACE_OF_EMPLOYMENT))
            .andExpect(jsonPath("$.employerAddress").value(DEFAULT_EMPLOYER_ADDRESS))
            .andExpect(jsonPath("$.employerEmail").value(DEFAULT_EMPLOYER_EMAIL))
            .andExpect(jsonPath("$.dateOfEmployment").value(DEFAULT_DATE_OF_EMPLOYMENT))
            .andExpect(jsonPath("$.areaOfEmployment").value(DEFAULT_AREA_OF_EMPLOYMENT.toString()))
            .andExpect(jsonPath("$.employmentStatus").value(DEFAULT_EMPLOYMENT_STATUS.toString()))
            .andExpect(jsonPath("$.typeOfInstitution").value(DEFAULT_TYPE_OF_INSTITUTION.toString()))
            .andExpect(jsonPath("$.provinceEmployed").value(DEFAULT_PROVINCE_EMPLOYED.toString()))
            .andExpect(jsonPath("$.reasonForNonEmployment").value(DEFAULT_REASON_FOR_NON_EMPLOYMENT))
            .andExpect(jsonPath("$.dateOfApplication").value(DEFAULT_DATE_OF_APPLICATION))
            .andExpect(jsonPath("$.applicationFee").value(DEFAULT_APPLICATION_FEE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.reasonNotApproved").value(DEFAULT_REASON_NOT_APPROVED));
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
            .practitionerType(UPDATED_PRACTITIONER_TYPE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .title(UPDATED_TITLE)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .previousSurname(UPDATED_PREVIOUS_SURNAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .placeOfBirthTown(UPDATED_PLACE_OF_BIRTH_TOWN)
            .placeOfBirthCountry(UPDATED_PLACE_OF_BIRTH_COUNTRY)
            .nationality(UPDATED_NATIONALITY)
            .nationalId(UPDATED_NATIONAL_ID)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .residentialAddress1(UPDATED_RESIDENTIAL_ADDRESS_1)
            .residentialAddress2(UPDATED_RESIDENTIAL_ADDRESS_2)
            .residentialAddress3(UPDATED_RESIDENTIAL_ADDRESS_3)
            .homePhone(UPDATED_HOME_PHONE)
            .workPhone(UPDATED_WORK_PHONE)
            .cellPhone(UPDATED_CELL_PHONE)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .nameOfPlaceOfEmployment(UPDATED_NAME_OF_PLACE_OF_EMPLOYMENT)
            .employerAddress(UPDATED_EMPLOYER_ADDRESS)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .dateOfEmployment(UPDATED_DATE_OF_EMPLOYMENT)
            .areaOfEmployment(UPDATED_AREA_OF_EMPLOYMENT)
            .employmentStatus(UPDATED_EMPLOYMENT_STATUS)
            .typeOfInstitution(UPDATED_TYPE_OF_INSTITUTION)
            .provinceEmployed(UPDATED_PROVINCE_EMPLOYED)
            .reasonForNonEmployment(UPDATED_REASON_FOR_NON_EMPLOYMENT)
            .dateOfApplication(UPDATED_DATE_OF_APPLICATION)
            .applicationFee(UPDATED_APPLICATION_FEE)
            .status(UPDATED_STATUS)
            .reasonNotApproved(UPDATED_REASON_NOT_APPROVED);

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

        partialUpdatedSurvivor
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .dob(UPDATED_DOB)
            .placeOfBirthCountry(UPDATED_PLACE_OF_BIRTH_COUNTRY)
            .nationality(UPDATED_NATIONALITY)
            .nationalId(UPDATED_NATIONAL_ID)
            .residentialAddress1(UPDATED_RESIDENTIAL_ADDRESS_1)
            .residentialAddress2(UPDATED_RESIDENTIAL_ADDRESS_2)
            .residentialAddress3(UPDATED_RESIDENTIAL_ADDRESS_3)
            .homePhone(UPDATED_HOME_PHONE)
            .workPhone(UPDATED_WORK_PHONE)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .employerAddress(UPDATED_EMPLOYER_ADDRESS)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .areaOfEmployment(UPDATED_AREA_OF_EMPLOYMENT)
            .typeOfInstitution(UPDATED_TYPE_OF_INSTITUTION)
            .provinceEmployed(UPDATED_PROVINCE_EMPLOYED)
            .reasonForNonEmployment(UPDATED_REASON_FOR_NON_EMPLOYMENT)
            .applicationFee(UPDATED_APPLICATION_FEE)
            .status(UPDATED_STATUS);

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
            .practitionerType(UPDATED_PRACTITIONER_TYPE)
            .registrationNumber(UPDATED_REGISTRATION_NUMBER)
            .title(UPDATED_TITLE)
            .surname(UPDATED_SURNAME)
            .forenames(UPDATED_FORENAMES)
            .previousSurname(UPDATED_PREVIOUS_SURNAME)
            .dob(UPDATED_DOB)
            .gender(UPDATED_GENDER)
            .placeOfBirthTown(UPDATED_PLACE_OF_BIRTH_TOWN)
            .placeOfBirthCountry(UPDATED_PLACE_OF_BIRTH_COUNTRY)
            .nationality(UPDATED_NATIONALITY)
            .nationalId(UPDATED_NATIONAL_ID)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .residentialAddress1(UPDATED_RESIDENTIAL_ADDRESS_1)
            .residentialAddress2(UPDATED_RESIDENTIAL_ADDRESS_2)
            .residentialAddress3(UPDATED_RESIDENTIAL_ADDRESS_3)
            .homePhone(UPDATED_HOME_PHONE)
            .workPhone(UPDATED_WORK_PHONE)
            .cellPhone(UPDATED_CELL_PHONE)
            .emailAddress(UPDATED_EMAIL_ADDRESS)
            .nameOfPlaceOfEmployment(UPDATED_NAME_OF_PLACE_OF_EMPLOYMENT)
            .employerAddress(UPDATED_EMPLOYER_ADDRESS)
            .employerEmail(UPDATED_EMPLOYER_EMAIL)
            .dateOfEmployment(UPDATED_DATE_OF_EMPLOYMENT)
            .areaOfEmployment(UPDATED_AREA_OF_EMPLOYMENT)
            .employmentStatus(UPDATED_EMPLOYMENT_STATUS)
            .typeOfInstitution(UPDATED_TYPE_OF_INSTITUTION)
            .provinceEmployed(UPDATED_PROVINCE_EMPLOYED)
            .reasonForNonEmployment(UPDATED_REASON_FOR_NON_EMPLOYMENT)
            .dateOfApplication(UPDATED_DATE_OF_APPLICATION)
            .applicationFee(UPDATED_APPLICATION_FEE)
            .status(UPDATED_STATUS)
            .reasonNotApproved(UPDATED_REASON_NOT_APPROVED);

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
