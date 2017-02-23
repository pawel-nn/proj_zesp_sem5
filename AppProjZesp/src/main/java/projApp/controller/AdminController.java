package projApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projApp.service.UserService;
import projApp.formDTO.UserDTO;
import projApp.model.user.User;


@Controller
public class AdminController {
	
	@Autowired
	private UserService us;
	
    private static int MAX_ROWS_PER_PAGE = 5;
	
    @GetMapping("/admin/registerEmployee")
    public String registerEmployee(UserDTO userDTO) {
      return "register_employee";
    }

    @PostMapping("/admin/registerEmployee")
    public String registerEmployeeValidation(@Valid UserDTO userDTO, BindingResult bindingResult, Model m) {
        if (bindingResult.hasErrors() || !userDTO.arePasswordsEquals()) {
            return "register_employee";
        }
        boolean status = us.saveNewUser(userDTO);
        if(!status) {
        	m.addAttribute("msg", "Error! Employee can not be created.");
            return "result_admin";	
        }
        m.addAttribute("msg", "Success! Employee has been created.");
        return "result_admin";
    }

    @GetMapping("/admin/accountsControl")
    public String accountsControl(@RequestParam(value="page", required=false) Integer pageReq, Model model) {
    	int usersNumber = us.countAllUsers();
    	int maxPagesNumber = (int) (Math.ceil(1.0*usersNumber/MAX_ROWS_PER_PAGE));
    	int pageNumber = 1;
    	if( maxPagesNumber == 0 )
    		maxPagesNumber = 1;
    	if(pageReq != null)
    		pageNumber = pageReq;
    	Page<User> userList = (Page<User>) us.findAllUsers(new PageRequest(pageNumber - 1, MAX_ROWS_PER_PAGE, Sort.Direction.ASC, "username"));
    	if(userList.getNumberOfElements() == 0) 
    		model.addAttribute("isEmpty", true); 
    	else 
    		model.addAttribute("isEmpty", false);
    	model.addAttribute("userList", userList);
    	model.addAttribute("pageNumber",pageNumber);
    	model.addAttribute("maxPagesNumber",maxPagesNumber);
    	return "accounts_control";
    }
    
    @GetMapping("/admin/accountsControl/blockUser")
    public String blockUser(@RequestParam(value="user", required=true) String username, Model m) {
    	boolean status = us.blockUser(username);
    	if(!status) {
    		m.addAttribute("msg", "Error! User can not be blocked!");
    		return "result_admin";	
    	}
    	m.addAttribute("msg", "Success! User has been blocked.");
	    return "result_admin";
	}
    
    @GetMapping("/admin/accountsControl/unlockUser")
    public String unlockUser(@RequestParam(value="user", required=true) String username, Model m) {
    	boolean status = us.unlockUser(username);
    	if(!status) {
    		m.addAttribute("msg", "Error! User can not be unlocked!");
    		return "result_admin";	
    	}
    	m.addAttribute("msg", "Success! User has been unlocked.");
	    return "result_admin";
	}
}
