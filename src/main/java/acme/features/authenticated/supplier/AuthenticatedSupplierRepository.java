package acme.features.authenticated.supplier;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Supplier;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSupplierRepository extends AbstractRepository{
	
	@Query("select s from Supplier s where s.userAccount.id = ?1")
	Supplier findOneSupplierByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

}
