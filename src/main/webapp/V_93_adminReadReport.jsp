<%@ include file="public/V_93_admin_header.jsp" %>


		<h1>Report</h1>
		<p>
			
			<% 
			String reportTable = (String) request.getAttribute("reportTable"); 
			%>
			
			<%= reportTable != null ? reportTable : "" %>
			
		</p>
		
		<a href='V_93_adminIndex.jsp'>Go Home</a><br/>

<%@ include file="public/V_93_admin_footer.html" %>
