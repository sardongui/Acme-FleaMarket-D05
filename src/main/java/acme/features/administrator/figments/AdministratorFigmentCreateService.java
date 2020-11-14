package acme.features.administrator.figments;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.figments.Figment;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorFigmentCreateService implements AbstractCreateService<Administrator, Figment>{

	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorFigmentRepository repository;
	
	@Override
	public boolean authorise(Request<Figment> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Figment> request, Figment entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<Figment> request, Figment entity, Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "nameInventor", "description","creationMoment", "minMoney", "maxMoney");
		
	}

	@Override
	public Figment instantiate(Request<Figment> request) {
		Figment result = new Figment();
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		return result;
	}

	@Override
	public void validate(Request<Figment> request, Figment entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isMinMoneyEuro, isMaxMoneyEuro, isMinMoneyLowerThanMaxMoney;
		
		if (!errors.hasErrors("minMoney")) {
			String minMoney = entity.getMinMoney().getCurrency();
			isMinMoneyEuro = minMoney.equals("€") || minMoney.equals("EUR");
			errors.state(request, isMinMoneyEuro, "minMoney", "administrator.figment.error.minMoney");
		}

		if (!errors.hasErrors("maxMoney")) {
			String maxMoney = entity.getMaxMoney().getCurrency();
			isMaxMoneyEuro = maxMoney.equals("€") || maxMoney.equals("EUR");
			errors.state(request, isMaxMoneyEuro, "maxMoney", "administrator.figment.error.maxMoney");
		}

		if (!errors.hasErrors("minMoney") && !errors.hasErrors("maxMoney")) {
			Double minMoney = entity.getMinMoney().getAmount();
			Double maxMoney = entity.getMaxMoney().getAmount();
			isMinMoneyLowerThanMaxMoney = minMoney < maxMoney;
			errors.state(request, isMinMoneyLowerThanMaxMoney, "minMoney", "administrator.figment.error.minMoneyLower");
		}
		
	}

	@Override
	public void create(Request<Figment> request, Figment entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
		
	}

}
