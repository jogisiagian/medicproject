package com.mini.medicproject.controllers;

import java.sql.Date;
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

import com.mini.medicproject.models.CaraPembayaran;
import com.mini.medicproject.repositories.CaraPembayaranRepo;


@Controller
@RequestMapping(value = "/carapembayaran/")

public class CaraPembayaranController {
	@Autowired
	private CaraPembayaranRepo carapembayaranrepo;
	
	@GetMapping(value = "index_carapembayaran")
	public ModelAndView  index_carapembayaran() {
		ModelAndView view = new ModelAndView("/carapembayaran/index_carapembayaran");
		List<CaraPembayaran> cpembayarandata = this.carapembayaranrepo.findAll();
		view.addObject("cpembayaran", cpembayarandata);
		return view;
	}
	
	@GetMapping(value = "form_carapembayaran")
	public ModelAndView form_carapembayaran() {
		ModelAndView view = new ModelAndView("/carapembayaran/form_carapembayaran");
		CaraPembayaran carapembayaran = new CaraPembayaran();
		view.addObject("carapembayaranform", carapembayaran);
		return view;
	}
	
	@PostMapping(value = "save")
	public ModelAndView save(@ModelAttribute CaraPembayaran carapembayaran, BindingResult result, HttpSession sess) {
		if(!result.hasErrors()) {
			if(carapembayaran.getId() != null) {
			Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
			carapembayaran.setModified_by((Long)sess.getAttribute("uid"));
			carapembayaran.setModified_on(timestamp2);
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			carapembayaran.setCreated_by((Long)sess.getAttribute("uid"));
			carapembayaran.setCreated_on(timestamp);
			carapembayaran.setIs_delete(false);
			this.carapembayaranrepo.save(carapembayaran);
		}
		return new ModelAndView("redirect:/carapembayaran/index_carapembayaran");
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/carapembayaran/form_carapembayaran");
		CaraPembayaran carapembayaran = this.carapembayaranrepo.findById(id).orElse(null);
		view.addObject("carapembayaranform", carapembayaran);
		return view;
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delcarapembayaran(@PathVariable("id") Long id) {
		if(id != null) {
			this.carapembayaranrepo.deleteById(id);
		}
		return new ModelAndView("redirect:/carapembayaran/index_carapembayaran");
	}
	
	@GetMapping(value = "/caripembayaran/{nama}")
	public ResponseEntity<List<CaraPembayaran>> caripembayaran(
			@PathVariable("nama") String nama
			) {
		try {
			List<CaraPembayaran> carapembayaran = this.carapembayaranrepo.caripembayaran(nama.toLowerCase());
			return new ResponseEntity<>(carapembayaran, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping(value = "form_lupapassword")
	public ModelAndView form_lupapassword() {
		ModelAndView view = new ModelAndView("/lupapassword/form_lupapassword");
		return view;
	}
	
	@GetMapping(value = "form_lupapassword2")
	public ModelAndView form_lupapassword2() {
		ModelAndView view = new ModelAndView("/lupapassword/form_lupapassword2");
		return view;
	}
	
	@GetMapping(value = "form_lupapassword3")
	public ModelAndView form_lupapassword3() {
		ModelAndView view = new ModelAndView("/lupapassword/form_lupapassword3");
		return view;
	}
}
