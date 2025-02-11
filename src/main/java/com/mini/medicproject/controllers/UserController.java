package com.mini.medicproject.controllers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

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

import com.mini.medicproject.models.User;
import com.mini.medicproject.models.UserProfil;
import com.mini.medicproject.repositories.UserRepo;

@Controller
@RequestMapping(value = "/users/")
public class UserController {

	@Autowired
	private UserRepo userrepo;

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2* hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) {
				hexString.append(0);
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	@PostMapping(value = "/ceklogin")
	ModelAndView ceklogin(@ModelAttribute User user, BindingResult result, HttpSession sess) {
		String redirect = "";
		ModelAndView view = new ModelAndView("/index");
		String output = "";
		if (!result.hasErrors()) {
			String email = (String) result.getFieldValue("Email");
			String password = (String) result.getFieldValue("Password");

			try {
				MessageDigest digest;
				digest = MessageDigest.getInstance("SHA-256");
				byte[] encodehash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
				String pass = bytesToHex(encodehash);

				List<User> getuser = this.userrepo.getLogin(email, pass);
				
//				String fullname = (String)getname.get(0).get("fullname");
//				System.out.println(fullname);
				try {
					List<Map<String, Object>> getname = this.userrepo.getFullname(getuser.get(0).getBiodataId());
					sess.setAttribute("fname", getname.get(0).get("fullname"));
					sess.setAttribute("uid", getuser.get(0).getId());
					sess.setAttribute("email", getuser.get(0).getEmail());
					sess.setAttribute("roleid", getuser.get(0).getRoleId());
					sess.setAttribute("biodataid", getuser.get(0).getBiodataId());
					redirect = "redirect:/index";
					output= "Berhasil Login";
					System.out.println(output);
					view.addObject("output", output);

				} catch (Exception e) {
					output = "Email atau password salah";
					System.out.println(output);
					redirect = "redirect:/";
					view.addObject("output", output);
				}
			} catch (Exception e) {
				e.printStackTrace();
				redirect = "redirect:/";
			}
		}
		return view;
	}

	@GetMapping(value = "index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("/users/index");
		List<User> userdata = this.userrepo.findAll();
		view.addObject("udata", userdata);
		return view;
	}

	@GetMapping(value = "login")
	public ModelAndView form() {
		ModelAndView view = new ModelAndView("/login/form");
		return view;
	}
	@GetMapping(value = "daftar")
	public ModelAndView formDaftar() {
		ModelAndView view = new ModelAndView("/daftar/form");
		return view;
	}
	@GetMapping(value = "lupapassword")
	public ModelAndView formLupaPassword() {
		ModelAndView view = new ModelAndView("/LupaPassword/Form_LupaPassword");
		return view;
	}

	@PostMapping(value = "save")
	public ModelAndView save(@ModelAttribute User user, BindingResult result) throws NoSuchAlgorithmException {
		if (!result.hasErrors()) {
			this.userrepo.save(user);

			String pass = (String) result.getFieldValue("Password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
			user.setPassword(bytesToHex(encodedhash));

			this.userrepo.save(user);
		}
		return new ModelAndView("redirect:/index");
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editForm(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("/users/form");
		User user = this.userrepo.findById(id).orElse(null);
		view.addObject("usersform", user);
		return view;
	}

	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		if (id != null) {
			this.userrepo.deleteById(id);
		}
		return new ModelAndView("redirect:/users/index");

	}
}