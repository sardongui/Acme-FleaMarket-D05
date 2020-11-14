package acme.features.supplier.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SupplierItemShowService  implements AbstractShowService<Supplier, Item>{

	@Autowired
	SupplierItemRepository repository;

	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;

		boolean result;
		int itemId;
		Item item;
		Supplier supplier;
		Principal principal;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findOneById(itemId);
		supplier = item.getSupplier();
		principal = request.getPrincipal();
		result = supplier.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "title", "itemCategory", "description", "price", "photo", "link");
	}

	@Override
	public Item findOne(Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}

}
