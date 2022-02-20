package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Meccanico;
import it.uniroma3.siw.spring.model.TipologiaIntervento;


public interface TipologiaInterventoRepository extends CrudRepository<TipologiaIntervento, Long> {
	
	public List<TipologiaIntervento> findByNome(String nome);
	
	public List<TipologiaIntervento> findByMeccanico(Meccanico mec);
	
	public List<TipologiaIntervento> findByOrderByNomeAsc();

}
