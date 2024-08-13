<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>

    <form method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <input type="submit" value="Login">
    </form>

    <%
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Connect to the database
                Class.forName("com.mysql.cj.jdbc.Driver");
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "root");
                // Kirill's pasward
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/insurance", "root", "7400");
                

                String query = "SELECT userid, role FROM users WHERE username = ? AND password = ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String role = rs.getString("role");
                    String userId = rs.getString("userid");
                    
					
/*                     String data = "someData";
                    request.setAttribute("data", data);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("destination.jsp");
                    dispatcher.forward(request, response); */
                    
                    
                    
                    if ("customer".equalsIgnoreCase(role)) {
                        response.sendRedirect("RegisterProduct.jsp?userId=" + userId);
                    } else if ("admin".equalsIgnoreCase(role)) {
                        response.sendRedirect("V_93_adminIndex.jsp");
                    } else {
                        out.println("<p>Invalid role: " + role + "</p>");
                    }
                } else {
                    out.println("<p>Invalid username or password</p>");
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
                    //dasdfasdfdsf
                }
            }
        }
    %>
</body>
</html>
