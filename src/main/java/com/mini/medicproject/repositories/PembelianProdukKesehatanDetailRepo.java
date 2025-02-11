package com.mini.medicproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.PembelianProdukKesehatanDetail;

@Repository
public interface PembelianProdukKesehatanDetailRepo extends JpaRepository<PembelianProdukKesehatanDetail, Long> {

}
