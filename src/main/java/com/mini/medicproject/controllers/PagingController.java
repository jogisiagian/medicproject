package com.mini.medicproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mini.medicproject.models.CaraPembayaran;
import com.mini.medicproject.service.CaraPembayaranService;

public class PagingController {
    @Autowired
    private CaraPembayaranService carapembayaranservice;

    @GetMapping("/pageable/{pageNo}/{pageSize}")
    public ResponseEntity<List<CaraPembayaran>> getProductPageable(
            @RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            List<CaraPembayaran> carapembayarandata = this.carapembayaranservice.getAllProduct(pageNo, pageSize);
            return new ResponseEntity<>(carapembayarandata, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}