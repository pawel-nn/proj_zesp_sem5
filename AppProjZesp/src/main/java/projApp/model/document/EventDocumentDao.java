package projApp.model.document;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface EventDocumentDao extends CrudRepository<EventDocument, Integer>{
	
}