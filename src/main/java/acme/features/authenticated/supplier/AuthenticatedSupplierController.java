package acme.features.authenticated.supplier;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Supplier;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/supplier/")
public class AuthenticatedSupplierController  extends AbstractController<Authenticated, Supplier>{


	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedSupplierCreateService	createService;

	@Autowired
	private AuthenticatedSupplierUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
