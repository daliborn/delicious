package app.database;

import org.springframework.data.repository.CrudRepository;

import app.domain.CheckStatus;

public interface CheckStatusRepository extends CrudRepository<CheckStatus, Long> {

}
