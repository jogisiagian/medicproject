package com.mini.medicproject.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.Medical_Item_Category;

@Repository
public interface Medical_Item_CategoryRepo extends JpaRepository<Medical_Item_Category, Long> {

	@Query(value = "SELECT * FROM m_medical_item_category mc WHERE mc.is_delete = false",
			nativeQuery = true)
	List<Medical_Item_Category> tampilKatKes();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_medical_item_category SET is_delete = true, deleted_by = ?1, deleted_on = ?2 "
			+ "WHERE id = ?3",
			nativeQuery = true)
	void deletProkes(Long sesis, Timestamp time, Long id);
	
	@Query(value = "SELECT * FROM m_medical_item_category mc WHERE LOWER(mc.Name) LIKE %?1% AND mc.is_delete != true",
			nativeQuery = true)
	List<Medical_Item_Category> cariKatProKes(String name);
	
	@Query(value="SELECT COUNT(mc.Name) FROM m_medical_item_category mc WHERE LOWER(mc.Name) = ?1 AND mc.is_delete != true",
			nativeQuery = true)
	Long cekData(String name);
	
}
