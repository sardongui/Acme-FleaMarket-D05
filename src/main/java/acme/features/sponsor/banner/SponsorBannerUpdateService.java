
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
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorBannerUpdateService implements AbstractUpdateService<Sponsor, Banner> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	SponsorBannerRepository							repository;

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

		//CREDITCARD CHECKS
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		if (entity.getCreditCard() != null) {
			model.setAttribute("bannerHasCreditCard", entity.getCreditCard().getId() != 0);
		} else {
			model.setAttribute("bannerHasCreditCard", entity.getCreditCard() != null);
		}

		if (sponsor.getCreditCard() != null) {
			model.setAttribute("sponsorHasCreditCard", sponsor.getCreditCard().getId() != 0);
			model.setAttribute("sponsorCreditCard", sponsor.getCreditCard().getId());
			model.setAttribute("isExpired", sponsor.getCreditCard().isExpired());
		} else {
			model.setAttribute("sponsorHasCreditCard", sponsor.getCreditCard() != null);
		}

		Collection<Banner> banners = this.repository.anyCreditCardLinked(principal.getActiveRoleId());
		model.setAttribute("creditCardLinked", banners.size() > 0);

	}

	@Override
	public Banner findOne(final Request<Banner> request) {
		assert request != null;

		Banner result;

		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Banner> request, final Banner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//CREDITCARD CHECKS
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		Model model = request.getModel();

		if (entity.getCreditCard() != null) {
			model.setAttribute("bannerHasCreditCard", entity.getCreditCard().getId() != 0);
		} else {
			model.setAttribute("bannerHasCreditCard", entity.getCreditCard() != null);
		}

		if (sponsor.getCreditCard() != null) {
			model.setAttribute("sponsorHasCreditCard", sponsor.getCreditCard().getId() != 0);
			model.setAttribute("sponsorCreditCard", sponsor.getCreditCard().getId());
			model.setAttribute("isExpired", sponsor.getCreditCard().isExpired());
		} else {
			model.setAttribute("sponsorHasCreditCard", sponsor.getCreditCard() != null);
		}

		Collection<Banner> banners = this.repository.anyCreditCardLinked(principal.getActiveRoleId());
		model.setAttribute("creditCardLinked", banners.size() > 0);

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

	}

	@Override
	public void update(final Request<Banner> request, final Banner entity) {
		assert request != null;
		assert entity != null;

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		CreditCard creditCard = sponsor.getCreditCard();

		//Linkear creditCard al banner actual
		if (request.getModel().hasAttribute("linkTo")) {
			boolean linkTo = request.getModel().getBoolean("linkTo");
			if (creditCard != null && linkTo == true) {
				entity.setCreditCard(creditCard);
			}
		}

		this.repository.save(entity);

	}

}
