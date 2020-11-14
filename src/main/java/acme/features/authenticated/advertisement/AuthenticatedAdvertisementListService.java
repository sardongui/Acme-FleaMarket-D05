package acme.features.authenticated.advertisement;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.advertisements.Advertisement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAdvertisementListService implements AbstractListService<Authenticated, Advertisement>{

	// Internal state ---------------------------------------------------------

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
		public Collection<Advertisement> findMany(final Request<Advertisement> request) {
			assert request != null;

			Collection<Advertisement> result = this.repository.findMany();

			return result;
		}

		@Override
		public void unbind(final Request<Advertisement> request, final Advertisement entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "title", "creationMoment");
			
		}

}
