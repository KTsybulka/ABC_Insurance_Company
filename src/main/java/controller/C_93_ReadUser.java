package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.DAO_93_ReadUserQuery;

/**
 * Servlet implementation class C_93_ReadUser
 */
@WebServlet("/C_93_ReadUser")
public class C_93_ReadUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public C_93_ReadUser() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		try {
		    // Code that might throw an exception
		DAO_93_ReadUserQuery rq = new DAO_93_ReadUserQuery();
		
		String userid = request.getParameter("userid");
		
		String tableAllUsers, tableOneUser, searchUserForm = "";

		if(userid != null && !userid.isEmpty()) {	
			rq.doReadUsersById(userid);			
			tableOneUser = rq.getHTMLUserTable();
//			System.out.println("Table One : " + tableOne);
			request.setAttribute("tableOneUser", tableOneUser);
		}
					
		searchUserForm = rq.getSerchUserForm();				
//		System.out.println("Search Form: " + searchForm);		
		request.setAttribute("searchUserForm", searchUserForm);
		
		rq.doReadAllUsers();		
		tableAllUsers = rq.getHTMLUserTable();		
		request.setAttribute("tableAllUsers", tableAllUsers);
		

		String url = "/V_93_adminReadUser.jsp";				
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);						
				
		
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}		
	}
}
