
package acme.entities.creditCards;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditCard extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				holderName;

	@NotBlank
	@CreditCardNumber
	private String				number;

	@NotBlank
	private String				brand;

	@NotNull
	@Range(min = 0, max = 12)
	private Integer				month;

	@NotNull
	private Integer				year;

	@NotBlank
	@Pattern(regexp = "^\\d{3,4}$", message = "{creditCard.error.cvv}")
	private String				cvv;

	private boolean				expired;


	@PrePersist
	@PreUpdate
	public void checkExpiration() {

		Calendar calendar = new GregorianCalendar();
		calendar.set(this.year, this.month, 1);
		Date date = calendar.getTime();

		Date currentTime = new Date(System.currentTimeMillis() - 1000);

		if (date.before(currentTime)) {
			this.expired = true;
		} else {
			this.expired = false;
		}

	}

}
