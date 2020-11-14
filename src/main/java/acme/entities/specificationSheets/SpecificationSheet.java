package acme.entities.specificationSheets;


import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.sections.Section;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SpecificationSheet extends DomainEntity {

	private static final long	serialVersionUID	= 1L;
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@OneToMany()
	private Collection<@Valid Section> sections;
	

}
