package acme.features.supplier.items;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/supplier/item/")
public class SupplierItemController  extends AbstractController<Supplier, Item>{


	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierItemListMineService	listMineService;

	@Autowired
	private SupplierItemShowService	showService;

	@Autowired
	private SupplierItemCreateService createService;
	
	@Autowired
	private SupplierItemUpdateService updateService;
	
	@Autowired
	private SupplierItemDeleteService deleteService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
