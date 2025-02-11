package com.mini.medicproject.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mini.medicproject.models.GolDarah;
import com.mini.medicproject.repositories.GolDarahRepo;

@Controller
@RequestMapping(value="/goldarah/")
public class GolDarahController {
	
	@Autowired
	private GolDarahRepo goldarahrepo;
	
	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/goldarah/index");
		List<GolDarah> goldarahdata = this.goldarahrepo.findAll();
		view.addObject("gddata", goldarahdata);
		return view;
	}
	
	@GetMapping(value="form")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/goldarah/form");
		GolDarah goldarah = new GolDarah();
		view.addObject("goldarahform", goldarah);
		return view;
	}
	
	
	@PostMapping(value="save")
	public ModelAndView save(@ModelAttribute GolDarah goldarah, BindingResult result, HttpSession sess) {
		if(!result.hasErrors()) {
			goldarah.setIsDelete(false);
			goldarah.setCreatedBy((long)sess.getAttribute("uid"));
			if(goldarah.getId()!=null) {
				Timestamp now1 = new Timestamp(System.currentTimeMillis());
				goldarah.setModifiedOn(now1);
				goldarah.setModifiedBy((long)sess.getAttribute("uid"));
			}else {
				Timestamp now = new Timestamp(System.currentTimeMillis());
				goldarah.setCreatedOn(now);
			}
			this.goldarahrepo.save(goldarah);
		}
		return new ModelAndView("redirect:/goldarah/index");
	}
	
	@GetMapping(value="/edit/{id}")
	public ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/goldarah/form");
		GolDarah goldarah = this.goldarahrepo.findById(id).orElse(null);
		view.addObject("goldarahform", goldarah);
		return view;
	}
	
	@GetMapping(value="/delete/{id}")
	public ModelAndView delprofile(@PathVariable("id") Long id, HttpSession sess) {
		if(id != null) {
			Timestamp del = new Timestamp(System.currentTimeMillis());
			Object s = sess.getAttribute("uid");
			this.goldarahrepo.delete((Long) s, del, id);
		}
		return new ModelAndView("redirect:/goldarah/index");
	}
	
	@GetMapping(value = "/caridarah/{code}")
	public ResponseEntity<List<GolDarah>> caridarah(
			@PathVariable("code") String code
			) {
		try {
			List<GolDarah> goldarah = this.goldarahrepo.caridarah(code.toLowerCase());
			return new ResponseEntity<>(goldarah, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
