
package acme.features.buyer.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requests.RequestEntity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BuyerRequestRepository extends AbstractRepository {

	@Query("select r from RequestEntity r where r.buyer.id=?1")
	Collection<RequestEntity> findManyByBuyerId(int buyerId);

	@Query("select r from RequestEntity r where r.id =?1")
	RequestEntity findOneById(int id);
}
