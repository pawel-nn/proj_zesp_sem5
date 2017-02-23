package projApp.model.user;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Transactional
@Repository
public interface UserRoleDao extends CrudRepository<UserRole, Integer> {

  public UserRole findByUser(User user);
  
}