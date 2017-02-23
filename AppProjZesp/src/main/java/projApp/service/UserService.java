package projApp.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projApp.formDTO.NewPasswordDTO;
import projApp.formDTO.UserDTO;
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
	
	public boolean saveNewUser(UserDTO udto) {
		try {
			if((userDao.findByUsername(udto.getUsername()) != null) || (employeeDao.findByEmail(udto.getEmail()) != null))
				return false;
			User user = new User(udto.getUsername() , udto.getPassword(), true);
			UserRole userRole = new UserRole( user, "ROLE_EMPLOYEE");
			Set<UserRole> userRoleSet = new HashSet<UserRole>(0);
			userRoleSet.add(userRole);
			user.setUserRole(userRoleSet);
			Employee employee = new Employee(udto.getFirstName(),udto.getLastName(),udto.getEmail(), udto.getPosition(),udto.getSalary(), user);	
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
	
}

