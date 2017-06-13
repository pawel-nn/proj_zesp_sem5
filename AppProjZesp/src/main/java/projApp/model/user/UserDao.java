package projApp.model.user;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import projApp.model.user.User;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@Transactional
@Repository
public interface UserDao extends PagingAndSortingRepository<User, String> {

	  public User findByUsername(String username);
	  
	  public boolean exists(String username);
	  
	  public Iterable<User> findAll(Sort sort);

	  public Page<User> findAll(Pageable pageable);

	  public long count();
	  
}