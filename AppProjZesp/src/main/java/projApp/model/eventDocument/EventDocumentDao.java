package projApp.model.eventDocument;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@Transactional
@Repository
public interface EventDocumentDao extends CrudRepository<EventDocument, Integer>{

	  public EventDocument findByEventDocumentId(Integer eventDocumentId);
	
}