package acme.features.supplier.specificationSheet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Supplier;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/specification-sheet/")
public class SupplierSpecificationSheetController  extends AbstractController<Supplier, SpecificationSheet>{


	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierSpecificationSheetListMineService	listMineService;

	@Autowired
	private SupplierSpecificationSheetShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
