package acme.features.auditor.auditorRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord>{

	@Autowired
	AuditorAuditRecordRepository repository;

	@Override
	public boolean authorise(Request<AuditRecord> request) {
		assert request != null;

//Hemos quitado la restriccion de solo ver para el usuario que se ha registrado, 
//porque en el show de list-not-mine no podría ver entonces el AuditRecord
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
		
		request.unbind(entity, model, "title", "creationMoment", "status", "body", "item.title", "finalMode");
		
	}

	@Override
	public AuditRecord findOne(Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		int id;

		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		
		return result;
	}


}
