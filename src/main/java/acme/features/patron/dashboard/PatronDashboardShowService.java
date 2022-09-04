package acme.features.patron.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.patron.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;


@Service
public class PatronDashboardShowService implements AbstractShowService<Patron,PatronDashboard>{
		@Autowired
		protected PatronDashboardRepository repository;
		
		@Override
		public boolean authorise(final Request<PatronDashboard>request) {
			assert request != null;
			return true;
		}

		@Override
		public PatronDashboard findOne(final Request<PatronDashboard>request) {
			assert request != null;
			
			PatronDashboard result;
			
			Integer	totalNumberOfProposedPatronages;
			Integer	totalNumberOfAcceptedPatronages;
			Integer	totalNumberOfDeniedPatronages;
			Double averageBudgetOfProposedPatronages;
			Double deviationBudgetOfProposedPatronages;
			Double minimumBudgetOfProposedPatronages;
			Double maximumBudgetOfProposedPatronages;
			Double averageBudgetOfAcceptedPatronages;
			Double deviationBudgetOfAcceptedPatronages;
			Double minimumBudgetOfAcceptedPatronages;
			Double maximumBudgetOfAcceptedPatronages;
			Double averageBudgetOfDeniedPatronages;
			Double deviationBudgetOfDeniedPatronages;
			Double minimumBudgetOfDeniedPatronages;
			Double maximumBudgetOfDeniedPatronages;
			
			totalNumberOfProposedPatronages = this.repository.totalNumberOfProposedPatronages();
			totalNumberOfAcceptedPatronages = this.repository.totalNumberOfAcceptedPatronages();
			totalNumberOfDeniedPatronages = this.repository.totalNumberOfDeniedPatronages();
			averageBudgetOfProposedPatronages = this.repository.averageBudgetOfProposedPatronages();
			deviationBudgetOfProposedPatronages = this.repository.deviationBudgetOfProposedPatronages();
			minimumBudgetOfProposedPatronages = this.repository.minimumBudgetOfProposedPatronages();
			maximumBudgetOfProposedPatronages = this.repository.maximumBudgetOfProposedPatronages();
			averageBudgetOfAcceptedPatronages = this.repository.averageBudgetOfAcceptedPatronages();
			deviationBudgetOfAcceptedPatronages = this.repository.deviationBudgetOfAcceptedPatronages();
			minimumBudgetOfAcceptedPatronages = this.repository.minimumBudgetOfAcceptedPatronages();
			maximumBudgetOfAcceptedPatronages = this.repository.maximumBudgetOfAcceptedPatronages();
			averageBudgetOfDeniedPatronages = this.repository.averageBudgetOfDeniedPatronages();
			deviationBudgetOfDeniedPatronages = this.repository.deviationBudgetOfDeniedPatronages();
			minimumBudgetOfDeniedPatronages = this.repository.minimumBudgetOfDeniedPatronages();
			maximumBudgetOfDeniedPatronages = this.repository.maximumBudgetOfDeniedPatronages();
			
			result = new PatronDashboard();
			result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
			result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
			result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
			result.setAverageBudgetOfProposedPatronages(averageBudgetOfProposedPatronages);
			result.setDeviationBudgetOfProposedPatronages(deviationBudgetOfProposedPatronages);
			result.setMinimumBudgetOfProposedPatronages(minimumBudgetOfProposedPatronages);
			result.setMaximumBudgetOfProposedPatronages(maximumBudgetOfProposedPatronages);
			result.setAverageBudgetOfAcceptedPatronages(averageBudgetOfAcceptedPatronages);
			result.setDeviationBudgetOfAcceptedPatronages(deviationBudgetOfAcceptedPatronages);
			result.setMinimumBudgetOfAcceptedPatronages(minimumBudgetOfAcceptedPatronages);
			result.setMaximumBudgetOfAcceptedPatronages(maximumBudgetOfAcceptedPatronages);
			result.setAverageBudgetOfDeniedPatronages(averageBudgetOfDeniedPatronages);
			result.setDeviationBudgetOfDeniedPatronages(deviationBudgetOfDeniedPatronages);
			result.setMinimumBudgetOfDeniedPatronages(minimumBudgetOfDeniedPatronages);
			result.setMaximumBudgetOfDeniedPatronages(maximumBudgetOfDeniedPatronages);
			
			return result;
		}
		
		@Override
		public void unbind(final Request<PatronDashboard>request, final PatronDashboard entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages","averageBudgetOfProposedPatronages",
											"deviationBudgetOfProposedPatronages","minimumBudgetOfProposedPatronages","maximumBudgetOfProposedPatronages",
											"averageBudgetOfAcceptedPatronages","deviationBudgetOfAcceptedPatronages","minimumBudgetOfAcceptedPatronages",
											"maximumBudgetOfAcceptedPatronages","averageBudgetOfDeniedPatronages","deviationBudgetOfDeniedPatronages",
											"minimumBudgetOfDeniedPatronages","maximumBudgetOfDeniedPatronages");
		}
}
