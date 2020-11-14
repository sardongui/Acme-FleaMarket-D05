package acme.features.administrator.suggestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.suggestions.Suggestion;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorSuggestionDeleteService implements AbstractDeleteService<Administrator, Suggestion> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorSuggestionRepository repository;
	
	@Override
	public boolean authorise(Request<Suggestion> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Suggestion> request, Suggestion entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(Request<Suggestion> request, Suggestion entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "creationMoment", "email");
		
	}

	@Override
	public Suggestion findOne(Request<Suggestion> request) {
		assert request != null;

		Suggestion result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(Request<Suggestion> request, Suggestion entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(Request<Suggestion> request, Suggestion entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
		
	}

}
