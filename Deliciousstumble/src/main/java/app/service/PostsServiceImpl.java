package app.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.database.DatabaseManager;
import domain.Post;

@Service
@Qualifier("postsService")
public class PostsServiceImpl implements PostsService {
	private static final Logger logger = Logger.getLogger(PostsServiceImpl.class.getName());
	
	@Override
	public void createPosts(List<Post> postsList) {
		try {
			DatabaseManager.executeBatchUpdate(postsList);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			SQLException exe = e.getNextException();
			if (exe !=null)	logger.error(exe.getMessage());
		}

	}

	@Override
	public List<Post> getAllPosts() {
		String query = "SELECT * FROM posts";
		List<Post> list = new ArrayList<>();
		try {
			ResultSet resultSet = DatabaseManager.createResultSet(query);
			
			while(resultSet.next()) {
				Post post = new Post();
				post.setHref(resultSet.getString("href"));
				post.setDescription(resultSet.getString("description"));
				post.setHash(resultSet.getString("hash"));
				post.setId(resultSet.getInt("posts_id"));
				list.add(post);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updatePost(Post post) {
		String sql = "update Where ";
		try {
			int status  = DatabaseManager.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
