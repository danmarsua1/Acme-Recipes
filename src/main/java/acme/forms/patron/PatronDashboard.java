package acme.forms.patron;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

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