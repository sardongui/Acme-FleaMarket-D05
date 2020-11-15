package acme.entities.sections;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Section extends DomainEntity{
	
	private static final long	serialVersionUID	= 1L;
	
	@NotNull
	@Column(unique = true)
	@Min(0)
	private Integer indexer;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String description;
	
	@URL
	private String photo;
	
	// Relationships ----------------------------------------------------------
//	@NotNull
//	@Valid
//	@ManyToOne(optional = false)
//	private SpecificationSheet				specificationSheet;

}
