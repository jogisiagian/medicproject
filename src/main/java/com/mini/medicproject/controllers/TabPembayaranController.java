package com.mini.medicproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/layoutpembayaran/")
public class TabPembayaranController {
	@GetMapping(value="tabpembayaran")
	public ModelAndView tabprofil() {
		ModelAndView view = new ModelAndView("/layoutpembayaran/tabpembayaran");
		return view;
	}

}
