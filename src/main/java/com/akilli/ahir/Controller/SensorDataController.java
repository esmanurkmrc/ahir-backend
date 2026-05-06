package com.akilli.ahir.Controller;

import com.akilli.ahir.DTO.request.SensorDataRequest;
import com.akilli.ahir.Model.SensorData;
import com.akilli.ahir.Service.SensorDataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensor-data")
@CrossOrigin(origins = "http://localhost:3000")
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

   
    @PostMapping
    public SensorData veriEkle(@RequestBody SensorDataRequest request) {
        return sensorDataService.veriKaydet(request);
    }

    
    @GetMapping
    public List<SensorData> tumVerileriGetir() {
        return sensorDataService.tumVeriler();
    }

   
    @GetMapping("/{id}")
    public SensorData veriGetir(@PathVariable Long id) {
        return sensorDataService.veriGetir(id);
    }

    
    @DeleteMapping("/{id}")
    public String veriSil(@PathVariable Long id) {
        sensorDataService.veriSil(id);
        return "Veri silindi: " + id;
    }

    
    @GetMapping("/son")
    public SensorData sonVeriGetir() {
        return sensorDataService.sonVeriGetir();
    }
}