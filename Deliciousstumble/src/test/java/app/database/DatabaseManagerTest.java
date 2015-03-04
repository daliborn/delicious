package app.database;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class DatabaseManagerTest {

	@Test
	public void testCreateConnection() {
		DatabaseManager db = new DatabaseManager();
		Connection connection = db.createConnection();
		
		assertNotNull(connection);
	}

}
