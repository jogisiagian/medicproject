package com.mini.medicproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mini.medicproject.models.Location;
import com.mini.medicproject.repositories.LocationRepo;

@Controller
@RequestMapping(value = "/caridokter/")
public class CariDokterController {
	
	@Autowired
	private LocationRepo locationrepo;
	
	@GetMapping(value = "index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/caridokter/index");
		return view;
	}
	
	@GetMapping(value = "form")
	public ModelAndView caridokter() {
		ModelAndView view = new ModelAndView("/caridokter/form");
		return view;
	}

}
