


<%@ include file="public/V_93_admin_header.jsp" %>


		<h1>User's data</h1>
		
		<p>
		
		<%String tableOneUser = (String) request.getAttribute("tableOneUser");
		if(tableOneUser != null && !tableOneUser.isEmpty()){
				%>
				<%= tableOneUser %>
		<%	
		}			
			%>
			
			
		
			<% 
			String searchUserForm = (String) request.getAttribute("searchUserForm");
			String tableAllUsers = (String) request.getAttribute("tableAllUsers"); 
			%>
			<%= searchUserForm != null ?  searchUserForm : ""%>
			<%= tableAllUsers != null ? tableAllUsers : ""%>
		</p>
		
		<a href='V_93_adminIndex.jsp'>Go Home</a><br/>



<%@ include file="public/V_93_admin_footer.html" %>

