package projApp.model.eventChat;


import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface EventChatDao extends CrudRepository<EventChat, Integer>{
	
}