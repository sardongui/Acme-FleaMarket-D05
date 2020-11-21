package acme.features.supplier.sections;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Supplier;
import acme.entities.sections.Section;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SupplierSectionListMineService implements AbstractListService<Supplier, Section>{

	@Autowired
	SupplierSectionRepository repository;

	@Override
	public boolean authorise(Request<Section> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(Request<Section> request, Section entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "indexer", "title", "description", "photo");
	}

	@Override
	public Collection<Section> findMany(Request<Section> request) {
		assert request != null;

		Collection<Section> result;
		Principal principal;
		
		principal =request.getPrincipal();

		result = this.repository.findManyBySupplierId(principal.getActiveRoleId());

		return result;
	}
}
