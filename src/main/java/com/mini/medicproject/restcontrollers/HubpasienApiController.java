package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.HubunganPasien;
import com.mini.medicproject.repositories.HubunganPasienRepo;

@RestController
@RequestMapping(value = "/hubunganpasien/api")
@CrossOrigin("*")
public class HubpasienApiController {

	@Autowired
	private HubunganPasienRepo hubpasienrepo;
	
	@GetMapping("/")
	public ResponseEntity<List<HubunganPasien>> showHubpasien() {
		try {
			List<HubunganPasien> hubpasien = this.hubpasienrepo.tampilHubpas();
			return new ResponseEntity<>(hubpasien, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getHubpasienById(@PathVariable("id") Long id) {
		Optional<HubunganPasien> hubpasien = this.hubpasienrepo.findById(id);
		if (hubpasien.isPresent()) {
			return new ResponseEntity<>(hubpasien, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No data", HttpStatus.OK);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteHubpasien(@PathVariable("id") Long id) {
		this.hubpasienrepo.deleteById(id);
		return new ResponseEntity<>("Hapus Data Berhasil!", HttpStatus.OK);

	}
	
	//carinama
	@GetMapping(value = "/cari/{name}")
	public ResponseEntity<List<HubunganPasien>> cariHubPas(@PathVariable("name") String name) {
		try {
			List<HubunganPasien> hubpas = this.hubpasienrepo.cariHubPas(name.toLowerCase());
			return new ResponseEntity<>(hubpas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//caridata
	@GetMapping(value="cekdata/{name}")
	public ResponseEntity<Object> cekData(@PathVariable("name") String name) throws Exception {
		try {
			Long hubpas = this.hubpasienrepo.cekData(name.toLowerCase());
			return new  ResponseEntity<>(hubpas, HttpStatus.OK);
		} catch (Exception e) {
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
}
