package projApp.model.client;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ClientDao extends CrudRepository<Client, Integer> {
	
}
