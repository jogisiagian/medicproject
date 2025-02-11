package com.mini.medicproject.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Role;
import com.mini.medicproject.repositories.RoleRepo;

@RestController
@RequestMapping(value="/api/role/")
@CrossOrigin("*")
public class RoleApiController {
	
	@Autowired
	private RoleRepo rolerepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Role>> showmenu(){
		try {
			List<Role> role = this.rolerepo.findAll();
			return new ResponseEntity<>(role, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
