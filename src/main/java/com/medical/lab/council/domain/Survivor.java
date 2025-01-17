package com.medical.lab.council.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medical.lab.council.domain.enumeration.Gender;
import com.medical.lab.council.domain.enumeration.MaritalStatus;
import com.medical.lab.council.domain.enumeration.Title;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Survivor.
 */
@Entity
@Table(name = "survivor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Survivor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The survivorId attribute.
     */
    @Schema(description = "The survivorId attribute.", required = true)
    @NotNull
    @Column(name = "registration_number", nullable = false)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "title")
    private Title title;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "forenames", nullable = false)
    private String forenames;

    @Column(name = "previous_surname")
    private String previousSurname;

    @NotNull
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "place_of_birth_town")
    private String placeOfBirthTown;

    @Column(name = "place_of_birth_country")
    private String placeOfBirthCountry;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "national_id")
    private String nationalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "residential_address_1")
    private String residentialAddress1;

    @Column(name = "residential_address_2")
    private String residentialAddress2;

    @Column(name = "residential_address_3")
    private String residentialAddress3;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "name_of_place_of_employment")
    private String nameOfPlaceOfEmployment;

    @Column(name = "employer_address")
    private String employerAddress;

    @Column(name = "employer_email")
    private String employerEmail;

    @Column(name = "date_of_employment")
    private String dateOfEmployment;

    @Column(name = "reason_for_non_employment")
    private String reasonForNonEmployment;

    @Column(name = "date_of_application")
    private String dateOfApplication;

    @Column(name = "application_fee")
    private String applicationFee;

    @Column(name = "status")
    private String status;

    @Column(name = "reason_not_approved")
    private String reasonNotApproved;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "survivor")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "survivor" }, allowSetters = true)
    private Set<Resource> resources = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Survivor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public Survivor registrationNumber(String registrationNumber) {
        this.setRegistrationNumber(registrationNumber);
        return this;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Title getTitle() {
        return this.title;
    }

    public Survivor title(Title title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getSurname() {
        return this.surname;
    }

    public Survivor surname(String surname) {
        this.setSurname(surname);
        return this;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForenames() {
        return this.forenames;
    }

    public Survivor forenames(String forenames) {
        this.setForenames(forenames);
        return this;
    }

    public void setForenames(String forenames) {
        this.forenames = forenames;
    }

    public String getPreviousSurname() {
        return this.previousSurname;
    }

    public Survivor previousSurname(String previousSurname) {
        this.setPreviousSurname(previousSurname);
        return this;
    }

    public void setPreviousSurname(String previousSurname) {
        this.previousSurname = previousSurname;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public Survivor dob(LocalDate dob) {
        this.setDob(dob);
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Survivor gender(Gender gender) {
        this.setGender(gender);
        return this;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirthTown() {
        return this.placeOfBirthTown;
    }

    public Survivor placeOfBirthTown(String placeOfBirthTown) {
        this.setPlaceOfBirthTown(placeOfBirthTown);
        return this;
    }

    public void setPlaceOfBirthTown(String placeOfBirthTown) {
        this.placeOfBirthTown = placeOfBirthTown;
    }

    public String getPlaceOfBirthCountry() {
        return this.placeOfBirthCountry;
    }

    public Survivor placeOfBirthCountry(String placeOfBirthCountry) {
        this.setPlaceOfBirthCountry(placeOfBirthCountry);
        return this;
    }

    public void setPlaceOfBirthCountry(String placeOfBirthCountry) {
        this.placeOfBirthCountry = placeOfBirthCountry;
    }

    public String getNationality() {
        return this.nationality;
    }

    public Survivor nationality(String nationality) {
        this.setNationality(nationality);
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public Survivor nationalId(String nationalId) {
        this.setNationalId(nationalId);
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public MaritalStatus getMaritalStatus() {
        return this.maritalStatus;
    }

    public Survivor maritalStatus(MaritalStatus maritalStatus) {
        this.setMaritalStatus(maritalStatus);
        return this;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getResidentialAddress1() {
        return this.residentialAddress1;
    }

    public Survivor residentialAddress1(String residentialAddress1) {
        this.setResidentialAddress1(residentialAddress1);
        return this;
    }

    public void setResidentialAddress1(String residentialAddress1) {
        this.residentialAddress1 = residentialAddress1;
    }

    public String getResidentialAddress2() {
        return this.residentialAddress2;
    }

    public Survivor residentialAddress2(String residentialAddress2) {
        this.setResidentialAddress2(residentialAddress2);
        return this;
    }

    public void setResidentialAddress2(String residentialAddress2) {
        this.residentialAddress2 = residentialAddress2;
    }

    public String getResidentialAddress3() {
        return this.residentialAddress3;
    }

    public Survivor residentialAddress3(String residentialAddress3) {
        this.setResidentialAddress3(residentialAddress3);
        return this;
    }

    public void setResidentialAddress3(String residentialAddress3) {
        this.residentialAddress3 = residentialAddress3;
    }

    public String getHomePhone() {
        return this.homePhone;
    }

    public Survivor homePhone(String homePhone) {
        this.setHomePhone(homePhone);
        return this;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return this.workPhone;
    }

    public Survivor workPhone(String workPhone) {
        this.setWorkPhone(workPhone);
        return this;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getCellPhone() {
        return this.cellPhone;
    }

    public Survivor cellPhone(String cellPhone) {
        this.setCellPhone(cellPhone);
        return this;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public Survivor emailAddress(String emailAddress) {
        this.setEmailAddress(emailAddress);
        return this;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getNameOfPlaceOfEmployment() {
        return this.nameOfPlaceOfEmployment;
    }

    public Survivor nameOfPlaceOfEmployment(String nameOfPlaceOfEmployment) {
        this.setNameOfPlaceOfEmployment(nameOfPlaceOfEmployment);
        return this;
    }

    public void setNameOfPlaceOfEmployment(String nameOfPlaceOfEmployment) {
        this.nameOfPlaceOfEmployment = nameOfPlaceOfEmployment;
    }

    public String getEmployerAddress() {
        return this.employerAddress;
    }

    public Survivor employerAddress(String employerAddress) {
        this.setEmployerAddress(employerAddress);
        return this;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getEmployerEmail() {
        return this.employerEmail;
    }

    public Survivor employerEmail(String employerEmail) {
        this.setEmployerEmail(employerEmail);
        return this;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getDateOfEmployment() {
        return this.dateOfEmployment;
    }

    public Survivor dateOfEmployment(String dateOfEmployment) {
        this.setDateOfEmployment(dateOfEmployment);
        return this;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public String getReasonForNonEmployment() {
        return this.reasonForNonEmployment;
    }

    public Survivor reasonForNonEmployment(String reasonForNonEmployment) {
        this.setReasonForNonEmployment(reasonForNonEmployment);
        return this;
    }

    public void setReasonForNonEmployment(String reasonForNonEmployment) {
        this.reasonForNonEmployment = reasonForNonEmployment;
    }

    public String getDateOfApplication() {
        return this.dateOfApplication;
    }

    public Survivor dateOfApplication(String dateOfApplication) {
        this.setDateOfApplication(dateOfApplication);
        return this;
    }

    public void setDateOfApplication(String dateOfApplication) {
        this.dateOfApplication = dateOfApplication;
    }

    public String getApplicationFee() {
        return this.applicationFee;
    }

    public Survivor applicationFee(String applicationFee) {
        this.setApplicationFee(applicationFee);
        return this;
    }

    public void setApplicationFee(String applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getStatus() {
        return this.status;
    }

    public Survivor status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReasonNotApproved() {
        return this.reasonNotApproved;
    }

    public Survivor reasonNotApproved(String reasonNotApproved) {
        this.setReasonNotApproved(reasonNotApproved);
        return this;
    }

    public void setReasonNotApproved(String reasonNotApproved) {
        this.reasonNotApproved = reasonNotApproved;
    }

    public Set<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Set<Resource> resources) {
        if (this.resources != null) {
            this.resources.forEach(i -> i.setSurvivor(null));
        }
        if (resources != null) {
            resources.forEach(i -> i.setSurvivor(this));
        }
        this.resources = resources;
    }

    public Survivor resources(Set<Resource> resources) {
        this.setResources(resources);
        return this;
    }

    public Survivor addResource(Resource resource) {
        this.resources.add(resource);
        resource.setSurvivor(this);
        return this;
    }

    public Survivor removeResource(Resource resource) {
        this.resources.remove(resource);
        resource.setSurvivor(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Survivor)) {
            return false;
        }
        return getId() != null && getId().equals(((Survivor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Survivor{" +
            "id=" + getId() +
            ", registrationNumber='" + getRegistrationNumber() + "'" +
            ", title='" + getTitle() + "'" +
            ", surname='" + getSurname() + "'" +
            ", forenames='" + getForenames() + "'" +
            ", previousSurname='" + getPreviousSurname() + "'" +
            ", dob='" + getDob() + "'" +
            ", gender='" + getGender() + "'" +
            ", placeOfBirthTown='" + getPlaceOfBirthTown() + "'" +
            ", placeOfBirthCountry='" + getPlaceOfBirthCountry() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", residentialAddress1='" + getResidentialAddress1() + "'" +
            ", residentialAddress2='" + getResidentialAddress2() + "'" +
            ", residentialAddress3='" + getResidentialAddress3() + "'" +
            ", homePhone='" + getHomePhone() + "'" +
            ", workPhone='" + getWorkPhone() + "'" +
            ", cellPhone='" + getCellPhone() + "'" +
            ", emailAddress='" + getEmailAddress() + "'" +
            ", nameOfPlaceOfEmployment='" + getNameOfPlaceOfEmployment() + "'" +
            ", employerAddress='" + getEmployerAddress() + "'" +
            ", employerEmail='" + getEmployerEmail() + "'" +
            ", dateOfEmployment='" + getDateOfEmployment() + "'" +
            ", reasonForNonEmployment='" + getReasonForNonEmployment() + "'" +
            ", dateOfApplication='" + getDateOfApplication() + "'" +
            ", applicationFee='" + getApplicationFee() + "'" +
            ", status='" + getStatus() + "'" +
            ", reasonNotApproved='" + getReasonNotApproved() + "'" +
            "}";
    }
}
