package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.TipologiaInterventoValidator;
import it.uniroma3.siw.spring.model.TipologiaIntervento;
import it.uniroma3.siw.spring.service.MeccanicoService;

import it.uniroma3.siw.spring.service.TipologiaInterventoService;



@Controller
public class TipologiaInterventoController {
	
	@Autowired
	private TipologiaInterventoService tipologiaInterventoService;
	
    @Autowired
    private TipologiaInterventoValidator tipologiaInterventoValidator;


    @Autowired
	private MeccanicoService meccanicoService;
 
    @RequestMapping(value="/admin/tipoIntervento", method = RequestMethod.GET)
    public String addtipoIntervento(Model model) {
    	model.addAttribute("tipoIntervento", new TipologiaIntervento());
    	model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
        return "tipoInterventoForm";
    }

   
    @RequestMapping(value = "/tipoIntervento/{id}", method = RequestMethod.GET)
    public String gettipoIntervento(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("tipoIntervento", this.tipologiaInterventoService.tipoInterventoPerId(id));
    	return "tipoIntervento";
    }


    @RequestMapping(value = "/tipoInterventi", method = RequestMethod.GET)
    public String getCollezioni(Model model) {
    		model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
    		return "tipoInterventi";
    }
    
    
    @RequestMapping(value = "/admin/tipoIntervento/{id}", method = RequestMethod.GET)
    public String deleteCollezione(@PathVariable("id") Long id, Model model) {
    	this.tipologiaInterventoService.cancella(id);
    	model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
    	return "tipoInterventi";
    }
    
    @RequestMapping(value = "/admin/tipoIntervento", method = RequestMethod.POST)
    public String addQuadro(@ModelAttribute("tipoIntervento") TipologiaIntervento tipoIntervento, 
    									Model model, BindingResult bindingResult) {
    	this.tipologiaInterventoValidator.validate(tipoIntervento, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	this.tipologiaInterventoService.inserisci(tipoIntervento);
        	
            model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
            return "tipoInterventi";
        }
        model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
        return "tipoInterventoForm";
    }


}
