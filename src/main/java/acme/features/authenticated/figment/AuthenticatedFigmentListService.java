package acme.features.authenticated.figment;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.figments.Figment;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedFigmentListService implements AbstractListService<Authenticated, Figment>{

	// Internal state ---------------------------------------------------------

		@Autowired
		AuthenticatedFigmentRepository repository;


		@Override
		public boolean authorise(final Request<Figment> request) {
			assert request != null;
			Boolean result = false;
			Principal principal = request.getPrincipal();
			if (principal.isAuthenticated()) {
				result = true;
			}
			return result;
		}

		@Override
		public void unbind(final Request<Figment> request, final Figment entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			request.unbind(entity, model, "creationMoment", "title");
		}

		@Override
		public Collection<Figment> findMany(final Request<Figment> request) {
			assert request != null;
			Collection<Figment> result = this.repository.findMany();
			return result;
		}

}
