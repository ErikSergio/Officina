package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Meccanico;
import it.uniroma3.siw.spring.repository.MeccanicoRepository;



@Service
public class MeccanicoService {
	
	@Autowired
	private MeccanicoRepository meccanicoRepository; 
	
	@Transactional
	public Meccanico inserisci(Meccanico meccanico) {
		return meccanicoRepository.save(meccanico);
	}

	@Transactional
	public List<Meccanico> tutti() {
		return (List<Meccanico>) meccanicoRepository.findAll();
	}
	
	@Transactional
	public List<Meccanico> tuttiOrdinati() {
		return this.meccanicoRepository.findByOrderByCognomeAsc();
	}

	@Transactional
	public Meccanico meccanicoPerId(Long id) {
		Optional<Meccanico> optional = meccanicoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Meccanico meccanico) {
		List<Meccanico> artisti = this.meccanicoRepository.findByNomeAndCognome(meccanico.getNome(), meccanico.getCognome());
		if (artisti.size() > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public void cancella(Long id) {
		this.meccanicoRepository.deleteById(id);
	}
	

}
