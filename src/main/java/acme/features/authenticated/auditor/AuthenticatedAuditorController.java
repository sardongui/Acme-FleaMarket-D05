
package acme.features.authenticated.auditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/auditor/")
public class AuthenticatedAuditorController extends AbstractController<Authenticated, Auditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedAuditorCreateService	createService;

	@Autowired
	private AuthenticatedAuditorUpdateService	updateService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
