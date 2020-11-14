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

package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.forums.Forum;
import acme.entities.items.Item;
import acme.entities.messages.Message;
import acme.features.authenticated.item.AuthenticatedItemRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository	repository;

	@Autowired
	private AuthenticatedItemRepository		itemRepository;


	// AbstractCreateService<Authenticated, Message> interface ---------------

	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "item", "forum");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creation", "tags", "body");
		Integer item = request.getModel().getInteger("item");
		model.setAttribute("item", item);

		Integer forum = request.getModel().getInteger("forum");
		model.setAttribute("forum", forum);

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result = new Message();
		Date creation = new Date(System.currentTimeMillis() - 1);
		result.setCreation(creation);

		Integer itemId = request.getModel().getInteger("item");
		Item item = this.itemRepository.findOneById(itemId);

		Integer forumId = request.getModel().getInteger("forum");
		Forum forum = this.repository.findForumById(forumId);

		forum.setItem(item);
		result.setForum(forum);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date creation = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(creation);

		this.repository.save(entity);
	}

}
