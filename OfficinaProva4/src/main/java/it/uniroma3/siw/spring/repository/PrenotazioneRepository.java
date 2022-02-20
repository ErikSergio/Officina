package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Prenotazione;



@Repository
public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long> {
	
	public List<Prenotazione> findByCredential(Credentials cred);

	public List<Prenotazione> findByIdAndCredential(Long id, Credentials cred);
	
}
