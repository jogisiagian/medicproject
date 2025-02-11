package com.mini.medicproject.controllers;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mini.medicproject.models.HubunganPasien;
import com.mini.medicproject.repositories.HubunganPasienRepo;

@Controller
@RequestMapping(value = "/hubunganpasien/")
public class HubunganPasienController {

	@Autowired
	private HubunganPasienRepo hubunganpasienrepo;
	
	@GetMapping(value = "index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/hubunganpasien/index");
		List<HubunganPasien> hubpasiendata = this.hubunganpasienrepo.findAll();
		view.addObject("hubpasdata", hubpasiendata);
		return view;
	}
	
	@GetMapping(value = "form_hubpasien")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/hubunganpasien/form_hubpasien");
		HubunganPasien hubunganpasien = new HubunganPasien();
		view.addObject("hubpasien_form", hubunganpasien);
		return view;
	}
	
	@PostMapping(value = "save")
	public ModelAndView save(@ModelAttribute HubunganPasien hubpasien, BindingResult result, HttpSession sess) {
		if (!result.hasErrors()) {
			if (hubpasien.getId()!=null) {
				Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
				hubpasien.setModifiedBy((Long)sess.getAttribute("uid"));
				hubpasien.setModifiedOn(timestamp2);
				hubpasien.setName(hubpasien.getName().trim());
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			hubpasien.setCreatedBy((Long)sess.getAttribute("uid"));
			hubpasien.setCreatedOn(timestamp);
			hubpasien.setIsDelete(false);
			hubpasien.setName(hubpasien.getName().trim());
			this.hubunganpasienrepo.save(hubpasien);
		}
		return new ModelAndView("redirect:/hubunganpasien/index");
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/hubunganpasien/form_hubpasien");
		HubunganPasien hubunganpasien = this.hubunganpasienrepo.findById(id).orElse(null);
		view.addObject("hubpasien_form", hubunganpasien);
		return view;
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delhubpasien(HttpSession sess, @PathVariable("id") Long id) {
		if (id != null) {
			Object ses = sess.getAttribute("uid");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			this.hubunganpasienrepo.deletIs((Long) ses, time, id);
		}
		return new ModelAndView("redirect:/hubunganpasien/index");
	}
	
}
