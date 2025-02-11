package com.mini.medicproject.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.User;
import com.mini.medicproject.models.UserProfil;

@Repository
public interface UserProfilRepo extends JpaRepository<UserProfil, Long> {
	
	@Query(value="SELECT * FROM m_user WHERE m_user.email LIKE %?% AND m_user.password LIKE %?%",
			nativeQuery = true)
	List<User> cariuser(String email, String password);
	
	
}


