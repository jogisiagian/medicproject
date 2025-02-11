package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Customer;
import com.mini.medicproject.models.User;
import com.mini.medicproject.models.UserProfil;
import com.mini.medicproject.repositories.CustomerRepo;
import com.mini.medicproject.repositories.UserProfilRepo;
import com.mini.medicproject.repositories.UserRepo;

@RestController
@RequestMapping(value="/api/customer")
@CrossOrigin("*")
public class UserProfilApiController {

	@Autowired UserProfilRepo userprofilrepo;
	
	@Autowired UserRepo userrepo;
	
	@Autowired CustomerRepo customerrepo;
	
	//Get Data Customer berdasarkan biodata id
	@GetMapping(value="/{id}")
	public ResponseEntity<List<Map<String, Object>>> getDataCustomer(@PathVariable("id") Long id) {
		List<Map<String, Object>> customer = this.customerrepo.getDataCustomer(id);
		if(!customer.isEmpty()) {
			return new ResponseEntity<>(customer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PatchMapping(value="/customer/{id}") //Tidak null jika tidak diupdate
	public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id) {
		Optional<Customer> customer_ = this.customerrepo.findById(id);
		if(customer_.isPresent()) {
			customer.setId(id);
			this.customerrepo.save(customer);
			return new ResponseEntity<>(customer_, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(customer_, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping(value="/biodata/{id}")
	public ResponseEntity<Object> updateBiodata(@RequestBody UserProfil userprofil, @PathVariable("id") Long id) {
		Optional<UserProfil> userprofil_ = this.userprofilrepo.findById(id);
		if(userprofil_.isPresent()) {
			userprofil.setId(id);
			this.userprofilrepo.save(userprofil);
			return new ResponseEntity<>(userprofil_, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(userprofil_, HttpStatus.BAD_REQUEST);
		}
	}
	
	// Get Data User email dan password
	@GetMapping("/")
	public ResponseEntity<List<User>> showuser() {
		try {
			List<User> user = this.userrepo.findAll();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	// Get Data User email dan password sesuai id
	@GetMapping(value="/akun/{id}")
	public ResponseEntity<Object> getUserEntity(@PathVariable("id") Long id) {
		Optional<User> user = this.userrepo.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No Data", HttpStatus.OK);
		}
	}
//	
//	@GetMapping(value="/cari/{email}/{password}")
//	public ResponseEntity<List<User>> cariuser(
//			@PathVariable("email") String email,
//			@PathVariable("password") String password
//			) {
//		try {
//			List<User> user = this.userprofilrepo.cariuser(email, password);
//			return new ResponseEntity<>(user, HttpStatus.OK);
//		} catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//	}
}
