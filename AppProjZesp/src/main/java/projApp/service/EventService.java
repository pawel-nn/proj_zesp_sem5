package projApp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projApp.formDTO.EventDTO;
import projApp.model.cooperation.Cooperation;
import projApp.model.cooperation.CooperationDao;
import projApp.model.event.Event;
import projApp.model.event.EventDao;

@Service("EventService")
public class EventService {

	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private CooperationDao cooperationDao;
	
	public Event getEmployeeEvent(String username, Integer eventId) {
		Event event;
		try {
			event = eventDao.findByEventId(eventId);
		}
		catch (Exception e) {
			return null;
		}
		if(event.getCooperation().getEmployee().getUser().getUsername().equals(username)) {
			return event;
		}
		return null;
	}

	public Integer createEvent(EventDTO edto) {
		Event event;
		try {
			Cooperation cooperation = cooperationDao.findByCooperationId(edto.getCooperationId());
			Date date = new Date();
			event = new Event(edto.getSubject(), edto.getEventType(), edto.getContent(), date, cooperation, null);
			event = eventDao.save(event);
		}
		catch (Exception e) {
			return null;
		}
		return event.getEventId();
	}

}
