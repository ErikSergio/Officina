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
import it.uniroma3.siw.spring.misc.FileUploadUtil;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Intervento;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.InterventoService;
import it.uniroma3.siw.spring.service.MeccanicoService;
import it.uniroma3.siw.spring.service.TipologiaInterventoService;




@Controller
public class InterventoController {
	
	@Autowired
	private InterventoService interventoService;
	
    @Autowired
    private InterventoValidator interventoValidator;
    
    @Autowired
    private MeccanicoService meccanicoService;
    
    @Autowired
    private TipologiaInterventoService tipologiaInterventoService;

    @Autowired
	private CredentialsService credentialsService;


    
  
    
   
    @RequestMapping(value="/admin/intervento", method = RequestMethod.GET)
    public String addIntervento(Model model) {
    	model.addAttribute("intervento", new Intervento());
    	model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
    	model.addAttribute("credentials", this.credentialsService.tuttiOrdinati());
    	model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
        return "interventoForm";
    }

   
    @RequestMapping(value = "/intervento/{id}", method = RequestMethod.GET)
    public String getIntervento(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("intervento", this.interventoService.interventoPerId(id));
    	return "intervento";
    }
    
  
    @RequestMapping(value = "/admin/intervento/{id}", method = RequestMethod.GET)
    public String deleteIntervento(@PathVariable("id") Long id, Model model) {
    	this.interventoService.cancella(id);
    	model.addAttribute("interventi", this.interventoService.tuttiOrdinatiPerTitolo());
    	return "interventi";
    }

 
    @RequestMapping(value = "/interventi", method = RequestMethod.GET)
    public String getInterventi(Model model) {
    		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
    			model.addAttribute("interventi", this.interventoService.tuttiOrdinatiPerTitolo());
    			return "interventi";

    		}
    		model.addAttribute("credentials",this.credentialsService.getCredentials(userDetails.getUsername()));
    		model.addAttribute("interventi", this.interventoService.perCliente(this.credentialsService.getCredentials(userDetails.getUsername())));
    		return "interventi";
    }

    @RequestMapping(value = "/admin/intervento", method = RequestMethod.POST)
    public String addIntervento(@ModelAttribute("intervento") Intervento intervento, 
    									Model model, BindingResult bindingResult,@RequestParam("image") MultipartFile multipartFile) throws IOException {
    	this.interventoValidator.validate(intervento, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            intervento.setFoto(fileName);
         
        	this.interventoService.inserisci(intervento);
            String uploadDir = "uploadable/interventi/" + intervento.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
           
            model.addAttribute("interventi", this.interventoService.tuttiOrdinatiPerTitolo());
            return "interventi";
        }
        model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
        model.addAttribute("tipoInterventi", this.tipologiaInterventoService.tuttiOrdinati());
        return "interventoForm";
    }
}
