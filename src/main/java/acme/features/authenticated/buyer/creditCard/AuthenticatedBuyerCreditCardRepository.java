
package acme.features.authenticated.buyer.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.entities.roles.Buyer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBuyerCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c")
	Collection<CreditCard> findMany();

	@Query("select c from CreditCard c where c.id =?1")
	CreditCard findOneById(int id);

	@Query("select b from Buyer b where b.id =?1")
	Buyer findBuyerById(int activeRoleId);

	@Query("select b.creditCard from Buyer b where b.creditCard.id =?1")
	CreditCard findOneByBuyerId(int id);

}
