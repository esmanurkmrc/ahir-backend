package com.akilli.ahir.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "environment_data")
public class EnvironmentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tarih;
    private LocalTime saat;
    private double sicaklik;
    private double nem;
    private double isik;
    private double amonyak;

    public EnvironmentData() {
    }

    public EnvironmentData(Long id, LocalDate tarih, LocalTime saat, double sicaklik, double nem, double isik, double amonyak) {
        this.id = id;
        this.tarih = tarih;
        this.saat = saat;
        this.sicaklik = sicaklik;
        this.nem = nem;
        this.isik = isik;
        this.amonyak = amonyak;
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
}