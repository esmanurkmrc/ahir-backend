package com.akilli.ahir.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akilli.ahir.Model.AnalysisProductivity;

public interface AnalysisProductivityRepository extends JpaRepository<AnalysisProductivity, Long> {

    List<AnalysisProductivity> findByHayvanId(int hayvanId);

    List<AnalysisProductivity> findByTarih(LocalDate tarih);

    List<AnalysisProductivity> findByHayvanIdAndTarihBetween(
            int hayvanId,
            LocalDate start,
            LocalDate end
    );

    List<AnalysisProductivity> findByTarihBetween(LocalDate start, LocalDate end);

    List<AnalysisProductivity> findAllByOrderByTarihAsc();
}