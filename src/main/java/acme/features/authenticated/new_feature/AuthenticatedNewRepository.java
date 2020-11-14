
package acme.features.authenticated.new_feature;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.news.New;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedNewRepository extends AbstractRepository {

	@Query("Select n from New n")
	Collection<New> findMany();

	@Query("Select n from New n where CURRENT_TIMESTAMP >= n.moment and CURRENT_TIMESTAMP < n.deadline")
	Collection<New> findManyActive();

	@Query("select n from New n where n.id =?1")
	New findOneById(int id);
}
