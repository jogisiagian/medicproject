package com.mini.medicproject.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini.medicproject.models.User;


public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query(value="SELECT * FROM m_user u Where u.Email = ?1 AND u.Password = ?2",
			nativeQuery = true)
	List<User> getLogin(String email, String password);
	
	@Query(value="SELECT COUNT(u.Email) FROM m_user u Where u.Email = ?1 ",
			nativeQuery = true)
	Long cekEmail(String email);
	
	@Query(value="INSERT INTO m_biodata (Fullname, MobilePhone) VALUES ('?1','?2') ",
			nativeQuery = true)
	Long daftarBiodata(String fullname, String phone);
	
	@Query(value="SELECT Id FROM m_biodata WHERE Fullname='?1' and MobilePhone='?2'",
			nativeQuery = true)
	Long getBiodataId(String fullname, String phone);
	
	@Query(value="INSERT INTO m_user (Email,Password) VALUES ('?1', '?2')",
			nativeQuery = true)
	Long daftarUser(String email, String password);
	
	@Query(value="SELECT MAX(Id) from m_biodata",
			nativeQuery = true)
	Long lastBiodataId();
	
	@Query(value="SELECT * FROM m_user u WHERE u.biodata_id = ?1",
			nativeQuery = true)
	List<User> findByBiodataId( Long biodataid);
	
	@Query(value="SELECT b.fullname FROM m_user u \r\n"
			+ "JOIN m_biodata b \r\n"
			+ "ON u.biodata_id = b.id \r\n"
			+ "where u.biodata_id= ?1",
			nativeQuery = true)
	List<Map<String, Object>> getFullname(Long userbid);
	
	
	
	

}
