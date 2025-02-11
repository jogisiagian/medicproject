package com.mini.medicproject.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mini.medicproject.models.CaraPembayaran;
import com.mini.medicproject.models.User;

public interface LupaPasswordRepo extends JpaRepository<User, Long> {
	
	@Query(value="SELECT COUNT(u.Email) FROM m_user u Where u.Email = ?1 ",
			nativeQuery = true)
	Long cekEmail(String email);
	
	@Query(value="SELECT * FROM m_user u WHERE u.email = ?1",
			nativeQuery = true)
	User findbyemail2(String email);
	
	@Query(value="SELECT * FROM m_user u WHERE u.email = ?1",
			nativeQuery = true)
	Optional<User> findbyemail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update m_user u set u.password = ?1 where u.id = ?2",
	nativeQuery = true)
	void updatepass(String password, Long id);

}
