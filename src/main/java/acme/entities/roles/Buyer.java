/*
 * Consumer.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.creditCards.CreditCard;
import acme.entities.requests.RequestEntity;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Buyer extends UserRole {

	// Serialisation identifier -----------------------------------------------

	private static final long			serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Email
	private String						email;

	@NotBlank
	@Pattern(regexp = "^\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9]) \\(([1-9]|[1-9][0-9]|[1-9][0-9][0-9])\\) ([0-9]{4,})$|^\\+([1-9]|[1-9][0-9]|[1-9][0-9][0-9]) ([0-9]{4,})$|^([0-9]{4,})$", message = "{buyer.error.phone}")
	private String						phone;

	@NotBlank
	private String						deliveryAddress;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

	@Valid
	@NotNull
	@OneToOne(optional = false)
	private CreditCard					creditCard;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "buyer")
	private Collection<RequestEntity>	requests;

}
