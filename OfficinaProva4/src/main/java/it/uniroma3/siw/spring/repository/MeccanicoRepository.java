package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Meccanico;



public interface MeccanicoRepository extends CrudRepository<Meccanico, Long> {
	
	public List<Meccanico> findByCognome(String cognome);
	
	public List<Meccanico> findByNome(String nome);
	
	public List<Meccanico> findByNazionalita(String nazionalita);

	public List<Meccanico> findByNomeAndCognome(String nome, String cognome);

	//riporta tutti i medici ordinati con cognomi in ordine alfabetico
	public List<Meccanico> findByOrderByCognomeAsc();
	
}
