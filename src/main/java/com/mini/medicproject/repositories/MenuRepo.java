package com.mini.medicproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.Menu;


@Repository
public interface MenuRepo extends JpaRepository<Menu, Long> {

	@Query(value="SELECT * FROM menu m WHERE m.parent_id IS NULL",
			nativeQuery = true)
	List<Menu> getParentMenu();
	
	@Query(value="SELECT * FROM menu m WHERE m.parent_id = ?1", nativeQuery = true)
	List<Menu> getSubMenu(Long parent_id);
	
	@Query(value="SELECT * FROM menu m join menu_role mr on m.id = mr.menu_id WHERE role_id = ?1 ORDER BY m.id", nativeQuery = true)
	List<Menu> getRoleId(Long role_id);
}
