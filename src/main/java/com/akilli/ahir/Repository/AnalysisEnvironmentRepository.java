package com.akilli.ahir.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akilli.ahir.Model.AnalysisEnvironment;

public interface AnalysisEnvironmentRepository extends JpaRepository<AnalysisEnvironment, Long> {

    Optional<AnalysisEnvironment> findFirstByOrderByIdDesc();

    List<AnalysisEnvironment> findAllByOrderByTarihAsc();

    List<AnalysisEnvironment> findByTarih(LocalDate tarih);

    List<AnalysisEnvironment> findByTarihBetween(LocalDate start, LocalDate end);
}