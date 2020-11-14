
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Banner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorBannerRepository extends AbstractRepository {

	@Query("select b from Banner b")
	Collection<Banner> findMany();

	@Query("select b from Banner b where b.id =?1")
	Banner findOneById(int id);

	@Query("select s from Sponsor s where s.id =?1")
	Sponsor findSponsorById(int activeRoleId);

	@Query("select s.banners from Sponsor s where s.id=?1")
	Collection<Banner> findManyBySponsorId(int sponsorId);

	@Query("select b from Banner b, Sponsor s where s.creditCard.id=b.creditCard.id and s.id=?1")
	Collection<Banner> anyCreditCardLinked(int sponsorId);
}
