package projApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
   
    @GetMapping("/")
    String index() {
        return "index";
    }
    
    @RequestMapping("/login")
    public String loginClient() {
      return "login";
    }
    
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied() {
		return "access_denied";
	}
    
}