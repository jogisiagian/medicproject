package com.mini.medicproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

	@Query(value = "SELECT * FROM m_location l WHERE l.is_delete = false",
			nativeQuery = true)
	List<Location> tampillocation();
	
}
