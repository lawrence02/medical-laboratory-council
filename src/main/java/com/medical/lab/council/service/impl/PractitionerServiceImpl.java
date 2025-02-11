package com.medical.lab.council.service.impl;

import com.medical.lab.council.domain.Practitioner;
import com.medical.lab.council.repository.PractitionerRepository;
import com.medical.lab.council.service.PractitionerService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.medical.lab.council.domain.Practitioner}.
 */
@Service
@Transactional
public class PractitionerServiceImpl implements PractitionerService {

    private static final Logger LOG = LoggerFactory.getLogger(PractitionerServiceImpl.class);

    private final PractitionerRepository practitionerRepository;

    public PractitionerServiceImpl(PractitionerRepository practitionerRepository) {
        this.practitionerRepository = practitionerRepository;
    }

    @Override
    public Practitioner save(Practitioner practitioner) {
        LOG.debug("Request to save Practitioner : {}", practitioner);
        return practitionerRepository.save(practitioner);
    }

    @Override
    public Practitioner update(Practitioner practitioner) {
        LOG.debug("Request to update Practitioner : {}", practitioner);
        return practitionerRepository.save(practitioner);
    }

    @Override
    public Optional<Practitioner> partialUpdate(Practitioner practitioner) {
        LOG.debug("Request to partially update Practitioner : {}", practitioner);

        return practitionerRepository
            .findById(practitioner.getId())
            .map(existingPractitioner -> {
                if (practitioner.getPractitionerType() != null) {
                    existingPractitioner.setPractitionerType(practitioner.getPractitionerType());
                }
                if (practitioner.getRegistrationNumber() != null) {
                    existingPractitioner.setRegistrationNumber(practitioner.getRegistrationNumber());
                }
                if (practitioner.getTitle() != null) {
                    existingPractitioner.setTitle(practitioner.getTitle());
                }
                if (practitioner.getSurname() != null) {
                    existingPractitioner.setSurname(practitioner.getSurname());
                }
                if (practitioner.getForenames() != null) {
                    existingPractitioner.setForenames(practitioner.getForenames());
                }
                if (practitioner.getPreviousSurname() != null) {
                    existingPractitioner.setPreviousSurname(practitioner.getPreviousSurname());
                }
                if (practitioner.getDob() != null) {
                    existingPractitioner.setDob(practitioner.getDob());
                }
                if (practitioner.getGender() != null) {
                    existingPractitioner.setGender(practitioner.getGender());
                }
                if (practitioner.getPlaceOfBirthTown() != null) {
                    existingPractitioner.setPlaceOfBirthTown(practitioner.getPlaceOfBirthTown());
                }
                if (practitioner.getPlaceOfBirthCountry() != null) {
                    existingPractitioner.setPlaceOfBirthCountry(practitioner.getPlaceOfBirthCountry());
                }
                if (practitioner.getNationality() != null) {
                    existingPractitioner.setNationality(practitioner.getNationality());
                }
                if (practitioner.getNationalId() != null) {
                    existingPractitioner.setNationalId(practitioner.getNationalId());
                }
                if (practitioner.getMaritalStatus() != null) {
                    existingPractitioner.setMaritalStatus(practitioner.getMaritalStatus());
                }
                if (practitioner.getResidentialAddress1() != null) {
                    existingPractitioner.setResidentialAddress1(practitioner.getResidentialAddress1());
                }
                if (practitioner.getResidentialAddress2() != null) {
                    existingPractitioner.setResidentialAddress2(practitioner.getResidentialAddress2());
                }
                if (practitioner.getResidentialAddress3() != null) {
                    existingPractitioner.setResidentialAddress3(practitioner.getResidentialAddress3());
                }
                if (practitioner.getHomePhone() != null) {
                    existingPractitioner.setHomePhone(practitioner.getHomePhone());
                }
                if (practitioner.getWorkPhone() != null) {
                    existingPractitioner.setWorkPhone(practitioner.getWorkPhone());
                }
                if (practitioner.getCellPhone() != null) {
                    existingPractitioner.setCellPhone(practitioner.getCellPhone());
                }
                if (practitioner.getEmailAddress() != null) {
                    existingPractitioner.setEmailAddress(practitioner.getEmailAddress());
                }
                if (practitioner.getNameOfPlaceOfEmployment() != null) {
                    existingPractitioner.setNameOfPlaceOfEmployment(practitioner.getNameOfPlaceOfEmployment());
                }
                if (practitioner.getEmployerAddress() != null) {
                    existingPractitioner.setEmployerAddress(practitioner.getEmployerAddress());
                }
                if (practitioner.getEmployerEmail() != null) {
                    existingPractitioner.setEmployerEmail(practitioner.getEmployerEmail());
                }
                if (practitioner.getDateOfEmployment() != null) {
                    existingPractitioner.setDateOfEmployment(practitioner.getDateOfEmployment());
                }
                if (practitioner.getAreaOfEmployment() != null) {
                    existingPractitioner.setAreaOfEmployment(practitioner.getAreaOfEmployment());
                }
                if (practitioner.getEmploymentStatus() != null) {
                    existingPractitioner.setEmploymentStatus(practitioner.getEmploymentStatus());
                }
                if (practitioner.getTypeOfInstitution() != null) {
                    existingPractitioner.setTypeOfInstitution(practitioner.getTypeOfInstitution());
                }
                if (practitioner.getProvinceEmployed() != null) {
                    existingPractitioner.setProvinceEmployed(practitioner.getProvinceEmployed());
                }
                if (practitioner.getReasonForNonEmployment() != null) {
                    existingPractitioner.setReasonForNonEmployment(practitioner.getReasonForNonEmployment());
                }
                if (practitioner.getDateOfApplication() != null) {
                    existingPractitioner.setDateOfApplication(practitioner.getDateOfApplication());
                }
                if (practitioner.getApplicationFee() != null) {
                    existingPractitioner.setApplicationFee(practitioner.getApplicationFee());
                }
                if (practitioner.getStatus() != null) {
                    existingPractitioner.setStatus(practitioner.getStatus());
                }
                if (practitioner.getReasonNotApproved() != null) {
                    existingPractitioner.setReasonNotApproved(practitioner.getReasonNotApproved());
                }

                return existingPractitioner;
            })
            .map(practitionerRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Practitioner> findAll(Pageable pageable) {
        LOG.debug("Request to get all Practitioners");
        return practitionerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Practitioner> findOne(Long id) {
        LOG.debug("Request to get Practitioner : {}", id);
        return practitionerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Practitioner : {}", id);
        practitionerRepository.deleteById(id);
    }
}
