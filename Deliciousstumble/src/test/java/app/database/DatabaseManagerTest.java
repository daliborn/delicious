package app.database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class DatabaseManagerTest {

	@Test
	public void testCreateConnection() throws SQLException {
		Connection connection = DatabaseManager.getConnection();
		
		assertNotNull(connection);
	}

}
