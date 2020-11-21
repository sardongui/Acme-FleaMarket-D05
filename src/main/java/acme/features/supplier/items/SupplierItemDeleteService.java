package acme.features.supplier.items;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.forums.Forum;
import acme.entities.items.Item;
import acme.entities.messages.Message;
import acme.entities.requests.RequestEntity;
import acme.entities.roles.Supplier;
import acme.entities.specificationSheets.SpecificationSheet;
import acme.features.auditor.auditorRecord.AuditorAuditRecordRepository;
import acme.features.authenticated.message.AuthenticatedForumRepository;
import acme.features.authenticated.message.AuthenticatedMessageRepository;
import acme.features.supplier.request.SupplierRequestRepository;
import acme.features.supplier.specificationSheet.SupplierSpecificationSheetRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SupplierItemDeleteService implements AbstractDeleteService<Supplier, Item>{

	@Autowired
	private SupplierItemRepository repository;
	
	@Autowired
	private SupplierSpecificationSheetRepository specificationSheetRepository;
	
	@Autowired
	private SupplierRequestRepository requestRepository;
	
	@Autowired
	private AuditorAuditRecordRepository auditRecordRepository;
	
	@Autowired
	private AuthenticatedMessageRepository messageRepository;
	
	@Autowired
	private AuthenticatedForumRepository forumRepository;
	
	@Override
	public boolean authorise(Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(Request<Item> request, Item entity, Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "ticker", "creationMoment", "title", "itemCategory", "description", "price", "photo", "link");	
	}

	@Override
	public Item findOne(Request<Item> request) {
		assert request != null;
		Item result;
	
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
		
	}

	@Override
	public void validate(Request<Item> request, Item entity, Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			
	}

	@Override
	public void delete(Request<Item> request, Item entity) {
		assert request != null;
		assert entity != null;
	
		Collection<AuditRecord> ar = this.auditRecordRepository.findManyByItemId(entity.getId());
		if(!ar.isEmpty()) {
			for(AuditRecord audit: ar) {
				audit.setAuditor(null);
				audit.setItem(null);
				this.auditRecordRepository.delete(audit);
			}
		}
		
		Forum forum = this.forumRepository.findForumByItemId(entity.getId());
		if(forum!=null) {
		Collection<Message> messages = this.messageRepository.findMany(entity.getId());
			for(Message m: messages) {
				m.setForum(null);
				this.messageRepository.delete(m);
			}
		forum.setItem(null);	
		this.forumRepository.delete(forum);
		}
		SpecificationSheet ss = this.repository.findSpecificationSheetById(entity.getId());
		
		this.repository.delete(entity);
		entity.setSpecificationSheet(null);
		this.specificationSheetRepository.delete(ss);
	
	}

}
