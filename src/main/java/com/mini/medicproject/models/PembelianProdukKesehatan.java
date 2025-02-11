package com.mini.medicproject.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_medical_item_purchase")
public class PembelianProdukKesehatan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id", nullable = false)
	private Long Id;
	
	@Column(name = "customer_id")
	private Long CustomerId;
	
	@Column(name = "payment_method_id")
	private Long PaymentMethodId;
	
	@Column(name = "created_by", nullable = false, updatable = false)
	private Long CreatedBy;
	
	@Column(name = "created_on", nullable = false, updatable = false)
	private Timestamp CreatedOn;
	
	@Column(name = "modified_by")
	private Long ModifiedBy;
	
	@Column(name = "modified_on")
	private Timestamp ModifiedOn;
	
	@Column(name = "deleted_by")
	private Long DeletedBy;
	
	@Column(name = "deleted_on")
	private Timestamp DeletedOn;
	
	@Column(name = "is_delete", nullable = false)
	private Boolean IsDelete;

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

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	public Long getPaymentMethodId() {
		return PaymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		PaymentMethodId = paymentMethodId;
	}

}
