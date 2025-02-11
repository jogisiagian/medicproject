package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.GolDarah;
import com.mini.medicproject.repositories.GolDarahRepo;

@RestController
@RequestMapping(value="/GolDarah/api")
public class GolDarahRestController {
	
	@Autowired
	GolDarahRepo goldarahrepo;
	
	@GetMapping("/")
	public ResponseEntity<List<GolDarah>> showgoldarah(){
		try {
			List<GolDarah> goldarah = this.goldarahrepo.tampildarah();
			return new ResponseEntity<>(goldarah, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id){
		Optional<GolDarah> goldarah = this.goldarahrepo.findById(id);
		if(goldarah.isPresent()) {
			return new ResponseEntity<>(goldarah, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("No data", HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteGolDarah(@PathVariable("id") Long id){
		this.goldarahrepo.deleteById(id);
		return new ResponseEntity<>("Hapus Data Berhasil!", HttpStatus.OK);
	}

}
