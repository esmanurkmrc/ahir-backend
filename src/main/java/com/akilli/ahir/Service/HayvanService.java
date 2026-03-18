package com.akilli.ahir.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.akilli.ahir.Factory.HayvanFactory;
import com.akilli.ahir.Model.Hayvan;
import com.akilli.ahir.Repository.HayvanRepository;

@Service
public class HayvanService {

    private final HayvanRepository hayvanRepository;
    private final HayvanFactory hayvanFactory;

    public HayvanService(HayvanRepository hayvanRepository, HayvanFactory hayvanFactory) {
        this.hayvanRepository = hayvanRepository;
        this.hayvanFactory = hayvanFactory;
    }

    // TÜM HAYVANLARI LİSTELE
    public List<Hayvan> tumHayvanlar() {
        return hayvanRepository.findAll();
    }

    // YENİ HAYVAN EKLE (Factory Kullanarak)
    public Hayvan hayvanKaydet(String tur, Hayvan data) {

        if (hayvanRepository.existsByKupeNo(data.getKupeNo())) {
            throw new RuntimeException("Bu Küpe No zaten kayıtlı!");
        }

        // Factory ile hayvan oluştur
        Hayvan yeniHayvan = hayvanFactory.createHayvan(tur);

        // Verileri aktar
        yeniHayvan.setKupeNo(data.getKupeNo());
        yeniHayvan.setIrk(data.getIrk());
        yeniHayvan.setCinsiyet(data.getCinsiyet());
        yeniHayvan.setDogumTarihi(data.getDogumTarihi());
        yeniHayvan.setDurum(data.getDurum());
        yeniHayvan.setSonAgirlik(data.getSonAgirlik());
        yeniHayvan.setGunlukSutVerimi(data.getGunlukSutVerimi());
        yeniHayvan.setAsiTakvimi(data.getAsiTakvimi());

        return hayvanRepository.save(yeniHayvan);
    }

    // HAYVAN GÜNCELLE
    public Hayvan hayvanGuncelle(Long id, Hayvan data) {

        Hayvan hayvan = hayvanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı"));

        hayvan.setKupeNo(data.getKupeNo());
        hayvan.setIrk(data.getIrk());
        hayvan.setCinsiyet(data.getCinsiyet());
        hayvan.setDogumTarihi(data.getDogumTarihi());
        hayvan.setDurum(data.getDurum());
        hayvan.setSonAgirlik(data.getSonAgirlik());
        hayvan.setGunlukSutVerimi(data.getGunlukSutVerimi());
        hayvan.setAsiTakvimi(data.getAsiTakvimi());

        return hayvanRepository.save(hayvan);
    }

    // HAYVAN SİL
    public void hayvanSil(Long id) {
        hayvanRepository.deleteById(id);
    }
}