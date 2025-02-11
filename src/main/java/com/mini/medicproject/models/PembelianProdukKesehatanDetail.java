package com.mini.medicproject.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_medical_item_purchase_detail")
public class PembelianProdukKesehatanDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id", nullable = false)
	private Long Id;
	
	@Column(name = "medical_item_purchase_id")
	private Long MedicalItemPurchaseId;
	
	@Column(name = "medical_item_id", nullable = false)
	private Long MedicalItemId;
	
	@Column(name = "qty", nullable = false)
	private Integer qty;
	
	@Column(name = "medical_facility_id", nullable = false)
	private Long MedicalFacilityId;
	
	@Column(name = "courir_id", nullable = false)
	private Long CourirId;
	
	@Column(name = "sub_total", nullable = false)
	private Double SubTotal;
	
	@Column(name = "created_by", nullable = true)
	private Long CreatedBy;
	
	@Column(name = "created_on", nullable = true)
	private Timestamp CreatedOn;
	
	@Column(name = "modified_by")
	private Long ModifiedBy;
	
	@Column(name = "modified_on")
	private Timestamp ModifiedOn;
	
	@Column(name = "deleted_by")
	private Long DeletedBy;
	
	@Column(name = "deleted_on")
	private Timestamp DeletedOn;
	
	@Column(name = "is_delete", nullable = true)
	private Boolean IsDelete;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getMedicalItemPurchaseId() {
		return MedicalItemPurchaseId;
	}

	public void setMedicalItemPurchaseId(Long medicalItemPurchaseId) {
		MedicalItemPurchaseId = medicalItemPurchaseId;
	}

	public Long getMedicalItemId() {
		return MedicalItemId;
	}

	public void setMedicalItemId(Long medicalItemId) {
		MedicalItemId = medicalItemId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Long getMedicalFacilityId() {
		return MedicalFacilityId;
	}

	public void setMedicalFacilityId(Long medicalFacilityId) {
		MedicalFacilityId = medicalFacilityId;
	}

	public Long getCourirId() {
		return CourirId;
	}

	public void setCourirId(Long courirId) {
		CourirId = courirId;
	}

	public Double getSubTotal() {
		return SubTotal;
	}

	public void setSubTotal(Double subTotal) {
		SubTotal = subTotal;
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
