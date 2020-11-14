package acme.entities.suggestions;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Suggestion extends DomainEntity {
	
	private static final long	serialVersionUID	= 1L;
	
	@NotEmpty
	private String				title;

	@NotEmpty
	private String				description;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				creationMoment;
	
	@NotEmpty
	@Email
	private String				email;

}
