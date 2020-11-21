package acme.features.supplier.sections;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.sections.Section;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.framework.repositories.AbstractRepository;

public interface SupplierSectionRepository extends AbstractRepository{

	@Query("select r.specificationSheet.sections from Item r where r.supplier.id=?1")
	Collection<Section> findManyBySupplierId(int supplierId);

	@Query("select r from Section r where r.id =?1")
	Section findOneById(int id);

	@Query("select i.specificationSheet from Item i where i.specificationSheet.id=?1")
	SpecificationSheet findSpecificationById(Integer specificationSheetId);

	@Query("select r from Section r")
	Collection<Section> findMany();

}
