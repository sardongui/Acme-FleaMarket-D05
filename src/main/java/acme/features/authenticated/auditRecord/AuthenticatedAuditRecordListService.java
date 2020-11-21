package acme.features.authenticated.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditRecordListService implements AbstractListService<Authenticated, AuditRecord>{

	// Internal state ---------------------------------------------------------

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
			request.unbind(entity, model, "title", "creationMoment","item.title");
		}

		@Override
		public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
			assert request != null;
			int item = request.getModel().getInteger("item");

			Collection<AuditRecord> result = this.repository.findManyByItem(item);
			return result;
		}

}
