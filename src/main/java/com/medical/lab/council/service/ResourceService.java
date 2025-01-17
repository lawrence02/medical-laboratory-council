package com.medical.lab.council.service;

import com.medical.lab.council.domain.Resource;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.medical.lab.council.domain.Resource}.
 */
public interface ResourceService {
    /**
     * Save a resource.
     *
     * @param resource the entity to save.
     * @return the persisted entity.
     */
    Resource save(Resource resource);

    /**
     * Updates a resource.
     *
     * @param resource the entity to update.
     * @return the persisted entity.
     */
    Resource update(Resource resource);

    /**
     * Partially updates a resource.
     *
     * @param resource the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Resource> partialUpdate(Resource resource);

    /**
     * Get all the resources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Resource> findAll(Pageable pageable);

    /**
     * Get the "id" resource.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Resource> findOne(Long id);

    /**
     * Delete the "id" resource.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
