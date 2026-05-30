package com.akilli.ahir.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "analysis_productivity")
public class AnalysisProductivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tarih;

    private LocalTime saat;

    private int hayvanId;

    private double yemTuketimi;

    private double sutVerimi;

    private String durum;

    public AnalysisProductivity() {
    }

    public AnalysisProductivity(
            Long id,
            LocalDate tarih,
            LocalTime saat,
            int hayvanId,
            double yemTuketimi,
            double sutVerimi,
            String durum
    ) {
        this.id = id;
        this.tarih = tarih;
        this.saat = saat;
        this.hayvanId = hayvanId;
        this.yemTuketimi = yemTuketimi;
        this.sutVerimi = sutVerimi;
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

    public int getHayvanId() {
        return hayvanId;
    }

    public void setHayvanId(int hayvanId) {
        this.hayvanId = hayvanId;
    }

    public double getYemTuketimi() {
        return yemTuketimi;
    }

    public void setYemTuketimi(double yemTuketimi) {
        this.yemTuketimi = yemTuketimi;
    }

    public double getSutVerimi() {
        return sutVerimi;
    }

    public void setSutVerimi(double sutVerimi) {
        this.sutVerimi = sutVerimi;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}