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
                if (survivor.getSurvivorId() != null) {
                    existingSurvivor.setSurvivorId(survivor.getSurvivorId());
                }
                if (survivor.getName() != null) {
                    existingSurvivor.setName(survivor.getName());
                }
                if (survivor.getAge() != null) {
                    existingSurvivor.setAge(survivor.getAge());
                }
                if (survivor.getGender() != null) {
                    existingSurvivor.setGender(survivor.getGender());
                }
                if (survivor.getLatitude() != null) {
                    existingSurvivor.setLatitude(survivor.getLatitude());
                }
                if (survivor.getLongitude() != null) {
                    existingSurvivor.setLongitude(survivor.getLongitude());
                }
                if (survivor.getStatus() != null) {
                    existingSurvivor.setStatus(survivor.getStatus());
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
