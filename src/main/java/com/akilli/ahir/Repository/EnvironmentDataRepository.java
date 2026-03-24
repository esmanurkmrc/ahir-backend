package com.akilli.ahir.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional; // Bunu import etmeyi unutma

import org.springframework.data.jpa.repository.JpaRepository;
import com.akilli.ahir.Model.EnvironmentData;

public interface EnvironmentDataRepository extends JpaRepository<EnvironmentData, Long> {

    // ✅ EKLEDİĞİMİZ METOD: En güncel veriyi sidebar kartları için çekeceğiz
    Optional<EnvironmentData> findFirstByOrderByIdDesc();

    // Tüm verileri tarihe göre sıralı getir
    List<EnvironmentData> findAllByOrderByTarihAsc();

    // Belirli tarihe göre veri
    List<EnvironmentData> findByTarih(LocalDate tarih);

    // Tarih aralığına göre veri
    List<EnvironmentData> findByTarihBetween(LocalDate start, LocalDate end);
}