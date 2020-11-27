package acme.features.auditor.item;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;

import acme.framework.services.AbstractListService;

@Service
public class AuditorItemListService implements AbstractListService<Auditor, Item> {

	@Autowired
	AuditorItemRepository					repository;

	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//Fecha actual
		Calendar today = Calendar.getInstance();
		int todayYear = today.get(Calendar.YEAR);
		int todayMonth = today.get(Calendar.MONTH);
		int todayDay = today.get(Calendar.DAY_OF_MONTH);
								
		LocalDate now = LocalDate.of(todayYear, todayMonth, todayDay); 
								
		//Fecha creacion
		Calendar creation = new GregorianCalendar();
		creation.setTime(entity.getCreationMoment());
		int creationYear = creation.get(Calendar.YEAR);
		int creationMonth = creation.get(Calendar.MONTH);
		int creationDay = creation.get(Calendar.DAY_OF_MONTH);
						
		LocalDate creationDate = LocalDate.of(creationYear, creationMonth, creationDay);
						
		long daysBetween = ChronoUnit.DAYS.between(creationDate, now);
		if(daysBetween<=7) {
			entity.setNewItem(true);
		}
		
		request.unbind(entity, model, "title", "creationMoment", "newItem");
		
	}

	@Override
	public Collection<Item> findMany(Request<Item> request) {
		assert request != null;

		Collection<Item> result;
		
		result = this.repository.findMany();

		return result;
	}

}
