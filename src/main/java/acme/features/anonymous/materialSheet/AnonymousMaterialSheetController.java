
package acme.features.anonymous.materialSheet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.materialSheets.MaterialSheet;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/material-sheet/")
public class AnonymousMaterialSheetController extends AbstractController<Anonymous, MaterialSheet> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AnonymousMaterialSheetListService	listService;

	@Autowired
	private AnonymousMaterialSheetShowService	showService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
