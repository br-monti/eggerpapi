package com.eggerp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eggerp.domain.model.ShedManufacturer;

@Repository
public interface ShedManufacturerRepository extends JpaRepository<ShedManufacturer, Long> {

}