package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.DoctorOfficeTreatment;
import com.mini.medicproject.models.DoctorTreatment;
import com.mini.medicproject.models.Location;
import com.mini.medicproject.models.Specialization;
import com.mini.medicproject.repositories.DoctorOfficeTreatmentRepo;
import com.mini.medicproject.repositories.DoctorTreatmentRepo;
import com.mini.medicproject.repositories.LocationRepo;
import com.mini.medicproject.repositories.SpecializationRepo;

@RestController
@RequestMapping(value="/api/caridokter/")
@CrossOrigin("*")
public class CariDokterApiController {
	
	@Autowired
	private LocationRepo locationrepo;
	
	@Autowired
	private SpecializationRepo specializationrepo;
	
	@Autowired
	private DoctorTreatmentRepo treatmentrepo;
	
	@Autowired
	private DoctorOfficeTreatmentRepo doctorofficetreatmentrepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Location>> showLocation() {
		try {
			List<Location> location = this.locationrepo.findAll();
			return new ResponseEntity<>(location, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/specialization")
	public ResponseEntity<List<Specialization>> showSpec() {
		try {
			List<Specialization> spec = this.specializationrepo.findAll();
			return new ResponseEntity<>(spec, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/treatment")
	public ResponseEntity<List<DoctorTreatment>> showTreat() {
		try {
			List<DoctorTreatment> treat = this.treatmentrepo.findAll();
			return new ResponseEntity<>(treat, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/tindakan")
	public ResponseEntity<List<Map<String, Object>>> tindakan() {
		try {
			List<Map<String, Object>> treat = this.treatmentrepo.getTindakan();
			return new ResponseEntity<>(treat, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/doctors")
	public ResponseEntity<List<Map<String, Object>>> doctors() {
		try {
			List<Map<String, Object>> doctors = this.treatmentrepo.getDoctor();
			return new ResponseEntity<>(doctors, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="/caridoctor/{location}/{fullname}/{specialization}/{treatment}")
	public ResponseEntity<List<Map<String, Object>>> caridoctor(
			@PathVariable("location") String location,
			@PathVariable("fullname") String fullname,
			@PathVariable("specialization") String specialization,
			@PathVariable("treatment") String treatment
			) {
		try {
			List<Map<String, Object>> caridoctor = this.treatmentrepo.cariDoctor(location.toLowerCase(), fullname.toLowerCase(), specialization.toLowerCase(), treatment.toLowerCase());
			return new ResponseEntity<>(caridoctor, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/finddoctor")
	public ResponseEntity<List<DoctorOfficeTreatment>> showdoc() {
		try {
			List<DoctorOfficeTreatment> finddoc = this.doctorofficetreatmentrepo.findAll();
			return new ResponseEntity<>(finddoc, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value="/finddoctor/{fullname}")
	public ResponseEntity<List<DoctorOfficeTreatment>> showdoc2(
			@PathVariable("fullname") String fullname) {
		try {
			List<DoctorOfficeTreatment> finddoc = this.doctorofficetreatmentrepo.findAll();
			return new ResponseEntity<>(finddoc, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
