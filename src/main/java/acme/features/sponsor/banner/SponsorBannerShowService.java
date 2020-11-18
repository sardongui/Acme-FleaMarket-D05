
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Banner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorBannerShowService implements AbstractShowService<Sponsor, Banner> {

	@Autowired
	SponsorBannerRepository repository;


	@Override
	public boolean authorise(final Request<Banner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Banner> request, final Banner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		model.setAttribute("bannerHasCreditCard", entity.getCreditCard() != null);

		model.setAttribute("sponsorHasCreditCard", sponsor.getCreditCard() != null);

		if (sponsor.getCreditCard() != null) {
			model.setAttribute("sponsorCreditCard", sponsor.getCreditCard().getId());
			model.setAttribute("isExpired", sponsor.getCreditCard().isExpired());
		}

		Collection<Banner> banners = this.repository.anyCreditCardLinked(principal.getActiveRoleId());
		model.setAttribute("creditCardLinked", banners.size() > 0);

		int id = entity.getId();
		model.setAttribute("banner", id);

		request.unbind(entity, model, "picture", "slogan", "target", "creditCard.number");
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
}
