package app.database;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.domain.CheckStatus;

public interface CheckStatusRepository extends CrudRepository<CheckStatus, Long> {
	List<CheckStatus> findByStatusCode(Integer statusCode);
}
