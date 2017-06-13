package projApp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projApp.formDTO.EventDTO;
import projApp.formDTO.EventDocumentDTO;
import projApp.formDTO.EventMessageDTO;
import projApp.model.client.Client;
import projApp.model.client.ClientDao;
import projApp.model.cooperation.Cooperation;
import projApp.model.cooperation.CooperationDao;
import projApp.model.employee.Employee;
import projApp.model.employee.EmployeeDao;
import projApp.model.event.Event;
import projApp.model.event.EventDao;
import projApp.model.eventDocument.EventDocument;
import projApp.model.eventDocument.EventDocumentDao;
import projApp.model.eventMessage.EventMessage;

@Service("EventService")
public class EventService {

	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private CooperationDao cooperationDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private EventDocumentDao eventDaocumentDao;
	
	public Event getEmployeeEvent(String username, Integer eventId) {
		Event event;
		try {
			event = eventDao.findByEventId(eventId);
		}
		catch (Exception e) {
			return null;
		}
		if(event != null && event.getCooperation().getEmployee().getUser().getUsername().equals(username)) {
			return event;
		}
		return null;
	}

	public Integer createEvent(EventDTO edto) {
		Event event;
		try {
			Cooperation cooperation = cooperationDao.findByCooperationId(edto.getCooperationId());
			Date date = new Date();
			cooperation.setDateOfLastEvent(date);
			event = new Event(edto.getSubject(), edto.getEventType(), edto.getContent(), date, cooperation, null);
			event = eventDao.save(event);
		}
		catch (Exception e) {
			return null;
		}
		return event.getEventId();
	}

	public Integer saveEventMessage(EventMessageDTO emdto, Event event) {
		try {
			List<EventMessage> emList = event.getEventMessages();
			Employee employee = employeeDao.findByEmployeeId(emdto.getEmployeeId());
			Client client = clientDao.findByClientId(emdto.getClientId());
			EventMessage em = new EventMessage(emdto.getChatMessage(), employee, client, event);
			emList.add(em);
			event.setEventMessages(emList);
			eventDao.save(event);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return event.getEventId();
	}

	public Integer saveDocument(EventDocumentDTO eddto, Integer eventId) {
		Event event;
		try {
			event = eventDao.findByEventId(eventId);
			List<EventDocument> edList = event.getEventDocuments();
			EventDocument ed = new EventDocument( eddto.getName(), eddto.getType(), eddto.getPath(), eddto.getDescription(), event);
			edList.add(ed);
			event.setEventDocuments(edList);
			eventDao.save(event);
		}
		catch (Exception e) {
			return null;
		}
		return event.getEventId();
	}

	public EventDocument getEventDocument(Integer eventDocumentId) {
		return eventDaocumentDao.findByEventDocumentId(eventDocumentId);
	}
	
}
