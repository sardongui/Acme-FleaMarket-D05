package acme.features.supplier.specificationSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Supplier;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class SupplierSpecificationSheetShowService implements AbstractShowService<Supplier, SpecificationSheet>{

	@Autowired
	SupplierSpecificationSheetRepository repository;


	@Override
	public boolean authorise(Request<SpecificationSheet> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<SpecificationSheet> request, SpecificationSheet entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sections.title");
	
	}

	@Override
	public SpecificationSheet findOne(Request<SpecificationSheet> request) {
		assert request != null;

		SpecificationSheet result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}
}