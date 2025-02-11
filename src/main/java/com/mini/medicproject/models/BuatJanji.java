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

@Entity
@Table(name = "t_appointment")
public class BuatJanji {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long Id;
	
	@ManyToOne
	@JoinColumn(name="customer_id", insertable=false, updatable=false)
	public Customer customer;
	
	@Column(name = "customer_id", nullable = true)
	private Long CustomerId;
	
	@Column(name = "doctor_office_id", nullable = true)
	private Long DoctorOfficeId;
	
	@Column(name = "doctor_office_schedule_id", nullable = true)
	private Long DoctorOfficeScheduleId;
	
	@Column(name = "doctor_office_treatment_id", nullable = true)
	private Long DoctorOfficeTreatmentId;
	
	@Column(name = "appointment_date", nullable = true)
	private Date AppointmentDate;
	
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(Long customerId) {
		CustomerId = customerId;
	}

	public Long getDoctorOfficeId() {
		return DoctorOfficeId;
	}

	public void setDoctorOfficeId(Long doctorOfficeId) {
		DoctorOfficeId = doctorOfficeId;
	}

	public Long getDoctorOfficeScheduleId() {
		return DoctorOfficeScheduleId;
	}

	public void setDoctorOfficeScheduleId(Long doctorOfficeScheduleId) {
		DoctorOfficeScheduleId = doctorOfficeScheduleId;
	}

	public Long getDoctorOfficeTreatmentId() {
		return DoctorOfficeTreatmentId;
	}

	public void setDoctorOfficeTreatmentId(Long doctorOfficeTreatmentId) {
		DoctorOfficeTreatmentId = doctorOfficeTreatmentId;
	}

	public Date getAppointmentDate() {
		return AppointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		AppointmentDate = appointmentDate;
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
