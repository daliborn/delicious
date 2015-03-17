package app.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import domain.Post;

public class DatabaseManager {
	private static final Logger logger = Logger.getLogger(DatabaseManager.class.getName());

	
	private DatabaseManager() {}
	
	private static Connection conn = null;
	
	static {
		Properties prop = new Properties();
		
		try {
			InputStream is = ClassLoader.class.getResourceAsStream("/application.properties"); 
			prop.load(is);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String password= prop.getProperty("database.password");
		
		String user = prop.getProperty("database.user");

		String url = prop.getProperty("database.url");
		
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
			ps.setString(1, post.getHref());
		    ps.setString(2, post.getDescription());
		    ps.setString(3, post.getExtended());
		    ps.setString(4, post.getHash());
		    ps.setTimestamp(5, post.getTime());
		    
		    ps.addBatch();
		     
		    if(++count % batchSize == 0) {
		        ps.executeBatch();
		    }
		}
		ps.executeBatch(); // insert remaining records

		return count;		
	}
}
