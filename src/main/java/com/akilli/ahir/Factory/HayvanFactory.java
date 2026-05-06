package com.akilli.ahir.Factory;


import com.akilli.ahir.Model.Hayvan; 

import org.springframework.stereotype.Component;

@Component
public class HayvanFactory {
    
    public Hayvan createHayvan(String tur) {
        if (tur == null || tur.isEmpty()) {
            return new Hayvan(); 
        }
        return new Hayvan(); 
    }
}