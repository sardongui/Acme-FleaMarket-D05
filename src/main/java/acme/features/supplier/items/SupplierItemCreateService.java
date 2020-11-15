package acme.features.supplier.items;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierItemCreateService implements AbstractCreateService<Supplier, Item> {

	// Internal state ---------------------------------------------------------

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

		request.bind(entity, errors, "status", "finalMode", "creationMoment", "supplier");
		
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "ticker", "title", "itemCategory", "description", "price", "photo", "link");
		
	}

	@Override
	public Item instantiate(Request<Item> request) {
		
		Item result;
		result = new Item();
		Date moment;
		
		Supplier supplier;

		supplier = this.repository.findSupplierById(request.getPrincipal().getActiveRoleId());
		result.setSupplier(supplier);

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setSupplier(supplier);
		result.setStatus("DRAFT");
		result.setFinalMode(false); 
		
		return result;
	}

	@Override
	public void validate(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void create(Request<Item> request, Item entity) {
		
		Date creationMoment;
		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		//Cuando se crea un item el estado esta en borrardor y no en modo final 
		entity.setStatus("DRAFT");
		entity.setFinalMode(false);
		
		this.repository.save(entity);
		
	}

}
