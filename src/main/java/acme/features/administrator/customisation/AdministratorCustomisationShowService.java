package acme.features.administrator.customisation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;
import java.util.List;

@Service
public class AdministratorCustomisationShowService implements AbstractShowService<Administrator, Customisation> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorCustomisationRepository repository;
	
	@Override
	public boolean authorise(Request<Customisation> request) {
		assert request != null;
		return true;
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
		List<Customisation> res = (List<Customisation>) this.repository.findMany();
		Customisation result = res.get(0);
		return result;
	}

}
