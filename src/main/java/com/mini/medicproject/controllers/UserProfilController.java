package com.mini.medicproject.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mini.medicproject.models.Customer;
import com.mini.medicproject.models.User;
import com.mini.medicproject.models.UserProfil;
import com.mini.medicproject.repositories.CustomerRepo;
import com.mini.medicproject.repositories.UserProfilRepo;
import com.mini.medicproject.repositories.UserRepo;

@Controller
@RequestMapping(value="/layoutprofil/")
public class UserProfilController {
	
	@Autowired UserProfilRepo userprofilrepo;
	
	@Autowired UserRepo userrepo;
	
	@Autowired CustomerRepo customerrepo;

	@GetMapping(value="userprofil/{id}")
	public ModelAndView userprofil(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/layoutprofil/userprofil");
		List<User> userdata = this.userrepo.findByBiodataId(id);
		view.addObject("udata", userdata);
		Customer customerdata = this.customerrepo.findById(id).orElse(null);
		view.addObject("customerdata", customerdata);
		return view;
	}
	
	
	// customer berdasarkan biodata_id
	// untuk update customernya berdasarkan biodata_id
	// update untuk biodata berdasarkan id
	
	@GetMapping(value="tabprofil")
	public ModelAndView tabprofil() {
		ModelAndView view = new ModelAndView("/layoutprofil/tabprofil");
		UserProfil userprofil = new UserProfil();
		view.addObject("userprofilform", userprofil);
		Customer customer = new Customer();
		view.addObject("customerform", customer);
		return view;
	}
	
	@GetMapping(value="dataakun")
	public ModelAndView dataakun() {
		ModelAndView view = new ModelAndView("/layoutprofil/dataakun");
		User user = new User();
		view.addObject("dataakunform", user);
		return view;
	}
	
	@GetMapping(value="editfoto/{id}")
	public ModelAndView editfoto(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/layoutprofil/editfoto");
		UserProfil userprofil = this.userprofilrepo.findById(id).orElse(null);
		view.addObject("editfotoform", userprofil);
		return view;
	}
	
	private static String UPLOADED_FOLDER = "D:\\Foto\\javafile\\";
	
	
	
	@PostMapping(value="save")
	public ModelAndView save(@ModelAttribute UserProfil userprofil, 
			BindingResult result,
			@RequestParam("photofile") MultipartFile file) throws Exception {
		if(!result.hasErrors()) {
			if(file.getOriginalFilename() != "") {
				byte[] bytes = file.getBytes();
				// membuat file name
				
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
			}
			this.userprofilrepo.save(userprofil);
			
		}
		String id = (String)result.getFieldValue("Id");
		return new ModelAndView("redirect:/layoutprofil/userprofil/"+id);
	}
	
	@GetMapping(value="/edit/{id}")
	public ModelAndView edittabprofil(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/layoutprofil/tabprofil");
		UserProfil userprofil = this.userprofilrepo.findById(id).orElse(null);
		view.addObject("userprofilform", userprofil);
		return view;
	}
	
	@GetMapping(value="/delete/{id}")
	public ModelAndView deltabprofil(@PathVariable("id") Long id) {
		if(id != null) {
			this.userprofilrepo.deleteById(id);
		}
		return new ModelAndView("redirect:/layoutprofil/userprofil");
	}
}
