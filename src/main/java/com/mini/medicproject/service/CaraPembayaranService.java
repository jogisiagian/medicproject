package com.mini.medicproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mini.medicproject.models.CaraPembayaran;
import com.mini.medicproject.models.User;
import com.mini.medicproject.repositories.CaraPembayaranRepo;
import com.mini.medicproject.repositories.LupaPasswordRepo;
import com.mini.medicproject.repositories.PagingRepo;

@Service

public class CaraPembayaranService {
	@Autowired
	CaraPembayaranRepo carapembayaranrepo;
	
	public List<CaraPembayaran> getAllProduct(Integer pageNo, Integer pageSize) {
		
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		Page<CaraPembayaran> pagedProduct = this.carapembayaranrepo.findAll(paging);
		
		if(pagedProduct.hasContent()) {
			return pagedProduct.getContent();
		} else {
            return new ArrayList<CaraPembayaran>();
		}
		
	}
	
}
