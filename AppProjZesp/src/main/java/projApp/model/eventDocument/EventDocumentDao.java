package projApp.model.eventDocument;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface EventDocumentDao extends CrudRepository<EventDocument, Integer>{

	  public EventDocument findByEventDocumentId(Integer eventDocumentId);
	
}