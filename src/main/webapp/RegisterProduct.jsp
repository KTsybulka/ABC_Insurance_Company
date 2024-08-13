<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Registration</title>
</head>
<body>
    <h2>Register Product</h2>

    <%
        String action = request.getParameter("action");
        String selectedProductName = request.getParameter("productName");
        String productid = "";

        String serialNo = "";
        String productModel = "";
        
/*         String userid = (String) request.getAttribute("userid"); 
        out.println("Received data: " + userid);
 */        
 
		 String userid = request.getParameter("userId");
/* 		 if (userId != null) {
		     out.println("Received userId: " + userId);
		 } else {
		     out.println("No userId received");
		 } */


        if ("register".equals(action)) {
            // Handle form submission
            selectedProductName = request.getParameter("productName");
            productid = request.getParameter("productid");
            serialNo = request.getParameter("serialNo");
            productModel = request.getParameter("productModel");
            String purchase_date = request.getParameter("purchaseDate");

            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "root");

                String query = "INSERT INTO registered_product (userid, productid, serial_no, purchase_date) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(query);
                
                pstmt.setString(1, userid);
                pstmt.setString(2, productid);
                pstmt.setString(3, serialNo);
                pstmt.setDate(4, java.sql.Date.valueOf(purchase_date));
/*                 pstmt.setString(1, selectedProductName);
                pstmt.setString(2, serialNo);
                pstmt.setString(3, productModel);
                pstmt.setDate(4, java.sql.Date.valueOf(purchaseDate)); */
                pstmt.executeUpdate();
                out.println("<p>Product registered successfully!</p>");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error registering product: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (selectedProductName != null && !selectedProductName.isEmpty()) {
            // Fetch product details for the selected product
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "root");

                String query = "SELECT productid, serial_no, product_model FROM product WHERE product_name = ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, selectedProductName);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                	productid = rs.getString("productid");
                    serialNo = rs.getString("serial_no");
                    productModel = rs.getString("product_model");
                } else {
                    out.println("<p>Error: Product not found</p>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<p>Error: " + e.getMessage() + "</p>");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Fetch product names for the dropdown menu
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<String> products = new ArrayList<>();

        try {
            // Connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "root");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "7400");


            String query = "SELECT product_name FROM product";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                products.add(rs.getString("product_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    %>

    <form method="get">
        <label for="productName">Product Name:</label>
        <select id="productName" name="productName" onchange="this.form.submit()">
            <option value="">Select a product</option>
            <%
                for (String product : products) {
                    if (product.equals(selectedProductName)) {
                        out.println("<option value=\"" + product + "\" selected>" + product + "</option>");
                    } else {
                        out.println("<option value=\"" + product + "\">" + product + "</option>");
                    }
                }
            %>
        </select><br><br>
    </form>

    <form method="post">
        <input type="hidden" name="action" value="register">
        <label for="serialNo">Serial No:</label>
        <input type="text" id="serialNo" name="serialNo" value="<%= serialNo %>" readonly><br><br>

        <label for="productModel">Product Model:</label>
        <input type="text" id="productModel" name="productModel" value="<%= productModel %>" readonly><br><br>

        <label for="purchaseDate">Purchase Date:</label>
        <input type="date" id="purchaseDate" name="purchaseDate"><br><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>
