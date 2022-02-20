package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "intervento")

public class Intervento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(nullable = false)
	private String titolo;
	@Column(length=5096)
	private String descrizione;
	
	@ManyToOne(fetch = FetchType.EAGER)	
	private TipologiaIntervento tipoIntervento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "meccanico_id")
	private Meccanico meccanico;
	
	
	@JoinColumn(name = "data")
	private LocalDate dataRisultato=LocalDate.now();;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credential_id")
	private Credentials credential;
	
	
	@Column(nullable=true)
	private String foto;
	
	 @Transient
	    public String getPhotosImagePath() {
	        if (foto.equals(null) || id.equals(null)) return null;
	         
	        return "/uploadable/interventi/" + id + "/" + foto;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TipologiaIntervento getTipoIntervento() {
		return tipoIntervento;
	}

	public void setTipoIntervento(TipologiaIntervento tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}

	public Meccanico getMeccanico() {
		return meccanico;
	}

	public void setMeccanico(Meccanico meccanico) {
		this.meccanico = meccanico;
	}

	public LocalDate getDataRisultato() {
		return dataRisultato;
	}

	public void setDataRisultato(LocalDate dataRisultato) {
		this.dataRisultato = dataRisultato;
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	 

}
