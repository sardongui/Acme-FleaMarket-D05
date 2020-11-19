package acme.features.supplier.specificationSheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.specificationSheets.SpecificationSheet;
import acme.framework.repositories.AbstractRepository;

public interface SupplierSpecificationSheetRepository extends AbstractRepository{

	@Query("select i.specificationSheet from Item i where i.supplier.id=?1")
	Collection<SpecificationSheet> findManyBySupplierId(int supplierId);

	@Query("select r from SpecificationSheet r where r.id =?1")
	SpecificationSheet findOneById(int id);

}
