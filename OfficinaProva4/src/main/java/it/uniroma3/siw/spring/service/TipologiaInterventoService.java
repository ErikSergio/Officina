package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Meccanico;
import it.uniroma3.siw.spring.model.TipologiaIntervento;
import it.uniroma3.siw.spring.repository.TipologiaInterventoRepository;


@Service
public class TipologiaInterventoService {
	
	@Autowired
	private TipologiaInterventoRepository tipologiaInterventoRepository;
	
	@Transactional
	public TipologiaIntervento inserisci(TipologiaIntervento ti) {
		return tipologiaInterventoRepository.save(ti);
	}

	@Transactional
	public List<TipologiaIntervento> tutti() {
		return (List<TipologiaIntervento>) tipologiaInterventoRepository.findAll();
	}
	
	@Transactional
	public List<TipologiaIntervento> tuttiOrdinati() {
		return tipologiaInterventoRepository.findByOrderByNomeAsc();
	}

	@Transactional
	public TipologiaIntervento tipoInterventoPerId(Long id) {
		Optional<TipologiaIntervento> optional = tipologiaInterventoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	
	@Transactional
	public List<TipologiaIntervento> perMeccanico(Meccanico mec) {
		return (List<TipologiaIntervento>) tipologiaInterventoRepository.findByMeccanico(mec);
	}

	
	@Transactional
	public boolean alreadyExists(TipologiaIntervento tip) {
		List<TipologiaIntervento> collezioni = this.tipologiaInterventoRepository.findByNome(tip.getNome());
		if (collezioni.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.tipologiaInterventoRepository.deleteById(id);
	}
	
	

}
