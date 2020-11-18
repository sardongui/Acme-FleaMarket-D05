
package acme.features.sponsor.banner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.creditCards.CreditCard;
import acme.entities.customisations.Customisation;
import acme.entities.roles.Sponsor;
import acme.features.administrator.customisation.AdministratorCustomisationRepository;
import acme.features.sponsor.creditCard.SponsorCreditCardRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorBannerCreateService implements AbstractCreateService<Sponsor, Banner> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	SponsorBannerRepository							repository;

	@Autowired
	SponsorCreditCardRepository						creditCardRepository;

	@Autowired
	private AdministratorCustomisationRepository	customisationRepository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target");
		request.unbind(entity, model, "creditCard.holderName", "creditCard.number", "creditCard.brand", "creditCard.month", "creditCard.year", "creditCard.cvv");

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		CreditCard creditCard = sponsor.getCreditCard();

		Collection<Banner> banners = this.repository.anyCreditCardLinked(principal.getActiveRoleId());

		model.setAttribute("creditCardLinked", banners.size() > 0);

		if (banners.size() <= 0) {

			model.setAttribute("hasCreditCard", creditCard != null);

			if (creditCard != null) {
				model.setAttribute("isExpired", creditCard.isExpired());
			}

			if (creditCard != null && !creditCard.isExpired()) {
				model.setAttribute("creditCard.holderName", sponsor.getCreditCard().getHolderName());
				model.setAttribute("creditCard.number", sponsor.getCreditCard().getNumber());
				model.setAttribute("creditCard.brand", sponsor.getCreditCard().getBrand());
				model.setAttribute("creditCard.month", sponsor.getCreditCard().getMonth());
				model.setAttribute("creditCard.year", sponsor.getCreditCard().getYear());
				model.setAttribute("creditCard.cvv", sponsor.getCreditCard().getCvv());
			}

		}

	}

	@Override
	public Banner instantiate(final Request<Banner> request) {
		Banner result = new Banner();
		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//SPAM CHECK
		List<Customisation> customisations = new ArrayList<Customisation>(this.customisationRepository.findMany());
		Customisation customisation = customisations.get(0);
		String spam = customisation.getSpamwords();

		String[] spamWords = spam.split(",");
		String picture = entity.getPicture();
		String slogan = entity.getSlogan();
		String target = entity.getTarget();

		for (String s : spamWords) {
			if (picture.contains(s)) {
				errors.state(request, false, "picture", "sponsor.banner.error.spam");
			}
			if (slogan.contains(s)) {
				errors.state(request, false, "slogan", "sponsor.banner.error.spam");
			}
			if (target.contains(s)) {
				errors.state(request, false, "target", "sponsor.banner.error.spam");
			}
		}

		//CreditCard checks
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		CreditCard creditCard = sponsor.getCreditCard();

		Model model = request.getModel();

		Collection<Banner> banners = this.repository.anyCreditCardLinked(principal.getActiveRoleId());
		model.setAttribute("creditCardLinked", banners.size() > 0);

		if (banners.size() <= 0) {

			model.setAttribute("hasCreditCard", creditCard != null);

			if (creditCard != null) {
				model.setAttribute("isExpired", creditCard.isExpired());
			}

			if (creditCard != null && !creditCard.isExpired()) {
				model.setAttribute("creditCard.holderName", sponsor.getCreditCard().getHolderName());
				model.setAttribute("creditCard.number", sponsor.getCreditCard().getNumber());
				model.setAttribute("creditCard.brand", sponsor.getCreditCard().getBrand());
				model.setAttribute("creditCard.month", sponsor.getCreditCard().getMonth());
				model.setAttribute("creditCard.year", sponsor.getCreditCard().getYear());
				model.setAttribute("creditCard.cvv", sponsor.getCreditCard().getCvv());
			}

		}

	}

	@Override
	public void create(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		Collection<Banner> banners = this.repository.anyCreditCardLinked(principal.getActiveRoleId());

		sponsor.getBanners().add(entity);

		if (banners.size() <= 0) {

			CreditCard creditCard = sponsor.getCreditCard();

			if (creditCard != null && !creditCard.isExpired()) {
				entity.setCreditCard(creditCard);
			}

		} else {
			entity.setCreditCard(null);
		}

		this.repository.save(entity);
	}

}
