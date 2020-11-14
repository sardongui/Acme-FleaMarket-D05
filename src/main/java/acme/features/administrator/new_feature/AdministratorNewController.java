
package acme.features.administrator.new_feature;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.news.New;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/new/")
public class AdministratorNewController extends AbstractController<Administrator, New> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorNewListService		listService;

	@Autowired
	private AdministratorNewShowService		showService;

	@Autowired
	private AdministratorNewCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
