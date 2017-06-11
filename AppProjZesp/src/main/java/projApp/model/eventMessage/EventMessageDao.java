package projApp.model.eventMessage;


import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface EventMessageDao extends CrudRepository<EventMessage, Integer>{
	
}