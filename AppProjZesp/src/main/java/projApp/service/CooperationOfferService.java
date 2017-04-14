package projApp.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
	
	public Iterable<CooperationOffer> findAllEmployeeCooperationOffers(Employee employee, Sort sort) {
		return cooperationOfferDao.findAllCooperationOffersByEmployee(employee, sort);
	}
	
	// TEST TEST TEST
	public Integer createCooperationOffer(CooperationOfferDTO codto) {
		CooperationOffer cooperationOffer;
		try {
			Client client = clientDao.findByClientId(codto.getClientId());
			Employee employee = employeeDao.findByEmployeeId(codto.getEmployeeId());
			Date date = new Date();
			cooperationOffer = new CooperationOffer(codto.getTypeOfCooperation(), codto.getDescription(), date, client, employee);
			cooperationOffer = cooperationOfferDao.save(cooperationOffer);
		}
		catch (Exception e) {
			return null;
		}
		return cooperationOffer.getCooperationOfferId();
	}
	
	public Integer confirmCooperationOffer(CooperationOfferDTO codto) {
		Cooperation cooperation;
		try {
			CooperationOffer cooperationOffer = cooperationOfferDao.findByCooperationOfferId(codto.getCooperationOfferId());
			cooperation = new Cooperation(cooperationOffer.getTypeOfCooperation(), cooperationOffer.getDescription(), null, cooperationOffer.getClient(), cooperationOffer.getEmployee(), null);
			cooperation = cooperationDao.save(cooperation);
		}
		catch (Exception e) {
			return null;
		}
		return cooperation.getCooperationId();
	}
	
	public boolean rejectCooperationOffer(CooperationOfferDTO codto) {
		CooperationOffer cooperationOffer;
		try {
			cooperationOffer = cooperationOfferDao.findByCooperationOfferId(codto.getCooperationOfferId());
			cooperationOfferDao.delete(cooperationOffer);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
