package acme.features.auditor.auditorRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecords.AuditRecord;

import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record/")
public class AuditorAuditRecordController  extends AbstractController<Auditor, AuditRecord>{


	// Internal state ---------------------------------------------------------

	@Autowired
	private AuditorAuditRecordListMineService	listMineService;
	
	@Autowired
	private AuditorAuditRecordListNotMineService	listNotMineService;

	@Autowired
	private AuditorAuditRecordShowService	showService;



	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.LIST_NOT_MINE, BasicCommand.LIST, this.listNotMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
