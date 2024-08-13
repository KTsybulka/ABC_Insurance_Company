package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

import model.M_93_InsuranceClaim;
import model.M_93_Product;

public class DAO_93_ReadClaimQuery {

	private Connection conn;
	private ResultSet resultSet;
	
	public DAO_93_ReadClaimQuery() throws ClassNotFoundException, SQLException, IOException {
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
	
	public void doReadClaim() throws SQLException {
		String sql = "SELECT * FROM insurance_claim";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);		
		resultSet = preparedStatement.executeQuery();	
	}
	
	
	public M_93_InsuranceClaim doReadClaimById(int claim_id) throws SQLException {
	    String sql = "SELECT * FROM insurance_claim WHERE claim_id = ?";
	    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	        preparedStatement.setInt(1, claim_id);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	        	M_93_InsuranceClaim claim = new M_93_InsuranceClaim();
	            claim.setClaim_id(resultSet.getInt("claim_id"));
	            claim.setClaim_status(resultSet.getString("claim_status"));
	            return claim;
	        } else {
	            return null;
	        }
	    }
	}
	
	
	public void doUpdateClaim(int claim_id, String claim_status) throws SQLException {
		String sql = "UPDATE insurance_claim SET claim_status = ? WHERE claim_id = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, claim_status);
		preparedStatement.setInt(2, claim_id);
		preparedStatement.executeUpdate();	
	}
	
	public String getHTMLClaimTable() throws SQLException {
		StringBuilder table = new StringBuilder();	
		
		table.append("<table border=5>");
		table.append("<tr>");
		table.append("<th>Claim Id</th>");
		table.append("<th>Reg Product Id</th>");
		table.append("<th>Claim Date</th>");
		table.append("<th>User Id</th>");
		table.append("<th>Description</th>"); 
		table.append("<th>Claim Status</th>"); 
		table.append("<th>Action</th>"); 
		table.append("</tr>");

		while(this.resultSet.next()) {
			M_93_InsuranceClaim claim = new M_93_InsuranceClaim(); 		
			
			
			claim.setClaim_id(resultSet.getInt("claim_id"));			
			claim.setReg_product_id(resultSet.getInt("reg_product_id"));
			claim.setClaim_date(resultSet.getDate("claim_date"));
			claim.setUserid(resultSet.getInt("userid"));
			claim.setDescription(resultSet.getString("description"));
			claim.setClaim_status(resultSet.getString("claim_status"));
		
							
			table.append("<tr>");			
	        table.append("<td>").append(claim.getClaim_id()).append("</td>");
	        table.append("<td>").append(claim.getReg_product_id()).append("</td>");
	        table.append("<td>").append(claim.getUserid()).append("</td>");
	        table.append("<td>").append(claim.getClaim_date()).append("</td>");
//	        table.append("<td>").append(claim.getClaim_date() != null ? claim.getClaim_date().toString() : "N/A").append("</td>");	        	        
	        table.append("<td>").append(claim.getDescription()).append("</td>");
	        table.append("<td>").append(claim.getClaim_status()).append("</td>");
	        table.append("<td><a href='C_93UpdateClaim?claim_id=").append(claim.getClaim_id()).append("'>Update</a></td>");
	        table.append("</tr>");	
		}
		table.append("</table>");

		
		return table.toString();
	}	
}
