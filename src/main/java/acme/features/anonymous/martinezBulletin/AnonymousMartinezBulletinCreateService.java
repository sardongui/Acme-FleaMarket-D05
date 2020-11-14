
package acme.features.anonymous.martinezBulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.MartinezBulletin;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousMartinezBulletinCreateService implements AbstractCreateService<Anonymous, MartinezBulletin> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AnonymousMartinezBulletinRepository repository;


	@Override
	public boolean authorise(final Request<MartinezBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MartinezBulletin> request, final MartinezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MartinezBulletin> request, final MartinezBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "web", "description");
	}

	@Override
	public MartinezBulletin instantiate(final Request<MartinezBulletin> request) {
		assert request != null;

		MartinezBulletin result = new MartinezBulletin();
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		result.setWeb("Twitter");
		result.setDescription("Donde las críticas constructivas, brillan por su ausencia.");
		result.setMoment(moment);
		return result;
	}

	@Override
	public void validate(final Request<MartinezBulletin> request, final MartinezBulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<MartinezBulletin> request, final MartinezBulletin entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
