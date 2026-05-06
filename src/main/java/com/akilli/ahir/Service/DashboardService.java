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
       
        Optional<EnvironmentData> latestOpt = environmentDataRepository.findFirstByOrderByIdDesc();
        
        if (latestOpt.isEmpty()) {
            return new DashboardStatsResponse(0.0, 0.0, 0.0, 0, 0.0, "Veri Bekleniyor...");
        }

        EnvironmentData latest = latestOpt.get();

       
        double sonSutVerimi = 412.0; 

        
        int risk = 0;
        if (latest.getAmonyak() > 25) risk += 50; 
        if (latest.getSicaklik() > 28) risk += 25; 
        if (latest.getNem() > 80) risk += 25;      
        
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