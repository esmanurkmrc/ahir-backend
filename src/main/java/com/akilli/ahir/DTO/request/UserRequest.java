package com.akilli.ahir.DTO.request;

public class UserRequest {
    private String ad;
    private String soyad;
    private String email;
    private String sifre;

    
    public String getAd() { 
        return ad; 
    }
    public void setAd(String ad) {
         this.ad = ad;
        
        }
    public String getSoyad() { 
        return soyad; }
    public void setSoyad(String soyad) { 
        this.soyad = soyad; }

    public String getEmail() {
         return email;

     }
    public void setEmail(String email) {
         this.email = email; 
        }

    public String getSifre() { 
        return sifre; }
    public void setSifre(String sifre) {
         this.sifre = sifre; 
        }
}