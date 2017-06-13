package projApp.model.client;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import projApp.model.client.Client;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@Transactional
@Repository
public interface ClientDao extends CrudRepository<Client, Integer> {
	
	public Client findByClientId(Integer clientId);

	public Client findByEmail(String email);
	
}
