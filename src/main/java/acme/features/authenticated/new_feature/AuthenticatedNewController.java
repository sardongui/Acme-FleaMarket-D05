
package acme.features.authenticated.new_feature;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.news.New;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/new/")
public class AuthenticatedNewController extends AbstractController<Authenticated, New> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedNewListService	listService;

	@Autowired
	private AuthenticatedNewShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
