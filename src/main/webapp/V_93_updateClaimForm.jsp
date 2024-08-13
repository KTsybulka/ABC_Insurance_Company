<%@ include file="public/V_93_admin_header.jsp" %>
<%@ page import="model.M_93_InsuranceClaim" %>

<h1>Update Claim</h1>

<%
    M_93_InsuranceClaim claimUpdate = (M_93_InsuranceClaim) request.getAttribute("claimUpdate");
    if (claimUpdate == null) {
%>
    <p>Error: Claim data is not available.</p>
<%
    } else {
%>

    <form action="C_93UpdateClaim" method="post">
        <label for="claim_id">Claim Id:</label>
        <input type="text" id="claim_id" name="claim_id" value="<%= claimUpdate.getClaim_id() %>" readonly />

        <label for="status">Claim Status:</label>
        <select id="status" name="claim_status">
            <option value="pending">Pending</option>
            <option value="approved">Approved</option>
        </select>

        <br />
        <input type="submit" value="Update" />
    </form>

    <a href='V_93_adminIndex.jsp'>Go Home</a><br/>

<%
    }
%>

<%@ include file="public/V_93_admin_footer.html" %>