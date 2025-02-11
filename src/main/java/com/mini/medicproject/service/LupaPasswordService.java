package com.mini.medicproject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mini.medicproject.models.User;
import com.mini.medicproject.repositories.LupaPasswordRepo;

@Service
@Transactional

public class LupaPasswordService {
	@Autowired
	LupaPasswordRepo lupapasswordrepo;
	
	 public void updatePassword(User user, String newPassword) {
	        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        //String encodedPassword = passwordEncoder.encode(newPassword);
	        user.setPassword(newPassword);
	         
	        lupapasswordrepo.save(user);
	    }
}
