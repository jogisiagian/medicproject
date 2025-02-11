package com.mini.medicproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.MedicalFacility;

@Repository
public interface MedicalFacilityRepo extends JpaRepository<MedicalFacility, Long> {

}
