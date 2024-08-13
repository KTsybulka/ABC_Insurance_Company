<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<h2>Add a product</h2>
    <form method="post">
        <label for="productName">Product Name</label><br>
        <input type="text" id="productName" name="productName" required><br><br>

        <label for="productSerial">Product Serial No</label><br>
        <input type="text" id="productSerial" name="productSerial" required><br><br>
        
        <label for="productModel">Product Model</label><br>
        <input type="text" id="productModel" name="productModel" required><br><br>

        <label for="productDescription">Product Description</label><br>
        <input type="text" id="productDescription" name="productDescription" required><br><br>

        <input type="submit" value="Submit">
    </form>

	<%
		String productName = request.getParameter("productName");
		String productSerial = request.getParameter("productSerial");
		String productModel = request.getParameter("productModel");
		String productDescription = request.getParameter("productDescription");
		
		String errorMessage = "";
		
		if (productName != null && productSerial != null && productModel != null && productDescription != null){
			try{
				 Class.forName("com.mysql.jdbc.Driver");
	   	    	 String username="root";

	   	    	 //String password="root";
	   	    	String password="7400";

	/*    	    	 String password="root";
 */
	   			 String url ="jdbc:mysql://localhost:3306/insurance" ;
	   	    	 Connection connection = DriverManager.getConnection(url, username, password);
	   	    	
	   	    	
	   	    	 // create statement
	   	    	 Statement st= connection.createStatement();
    	    	 String sql = "insert into product(product_name, serial_no, product_model, product_description) values('" + productName + "', '" + productSerial + "', '" + productModel + "', '" + productDescription + "')";
    	    	 st.executeUpdate(sql);
    	    	 System.out.println("Data inserted");
    	    	 connection.close();

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	%>
</body>

</html>