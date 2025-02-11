package com.mini.medicproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.DoctorOffice;

@Repository
public interface DoctorOfficeRepo extends JpaRepository<DoctorOffice, Long> {

}
