/*
 * AuthenticatedMessageCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.supplier.sections;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.entities.sections.Section;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.features.supplier.items.SupplierItemRepository;
import acme.features.supplier.specificationSheet.SupplierSpecificationSheetRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierSectionCreateService implements AbstractCreateService<Supplier, Section> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierSectionRepository	repository;
	
	@Autowired
	private SupplierItemRepository	itemRepository;


	@Autowired
	private SupplierSpecificationSheetRepository		specificationRepository;


	// AbstractCreateService<Authenticated, Message> interface ---------------

	@Override
	public boolean authorise(final Request<Section> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Section> request, final Section entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "item", "specificationSheet");
	}

	@Override
	public void unbind(final Request<Section> request, final Section entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "indexer", "description", "photo");
		Integer item = request.getModel().getInteger("item");
		model.setAttribute("item", item);

		Integer specificationSheet = request.getModel().getInteger("specificationSheet");
		model.setAttribute("specificationSheet", specificationSheet);

	}

	@Override
	public Section instantiate(final Request<Section> request) {
		Section result = new Section();
		return result;
	}

	@Override
	public void validate(final Request<Section> request, final Section entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Section> request, final Section entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
		Integer id = request.getModel().getInteger("item");
		SpecificationSheet specificationSheet = this.itemRepository.findSpecificationSheetById(id);
		Item item = this.itemRepository.findOneById(id);
		item.setSpecificationSheet(specificationSheet);
		specificationSheet.setSections((Collection<Section>) entity);
		
		Principal principal = request.getPrincipal();
		Supplier supplier = this.itemRepository.findSupplierById(principal.getActiveRoleId());
	}

}
