package projApp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import projApp.formDTO.EventDTO;
import projApp.formDTO.EventDocumentDTO;
import projApp.formDTO.EventMessageDTO;
import projApp.model.employee.Employee;
import projApp.model.event.Event;
import projApp.model.eventDocument.EventDocument;
import projApp.service.EventService;
import projApp.service.UserService;

@Controller
public class EmployeeEventController {
	
	@Autowired
	private EventService es;
	
	@Autowired
	private UserService us;
	
	private static List<String> allTypesOfEvents = Arrays.asList("Informacja", "Sytuacja pilna", "Umuwienie na spotkanie", "Rozprawa s¹dowa", "Dostarczenie dokumentów");
	private static final String rootPath = "C:\\";
	private static final String dirPath = rootPath + File.separator + "projectFiles";
	
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
	
    @GetMapping("/employee/cooperationsList/cooperation/event/addEventDocument")
    public String addEventGET(@RequestParam(value="eventId", required=false) Integer eventId, EventDocumentDTO eventDocumentDTO, Model m) {
		m.addAttribute("eventId", eventId);
    	return "document_add";
    }
	
	@PostMapping("/employee/cooperationsList/cooperation/event/addEventDocument")
	public String eventDocumentGET(@RequestParam(value="eventId", required=false) Integer eventId, @Valid EventDocumentDTO eventDocumentDTO, BindingResult bindingResult,  Model m, RedirectAttributes redirectAttributes) {
		MultipartFile eventDocument = eventDocumentDTO.getEventDocument();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String username = auth.getName();
    	Employee employee = us.findEmployee(username);
    	Event event = es.getEmployeeEvent(username, eventId);
		if(event == null || employee == null)
			return "bad_request";
        if (bindingResult.hasErrors()) {
        	m.addAttribute("eventId", eventId);
            return "document_add";
        }
		if (!eventDocument.isEmpty()) {
			try {
				byte[] bytes = eventDocument.getBytes();
							
				File dir1 = new File(dirPath);
				if (!dir1.exists())
					dir1.mkdirs();
				File dir2 = new File(dirPath + File.separator + "documents");
				if (!dir2.exists())
					dir2.mkdirs();
		
				String filePath = dirPath + File.separator + "documents" + File.separator + eventDocument.getOriginalFilename();
				File convertedFile = new File(filePath);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(convertedFile));
				stream.write(bytes);
				stream.close();		
				
				eventDocumentDTO.setName(eventDocument.getOriginalFilename().split("\\.")[0]);
				eventDocumentDTO.setType(eventDocument.getOriginalFilename().split("\\.")[1]);
				eventDocumentDTO.setPath(filePath);				
			} catch (Exception e) {
	        	m.addAttribute("msg", "B³¹d! Nie zapisano dokumentu.");
	            return "result";
			}
		} else {
        	m.addAttribute("msg", "B³¹d! Plik jest pusty.");
            return "result";
		}
		
		Integer result = es.saveDocument(eventDocumentDTO, eventId);
		if(result == null) {
        	m.addAttribute("msg", "B³¹d! Nie zapisano dokumentu.");
            return "result";
		}
        
        redirectAttributes.addAttribute("eventId", eventId);
        return "redirect:/employee/cooperationsList/cooperation/event";
	}
	
    @GetMapping(value = "/employee/data/documents/{eventDocumentId}", produces = "application/pdf")
    public ResponseEntity<byte[]> getEventDocument(@PathVariable(value="eventDocumentId") int eventDocumentId, Model model) {	
		EventDocument ed = es.getEventDocument(eventDocumentId);
		File document = new File(ed.getPath());
		byte[] bytes = new byte[(int) document.length()];
		try {
			if (document.exists()) {
				BufferedInputStream stream;
				stream = new BufferedInputStream(new FileInputStream(document));
				stream.read(bytes);
				stream.close();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(bytes);
    }
	
    @GetMapping("/employee/cooperationsList/cooperation/addEvent")
    public String addEventGET(@RequestParam(value="cooperationId", required=false) Integer cooperationId, EventDTO eventDTO, Model m) {
    	eventDTO.setCooperationId(cooperationId);
    	m.addAttribute("allTypesOfEvents", allTypesOfEvents );
    	return "event_add";
    }

    @PostMapping("/employee/cooperationsList/cooperation/addEvent")
    public String addEventPOST(@Valid EventDTO eventDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model m) {
        if (bindingResult.hasErrors()) {
        	m.addAttribute("allTypesOfEvents", allTypesOfEvents );
            return "event_add";
        }
        Integer eventId = es.createEvent(eventDTO);
        if(eventId == null) {
        	m.addAttribute("msg", "B³¹d! Wydarzenie nie zosta³o utworzone.");
            return "result";	
        }
        redirectAttributes.addAttribute("eventId", eventId);
        return "redirect:/employee/cooperationsList/cooperation/event";
    }
    
}
