package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.M_93_Users;

public class DAO_93_ReadUserQuery {
	
	private Connection conn;
	private ResultSet rs;
	
	public DAO_93_ReadUserQuery() throws ClassNotFoundException, SQLException, IOException {

		Properties properties = new Properties();
		InputStream inputStream;
						
		inputStream = getClass().getResourceAsStream("config_93_dbConn.properties");
		
		if(inputStream!= null) {
			properties.load(inputStream);
			inputStream.close();
			
			String driver = properties.getProperty("jdbc.driver");
			System.out.println ("Driver load");
			
			if(!Objects.isNull(driver)) {
				Class.forName(driver);
			}
			
			String url = properties.getProperty("jdbc.url");
			String username = properties.getProperty("jdbc.username");
			String password = properties.getProperty("jdbc.password");
		
			conn= DAO_93_DB.getConnection(url, username, password);
			
		}else {
			throw new IOException("Property file not found.");
		}
	}
	
	public void doReadAllUsers() throws SQLException {

		String sql = "SELECT * FROM users";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		rs = preparedStatement.executeQuery();			
	}
	
	
	public String getUserSerchForm() {
		StringBuilder searchForm = new StringBuilder();
		
		searchForm .append("<form action='C_93_ReadUser' method='post'>")
    	.append("<input type='text' name='userid'/>")
    	.append("<input type='submit' value='Search'/>")
    	.append("</form>");
	
		return searchForm.toString();
	}
	public void doReadUsersById(String userid) throws SQLException {
		String sql = "SELECT * FROM users WHERE userid = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, userid );
		rs = preparedStatement.executeQuery();			
	}
	
	public String  getSerchUserForm() {
		StringBuilder table = new StringBuilder();		
		
		table.append("<form action='C_93_ReadUser' method='post'>")
    	.append("<input type='text' name='userid'/>")
    	.append("<input type='submit' value='Search'/>")
    	.append("</form>");		
		
		return table.toString();
	}
	
	public String getHTMLUserTable() throws SQLException {
		StringBuilder table = new StringBuilder();

		table.append("<table border=5>");
        table.append("<tr>");
        table.append("<th>Userid ID</th>");
        table.append("<th>User Name</th>");
        table.append("<th>Password</th>");
        table.append("<th>Cellphone</th>");
        table.append("<th>Favorite Color</th>");
        table.append("<th>Address</th>");
        table.append("<th>Role</th>");
        table.append("<th>Actions</th>");        
        table.append("</tr>");

		while(this.rs.next()) {
			M_93_Users friend = new M_93_Users(); // Change this object creation and add the data by using appropriate constructor
			
			friend.setUserid(rs.getInt("userid"));			
			friend.setUsername(rs.getString("username"));
			friend.setPassword(rs.getString("password"));
			friend.setName(rs.getString("name"));
			friend.setCellphone(rs.getString("cellphone"));
			friend.setEmail(rs.getString("email"));
			friend.setAddress(rs.getString("address"));
			friend.setRole(rs.getString("role"));			
							
			table.append("<tr>");			
	        table.append("<td>").append(friend.getUserid()).append("</td>");
	        table.append("<td>").append(friend.getUsername()).append("</td>");
	        table.append("<td>").append(friend.getPassword()).append("</td>");
	        table.append("<td>").append(friend.getName()).append("</td>");
	        table.append("<td>").append(friend.getCellphone()).append("</td>");
	        table.append("<td>").append(friend.getEmail()).append("</td>");
	        table.append("<td>").append(friend.getAddress()).append("</td>");
	        table.append("<td>").append(friend.getRole()).append("</td>");
	        table.append("</tr>");	
	        
		}
		table.append("</table>");

		
		return table.toString();
	}
}
