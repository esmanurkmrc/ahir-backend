package com.akilli.ahir.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hayvanlar")
public class Hayvan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String kupeNo;

    private String irk;
    private String cinsiyet;
    private LocalDate dogumTarihi;
    private String durum; 
    private Double sonAgirlik;
    private Double gunlukSutVerimi;

    @Column(length = 1000)
    private String asiTakvimi;



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKupeNo() { return kupeNo; }
    public void setKupeNo(String kupeNo) { this.kupeNo = kupeNo; }

    public String getIrk() { return irk; }
    public void setIrk(String irk) { this.irk = irk; }

    public String getCinsiyet() { return cinsiyet; }
    public void setCinsiyet(String cinsiyet) { this.cinsiyet = cinsiyet; }

    public LocalDate getDogumTarihi() { return dogumTarihi; }
    public void setDogumTarihi(LocalDate dogumTarihi) { this.dogumTarihi = dogumTarihi; }

    public String getDurum() { return durum; }
    public void setDurum(String durum) { this.durum = durum; }

    public Double getSonAgirlik() { return sonAgirlik; }
    public void setSonAgirlik(Double sonAgirlik) { this.sonAgirlik = sonAgirlik; }

    public Double getGunlukSutVerimi() { return gunlukSutVerimi; }
    public void setGunlukSutVerimi(Double gunlukSutVerimi) { this.gunlukSutVerimi = gunlukSutVerimi; }

    public String getAsiTakvimi() { return asiTakvimi; }
    public void setAsiTakvimi(String asiTakvimi) { this.asiTakvimi = asiTakvimi; }
}