
package acme.features.administrator.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Chart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorChartShowService implements AbstractShowService<Administrator, Chart> {

	@Autowired
	AdministratorChartRepository repository;


	@Override
	public boolean authorise(final Request<Chart> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Chart> request, final Chart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberOfNewsGroupedByCategory", "ratioOfWarningNewsVersusRestOfit",
				"numberOfAdvertisementsGroupedByDiscount", "ratioOfItemsGroupedByItemCategory", 
				"ratioOfSponsorsGroupedByCreditCard");
	}

	@Override
	public Chart findOne(final Request<Chart> request) {
		assert request != null;

		Chart result = new Chart();
		Object[] numberOfNewsGroupedByCategory = this.repository.findNewsByCategory();
		result.setNumberOfNewsGroupedByCategory(numberOfNewsGroupedByCategory);

		Object[] ratioOfWarningNewsVersusRestOfit = this.repository.findRatioWarningNews();
		result.setRatioOfWarningNewsVersusRestOfit(ratioOfWarningNewsVersusRestOfit);

		Object[] numberOfAdvertisementsGroupedByDiscount = this.repository.findAdvertisementByDiscount();
		result.setNumberOfAdvertisementsGroupedByDiscount(numberOfAdvertisementsGroupedByDiscount);
		
		Object[] ratioOfItemsGroupedByItemCategory = this.repository.findItemsByCategory();
		result.setRatioOfItemsGroupedByItemCategory(ratioOfItemsGroupedByItemCategory);
		
		Object[] ratioOfSponsorsGroupedByCreditCard = this.repository.findSponsorByCreditCard();
		result.setRatioOfSponsorsGroupedByCreditCard(ratioOfSponsorsGroupedByCreditCard);
		

		return result;
	}

}
