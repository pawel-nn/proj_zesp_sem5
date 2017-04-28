package projApp.model.event;

import org.springframework.data.repository.CrudRepository;

public interface EventDao extends CrudRepository<Event, Integer> {

	public Event findByEventId(Integer eventId);
	
}
