/*
 * AuthenticatedProviderUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.supplier.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requests.RequestEntity;
import acme.entities.requests.RequestEntityStatus;
import acme.entities.roles.Supplier;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class SupplierRequestUpdateService implements AbstractUpdateService<Supplier, RequestEntity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierRequestRepository repository;


	// AbstractUpdateService<Authenticated, Provider> interface ---------------

	@Override
	public boolean authorise(final Request<RequestEntity> request) {
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
	public void bind(final Request<RequestEntity> request, final RequestEntity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<RequestEntity> request, final RequestEntity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		String referenceItem = entity.getItem().getTicker();
		model.setAttribute("referenceItem", referenceItem);
		String itemSupplier = entity.getItem().getSupplier().getUserAccount().getUsername();
		model.setAttribute("itemSupplier", itemSupplier);

		request.unbind(entity, model, "quantity", "notes","status","rejectionJustification");
	}

	@Override
	public RequestEntity findOne(final Request<RequestEntity> request) {
		assert request != null;

		RequestEntity result;
			
		int id;
		
		id= request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<RequestEntity> request, final RequestEntity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean statusHasErrors = errors.hasErrors("status");
		if (!statusHasErrors) {
			boolean rejectionJustificationHasErrors = errors.hasErrors("rejectionJustification");
			if (!rejectionJustificationHasErrors) {
				boolean hasRejectionJustification = entity.getStatus() != RequestEntityStatus.REJECTED || entity.getStatus() == RequestEntityStatus.REJECTED && entity.getRejectionJustification().length() > 0;
				errors.state(request, hasRejectionJustification, "rejectionJustification", "supplier.request.form.error.rejectionJustification");
			}
		}
	}

	@Override
	public void update(final Request<RequestEntity> request, final RequestEntity entity) {
		assert request != null;
		assert entity != null;
		
		RequestEntity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		result.setStatus(entity.getStatus());
		if (result.getStatus() == RequestEntityStatus.REJECTED) {
			result.setRejectionJustification(entity.getRejectionJustification());
		}

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<RequestEntity> request, final Response<RequestEntity> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
