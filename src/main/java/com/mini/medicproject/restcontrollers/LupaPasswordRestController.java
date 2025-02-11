package com.mini.medicproject.restcontrollers;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini.medicproject.models.User;
import com.mini.medicproject.repositories.LupaPasswordRepo;
import com.mini.medicproject.repositories.UserRepo;
import com.mini.medicproject.service.LupaPasswordService;

@RestController
@RequestMapping(value = "/lupapassword/")
@CrossOrigin("*")

public class LupaPasswordRestController {
	private Long otp;
	private static final long OTP_VALID_DURATION = 10 * 60 * 1000;
	private Long otpReqTime;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private LupaPasswordRepo lupapasswordrepo;
	
	@Autowired
	private JavaMailSender javamailsender;
	
	@Autowired
	LupaPasswordService lupapasswordservice;
	
	public void sendmail(String address, String subject, String message) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(address);
		msg.setSubject(subject);
		msg.setText(message);
		
		javamailsender.send(msg);
	}
	
	@GetMapping(value="cekemail/{email}")
	public ResponseEntity<Object> cekEmail(@PathVariable("email") String email) throws Exception {
		User cekemail = this.lupapasswordrepo.findbyemail2(email);
		if(cekemail.getEmail().equals(email)) {
			otpReqTime = System.currentTimeMillis();
			
			Random rnd = new Random();
		    this.otp = rnd.nextLong(999999);

			String subject = "Konfirmasi OTP";
			String message = "Kode OTP anda: " +otp;
			
			sendmail(email, subject, message);
			return new  ResponseEntity<>(cekemail, HttpStatus.OK);
		} else {
			return new  ResponseEntity<>(cekemail, HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping(value="cekotp/{otp}")
	public ResponseEntity<Object> cekOtp(@PathVariable("otp") String otp){
		Boolean valid=false;
		if (Integer.parseInt(otp)==this.otp) {
			valid=true;
			long currentTimeInMillis = System.currentTimeMillis();
//	        long otpRequestedTimeInMillis = this.otpRequestedTime.getTime();
	         
	        if (otpReqTime + OTP_VALID_DURATION < currentTimeInMillis) {
	        	clearOTP();
	            valid=false;
	        }
			return new  ResponseEntity<>(valid, HttpStatus.OK);
		}
		return new  ResponseEntity<>(valid, HttpStatus.OK);

	}
	
	public void clearOTP() {
	    this.otp = null;
	    this.otpReqTime = null; 
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		Optional<User> users = this.userrepo.findById(id);
		if (users.isPresent()) {
			return new  ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new  ResponseEntity<>("No data", HttpStatus.NO_CONTENT);
		}
	}
	
	@PatchMapping(value="/test/{id}/{password}")
	public ResponseEntity<Object> savePassword(@RequestBody User user, @PathVariable("id") Long id, @PathVariable("password") String password){
		Optional<User> user_ = this.lupapasswordrepo.findById(id);
		if (user_.isPresent()) {
			user.setId(id);
			user.setPassword(password);
			this.lupapasswordrepo.save(user);
			return new ResponseEntity<>(user_, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(user_, HttpStatus.BAD_REQUEST);
		}
		//return new ResponseEntity<>(user_, HttpStatus.OK);
	}
	
//	@PostMapping("/reset_password")
//	public String processResetPassword(HttpServletRequest request, Model model) {
//		String password = request.getParameter("password");
//		
//		User user = 
//		lupapasswordservice.updatePassword(user, password);
//		
//	}
	
}
