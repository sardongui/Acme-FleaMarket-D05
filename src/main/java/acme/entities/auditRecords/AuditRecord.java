package acme.entities.auditRecords;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import acme.entities.items.Item;
import acme.entities.roles.Auditor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditRecord extends DomainEntity{

	// Serialisation identifier -----------------------------------------------
	private static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	private String title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date creationMoment;
	
	@NotBlank
	private String body;
	
	@NotBlank
	@Pattern(regexp = "^(DRAFT|PUBLISHED)$")
	private String				status;
	

	// Derived attributes -----------------------------------------------------
	
	@NotNull
	private boolean				finalMode;
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Auditor auditor;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	private Item item;


}
