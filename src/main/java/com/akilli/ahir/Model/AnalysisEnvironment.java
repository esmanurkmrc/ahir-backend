package com.akilli.ahir.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "analysis_environment")
public class AnalysisEnvironment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tarih;

    private LocalTime saat;

    private double sicaklik;

    private double nem;

    private double isik;

    private double amonyak;

    private double thi;

    private String durum;

    public AnalysisEnvironment() {
    }

    public AnalysisEnvironment(
            Long id,
            LocalDate tarih,
            LocalTime saat,
            double sicaklik,
            double nem,
            double isik,
            double amonyak,
            double thi,
            String durum
    ) {
        this.id = id;
        this.tarih = tarih;
        this.saat = saat;
        this.sicaklik = sicaklik;
        this.nem = nem;
        this.isik = isik;
        this.amonyak = amonyak;
        this.thi = thi;
        this.durum = durum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public LocalTime getSaat() {
        return saat;
    }

    public void setSaat(LocalTime saat) {
        this.saat = saat;
    }

    public double getSicaklik() {
        return sicaklik;
    }

    public void setSicaklik(double sicaklik) {
        this.sicaklik = sicaklik;
    }

    public double getNem() {
        return nem;
    }

    public void setNem(double nem) {
        this.nem = nem;
    }

    public double getIsik() {
        return isik;
    }

    public void setIsik(double isik) {
        this.isik = isik;
    }

    public double getAmonyak() {
        return amonyak;
    }

    public void setAmonyak(double amonyak) {
        this.amonyak = amonyak;
    }

    public double getThi() {
        return thi;
    }

    public void setThi(double thi) {
        this.thi = thi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}