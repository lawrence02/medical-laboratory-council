package com.medical.lab.council.web.rest;

import com.medical.lab.council.domain.Resource;
import com.medical.lab.council.repository.ResourceRepository;
import com.medical.lab.council.service.ResourceService;
import com.medical.lab.council.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.medical.lab.council.domain.Resource}.
 */
@RestController
@RequestMapping("/api/resources")
public class ResourceResource {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceResource.class);

    private static final String ENTITY_NAME = "resource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResourceService resourceService;

    private final ResourceRepository resourceRepository;

    public ResourceResource(ResourceService resourceService, ResourceRepository resourceRepository) {
        this.resourceService = resourceService;
        this.resourceRepository = resourceRepository;
    }

    /**
     * {@code POST  /resources} : Create a new resource.
     *
     * @param resource the resource to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new resource, or with status {@code 400 (Bad Request)} if the resource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) throws URISyntaxException {
        LOG.debug("REST request to save Resource : {}", resource);
        if (resource.getId() != null) {
            throw new BadRequestAlertException("A new resource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        resource = resourceService.save(resource);
        return ResponseEntity.created(new URI("/api/resources/" + resource.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, resource.getId().toString()))
            .body(resource);
    }

    /**
     * {@code PUT  /resources/:id} : Updates an existing resource.
     *
     * @param id the id of the resource to save.
     * @param resource the resource to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resource,
     * or with status {@code 400 (Bad Request)} if the resource is not valid,
     * or with status {@code 500 (Internal Server Error)} if the resource couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Resource resource
    ) throws URISyntaxException {
        LOG.debug("REST request to update Resource : {}, {}", id, resource);
        if (resource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resource.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        resource = resourceService.update(resource);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resource.getId().toString()))
            .body(resource);
    }

    /**
     * {@code PATCH  /resources/:id} : Partial updates given fields of an existing resource, field will ignore if it is null
     *
     * @param id the id of the resource to save.
     * @param resource the resource to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated resource,
     * or with status {@code 400 (Bad Request)} if the resource is not valid,
     * or with status {@code 404 (Not Found)} if the resource is not found,
     * or with status {@code 500 (Internal Server Error)} if the resource couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Resource> partialUpdateResource(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Resource resource
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Resource partially : {}, {}", id, resource);
        if (resource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, resource.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!resourceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Resource> result = resourceService.partialUpdate(resource);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, resource.getId().toString())
        );
    }

    /**
     * {@code GET  /resources} : get all the resources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resources in body.
     */
    @GetMapping("")
    public List<Resource> getAllResources() {
        LOG.debug("REST request to get all Resources");
        return resourceService.findAll();
    }

    /**
     * {@code GET  /resources/:id} : get the "id" resource.
     *
     * @param id the id of the resource to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resource, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResource(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Resource : {}", id);
        Optional<Resource> resource = resourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resource);
    }

    /**
     * {@code DELETE  /resources/:id} : delete the "id" resource.
     *
     * @param id the id of the resource to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Resource : {}", id);
        resourceService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
