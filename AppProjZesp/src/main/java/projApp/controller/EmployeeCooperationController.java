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
import org.springframework.web.bind.annotation.RequestParam;

import projApp.model.cooperation.Cooperation;
import projApp.model.employee.Employee;
import projApp.service.CooperationService;
import projApp.service.UserService;

@Controller
public class EmployeeCooperationController {
	
	private static final int MAX_ROWS_PER_PAGE = 5;
	
	@Autowired
	private CooperationService cs;
	
	@Autowired
	private UserService us;
	
	@GetMapping("/employee/cooperationsList")
	public String cooperationList(@RequestParam(value="page", required=false) Integer pageReq, Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
		Employee employee = us.findEmployee(username);
		int carsNumber = cs.countAllEmployeeCooperations(employee);
		int maxPagesNumber = (int) (Math.ceil(1.0*carsNumber/MAX_ROWS_PER_PAGE));
		int pageNumber = 1;
		if( maxPagesNumber == 0 )
			maxPagesNumber = 1;
		if(pageReq != null )
			if(pageReq < 1) pageReq = 1;
			else pageNumber = pageReq;
		Page<Cooperation> cooperationsList = (Page<Cooperation>) cs.findAllEmployeeCooperations(employee, new PageRequest(pageNumber - 1, MAX_ROWS_PER_PAGE, Sort.Direction.DESC, "dateOfLastEvent"));
		model.addAttribute("cooperationsList", cooperationsList);
		model.addAttribute("pageNumber",pageNumber);
		model.addAttribute("maxPagesNumber",maxPagesNumber);
		return "cooperations_list";
	} 

	@GetMapping("/employee/cooperationsList/cooperation")
	public String cooperation(@RequestParam(value="cooperationId", required=false) Integer cooperationId, Model m) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	Cooperation cooperation = cs.getEmployeeCooperation(username, cooperationId);
		if(cooperation == null)
			return "bad_request";
		m.addAttribute("cooperation", cooperation);
		return "cooperation";
	}
	
}
