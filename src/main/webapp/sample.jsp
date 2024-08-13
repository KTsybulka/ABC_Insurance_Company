        	<form action="AdminAction" method="post">
           	 	<h2>See a user account</h2>
            	<a href="UsAc">View All Users</a>
            	<label>...or a specific user</label>
            	<input type="text" name="userName" placeholder="Enter username">

            	<h2>See product</h2>
            	<a href="RegProds">View All Products</a>
            	<label>...or a specific product</label>
            	<input type="text" name="productName" placeholder="Enter product name">

				<h2>Update product</h2>
            	<label>Enter/update a list of available products</label>
            	<input type="text" name="updateProduct" placeholder="Enter product details">
            	
				<h2>Claim</h2>
            	<label>See the list of recent claims and update their status</label>
            	<input type="text" name="claimId" placeholder="Enter claim ID">

				<h2>Report</h2>
            	<label>See reports: the list of all users, their registered products, and their claims</label>
            	<input type="text" name="report" placeholder="Enter report details">

            	<input type="submit" value="Submit">
        	</form>