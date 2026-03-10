package com.akilli.ahir.DTO.response;

public class UserResponse {

    private final Long id;
    private final String ad;
    private final String soyad;
    private final String email;

    public UserResponse(Long id, String ad, String soyad, String email) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getEmail() {
        return email;
    }
}