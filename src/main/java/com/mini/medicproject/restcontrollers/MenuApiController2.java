package com.mini.medicproject.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Menu;
import com.mini.medicproject.repositories.MenuRepo;

@RestController
@RequestMapping(value="/api/menus/")
@CrossOrigin("*")
public class MenuApiController2 {
	
	@Autowired
	private MenuRepo menurepo;
	
	@GetMapping("/")
	public ResponseEntity<List<Menu>> showmenu() {
		try {
			List<Menu> menu = this.menurepo.getParentMenu();
			return new ResponseEntity<>(menu, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
