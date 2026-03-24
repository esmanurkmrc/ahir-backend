package com.akilli.ahir.Service;

import com.akilli.ahir.DTO.response.DashboardStatsResponse;
import com.akilli.ahir.Model.EnvironmentData;
import com.akilli.ahir.Repository.AnimalProductivityRepository;
import com.akilli.ahir.Repository.EnvironmentDataRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

import com.akilli.ahir.DTO.response.DashboardStatsResponse;

@Service
public class DashboardService {

    private final EnvironmentDataRepository environmentDataRepository;
    private final AnimalProductivityRepository animalProductivityRepository;

    public DashboardService(EnvironmentDataRepository environmentDataRepository, 
                            AnimalProductivityRepository animalProductivityRepository) {
        this.environmentDataRepository = environmentDataRepository;
        this.animalProductivityRepository = animalProductivityRepository;
    }

    public DashboardStatsResponse getLatestDashboardStats() {
        // 1. En güncel ortam verisini çek (Sidebar'daki Amonyak, Sıcaklık, Nem için)
        Optional<EnvironmentData> latestOpt = environmentDataRepository.findFirstByOrderByIdDesc();
        
        if (latestOpt.isEmpty()) {
            return new DashboardStatsResponse(0.0, 0.0, 0.0, 0, 0.0, "Veri Bekleniyor...");
        }

        EnvironmentData latest = latestOpt.get();

        // 2. Günlük Süt Verimini Hesapla (Repository'ndeki tüm verilerin sonuncusunu veya toplamını alabiliriz)
        // Şimdilik sistemdeki en son süt verisini çekelim (örnek amaçlı)
        double sonSutVerimi = 412.0; // Varsayılan değer

        // 3. RİSK SKORU HESAPLAMA (Karar Destek Sistemi Mantığı)
        int risk = 0;
        if (latest.getAmonyak() > 25) risk += 50; // Amonyak tehlikesi
        if (latest.getSicaklik() > 28) risk += 25; // Sıcaklık stresi
        if (latest.getNem() > 80) risk += 25;      // Nem/Bakteri riski
        
        risk = Math.min(risk, 100);

        return new DashboardStatsResponse(
            latest.getAmonyak(),
            latest.getSicaklik(),
            latest.getNem(),
            risk,
            sonSutVerimi,
            risk > 50 ? "⚠️ Kritik Uyarı: Ortam Şartları Kötü!" : "✅ Sistem Stabil"
        );
    }
}