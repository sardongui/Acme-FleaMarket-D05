package acme.features.supplier.request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.requests.RequestEntity;
import acme.framework.repositories.AbstractRepository;

public interface SupplierRequestRepository extends AbstractRepository{

	@Query("select r from RequestEntity r where r.item.supplier.id=?1 group by r.ticker, r.creation")
	Collection<RequestEntity> findManyBySupplierId(int supplierId);

	@Query("select r from RequestEntity r where r.id =?1")
	RequestEntity findOneById(int id);

}
