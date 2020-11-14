package acme.entities.figments;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Figment extends DomainEntity{
	
	private static final long	serialVersionUID	= 1L;
	

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				creationMoment;
	
	@NotBlank
	private String				nameInventor;
	
	@NotBlank
	private String				description;
	
	@Valid
	@NotNull
	private Money				minMoney;

	@Valid
	@NotNull
	private Money				maxMoney;
}