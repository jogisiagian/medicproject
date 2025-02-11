package com.mini.medicproject.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.LocationLevel;
import com.mini.medicproject.repositories.LocationLevelRepo;

@RestController
@RequestMapping(value="/locationlevel/api/")
@CrossOrigin("*")

public class LocationLevelRestController {
	
	@Autowired
	private LocationLevelRepo locationlevelrepo;
	
	@GetMapping("/")
	public ResponseEntity<List<LocationLevel>> showlevel(){
		try {
			List<LocationLevel> locationlevel = this.locationlevelrepo.findAll();
			return new ResponseEntity<>(locationlevel, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
