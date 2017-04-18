package projApp.model.employee;


import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import projApp.model.user.User;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

	public Employee findByEmployeeId(Integer employeeId);
	
	public Employee findByEmail(String email);

	public boolean exists(Integer employeeId);

	public Employee findByUser(User user);
  
}

