package projApp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import projApp.formDTO.EmployeeUpdateDTO;
import projApp.model.employee.Employee;
import projApp.service.UserService;

@Controller
public class EmployeeProfileController {

	@Autowired
	private UserService us;
	
//	private static final String rootPath = "C:\\Users\\Pawe³\\Desktop";
	private static final String rootPath = "C:\\";
	private static final String dirPath = rootPath + File.separator + "projectFiles";
	
    @GetMapping("/employee/profile")
    public String registerEmployee(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();	
		Employee employee = us.findEmployee(username);
		
		String photoName = employee.getEmployeeId() + "-" + employee.getEmail();
		model.addAttribute("photoName", photoName);
		model.addAttribute("employee", employee);
        return "employee_profile";
    }
    
    @GetMapping(value = "/employee/data/images/{photoName}", produces = "image/png")
    public ResponseEntity<byte[]> registerEmployee(@PathVariable(value="photoName") String photoName, EmployeeUpdateDTO employeeUpdateDTO, Model model) {	
		String filePath = dirPath + File.separator + photoName + ".png";
		File photo = new File(filePath);
		byte[] bytes = new byte[(int) photo.length()];
		try {
			if (photo.exists()) {
				BufferedInputStream stream;
				stream = new BufferedInputStream(new FileInputStream(photo));
				stream.read(bytes);
				stream.close();	
			} else {
				InputStream is = EmployeeProfileController.class.getResourceAsStream("/no-photo.png");
				bytes = new byte[1000];
				is.read(bytes);
			    return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(bytes);
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
				
				String filePath = dirPath + File.separator + employeeUpdateDTO.getEmployeeId() + "-" + employeeUpdateDTO.getEmail();
				
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
		boolean result = us.updateEmployee(employeeUpdateDTO, username);
		if(!result) {
			return "employee_profile_update";	
		}
		return "redirect:/employee/profile";
	}
	
}
