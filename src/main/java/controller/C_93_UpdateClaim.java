package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.DAO_93_ReadClaimQuery;
import model.M_93_InsuranceClaim;

/**
 * Servlet implementation class C_93UpdateClaim
 */
@WebServlet("/C_93UpdateClaim")
public class C_93_UpdateClaim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C_93_UpdateClaim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int claim_id = Integer.parseInt(request.getParameter("claim_id"));
		
		
		try {
			DAO_93_ReadClaimQuery claimUptateQuery= new DAO_93_ReadClaimQuery();
			
			M_93_InsuranceClaim claimUpdate = claimUptateQuery.doReadClaimById(claim_id);
			
			request.setAttribute("claimUpdate", claimUpdate);
			
			
			String url = "/V_93_updateClaimForm.jsp";				
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);		
			
			
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        int claim_id = Integer.parseInt(request.getParameter("claim_id"));
        String claim_status = request.getParameter("claim_status");
  
		
		DAO_93_ReadClaimQuery claimQuery;
		try {
			claimQuery = new DAO_93_ReadClaimQuery();
			
			claimQuery.doUpdateClaim(claim_id, claim_status);
			
//			String url = "/V_93_adminReadClaim.jsp";	
			String url = "C_93_ReadClaim";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);	
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
