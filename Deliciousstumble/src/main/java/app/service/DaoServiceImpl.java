package app.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.database.DatabaseManager;
import domain.Post;

@Service
@Qualifier("daoService")
public class DaoServiceImpl implements DaoService {
	private static final Logger logger = Logger.getLogger(DaoServiceImpl.class.getName());
	
	@Override
	public void createPosts(List<Post> postsList) {
		String sql = "insert into posts (href, description, extended, hash, meta) values (?, ?, ?, ?, ?)";
				
		try {
			DatabaseManager.executeBatchUpdate(sql,postsList);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			SQLException exe = e.getNextException();
			
			logger.error(exe.getMessage());
		}

	}

}
