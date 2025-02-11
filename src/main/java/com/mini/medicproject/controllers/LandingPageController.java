package com.mini.medicproject.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class LandingPageController {

	@GetMapping(value="index")
	public ModelAndView index(HttpSession sess) {
		ModelAndView view = new ModelAndView("/index");
		view .addObject("sessrole", sess.getAttribute("roleid"));
		view .addObject("sessfname", sess.getAttribute("fname"));
		return view;
	}
	
	@GetMapping(value="/index/{role_id}")
	public ModelAndView index(@PathVariable("role_id") Long roleid) {
		ModelAndView view = new ModelAndView("/index");
		view.addObject("role", roleid);
		return view;
	}
}
