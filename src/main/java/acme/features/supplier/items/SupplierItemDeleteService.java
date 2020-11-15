package acme.features.supplier.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SupplierItemDeleteService implements AbstractDeleteService<Supplier, Item>{

	@Autowired
	private SupplierItemRepository repository;
	
	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
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

	@Override
	public void validate(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(Request<Item> request, Item entity) {
		// TODO Auto-generated method stub
		
	}

}
