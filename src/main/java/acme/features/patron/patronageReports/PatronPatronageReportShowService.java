package acme.features.patron.patronageReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport> {

	@Autowired
	protected PatronPatronageReportRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result;
		int patronageReportId;
		PatronageReport patronageReport;
		
		patronageReportId = request.getModel().getInteger("id");
		patronageReport = this.repository.findOnePatronageReportById(patronageReportId);
		result = request.getPrincipal().getActiveRoleId() == patronageReport.getPatronage().getPatron().getId();
		
		return result;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		
		final PatronageReport report;
		int reportId;
		
		reportId = request.getModel().getInteger("id");
		report = this.repository.findOnePatronageReportById(reportId);
		
		return report;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "sequenceNumber", "creationMoment", "memorandum", "link");
	}
}