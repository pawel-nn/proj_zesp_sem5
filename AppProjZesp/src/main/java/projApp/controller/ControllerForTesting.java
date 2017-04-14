package projApp.controller;


import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import projApp.model.client.Client;
import projApp.formDTO.ClientDTO;
import projApp.formDTO.CooperationOfferDTO;
import projApp.model.employee.Employee;
import projApp.service.CooperationOfferService;
import projApp.service.UserService;

@Controller
public class ControllerForTesting {
	
	@Autowired
	private UserService us;

	@Autowired
	private CooperationOfferService cos;
	
	private static List<String> allTypesOfCooperation = Arrays.asList("Doradztwo prawne", "Sprawa s¹dowa", "inne?");

	/* FOR Testing PURPOSE */
	
    @GetMapping("/registerClient")
    public String register_client(ClientDTO clientDTO) {
      return "FOR_TEST_register_client";
    }

    @PostMapping("/registerClient")
    public String registerClientValidation(@Valid ClientDTO clientDTO, BindingResult bindingResult, Model m) {
        if (bindingResult.hasErrors() || !clientDTO.arePasswordsEquals()) {
            return "FOR_TEST_register_client";
        }
        boolean status = us.saveUserClient(clientDTO);
        if(!status) {
        	m.addAttribute("msg", "Error! User can not be created.");
            return "FOR_TEST_register_client_result";	
        }
        m.addAttribute("msg", "Success! User has been created.");
        return "FOR_TEST_register_client_result";
    }
	
    @GetMapping("/addCooperationOffer")
    public String register(CooperationOfferDTO cooperationOfferDTO, Model m) {
    	List<Client> allClients = (List<Client>) us.findAllClients();
    	List<Employee> allEmployees = (List<Employee>) us.findAllEmployees();
		m.addAttribute("allClients", allClients );
		m.addAttribute("allEmployees", allEmployees );
    	m.addAttribute("allTypesOfCooperation", allTypesOfCooperation );
    	return "FOR_TEST_add_cooperation_offer";
    }

    @PostMapping("/addCooperationOffer")
    public String registerValidation(@Valid CooperationOfferDTO cooperationOfferDTO, BindingResult bindingResult, Model m) {
        if (bindingResult.hasErrors()) {
        	List<Client> allClients = (List<Client>) us.findAllClients();
        	List<Employee> allEmployees = (List<Employee>) us.findAllEmployees();
    		m.addAttribute("allClients", allClients );
    		m.addAttribute("allEmployees", allEmployees );
        	m.addAttribute("allTypesOfCooperation", allTypesOfCooperation );
            return "FOR_TEST_add_cooperation_offer";
        }
        Integer status = cos.createCooperationOffer(cooperationOfferDTO);
        if(status == null) {
        	m.addAttribute("msg", "Error! User can not be created.");
            return "FOR_TEST_add_cooperation_offer_result";	
        }
        m.addAttribute("msg", "Success! User has been created.");
        return "FOR_TEST_add_cooperation_offer_result";
    }
}