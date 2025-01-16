package com.medical.lab.council.service.impl;

import com.medical.lab.council.domain.Resource;
import com.medical.lab.council.repository.ResourceRepository;
import com.medical.lab.council.service.ResourceService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.medical.lab.council.domain.Resource}.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceServiceImpl.class);

    private final ResourceRepository resourceRepository;

    public ResourceServiceImpl(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource save(Resource resource) {
        LOG.debug("Request to save Resource : {}", resource);
        return resourceRepository.save(resource);
    }

    @Override
    public Resource update(Resource resource) {
        LOG.debug("Request to update Resource : {}", resource);
        return resourceRepository.save(resource);
    }

    @Override
    public Optional<Resource> partialUpdate(Resource resource) {
        LOG.debug("Request to partially update Resource : {}", resource);

        return resourceRepository
            .findById(resource.getId())
            .map(existingResource -> {
                if (resource.getResourceType() != null) {
                    existingResource.setResourceType(resource.getResourceType());
                }
                if (resource.getQuantity() != null) {
                    existingResource.setQuantity(resource.getQuantity());
                }

                return existingResource;
            })
            .map(resourceRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resource> findAll() {
        LOG.debug("Request to get all Resources");
        return resourceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Resource> findOne(Long id) {
        LOG.debug("Request to get Resource : {}", id);
        return resourceRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Resource : {}", id);
        resourceRepository.deleteById(id);
    }
}
