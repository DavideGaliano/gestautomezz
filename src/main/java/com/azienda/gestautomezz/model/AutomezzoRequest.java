package com.azienda.gestautomezz.model;

import java.util.List;

public class AutomezzoRequest {
    private String email;
    private List<Automezzo> automezzi;

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Automezzo> getAutomezzi() {
        return automezzi;
    }

    public void setAutomezzi(List<Automezzo> automezzi) {
        this.automezzi = automezzi;
    }
}