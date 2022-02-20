package it.uniroma3.siw.spring.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
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

import it.uniroma3.siw.spring.controller.validator.MeccanicoValidator;
import it.uniroma3.siw.spring.misc.FileUploadUtil;
import it.uniroma3.siw.spring.model.Meccanico;
import it.uniroma3.siw.spring.service.MeccanicoService;


@Controller
@RequestMapping
public class MeccanicoController {
	
	@Autowired
	private MeccanicoService meccanicoService;
	
	@Autowired
	private MeccanicoValidator meccanicoValidator;
	


	

	@RequestMapping(value="/admin/meccanico", method = RequestMethod.GET)
	public String addMeccanico(Model model) {
	    model.addAttribute("meccanico", new Meccanico());
	    return "meccanicoForm";
	}

	
    @RequestMapping(value = "/meccanico/{id}", method = RequestMethod.GET)
    public String getMeccanico(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("meccanico", this.meccanicoService.meccanicoPerId(id));
    	return "meccanico";
    }
    
 
    @RequestMapping(value = "/admin/meccanico/{id}", method = RequestMethod.GET)
    public String deleteMeccanico(@PathVariable("id") Long id, Model model) {
    	this.meccanicoService.cancella(id);
    	model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
    	return "meccanici";
    }

 
    @RequestMapping(value = "/meccanici", method = RequestMethod.GET)
    public String getMeccanici(Model model) {
    		model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
    		return "meccanici";
    }
    

    @RequestMapping(value = "/admin/meccanico", method = RequestMethod.POST)
    public String addMeccanico(@ModelAttribute("meccanico") Meccanico meccanico, 
    									Model model, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
    	this.meccanicoValidator.validate(meccanico, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        	meccanico.setFoto(fileName);
        	this.meccanicoService.inserisci(meccanico);
        	String uploadDir = "uploadable/meccanico-foto/" + meccanico.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);           
            model.addAttribute("meccanici", this.meccanicoService.tuttiOrdinati());
            return "meccanici";
        
        }
        
        return "meccanicoForm";
    
    }

}
