<%@ include file="public/V_93_admin_header.jsp" %>


		<h1>Product's data</h1>
		<p>
 			<% String tableOne = (String) request.getAttribute("tableOne"); 
			if(tableOne != null && !tableOne.isEmpty()){
				%>
					<%= tableOne %>
				<% 
			}
			%> 
		
			
			<% 
			String searchForm= (String) request.getAttribute("searchForm");
			String tableAll = (String) request.getAttribute("tableAll"); 
			%>
			
			<%= searchForm != null ? searchForm : "" %>
			<%= tableAll != null ? tableAll : "" %>
			
		</p>
		
		<a href='V_93_adminIndex.jsp'>Go Home</a><br/>

<%@ include file="public/V_93_admin_footer.html" %>
