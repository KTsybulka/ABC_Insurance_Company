<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Registration</title>
    <script>
        function populateFields() {
            var productName = document.getElementById("productName").value;

            // Fetch the product details from the server using AJAX
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "productRegistration.jsp?action=getProductDetails&productName=" + productName, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var response = xhr.responseText;
                    if (response.startsWith("Error:")) {
                        alert(response);
                    } else {
                        var productDetails = response.split(",");
                        document.getElementById("serialNo").value = productDetails[0];
                        document.getElementById("productModel").value = productDetails[1];
                    }
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>
    <h2>Register Product</h2>

    <%
        String action = request.getParameter("action");
        if ("register".equals(action)) {
            // Handle form submission
            String productName = request.getParameter("productName");
            String serialNo = request.getParameter("serialNo");
            String productModel = request.getParameter("productModel");
            String purchaseDate = request.getParameter("purchaseDate");

            Connection conn = null;
            PreparedStatement pstmt = null;

            //insert (register a product) 
            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "root");

                String query = "INSERT INTO registered_product (userid, productid, serial_no, purchase_date) VALUES (?, ?, ?, ?)";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, productName);
                pstmt.setString(2, serialNo);
                pstmt.setString(3, productModel);
                pstmt.setDate(4, java.sql.Date.valueOf(purchaseDate));
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
        } else if ("getProductDetails".equals(action)) {
            // Handle AJAX request for product details
            System.out.print("I am here");
            String productName = request.getParameter("productName");
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "root");

                String query = "SELECT serial_no, product_model FROM products WHERE product_name = ?";
                System.out.println(query);
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, productName);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    out.print(rs.getString("serial_no") + "," + rs.getString("product_model"));
                } else {
                    out.print("Error: Product not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("Error: " + e.getMessage());
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    out.print("Error: " + e.getMessage());
                }
            }
            return; // Stop further processing for AJAX request
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

            //
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

    <form method="post">
        <input type="hidden" name="action" value="register">
        <label for="productName">Product Name:</label>
        <select id="productName" name="productName" onchange="populateFields()">
            <option value="">Select a product</option>
            <%
                for (String product : products) {
                    out.println("<option value=\"" + product + "\">" + product + "</option>");
                }
            %>
        </select><br><br>

        <label for="serialNo">Serial No:</label>
        <input type="text" id="serialNo" name="serialNo" readonly><br><br>

        <label for="productModel">Product Model:</label>
        <input type="text" id="productModel" name="productModel" readonly><br><br>

        <label for="purchaseDate">Purchase Date:</label>
        <input type="date" id="purchaseDate" name="purchaseDate"><br><br>

        <input type="submit" value="Register">
    </form>
</body>
</html>
