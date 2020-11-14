
package acme.features.administrator.new_feature;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.news.New;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNewListService implements AbstractListService<Administrator, New> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorNewRepository repository;


	@Override
	public boolean authorise(final Request<New> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<New> request, final New entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "category", "picture", "title", "moment", "deadline", "body", "relatedNews", "confirmed");

	}

	@Override
	public Collection<New> findMany(final Request<New> request) {
		assert request != null;

		Collection<New> result = this.repository.findMany();

		return result;
	}

}
