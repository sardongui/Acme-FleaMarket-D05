package acme.features.supplier.sections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Supplier;
import acme.entities.sections.Section;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/section/")
public class SupplierSectionController  extends AbstractController<Supplier, Section>{


	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierSectionListMineService	listMineService;

	@Autowired
	private SupplierSectionShowService	showService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
