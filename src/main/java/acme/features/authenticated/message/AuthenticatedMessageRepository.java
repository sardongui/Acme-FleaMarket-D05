
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.forums.Forum;
import acme.entities.messages.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.forum.item.id=?1")
	Collection<Message> findMany(int itemId);

	@Query("select m from Message m where m.id =?1")
	Message findOneById(int id);

	@Query("select f from Forum f where f.item.id=?1")
	Forum findForumByItemId(int itemId);

	@Query("select f from Forum f where f.id=?1")
	Forum findForumById(int forumId);
}
