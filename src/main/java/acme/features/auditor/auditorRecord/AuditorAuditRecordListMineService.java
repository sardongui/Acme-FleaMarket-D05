package acme.features.auditor.auditorRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListMineService implements AbstractListService<Auditor, AuditRecord>{

	@Autowired
	AuditorAuditRecordRepository repository;

	@Override
	public boolean authorise(Request<AuditRecord> request) {
		assert request != null;

//		boolean result;
//		int auditRecordId;
//		AuditRecord ar;
//		Auditor auditor;
//		Principal principal;
//
//		auditRecordId = request.getModel().getInteger("id");
//		ar = this.repository.findOneById(auditRecordId);
//		auditor = ar.getAuditor();
//		principal = request.getPrincipal();
//		result = auditor.getUserAccount().getId() == principal.getAccountId();

		return true;
	}
	
	@Override
	public void unbind(Request<AuditRecord> request, AuditRecord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, "title", "creationMoment","item.title");
		
		
	}

	@Override
	public Collection<AuditRecord> findMany(Request<AuditRecord> request) {
		assert request != null;

		Collection<AuditRecord> result;
		Principal principal;
		int item = request.getModel().getInteger("item");

		principal = request.getPrincipal();
		result = this.repository.findManyByAuditorIdByItemId(principal.getActiveRoleId(), item);

		return result;
	}
}
