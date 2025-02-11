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

import com.mini.medicproject.models.Customer;
import com.mini.medicproject.models.CustomerMember;
import com.mini.medicproject.models.GolDarah;
import com.mini.medicproject.models.HubunganPasien;
import com.mini.medicproject.models.UserProfil;
import com.mini.medicproject.repositories.CustomerMemberRepo;
import com.mini.medicproject.repositories.CustomerRepo;
import com.mini.medicproject.repositories.GolDarahRepo;
import com.mini.medicproject.repositories.HubunganPasienRepo;
import com.mini.medicproject.repositories.UserProfilRepo;
import com.mini.medicproject.repositories.UserRepo;

@Controller
@RequestMapping(value = "/pasien/")
public class CustomerController {

	@Autowired
	private CustomerRepo customerrepo;
	
	@Autowired
	private UserProfilRepo userprofilerepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private HubunganPasienRepo hubpasienrepo;
	
	@Autowired
	private GolDarahRepo goldarahrepo;
	
	@Autowired
	private CustomerMemberRepo customermemberrepo;
	
	@GetMapping(value = "index_pasien")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/pasien/index_pasien");
		List<Customer> pasiendata = this.customerrepo.findAll();
		List<CustomerMember> pasiendata2 = this.customermemberrepo.findAll();
		view.addObject("datapasien", pasiendata);
		view.addObject("datapasien", pasiendata2);
		return view;
	}
	
	@GetMapping(value = "form_pasien")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/pasien/form_pasien");
		UserProfil biodata = new UserProfil();
		Customer customer = new Customer();
		CustomerMember cusmember = new CustomerMember();
		view.addObject("bio", biodata);
		view.addObject("pasien_form", customer);
		view.addObject("cusmem", cusmember);
		List<GolDarah> goldar = this.goldarahrepo.tampildarah();//mengambil dari db goldarah
		view.addObject("combogoldar", goldar);//taro di object view
		List<HubunganPasien> hubpas = this.hubpasienrepo.tampilHubpas();//mengambil dari db hubpasien
		view.addObject("combohubpas", hubpas);//taro di object view
		return view;
	}
	
	@PostMapping(value = "save")
	public ModelAndView save(@ModelAttribute Customer customer, @ModelAttribute UserProfil biodata, @ModelAttribute CustomerMember cusmem, BindingResult result, HttpSession sess) {
		if (!result.hasErrors()) {
			//save pertama ke table biodata
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			biodata.setCreatedBy((Long)sess.getAttribute("uid"));
			biodata.setCreatedOn(timestamp);
			biodata.setIsDeleted(false);
			this.userprofilerepo.save(biodata);
			
			//save kedua ke table customer
			Long bid = this.userrepo.lastBiodataId();
			customer.setBiodataId(bid);
			customer.setCreatedBy((Long)sess.getAttribute("uid"));
			customer.setCreatedOn(timestamp);
			biodata.setIsDeleted(false);
			this.customerrepo.save(customer);
			
			//save ketiga ke table customer member
			Long cid = this.customermemberrepo.lastCustomerId();
			cusmem.setCustomerId(cid);
			cusmem.setParentBiodataId((Long)sess.getAttribute("uid"));
			cusmem.setCreatedBy((Long) sess.getAttribute("uid"));
			cusmem.setCreatedOn(timestamp);
			cusmem.setIsDelete(false);
			this.customermemberrepo.save(cusmem);
		}
		return new ModelAndView("redirect:/pasien/index_pasien");
	}
	
	@GetMapping(value = "/edit/{id}")
	public ModelAndView editform(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/pasien/edit_pasien");
		CustomerMember cusmember = this.customermemberrepo.findById(id).orElse(null);
		Customer customer = this.customerrepo.findById(cusmember.getCustomerId()).orElse(null);
		UserProfil biodata = this.userprofilerepo.findById(customer.getBiodataId()).orElse(null);
		view.addObject("bio", biodata);
		view.addObject("pasien_form", customer);
		view.addObject("cusmem", cusmember);
		List<GolDarah> goldar = this.goldarahrepo.tampildarah();//mengambil dari db goldar
		view.addObject("combogoldar", goldar);//taro di object view
		List<HubunganPasien> hubpas = this.hubpasienrepo.tampilHubpas();//mengambil dari db hubpasien
		view.addObject("combohubpas", hubpas);//taro di object view
		return view;
	}
	
	@PostMapping(value = "edit")
	public ModelAndView edit(@ModelAttribute Customer customer, @ModelAttribute UserProfil biodata, @ModelAttribute CustomerMember cusmem, BindingResult result, HttpSession sess) {
		if (!result.hasErrors()) {
			
			//save pertama ke table biodata
			Long test = this.customermemberrepo.idBio(customer.getId());
			biodata.setId(test);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			biodata.setModifiedBy((Long)sess.getAttribute("uid"));
			biodata.setModifiedOn(timestamp);
			biodata.setIsDeleted(false);
			this.userprofilerepo.save(biodata);
			
			//save kedua ke table customer
			Long bid = this.userrepo.lastBiodataId();
			customer.setBiodataId(bid);
			customer.setModifiedBy((Long)sess.getAttribute("uid"));
			customer.setModifiedOn(timestamp);
			customer.setIsDeleted(false);
			this.customerrepo.save(customer);
			
			//save ketiga ke table customer member
			Long cid = this.customermemberrepo.lastCustomerId();
			cusmem.setCustomerId(cid);
			cusmem.setParentBiodataId((Long)sess.getAttribute("uid"));
			cusmem.setModifiedBy((Long)sess.getAttribute("uid"));
			cusmem.setModifiedOn(timestamp);
			cusmem.setIsDelete(false);
			this.customermemberrepo.save(cusmem);
		}
		return new ModelAndView("redirect:/pasien/index_pasien");
	}
	
	@GetMapping(value = "/delete/{id}")
	public ModelAndView delPasien(HttpSession sess, @PathVariable("id") Long id) {
		if (id != null) {
			Object ses = sess.getAttribute("uid");
			Timestamp time = new Timestamp(System.currentTimeMillis());
			this.customermemberrepo.deletPasMem((Long) ses, time, id);
			this.customermemberrepo.deletPasCus((Long) ses, time, id);
			Long test = this.customermemberrepo.idBio(id);
			this.customermemberrepo.deletPasBio((Long) ses, time, test);
		}
		return new ModelAndView("redirect:/pasien/index_pasien");
	}
	
	@GetMapping(value = "/delete/multi/{id}")
	public ModelAndView delMultiPasien(HttpSession sess, @PathVariable("id") String id) {
		String[] di = id.split("");
		Object ses = sess.getAttribute("uid");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		for (int i = 0; i < di.length; i++) {
			if (Long.valueOf(di[i]) != null) {
				this.customermemberrepo.deletPasMem((Long) ses, time, Long.valueOf(di[i]));
				this.customermemberrepo.deletPasCus((Long) ses, time, Long.valueOf(di[i]));
				Long test = this.customermemberrepo.idBio(Long.valueOf(di[i]));
				this.customermemberrepo.deletPasBio((Long) ses, time, test);
			}
			
		}
		return new ModelAndView("redirect:/pasien/index_pasien");
	}
	
}
