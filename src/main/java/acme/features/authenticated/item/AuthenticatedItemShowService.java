
package acme.features.authenticated.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedItemShowService implements AbstractShowService<Authenticated, Item> {

	@Autowired
	AuthenticatedItemRepository		repository;

	@Autowired
	AuthenticatedMessageRepository	messageRepository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ticker", "creationMoment", "title", "itemCategory", "description", "price", "photo", "link");
		model.setAttribute("item", entity.getId());

		Integer forum = this.messageRepository.findForumByItemId(entity.getId()).getId();
		model.setAttribute("forum", forum);
		request.setModel(model);

	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}
}
