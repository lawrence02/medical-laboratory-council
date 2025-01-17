package com.medical.lab.council.service.impl;

import com.medical.lab.council.domain.Survivor;
import com.medical.lab.council.repository.SurvivorRepository;
import com.medical.lab.council.service.SurvivorService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.medical.lab.council.domain.Survivor}.
 */
@Service
@Transactional
public class SurvivorServiceImpl implements SurvivorService {

    private static final Logger LOG = LoggerFactory.getLogger(SurvivorServiceImpl.class);

    private final SurvivorRepository survivorRepository;

    public SurvivorServiceImpl(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    @Override
    public Survivor save(Survivor survivor) {
        LOG.debug("Request to save Survivor : {}", survivor);
        return survivorRepository.save(survivor);
    }

    @Override
    public Survivor update(Survivor survivor) {
        LOG.debug("Request to update Survivor : {}", survivor);
        return survivorRepository.save(survivor);
    }

    @Override
    public Optional<Survivor> partialUpdate(Survivor survivor) {
        LOG.debug("Request to partially update Survivor : {}", survivor);

        return survivorRepository
            .findById(survivor.getId())
            .map(existingSurvivor -> {
                if (survivor.getPractitionerType() != null) {
                    existingSurvivor.setPractitionerType(survivor.getPractitionerType());
                }
                if (survivor.getRegistrationNumber() != null) {
                    existingSurvivor.setRegistrationNumber(survivor.getRegistrationNumber());
                }
                if (survivor.getTitle() != null) {
                    existingSurvivor.setTitle(survivor.getTitle());
                }
                if (survivor.getSurname() != null) {
                    existingSurvivor.setSurname(survivor.getSurname());
                }
                if (survivor.getForenames() != null) {
                    existingSurvivor.setForenames(survivor.getForenames());
                }
                if (survivor.getPreviousSurname() != null) {
                    existingSurvivor.setPreviousSurname(survivor.getPreviousSurname());
                }
                if (survivor.getDob() != null) {
                    existingSurvivor.setDob(survivor.getDob());
                }
                if (survivor.getGender() != null) {
                    existingSurvivor.setGender(survivor.getGender());
                }
                if (survivor.getPlaceOfBirthTown() != null) {
                    existingSurvivor.setPlaceOfBirthTown(survivor.getPlaceOfBirthTown());
                }
                if (survivor.getPlaceOfBirthCountry() != null) {
                    existingSurvivor.setPlaceOfBirthCountry(survivor.getPlaceOfBirthCountry());
                }
                if (survivor.getNationality() != null) {
                    existingSurvivor.setNationality(survivor.getNationality());
                }
                if (survivor.getNationalId() != null) {
                    existingSurvivor.setNationalId(survivor.getNationalId());
                }
                if (survivor.getMaritalStatus() != null) {
                    existingSurvivor.setMaritalStatus(survivor.getMaritalStatus());
                }
                if (survivor.getResidentialAddress1() != null) {
                    existingSurvivor.setResidentialAddress1(survivor.getResidentialAddress1());
                }
                if (survivor.getResidentialAddress2() != null) {
                    existingSurvivor.setResidentialAddress2(survivor.getResidentialAddress2());
                }
                if (survivor.getResidentialAddress3() != null) {
                    existingSurvivor.setResidentialAddress3(survivor.getResidentialAddress3());
                }
                if (survivor.getHomePhone() != null) {
                    existingSurvivor.setHomePhone(survivor.getHomePhone());
                }
                if (survivor.getWorkPhone() != null) {
                    existingSurvivor.setWorkPhone(survivor.getWorkPhone());
                }
                if (survivor.getCellPhone() != null) {
                    existingSurvivor.setCellPhone(survivor.getCellPhone());
                }
                if (survivor.getEmailAddress() != null) {
                    existingSurvivor.setEmailAddress(survivor.getEmailAddress());
                }
                if (survivor.getNameOfPlaceOfEmployment() != null) {
                    existingSurvivor.setNameOfPlaceOfEmployment(survivor.getNameOfPlaceOfEmployment());
                }
                if (survivor.getEmployerAddress() != null) {
                    existingSurvivor.setEmployerAddress(survivor.getEmployerAddress());
                }
                if (survivor.getEmployerEmail() != null) {
                    existingSurvivor.setEmployerEmail(survivor.getEmployerEmail());
                }
                if (survivor.getDateOfEmployment() != null) {
                    existingSurvivor.setDateOfEmployment(survivor.getDateOfEmployment());
                }
                if (survivor.getAreaOfEmployment() != null) {
                    existingSurvivor.setAreaOfEmployment(survivor.getAreaOfEmployment());
                }
                if (survivor.getEmploymentStatus() != null) {
                    existingSurvivor.setEmploymentStatus(survivor.getEmploymentStatus());
                }
                if (survivor.getTypeOfInstitution() != null) {
                    existingSurvivor.setTypeOfInstitution(survivor.getTypeOfInstitution());
                }
                if (survivor.getProvinceEmployed() != null) {
                    existingSurvivor.setProvinceEmployed(survivor.getProvinceEmployed());
                }
                if (survivor.getReasonForNonEmployment() != null) {
                    existingSurvivor.setReasonForNonEmployment(survivor.getReasonForNonEmployment());
                }
                if (survivor.getDateOfApplication() != null) {
                    existingSurvivor.setDateOfApplication(survivor.getDateOfApplication());
                }
                if (survivor.getApplicationFee() != null) {
                    existingSurvivor.setApplicationFee(survivor.getApplicationFee());
                }
                if (survivor.getStatus() != null) {
                    existingSurvivor.setStatus(survivor.getStatus());
                }
                if (survivor.getReasonNotApproved() != null) {
                    existingSurvivor.setReasonNotApproved(survivor.getReasonNotApproved());
                }

                return existingSurvivor;
            })
            .map(survivorRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Survivor> findAll(Pageable pageable) {
        LOG.debug("Request to get all Survivors");
        return survivorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Survivor> findOne(Long id) {
        LOG.debug("Request to get Survivor : {}", id);
        return survivorRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Survivor : {}", id);
        survivorRepository.deleteById(id);
    }
}
