<%@ include file="public/V_93_admin_header.jsp" %>


		<h1>Claim's data</h1>
		<p>
			
			<% 
				String claimTable= (String) request.getAttribute("claimTable"); 
			%>
			
			<%= claimTable != null ? claimTable : "" %>
			
		</p>
		
		<a href='V_93_adminIndex.jsp'>Go Home</a><br/>

<%@ include file="public/V_93_admin_footer.html" %>