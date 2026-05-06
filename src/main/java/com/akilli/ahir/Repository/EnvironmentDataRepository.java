package com.akilli.ahir.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaRepository;
import com.akilli.ahir.Model.EnvironmentData;

public interface EnvironmentDataRepository extends JpaRepository<EnvironmentData, Long> {

    
    Optional<EnvironmentData> findFirstByOrderByIdDesc();

   
    List<EnvironmentData> findAllByOrderByTarihAsc();

   
    List<EnvironmentData> findByTarih(LocalDate tarih);

  
    List<EnvironmentData> findByTarihBetween(LocalDate start, LocalDate end);
}