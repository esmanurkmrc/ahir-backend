package com.akilli.ahir.Controller;

import com.akilli.ahir.Model.SutVerimKaydi;
import com.akilli.ahir.Service.SutVerimKaydiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sut-verim")
@CrossOrigin(origins = "*")
public class SutVerimKaydiController {

    @Autowired
    private SutVerimKaydiService sutVerimKaydiService;

    @PostMapping
    public SutVerimKaydi kaydet(@RequestBody SutVerimKaydi kayit) {
        return sutVerimKaydiService.kaydet(kayit);
    }

    @GetMapping
    public List<SutVerimKaydi> tumKayitlariGetir() {
        return sutVerimKaydiService.tumKayitlariGetir();
    }

    @GetMapping("/liste/son50")
    public List<SutVerimKaydi> son50KayitGetir() {
        return sutVerimKaydiService.son50KayitGetir();
    }

    @GetMapping("/id/{id}")
    public SutVerimKaydi idIleGetir(@PathVariable Long id) {
        return sutVerimKaydiService
                .idIleGetir(id)
                .orElse(null);
    }

    @DeleteMapping("/sil/{id}")
    public void sil(@PathVariable Long id) {
        sutVerimKaydiService.sil(id);
    }
}