package com.akilli.ahir.Controller;

import com.akilli.ahir.Model.Hayvan;
import com.akilli.ahir.Service.HayvanService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hayvanlar")
@CrossOrigin(origins = "http://localhost:3000") 
public class HayvanController {

    private final HayvanService hayvanService;

    public HayvanController(HayvanService hayvanService) {
        this.hayvanService = hayvanService;
    }

   
    @GetMapping
    public List<Hayvan> tumHayvanlar() {
        return hayvanService.tumHayvanlar();
    }

    
    @PostMapping("/{tur}")
    public Hayvan ekle(@PathVariable String tur, @RequestBody Hayvan hayvan) {
        return hayvanService.hayvanKaydet(tur, hayvan);
    }

   
    @PutMapping("/{id}")
    public Hayvan guncelle(@PathVariable Long id, @RequestBody Hayvan hayvan) {
        return hayvanService.hayvanGuncelle(id, hayvan);
    }

   
    @DeleteMapping("/{id}")
    public void sil(@PathVariable Long id) {
        hayvanService.hayvanSil(id);
    }
}