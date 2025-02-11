package com.mini.medicproject.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.DoctorTreatment;

@Repository
public interface DoctorTreatmentRepo extends JpaRepository<DoctorTreatment, Long>{
	
	@Query(value="select distinct dt.name from t_doctor_treatment dt", nativeQuery = true)
	List<Map<String, Object>> getTindakan();
	
	@Query(value="select b.fullname, dof.specialization, mf.name as \"rs\", lc.name \r\n"
			+ "from m_biodata b\r\n"
			+ "join m_doctor d\r\n"
			+ "on b.id = d.biodata_id\r\n"
			+ "join t_doctor_office dof\r\n"
			+ "on d.id = dof.doctor_id\r\n"
			+ "join m_medical_facility mf\r\n"
			+ "on dof.medical_facility_id = mf.id join m_location lc on mf.location_id = lc.id", nativeQuery = true)
	List<Map<String, Object>> getDoctor();
	
	@Query(value="select b.fullname, dof.specialization, mf.name as \"rs\", lc.name, dt.name as \"tindakan\" \r\n"
			+ "from m_biodata b\r\n"
			+ "join m_doctor d\r\n"
			+ "on b.id = d.biodata_id\r\n"
			+ "join t_doctor_office dof\r\n"
			+ "on d.id = dof.doctor_id\r\n"
			+ "join m_medical_facility mf\r\n"
			+ "on dof.medical_facility_id = mf.id join m_location lc on mf.location_id = lc.id join t_doctor_treatment dt on d.id = dt.doctor_id"
			+ " WHERE LOWER(lc.name) LIKE %?1% AND LOWER(b.fullname) LIKE %?2% AND LOWER(dof.specialization) LIKE %?3% AND LOWER(dt.name) LIKE %?4% ", nativeQuery = true)
	List<Map<String, Object>> cariDoctor(String location, String fullname, String specialization, String treatment);
}
