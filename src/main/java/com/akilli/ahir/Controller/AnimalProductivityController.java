package com.akilli.ahir.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akilli.ahir.Model.AnimalProductivity;
import com.akilli.ahir.Repository.AnimalProductivityRepository;

@RestController
@RequestMapping("/api/productivity")
@CrossOrigin(origins = "*")
public class AnimalProductivityController {

    private final AnimalProductivityRepository animalProductivityRepository;

    public AnimalProductivityController(AnimalProductivityRepository animalProductivityRepository) {
        this.animalProductivityRepository = animalProductivityRepository;
    }

    // Tüm veriler
    @GetMapping
    public List<AnimalProductivity> getAll() {
        return animalProductivityRepository.findAll();
    }

    // Hayvana göre
    @GetMapping("/{hayvanId}")
    public List<AnimalProductivity> getByHayvanId(@PathVariable int hayvanId) {
        return animalProductivityRepository.findByHayvanId(hayvanId);
    }

    // Belirli tarih
    @GetMapping("/date")
    public List<AnimalProductivity> getByDate(@RequestParam String tarih) {
        return animalProductivityRepository.findByTarih(LocalDate.parse(tarih));
    }

    // Hayvan + tarih aralığı
    @GetMapping("/range")
    public List<AnimalProductivity> getByRange(
            @RequestParam int hayvanId,
            @RequestParam String start,
            @RequestParam String end) {

        return animalProductivityRepository.findByHayvanIdAndTarihBetween(
                hayvanId,
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}