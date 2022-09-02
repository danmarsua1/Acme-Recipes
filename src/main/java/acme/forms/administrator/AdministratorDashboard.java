package acme.forms.administrator;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberOfComponents;

	List<Object>						averageRetailPriceOfComponents;

	List<Object>						deviationRetailPriceOfComponents;

	List<Object>						minimumRetailPriceOfComponents;

	List<Object>						maximumRetailPriceOfComponents;

	Integer						totalNumberOfTools;

	List<Object>						averageRetailPriceOfTools;

	List<Object>						deviationRetailPriceOfTools;

	List<Object>						minimumRetailPriceOfTools;

	List<Object>						maximumRetailPriceOfTools;

	Integer						totalNumberOfProposedPatronages;

	Integer						totalNumberOfAcceptedPatronages;

	Integer						totalNumberOfDeniedPatronages;

	List<Object>						averageBudgetOfProposedPatronages;

	List<Object>						deviationBudgetOfProposedPatronages;

	List<Object>						minimumBudgetOfProposedPatronages;

	List<Object>						maximumBudgetOfProposedPatronages;

	List<Object>						averageBudgetOfAcceptedPatronages;

	List<Object>						deviationBudgetOfAcceptedPatronages;

	List<Object>						minimumBudgetOfAcceptedPatronages;

	List<Object>						maximumBudgetOfAcceptedPatronages;

	List<Object>						averageBudgetOfDeniedPatronages;

	List<Object>						deviationBudgetOfDeniedPatronages;

	List<Object>						minimumBudgetOfDeniedPatronages;

	List<Object>						maximumBudgetOfDeniedPatronages;

}