package projApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projApp.formDTO.ConfirmationDTO;
import projApp.model.cooperationOffer.CooperationOffer;
import projApp.model.employee.Employee;
import projApp.service.CooperationOfferService;
import projApp.service.UserService;

@Controller
public class EmployeeCooperationOfferController {
	
	private static final int MAX_ROWS_PER_PAGE = 5;
	
	@Autowired
	private CooperationOfferService cos;

	@Autowired
	private UserService us;
	
	@GetMapping("/employee/cooperationOffersList")
	public String cooperationOfferList(@RequestParam(value="page", required=false) Integer pageReq, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
		Employee employee = us.findEmployee(username);
		int carsNumber = cos.countAllEmployeeCooperationOffers(employee);
		int maxPagesNumber = (int) (Math.ceil(1.0*carsNumber/MAX_ROWS_PER_PAGE));
		int pageNumber = 1;
		if( maxPagesNumber == 0 )
			maxPagesNumber = 1;
		if(pageReq != null )
			if(pageReq < 1) pageReq = 1;
			else pageNumber = pageReq;
		Page<CooperationOffer> cooperationOffersList = (Page<CooperationOffer>) cos.findAllEmployeeCooperationOffers(employee, new PageRequest(pageNumber - 1, MAX_ROWS_PER_PAGE, Sort.Direction.DESC, "dateOfOfferCreation"));
		model.addAttribute("cooperationOffersList", cooperationOffersList);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("maxPagesNumber",maxPagesNumber);
		return "cooperation_offers_list";
	} 
	
	@GetMapping("/employee/cooperationOffersList/cooperationOffer")
	public String cooperationOffer(@RequestParam(value="cooperationOfferId", required=false) Integer cooperationOfferId, Model m) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	CooperationOffer cooperationOffer = cos.getEmployeeCooperationOffer(username, cooperationOfferId);
		if(cooperationOffer == null)
			return "bad_request";
		m.addAttribute("cooperationOffer", cooperationOffer);
		return "cooperation_offer";
	}

	@GetMapping("/employee/cooperationOffersList/acceptCooperationOffer")
	public String acceptCooperationOfferGET(@RequestParam(value="elementId", required=false) Integer elementId, ConfirmationDTO confirmationDTO, Model m) {
		if(!cos.existsCooperationOffer(elementId))
			return "bad_request";
		m.addAttribute("msg", "Potwierdz akceptacjê oferty wspó³pracy.");
		m.addAttribute("elementId", elementId);
		m.addAttribute("route", "/employee/cooperationOffersList/acceptCooperationOffer");
		return "confirmation_page_employee";
	}

	@PostMapping("/employee/cooperationOffersList/acceptCooperationOffer")
	public String acceptCooperationOfferPOST(@RequestParam(value="elementId", required=false) Integer elementId, ConfirmationDTO confirmationDTO, Model m) {
		if(!cos.existsCooperationOffer(elementId))
			return "bad_request";
		if( confirmationDTO.getConf() == null || confirmationDTO.getConf().equals("no")) {
			m.addAttribute("msg", "B³¹d! Oferta nie zosta³a zaakceptowana.");
			return "result_employee";	
		}
		Integer result = cos.acceptCooperationOffer(elementId);
		if(result == null) {
			m.addAttribute("msg", "B³¹d! Oferta nie zosta³a zaakceptowana.");
			return "result_employee";	
		}
		m.addAttribute("msg", "Oferta zaakceptowana!");
		return "result_employee";
	}
	
	@GetMapping("/employee/cooperationOffersList/rejectCooperationOffer")
	public String rejectCooperationOfferGET(@RequestParam(value="elementId", required=false) Integer elementId, ConfirmationDTO confirmationDTO, Model m) {
		if(!cos.existsCooperationOffer(elementId))
			return "bad_request";
		m.addAttribute("msg", "PotwierdŸ odrzucenie oferty wspó³pracy.");
		m.addAttribute("elementId", elementId);
		m.addAttribute("route", "/employee/cooperationOffersList/rejectCooperationOffer");
		return "confirmation_page_employee";
	}
	
	@PostMapping("/employee/cooperationOffersList/rejectCooperationOffer")
	public String rejectCooperationOfferPOST(@RequestParam(value="elementId", required=false) Integer elementId, ConfirmationDTO confirmationDTO, Model m) {
		if(!cos.existsCooperationOffer(elementId))
			return "bad_request";
		if( confirmationDTO.getConf() == null || confirmationDTO.getConf().equals("no")) {
			m.addAttribute("msg", "B³¹d! Oferta nie zosta³a odrzucona.");
			return "result_employee";	
		}
		Integer result = cos.rejectCooperationOffer(elementId);
		if(result == null) {
			m.addAttribute("msg", "B³¹d! Oferta nie zosta³a odrzucona.");
			return "result_employee";	
		}
		m.addAttribute("msg", "Oferta zosta³a odrzucona.");
		return "result_employee";
	}

}
