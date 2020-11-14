
package acme.features.anonymous.toolSheet;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolSheets.ToolSheet;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousToolSheetRepository extends AbstractRepository {

	@Query("Select t from ToolSheet t")
	Collection<ToolSheet> findMany();

	@Query("select t from ToolSheet t where t.id =?1")
	ToolSheet findOneById(int id);

}
