
package acme.features.anonymous.martinezBulletin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.bulletins.MartinezBulletin;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/martinez-bulletin/")
public class AnonymousMartinezBulletinController extends AbstractController<Anonymous, MartinezBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousMartinezBulletinListService	listService;
	@Autowired
	private AnonymousMartinezBulletinCreateService	createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
