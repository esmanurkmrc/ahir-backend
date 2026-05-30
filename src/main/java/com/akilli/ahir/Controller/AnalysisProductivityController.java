package com.akilli.ahir.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akilli.ahir.Model.AnalysisProductivity;
import com.akilli.ahir.Repository.AnalysisProductivityRepository;

@RestController
@RequestMapping("/api/analysis-productivity")
@CrossOrigin(origins = "*")
public class AnalysisProductivityController {

    private final AnalysisProductivityRepository analysisProductivityRepository;

    public AnalysisProductivityController(AnalysisProductivityRepository analysisProductivityRepository) {
        this.analysisProductivityRepository = analysisProductivityRepository;
    }

    @GetMapping
    public List<AnalysisProductivity> getAll() {
        return analysisProductivityRepository.findAllByOrderByTarihAsc();
    }

    @GetMapping("/{hayvanId}")
    public List<AnalysisProductivity> getByHayvanId(@PathVariable int hayvanId) {
        return analysisProductivityRepository.findByHayvanId(hayvanId);
    }

    @GetMapping("/date")
    public List<AnalysisProductivity> getByDate(@RequestParam String tarih) {
        return analysisProductivityRepository.findByTarih(LocalDate.parse(tarih));
    }

    @GetMapping("/range")
    public List<AnalysisProductivity> getByRange(
            @RequestParam int hayvanId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        return analysisProductivityRepository.findByHayvanIdAndTarihBetween(
                hayvanId,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }

    @GetMapping("/date-range")
    public List<AnalysisProductivity> getByDateRange(
            @RequestParam String start,
            @RequestParam String end
    ) {
        return analysisProductivityRepository.findByTarihBetween(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
    @PostMapping
public AnalysisProductivity veriEkle(@RequestBody AnalysisProductivity data) {
    return analysisProductivityRepository.save(data);
}
}