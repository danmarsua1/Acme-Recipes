package acme.forms.administrator;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {


	// Manage COMPONENTS
	@Query("select count(i) from Item i where i.type = 'COMPONENT'")
	Integer totalNumberOfComponents();

	@Query("select avg(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency")
	List<Object> averageRetailPriceOfComponents();

	@Query("select stddev(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency")
	List<Object> deviationRetailPriceOfComponents();
	
	@Query("select min(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency")
	List<Object> minimumRetailPriceOfComponents();

	@Query("select max(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'COMPONENT' group by i.retailPrice.currency")
	List<Object> maximumRetailPriceOfComponents();
	
	// Manage TOOLS
	@Query("select count(i) from Item i where i.type = 'TOOL'")
	Integer totalNumberOfTools();
	
	@Query("select avg(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object> averageRetailPriceOfTools();

	@Query("select stddev(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object> deviationRetailPriceOfTools();

	@Query("select min(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object> minimumRetailPriceOfTools();
	
	@Query("select max(i.retailPrice.amount), i.retailPrice.currency from Item i where i.type = 'TOOL' group by i.retailPrice.currency")
	List<Object> maximumRetailPriceOfTools();
	
	// Manage TOTALS
	@Query("select count(p) from Patronage p where p.status = 'PROPOSED'")
	Integer totalNumberOfProposedPatronages();

	@Query("select count(p) from Patronage p where p.status = 'ACCEPTED'")
	Integer totalNumberOfAcceptedPatronages();

	@Query("select count(p) from Patronage p where p.status = 'DENIED'")
	Integer totalNumberOfDeniedPatronages();

	// Manage PROPOSED
	@Query("select avg(p.budget.amount), p.budget.currency from Patronage p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> averageBudgetOfProposedPatronages();

	@Query("select stddev(p.budget.amount), p.budget.currency from Patronage p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> deviationBudgetOfProposedPatronages();

	@Query("select min(p.budget.amount), p.budget.currency from Patronage p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> minimumBudgetOfProposedPatronages();

	@Query("select max(p.budget.amount), p.budget.currency from Patronage p where p.status = 'PROPOSED' group by p.budget.currency")
	List<Object> maximumBudgetOfProposedPatronages();

	// Manage ACCEPTED
	@Query("select avg(p.budget.amount), p.budget.currency from Patronage p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> averageBudgetOfAcceptedPatronages();

	@Query("select stddev(p.budget.amount), p.budget.currency from Patronage p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> deviationBudgetOfAcceptedPatronages();

	@Query("select min(p.budget.amount), p.budget.currency from Patronage p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> minimumBudgetOfAcceptedPatronages();

	@Query("select max(p.budget.amount), p.budget.currency from Patronage p where p.status = 'ACCEPTED' group by p.budget.currency")
	List<Object> maximumBudgetOfAcceptedPatronages();

	// Manage DENIED
	@Query("select avg(p.budget.amount), p.budget.currency from Patronage p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> averageBudgetOfDeniedPatronages();

	@Query("select stddev(p.budget.amount), p.budget.currency from Patronage p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> deviationBudgetOfDeniedPatronages();

	@Query("select min(p.budget.amount), p.budget.currency from Patronage p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> minimumBudgetOfDeniedPatronages();

	@Query("select max(p.budget.amount), p.budget.currency from Patronage p where p.status = 'DENIED' group by p.budget.currency")
	List<Object> maximumBudgetOfDeniedPatronages();
	
}