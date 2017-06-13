package projApp.model.cooperation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import projApp.model.employee.Employee;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

public interface CooperationDao extends CrudRepository<Cooperation, Integer> {
	
	public Page<Cooperation> findAll(Pageable pageable);

	public Cooperation findByCooperationId(Integer cooperationId);

	public Page<Cooperation> findAllCooperationsByEmployee(Employee employee, Pageable pageable);

	public long count();

	public List<Cooperation> findAllByEmployee(Employee employee);

}
