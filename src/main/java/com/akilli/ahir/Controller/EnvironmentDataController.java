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
import com.akilli.ahir.Repository.EnvironmentDataRepository;
import com.akilli.ahir.Service.DashboardService;

@RestController
@RequestMapping("/api/environment")
@CrossOrigin(origins = "*")
public class EnvironmentDataController {

    private final EnvironmentDataRepository environmentDataRepository;
    private final DashboardService dashboardService;

    public EnvironmentDataController(EnvironmentDataRepository environmentDataRepository, DashboardService dashboardService) {
        this.environmentDataRepository = environmentDataRepository;
        this.dashboardService = dashboardService;
    }

    
    @GetMapping("/latest")
    public ResponseEntity<EnvironmentData> getLatestData() {
       
        return environmentDataRepository.findAllByOrderByTarihAsc()
                .stream()
                .reduce((first, second) -> second) 
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsResponse> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getLatestDashboardStats());
    }

    @GetMapping
    public List<EnvironmentData> getAll() {
        return environmentDataRepository.findAllByOrderByTarihAsc();
    }

    @GetMapping("/date")
    public List<EnvironmentData> getByDate(@RequestParam String tarih) {
        return environmentDataRepository.findByTarih(LocalDate.parse(tarih));
    }

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