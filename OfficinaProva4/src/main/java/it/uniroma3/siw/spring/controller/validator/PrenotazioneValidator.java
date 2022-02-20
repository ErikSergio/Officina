package it.uniroma3.siw.spring.controller.validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.InterventoService;
import it.uniroma3.siw.spring.service.PrenotazioneService;



@Component
public class PrenotazioneValidator implements Validator {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
    private static final Logger logger = LoggerFactory.getLogger(PrenotazioneValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoIntervento", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.prenotazioneService.alreadyExists(( Prenotazione)o)) {
				logger.debug("e' un duplicato");
				errors.reject("intervento.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Intervento.class.equals(aClass);
	}

}
