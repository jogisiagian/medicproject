package com.mini.medicproject.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "m_specialization")
public class Specialization {
	
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long Id;

	@Nullable
	@Column(name="name", length = 50)
	private String Name;
	
	@NonNull
	@Column(name = "created_by")
	private Long CreatedBy;
	
	@NonNull
	@Column(name = "created_on")
	private Timestamp CreatedOn;
	
	@Nullable
	@Column(name = "modified_by")
	private Long ModifiedBy;
	
	@Nullable
	@Column(name = "modified_on")
	private Timestamp ModifiedOn;
	
	@Nullable
	@Column(name = "deleted_by")
	private Long DeletedBy;
	
	@Nullable
	@Column(name = "deleted_on")
	private Timestamp DeletedOn;
	
	@NonNull
	@Column(name = "is_delete")
	private boolean IsDelete;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Long getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(Long createdBy) {
		CreatedBy = createdBy;
	}

	public Timestamp getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		CreatedOn = createdOn;
	}

	public Long getModifiedBy() {
		return ModifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		ModifiedBy = modifiedBy;
	}

	public Timestamp getModifiedOn() {
		return ModifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		ModifiedOn = modifiedOn;
	}

	public Long getDeletedBy() {
		return DeletedBy;
	}

	public void setDeletedBy(Long deletedBy) {
		DeletedBy = deletedBy;
	}

	public Timestamp getDeletedOn() {
		return DeletedOn;
	}

	public void setDeletedOn(Timestamp deletedOn) {
		DeletedOn = deletedOn;
	}

	public boolean isIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(boolean isDelete) {
		IsDelete = isDelete;
	}
	
	
}
