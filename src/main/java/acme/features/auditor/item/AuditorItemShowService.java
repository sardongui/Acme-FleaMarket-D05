package acme.features.auditor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorItemShowService implements AbstractShowService<Auditor, Item>{

	@Autowired
	AuditorItemRepository repository;

	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;

		//Si tengo este codigo de abajo no no se puede realizar el show de los items que no son mios
//		boolean result;
//		int itemId;
//		Item item;
//		Auditor auditor;
//		Principal principal;
//
//		itemId = request.getModel().getInteger("id");
//		item = this.repository.findOneById(itemId);
//		auditor = item.getAuditRecord().getAuditor();
//		principal = request.getPrincipal();
//		result = auditor.getUserAccount().getId() == principal.getAccountId();

		return true;
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
//		boolean result;
//		int itemId;
//		Item item;
//		Auditor auditor;
//		Principal principal;
//
//		itemId = request.getModel().getInteger("id");
//		item = this.repository.findOneById(itemId);
//		auditor = entity.getAuditRecords().
//		principal = request.getPrincipal();
//		result = auditor.getUserAccount().getId() == principal.getAccountId();
//		
//		if(model.getAttribute("myAuditRecord").equals(true)) {
//			model.setAttribute("myAuditRecord", true);
//		}
		
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
