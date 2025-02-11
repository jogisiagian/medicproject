package com.mini.medicproject.repositories;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mini.medicproject.models.CustomerMember;

public interface CustomerMemberRepo extends JpaRepository<CustomerMember, Long> {

	@Query(value="SELECT MAX(Id) from m_customer",
			nativeQuery = true)
	Long lastCustomerId();
	
	@Query(value = "SELECT cm.id, c.biodata_id, b.fullname, c.dob, cr.name "
			+ "FROM m_biodata b INNER JOIN m_customer c "
			+ "ON b.id = c.biodata_id INNER JOIN m_customer_member cm "
			+ "ON c.id = cm.customer_id INNER JOIN m_customer_relation cr "
			+ "ON cm.customer_relation_id = cr.id "
			+ "where cm.parent_biodata_id = ?1 AND cm.is_delete != true",
			nativeQuery = true)
	List<Map<String, Object>> getPasienById(Long uid);
	
	@Query(value = "SELECT cm.id, c.biodata_id, b.fullname, c.dob, cr.name, COALESCE(qr.janji,0) janjian, COALESCE(qy.chat,0) chatan "
			+ "FROM m_biodata b INNER JOIN m_customer c "
			+ "ON b.id = c.biodata_id INNER JOIN m_customer_member cm "
			+ "ON c.id = cm.customer_id INNER JOIN m_customer_relation cr "
			+ "ON cm.customer_relation_id = cr.id LEFT JOIN (SELECT c.id, count(ta.customer_id) janji "
			+ "FROM m_customer c INNER JOIN t_appointment ta "
			+ "ON c.id = ta.customer_id "
			+ "WHERE c.created_by = ?1 AND c.is_deleted != true "
			+ "GROUP BY c.id) qr "
			+ "ON c.id = qr.id LEFT JOIN (SELECT c.id, count(cc.customer_id) chat "
			+ "FROM m_customer c JOIN t_customer_chat cc "
			+ "ON c.id = cc.customer_id "
			+ "WHERE c.created_by = ?1 AND c.is_deleted != true "
			+ "GROUP BY c.id) qy "
			+ "ON c.id = qy.id "
			+ "WHERE cm.parent_biodata_id = ?1 AND cm.is_delete != true",
			nativeQuery = true)
	List<Map<String, Object>> getPasienByKuy(Long uid);
	
	@Query(value = "SELECT c.biodata_id FROM m_customer c WHERE id = ?1",
			nativeQuery = true)
	Long idBio(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_customer_member SET is_delete = true, deleted_by = ?1, deleted_on = ?2 "
			+ "WHERE id = ?3",
			nativeQuery = true)
	void deletPasMem(Long sesis, Timestamp time, Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_customer SET is_deleted = true, deleted_by = ?1, deleted_on = ?2 "
			+ "WHERE id = ?3",
			nativeQuery = true)
	void deletPasCus(Long sesis, Timestamp time, Long id);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE m_biodata SET is_deleted = true, deleted_by = ?1, deleted_on = ?2 "
			+ "WHERE id = ?3",
			nativeQuery = true)
	void deletPasBio(Long sesis, Timestamp time, Long id);
	
	@Query(value = "SELECT c.id, b.fullname "
			+ "FROM m_biodata b INNER JOIN m_customer c "
			+ "ON b.id = c.biodata_id "
			+ "WHERE c.id IN (?1) AND c.is_deleted != true",
			nativeQuery = true)
	List<Map<String, Object>> getPasienByList(Long id);
	
	@Query(value = "SELECT cm.id, c.biodata_id, b.fullname, c.dob, cr.name, COALESCE(qr.janji,0) janjian, COALESCE(qy.chat,0) chatan "
			+ "FROM m_biodata b INNER JOIN m_customer c "
			+ "ON b.id = c.biodata_id INNER JOIN m_customer_member cm "
			+ "ON c.id = cm.customer_id INNER JOIN m_customer_relation cr "
			+ "ON cm.customer_relation_id = cr.id LEFT JOIN (SELECT c.id, count(ta.customer_id) janji "
			+ "FROM m_customer c INNER JOIN t_appointment ta "
			+ "ON c.id = ta.customer_id "
			+ "WHERE c.created_by = ?1 AND c.is_deleted != true "
			+ "GROUP BY c.id) qr "
			+ "ON c.id = qr.id LEFT JOIN (SELECT c.id, count(cc.customer_id) chat "
			+ "FROM m_customer c JOIN t_customer_chat cc "
			+ "ON c.id = cc.customer_id "
			+ "WHERE c.created_by = ?1 AND c.is_deleted != true "
			+ "GROUP BY c.id) qy "
			+ "ON c.id = qy.id "
			+ "WHERE cm.parent_biodata_id = ?1 AND cm.is_delete != true",
			nativeQuery = true)
	Page<Map<String, Object>> getPasienByParent(Long uid, Pageable paging);
}
