package com.mini.medicproject.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name="m_biodata")
public class UserProfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@NonNull
	@Column(name="id")
	private Long Id;
	
	@Column(name="fullname", length=255)
	private String Fullname;
	
	@Column(name="mobile_phone", length=15)
	private String MobilePhone;
	
	@Column(name="image", length=200)
	private String Image;
	
	@Column(name="image_path", length=255)
	public String ImagePath;
	
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

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public String getMobilePhone() {
		return MobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		MobilePhone = mobilePhone;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getImagePath() {
		return ImagePath;
	}

	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
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
