package com.mini.medicproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mini.medicproject.models.Role;
import com.mini.medicproject.repositories.RoleRepo;

@Controller
@RequestMapping(value = "/role/")
public class RoleController {
	
	@Autowired
	private RoleRepo rolerepo;
	
	@GetMapping(value = "index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/role/index");
		List<Role> role = this.rolerepo.findAll();
		view.addObject("rdata", role);
		return view;
	}

}
