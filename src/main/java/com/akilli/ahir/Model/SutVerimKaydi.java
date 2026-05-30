package com.akilli.ahir.Model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sut_verim_kayitlari")
public class SutVerimKaydi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String kupeNo;

    
    private Double sutVerimi;

    
    private Double yemTuketimi;

    
    private LocalDate tarih;

    
    public SutVerimKaydi() {
    }

    
    public SutVerimKaydi(String kupeNo,
                         Double sutVerimi,
                         Double yemTuketimi,
                         LocalDate tarih) {

        this.kupeNo = kupeNo;
        this.sutVerimi = sutVerimi;
        this.yemTuketimi = yemTuketimi;
        this.tarih = tarih;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKupeNo() {
        return kupeNo;
    }

    public void setKupeNo(String kupeNo) {
        this.kupeNo = kupeNo;
    }

    public Double getSutVerimi() {
        return sutVerimi;
    }

    public void setSutVerimi(Double sutVerimi) {
        this.sutVerimi = sutVerimi;
    }

    public Double getYemTuketimi() {
        return yemTuketimi;
    }

    public void setYemTuketimi(Double yemTuketimi) {
        this.yemTuketimi = yemTuketimi;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }
}