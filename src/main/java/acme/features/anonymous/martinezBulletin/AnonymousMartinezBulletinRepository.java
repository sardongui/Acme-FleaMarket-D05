
package acme.features.anonymous.martinezBulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.MartinezBulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousMartinezBulletinRepository extends AbstractRepository {

	@Query("Select db from MartinezBulletin db")
	Collection<MartinezBulletin> findMany();
}
