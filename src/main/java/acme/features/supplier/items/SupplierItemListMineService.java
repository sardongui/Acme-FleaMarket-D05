package acme.features.supplier.items;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SupplierItemListMineService implements AbstractListService<Supplier, Item>{

	@Autowired
	SupplierItemRepository repository;

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

		request.unbind(entity, model, "creationMoment", "title");
	}

	@Override
	public Collection<Item> findMany(Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		Principal principal;
		
		principal =request.getPrincipal();

		result = this.repository.findManyBySupplierId(principal.getActiveRoleId());

		return result;
	}

}
