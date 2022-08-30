package acme.features.inventor.patronageReports;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronage.PatronageReport;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorPatronageReportController extends AbstractController<Inventor, PatronageReport> {

	@Autowired
	protected InventorPatronageReportListService listService;

	@Autowired
	protected InventorPatronageReportShowService showService;
	
	@Autowired
	protected InventorPatronageReportCreateService createService;
	
	@Autowired
	protected InventorPatronageReportConfirmService confirmService;


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("confirm","create", this.confirmService);
	}
}