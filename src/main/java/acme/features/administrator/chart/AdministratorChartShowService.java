
package acme.features.administrator.chart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
				"ratioOfSponsorsGroupedByCreditCard","numberOfRejectedRequestsLastThreeWeeks",
				"numberOfPendingRequestsLastThreeWeeks", "numberOfAcceptedRequestsLastThreeWeeks",
				"allDatesBeforeThreeWeeks", "ratioOfRequestsGroupedByStatus");
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
		
		Object[] requestsByStatement = this.repository.findRequestStatus();
		result.setRatioOfRequestsGroupedByStatus(requestsByStatement);
		
		Calendar calendar;
		String[] allDatesBeforeThreeWeeks = new String[15];

		calendar = new GregorianCalendar();
		calendar.setTime(new Date(System.currentTimeMillis()));
		calendar.add(Calendar.DATE, -15);
		Object[] rejectedRequestsByDays = this.repository.findRejectedRequestsLastThreeWeeks(calendar.getTime());
		result.setNumberOfRejectedRequestsLastThreeWeeks(rejectedRequestsByDays);
		Object[] pendingRequestsByDays = this.repository.findPendingRequestsLastThreeWeeks(calendar.getTime());
		result.setNumberOfPendingRequestsLastThreeWeeks(pendingRequestsByDays);
		Object[] acceptedRequestsByDays = this.repository.findAcceptedRequestsLastThreeWeeks(calendar.getTime());
		result.setNumberOfAcceptedRequestsLastThreeWeeks(acceptedRequestsByDays);

		//Obteniendo todas las fechas de 15 días anteriores = 3 semanas anteriores
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		for (Integer i = 0; i < 15; i++) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			allDatesBeforeThreeWeeks[i] = formatoFecha.format(calendar.getTime());
		}
		result.setAllDatesBeforeThreeWeeks(allDatesBeforeThreeWeeks);	

		return result;
	}

}
