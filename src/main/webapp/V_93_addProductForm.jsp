<%@ include file="public/V_93_admin_header.jsp" %>


<h1>Add Product</h1>


    <form action="C_93_ProductAdd" method="post">


        <label for="product_name">Product Name:</label>
        <input type="text" id="product_name" name="product_name"/>
        <br />

         <label for="serial_no">Serial No:</label>
        <input type="text" id="serial_no" name="serial_no"/>
        <br />   
        
         <label for="product_model">Product Model:</label>
        <input type="text" id="product_model" name="product_model"/>
        <br />   
        
	     <label for="product_description">Product Description:</label>
        <input type="text" id="product_description" name="product_description"/>
        <br />
        
        <input type="submit" value="Add" />
        <br />
    </form>

    <a href='V_93_adminIndex.jsp'>Go Home</a><br/>


<%@ include file="public/V_93_admin_footer.html" %>