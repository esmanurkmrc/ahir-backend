package com.akilli.ahir.Service;

import com.akilli.ahir.Model.SutVerimKaydi;
import com.akilli.ahir.Repository.SutVerimKaydiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SutVerimKaydiService {

    @Autowired
    private SutVerimKaydiRepository sutVerimKaydiRepository;

    public SutVerimKaydi kaydet(SutVerimKaydi kayit) {
        return sutVerimKaydiRepository.save(kayit);
    }

    public List<SutVerimKaydi> tumKayitlariGetir() {
        return sutVerimKaydiRepository.findAll();
    }

    public Optional<SutVerimKaydi> idIleGetir(Long id) {
        return sutVerimKaydiRepository.findById(id);
    }

    public void sil(Long id) {
        sutVerimKaydiRepository.deleteById(id);
    }

    public List<SutVerimKaydi> son50KayitGetir() {
        return sutVerimKaydiRepository.findTop50ByOrderByTarihDesc();
    }
}