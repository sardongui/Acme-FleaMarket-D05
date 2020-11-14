package acme.features.administrator.customisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCustomisationUpdateService implements AbstractUpdateService<Administrator, Customisation>{
	
	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorCustomisationRepository repository;

	@Override
	public boolean authorise(Request<Customisation> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Customisation> request, Customisation entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Customisation> request, Customisation entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "spamwords", "threshold", "newsCategories", "itemsCategories");		
	}

	@Override
	public Customisation findOne(Request<Customisation> request) {
		assert request != null;

		Customisation result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(Request<Customisation> request, Customisation entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	
		
	}

	@Override
	public void update(Request<Customisation> request, Customisation entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
