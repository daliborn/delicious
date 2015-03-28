package app.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.database.DatabaseManager;
import domain.CheckStatus;
import domain.Post;

@Service
@Qualifier("checkStatusService")
public class CheckStatusServiceImpl implements CheckStatusService {
	private static final Logger logger = Logger.getLogger(CheckStatusServiceImpl.class.getName());
	
	@Override
	public void createCheckStatus(Post post, CheckStatus status) {
		String sql = "insert into check_status (status_code,date_run,Post_id) values (?, ?, ?)";
		
		try {
			DatabaseManager.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<CheckStatus> getStatusesforPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

}
