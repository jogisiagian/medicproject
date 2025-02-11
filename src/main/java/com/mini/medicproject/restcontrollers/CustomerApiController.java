package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Customer;
import com.mini.medicproject.models.CustomerMember;
import com.mini.medicproject.models.HubunganPasien;
import com.mini.medicproject.repositories.CustomerMemberRepo;
import com.mini.medicproject.repositories.CustomerRepo;
import com.mini.medicproject.repositories.HubunganPasienRepo;
import com.mini.medicproject.repositories.UserProfilRepo;
import com.mini.medicproject.service.PagingCustomerService;

@RestController
@RequestMapping(value = "/pasien/api")
@CrossOrigin("*")
public class CustomerApiController {

	@Autowired
	private CustomerRepo customerrepo;
	
	@Autowired
	private CustomerMemberRepo customermemberrepo;
	
	@Autowired
    private PagingCustomerService pagingservice;
	
	@Autowired
	private HubunganPasienRepo hubpasienrepo;
	
	@Autowired
	private UserProfilRepo userprofilerepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Map<String, Object>>> showPasien(HttpSession sess) {
		try {
			Long uid = (Long) sess.getAttribute("uid");
			//List<CustomerMember> pasien = this.customermemberrepo.findAll();
			List<Map<String, Object>> getpasien = this.customermemberrepo.getPasienByKuy(uid);
			return new ResponseEntity<>(getpasien, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> getPasienById(@PathVariable("id") Long id) {
		Optional<CustomerMember> cusber = this.customermemberrepo.findById(id);
		if (cusber.isPresent()) {
			return new ResponseEntity<>(cusber, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No data", HttpStatus.OK);
		}
	}
	
	@GetMapping("/pageable")
    public ResponseEntity<List<Map<String, Object>>> getProductPageable(
    		HttpSession sess,
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize
            ) {
        try {
        	Long uid = (Long) sess.getAttribute("uid");
            List<Map<String, Object>> pasiendata = this.pagingservice.getAllPasien(uid, pageNo, pageSize);
            return new ResponseEntity<>(pasiendata, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
	
}
