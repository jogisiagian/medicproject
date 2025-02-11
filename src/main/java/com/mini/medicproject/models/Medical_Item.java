package com.mini.medicproject.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="m_medical_item")
public class Medical_Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "id", nullable = false)
	private Long Id;
	
	@Column(name = "name", length = 50)
	private String Name;
	
	@Column(name = "medical_item_category_id")
	private Long MedicalItemCategoryId;
	
	@Column(name = "composition", columnDefinition = "TEXT")
	private String Composition;
	
	@Column(name = "medical_item_segmentation_id")
	private Long MedicalItemSegmentationId;
	
	@Column(name = "manufacturer", length = 100)
	private String Manufacturer;
	
	@Column(name = "indication", columnDefinition = "TEXT")
	private String Indication;
	
	@Column(name = "dosage", columnDefinition = "TEXT")
	private String Dosage;
	
	@Column(name = "direction", columnDefinition = "TEXT")
	private String Direction;
	
	@Column(name = "contraindication", columnDefinition = "TEXT")
	private String Contraindication;
	
	@Column(name = "caution", columnDefinition = "TEXT")
	private String Caution;
	
	@Column(name = "packaging", length = 50)
	private String Packaging;
	
	@Column(name = "price_max")
	private Long PriceMax;
	
	@Column(name = "price_min")
	private Long PriceMin;
	
	@Column(name = "image")
	private String Image;
	
	@Column(name = "image_path")
	private String ImagePath;
	
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

	public Long getMedicalItemCategoryId() {
		return MedicalItemCategoryId;
	}

	public void setMedicalItemCategoryId(Long medicalItemCategoryId) {
		MedicalItemCategoryId = medicalItemCategoryId;
	}

	public String getComposition() {
		return Composition;
	}

	public void setComposition(String composition) {
		Composition = composition;
	}

	public Long getMedicalItemSegmentationId() {
		return MedicalItemSegmentationId;
	}

	public void setMedicalItemSegmentationId(Long medicalItemSegmentationId) {
		MedicalItemSegmentationId = medicalItemSegmentationId;
	}

	public String getManufacturer() {
		return Manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public String getIndication() {
		return Indication;
	}

	public void setIndication(String indication) {
		Indication = indication;
	}

	public String getDosage() {
		return Dosage;
	}

	public void setDosage(String dosage) {
		Dosage = dosage;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

	public String getContraindication() {
		return Contraindication;
	}

	public void setContraindication(String contraindication) {
		Contraindication = contraindication;
	}

	public String getCaution() {
		return Caution;
	}

	public void setCaution(String caution) {
		Caution = caution;
	}

	public String getPackaging() {
		return Packaging;
	}

	public void setPackaging(String packaging) {
		Packaging = packaging;
	}

	public Long getPriceMax() {
		return PriceMax;
	}

	public void setPriceMax(Long priceMax) {
		PriceMax = priceMax;
	}

	public Long getPriceMin() {
		return PriceMin;
	}

	public void setPriceMin(Long priceMin) {
		PriceMin = priceMin;
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

	public Boolean getIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		IsDelete = isDelete;
	}

}