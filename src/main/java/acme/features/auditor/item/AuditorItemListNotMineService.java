
package acme.features.auditor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Auditor;
import acme.features.authenticated.auditor.AuthenticatedAuditorRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorItemListNotMineService implements AbstractListService<Auditor, Item> {

	@Autowired
	AuditorItemRepository					repository;

	@Autowired
	private AuthenticatedAuditorRepository	auditorRepository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		Auditor auditor = this.auditorRepository.findOneAuditorByUserAccountId(principal.getAccountId());

		assert auditor.isAccepted();

		return true;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");

	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findOthersByAuditorId(principal.getActiveRoleId());

		return result;
	}

}
