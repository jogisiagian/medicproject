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

@Entity
@Table(name = "m_customer_member")
public class CustomerMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="parent_biodata_id", insertable=false, updatable=false)
	public UserProfil userprofil;
	
	@Column(name = "parent_biodata_id", nullable = true)
	private Long ParentBiodataId;
	
	@ManyToOne
	@JoinColumn(name="customer_id", insertable=false, updatable=false)
	public Customer customer;
	
	@Column(name = "customer_id", nullable = true)
	private Long CustomerId;
	
	@ManyToOne
	@JoinColumn(name="customer_relation_id", insertable=false, updatable=false)
	public HubunganPasien hubpasien;
	
	@Column(name = "customer_relation_id", nullable = true)
	private Long CustomerRelationId;
	
	@Column(name = "created_by", nullable = true, updatable = false)
	private Long CreatedBy;
	
	@Column(name = "created_on", nullable = true, updatable = false)
	private Timestamp CreatedOn;
	
	@Column(name = "modified_by", nullable = true)
	private Long ModifiedBy;
	
	@Column(name = "modified_on", nullable = true)
	private Timestamp ModifiedOn;
	
	@Column(name = "deleted_by", nullable = true)
	private Long DeletedBy;
	
	@Column(name = "deleted_on", nullable = true)
	private Timestamp DeletedOn;
	
	@Column(name = "is_delete", nullable = true)
	private Boolean IsDelete;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getParentBiodataId() {
		return ParentBiodataId;
	}

	public void setParentBiodataId(Long parentBiodataId) {
		ParentBiodataId = parentBiodataId;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	public Long getCustomerRelationId() {
		return CustomerRelationId;
	}

	public void setCustomerRelationId(Long customerRelationId) {
		CustomerRelationId = customerRelationId;
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

	public UserProfil getUserprofil() {
		return userprofil;
	}

	public void setUserprofil(UserProfil userprofil) {
		this.userprofil = userprofil;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public HubunganPasien getHubpasien() {
		return hubpasien;
	}

	public void setHubpasien(HubunganPasien hubpasien) {
		this.hubpasien = hubpasien;
	}
	
}
