package com.mini.medicproject.models;

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
import org.springframework.lang.Nullable;

@Entity
@Table(name = "m_medical_facility")
public class MedicalFacility {
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Nullable
	@Column(name="name", length = 50)
	private String Name;
	
	@Nullable
	@Column(name="medical_facility_category_id")
	private Long MedicalFacilityCategoryId;
	
	@ManyToOne
	@JoinColumn(name="location_id", insertable = false, updatable = false)
	public Location m_location;
	
	@Nullable
	@Column(name="location_id")
	private Long LocationId;
	
	@Nullable
	@Column(name="full_address")
	private String FullAddress;
	
	@Nullable
	@Column(name="email", length = 100)
	private String Email;
	
	@Nullable
	@Column(name="phone_code", length = 10)
	private String PhoneCode;
	
	@Nullable
	@Column(name="phone", length = 15)
	private String Phone;
	
	@Nullable
	@Column(name="fax", length = 15)
	private String Fax;
	
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

	public Long getMedicalFacilityCategoryId() {
		return MedicalFacilityCategoryId;
	}

	public void setMedicalFacilityCategoryId(Long medicalFacilityCategoryId) {
		MedicalFacilityCategoryId = medicalFacilityCategoryId;
	}

	public Long getLocationId() {
		return LocationId;
	}

	public void setLocationId(Long locationId) {
		LocationId = locationId;
	}

	public String getFullAddress() {
		return FullAddress;
	}

	public void setFullAddress(String fullAddress) {
		FullAddress = fullAddress;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneCode() {
		return PhoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		PhoneCode = phoneCode;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getFax() {
		return Fax;
	}

	public void setFax(String fax) {
		Fax = fax;
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
