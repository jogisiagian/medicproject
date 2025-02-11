package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Medical_Item_Category;
import com.mini.medicproject.repositories.Medical_Item_CategoryRepo;

@RestController
@RequestMapping(value = "/katprodukkesehatan/api")
@CrossOrigin("*")
public class KatProdukKesehatanApiController {

	@Autowired
	private Medical_Item_CategoryRepo katprodukkesrepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Medical_Item_Category>> showKatProdukKes() {
		try {
			List<Medical_Item_Category> katkes = this.katprodukkesrepo.tampilKatKes();
			return new ResponseEntity<>(katkes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getKatProdukKesById(@PathVariable("id") Long id) {
		Optional<Medical_Item_Category> katkes = this.katprodukkesrepo.findById(id);
		if (katkes.isPresent()) {
			return new ResponseEntity<>(katkes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No data", HttpStatus.OK);
		}
	}
	
	//carinama
	@GetMapping(value = "/cari/{name}")
	public ResponseEntity<List<Medical_Item_Category>> cariKatKes(@PathVariable("name") String name) {
		try {
			List<Medical_Item_Category> katkes = this.katprodukkesrepo.cariKatProKes(name.toLowerCase());
			return new ResponseEntity<>(katkes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	//caridata
	@GetMapping(value="cekdata/{name}")
	public ResponseEntity<Object> cekData(@PathVariable("name") String name) throws Exception {
		try {
			Long katkes = this.katprodukkesrepo.cekData(name.toLowerCase());
			return new  ResponseEntity<>(katkes, HttpStatus.OK);
		} catch (Exception e) {
			return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
}
