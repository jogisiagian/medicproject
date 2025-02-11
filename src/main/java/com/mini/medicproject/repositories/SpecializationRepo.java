package com.mini.medicproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.Specialization;

@Repository
public interface SpecializationRepo extends JpaRepository<Specialization, Long> {

}
