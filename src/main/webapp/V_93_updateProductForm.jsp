<%@ include file="public/V_93_admin_header.jsp" %>
<%@ page import="model.M_93_Product" %>

<h1>Update Product</h1>

<%
	M_93_Product productUpdate = (M_93_Product) request.getAttribute("productUpdate");
    if (productUpdate == null) {
%>
    <p>Error: Product data is not available.</p>
<%
    } else {
%>

    <form action="C_93_ProductUpdate" method="post">
        <label for="productid">Product Id:</label>
        <input type="text" id="productid" name="productid" value="<%=  productUpdate.getProductid() %>" readonly />
        <br />

        <label for="product_name">Product Name:</label>
        <input type="text" id="product_name" name="product_name" value="<%=  productUpdate.getProduct_name() %>"/>
        <br />

         <label for="serial_no">Serial No:</label>
        <input type="text" id="serial_no" name="serial_no" value="<%=  productUpdate.getSerial_no() %>"/>
        <br />   
        
         <label for="product_model">Product Model:</label>
        <input type="text" id="product_model " name=".product_model " value="<%=  productUpdate.getProduct_model () %>"/>
        <br />   
        
	     <label for="product_description">Product Description:</label>
        <input type="text" id="product_description" name="product_description" value="<%=  productUpdate.getProduct_description() %>"/>
        <br />
        
        <input type="submit" value="Update" />
        <br />
    </form>

    <a href='V_93_adminIndex.jsp'>Go Home</a><br/>

<%
    }
%>

<%@ include file="public/V_93_admin_footer.html" %>