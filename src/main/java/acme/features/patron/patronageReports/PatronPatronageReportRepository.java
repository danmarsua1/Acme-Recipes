package acme.features.patron.patronageReports;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {
	
	@Query("select patronageReport from PatronageReport patronageReport where patronageReport.patronage.patron.id = :id")
	Collection<PatronageReport> findManyPatronageReportByPatronId(int id);
	
	@Query("select patronageReport from PatronageReport patronageReport where patronageReport.id = :id")
	PatronageReport findOnePatronageReportById(int id);
}
