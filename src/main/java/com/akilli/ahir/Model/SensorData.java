package com.akilli.ahir.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double sicaklik;
    private double nem;
    private int amonyak;

    private double isik; 

    private LocalDateTime zaman;

    public SensorData() {
    }

    public SensorData(double sicaklik, double nem, int amonyak, double isik, LocalDateTime zaman) {
        this.sicaklik = sicaklik;
        this.nem = nem;
        this.amonyak = amonyak;
        this.isik = isik; 
        this.zaman = zaman;
    }

    public Long getId() {
        return id;
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

    public int getAmonyak() {
        return amonyak;
    }

    public void setAmonyak(int amonyak) {
        this.amonyak = amonyak;
    }

    
    public double getIsik() {
        return isik;
    }

    public void setIsik(double isik) {
        this.isik = isik;
    }

    public LocalDateTime getZaman() {
        return zaman;
    }

    public void setZaman(LocalDateTime zaman) {
        this.zaman = zaman;
    }
}