package com.akilli.ahir.Service;

import com.akilli.ahir.DTO.request.SensorDataRequest;
import com.akilli.ahir.Model.SensorData;
import com.akilli.ahir.Repository.SensorDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;

    public SensorDataService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    
    public SensorData veriKaydet(SensorDataRequest request) {
        SensorData data = new SensorData();

        data.setSicaklik(request.getSicaklik());
        data.setNem(request.getNem());
        data.setAmonyak(request.getAmonyak());
        data.setIsik(request.getIsik());
        data.setZaman(LocalDateTime.now());

        return sensorDataRepository.save(data);
    }

    
    public List<SensorData> tumVeriler() {
        return sensorDataRepository.findAll();
    }

    
    public SensorData veriGetir(Long id) {
        return sensorDataRepository.findById(id).orElse(null);
    }

    
    public void veriSil(Long id) {
        sensorDataRepository.deleteById(id);
    }

   
    public SensorData sonVeriGetir() {
        return sensorDataRepository.findTopByOrderByZamanDesc();
    }
}