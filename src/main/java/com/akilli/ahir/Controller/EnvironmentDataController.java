package com.akilli.ahir.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akilli.ahir.DTO.response.DashboardStatsResponse;
import com.akilli.ahir.Model.EnvironmentData;
import com.akilli.ahir.Repository.EnvironmentDataRepository; // Yeni servisi import et
import com.akilli.ahir.Service.DashboardService; // DTO'yu import et

@RestController
@RequestMapping("/api/environment")
@CrossOrigin(origins = "*")
public class EnvironmentDataController {

    private final EnvironmentDataRepository environmentDataRepository;
    private final DashboardService dashboardService; // Servisi tanımla

    // Constructor'a DashboardService'i ekle
    public EnvironmentDataController(EnvironmentDataRepository environmentDataRepository, DashboardService dashboardService) {
        this.environmentDataRepository = environmentDataRepository;
        this.dashboardService = dashboardService;
    }

    // 🚀 YENİ EKLENEN: Sidebar Kartları İçin Canlı İstatistikler
    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getLatestDashboardStats());
    }

    // Tüm veriler (sıralı)
    @GetMapping
    public List<EnvironmentData> getAll() {
        return environmentDataRepository.findAllByOrderByTarihAsc();
    }

    // Belirli tarih
    @GetMapping("/date")
    public List<EnvironmentData> getByDate(@RequestParam String tarih) {
        return environmentDataRepository.findByTarih(LocalDate.parse(tarih));
    }

    // Tarih aralığı
    @GetMapping("/range")
    public List<EnvironmentData> getByRange(
            @RequestParam String start,
            @RequestParam String end) {

        return environmentDataRepository.findByTarihBetween(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}