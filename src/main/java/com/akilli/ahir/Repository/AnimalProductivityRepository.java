package com.akilli.ahir.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.akilli.ahir.Model.AnimalProductivity;

public interface AnimalProductivityRepository extends JpaRepository<AnimalProductivity, Long> {

    // Belirli bir hayvanın tüm geçmişi
    List<AnimalProductivity> findByHayvanId(int hayvanId);

    // Belirli bir gündeki tüm ahır verisi
    List<AnimalProductivity> findByTarih(LocalDate tarih);

    // Belirli bir hayvanın tarih aralığındaki performansı
    List<AnimalProductivity> findByHayvanIdAndTarihBetween(int hayvanId, LocalDate start, LocalDate end);

    // YENİ: Seçilen tarihler arasındaki TÜM hayvanların verimi (Genel Rapor için)
    List<AnimalProductivity> findByTarihBetween(LocalDate start, LocalDate end);
}