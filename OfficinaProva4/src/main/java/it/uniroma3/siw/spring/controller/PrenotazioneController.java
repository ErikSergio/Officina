package it.uniroma3.siw.spring.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.controller.validator.InterventoValidator;
import it.uniroma3.siw.spring.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.spring.misc.FileUploadUtil;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.model.Prenotazione;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.InterventoService;
import it.uniroma3.siw.spring.service.MeccanicoService;
import it.uniroma3.siw.spring.service.PrenotazioneService;
import it.uniroma3.siw.spring.service.TipologiaInterventoService;




@Controller
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
    @Autowired
    private PrenotazioneValidator prenotazioneValidator;
    
    
    @Autowired
    private TipologiaInterventoService tipologiaInterventoService;

    @Autowired
	private CredentialsService credentialsService;


    
  
    
   
    @RequestMapping(value="/prenotazione", method = RequestMethod.GET)
    public String addPrenotazione(Model model) {
    	model.addAttribute("prenotazione", new Prenotazione());
    	model.addAttribute("credentials", this.credentialsService.tuttiOrdinati());
    	model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
        return "prenotazioneForm";
    }

   
    @RequestMapping(value = "/prenotazione/{id}", method = RequestMethod.GET)
    public String getPrenotazione(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("prenotazione", this.prenotazioneService.prenotazionePerId(id));
    	return "prenotazione";
    }
    
  
    @RequestMapping(value = "/admin/prenotazione/{id}", method = RequestMethod.GET)
    public String deletePrenotazione(@PathVariable("id") Long id, Model model) {
    	this.prenotazioneService.cancella(id);
    	model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
    	return "prenotazioni";
    }

 
    @RequestMapping(value = "/prenotazioni", method = RequestMethod.GET)
    public String getPrenotazioni(Model model) {
    		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    			model.addAttribute("prenotazioni", this.prenotazioneService.tutti());
    			return "prenotazioni";

    		}
    		model.addAttribute("credentials",this.credentialsService.getCredentials(userDetails.getUsername()));
    		model.addAttribute("prenotazioni", this.prenotazioneService.perCliente(this.credentialsService.getCredentials(userDetails.getUsername())));
    		return "prenotazioni";
    }

    @RequestMapping(value = "/prenotazione", method = RequestMethod.POST)
    public String addPrenotazione(@ModelAttribute("prenotazione") Prenotazione prenotazione, 
    									Model model, BindingResult bindingResult) throws IOException {
    	this.prenotazioneValidator.validate(prenotazione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.prenotazioneService.inserisci(prenotazione);
            return "prenotazioneSuccessful";
        }
      
        model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
        return "prenotazioneForm";
    }
}
