
package acme.features.administrator.new_feature;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.news.New;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorNewCreateService implements AbstractCreateService<Administrator, New> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorNewRepository repository;


	@Override
	public boolean authorise(final Request<New> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<New> request, final New entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<New> request, final New entity, final Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "category", "picture", "title", "moment", "deadline", "body", "relatedNews", "confirmed");

	}

	@Override
	public New instantiate(final Request<New> request) {
		New result = new New();
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<New> request, final New entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!entity.isConfirmed()) {
			errors.state(request, false, "confirmed", "administrator.new.error.notConfirmed");
		}

	}

	@Override
	public void create(final Request<New> request, final New entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
