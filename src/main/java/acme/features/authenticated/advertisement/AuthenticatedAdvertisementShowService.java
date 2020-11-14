package acme.features.authenticated.advertisement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.advertisements.Advertisement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAdvertisementShowService implements AbstractShowService<Authenticated, Advertisement> {

	// Internal state ------------------------------------------------------------------
		@Autowired
		AuthenticatedAdvertisementRepository repository;

		@Override
		public boolean authorise(final Request<Advertisement> request) {
			assert request != null;
			Boolean res = false;
			Principal principal = request.getPrincipal();
			if (principal.isAuthenticated()) {
				res = true;
			}
			return res;
		}

		@Override
		public Advertisement findOne(final Request<Advertisement> request) {
			assert request != null;

			Advertisement result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneById(id);

			return result;
		}

		@Override
		public void unbind(final Request<Advertisement> request, final Advertisement entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "picture", "title", "creationMoment", "displayPeriod", "text", "discounts");
		}
}
