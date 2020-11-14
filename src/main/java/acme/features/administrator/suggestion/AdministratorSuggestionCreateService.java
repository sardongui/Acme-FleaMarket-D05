package acme.features.administrator.suggestion;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.suggestions.Suggestion;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorSuggestionCreateService implements AbstractCreateService<Administrator, Suggestion>{

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
	public Suggestion instantiate(Request<Suggestion> request) {
		Suggestion result = new Suggestion();
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		return result;
	}

	@Override
	public void validate(Request<Suggestion> request, Suggestion entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(Request<Suggestion> request, Suggestion entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
		
	}

}
