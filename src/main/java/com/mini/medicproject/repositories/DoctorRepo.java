package com.mini.medicproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
