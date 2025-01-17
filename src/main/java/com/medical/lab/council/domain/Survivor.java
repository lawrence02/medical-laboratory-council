package com.medical.lab.council.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.medical.lab.council.domain.enumeration.Gender;
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
            ", surname='" + getSurname() + "'" +
            ", forenames='" + getForenames() + "'" +
            ", previousSurname='" + getPreviousSurname() + "'" +
            ", dob='" + getDob() + "'" +
            ", gender='" + getGender() + "'" +
            "}";
    }
}
