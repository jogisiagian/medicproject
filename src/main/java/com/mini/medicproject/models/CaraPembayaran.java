package com.mini.medicproject.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "cara_pembayaran")

public class CaraPembayaran {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "Id", nullable = false)
	private Long Id;
	
	@Column(name = "Nama", length = 50, nullable = true)
	private String Nama;

	@Column(name = "Created_By", nullable = true, updatable = false)
	private Long Created_by;
	
	@Column(name = "Created_On", nullable = true, updatable = false)
	private Timestamp Created_on;
	
	@Column(name = "Modified_By", nullable = true)
	private Long Modified_by;
	
	@Column(name = "Modified_On", nullable = true)
	private Timestamp Modified_on;
	
	@Column(name = "Deleted_By", nullable = true)
	private Long Deleted_by;
	
	@Column(name = "Deleted_On", nullable = true)
	private Timestamp Deleted_on;
	
	@Column(name = "Is_Delete", nullable = true)
	private Boolean Is_delete;
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}
	
	public String getNama() {
		return Nama;
	}

	public void setNama(String nama) {
		Nama = nama;
	}

	public Long getCreated_by() {
		return Created_by;
	}

	public void setCreated_by(Long created_by) {
		Created_by = created_by;
	}

	public Date getCreated_on() {
		return Created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		Created_on = created_on;
	}

	public Long getModified_by() {
		return Modified_by;
	}

	public void setModified_by(Long modified_by) {
		Modified_by = modified_by;
	}

	public Date getModified_on() {
		return Modified_on;
	}

	public void setModified_on(Timestamp modified_on) {
		Modified_on = modified_on;
	}

	public Long getDeleted_by() {
		return Deleted_by;
	}

	public void setDeleted_by(Long deleted_by) {
		Deleted_by = deleted_by;
	}

	public Date getDeleted_on() {
		return Deleted_on;
	}

	public void setDeleted_on(Timestamp deleted_on) {
		Deleted_on = deleted_on;
	}

	public Boolean getIs_delete() {
		return Is_delete;
	}

	public void setIs_delete(Boolean is_delete) {
		Is_delete = is_delete;
	}
	
	
	
	

}
