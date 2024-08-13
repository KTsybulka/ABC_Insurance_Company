package dbHelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO_93_DB {
	// Static variable to hold the database connection
	private static Connection conn;		
	
    /**
     * Static method to return a database connection.
     * 
     * @param url The JDBC URL of the database.
     * @param userName The username to connect to the database.
     * @param password The password to connect to the database.
     * @return The database connection object.
     */
    public static Connection getConnection(String url, String userName, String password) {
        try {
        	// Check if the connection is null or closed
            if (conn == null || conn.isClosed()) {
            	// If not, create a new connection using the provided URL, username, and password
                conn = DriverManager.getConnection(url, userName, password);
                System.out.println("Connection done!"); // Log successful connection
            }
        } catch (SQLException e) {
        	// Print stack trace if there is an SQL exception
            e.printStackTrace();
        }
     // Return the connection object
        return conn;
    }
}
