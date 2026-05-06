package com.akilli.ahir.Repository; 

import java.util.List; 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akilli.ahir.Model.Hayvan;

@Repository
public interface HayvanRepository extends JpaRepository<Hayvan, Long> {

    Optional<Hayvan> findByKupeNo(String kupeNo);

    
    List<Hayvan> findByDurum(String durum);

 
    boolean existsByKupeNo(String kupeNo);
}