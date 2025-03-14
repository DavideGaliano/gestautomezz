package com.azienda.gestautomezz.model;

import java.util.List;

public class FilialeRequest {
    private String email;
    private List<Filiale> filiali;

    // Costruttore
    public FilialeRequest(String email, List<Filiale> filiali) {
        this.email = email;
        this.filiali = filiali;
    }

    // Getter e Setter
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Filiale> getFiliali() {
        return filiali;
    }

    public void setFiliali(List<Filiale> filiali) {
        this.filiali = filiali;
    }
}
