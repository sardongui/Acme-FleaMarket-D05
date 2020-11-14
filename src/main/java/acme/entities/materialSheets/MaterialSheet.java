
package acme.entities.materialSheets;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MaterialSheet extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------

	@NotBlank
	private String				title;

	@NotBlank
	private String				description;

	@NotBlank
	private String				providerName;

	@NotBlank
	@URL
	private String				providerHomePage;

	@Range(min = 0, max = 5)
	private Integer				rating;

}
