
package acme.features.sponsor.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.features.sponsor.banner.SponsorBannerRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorCreditCardDeleteService implements AbstractDeleteService<Sponsor, CreditCard> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	SponsorCreditCardRepository	repository;

	@Autowired
	SponsorBannerRepository		bannerRepository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "banner");
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "holderName", "number", "brand", "month", "year", "cvv");
		Integer banner = request.getModel().getInteger("banner");
		model.setAttribute("banner", banner);
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		Integer id = request.getModel().getInteger("banner");
		Banner banner = this.bannerRepository.findOneById(id);
		banner.setCreditCard(null);

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.bannerRepository.findSponsorById(principal.getActiveRoleId());
		sponsor.setCreditCard(null);

		this.repository.delete(entity);

	}

}
