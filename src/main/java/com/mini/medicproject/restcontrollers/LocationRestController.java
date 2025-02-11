package com.mini.medicproject.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Location;
import com.mini.medicproject.repositories.LocationRepo;

@RestController
@RequestMapping(value = "/location/api")

public class LocationRestController {
	
	@Autowired
	private LocationRepo locationrepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Location>> showlocation() {
		try {
			List<Location> location = this.locationrepo.tampillocation();
			return new ResponseEntity<>(location, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	}
	
}
