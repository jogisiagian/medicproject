package com.mini.medicproject.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.GolDarah;

@Repository
public interface GolDarahRepo extends JpaRepository<GolDarah, Long> {
	
	@Query(value = "SELECT * FROM m_blood_group bg WHERE lower (bg.code) LIKE %?1% AND bg.is_delete = false",
			nativeQuery = true)
	List<GolDarah> caridarah(String Code);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_blood_group SET is_delete = true, deleted_by = ?1, deleted_on = ?2 WHERE id = ?3", nativeQuery = true)
	void delete(Long ses, Timestamp time, Long id);
	
	@Query(value = "SELECT * FROM m_blood_group bg WHERE bg.is_delete = false", nativeQuery = true)
	List<GolDarah> tampildarah();

}
