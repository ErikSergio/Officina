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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "prenotazionee")

public class Prenotazione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)	
	private TipologiaIntervento tipoIntervento;
	
	@JoinColumn(name = "dataPrenotazione")
	private LocalDate dataPrenotazione=LocalDate.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "credential_id")
	private Credentials credential;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public TipologiaIntervento getTipoIntervento() {
		return tipoIntervento;
	}

	public void setTipoIntervento(TipologiaIntervento tipoIntervento) {
		this.tipoIntervento = tipoIntervento;
	}



	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public Credentials getCredential() {
		return credential;
	}

	public void setCredential(Credentials credential) {
		this.credential = credential;
	}

	


}
