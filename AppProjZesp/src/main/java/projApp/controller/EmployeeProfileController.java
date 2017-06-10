package projApp.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import projApp.formDTO.EmployeeDTO;
import projApp.formDTO.EmployeeUpdateDTO;
import projApp.model.employee.Employee;
import projApp.service.UserService;

@Controller
public class EmployeeProfileController {

	@Autowired
	private UserService us;
	
    @GetMapping("/employee/profile")
    public String registerEmployee(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();	
		Employee employee = us.findEmployee(username);
		model.addAttribute("employee", employee);
        return "employee_profile";
    }
	
    @GetMapping("/employee/updateProfile")
    public String registerEmployee(EmployeeUpdateDTO employeeUpdateDTO, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();	
		Employee employee = us.findEmployee(username);
		employeeUpdateDTO.setUp(employee);
        return "employee_profile_update";
    }
	
	@RequestMapping(value = "/employee/updateProfile", method = RequestMethod.POST)
	public String uploadFileHandler(@RequestParam("employeePhoto") MultipartFile employeePhoto, @Valid EmployeeUpdateDTO employeeUpdateDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
        	System.out.println("xxx " + bindingResult.getFieldError() );
            return "employee_profile_update";
        }
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();	
		if (!employeePhoto.isEmpty()) {
			try {
				byte[] bytes = employeePhoto.getBytes();
				
				String rootPath = "C:\\Users\\Pawe³\\Desktop";
				String dirPath = rootPath + File.separator + "projectFiles";
				String filePath = dirPath + File.separator + employeeUpdateDTO.getEmployeeId() + "-" + username;
				
				File dir = new File(dirPath);
				if (!dir.exists())
					dir.mkdirs();

				File convertedFile = new File(filePath + ".png");
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(convertedFile));
				stream.write(bytes);
				stream.close();				
			} catch (Exception e) {
				return "employee_profile_update";
			}
		} else {
			return "employee_profile_update";
		}	
		Employee employee = us.findEmployee(username);
		model.addAttribute("employee", employee);
		return "redirect:/employee/profile";
	}
	
}
