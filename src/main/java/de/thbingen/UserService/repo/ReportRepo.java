package de.thbingen.UserService.repo;

import de.thbingen.UserService.entity.Report;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReportRepo extends CrudRepository<Report,Integer> {
    Optional<Report> getReportById(int id);

}
