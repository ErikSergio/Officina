package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "tipoInterventi")

public class TipologiaIntervento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String nome;
	@Column(length=5096)
	private String descrizione;
	
	@Column
	private int costo;
	
	@OneToMany(mappedBy = "tipoIntervento", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Intervento> interventi;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meccanico_id")
	private Meccanico meccanico;
	
	public TipologiaIntervento(String n, String d) {
		this.nome = n;
		this.descrizione = d;
	}

	public TipologiaIntervento() {
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Intervento> getInterventi() {
		return interventi;
	}

	public void setInterventi(List<Intervento> interventi) {
		this.interventi = interventi;
	}

	public Meccanico getMeccanico() {
		return meccanico;
	}

	public void setMeccanico(Meccanico meccanico) {
		this.meccanico = meccanico;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}
	

}
