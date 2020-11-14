
package acme.entities.news;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class New extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------

	@NotBlank
	private String				category;

	@NotBlank
	@URL
	private String				picture;

	@NotBlank
	private String				title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Future(message = "{administrator.new.error.deadline}")
	private Date				deadline;

	@NotBlank
	private String				body;

	@URL
	private String				relatedNews;

	private boolean				confirmed;

}
