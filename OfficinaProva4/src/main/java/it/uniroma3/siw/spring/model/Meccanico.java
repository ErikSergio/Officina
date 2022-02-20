package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "meccanico")

public class Meccanico {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String cognome;
	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dataDiNascita;
	@Column(nullable = false)
	private String luogoDiNascita;
	@Column
	private String nazionalita;
	
	@OneToMany(mappedBy = "meccanico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Intervento> interventi;
	
	@OneToMany(mappedBy = "meccanico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TipologiaIntervento> tipoInterventi;
	
	@Column(nullable=true)
	private String foto;
	@Column(length=5096)
	private String biografia;

	 @Transient
	    public String getPhotosImagePath() {
	        if (foto.equals(null) || id.equals(null)) return null;
	         
	        return "/uploadable/meccanico-foto/" + id + "/" + foto;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(String luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public List<Intervento> getInterventi() {
		return interventi;
	}

	public void setEsami(List<Intervento> interventi) {
		this.interventi = interventi;
	}

	public List<TipologiaIntervento> getTipoInterventi() {
		return tipoInterventi;
	}

	public void setTipoEsami(List<TipologiaIntervento> tipoInterventi) {
		this.tipoInterventi = tipoInterventi;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	 
	 
}
