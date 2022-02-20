package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Intervento;



@Repository
public interface InterventoRepository extends CrudRepository<Intervento, Long> {
	
	public List<Intervento> findByTitolo(String titolo);

	public List<Intervento> findByTitoloAndCredential(String titolo, Credentials cred);
	
	public List<Intervento> findByCredential(Credentials cred);
	
	public List<Intervento> findByOrderByTitoloAsc();
	

	


}
