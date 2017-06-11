package projApp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projApp.formDTO.EventMessageDTO;
import projApp.model.client.Client;
import projApp.model.client.ClientDao;
import projApp.model.employee.Employee;
import projApp.model.employee.EmployeeDao;
import projApp.model.event.Event;
import projApp.model.event.EventDao;
import projApp.model.eventMessage.EventMessage;

@Service("EventMessageService")
public class EventMessageService {

	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ClientDao clientDao;
	
	public Integer saveEventMessage(EventMessageDTO emdto, Integer eventId) {
		Event event;
		try {
			event = eventDao.findByEventId(eventId);
			ArrayList<EventMessage> emList = (ArrayList<EventMessage>) event.getEventMessages();
			Employee employee = employeeDao.findByEmployeeId(emdto.getEmployeeId());
			Client client = clientDao.findByClientId(emdto.getClientId());
			EventMessage em = new EventMessage(emdto.getMessage(), employee, client, event);
			emList.add(em);
			event.setEventMessages(emList);
			eventDao.save(event);
		}
		catch (Exception e) {
			return null;
		}
		return event.getEventId();
	}

}
