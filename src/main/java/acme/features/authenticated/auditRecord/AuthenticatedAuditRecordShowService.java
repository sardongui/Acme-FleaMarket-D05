package acme.features.authenticated.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAuditRecordShowService implements AbstractShowService<Authenticated, AuditRecord> {

	// Internal state ------------------------------------------------------------------
	@Autowired
	AuthenticatedAuditRecordRepository repository;
	
	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		Boolean result = false;
		Principal principal = request.getPrincipal();
		if (principal.isAuthenticated()) {
			result = true;
		}
		return result;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "status", "body", "item.title", "finalMode");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
