package com.mini.medicproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mini.medicproject.models.CustomerMember;
import com.mini.medicproject.repositories.CustomerMemberRepo;

@Service
public class PagingCustomerService {

	@Autowired
	CustomerMemberRepo pagingmemberrepo;
	
	public List<Map<String, Object>> getAllPasien(Long uid, Integer pageNo, Integer pageSize) {
		
		Pageable paging = PageRequest.of(pageNo, pageSize);
		
		Page<Map<String, Object>> pagedProduct = this.pagingmemberrepo.getPasienByParent(uid, paging);

        if(pagedProduct.hasContent()) {
            return pagedProduct.getContent();
        } else {
            return new ArrayList<Map<String, Object>>();
        }
	}
}
