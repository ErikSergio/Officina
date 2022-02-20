package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Meccanico;
import it.uniroma3.siw.spring.service.MeccanicoService;



@Component
public class MeccanicoValidator implements Validator {
	
	@Autowired
	private MeccanicoService meccanicoService;
	
    private static final Logger logger = LoggerFactory.getLogger(InterventoValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "luogoDiNascita", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nazionalita", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.meccanicoService.alreadyExists((Meccanico)o)) {
				logger.debug("e' un duplicato");
				errors.reject("meccanico.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Meccanico.class.equals(aClass);
	}


}
