package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.repository.InterventoRepository;
import it.uniroma3.siw.spring.repository.PrenotazioneRepository;



@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@Transactional
	public Prenotazione inserisci(Prenotazione pr) {
		return this.prenotazioneRepository.save(pr);
	}
	
	@Transactional
	public List<Prenotazione> tutti() {
		return (List<Prenotazione>) prenotazioneRepository.findAll();
	}
	
	
	
	@Transactional
	public List<Prenotazione> perCliente(Credentials cli){
		return (List<Prenotazione>) prenotazioneRepository.findByCredential(cli);
	}
	
	@Transactional
	public Prenotazione prenotazionePerId(Long id) {
		Optional<Prenotazione> optional = prenotazioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	
	
	@Transactional 
	public boolean alreadyExists(Prenotazione op) {
		List<Prenotazione> prenotazioni = this.prenotazioneRepository.findByIdAndCredential(op.getId(), op.getCredential());
		if (prenotazioni.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.prenotazioneRepository.deleteById(id);
	}

}
