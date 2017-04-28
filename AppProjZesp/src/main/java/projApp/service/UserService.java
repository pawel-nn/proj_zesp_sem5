package projApp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projApp.model.client.Client;
import projApp.formDTO.ClientDTO;
import projApp.model.client.ClientDao;
import projApp.formDTO.NewPasswordDTO;
import projApp.formDTO.EmployeeDTO;
import projApp.model.employee.Employee;
import projApp.model.employee.EmployeeDao;
import projApp.model.user.User;
import projApp.model.user.UserDao;
import projApp.model.user.UserRole;

/* Reprezentuje logike aplikacji TO ZNACZY wykonuje polecenia z controller (Zapisz, usuñ tak jak adres url) i korzysta przy tym z MODELU */

@Service("UserService")
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public boolean saveNewUser(EmployeeDTO edto) {
		try {
			if((userDao.findByUsername(edto.getUsername()) != null) || (employeeDao.findByEmail(edto.getEmail()) != null))
				return false;
			User user = new User(edto.getUsername() , edto.getPassword(), true);
			UserRole userRole = new UserRole( user, "ROLE_EMPLOYEE");
			Set<UserRole> userRoleSet = new HashSet<UserRole>(0);
			userRoleSet.add(userRole);
			user.setUserRole(userRoleSet);
			Employee employee = new Employee(edto.getFirstName(),edto.getLastName(),edto.getEmail(), edto.getPosition(),edto.getSalary(), user, edto.getCity(), edto.getCityPostCode(), edto.getStreet(), edto.getAccommodationNumber(), edto.getMobile());	
			employeeDao.save(employee);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean updateUserPassword(NewPasswordDTO npdto, String username) {
		try {
			User user = userDao.findByUsername(username);
			user.setPassword(npdto.getNewPassword());
			userDao.save(user);
		}
		catch (Exception ex) {
			return false;
		}
		return true;
	}
	
	public boolean blockUser(String username) {
		try {
			User user = userDao.findByUsername(username);
	    	if(user.hasAdminRole())
	    		return false;
			user.setEnabled(false);
			userDao.save(user);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean unlockUser(String username) {
		try {
			User user = userDao.findByUsername(username);
			user.setEnabled(true);
			userDao.save(user);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public Iterable<User> findAllUsers(Pageable pageable) {
		return userDao.findAll(pageable);
    }	
	
	public int countAllUsers() {
		return (int) userDao.count();
    }
	
	public User getUser(String username) {
		User user = null;
		try {
			user = userDao.findByUsername(username);
		}
		catch (Exception ex) {
			return null;
		}
		return user;
	}
	
	public Employee findEmployee(String username) {
		User user = userDao.findByUsername(username);
		return employeeDao.findByUser(user);
	}
	
	/* FOR TESTING PURPOSE !!! */
	@Autowired
	private ClientDao clientDao;
	
	public Iterable<Client> findAllClients() {
		return clientDao.findAll();
    }	
	
	public Iterable<Employee> findAllEmployees() {
		return employeeDao.findAll();
    }	
	
	public boolean saveUserClient(ClientDTO cdto) {
		try {
			if(clientDao.findByEmail(cdto.getEmail()) != null)
				return false;
			Client client = new Client(cdto.getFirstName(),cdto.getLastName(),cdto.getEmail(), cdto.getCity(), cdto.getCityPostCode(), cdto.getStreet(), cdto.getAccommodationNumber(), cdto.getMobile());		
			clientDao.save(client);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	
}

