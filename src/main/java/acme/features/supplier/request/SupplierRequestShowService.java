package acme.features.supplier.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.RequestEntity;
import acme.entities.roles.Supplier;
import acme.framework.components.Model;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.framework.components.Request;

@Service
public class SupplierRequestShowService implements AbstractShowService<Supplier, RequestEntity>{

	@Autowired
	SupplierRequestRepository repository;


	@Override
	public boolean authorise(Request<RequestEntity> request) {
		assert request != null;

		boolean result;
		int requestId;
		RequestEntity r;
		Supplier supplier;
		Principal principal;

		requestId = request.getModel().getInteger("id");
		r = this.repository.findOneById(requestId);
		supplier = r.getItem().getSupplier();
		principal = request.getPrincipal();
		result = supplier.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(Request<RequestEntity> request, RequestEntity entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creation", "quantity", "notes", "item.ticker", "item.title",
				"item.itemCategory", "item.description", "item.price");
	
	}

	@Override
	public RequestEntity findOne(Request<RequestEntity> request) {
		assert request != null;

		RequestEntity result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}
}