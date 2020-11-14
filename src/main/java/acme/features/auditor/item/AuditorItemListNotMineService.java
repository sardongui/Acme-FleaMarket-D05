package acme.features.auditor.item;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorItemListNotMineService implements AbstractListService<Auditor, Item>{

	@Autowired
	AuditorItemRepository repository;
	
	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "creationMoment");
		
	}

	@Override
	public Collection<Item> findMany(Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findOthersByAuditorId(principal.getActiveRoleId());

		return result;
	}

}
