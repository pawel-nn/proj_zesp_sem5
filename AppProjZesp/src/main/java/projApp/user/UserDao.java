package projApp.user;
import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends PagingAndSortingRepository<User, String> {

  public User findByUsername(String username);
  
  public boolean exists(String username);
  
}