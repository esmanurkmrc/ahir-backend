package com.akilli.ahir.Repository;

import com.akilli.ahir.Model.SutVerimKaydi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SutVerimKaydiRepository
        extends JpaRepository<SutVerimKaydi, Long> {

    
    List<SutVerimKaydi> findTop50ByOrderByTarihDesc();
}