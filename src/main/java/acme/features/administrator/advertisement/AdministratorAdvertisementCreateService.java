package acme.features.administrator.advertisement;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.advertisements.Advertisement;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAdvertisementCreateService implements AbstractCreateService<Administrator, Advertisement>{

	// Internal state ------------------------------------------------------------------
	@Autowired
	AdministratorAdvertisementRepository repository;
	
	@Override
	public boolean authorise(Request<Advertisement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(Request<Advertisement> request, Advertisement entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<Advertisement> request, Advertisement entity, Model model) {

		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "picture", "displayPeriod","creationMoment", "text", "discounts", "displayPeriod");
		
	}

	@Override
	public Advertisement instantiate(Request<Advertisement> request) {
		Advertisement result = new Advertisement();
		Date moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		return result;
	}

	@Override
	public void validate(Request<Advertisement> request, Advertisement entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean isDisplayDateFuture;

		if (!errors.hasErrors("displayPeriod")) {
			Calendar calendar = new GregorianCalendar();
			Date currentMoment = calendar.getTime();
			isDisplayDateFuture = request.getModel().getDate("displayPeriod").after(currentMoment);
			errors.state(request, isDisplayDateFuture, "displayPeriod", "administrator.advertisement.error.displayPeriod");
		}
		
		
		
	}

	@Override
	public void create(Request<Advertisement> request, Advertisement entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		if(entity.getDisplayPeriod().before(moment)) {
			entity.setDisplayPeriodFuture(false);
		}
		entity.setDisplayPeriodFuture(true);
		this.repository.save(entity);
		
	}

}
