package app.service;

import java.sql.SQLException;
import java.util.List;

import app.database.DatabaseManager;
import domain.Post;

public class DaoServiceImpl implements DaoService {

	@Override
	public void createPosts(List<Post> postsList) {
		String sql = "insert into employee (name, city, phone) values (?, ?, ?)";
				
		try {
			DatabaseManager.executeBatchUpdate(sql,postsList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
