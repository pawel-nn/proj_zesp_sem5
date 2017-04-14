package projApp.model.cooperationOffer;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import projApp.model.employee.Employee;

@Transactional
@Repository
public interface CooperationOfferDao extends PagingAndSortingRepository<CooperationOffer, Integer> {

	  public CooperationOffer findByCooperationOfferId(int cooperationOfferId);

	  public boolean exists(Integer cooperationOfferId);

	  public Page<CooperationOffer> findAll(Pageable pageable);

	  public long count();

	  public Iterable<CooperationOffer> findAllCooperationOffersByEmployee(Employee employee, Sort sort);
}
