package com.akilli.ahir.Repository; // Paket isminin baş harfi büyük 'R'

import com.akilli.ahir.Model.Hayvan; // Model isminin baş harfi büyük 'M'
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface HayvanRepository extends JpaRepository<Hayvan, Long> {

    // 1. Küpe Numarasına göre tek bir hayvan bulma
    Optional<Hayvan> findByKupeNo(String kupeNo);

    // 2. Belirli bir durumdaki (Örn: "Sağlıklı") hayvanları listeleme
    List<Hayvan> findByDurum(String durum);

    // 3. Küpe No'nun sistemde olup olmadığını kontrol etme
    boolean existsByKupeNo(String kupeNo);
}