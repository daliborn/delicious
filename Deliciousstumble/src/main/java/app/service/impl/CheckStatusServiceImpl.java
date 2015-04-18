package app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.database.CheckStatusRepository;
import app.domain.CheckStatus;
import app.domain.Post;
import app.service.CheckStatusService;

@Service
@Qualifier("checkStatusService")
public class CheckStatusServiceImpl implements CheckStatusService {
	private static final Logger logger = Logger.getLogger(CheckStatusServiceImpl.class.getName());
	
	 @Autowired
	 CheckStatusRepository repository;
	
	@Override
	public void createCheckStatus(CheckStatus status) {
		repository.save(status);
	}

	@Override
	public List<CheckStatus> getStatusesforPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}
