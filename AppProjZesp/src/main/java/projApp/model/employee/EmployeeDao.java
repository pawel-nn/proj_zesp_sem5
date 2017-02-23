package projApp.model.employee;


import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

	public Employee findByEmployeeId(Integer employeeId);
	
	public Employee findByEmail(String email);

	public boolean exists(Integer employeeId);
  
}

