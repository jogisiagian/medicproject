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
@Table(name = "m_user")
public class User {

	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Nullable
	@Column(name="biodata_id")
	private Long BiodataId;
	
	@Nullable
	@Column(name="role_id")
	private Long RoleId;
	
	@Nullable
	@Column(name="email", length=100)
	private String Email;
	
	@Nullable
	@Column(name="password", length=255)
	private String Password;
	
	@Nullable
	@Column(name="login_attempt")
	private Integer LoginAttempt;
	
	@Nullable
	@Column(name="is_locked")
	private Boolean IsLocked;
	
	@Nullable
	@Column(name="last_login")
	private Timestamp LastLogin;
	
	@NonNull
	@Column(name = "created_by", updatable = false)
	private Long CreatedBy;
	
	@NonNull
	@Column(name = "created_on", updatable = false)
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
	private Boolean IsDelete;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getBiodataId() {
		return BiodataId;
	}

	public void setBiodataId(Long biodataId) {
		BiodataId = biodataId;
	}

	public Long getRoleId() {
		return RoleId;
	}

	public void setRoleId(Long roleId) {
		RoleId = roleId;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getLoginAttempt() {
		return LoginAttempt;
	}

	public void setLoginAttempt(Integer loginAttempt) {
		LoginAttempt = loginAttempt;
	}

	public Boolean getIsLocked() {
		return IsLocked;
	}

	public void setIsLocked(Boolean isLocked) {
		IsLocked = isLocked;
	}

	public Timestamp getLastLogin() {
		return LastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		LastLogin = lastLogin;
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

	public Boolean getIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		IsDelete = isDelete;
	}
	
}
