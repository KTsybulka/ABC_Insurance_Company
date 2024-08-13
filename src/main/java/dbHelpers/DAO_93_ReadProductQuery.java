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
import model.M_93_Users;

public class DAO_93_ReadProductQuery {

	private Connection conn;
	private ResultSet rs;
	
	
	public DAO_93_ReadProductQuery() throws ClassNotFoundException, SQLException, IOException {
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
	
	
	public void doReadAllProduct() throws SQLException {

		String sql = "SELECT * FROM product";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		rs = preparedStatement.executeQuery();			
	}
	
	public void doReadProductById(String productid) throws SQLException {
		String sql = "SELECT * FROM product WHERE productid = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, productid);
		rs = preparedStatement.executeQuery();			
	}
		

	public M_93_Product doReadProductObjectById(int productid) throws SQLException {
//	public M_93_Product doReadProductObjectById(String productid) throws SQLException {
		String sql = "SELECT * FROM product WHERE productid = ?";
	    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
//	    	preparedStatement.setString(1, productid);
	        preparedStatement.setInt(1, productid);
	        rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	        	M_93_Product product = new M_93_Product();
	        	product.setProductid(rs.getInt("productid"));	
	        	product.setProduct_name(rs.getString("product_name"));
	        	product.setSerial_no(rs.getString("serial_no"));
	        	product.setProduct_model(rs.getString("product_model"));
	        	product.setProduct_description(rs.getString("product_description"));
	            return product;
	        } else {
	            return null;
	        }
	    }
	}
	
	public void addProduct(String product_name, String serial_no, String product_model, String product_description) throws SQLException {
	    String sql = "INSERT INTO product (product_name, serial_no, product_model, product_description) VALUES (?, ?, ?, ?)";
	    try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
	        // Set the values for each parameter in the SQL query
	        preparedStatement.setString(1, product_name);
	        preparedStatement.setString(2, serial_no);
	        preparedStatement.setString(3, product_model);
	        preparedStatement.setString(4, product_description);
	        
	        preparedStatement.executeUpdate();
	    }
	}
	
	
	
	public void doUpdateProduct(int productid, String product_name, String serial_no, 
			String product_model, String product_description) throws SQLException {
		String sql = "UPDATE product SET product_name = ?, serial_no = ?, "
				+ "product_model = ?, product_description = ?   WHERE productid = ?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, product_name);
		preparedStatement.setString(2, serial_no);
		preparedStatement.setString(3, product_model);
		preparedStatement.setString(4, product_description);
		preparedStatement.setInt(5, productid);
		preparedStatement.executeUpdate();	
	}
	
	

	public String getSerchForm() {
		StringBuilder searchForm = new StringBuilder();
		
		searchForm .append("<form action='C_93_ReadProduct' method='post'>")
//		.append("<input type='hidden' name='searchOneProduct' value='Search'/>")
    	.append("<input type='text' name='productid'/>")
    	.append("<input type='submit' value='Search'/>")
    	.append("</form>");
	
		return searchForm .toString();
	}
	
	public String getHTMLProductTable() throws SQLException {
		StringBuilder table = new StringBuilder();	
		
		table.append("<table border=5>");
        table.append("<tr>");
        table.append("<th>Product Id</th>");
        table.append("<th>Product Name</th>");
        table.append("<th>Serial No</th>");
        table.append("<th>Product Model</th>");
        table.append("<th>Product Description</th>"); 
        table.append("<th>Action</th>");      
        table.append("</tr>");

		while(this.rs.next()) {
			M_93_Product friend = new M_93_Product(); 
			
			friend.setProductid(rs.getInt("productid"));			
			friend.setProduct_name(rs.getString("product_name"));
			friend.setSerial_no(rs.getString("serial_no"));
			friend.setProduct_model(rs.getString("product_model"));
			friend.setProduct_description(rs.getString("product_description"));
		
							
			table.append("<tr>");			
	        table.append("<td>").append(friend.getProductid()).append("</td>");
	        table.append("<td>").append(friend.getProduct_name()).append("</td>");
	        table.append("<td>").append(friend.getSerial_no()).append("</td>");
	        table.append("<td>").append(friend.getProduct_model()).append("</td>");
	        table.append("<td>").append(friend.getProduct_description()).append("</td>");
	        table.append("<td><a href='C_93_ProductUpdate?productid=").append(friend.getProductid()).append("'>Update</a></td>");
	       
	        table.append("</tr>");	
		}
		table.append("</table>");

		
		return table.toString();
	}
	
}
