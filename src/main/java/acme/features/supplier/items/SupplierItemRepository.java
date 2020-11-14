package acme.features.supplier.items;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierItemRepository extends AbstractRepository{

	@Query("select i from Item i where i.supplier.id=?1")
	Collection<Item> findManyBySupplierId(int supplierId);

	@Query("select i from Item i where i.id =?1")
	Item findOneById(int id);
}
