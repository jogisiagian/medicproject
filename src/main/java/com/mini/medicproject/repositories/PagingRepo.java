package com.mini.medicproject.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.CaraPembayaran;

@Repository
public interface PagingRepo extends PagingAndSortingRepository<CaraPembayaran, Long> {

}