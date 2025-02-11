package com.mini.medicproject.repositories;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.CaraPembayaran;


@Repository
public interface CaraPembayaranRepo extends JpaRepository<CaraPembayaran, Long>{
	
	@Query(value = "SELECT * FROM cara_pembayaran cp WHERE lower (cp.nama) LIKE %?1% AND is_delete != true",
			nativeQuery = true)
	List<CaraPembayaran> caripembayaran(String Nama);
	
	@Query(value = "SELECT * FROM cara_pembayaran cp WHERE cp.is_delete = false",
			nativeQuery = true)
	List<CaraPembayaran> tampilcarapembayaran();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE cara_pembayaran SET is_delete = true, deleted_by = ?1, deleted_on = ?2 WHERE id = ?3",
	nativeQuery = true)
	void deletIs(Long sesis, Timestamp time, Long id);
	
	@Query(value = "SELECT COUNT (cp.nama) FROM cara_pembayaran cp WHERE lower (cp.nama) = ?1 AND is_delete != true",
			nativeQuery = true)
	Long carinama(String Nama2);
	
}
