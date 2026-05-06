package com.akilli.ahir.DTO.request;

public class SensorDataRequest {

    private double sicaklik;
    private double nem;
    private int amonyak;
    private double isik; 

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
}