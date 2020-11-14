
package acme.features.administrator.toolSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolSheets.ToolSheet;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorToolSheetUpdateService implements AbstractUpdateService<Administrator, ToolSheet> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorToolSheetRepository repository;


	@Override
	public boolean authorise(final Request<ToolSheet> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<ToolSheet> request, final ToolSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<ToolSheet> request, final ToolSheet entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "providerName", "providerHomePage", "rating");

	}

	@Override
	public ToolSheet findOne(final Request<ToolSheet> request) {
		assert request != null;

		ToolSheet result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<ToolSheet> request, final ToolSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<ToolSheet> request, final ToolSheet entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
