package com.mini.medicproject.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="m_customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name="id")
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="biodata_id", insertable=false, updatable=false)
	public UserProfil userprofil;

	@Column(name="biodata_id")
	private Long BiodataId;
	
	@Column(name="dob")
	private Date Dob;
	
	@Column(name="gender", length=1)
	private String Gender;
	
	@ManyToOne
	@JoinColumn(name="blood_group_id", insertable=false, updatable=false)
	public GolDarah goldarah;
	
	@Column(name="blood_group_id")
	private Long BlodGroupId;
	
	@Column(name="rhesus_type", length=5)
	private String RhesusType;
	
	@Column(name="height")
	private Double Height;
	
	@Column(name="weight")
	private Double Weight;
	
	@NonNull
	@Column(name = "created_by", nullable = true, updatable = false)
	private Long CreatedBy;
	
	@NonNull
	@Column(name = "created_on", nullable = true, updatable = false)
	private Timestamp CreatedOn;
	
	@Column(name = "modified_by")
	private Long ModifiedBy;
	
	@Column(name = "modified_on")
	private Timestamp ModifiedOn;
	
	@Column(name = "deleted_by")
	private Long DeletedBy;
	
	@Column(name = "deleted_on")
	private Timestamp DeletedOn;
	
	@NonNull
	@Column(name = "is_deleted")
	private Boolean IsDeleted;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public UserProfil getUserprofil() {
		return userprofil;
	}

	public void setUserprofil(UserProfil userprofil) {
		this.userprofil = userprofil;
	}

	public Long getBiodataId() {
		return BiodataId;
	}

	public void setBiodataId(Long biodataId) {
		BiodataId = biodataId;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public GolDarah getGoldarah() {
		return goldarah;
	}

	public void setGoldarah(GolDarah goldarah) {
		this.goldarah = goldarah;
	}

	public Long getBlodGroupId() {
		return BlodGroupId;
	}

	public void setBlodGroupId(Long blodGroupId) {
		BlodGroupId = blodGroupId;
	}

	public String getRhesusType() {
		return RhesusType;
	}

	public void setRhesusType(String rhesusType) {
		RhesusType = rhesusType;
	}

	public Double getHeight() {
		return Height;
	}

	public void setHeight(Double height) {
		Height = height;
	}

	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double weight) {
		Weight = weight;
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

	public Boolean getIsDeleted() {
		return IsDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		IsDeleted = isDeleted;
	}

}
