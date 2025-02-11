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
@RequestMapping(value = "/location/")

public class LocationController {
	
	@Autowired
	private LocationRepo locationrepo;
	
	@GetMapping(value = "index_location")
	public ModelAndView index_location() {
		ModelAndView view = new ModelAndView("/location/index_location");
		List<Location> locationdata = this.locationrepo.findAll();
		view.addObject("locdata", locationdata);
		return view;
	}
	
	@GetMapping(value = "form_location")
	public ModelAndView form_location() {
		ModelAndView view = new ModelAndView("/location/form_location");
		Location location = new Location();
		view.addObject("locationform", location);
		return view;
	}
	
}
