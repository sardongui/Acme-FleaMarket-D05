package acme.features.supplier.items;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.roles.Supplier;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SupplierItemRepository extends AbstractRepository{

	@Query("select i from Item i where i.supplier.id=?1")
	Collection<Item> findManyBySupplierId(int supplierId);

	@Query("select i from Item i where i.id =?1")
	Item findOneById(int id);

	@Query("select s from Supplier s where s.id =?1")
	Supplier findSupplierById(int id);
}
