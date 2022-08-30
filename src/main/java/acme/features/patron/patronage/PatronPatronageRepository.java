package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Configuration;
import acme.entities.patronage.Patronage;
import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select patronage from Patronage patronage where patronage.id = :id")
	Patronage findOnePatronageById(int id);

	@Query("select patronage from Patronage patronage where patronage.patron.id = :id")
	Collection<Patronage> findPatronagesByPatronId(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :patronageId")
    Collection<PatronageReport> findManyPatronageReportsByPatronageId(int patronageId);

	@Query("select patronage from Patronage patronage where patronage.code = :code")
	Patronage findOnePatronageByCode(String code);
	
	@Query("select configuration from Configuration configuration")
	Configuration findConfiguration();

	@Query("select patron from Patron patron where patron.userAccount.id =:id")
	Patron findPatronByUserId(int id);
	
	@Query("select p from Patronage p")
	Collection<Patronage> findAllPatronages();
	
	@Query("SELECT patron FROM Patron patron WHERE patron.id = :id")
	Patron findOnePatronById(int id);
}