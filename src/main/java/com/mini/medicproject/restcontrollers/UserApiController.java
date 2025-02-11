package com.mini.medicproject.restcontrollers;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mini.medicproject.models.User;
import com.mini.medicproject.models.UserProfil;
import com.mini.medicproject.repositories.UserProfilRepo;
import com.mini.medicproject.repositories.UserRepo;

import net.bytebuddy.utility.RandomString;


@RestController
@RequestMapping(value="/api/users/")
@CrossOrigin("*")
public class UserApiController {
	
	private Long kodeotp;
//	private String kodeotp2;
	private static final long OTP_VALID_DURATION = 10 * 60 * 1000; 
//	private Date otpRequestedTime;
	private Long otpReqTime;

	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private UserProfilRepo biodatarepo;
	
	@GetMapping("/")
	public ResponseEntity<List<User>> showUser() {
		try {
			List<User> user = this.userrepo.findAll();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
		}
	}
	
	@PostMapping(value="/")
	public ResponseEntity<Object> saveUser(@RequestBody User user) throws NoSuchAlgorithmException {
		
		MessageDigest digest;
		digest = MessageDigest.getInstance("SHA-256");
		byte[] encodehash = digest.digest(user.getPassword().getBytes(StandardCharsets.UTF_8));
		String pass = bytesToHex(encodehash);
		user.setPassword(pass);
		
		User user_ = this.userrepo.save(user);
		if(user_.equals(user)) {
			System.out.println("Daftar Berhasil");
			return new ResponseEntity<>(user_,HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Daftar gagal",HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value="savebiodata")
	public ResponseEntity<Object> saveBiodata(@RequestBody UserProfil biodata){
		UserProfil biodata_ = this.biodatarepo.save(biodata);
		if(biodata_.equals(biodata_)) {
			return new ResponseEntity<>(biodata_,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Update data gagal",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable("id") Long id) {
		Optional<User> users = this.userrepo.findById(id);
		if (users.isPresent()) {
			return new  ResponseEntity<>(users, HttpStatus.OK);
		} else {
			return new  ResponseEntity<>("No data", HttpStatus.OK);
		}
	}
	
	@GetMapping(value="cekemail/{email}")
	public ResponseEntity<Object> cekEmail(@PathVariable("email") String email) throws Exception {
		Long cekemail = this.userrepo.cekEmail(email);
		if(cekemail==0) {
			otpReqTime = System.currentTimeMillis();
			
			Random rnd = new Random();
		    this.kodeotp = rnd.nextLong(999999);
//			this.kodeotp2 = RandomString.make(8);
			
//			String emailadd = (String) result.getFieldValue("Email");
			String subject = "Konfirmasi OTP";
			String message = "Kode OTP anda: " +kodeotp;
			
			sendmail(email, subject, message);
			return new  ResponseEntity<>(cekemail, HttpStatus.OK);
		} else {
			return new  ResponseEntity<>(cekemail, HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value="ceklogin/{email}/{password}")
	public ResponseEntity<Object> cekLogin(@PathVariable("email") String email, @PathVariable("password") String password, HttpSession sess) throws Exception {
		String output = "";
		Boolean login = false;
		try {
			MessageDigest digest;
			digest = MessageDigest.getInstance("SHA-256");
			byte[] encodehash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
			String pass = bytesToHex(encodehash);

			List<User> getuser = this.userrepo.getLogin(email, pass);
			
//			String fullname = (String)getname.get(0).get("fullname");
//			System.out.println(fullname);
			if(!getuser.isEmpty()) {
				List<Map<String, Object>> getname = this.userrepo.getFullname(getuser.get(0).getBiodataId());
				sess.setAttribute("fname", getname.get(0).get("fullname"));
				sess.setAttribute("uid", getuser.get(0).getId());
				sess.setAttribute("email", getuser.get(0).getEmail());
				sess.setAttribute("roleid", getuser.get(0).getRoleId());
				sess.setAttribute("biodataid", getuser.get(0).getBiodataId());
				output= "Berhasil Login";
				System.out.println(output);
				login=true;

			} else {
				output = "Email atau password salah";
				System.out.println(output);
				login=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			login=false;
		}
		return new  ResponseEntity<>(login, HttpStatus.OK);
		
	}
	
	@GetMapping(value="sendotp/{email}")
	public ResponseEntity<Object> sendOtp(@PathVariable("email") String email) throws Exception{
		otpReqTime = System.currentTimeMillis();
		
		Random rnd = new Random();
	    this.kodeotp = rnd.nextLong(999999);

		String subject = "Konfirmasi OTP";
		String message = "Kode OTP anda: " +kodeotp;
		
		sendmail(email, subject, message);
		return new  ResponseEntity<>("1", HttpStatus.OK); 
	}
	
	@GetMapping(value="cekotp/{otp}")
	public ResponseEntity<Object> cekOtp(@PathVariable("otp") String otp){
		Boolean valid=false;
		if (Integer.parseInt(otp)==this.kodeotp) {
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
	    this.kodeotp=null;
	    this.otpReqTime = null; 
	}
	
	@Autowired
	private JavaMailSender javamailsender;
	
	public void sendmail(String address, String subject, String message) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(address);
		msg.setSubject(subject);
		msg.setText(message);
		
		javamailsender.send(msg);
	}
	
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
	
	@GetMapping(value="lastbiodataid")
	public Long getBiodataId() {
		return userrepo.lastBiodataId();
	}
	
	
}
