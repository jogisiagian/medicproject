package com.mini.medicproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/beliobat/")
public class BeliObatController {
	
	@GetMapping(value="index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/beliobat/index");
		return view;
	}
	
	@GetMapping(value="index1")
	public ModelAndView index1() {
		ModelAndView view = new ModelAndView("/beliobat/index1");
		return view;
	}

}
