/*
 * AuthenticatedBuyerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.buyer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCards.CreditCard;
import acme.entities.requests.RequestEntity;
import acme.entities.roles.Buyer;
import acme.features.authenticated.buyer.creditCard.AuthenticatedBuyerCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBuyerCreateService implements AbstractCreateService<Authenticated, Buyer> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBuyerRepository			repository;

	@Autowired
	private AuthenticatedBuyerCreditCardRepository	creditCardRepository;

	// AbstractCreateService<Authenticated, Buyer> ---------------------------


	@Override
	public boolean authorise(final Request<Buyer> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Buyer> request, final Buyer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void bind(final Request<Buyer> request, final Buyer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Buyer> request, final Buyer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "email", "phone", "deliveryAddress");
		request.unbind(entity, model, "creditCard.holderName", "creditCard.number", "creditCard.brand", "creditCard.month", "creditCard.year", "creditCard.cvv");
	}

	@Override
	public Buyer instantiate(final Request<Buyer> request) {
		assert request != null;

		Buyer result = null;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Buyer();
		result.setUserAccount(userAccount);

		List<RequestEntity> requests = new ArrayList<RequestEntity>();

		result.setRequests(requests);

		return result;
	}

	@Override
	public void create(final Request<Buyer> request, final Buyer entity) {
		assert request != null;
		assert entity != null;

		CreditCard creditCard = new CreditCard();

		Model model = request.getModel();

		String holderName = (String) model.getAttribute("creditCard.holderName");
		String number = (String) model.getAttribute("creditCard.number");
		String brand = (String) model.getAttribute("creditCard.brand");
		Integer month = model.getInteger("creditCard.month");
		Integer year = model.getInteger("creditCard.year");
		String cvv = (String) model.getAttribute("creditCard.cvv");

		creditCard.setHolderName(holderName);
		creditCard.setNumber(number);
		creditCard.setBrand(brand);
		creditCard.setMonth(month);
		creditCard.setYear(year);
		creditCard.setCvv(cvv);

		entity.setCreditCard(creditCard);

		this.creditCardRepository.save(creditCard);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Buyer> request, final Response<Buyer> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
