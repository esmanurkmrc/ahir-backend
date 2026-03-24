package com.akilli.ahir.DTO.response; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsResponse {
    private double amonyak;
    private double sicaklik;
    private double nem;
    private int riskSkoru;
    private double gunlukSutVerimi;
    private String durumMesaji;
}