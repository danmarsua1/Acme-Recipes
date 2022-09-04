package acme.forms.patron;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatronDashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

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