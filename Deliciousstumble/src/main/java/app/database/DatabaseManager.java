package app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import domain.Post;

public class DatabaseManager {
	private static final String url="jdbc:postgresql://localhost/mydb";
	private static final String password="tajna1983";
	private static final String user="postgres";
	
	private DatabaseManager() {}
	
	private static Connection conn = null;
	
	static {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static Connection getConnection() throws SQLException {
		return conn;
	}
	
	public static ResultSet createResultSet(String query) throws SQLException {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
	}
	
	public static int executeUpdate(String sql) throws SQLException {
		Statement statement = conn.createStatement();
		return statement.executeUpdate(sql);		
	}
	
	public static int executeBatchUpdate(String sql, List<Post> postsList) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		 
		final int batchSize = 1000;
		int count = 0;
		 
		for (Post post: postsList) {		 
		    ps.setString(1, post.getDescription());
		    ps.setString(2, post.getExtended());
		    ps.setString(3, post.getHash());
		    ps.setString(4, post.getHref());
		    ps.addBatch();
		     
		    if(++count % batchSize == 0) {
		        ps.executeBatch();
		    }
		}
		ps.executeBatch(); // insert remaining records

		return count;		
	}
}
