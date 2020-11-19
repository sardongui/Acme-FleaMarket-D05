
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chart implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	Object[]					numberOfNewsGroupedByCategory;
	Object[]					ratioOfWarningNewsVersusRestOfit;
	Object[]					numberOfAdvertisementsGroupedByDiscount;
	Object[]					ratioOfItemsGroupedByItemCategory;
	Object[]					ratioOfSponsorsGroupedByCreditCard;
	Object[] 					ratioOfRequestsGroupedByStatus;
	Object[]					numberOfRejectedRequestsLastThreeWeeks;
	Object[]					numberOfPendingRequestsLastThreeWeeks;
	Object[]					numberOfAcceptedRequestsLastThreeWeeks;
	String[]					allDatesBeforeThreeWeeks;
	

}
