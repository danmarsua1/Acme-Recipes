package acme.forms.administrator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard>request) {
		assert request !=null;
		return true;
	}
	
	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard>request) {
		assert request != null;
		
		AdministratorDashboard result;
		
		// Manage COMPONENTS
		Integer totalNumberOfComponents;
		List<Object> averageRetailPriceOfComponents;
		List<Object> deviationRetailPriceOfComponents;
		List<Object> minimumRetailPriceOfComponents;
		List<Object> maximumRetailPriceOfComponents;
		
		// Manage TOOLS
		Integer totalNumberOfTools;
		List<Object> averageRetailPriceOfTools;
		List<Object> deviationRetailPriceOfTools;
		List<Object> minimumRetailPriceOfTools;
		List<Object> maximumRetailPriceOfTools;
		
		// Manage TOTALS
		Integer totalNumberOfProposedPatronages;
		Integer totalNumberOfAcceptedPatronages;
		Integer totalNumberOfDeniedPatronages;

		// Manage PROPOSED
		List<Object> averageBudgetOfProposedPatronages;
		List<Object> deviationBudgetOfProposedPatronages;
		List<Object> minimumBudgetOfProposedPatronages;
		List<Object> maximumBudgetOfProposedPatronages;

		// Manage ACCEPTED
		List<Object> averageBudgetOfAcceptedPatronages;
		List<Object> deviationBudgetOfAcceptedPatronages;
		List<Object> minimumBudgetOfAcceptedPatronages;
		List<Object> maximumBudgetOfAcceptedPatronages;

		// Manage DENIED
		List<Object> averageBudgetOfDeniedPatronages;
		List<Object> deviationBudgetOfDeniedPatronages;
		List<Object> minimumBudgetOfDeniedPatronages;
		List<Object> maximumBudgetOfDeniedPatronages;
		
		
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		averageRetailPriceOfComponents = this.repository.averageRetailPriceOfComponents();
		deviationRetailPriceOfComponents = this.repository.deviationRetailPriceOfComponents();
		minimumRetailPriceOfComponents = this.repository.minimumRetailPriceOfComponents();
		maximumRetailPriceOfComponents = this.repository.maximumRetailPriceOfComponents();
		
		totalNumberOfTools = this.repository.totalNumberOfTools();
		averageRetailPriceOfTools = this.repository.averageRetailPriceOfTools();
		deviationRetailPriceOfTools = this.repository.deviationRetailPriceOfTools();
		minimumRetailPriceOfTools = this.repository.minimumRetailPriceOfTools();
		maximumRetailPriceOfTools = this.repository.maximumRetailPriceOfTools();
		
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
		
		result = new AdministratorDashboard();
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageRetailPriceOfComponents(averageRetailPriceOfComponents);
		result.setDeviationRetailPriceOfComponents(deviationRetailPriceOfComponents);
		result.setMinimumRetailPriceOfComponents(minimumRetailPriceOfComponents);
		result.setMaximumRetailPriceOfComponents(maximumRetailPriceOfComponents);
		
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setAverageRetailPriceOfTools(averageRetailPriceOfTools);
		result.setDeviationRetailPriceOfTools(deviationRetailPriceOfTools);
		result.setMinimumRetailPriceOfTools(minimumRetailPriceOfTools);
		result.setMaximumRetailPriceOfTools(maximumRetailPriceOfTools);
		
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
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity,model,"totalNumberOfComponents","averageRetailPriceOfComponents","deviationRetailPriceOfComponents","minimumRetailPriceOfComponents","maximumRetailPriceOfComponents",
									"totalNumberOfTools","averageRetailPriceOfTools","deviationRetailPriceOfTools","minimumRetailPriceOfTools","maximumRetailPriceOfTools",
									"totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages",
									"averageBudgetOfProposedPatronages","deviationBudgetOfProposedPatronages","minimumBudgetOfProposedPatronages","maximumBudgetOfProposedPatronages",
									"averageBudgetOfAcceptedPatronages","deviationBudgetOfAcceptedPatronages","minimumBudgetOfAcceptedPatronages","maximumBudgetOfAcceptedPatronages",
									"averageBudgetOfDeniedPatronages","deviationBudgetOfDeniedPatronages","minimumBudgetOfDeniedPatronages","maximumBudgetOfDeniedPatronages"
									
			);
	}
	
}
