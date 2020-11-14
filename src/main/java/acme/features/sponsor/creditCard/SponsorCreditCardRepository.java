
package acme.features.sponsor.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCards.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c")
	Collection<CreditCard> findMany();

	@Query("select c from CreditCard c where c.id =?1")
	CreditCard findOneById(int id);
	
	@Query("select b.creditCard from Banner b where b.creditCard.id =?1")
	CreditCard findOneByBannerId(int id);
	
	
}
