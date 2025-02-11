package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.Medical_Item_Category;
import com.mini.medicproject.repositories.Medical_ItemRepo;
import com.mini.medicproject.repositories.Medical_Item_CategoryRepo;

@RestController
@RequestMapping(value = "/api/beliobat/")
@CrossOrigin("*")
public class BeliObatApiController {
	
	@Autowired
	private Medical_Item_CategoryRepo medicalitemcategoryrepo;
	
	@Autowired
	private Medical_ItemRepo medicalitemrepo;
	
	@GetMapping("/category")
	public ResponseEntity<List<Medical_Item_Category>> showCategory(){
		try {
			List<Medical_Item_Category> item = this.medicalitemcategoryrepo.findAll();
			return new ResponseEntity<>(item, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/cariobat/{category}/{keyword}/{segmentation}/{min}/{max}")
	public ResponseEntity<List<Map<String, Object>>> cariobat(@PathVariable("category") String category,
			@PathVariable("keyword") String keyword, @PathVariable("segmentation") String segmen, @PathVariable("min") Long min,
			@PathVariable("max") Long max) {
		try {
			List<Map<String, Object>> keywords = this.medicalitemrepo.getNama(category, keyword.toLowerCase(), segmen, min, max);
			return new ResponseEntity<>(keywords, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
