package acme.features.auditor.auditorRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService  implements AbstractUpdateService<Auditor, AuditRecord> {
	
	@Autowired
	AuditorAuditRecordRepository repository;

	@Override
	public boolean authorise(Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<AuditRecord> request, AuditRecord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment","auditor", "item");
	}

	@Override
	public void unbind(Request<AuditRecord> request, AuditRecord entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		//model.setAttribute("item", entity.getItem().getId());
		request.unbind(entity, model, "title", "creationMoment", "status", "body", "finalMode");
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

	@Override
	public void validate(Request<AuditRecord> request, AuditRecord entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		
	}

	@Override
	public void update(Request<AuditRecord> request, AuditRecord entity) {
		assert request != null;
		assert entity != null;
		
		if(entity.isFinalMode()==true) {
			entity.setStatus("PUBLISHED");
		}
		this.repository.save(entity);

		
	}


}
