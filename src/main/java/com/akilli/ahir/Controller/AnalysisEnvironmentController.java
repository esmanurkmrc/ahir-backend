package com.akilli.ahir.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akilli.ahir.Model.AnalysisEnvironment;
import com.akilli.ahir.Repository.AnalysisEnvironmentRepository;

@RestController
@RequestMapping("/api/analysis-environment")
@CrossOrigin(origins = "*")
public class AnalysisEnvironmentController {

    private final AnalysisEnvironmentRepository analysisEnvironmentRepository;

    public AnalysisEnvironmentController(AnalysisEnvironmentRepository analysisEnvironmentRepository) {
        this.analysisEnvironmentRepository = analysisEnvironmentRepository;
    }

    @GetMapping("/latest")
    public ResponseEntity<AnalysisEnvironment> getLatestData() {
        return analysisEnvironmentRepository.findFirstByOrderByIdDesc()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AnalysisEnvironment> getAll() {
        return analysisEnvironmentRepository.findAllByOrderByTarihAsc();
    }

    @GetMapping("/date")
    public List<AnalysisEnvironment> getByDate(@RequestParam String tarih) {
        return analysisEnvironmentRepository.findByTarih(LocalDate.parse(tarih));
    }

    @GetMapping("/range")
    public List<AnalysisEnvironment> getByRange(
            @RequestParam String start,
            @RequestParam String end
    ) {
        return analysisEnvironmentRepository.findByTarihBetween(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}