package com.azienda.gestautomezz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Filiale")
public class Filiale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codice;
    
    private String indirizzo;
    private String città;
    private String cap;
    
    @OneToMany(mappedBy = "filiale", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Automezzo> automezzi;
	
    
    public Long getCodice() {
		return codice;
	}
	public void setCodice(Long codice) {
		this.codice = codice;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCittà() {
		return città;
	}
	public void setCittà(String città) {
		this.città = città;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public List<Automezzo> getAutomezzi() {
		return automezzi;
	}
	public void setAutomezzi(List<Automezzo> automezzi) {
		this.automezzi = automezzi;
	}
	



}
