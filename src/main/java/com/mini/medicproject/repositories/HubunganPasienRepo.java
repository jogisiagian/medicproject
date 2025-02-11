package com.mini.medicproject.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.HubunganPasien;

@Repository
public interface HubunganPasienRepo extends JpaRepository<HubunganPasien, Long> {

	@Query(value = "SELECT * FROM m_customer_relation r WHERE LOWER(r.Name) LIKE %?1% AND r.is_delete != true",
			nativeQuery = true)
	List<HubunganPasien> cariHubPas(String name);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_customer_relation SET is_delete = true, deleted_by = ?1, deleted_on = ?2 "
			+ "WHERE id = ?3",
			nativeQuery = true)
	void deletIs(Long sesis, Timestamp time, Long id);
	
	@Query(value = "SELECT * FROM m_customer_relation r WHERE r.is_delete = false",
			nativeQuery = true)
	List<HubunganPasien> tampilHubpas();
	
	@Query(value="SELECT COUNT(r.name) FROM m_customer_relation r WHERE LOWER(r.Name) = ?1 AND r.is_delete != true",
			nativeQuery = true)
	Long cekData(String name);
}
