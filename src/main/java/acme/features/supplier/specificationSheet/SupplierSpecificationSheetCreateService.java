
package acme.features.supplier.specificationSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.entities.roles.Supplier;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.features.sponsor.banner.SponsorBannerRepository;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.features.supplier.items.SupplierItemRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SupplierSpecificationSheetCreateService implements AbstractCreateService<Supplier, SpecificationSheet> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	SupplierSpecificationSheetRepository	repository;

	@Autowired
	SupplierItemRepository		itemRepository;


	@Override
	public boolean authorise(final Request<SpecificationSheet> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "item");
	}

	@Override
	public void unbind(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "sections");
		Integer item = request.getModel().getInteger("item");
		model.setAttribute("item", item);
	}

	@Override
	public SpecificationSheet instantiate(final Request<SpecificationSheet> request) {
		SpecificationSheet result = new SpecificationSheet();
		return result;
	}

	@Override
	public void validate(final Request<SpecificationSheet> request, final SpecificationSheet entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<SpecificationSheet> request, final SpecificationSheet entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

//		Integer id = request.getModel().getInteger("banner");
//		Banner banner = this.bannerRepository.findOneById(id);
//		banner.setCreditCard(entity);
//
//		Principal principal = request.getPrincipal();
//		Sponsor sponsor = this.bannerRepository.findSponsorById(principal.getActiveRoleId());
//		sponsor.setCreditCard(entity);
	}

}
