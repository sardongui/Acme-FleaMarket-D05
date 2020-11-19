package acme.features.supplier.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisations.Customisation;
import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.entities.sections.Section;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.features.supplier.sections.SupplierSectionRepository;
import acme.features.supplier.specificationSheet.SupplierSpecificationSheetRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierItemCreateService implements AbstractCreateService<Supplier, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private SupplierItemRepository repository;
	
	@Autowired
	private SupplierSectionRepository sectionRepository;
	
	@Autowired
	private SupplierSpecificationSheetRepository specificationRepository;
		
	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "status", "finalMode", "creationMoment", "supplier", 
				"ticker");
		
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "title", "itemCategory", "description", "price", "photo", "link", 
				"specificationSheet.sections");
		
	}

	@Override
	public Item instantiate(Request<Item> request) {
		
		Item result;
		result = new Item();
		Date moment;
		
		Supplier supplier;
		SpecificationSheet ss = new SpecificationSheet();
		Section section = new Section();

		supplier = this.repository.findSupplierById(request.getPrincipal().getActiveRoleId());
		result.setSupplier(supplier);

		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setStatus("DRAFT");
		result.setFinalMode(false); 
		
		
		result.setSpecificationSheet(ss);
		ss.setSections(ss.getSections());
		
		return result;
	}

	@Override
	public void validate(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		Collection<String> tickers = this.repository.findAllTickers();
		String tickerUpdate = this.repository.findOneTickerById(request.getModel().getInteger("id"));
		tickers.remove(tickerUpdate);

		Customisation customisation = this.repository.findCustomisation();
		String[] CustomisationParameter;
		Integer n = 0;
		
	
		// Tickers repetidos
		if (!errors.hasErrors("ticker")) {
			errors.state(request, !tickers.contains(entity.getTicker()), "ticker", "supplier.item.form.error.tickerRepetido");
		}
		
		//Section indexers in its specification sheet are unique
		Collection<Section> sections = this.repository.findAllSections();
		if(!errors.hasErrors("specificationSheet")) {
			for(Section s: entity.getSpecificationSheet().getSections()) {	
				errors.state(request,!sections.contains(s.getIndexer()),  "specificationSheet", "supplier.item.form.error.indexSectionNoUnico");
			}
			
		}
				
		// Ticker incorrecto
		if (!errors.hasErrors("ticker")) {
			List<String> res = new ArrayList<>();
			Date moment = this.repository.findOneById(request.getModel().getInteger("id")).getCreationMoment();
			Integer year = moment.getYear() + 1900;

			res.add(entity.getTicker().substring(0, entity.getTicker().indexOf("-")));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 1, entity.getTicker().indexOf("-") + 3));
			res.add(entity.getTicker().substring(entity.getTicker().indexOf("-") + 4, entity.getTicker().length()));

			boolean result = res.get(0).matches("[A-Z ]+") && (res.get(0).equals(entity.getSupplier().getItemCategory().substring(0, 1).toUpperCase()) || res.get(0).equals(entity.getSupplier().getItemCategory().substring(0, 2).toUpperCase())
					|| res.get(0).equals(entity.getSupplier().getItemCategory().substring(0, 3).toUpperCase())) && res.get(1).equals(year.toString().substring(2)) && res.get(2).matches("^[0-9]{6}$");

			errors.state(request, result, "ticker", "supplier.item.form.error.tickerIncorrecto");
		}

		// Spam título
		if (!errors.hasErrors("title")) {

			Double spam = Double.valueOf(entity.getTitle().split(" ").length) * customisation.getThreshold() / 100.0;
			CustomisationParameter = customisation.getSpamwords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getTitle().toLowerCase();
				int i = l.indexOf(s);
				while (i != -1) {
					n++;
					l = l.substring(i + 1);
					i = l.indexOf(s);
				}
				errors.state(request, n <= spam, "title", "supplier.item.form.error.tituloConSpam");

				if (n > spam) {
					break;
				}
			}

		}

		// Spam descripción
		if (!errors.hasErrors("description")) {

			Double spam = Double.valueOf(entity.getDescription().split(" ").length) * customisation.getThreshold() / 100.0;
			CustomisationParameter = customisation.getSpamwords().split(",");

			for (String s : CustomisationParameter) {
				String l = entity.getDescription().toLowerCase();
				int i = l.indexOf(s);
				while (i != -1) {
					n++;
					l = l.substring(i + 1);
					i = l.indexOf(s);
				}
				errors.state(request, n <= spam, "description", "supplier.item.form.error.descripcionConSpam");

				if (n > spam) {
					break;
				}
			}

		}

	}

	@Override
	public void create(Request<Item> request, Item entity) {
		
		Date creationMoment;
		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		
		//Cuando se crea un item el estado esta en borrardor y no en modo final 
		entity.setStatus("DRAFT");
		entity.setFinalMode(false);
		
		SpecificationSheet ss = new SpecificationSheet();
		Section section = new Section();
		
		entity.setSpecificationSheet(ss);
		
		this.sectionRepository.save(section);
		this.specificationRepository.save(ss);
		this.repository.save(entity);
		
	}

}
