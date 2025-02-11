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
import com.mini.medicproject.models.Medical_Item_Category;
import com.mini.medicproject.repositories.Medical_Item_CategoryRepo;

@Controller
@RequestMapping(value = "/katprodukkesehatan/")
public class KatProdukKesehatanController {

	@Autowired
	private Medical_Item_CategoryRepo katprodukkesrepo;
	
	@GetMapping(value = "index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/katprodukkesehatan/index");
		//List<Medical_Item_Category> katprodukdata = this.katprodukkesrepo.findAll();
		//view.addObject("katdata", katprodukdata);
		return view;
	}
	
	@GetMapping(value = "form_katprodukkesehatan")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/katprodukkesehatan/form_katprodukkesehatan");
		Medical_Item_Category katprodukkes = new Medical_Item_Category();
		view.addObject("katprodukkes_form", katprodukkes);
		return view;
	}
	
	@PostMapping(value = "save")
	public ModelAndView save(@ModelAttribute Medical_Item_Category meditemcat, BindingResult result, HttpSession sess) {
		if (!result.hasErrors()) {
			if (meditemcat.getId()!=null) {
				Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
				meditemcat.setModifiedBy((Long)sess.getAttribute("uid"));
				meditemcat.setModifiedOn(timestamp2);
				meditemcat.setName(meditemcat.getName().trim());
			}
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			meditemcat.setCreatedBy((Long)sess.getAttribute("uid"));
			meditemcat.setCreatedOn(timestamp);
			meditemcat.setIsDelete(false);
			meditemcat.setName(meditemcat.getName().trim());
			this.katprodukkesrepo.save(meditemcat);
		}
		return new ModelAndView("redirect:/katprodukkesehatan/index");
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/katprodukkesehatan/form_katprodukkesehatan");
		Medical_Item_Category katprodukkes = this.katprodukkesrepo.findById(id).orElse(null);
		view.addObject("katprodukkes_form", katprodukkes);
		return view;
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delProdukKes(HttpSession sess, @PathVariable("id") Long id) {
		if (id != null) {
			Object ses = sess.getAttribute("uid");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			this.katprodukkesrepo.deletProkes((Long) ses, time, id);
		}
		return new ModelAndView("redirect:/katprodukkesehatan/index");
	}
}
