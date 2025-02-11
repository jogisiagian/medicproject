package com.mini.medicproject.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mini.medicproject.models.Customer;


public interface CustomerRepo  extends JpaRepository<Customer, Long>{

	@Query(value="SELECT m_customer.id, m_biodata.fullname, m_biodata.mobile_phone, m_customer.dob "
			+ "from m_biodata join m_customer on m_biodata.id = m_customer.biodata_id "
			+ "where m_customer.biodata_id = ?1",
			nativeQuery = true)
	List<Map<String, Object>> getDataCustomer(Long biodata_id);
	
	@Query(value="SELECT m_customer.id, m_biodata.fullname, m_biodata.mobile_phone, m_customer.dob "
			+ "from m_biodata join m_customer on m_biodata.id = m_customer.biodata_id "
			+ "where m_customer.biodata_id = ?1",
			nativeQuery = true)
	List<Customer> findByBiodataId(Long biodata_id);
	
	//List<Customer> findByBiodataId(Long biodataId);
	
	
}
