package com.medical.lab.council.repository;

import com.medical.lab.council.domain.Survivor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Survivor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurvivorRepository extends JpaRepository<Survivor, Long> {}
