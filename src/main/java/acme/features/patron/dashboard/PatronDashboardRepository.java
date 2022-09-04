package acme.features.patron.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

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