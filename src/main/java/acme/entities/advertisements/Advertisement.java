package acme.entities.advertisements;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Advertisement extends DomainEntity{
	
	private static final long	serialVersionUID	= 1L;
	

	@NotEmpty
	private String				title;

	@URL
	@NotEmpty
	private String				picture;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				creationMoment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				displayPeriod;
	
	@NotEmpty
	private String				text;
	
	@NotEmpty
	@Pattern(regexp = "^(SMALL|AVERAGE|LARGE)$")
	private String				discounts;
	
	private Double item;
	
	public String kindDiscounts() {
		if(this.item==3 || this.item==2) {
			 this.discounts.equals("SMALL");
		}else if(this.item==4 || this.item==5) {
			this.discounts.equals("AVERAGE");
		}else if(this.item>=6) {
			this.discounts.equals("LARGE");
		}else {
			return "No tiene descuento";
		}

		return discounts;
	}

	public Boolean	displayPeriodFuture;
	

}
