package projApp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import projApp.formDTO.CooperationOfferDTO;
import projApp.model.client.Client;
import projApp.model.client.ClientDao;
import projApp.model.cooperation.Cooperation;
import projApp.model.cooperation.CooperationDao;
import projApp.model.cooperationOffer.CooperationOffer;
import projApp.model.employee.Employee;
import projApp.model.employee.EmployeeDao;
import projApp.model.cooperationOffer.CooperationOfferDao;

@Service("CooperationOfferService")
public class CooperationOfferService {
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CooperationOfferDao cooperationOfferDao;
	
	@Autowired
	private CooperationDao cooperationDao;

	public int countAllCooperationOffers() {
		return (int) cooperationOfferDao.count();
	}

	public int countAllEmployeeCooperationOffers(Employee employee) {
		return ((List<CooperationOffer>) cooperationOfferDao.findAllByEmployee(employee)).size();
	}
	
	public Page<CooperationOffer> findAllCooperationOffers(Pageable pageable) {
		return cooperationOfferDao.findAll(pageable);
	}
	
	public Iterable<CooperationOffer> findAllEmployeeCooperationOffers(Employee employee, Pageable pageable) {
		return cooperationOfferDao.findAllCooperationOffersByEmployee(employee, pageable);
	}

	public boolean existsCooperationOffer(Integer cooperationOfferId) {
		boolean status;
		try {
			status = cooperationOfferDao.exists(cooperationOfferId);
		}
		catch (Exception e) {
			return false;
		}
		return status;	
	}

	public CooperationOffer getEmployeeCooperationOffer(String username, Integer cooperationOfferId) {
		CooperationOffer cooperationOffer;
		try {
			cooperationOffer = cooperationOfferDao.findByCooperationOfferId(cooperationOfferId);
		}
		catch (Exception e) {
			return null;
		}
		if(cooperationOffer.getEmployee().getUser().getUsername().equals(username)) {
			return cooperationOffer;
		}
		return null;
	}
	
	public Integer rejectCooperationOffer(Integer elementId) {
		CooperationOffer cooperationOffer;
		try {
			cooperationOffer = cooperationOfferDao.findByCooperationOfferId(elementId);
			cooperationOfferDao.delete(cooperationOffer);
		}
		catch (Exception e) {
			return null;
		}
		return 0;
	}
	
	public Integer acceptCooperationOffer(Integer elementId) {
		CooperationOffer cooperationOffer;
		Cooperation cooperation;
		try {
			cooperationOffer = cooperationOfferDao.findByCooperationOfferId(elementId);
			cooperation = new Cooperation( cooperationOffer.getTypeOfCooperation(), cooperationOffer.getSubject(), cooperationOffer.getDescription(), null, cooperationOffer.getClient(), cooperationOffer.getEmployee(), null );
			cooperation = cooperationDao.save(cooperation);
			cooperationOfferDao.delete(cooperationOffer);
		}
		catch (Exception e) {
			return null;
		}
		return cooperation.getCooperationId();
	}
	
	
	// TEST TEST TEST
	public Integer createCooperationOffer(CooperationOfferDTO codto) {
		CooperationOffer cooperationOffer;
		try {
			Client client = clientDao.findByClientId(codto.getClientId());
			Employee employee = employeeDao.findByEmployeeId(codto.getEmployeeId());
			Date date = new Date();
			cooperationOffer = new CooperationOffer(codto.getTypeOfCooperation(), codto.getSubject(), codto.getDescription(), date, client, employee);
			cooperationOffer = cooperationOfferDao.save(cooperationOffer);
		}
		catch (Exception e) {
			return null;
		}
		return cooperationOffer.getCooperationOfferId();
	}
	
}
