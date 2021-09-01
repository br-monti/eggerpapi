package com.eggerp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.eggerp.domain.model.ChickenLineage;

@Repository
public interface ChickenLineageRepository extends JpaRepository<ChickenLineage, Long>, JpaSpecificationExecutor<ChickenLineage> {

}