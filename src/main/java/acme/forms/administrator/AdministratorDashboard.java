package acme.forms.administrator;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberOfComponents;

	Double						averageRetailPriceOfComponents;

	Double						deviationRetailPriceOfComponents;

	Double						minimumRetailPriceOfComponents;

	Double						maximumRetailPriceOfComponents;

	Integer						totalNumberOfTools;

	Double						averageRetailPriceOfTools;

	Double						deviationRetailPriceOfTools;

	Double						minimumRetailPriceOfTools;

	Double						maximumRetailPriceOfTools;

	Integer						totalNumberOfProposedPatronages;

	Integer						totalNumberOfAcceptedPatronages;

	Integer						totalNumberOfDeniedPatronages;

	Double						averageBudgetOfProposedPatronages;

	Double						deviationBudgetOfProposedPatronages;

	Double						minimumBudgetOfProposedPatronages;

	Double						maximumBudgetOfProposedPatronages;

	Double						averageBudgetOfAcceptedPatronages;

	Double						deviationBudgetOfAcceptedPatronages;

	Double						minimumBudgetOfAcceptedPatronages;

	Double						maximumBudgetOfAcceptedPatronages;

	Double						averageBudgetOfDeniedPatronages;

	Double						deviationBudgetOfDeniedPatronages;

	Double						minimumBudgetOfDeniedPatronages;

	Double						maximumBudgetOfDeniedPatronages;

}