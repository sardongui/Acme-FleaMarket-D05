package acme.features.authenticated.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRecordRepository extends AbstractRepository {
	
	@Query("select a from AuditRecord a where a.item.id=?1")
	Collection<AuditRecord> findManyByItem(int id);
	
	@Query("select f from AuditRecord f where f.id=?1")
	AuditRecord findOneById(int id);

	

}
