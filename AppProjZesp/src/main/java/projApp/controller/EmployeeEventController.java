package projApp.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import projApp.formDTO.EventDTO;
import projApp.formDTO.EventMessageDTO;
import projApp.model.employee.Employee;
import projApp.model.event.Event;
import projApp.service.EventService;
import projApp.service.UserService;

@Controller
public class EmployeeEventController {
	
	@Autowired
	private EventService es;
	
	@Autowired
	private UserService us;
	
	private static List<String> allTypesOfEvents = Arrays.asList("Informacja", "Sytuacja pilna", "Umuwienie na spotkanie", "Rozprawa s¹dowa", "Dostarczenie dokumentów");
	
	@GetMapping("/employee/cooperationsList/cooperation/event")
	public String eventGET(@RequestParam(value="eventId", required=false) Integer eventId, EventMessageDTO eventMessageDTO, Model m) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	Event event = es.getEmployeeEvent(username, eventId);
		if(event == null)
			return "bad_request";
		m.addAttribute("event", event);
		eventMessageDTO.setEmployeeId(event.getCooperation().getEmployee().getEmployeeId());
		eventMessageDTO.setChatMessage(null);
		return "event";
	}
	
	@PostMapping("/employee/cooperationsList/cooperation/event/eventMessage")
	public String eventMessagePOST(@RequestParam(value="eventId", required=false) Integer eventId, @Valid EventMessageDTO eventMessageDTO, BindingResult bindingResult, Model m, RedirectAttributes redirectAttributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	Employee employee = us.findEmployee(username);
    	Event event = es.getEmployeeEvent(username, eventId);
		if(event == null || employee == null || employee.getEmployeeId() != eventMessageDTO.getEmployeeId() || eventMessageDTO.getClientId() != null)
			return "bad_request";
        if (bindingResult.hasErrors()) {
        	m.addAttribute("event", event);
            return "event";
        }
        Integer status = es.saveEventMessage(eventMessageDTO, event);
        if(status == null) {
        	m.addAttribute("msg", "B³¹d! Wiadomoœæ nie zosta³a utworzona.");
            return "result";	
        }
          redirectAttributes.addAttribute("eventId", eventId);
          return "redirect:/employee/cooperationsList/cooperation/event";
	}
	
    @GetMapping("/employee/cooperationsList/cooperation/addEvent")
    public String addEventGET(@RequestParam(value="cooperationId", required=false) Integer cooperationId, EventDTO eventDTO, Model m) {
    	eventDTO.setCooperationId(cooperationId);
    	m.addAttribute("allTypesOfEvents", allTypesOfEvents );
    	return "event_add";
    }

    @PostMapping("/employee/cooperationsList/cooperation/addEvent")
    public String addEventPOST(@Valid EventDTO eventDTO, BindingResult bindingResult, Model m) {
        if (bindingResult.hasErrors()) {
        	m.addAttribute("allTypesOfEvents", allTypesOfEvents );
            return "event_add";
        }
        Integer status = es.createEvent(eventDTO);
        if(status == null) {
        	m.addAttribute("msg", "B³¹d! Wydarzenie nie zosta³o utworzone.");
            return "result";	
        }
        m.addAttribute("msg", "Sukces! Wydarzenie zosta³o utworzone.");
        return "result";
    }
    
}


