package com.akilli.ahir.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.akilli.ahir.Model.AnimalProductivity;

public interface AnimalProductivityRepository extends JpaRepository<AnimalProductivity, Long> {

 
    List<AnimalProductivity> findByHayvanId(int hayvanId);

 
    List<AnimalProductivity> findByTarih(LocalDate tarih);

    
    List<AnimalProductivity> findByHayvanIdAndTarihBetween(int hayvanId, LocalDate start, LocalDate end);

   
    List<AnimalProductivity> findByTarihBetween(LocalDate start, LocalDate end);
}