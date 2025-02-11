package com.mini.medicproject.restcontrollers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.CaraPembayaran;
import com.mini.medicproject.repositories.CaraPembayaranRepo;
import com.mini.medicproject.service.CaraPembayaranService;

@RestController
@RequestMapping(value = "/carapembayaran/api")

public class CaraPembayaranRestController {

	@Autowired
	CaraPembayaranRepo carapembayaranrepo;
	
	@Autowired
	CaraPembayaranService carapembayaranservice;

	@GetMapping("/")
	public ResponseEntity<List<CaraPembayaran>> showcarapembayaran() {
		try {
			List<CaraPembayaran> carapembayaran = this.carapembayaranrepo.tampilcarapembayaran();
			return new ResponseEntity<>(carapembayaran, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> deleteCaraPembayaran(HttpSession sess, @PathVariable("id") Long id) {
		Long ses = (Long) sess.getAttribute("uid");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        this.carapembayaranrepo.deletIs(ses, time, id);
		//this.carapembayaranrepo.deleteById(id);
		return new ResponseEntity<>("Hapus Data Berhasil!", HttpStatus.OK);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		Optional<CaraPembayaran> carapembayaran = this.carapembayaranrepo.findById(id);
		if (carapembayaran.isPresent()) {
			return new ResponseEntity<>(carapembayaran, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No data", HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/ceknama/{nama}")
	 public ResponseEntity<Object> ceknama(@PathVariable("nama") String nama) throws Exception {
        try {
            Long ceknama_ = this.carapembayaranrepo.carinama(nama.toLowerCase());
            return new  ResponseEntity<>(ceknama_, HttpStatus.OK);
        } catch (Exception e) {
            return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
	
	@GetMapping("/pageable")
    public ResponseEntity<List<CaraPembayaran>> getProductPageable(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            List<CaraPembayaran> productdata = this.carapembayaranservice.getAllProduct(pageNo, pageSize);
            return new ResponseEntity<>(productdata, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
	
	@PostMapping(value = "/")
	public ResponseEntity<Object> savePembayaran(@RequestBody CaraPembayaran carapembayaran, HttpSession sess){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		carapembayaran.setCreated_by((Long)sess.getAttribute("uid"));
		carapembayaran.setCreated_on(timestamp);
		carapembayaran.setIs_delete(false);
		
		CaraPembayaran carapembayaran_ = this.carapembayaranrepo.save(carapembayaran);
		if(carapembayaran.equals(carapembayaran)) {
			return new ResponseEntity<>(carapembayaran_, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Update data gagal", HttpStatus.BAD_REQUEST);
		}
	}
	
}
