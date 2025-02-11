package com.mini.medicproject.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.DoctorOfficeTreatment;

@Repository
public interface DoctorOfficeTreatmentRepo extends JpaRepository<DoctorOfficeTreatment, Long>{

	@Query(value="select b.fullname "
			+ " from m_biodata b"
			+ " where lower (b.fullname) LIKE %?1%", nativeQuery = true)
	List<Map<String, Object>> finddoctor(String fullname);
}
