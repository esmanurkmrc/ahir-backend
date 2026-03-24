package com.akilli.ahir.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "animal_productivity")
public class AnimalProductivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate tarih;
    private int hayvanId;
    private double yemTuketimi;
    private double sutVerimi;

    public AnimalProductivity() {
    }

    public AnimalProductivity(Long id, LocalDate tarih, int hayvanId, double yemTuketimi, double sutVerimi) {
        this.id = id;
        this.tarih = tarih;
        this.hayvanId = hayvanId;
        this.yemTuketimi = yemTuketimi;
        this.sutVerimi = sutVerimi;
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
}