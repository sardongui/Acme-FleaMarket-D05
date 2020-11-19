package acme.features.supplier.items;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.requests.RequestEntity;
import acme.entities.roles.Supplier;
import acme.entities.sections.Section;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.features.supplier.request.SupplierRequestRepository;
import acme.features.supplier.sections.SupplierSectionRepository;
import acme.features.supplier.specificationSheet.SupplierSpecificationSheetRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SupplierItemDeleteService implements AbstractDeleteService<Supplier, Item>{

	@Autowired
	private SupplierItemRepository repository;
	
	@Autowired
	private SupplierSpecificationSheetRepository specificationSheetRepository;
	
	@Autowired
	private SupplierSectionRepository sectionRepository;
	
	@Autowired
	private SupplierRequestRepository requestRepository;
	
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
		
		request.unbind(entity, model,"ticker", "creationMoment", "title", 
				"itemCategory", "description", "price", "photo", "link", "specificationSheet",
				"specificationSheet.sections.title", "specificationSheet.sections.indexer",
				"specificationSheet.sections.description");
		
		
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
		
		if(!errors.hasErrors("requests")) {
			errors.state(request, (!entity.getRequests().isEmpty()), "requests", "supplier.item.form.error.hasRequests");
			
		}
		
	}

	@Override
	public void delete(Request<Item> request, Item entity) {
		assert request != null;
		assert entity != null;
	
		Collection<RequestEntity> r = this.repository.findRequestByItemId(entity.getId());
		if(!r.isEmpty()) {
			for(RequestEntity re: r) {
				this.requestRepository.delete(re);
			}
		}
		SpecificationSheet ss = this.repository.findSpecificationSheetById(entity.getId());
		System.out.println(ss);
		for(Section section: ss.getSections()) {
			this.sectionRepository.delete(section);
			System.out.println(section);
		}
		this.specificationSheetRepository.delete(ss);
		this.repository.delete(entity);
		
	}

}
