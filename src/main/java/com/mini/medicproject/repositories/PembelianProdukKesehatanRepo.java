package com.mini.medicproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.medicproject.models.PembelianProdukKesehatan;

@Repository
public interface PembelianProdukKesehatanRepo extends JpaRepository<PembelianProdukKesehatan, Long> {

}
