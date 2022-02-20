package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.repository.InterventoRepository;



@Service
public class InterventoService {
	
	@Autowired
	private InterventoRepository interventoRepository;
	
	@Transactional
	public Intervento inserisci(Intervento in) {
		return this.interventoRepository.save(in);
	}
	
	@Transactional
	public List<Intervento> tutti() {
		return (List<Intervento>) interventoRepository.findAll();
	}
	
	@Transactional
	public List<Intervento> tuttiOrdinatiPerTitolo() {
		return this.interventoRepository.findByOrderByTitoloAsc();
	}
	
	@Transactional
	public List<Intervento> perCliente(Credentials cli){
		return (List<Intervento>) interventoRepository.findByCredential(cli);
	}
	
	@Transactional
	public Intervento interventoPerId(Long id) {
		Optional<Intervento> optional = interventoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	
	
	@Transactional 
	public boolean alreadyExists(Intervento op) {
		List<Intervento> interventi = this.interventoRepository.findByTitoloAndCredential(op.getTitolo(), op.getCredential());
		if (interventi.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.interventoRepository.deleteById(id);
	}

}
