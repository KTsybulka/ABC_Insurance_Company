package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.M_93_InsuranceClaim;
import model.M_93_Product;
import model.M_93_Report;
import model.M_93_Users;


public class DAO_93_ReadReportQuery {
	
	private Connection conn;
	private ResultSet rs;
	
	
	public DAO_93_ReadReportQuery() throws ClassNotFoundException, SQLException, IOException {

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
	
	public void doReadReport() throws SQLException {

		String sql = "SELECT u.userid, u.username, p.product_name, ic.claim_id, "
				+ "ic.description AS claim_description, ic.claim_status FROM users u "
				+ "LEFT JOIN registered_product rp ON u.userid = rp.userid "
				+ "LEFT JOIN product p ON rp.productid = p.productid "
				+ "LEFT JOIN insurance_claim ic ON rp.reg_product_id = ic.reg_product_id "
				+ "ORDER BY u.userid, ic.claim_id;";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		rs = preparedStatement.executeQuery();			
	}	
	
	public String getHTMLReportTable() throws SQLException {
		StringBuilder table = new StringBuilder();

		table.append("<table border=5>");
        table.append("<tr>");
        table.append("<th>Userid ID</th>");
        table.append("<th>User Name</th>");
        table.append("<th>Product name</th>");
        table.append("<th>Claim ID</th>");
        table.append("<th>Description</th>");
        table.append("<th>Claim Status</th>");       
        table.append("</tr>");

		while(this.rs.next()) {
//			M_93_Report report = new M_93_Report(); // Change this object creation and add the data by using appropriate constructor
			M_93_Users user = new M_93_Users();
			M_93_Product product = new M_93_Product();			
			M_93_InsuranceClaim claim = new M_93_InsuranceClaim();
			
			user.setUserid(rs.getInt("userid"));
			user.setUsername(rs.getString("username"));
			product.setProduct_name(rs.getString("product_name"));
			claim.setClaim_id(rs.getInt("claim_id"));
			claim.setDescription(rs.getString("claim_description"));
			claim.setClaim_status(rs.getString("claim_status"));
							
			table.append("<tr>");			
	        table.append("<td>").append(user.getUserid()).append("</td>");
	        table.append("<td>").append(user.getUsername()).append("</td>");
	        table.append("<td>").append(product.getProduct_name()).append("</td>");
	        table.append("<td>").append(claim.getClaim_id()).append("</td>");
	        table.append("<td>").append(claim.getDescription()).append("</td>");
	        table.append("<td>").append(claim.getClaim_status()).append("</td>");
	        table.append("</tr>");	
	        
		}
		table.append("</table>");
		
		return table.toString();
	}
}
