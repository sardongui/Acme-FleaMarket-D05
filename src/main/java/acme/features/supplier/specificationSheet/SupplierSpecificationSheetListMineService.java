package acme.features.supplier.specificationSheet;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Supplier;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SupplierSpecificationSheetListMineService implements AbstractListService<Supplier, SpecificationSheet>{

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
	public Collection<SpecificationSheet> findMany(Request<SpecificationSheet> request) {
		assert request != null;

		Collection<SpecificationSheet> result;
		Principal principal;
		
		principal =request.getPrincipal();

		result = this.repository.findManyBySupplierId(principal.getActiveRoleId());

		return result;
	}
}
