package projApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projApp.model.cooperation.Cooperation;
import projApp.model.cooperation.CooperationDao;
import projApp.model.employee.Employee;

/**
 * @authors Pawe³ Miszkiel & Rafa³ Teodorowski
 * 
 * 		PW, Wydzia³ Elektryczny - Informatyka - semestr VI
 *
 * 2017-06-13
 */

@Service("CooperationService")
public class CooperationService {
	
	@Autowired
	private CooperationDao cooperationDao;

	public int countAllCooperations() {
		return (int) cooperationDao.count();
	}
	
	public int countAllEmployeeCooperations(Employee employee) {
		return ((List<Cooperation>) cooperationDao.findAllByEmployee(employee)).size();
	}

	public Page<Cooperation> findAllCooperations(Pageable pageable) {
		return cooperationDao.findAll(pageable);
	}

	public Iterable<Cooperation> findAllEmployeeCooperations(Employee employee, Pageable pageable) {
		return cooperationDao.findAllCooperationsByEmployee(employee, pageable);
	}
	
	public boolean existsCooperation(Integer cooperationId) {
		boolean status;
		try {
			status = cooperationDao.exists(cooperationId);
		}
		catch (Exception e) {
			return false;
		}
		return status;	
	}
	
	public Cooperation getEmployeeCooperation(String username, Integer cooperationId) {
		Cooperation cooperation;
		try {
			cooperation = cooperationDao.findByCooperationId(cooperationId);
		}
		catch (Exception e) {
			return null;
		}
		if(cooperation.getEmployee().getUser().getUsername().equals(username)) {
			return cooperation;
		}
		return null;
	}

}
